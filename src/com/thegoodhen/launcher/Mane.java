package com.thegoodhen.launcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Mane {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File[] files=FileGatherer.gatherFiles();
		FileNameGenerator fing=new FileNameGenerator();
		FullNameGenerator fng=new FullNameGenerator();
		FirstSentenceDescGenerator fsdg=new FirstSentenceDescGenerator();
		FullDescGenerator fdg=new FullDescGenerator();
		IStringGenerator gens[]={fing, fng, fsdg, fdg};
		//PrintWriter writer = null;
		String guiAppsPath="/home/thegoodhen/Documents/tgh_launcher_gui.txt";
		String termAppsPath="/home/thegoodhen/Documents/tgh_launcher_term.txt";

	try(PrintWriter guiWriter = new PrintWriter(guiAppsPath, "UTF-8"); PrintWriter termWriter= new PrintWriter(termAppsPath, "UTF-8");) {
		
		for(IStringGenerator isg:gens)
		{
			for(File f:files)
			{
				File desktopFile=new File("/usr/share/applications/"+fing.generateString(f)+".desktop");
				if(desktopFile.exists())//TODO: Actually check the content of the .desktop file if it exists
				{
					guiWriter.write("[["+fing.generateString(f)+"]]\n");
					guiWriter.write(isg.generateString(f)+"\n");
				}
				else
				{
					termWriter.write("[["+fing.generateString(f)+"]]\n");
					termWriter.write(isg.generateString(f)+"\n");
				}
				//System.out.println("[["+fing.generateString(f)+"]]");
				//System.out.println( isg.generateString(f));
				
			}
		}
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


		
		
		
		// TODO Auto-generated method stub

		System.out.println( fng.generateString(new File("")));
		
		System.out.println(fsdg.generateString(new File("")));
		
		System.out.println(fdg.generateString(new File("")));
		//System.out.println(ManPageReader.read(new File("firefox")));
	}

}
