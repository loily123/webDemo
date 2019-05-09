package service.impl;

import java.sql.SQLException;

import dao.AdminDao;
import objectFactory.ObjectFactory;
import service.AdminService;
import transaction.Transaction;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao = (AdminDao) ObjectFactory.getoObject("AdminDao");
	private Transaction transaction = (Transaction) ObjectFactory.getoObject("Transaction");

	@Override
	public boolean queryByUserName(String userName, String password) {
		// TODO Auto-generated method stub
		boolean f = false;
		try {
			transaction.startTransaction();
			f = adminDao.queryByName(userName, password);
			transaction.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
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
