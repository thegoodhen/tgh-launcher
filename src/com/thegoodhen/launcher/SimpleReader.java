package com.thegoodhen.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SimpleReader {
	public static String read(File f)
	{
	StringBuilder sb=new StringBuilder();
	String fileName=f.getPath();
	try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
	{

		String sCurrentLine;

		while ((sCurrentLine = br.readLine()) != null) {
			sb.append(sCurrentLine);
		}

	} catch (IOException e) {
		e.printStackTrace();
	}
	return sb.toString(); 
	}
}

