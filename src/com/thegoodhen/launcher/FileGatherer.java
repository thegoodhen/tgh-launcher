package com.thegoodhen.launcher;

import java.io.File;

public class FileGatherer {
static final String programPath="usr/bin/";//TODO: load this from an external settings file
	public File[] gatherFiles()
	{
		File f=new File(programPath);
		File[] files;
		files=f.listFiles();
		return files;
	}

}
