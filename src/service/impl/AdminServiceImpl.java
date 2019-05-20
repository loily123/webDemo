package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.AdminDao;
import entity.Admin;
import exception.MyWebException;
import service.AdminService;
import transaction.Transaction;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;
	private Transaction transaction;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public List<Admin> queryByUserName(String userName) throws SQLException {
		// TODO Auto-generated method stub
		transaction.startTransaction();
		List<Admin> admins = adminDao.queryByName(userName);
		transaction.commit();
		return admins;
	}

	@Override
	public boolean updateAdmin(String username, String password) {
		// TODO Auto-generated method stub
		boolean f = false;
		try {
			transaction.startTransaction();
			f = adminDao.updateAdmin(username, password);
			transaction.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}

}
