package com.backbase.coreUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ConfigManager {

	public static String getProperty(String key) {
		Properties p = new Properties();
		try{
			File f = new File(System.getProperty("user.dir")+"/src/main/resources/Resources.properties");
			FileInputStream fileReader = new FileInputStream(f);
			p.load(fileReader);
			fileReader.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return  p.getProperty(key);	
	}

}
