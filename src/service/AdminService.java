package service;

import java.sql.SQLException;
import java.util.List;

import entity.Admin;

public interface AdminService {
	List<Admin> queryByUserName(String userName) throws SQLException;

	boolean updateAdmin(String username, String password);
}
