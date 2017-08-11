package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

public class PropertiesReader {
	Properties propertiesObj;
	FileInputStream objfile;
	public PropertiesReader(String fullPropertiesFilePath){
		this.propertiesObj = new Properties();
		//FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\propertiesFile\\propertiesSetup.properties");
		try {
			 objfile = new FileInputStream(fullPropertiesFilePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Specified file not with full path " + fullPropertiesFilePath + " not found in directory");
		}
	}
	
	public void printKeyValue() throws Exception{
		propertiesObj.load(objfile); 
		
		Enumeration<Object> KeyValues = propertiesObj.keys();
		while (KeyValues.hasMoreElements()) {
			String key = (String) KeyValues.nextElement();
			String value = propertiesObj.getProperty(key);
			System.out.println(key + ":- " + value);
		}
	}
	
	public Hashtable<String, String> getAllKeyValue() {
		Hashtable<String,String> hashTable = new Hashtable<String, String>();
		try {
			propertiesObj.load(objfile);
			
			Enumeration<Object> KeyValues = propertiesObj.keys();
			while (KeyValues.hasMoreElements()) {
				String key = (String) KeyValues.nextElement();
				String value = propertiesObj.getProperty(key);
				hashTable.put(key, value);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return hashTable;
	}
	public List<String> getAllKeys(){
		List<String> allKeys = new ArrayList<String>();
		try {
			propertiesObj.load(objfile);
			
			Enumeration<Object> KeyValues = propertiesObj.keys();
			while (KeyValues.hasMoreElements()) {
				String key = (String) KeyValues.nextElement();
				allKeys.add(key);
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allKeys;
	}
	public String getKeyValue(String keyName){
		try {
			propertiesObj.load(objfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertiesObj.getProperty(keyName);
	}
	public void closeStream(){
		try {
			objfile.close();
		} catch (IOException e) {
		}
	}
	
}

