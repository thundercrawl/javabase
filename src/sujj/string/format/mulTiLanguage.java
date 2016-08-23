package sujj.string.format;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 * Locate the localization file by the getbundles automatically, first search
 * Class with locale, then the properties with locale, if the locale not exist
 * will use the default, not throw any exception.
 */
public class mulTiLanguage {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		final String s1= "Sophie Bélanger";
		
		logger.Log.info(s1);
		
		ResourceBundle bd = ResourceBundle.getBundle("msg",Locale.CHINA);
	
		logger.Log.info("locale is : "+Locale.CHINA);
		logger.Log.info(bd.getString("n1"));
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		out.println(bd.getString("n1"));
		//out.println("小马哥");
		out.close();
		
		
	}

}
