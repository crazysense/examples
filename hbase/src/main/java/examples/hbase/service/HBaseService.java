package examples.hbase.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.ResultsExtractor;
import org.springframework.stereotype.Component;

@Component
public class HBaseService {
	@Autowired
	private HbaseTemplate template;

	public boolean isTableExist(String tableName) throws Exception {
		boolean isTableExist = false;

		try (Connection conn = ConnectionFactory.createConnection(template.getConfiguration())) {
			try (Admin admin = conn.getAdmin()) {
				isTableExist = admin.tableExists(TableName.valueOf(Bytes.toBytes(tableName)));
			}
		}

		return isTableExist;
	}

	public List<String> getAllTableNames() throws Exception {
		List<String> tableNames = new ArrayList<String>();
		
		try (Connection conn = ConnectionFactory.createConnection(template.getConfiguration())) {
			try (Admin admin = conn.getAdmin()) {
				for (HTableDescriptor tableDescriptors : admin.listTables()) {
					tableNames.add(tableDescriptors.getNameAsString());
				}
			}
		}
		
		return tableNames;
	}

	public List<String> scanRowkeys(String tableName, String... elements) {
		StringBuilder condition = new StringBuilder();

		for (String element : elements) {
			condition.append("(.*)").append(element);
		}
		condition.append("(.*)");

		RegexStringComparator keyComparator = new RegexStringComparator(condition.toString());
		RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, keyComparator);

		Scan rowScan = new Scan();
		rowScan.setFilter(rowFilter);

		List<String> findRowkeys = template.find(tableName, rowScan, new ResultsExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultScanner rs) throws Exception {
				List<String> children = new ArrayList<String>();

				Result result = null;
				while ((result = rs.next()) != null) {
					String key = Bytes.toString(result.getRow());
					children.add(key);
				}

				return children;
			}
		});

		return findRowkeys;
	}
}
