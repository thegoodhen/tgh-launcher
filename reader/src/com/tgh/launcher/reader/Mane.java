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
		AppList al=new AppList();
		al.load(guiFile);
		Scanner scan=new Scanner(System.in);
		scan.useDelimiter("\n");
		ArrayList<App> results=new ArrayList<App>();
		
		while(scan.hasNext())
		{
			results=al.findApp(scan.next(), 10);
			
			for(App a:results)
			{
				System.out.println(a.name+" "+a.relevance);
			}
		}
		
		
	
		// TODO Auto-generated method stub

	}

}
