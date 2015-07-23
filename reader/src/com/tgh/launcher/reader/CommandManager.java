package com.tgh.launcher.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {

	enum possibleTask{get, toggle, set, unset, assign};
	static Pattern assignPattern;
	static Pattern togglePattern;
	static Pattern getPattern;
	static Pattern unsetPattern;
	static Pattern setPattern;
	static OptionHolder oh;
	public static void setOptionHolder(OptionHolder oho)
	{
		oh=oho;
	}
	public CommandManager()
	{
		assignPattern=Pattern.compile("^set\\s+(.*?)\\s*=\\s*(.*?)$");
		togglePattern=Pattern.compile("^set\\s+(.*?)\\s*!\\s*$");
		getPattern=Pattern.compile("^set\\s+(.*?)\\s*\\?\\s*$");
		unsetPattern=	Pattern.compile("^set\\s+no(.*?)\\s*$");
		setPattern=Pattern.compile("^set\\s+(.*?)\\s*$");
		oh=new OptionHolder();
		IntOption kokodak=new IntOption("kokodak",0,0,9);
		oh.addOption(kokodak);
	}
	public static String handleCommand(String cmd) throws InvalidCommandException

	{
		String var="";
		String val="";
		possibleTask t = possibleTask.set;
		Matcher assignMatcher=assignPattern.matcher(cmd);
		Matcher toggleMatcher=togglePattern.matcher(cmd);
		Matcher getMatcher=getPattern.matcher(cmd);
		Matcher unsetMatcher=unsetPattern.matcher(cmd);
		Matcher setMatcher=setPattern.matcher(cmd);
		if(assignMatcher.find())
		{
			t=possibleTask.assign;
			var=assignMatcher.group(1);
			val=assignMatcher.group(2);
		}
		else if(toggleMatcher.find())
		{
			t=possibleTask.toggle;
			var=toggleMatcher.group(1);
		}
		else if(getMatcher.find())
		{
			t=possibleTask.get;
			var=getMatcher.group(1);
		}
		else if(unsetMatcher.find())
		{
			t=possibleTask.unset;
			var=unsetMatcher.group(1);
		}
		else if(setMatcher.find())
		{
			t=possibleTask.set;
			var=setMatcher.group(1);
		}
switch(t)
{
case assign:
	assign(var,val);
	break;
case toggle:
	toggle(var);
	break;
case get:
	return get(var);
case set:
	set(var,true);
	break;
case unset:
	set(var,false);
	break;
default:
	break;

}
		//System.out.println("var: "+var);
		//System.out.println("val: "+val);
return "";
}

	private static void assign(String var, String val) throws InvalidCommandException
	{
		//TODO: implement
		try {
			oh.setStringValue(var, val);
		} catch (NoSuchOptionException e) {
			throw new InvalidCommandException(e.getMessage());

		}
		catch(IllegalArgumentException e)
		{
			throw new InvalidCommandException(e.getMessage());
		}
		//System.out.println("Assigned var "+var+" a value of "+val);
	}
	private static void toggle(String var) throws InvalidCommandException
	{
		if (get(var).equals("true"))
		{
			set(var,false);
		}
		else
		{
			set(var,true);
		}
	}
	private static void set(String var, boolean on) throws InvalidCommandException
	{
		assign(var,on?"true":"false");
	}

	private static String get(String var) throws InvalidCommandException
	{
		try {
			return oh.getStringValue(var);
		} catch (NoSuchOptionException e) {
			throw new InvalidCommandException(e.getMessage());
		}
	}
	public static void main(String args[])
	{

	}
}
