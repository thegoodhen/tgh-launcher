package com.tgh.launcher.reader;

public class OptionUsageExample {
static OptionHolder oHolder;
static CommandManager cmdMngr=new CommandManager();
	public static void main(String[] args) {
		
		oHolder=new OptionHolder();
		oHolder.addOption(new IntOption("pipka", 10,0,50));
		oHolder.addOption(new IntOption("kokodak", 0,0,100));
		oHolder.addOption(new IntOption("kvok", 10,0,100));
		cmdMngr.setOptionHolder(oHolder);
		try {
			int test=oHolder.getInteger("pipka");
			System.out.println("pipka*2="+2*test);
		} catch (NoSuchOptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

////COMMAND INTERPRETING BELOW
		try {
			cmdMngr.handleCommand("set kokodak=10");
			System.out.println(cmdMngr.handleCommand("set kokodak?"));
			cmdMngr.handleCommand("set pipka=10000");
		} catch (InvalidCommandException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: "+e.getMessage());
		}
	}

}
