package sujj.charset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;

public class utf8support {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String s1 = "welcome";
		FileInputStream in = new FileInputStream("c:/1.txt");
		
		SortedMap<String,Charset> map = Charset.availableCharsets();
		System.out.println(map.toString());
		
		List<String> h = Files.readAllLines(Paths.get("c:/1.txt"), Charset.forName("Big5"));
		
		System.currentTimeMillis();
		System.out.println(h);
	}

}
