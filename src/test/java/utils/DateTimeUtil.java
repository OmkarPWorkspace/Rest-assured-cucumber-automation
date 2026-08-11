package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil 
{
	public static String getCurrentDateTime()
	{
		   DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

	       return LocalDateTime.now().format(formatter);
	        
	}
	


}
