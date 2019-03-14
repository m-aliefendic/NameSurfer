/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	
	public static void main(String [] args) {
		new NameSurfer().start(args);
	}
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    label = new JLabel ("Name ");
	    field = new JTextField(10);
	    field.addActionListener(this);
	    graph = new JButton("Graph");
	    clear = new JButton("Clear");	    
	    add(label,SOUTH);
	    add(field,SOUTH);
	    add(graph,SOUTH);
	    add(clear,SOUTH);
	     db= new NameSurferDataBase("names-data.txt");
	     canvas = new NameSurferGraph();
	     add(canvas);
	     
	     
	    addActionListeners();
	   
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==field) {
			if(field.getText().endsWith(" ")) {
				String name=field.getText();
				name= name.substring(0,name.indexOf(" "));
				canvas.addEntry(db.findEntry(name));
				canvas.update();
				field.setText("");
				
			}
			if(field.getText().equals("")) {
				
			}else {	
				canvas.addEntry(db.findEntry(field.getText()));
				canvas.update();
				}
			field.setText("");
		}
		if(e.getActionCommand().equals("Graph")) {
			if(field.getText().endsWith(" ")) {
				String name=field.getText();
				name= name.substring(0,name.indexOf(" "));
				canvas.addEntry(db.findEntry(name));
				canvas.update();
				field.setText("");
				
			}
			if(field.getText().equals("")) {
				
			}else {	
				canvas.addEntry(db.findEntry(field.getText()));
				canvas.update();
				}
			field.setText("");
			
		}
		if(e.getActionCommand().equals("Clear")) {
			canvas.clear();
		}
	}
	
	private NameSurferDataBase db;
	private JLabel label;
	private JTextField field;
	private JButton graph;
	private JButton clear;
	private NameSurferGraph canvas;
}
