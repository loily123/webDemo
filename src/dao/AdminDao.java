package dao;

import java.util.List;

import entity.Admin;
import exception.MyWebException;

public interface AdminDao {
	List<Admin> queryByName(String userName);

	boolean updateAdmin(String username, String password);
}
