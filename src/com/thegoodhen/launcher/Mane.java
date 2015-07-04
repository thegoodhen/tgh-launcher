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
	}

}
