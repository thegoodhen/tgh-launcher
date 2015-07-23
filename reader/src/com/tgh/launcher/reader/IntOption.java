package com.tgh.launcher.reader;

public class IntOption implements Option<Integer>{
int value;
int minValue;
int maxValue;

public IntOption(String name, int value, int minValue, int maxValue)
{
	this.name=name;
	this.value=value;
	this.minValue=minValue;
	this.maxValue=maxValue;
}
String name;
	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public String getStringValue() {
		return ((Integer)value).toString();
	}

	@Override
	public void setValue(Integer value) {
		this.value=value;
	}

	@Override
	public void setStringValue(String value) {
	try
	{
		this.value=Integer.parseInt(value);
		if(this.value>maxValue||this.value<minValue)
		{
			throw new IllegalArgumentException("Value "+value+ " is not withing range <"+minValue+", "+maxValue+">.");
		}
	}
	catch(NumberFormatException e)
	{
		throw new IllegalArgumentException(value+" is not a valid number.");
	}

	}

	@Override
	public String getName() {
		return name;
	}

}
