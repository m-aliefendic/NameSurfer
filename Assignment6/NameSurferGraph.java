/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;

import acmx.export.javax.swing.JLabel;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {	
		en= null;
		
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entr.removeAll(entr);
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		en = entry;
		entr.add(en);
		
		}
		
		
	
	
		
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {

		removeAll();
		
		int getX=3;
		int dece = START_DECADE;
		int x=8;
		for(int i=0;i<NDECADES;i++) {
			line1= new GLine (getX,0,getX,getHeight());
			lbDec= new GLabel(""+dece);
			line1.setColor(Color.black);
			lbDec.setColor(Color.BLACK);
			add(lbDec,x,getHeight()-lbDec.getAscent()/2);
			add(line1);
			getX+=getWidth()/NDECADES;
			x+=getWidth()/NDECADES;
			dece+=10;
			}
		line1= new GLine (0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
		add(line1);
		line1= new GLine (0,getHeight()-GRAPH_MARGIN_SIZE,getWidth(),getHeight()-GRAPH_MARGIN_SIZE);
		add(line1);
		
		int f=0;
		for(int i=0; i<entr.size();i++,f++) {
			
			if(f==4)
				f=0;
			
			String name;
			NameSurferEntry ent = entr.get(i);
			name= ent.getName();
			int dec=1900;
			int xx=8;
			double y;
			if(ent.getRank(dec)==0) {
				y=((double)(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK)*MAX_RANK;}
			else {	
				y=((double)(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK)*ent.getRank(dec);}
			GLabel lb = new GLabel(name+" "+ent.getRank(dec));
			lb.setColor(col[f]);
			add(lb,xx,y+GRAPH_MARGIN_SIZE);
			int x1=xx-5;
			int y1=(int)y+GRAPH_MARGIN_SIZE;
			dec+=10;
			xx+=getWidth()/NDECADES;
			int x2=xx-5;
				
				for(int j=0; j<10;j++) {
					if(ent.getRank(dec)==0) {
						y=((double)(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK)*MAX_RANK;}
					else {	
						y=((double)(getHeight()-2*GRAPH_MARGIN_SIZE)/MAX_RANK)*ent.getRank(dec);}
					
				GLine gl= new GLine(x1,y1,x2,y+GRAPH_MARGIN_SIZE);
				gl.setColor(col[f]);
				add(gl);
				
				lb = new GLabel(name+" "+ent.getRank(dec)); 
				lb.setColor(col[f]);
				
				add(lb,xx,y+GRAPH_MARGIN_SIZE);
				x1=x2;
				y1=(int)y+GRAPH_MARGIN_SIZE;
				dec+=10;
				
				xx+=getWidth()/NDECADES;
				x2=xx-5;
			
			}
				
	  }
	
			
   }

	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private NameSurferEntry en;
	private GLine line1;
	private GLabel lbDec;
	private ArrayList<NameSurferEntry> entr = new ArrayList<NameSurferEntry>();
	private Color[] col=  new Color[] {
		Color.black,Color.red,Color.blue ,Color.MAGENTA
	};
	
	
}
