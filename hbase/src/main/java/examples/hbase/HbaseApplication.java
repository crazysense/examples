package examples.hbase;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import examples.hbase.service.HBaseService;

@ImportResource("classpath:/META-INF/spring/app-config.xml")
@ComponentScan(basePackages = "examples.hbase")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class HbaseApplication {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(HbaseApplication.class).web(false).run(args);

		HBaseService service = context.getBean(HBaseService.class);

		// Show all tables
		try {
			List<String> allTableNames = service.getAllTableNames();

			System.out.println("=== Get All Tables");
			for (String tableName : allTableNames) {
				System.out.println(tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Check table exist
		String tableName = "test";
		boolean isTableExist = false;

		try {
			isTableExist = service.isTableExist(tableName);
			System.out.println("=== Is Table [ " + tableName + " ] Exist ? " + isTableExist);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Get rowkeys by elements
		if (isTableExist) {
			List<String> findRowKeys = service.scanRowkeys("test", "row");
			System.out.println("=== Get Matched Rowkey List");
			for (String findRowkey : findRowKeys) {
				System.out.println(findRowkey);
			}
		}
	}
}
