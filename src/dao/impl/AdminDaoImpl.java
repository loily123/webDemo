package dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import dao.AdminDao;
import entity.Admin;
import entity.AdminRowMapping;
import util.JDBCUtil;

public class AdminDaoImpl implements AdminDao {
	private String sql = "";

	@Override
	public boolean queryByName(String userName, String password) {
		// TODO Auto-generated method stub
		List<Admin> admins = null;
		sql = "select id,username,password from admin where username=?";
		admins = JDBCUtil.executeQuery(sql, new AdminRowMapping(), userName);
		if (admins.size() > 0) {
			if (admins.get(0).getPassword().equals(password)) {
				return true;
			}
			JOptionPane.showMessageDialog(null, "密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null, "该用户不存在", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	@Override
	public boolean updateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		int result = 0;
		sql = "update admin set password=? where username=?";
		result = JDBCUtil.executeUpdate(sql, password, username);
		if (result == 1) {
			return true;
		}
		return false;
	}
}