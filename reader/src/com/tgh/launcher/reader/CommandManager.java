package com.tgh.launcher.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {

			  enum possibleTask{get, toggle, set, unset, assign};
	public static void handleCommand(String cmd)
	{
		  Pattern pattern = Pattern.compile("^set\\s+(no)*([^!]*?)(\\??)(!?)\\s*?(=?)\\s*?([.&&[^!\\?=]]*?)$");
		  Matcher matcher = pattern.matcher(cmd);
		  if(matcher.find())
		  {	  
			  possibleTask whatToDo;
			  System.out.println("ují ují kokodák");
			  String variable=matcher.group(2);
			  if(matcher.group(3).equals("?"))
			  {
				  whatToDo=possibleTask.get;
			  }
			  if(matcher.group(4).equals("!"))
			  {
				  whatToDo=possibleTask.toggle;
			  }
			  if(matcher.group(5).equals("="))
			  {
				  whatToDo=possibleTask.assign;
			  }
			  else
				  if(matcher.group(1).equals("no"))
				  {
					  whatToDo=possibleTask.unset;
				  }
				  else
				  {
					  whatToDo=possibleTask.set;
				  }
			  String value=matcher.group(6);

		  System.out.println("var: "+variable);
		  System.out.println("val: "+value);
		  }
	}
	public static void main(String args[])
	{
		handleCommand("set nopipka?=10");
	}
}
