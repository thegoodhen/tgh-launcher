package com.tgh.launcher.reader;

import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import java.awt.event.InputMethodListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;

public class Gui {

	private JFrame frame;
	private JTextField textField;
	static File guiFile;
	ArrayList<App> results=new ArrayList<App>();
	static AppList al;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//guiFile=new File("/home/thegoodhen/Documents/tgh_launcher_gui.txt");
		guiFile=new File("C:/Users/Acer/tgh-launcher/reader/data/tgh_launcher_gui.txt");
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
		frame.setBounds(100, 100, 200, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	
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
		
		results=al.findApp(textField.getText(), 100);
		
		String lineSep = System.getProperty("line.separator");
		System.out.print(lineSep);
		for(App a:results)
		{
			System.out.println(a.name+" "+a.relevance);
			
			JButton btnLaunch = new JButton(a.name); //add a.relevance to text ? seems irrelevant
			btnLaunch.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
					//TODO: run app
					
						}
						
					}
			);
			
			frame.getContentPane().add(btnLaunch);
			
		}
		
	}
}
