package dao;


public interface AdminDao {
	boolean queryByName(String userName,String password);
	boolean updateAdmin(String username,String password);
}
