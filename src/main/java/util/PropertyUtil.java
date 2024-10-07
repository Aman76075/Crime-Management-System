package util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class PropertyUtil {
	public static String getPropertyString(String filename) {
		String connString=null;
		Properties prop=new Properties();
		
		try(FileInputStream fis= new FileInputStream(filename)){
			prop.load(fis);
			connString=prop.getProperty("db.url")+"?user="+prop.getProperty("db.username")+"&password="+prop.getProperty("db.password");
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Sorry! Not able to read the file.");
		}
		return connString;
	}
}
