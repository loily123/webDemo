package dao;

import java.util.List;

import entity.Admin;

public interface AdminDao {
	List<Admin> queryByName(String userName);

	boolean updateAdmin(String username, String password);
}
