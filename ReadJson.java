package JsonToSql;

/**
 * 
 * Author:guoy10
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import JsonToSql.ArrayObject;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadJson {
	public List<ArrayObject> read(String path) {
		List<ArrayObject> objects = new ArrayList<>();
		JsonParser parse =new JsonParser();  //创建JSON解析器
		try {
            JsonObject json=(JsonObject) parse.parse(new FileReader(path));  //创建jsonObject对象
            JsonArray array = json.get("Oberon").getAsJsonArray(); //获取JSON数组
            for(int i=0;i<array.size();i++) {
            	ArrayObject obj = new ArrayObject();//创建对象
            	JsonObject subobject = array.get(i).getAsJsonObject();
            	obj.setNAME(subobject.get("NAME").getAsString());
                obj.setSPA_IP(subobject.get("SPA_IP").getAsString());
                obj.setSPB_IP(subobject.get("SPB_IP").getAsString());
                obj.setBMCA_IP(subobject.get("BMCA_IP").getAsString());
                obj.setBMCA_IP(subobject.get("BMCB_IP").getAsString());
                obj.setDATE(subobject.get("DATE").getAsString());
                obj.setOWNER(subobject.get("OWNER").getAsString());
                obj.setMODE(subobject.get("MODE").getAsString());
                objects.add(obj);
            }
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		return objects;
	}
	
	public String ReadFile(String path) {
		BufferedReader reader = null;
		String lastStr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine())!=null) {
				lastStr += tempString;
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lastStr;
	}
}

