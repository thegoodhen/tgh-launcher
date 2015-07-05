package com.thegoodhen.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameGenerator implements IStringGenerator{
String readString="NAME kokodak uji uji DESCRIPTION";

	@Override
	public String generateString(File f) {
		
		
		
		StringBuilder sb = new StringBuilder();
		 
		Process p;
		try {
			p = Runtime.getRuntime().exec("timeout 1s whatis -l " +f.getName());//+" | col -b ");
			//System.out.println("koko");
			p.waitFor();
			//System.out.println("dak");
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
		
		
		
		
		
		/*
		//System.out.println("wee");
		  readString=ManPageReader.read(f);
			//System.out.println("woo");
		 // System.out.println(readString);
		  Pattern pattern = Pattern.compile("NAME(.*?)\\s+[A-Z]+\\n",Pattern.DOTALL);
	      Matcher matcher = pattern.matcher(readString);
	  	//System.out.println("waa");
	      if(matcher.find())
	      {
	    		return matcher.group(1);    	  
	      }
	      else
	      {
	    	  return "";
	      }
		*/
	}

}
