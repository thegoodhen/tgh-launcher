package com.thegoodhen.launcher;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameGenerator implements IStringGenerator{
String readString="NAME kokodak uji uji DESCRIPTION";

	@Override
	public String generateString(File f) {
		System.out.println("wee");
		  readString=ManPageReader.read(f);
			System.out.println("woo");
		 // System.out.println(readString);
		  Pattern pattern = Pattern.compile("NAME(.*?)\\s+[A-Z]+\\n",Pattern.DOTALL);
	      Matcher matcher = pattern.matcher(readString);
	  	System.out.println("waa");
	      if(matcher.find())
	      {
	    		return matcher.group(1);    	  
	      }
	      else
	      {
	    	  return "";
	      }
		
	}

}
