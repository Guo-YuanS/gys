package JsonToSql;

import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		ReadJson readJson = new ReadJson();
		WriteToSql writeToSql = new WriteToSql();
		writeToSql.sqlCon();
		List<ArrayObject> Oberon = readJson.read("C:\\Users\\gys\\Desktop\\test.json");
		for(int i=0;i<Oberon.size();i++) {
			System.out.println(Oberon.get(i).getNAME());
			System.out.println(Oberon.get(i).getSPA_IP());
			System.out.println(Oberon.get(i).getSPB_IP());
		}
		writeToSql.saveSql(Oberon);
	}
}
