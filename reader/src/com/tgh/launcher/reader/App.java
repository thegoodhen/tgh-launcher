package com.tgh.launcher.reader;

public class App {

String name;
String shortSearch;
String longSearch;
int relevance;

public App(String name, String shortSearch) {
	
	this.name=name;
	this.shortSearch=shortSearch;
}

@Override
public boolean equals(Object o)
{
	if (o instanceof App)
	{
		return name.equals(((App) o).name);
	}
	return false;
	
}

}
