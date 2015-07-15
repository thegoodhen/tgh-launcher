package com.tgh.launcher.reader;

import java.awt.AWTKeyStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.InputMethodListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;

public class Gui {

	private JFrame frame;
	private JTextField textField;
	static File guiFile;
	ArrayList<App> results=new ArrayList<App>();
	static AppList al;
	static boolean packed;
	private ArrayList<LaunchButton> existingBtns;
	private ArrayList<LaunchButton> oldBtns;
	private JPanel btnsPanel;
	private int shownBtns;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		packed = false;
		guiFile=new File("/home/thegoodhen/Documents/tgh_launcher_gui.txt");
		if (!guiFile.exists()){
		guiFile=new File("C:/Users/Acer/tgh-launcher/reader/data/tgh_launcher_gui.txt");
		}
		al=new AppList();
		al.load(guiFile);
		
 
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		existingBtns=new ArrayList<LaunchButton>();
		oldBtns=new ArrayList<LaunchButton>();
		frame = new JFrame();
		frame.setType(Type.POPUP);
		frame.setUndecorated(true);
		GraphicsDevice screen = frame.getGraphicsConfiguration().getDevice(); //this should return the screen on which the window is open. http://stackoverflow.com/questions/6322627/java-toolkit-getting-second-screen-size
		int width = screen.getDisplayMode().getWidth();
		int height = screen.getDisplayMode().getHeight()/16; //not important, frame.pack() resizes after each btnLaunch add
		
		frame.setBounds(0, 0, width, height);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		
		
		//dispatcher code taken from http://portfolio.planetjon.ca/2011/09/16/java-global-jframe-key-listener/
		
		//Custom dispatcher
		class KeyDispatcher implements KeyEventDispatcher {
		    public boolean dispatchKeyEvent(KeyEvent e) {
		        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
		        	System.exit(0);
		        
		        }
		        
		        if(e.getID()==KeyEvent.KEY_PRESSED && e.getKeyCode()==KeyEvent.VK_TAB){
		        	
		        	
		        	if(!textField.hasFocus()){
		        		textField.requestFocusInWindow(); //set focus to textfield
		        		return true;
		        	}
		        	
		        	if (existingBtns.size()>=2){
		        		existingBtns.get(1).requestFocusInWindow(); //set focus to 2nd button
		        		return true;
		        	}
		        	
		        	if (existingBtns.size()==1){
		        		existingBtns.get(0).requestFocusInWindow(); //set focus to 1st button
		        		return true;
		        	}
		        	
		        	return true; // if textfield has focus and there are no buttons, do nothing
		        
		        }
		        
