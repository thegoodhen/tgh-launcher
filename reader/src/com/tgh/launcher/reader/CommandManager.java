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
	public static void temCommandManager()
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
	public static void handleCommand(String cmd)

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
	get(var);//TODO: Oh snap, I need to return it somehow.
	break;
case set:
	set(var,true);
	break;
case unset:
	set(var,false);
	break;
default:
	break;

}
		System.out.println("var: "+var);
		System.out.println("val: "+val);
	}
	private static void assign(String var, String val)
	{
		//TODO: implement
		try {
			oh.setStringValue(var, val);
		} catch (NoSuchOptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Assigned var "+var+" a value of "+val);
	}
	private static void toggle(String var)
	{
		//TODO: implement
		System.out.println("toggled var: "+var);

	}
	private static void set(String var, boolean on)
	{
		//TODO: implement
		System.out.println("Set var "+var+" to "+on);
	}

	private static void get(String var)
	{
		//TODO: implement
		System.out.println("Got var "+var);
	}
	public static void main(String args[])
	{
		temCommandManager();
		handleCommand("set kokodak=10");
		handleCommand("set     nopipka");
		handleCommand("set     pipka    ");
		handleCommand("set     pipka!  ");
		handleCommand("set     pipka?  ");
		handleCommand("set     kokodak blablabla ? ! pipka?  ");//just messin' around
	}
}
