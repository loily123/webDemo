package entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.RowMapper;

public class AdminRowMapping implements RowMapper {

	@Override
	public Object rowMapping(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		int id = resultSet.getInt("id");
		String userName = resultSet.getString("username");
		String password = resultSet.getString("password");
		Admin admin = new Admin(userName, password, id);
		return admin;
	}

}
