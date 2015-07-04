package com.thegoodhen.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ManPageReader {
	private static String cachePath="~/Desktop/man_ls.txt";
	public static String read(File f)
	{
			 
			StringBuilder sb = new StringBuilder();
	 
			Process p;
			try {
				p = Runtime.getRuntime().exec("man " +f.getName()+" | col -b ");
				System.out.println("koko");
				p.waitFor();
				System.out.println("dak");
				BufferedReader reader = 
	                            new BufferedReader(new InputStreamReader(p.getInputStream()));
	 
	                        String line = "";			
				while ((line = reader.readLine())!= null) {
					sb.append(line + "\n");
					
				}
	 
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
			return sb.toString();
	 
		}
		
}

