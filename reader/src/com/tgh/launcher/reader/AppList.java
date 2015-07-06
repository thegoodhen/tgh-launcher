package com.tgh.launcher.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AppList {
int metaInfoCount;
ArrayList<App> theList=new ArrayList<App>();
	void load(File f)
	{
		
		try(BufferedReader br = new BufferedReader(new FileReader(f.getPath()))) {
	
		    String line = br.readLine();
		    String appName="";
		    String shortSearch="";
		    metaInfoCount=Integer.parseInt(line);
		    while (line != null) {
		    	line=br.readLine();
		    	if(line==null)
		    	{
		    		break;//fuj
		    	}
		        if (line.equals(">>>BEGIN"))
		        {
		        	shortSearch="";
		        	appName = br.readLine();
		        	for(int i=1;i<metaInfoCount;i++)
		        	{
		        		br.readLine();//skip all unknown lines
		        	}
		        }
		        else if (line.equals(">>>END"))
		         {
		        	theList.add(new App(appName, shortSearch));	
		         }
		        else
		        {
		        	shortSearch+=line;
		        }
		    }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException e)
		{
			
		}
	}
	ArrayList<App> findApp(String searchString, int maxResults)
	{
		ArrayList<App> returnList=new ArrayList<App>();
		String[] keyWords=searchString.split("\\,");
		for (App a:theList)
		{
			if(returnList.size()>=maxResults)
			{
				break;
			}
			a.relevance=0;
			for(String s:keyWords)
			{
				if (a.name.contains(s))
				{
					a.relevance+=4;
				}
				else //only if not found in the name
				{
					if(a.shortSearch.contains(s))
					{
						a.relevance+=2;
					}
						
				}
			}
			if(a.relevance!=0)
			{
				returnList.add(a);
			}
		}
		//TODO: Sort here
		return returnList;
	}
}
