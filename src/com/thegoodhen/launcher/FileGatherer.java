package com.thegoodhen.launcher;

import java.io.File;

public class FileGatherer {

	static String programPath="/usr/bin/";//TODO: load this from an external settings file

	public FileGatherer(String path)
	{
		programPath=path;
	}
	
	public static File[] gatherFiles()
	{
		File f=new File(programPath);
		System.out.println(programPath);
		File[] files;
		files=f.listFiles();
		return files;
	}

}
