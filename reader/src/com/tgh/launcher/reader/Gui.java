package com.tgh.launcher.reader;

import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import java.awt.event.InputMethodListener;
import java.io.File;
import java.util.ArrayList;
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
		frame = new JFrame();
		frame.setType(Type.UTILITY);
		frame.setUndecorated(true);
		
		GraphicsDevice screen = frame.getGraphicsConfiguration().getDevice(); //this should return the screen on which the window is open. http://stackoverflow.com/questions/6322627/java-toolkit-getting-second-screen-size
		int width = screen.getDisplayMode().getWidth();
		int height = screen.getDisplayMode().getHeight()/16; //not important, frame.pack() resizes after each btnLaunch add
		
		frame.setBounds(0, 0, width, height);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
	
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
				
		
				
		
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//JButton btnTest = new JButton("test");
		//frame.getContentPane().add(btnTest);
	}
	public void updateSearchResults()
	{
		
		results=al.findApp(textField.getText(), 5, 10);
		
		String lineSep = System.getProperty("line.separator");
		System.out.print(lineSep);
		
		
		for(App a:results)
		{
			System.out.println(a.name+" "+a.relevance);
			
			
			addBtn(a.name);
			
			
			if(!packed){	//the if needs to be removed when multiple lines of buttons become a thing.
			frame.pack();
			packed = true;
				}
		}
		
	}
	
	private void addBtn(String btnText){
		
		JButton btnLaunch = new JButton(btnText); 
		btnLaunch.setMargin(new Insets(5,0,5,0));
		
		btnLaunch.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {						
					String appName = ((JButton)e.getSource()).getText(); //to be used for running the app
					System.out.println("The user, in their eternal wisdom, commands me to open " + appName); //debug
					//TODO: actually run the app
					
					}
					
				}
		);
		
		frame.getContentPane().add(btnLaunch);
		
		
	}
	
}
