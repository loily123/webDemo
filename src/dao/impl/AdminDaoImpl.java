package dao.impl;

import java.util.List;


import dao.AdminDao;
import entity.Admin;
import entity.AdminRowMapping;
import util.JDBCUtil;

public class AdminDaoImpl implements AdminDao {
	private String sql = "";

	@Override
	public List<Admin> queryByName(String userName) {
		// TODO Auto-generated method stub
		List<Admin> admins = null;
		sql = "select id,username,password from admin where username=?";
		admins = JDBCUtil.executeQuery(sql, new AdminRowMapping(), userName);
		if (admins.size() > 0) {
			return admins;
		}
		return null;
	}

	@Override
	public boolean updateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		int result = 0;
		sql = "update admin set password=? where username=?";
		result = JDBCUtil.executeUpdate(sql, password, username);
		if (result > 0) {
			return true;
		}
		return false;
	}
}