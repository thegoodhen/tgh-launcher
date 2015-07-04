package com.thegoodhen.launcher;

import java.io.File;

public class FileNameGenerator implements IStringGenerator{


	@Override
	public  String generateString(File f) {
	
		return f.getName();
	}

}
