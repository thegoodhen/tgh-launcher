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
	ArrayList<App> findApp(String searchString, int maxResults, int perKeyWordResults)
	{
		ArrayList<App> returnList=new ArrayList<App>();
		String[] keyWords=searchString.split(" ");
		int[] keyWordOccurrences = new int[keyWords.length];
		
		//ArrayList[] listsForKeyWords = new ArrayList[keyWords.length];
		
		//for (int i=0; i<=listsForKeyWords.length;i++){
		//	listsForKeyWords[i] = new ArrayList<App>();
		//}
		
		
		boolean breakout = false;
		for (App a:theList)
		{	
			
			if (breakout){
				break;
			}
			
			
			
			a.relevance=0;
			boolean resultFound = false;
			for(String s:keyWords)
			{
				int kwi; 
				
				for (kwi=0;kwi<keyWords.length;kwi++){
					if (keyWords[kwi].equals(s)){
						break;}
				}	
				
				System.out.println("kwi is " + kwi + " number of results at this kwi is " + keyWordOccurrences[kwi] + "(" + keyWords[kwi] + ")");//debug
				
				System.out.println(keyWordOccurrences[kwi] + "<" + perKeyWordResults);//debug
				if (keyWordOccurrences[kwi] < perKeyWordResults){
				
					resultFound = false;
				
					if (a.name.toLowerCase().contains(s.toLowerCase()))
					{
						a.relevance+=4;
						resultFound = true;
						System.out.println(">->>result found !<<-<"); //debug
					}
					else //only if not found in the name
					{
						if(a.shortSearch.toLowerCase().contains(s.toLowerCase()))
						{
							a.relevance+=2;
							resultFound = true;
							System.out.println(">->>result found !<<-<");//debug

						}
						
					}
				
				
				
					if(resultFound){
				
						keyWordOccurrences[kwi]++;
						System.out.println("incrementing kwo at kwi=" + kwi + " by 1 to "+ keyWordOccurrences[kwi]);//debug
						//Note : this means that one app can increment multiple keyword occurrences. I am unsure if this
						//is the intended behavior, and rewriting the code to prevent this may be complicated.
					}
				
				}
			}
			
			
			
			
			
			
			//checking whether there are not too many results for particular kwi not needed here (I hope).
			//The app will stay at relevance = 0 if none of the kwos let it through.
			if(a.relevance!=0) 
			{
				if (!returnList.contains(a))
				{
					returnList.add(a);
				}
			}
			
			//check whether there are any kwos that still have space left, if not, breakout.
			int filledKeyWords = 0;
			for (int i = 0; i<keyWordOccurrences.length;i++){
				
				if(keyWordOccurrences[i]>=perKeyWordResults){
					filledKeyWords++;
				}
				
			}
			
			if (filledKeyWords == (keyWordOccurrences.length - 1)){
				breakout = true;
			}
			
			
			
		}
		
		
		
		
		//TODO: Sort here
		relevanceComparator compie = new relevanceComparator();
		Collections.sort(returnList,compie);
		
		ArrayList<App> trimmedReturnList=new ArrayList<App>();
		
		int afterTrim;
		
		if (returnList.size()<maxResults){
			afterTrim = returnList.size();
		}
		else
		{
			afterTrim = maxResults;
		}
		
		for(int i=0;i<afterTrim; i++){
			trimmedReturnList.add(returnList.get(i));
		}
		
		
		Collections.sort(trimmedReturnList, compie); //not completely sure if necessary
		
		return trimmedReturnList;
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
			return -1;//required by IDE
		}
		
	}
	
}
