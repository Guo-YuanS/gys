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
			Class.forName("com.mysql.jdbc.Driver");//�������ݿ�����
			System.out.println("�������ݿ������ɹ�");
			String url = "jdbc:mysql://localhost:3306/mysql";//���ݿ�ip:�˿�/���ݿ�����
			String user	= "root";//�˻���
			String password = "123456";//����
			//�������ݿ����ӣ���ȡ���Ӷ���
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("���ݿ����ӳɹ�");
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
			//		+ "BMCA_IP varchar(40),BMCB_IP varchar(40),OWNER varchar(40),DATE varchar(40),MODE varchar(40));");//������
			PreparedStatement prep = conn.prepareStatement("insert into oberon values(?);");//��������

			for(int i=0;i<Oberon.size();i++) {
				try {
					 //ʹ��ObjectOutputStream���������л�������ByteArrayOutputStream�У������һ��byte���͵�����ֱ�Ӵ������ݿ�
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

