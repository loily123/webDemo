package service;

public interface AdminService {
	boolean queryByUserName(String userName, String password);
	boolean updateAdmin(String username,String password);
}
