package com.thegoodhen.launcher;

import java.io.File;

/**
 * 
 * @author thegoodhen
 * This interface is a common abstraction for all classes that, given the name of the program
 * as a file object, find some info about it and return it afterwards.
 */

public interface IStringGenerator {

	/**
	 * Return a string that somehow corresponds with the file given and describes it in some way.
	 * @param f the file to get info about
	 */
	public String generateString(File f);

}
