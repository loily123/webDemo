package util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	Object rowMapping(ResultSet resultSet) throws SQLException;
}
