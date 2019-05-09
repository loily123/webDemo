package transaction;

import java.sql.SQLException;

public interface Transaction {
	void startTransaction() throws SQLException;

	void commit() throws SQLException;

	void rollback() throws SQLException;
}
