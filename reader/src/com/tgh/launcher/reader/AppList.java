package com.tgh.launcher.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AppList {
int metaInfoCount;
ArrayList<App> theList=new ArrayList<App>();
	void load(File f)
	{
		
		try(BufferedReader br = new BufferedReader(new FileReader(f.getPath()))) {
	
		    String line = br.readLine();
		    String appName="";
		    //String shortSearch="";
		    StringBuilder shortSearchSB=new StringBuilder();
		    metaInfoCount=Integer.parseInt(line);
		    while (line != null) {
		    	line=br.readLine();
		    	if(line==null)
		    	{
		    		break;//fuj
		    	}
		        if (line.equals(">>>BEGIN"))
		        {
		        	shortSearchSB.setLength(0);
		        	appName = br.readLine();
		        	for(int i=1;i<metaInfoCount;i++)
		        	{
		        		br.readLine();//skip all unknown lines
		        	}
		        }
		        else if (line.equals(">>>END"))
		         {
		        	theList.add(new App(appName, shortSearchSB.toString()));	
		         }
		        else
		        {
		        	shortSearchSB.append(line);
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
		String[] keyWords=searchString.split(" ");
		boolean breakout=false;
		for (App a:theList)
		{
			if (breakout)
			{
				break;
			}
			a.relevance=0;
			for(String s:keyWords)
			{
				if (a.name.toLowerCase().contains(s.toLowerCase()))
				{
					a.relevance+=4;
				}
				else //only if not found in the name
				{
					if(a.shortSearch.toLowerCase().contains(s.toLowerCase()))
					{
						a.relevance+=2;
					}
						
				}
			}
			if(a.relevance!=0)
			{
				if (!returnList.contains(a))
				{
					returnList.add(a);
				}
				if(returnList.size()>=maxResults)
				{
					breakout=true;
					break;
				}
			}
		}
		//TODO: Sort here
		Collections.sort(returnList,new relevanceComparator());
		return returnList;
	}
	private class relevanceComparator implements Comparator<App>
	{

		@Override
		public int compare(App a0, App a1) {
			// TODO Auto-generated method stub
			if(a0.relevance>a1.relevance)
				return -1;
			if(a0.relevance==a1.relevance)
				return 0;
			if(a0.relevance<a1.relevance)
				return 1;
			return -1;
		}
		
	}
	
}
