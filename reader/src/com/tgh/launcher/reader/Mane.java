package com.tgh.launcher.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Mane {

	/**
	 * @param args
	 */
	static File guiFile;
	public static void main(String[] args) {
		guiFile=new File("/home/thegoodhen/Documents/tgh_launcher_gui.txt");
		if (!guiFile.exists()){
		guiFile=new File("C:/Users/Acer/tgh-launcher/reader/data/tgh_launcher_gui.txt");
		}
		AppList al=new AppList();
		ArrayList<App> results=new ArrayList<App>();
		
		al.load(guiFile);
		Scanner scan=new Scanner(System.in);
		scan.useDelimiter(System.getProperty("line.separator"));
	
		
		while(scan.hasNext())
		{
			results=al.findApp(scan.next(), 10, 5);
			
			
			for(App a:results)
			{
				System.out.println(a.name+" "+a.relevance);
			}
		}
		
		
	
		// TODO Auto-generated method stub

	}

}
