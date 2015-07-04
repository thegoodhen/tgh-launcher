package com.thegoodhen.launcher;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstSentenceDescGenerator implements IStringGenerator{
String readString=".SH   DESCRIPTION kokodak je spesl pwogwam co umi upe vsecko. Zde je dalsi info: Bla bla bla kokodak";

	@Override
	public String generateString(File f) {
		  readString=ManPageReader.read(f);
		  Pattern pattern = Pattern.compile("(\\.SH\\s*DESCRIPTION)(.*)(\\. )",Pattern.DOTALL);
		  Matcher matcher = pattern.matcher(readString);
	      if(matcher.find())
	      {
	    		return matcher.group(2);    	  
	      }
	      else
	      {
	    	  return "";
	      }
	}

}
