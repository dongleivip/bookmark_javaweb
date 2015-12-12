package com.homework.util.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class JsonFileUtil {

	private static String path = JsonFileUtil.class.getClassLoader().getResource("").getPath();
	private static String CONFIGFILENAME = "InitJsonData";
	private static Properties prop = null;

	private JsonFileUtil() {

	}
	
	static {
		try {
			prop = new Properties();
			prop.load(new FileReader(JsonFileUtil.class.getClassLoader().getResource("config.properties").getPath()));
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static String ReadJsonFile(){
		System.out.println(path + prop.getProperty(CONFIGFILENAME));
		return ReadFile(path + prop.getProperty(CONFIGFILENAME));
	}
	
	public static String ReadJsonFile(String fileName){
		System.out.println(path + fileName);
		return ReadFile(path + fileName);
	}

	public static String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

}
