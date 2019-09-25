package JsonToSql;

import java.sql.SQLException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {
		ReadJson readJson = new ReadJson();
		WriteToSql writeToSql = new WriteToSql();
		writeToSql.sqlCon();
		List<ArrayObject> Oberon = readJson.read("C:\\Users\\gys\\Desktop\\test.json");
		writeToSql.saveSql(Oberon);
	}
}
