package com.tgh.launcher.reader;

import java.util.HashMap;

public class OptionHolder {
HashMap<String, Option> map=new HashMap<String, Option>();
public Option getOption(String key)
{
	return map.get(key);
}
public String getStringValue(String key) throws NoSuchOptionException
{ 
	try
	{
	return map.get(key).getStringValue();
	}
	catch(NullPointerException e)
	{
		throw new  NoSuchOptionException("No option of name "+key+" found.");
	}
}
public void setStringValue(String key, String value) throws NoSuchOptionException
{
	try
	{
         map.get(key).setStringValue(value);
	}
	catch(NullPointerException e)
	{
		throw new NoSuchOptionException("No option of name "+key+" found.");
	}
}
public void addOption(Option o)
{
	map.put(o.getName(), o);
}
}
