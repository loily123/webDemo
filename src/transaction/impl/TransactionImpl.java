package transaction.impl;

import java.sql.*;
import transaction.Transaction;
import util.JDBCUtil;

public class TransactionImpl implements Transaction {
	@Override
	public void startTransaction() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConnection();
		conn.setAutoCommit(false);
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConnection();
		conn.commit();
		JDBCUtil.close(null, null, conn);
	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=JDBCUtil.getConnection();
		conn.rollback();
		JDBCUtil.close(null, null, conn);
	}

}
