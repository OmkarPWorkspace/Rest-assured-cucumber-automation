package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	public static String readProperty (String propertyName) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties ();
		
		prop.load(new FileInputStream("src\\test\\resources\\Config.properties"));
		
		return prop.getProperty(propertyName);
		
	}

}
