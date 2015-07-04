package com.thegoodhen.launcher;

import java.io.File;

public class Mane {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FullNameGenerator fng=new FullNameGenerator();
		System.out.println( fng.generateString(new File("")));
		FirstSentenceDescGenerator fsdg=new FirstSentenceDescGenerator();
		System.out.println(fsdg.generateString(new File("")));
		FullDescGenerator fdg=new FullDescGenerator();
		System.out.println(fdg.generateString(new File("")));
		System.out.println(ManPageReader.read(new File("firefox")));
	}

}
