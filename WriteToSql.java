package JsonToSql;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.BatchUpdateException;
/**
 * 
 * Author:guoy10
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class WriteToSql {
	Connection conn;
	Statement stat;
	
	public void sqlCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
			System.out.println("加载数据库驱动成功");
			String url = "jdbc:mysql://localhost:3306/mysql";//数据库ip:端口/数据库名称
			String user	= "root";//账户名
			String password = "123456";//密码
			//建立数据库连接，获取连接对象
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("数据库连接成功");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveSql(List<ArrayObject> Oberon) throws SQLException{
		int[] arr = null;
		try {
			stat = conn.createStatement();
			//stat.executeUpdate("create table Oberon(NAME varchar(40),SPA_IP varchar(40),SPB_IP varchar(40),"
			//		+ "BMCA_IP varchar(40),BMCB_IP varchar(40),OWNER varchar(40),DATE varchar(40),MODE varchar(40));");//创建表
			PreparedStatement prep = conn.prepareStatement("insert into oberon values(?);");//插入数据

			for(int i=0;i<Oberon.size();i++) {
				try {
					 //使用ObjectOutputStream将对象序列化，传入ByteArrayOutputStream中，输出到一个byte类型的数组直接存入数据库
					ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
					objectOutputStream.writeObject(Oberon.get(i));
					objectOutputStream.flush();
					byte[] data=arrayOutputStream.toByteArray();
					String tmp = new String(data);
					prep.setObject(1,tmp);
					prep.addBatch();
					arrayOutputStream.close();
					objectOutputStream.close();
					conn.setAutoCommit(false);
					prep.executeBatch();
					conn.setAutoCommit(true);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}finally {
			stat.close();
			conn.close();
	}
}
}

