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

	try(PrintWriter writer = new PrintWriter("/home/thegoodhen/Documents/tgh_launcher_data.txt", "UTF-8");) {
		
		for(IStringGenerator isg:gens)
		{
			for(File f:files)
			{
				writer.write("[["+fing.generateString(f)+"]]\n");
				writer.write(isg.generateString(f)+"\n");
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
