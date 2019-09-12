package com.amazon.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProperticesFileUtil {
	public static String getProperty(String key) throws Throwable, Throwable {
		Properties config_propertices = new Properties();
		config_propertices
				.load(new FileInputStream("C:\\Users\\mounika\\git\\ojt65\\Amazon_TestNG\\PropertiesFile\\Environment.properties"));

		return config_propertices.getProperty(key);
	}
}
