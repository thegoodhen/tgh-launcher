package com.thegoodhen.launcher;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FullNameGenerator implements IStringGenerator{
String readString=".SH NAME kokodak uji uji .SH";

	@Override
	public String generateString(File f) {
		  
		  Pattern pattern = Pattern.compile("(\\.SH\\s*NAME)(.*)(\\.SH)",Pattern.DOTALL);
	      Matcher matcher = pattern.matcher(readString);
	      matcher.find();
		return matcher.group(2);
	}

}
