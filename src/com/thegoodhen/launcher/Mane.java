package com.thegoodhen.launcher;

import java.io.File;

public class Mane {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File[] files=FileGatherer.gatherFiles();
		FileNameGenerator fing=new FileNameGenerator();
		FullNameGenerator fng=new FullNameGenerator();
		FirstSentenceDescGenerator fsdg=new FirstSentenceDescGenerator();
		FullDescGenerator fdg=new FullDescGenerator();
		
		IStringGenerator gens[]={fing, fng, fsdg, fdg};
		
		for(IStringGenerator isg:gens)
		{
			for(File f:files)
			{
				System.out.println("[["+fing.generateString(f)+"]]");
				System.out.println( isg.generateString(f));
				
			}
		}
	
		// TODO Auto-generated method stub

		System.out.println( fng.generateString(new File("")));
		
		System.out.println(fsdg.generateString(new File("")));
		
		System.out.println(fdg.generateString(new File("")));
		//System.out.println(ManPageReader.read(new File("firefox")));
	}

}
