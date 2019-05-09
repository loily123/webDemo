package util;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.sql.*;
import javax.swing.*;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

public class JDBCUtil {
	static Logger log = Logger.getLogger(JDBCUtil.class);
	static Properties properties = new Properties();
	static DataSource dataSource;
	static Connection connection = null;
	public static int num = 1, end = 1;
	static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	static {
		try {
			properties.load(new FileInputStream("src/conn.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "配置文件路径错误", "错误提示", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "读写文件错误", "错误提示", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		connection = local.get();
		try {
			if (connection == null) {
				connection = dataSource.getConnection();
				log.debug("获得连接" + num);
				local.set(connection);
				num++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "配置url或用户名密码信息错误", "错误提示", JOptionPane.ERROR_MESSAGE);
		}
		return connection;
	}

	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
				log.debug("关闭resultSet");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
				log.debug("关闭执行sql");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				local.get().close();
				local.remove();
				log.debug("关闭连接" + end);
				end++;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setParam(PreparedStatement preparedStatement, Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			try {
				preparedStatement.setObject(i + 1, objects[i]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int executeUpdate(String sql, Object... objects) {
		connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		connection = getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				preparedStatement.setObject(i + 1, objects[i]);
			}
			log.debug(preparedStatement);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, preparedStatement, null);
		}
		return result;
	}

	public static <T> List<T> executeQuery(String sql, RowMapper rowMapper, Object... objects) {
		connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Object> arrayList = new ArrayList<Object>();
		connection = getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			setParam(preparedStatement, objects);
			log.debug(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Object o = rowMapper.rowMapping(resultSet);
				arrayList.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement, null);
		}
		return (List<T>) arrayList;
	}
}