		      //Allow the event to be redispatched
		        return false;
		    }
		}
		
		
		//Hijack the keyboard manager
		KeyboardFocusManager manager =
		         KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher( new KeyDispatcher());
		 
	
		textField = new JTextField();
			
		textField.getDocument().addDocumentListener(new DocumentListener() {

			  public void changedUpdate(DocumentEvent e) {
				  	  updateSearchResults();
				  }
				  public void removeUpdate(DocumentEvent e) {
					  updateSearchResults();
				  }
				  public void insertUpdate(DocumentEvent e) {
					  updateSearchResults();
				  }});
		textField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					existingBtns.get(0).doClick();
				}
				catch(Exception e){}
				
			}
			
			
		});
		
				
		
				
		
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 3));
		shownBtns = 0;
	    btnsPanel.setFocusCycleRoot(true); //this forces the focus traversal to cycle inside the panel
	    
		frame.getContentPane().add(btnsPanel);

		
		//following 4 statements are to suppress normal tab behavior
		frame.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET );
		frame.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET );
		
		frame.getContentPane().setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET );
		frame.getContentPane().setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, Collections.EMPTY_SET );
		
		Set<AWTKeyStroke> fwSet = new HashSet<AWTKeyStroke>();
		Set<AWTKeyStroke> bwSet = new HashSet<AWTKeyStroke>();
		
		
		fwSet.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_L, 0));
		bwSet.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_H, 0));
		
		btnsPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,fwSet);
		btnsPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,bwSet);
		
		
		//LaunchButton btnTest = new LaunchButton("test");
		//frame.getContentPane().add(btnTest);
	}
	public void updateSearchResults()
	{
		
		results=al.findApp(textField.getText(), 5, 10);
		
		
		
		
		//oldBtns = new ArrayList<LaunchButton>(existingBtns);
		
		//check if enough buttons really exist
		if(existingBtns.size()>=results.size()){
			//all is well
			}
		else if(existingBtns.size()<results.size()){
			//we need new buttons here.
			for (int neededBtns = results.size() - existingBtns.size();neededBtns>0;neededBtns--){
				LaunchButton butt = new LaunchButton();
				existingBtns.add(butt);
				btnsPanel.add(butt);
				System.out.println("adding a new button ! needed btns left : " + (neededBtns -1));
				/*Since buttons generated here do not get added to shownBtns, they will likely be unnecessarily
				 * set to visible in following code even though they need not be. I decided to keep it that way,
				 * since it would likely be annoying to handle.
				 */
			}
			
		}
		
		//show or hide buttons
		int visibleDiff = (shownBtns - results.size()); //number of btns currently shown - number of btns that will be needed.
		
		System.out.print("Visiblediff is " + visibleDiff);
		if(visibleDiff == 0){
			//all is okay
		}
		else if(visibleDiff < 0){ 
			//not enough shown btns
			
			int lastVisibleIndex = shownBtns - 1; // ! arraylist is zero-based 
			
			for(int i = - visibleDiff;i>0;i--){
				existingBtns.get(lastVisibleIndex+i).setVisible(true);
			}	
		}
		else if(visibleDiff > 0){
			//too many btns showing
			
			int lastVisibleIndexTarget = results.size() - 1; // ! arraylist is zero-based 
			
			for(int i = visibleDiff;i>0;i--){
				existingBtns.get(lastVisibleIndexTarget+i).setVisible(false);
			}
			
		}
		
		shownBtns = results.size();
		
		for (int i=0;i<shownBtns;i++){
			App a = results.get(i);
			String lineSep = System.getProperty("line.separator");
			System.out.print(lineSep);
			System.out.println(a.name+" "+a.relevance);
			
			existingBtns.get(i).changeText(a.name);
			
		}
		
		
		//if(!packed){	//the if needs to be removed when multiple lines of buttons become a thing.
			frame.pack();
			//packed = true;
				//}
			
	}

	
	private class LaunchButton extends JButton{
		String theAppName = "";
		
		public void changeText(String fullAppName){
			theAppName = fullAppName;
			this.setText(processAppName(theAppName));
		}
		
		LaunchButton(){
			super("");
			

			this.setMargin(new Insets(5,0,5,0));
			
			this.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {						
						System.out.println("The user, in their eternal wisdom, commands me to open " + theAppName); //debug
						
						try {
							Process p = Runtime.getRuntime().exec(theAppName);
							System.exit(0);
						} catch (IOException e1) {
							System.out.println("Application launch failure");
							e1.printStackTrace();
						}//+" | col -b ");
						}
						
					}
			
			);
			/**Following code taken from 
			 * 		http://www.devx.com/DevX/Tip/31605
			 * credits : Milan Zivkovic
			 * Makes the button fire an actionPerformed on enter or spacebar press,
			 * as opposed to just spacebar.
			 * TODO: Remove spacebar triggering
			 */
			super.registerKeyboardAction(
	                super.getActionForKeyStroke(
	                        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
	                        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
	                        JComponent.WHEN_FOCUSED);
	        super.registerKeyboardAction(
	                super.getActionForKeyStroke(
	                        KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
	                        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	                        JComponent.WHEN_FOCUSED);
			
			//END of borrowed code
		}
		
		String processAppName(String appNameToProcess){
			// method stub for future shortening / whatever of the appName before displaying
			return appNameToProcess;
		}
		
		
		
		
		
		
		
		
		
		
	}
}
