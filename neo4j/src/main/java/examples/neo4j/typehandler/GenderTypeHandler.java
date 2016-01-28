package examples.neo4j.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import examples.neo4j.domain.User;
import examples.neo4j.domain.User.GENDER;

public class GenderTypeHandler extends BaseTypeHandler<User.GENDER> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, GENDER parameter, JdbcType jdbcType)
			throws SQLException {
		// TODO Auto-generated method stub
		ps.setString(i, parameter == GENDER.MALE ? "MALE" : "FEMAIL");
	}

	@Override
	public GENDER getNullableResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnName).equals("MALE") ? GENDER.MALE : GENDER.FEMAIL;
	}

	@Override
	public GENDER getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnIndex).equals("MALE") ? GENDER.MALE : GENDER.FEMAIL;
	}

	@Override
	public GENDER getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return cs.getString(columnIndex).equals("MALE") ? GENDER.MALE : GENDER.FEMAIL;
	}

}
