package ex_1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.jhlabs.image.GaussianFilter;

public class previewFrame extends JFrame{
	
	int displayState;
	int row_position=1;
	int col_position=1;
	
	int rect_x=100;
	int rect_y=100;
	int rect_x_w=100;
	int rect_y_w=100;
	
	int gaussian_degree = 0;
	
	int[][] table = null;	
	tableInfo tableinfo;
	
	int display_width = 1920;  //FHD
	int display_height = 1080;
	
	int box = 80;
	
	BufferedImage img;
	BufferedImage Gaussian_img;
	GraphicsDevice device;
	
	GaussianFilter Gaussian;
	
	public previewFrame(){
		//this.device = device;
		//Dimension dim_mask =new Dimension(500,0);
		//this.setMinimumSize(dim_mask);
		//this.setSize(dim_mask);
		if(this.isAlwaysOnTopSupported()){
			System.out.println("yes");
			AlwaysOnTop();
		}

		this.setUndecorated(true);
		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//device.setFullScreenWindow(this);
		this.setResizable(false);
		this.mouseCut();

		//init();
		
		img = new BufferedImage(display_width,display_height, BufferedImage.TYPE_INT_RGB);


	}
	public void setDisplayDevice(GraphicsDevice device){
		this.device = device;
		init();
	}
	
	public GraphicsDevice getdevice(){
		return device;
	}	
	
	public void init(){
		DisplayMode mode = device.getDisplayMode();
		display_width = mode.getWidth();
		display_height = mode.getHeight();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		super.paint(g);
		Graphics2D img_g = img.createGraphics();
		
		DisplayMode mode = device.getDisplayMode();
		display_width = mode.getWidth();
		display_height = mode.getHeight();
		
		
		switch (displayState) {
		case 0 : //Blank
			g2.setBackground(Color.black);
			g2.clearRect(0, 0, display_width, display_height);
			System.out.println("blank");
			return;
		case 1 : //checking
			Shape s;
			//table = tableinfo.getTable();
			
			//int box = 60; 
			
			double rows = table.length;
			double cols = table[0].length;			
			double row_size = display_width/rows;
		    double col_size = display_height/cols;
		    
		    double rectangle_x = 0, rectangle_y = 0;
			
			g2.setColor(Color.black);
			s = new Rectangle2D.Double(0, 0, display_width, display_height);
			g2.fill(s);
			
			for(int r=0 ;r<rows ;r++){
				for(int c=0 ; c<cols ;c++){
					
					g2.setColor(Color.RED);
					s = new Rectangle2D.Double(row_size*r,col_size*c, row_size, col_size);
					g2.draw(s);
					
					if((row_position-1 == r) &&(col_position-1 ==c)){
						int color = table[row_position-1][col_position-1];
						g2.setColor(new Color(color,color,color));
						System.out.println("color:"+color);
						if(r==0){
							rectangle_x = 0;
							//s = new Rectangle2D.Double(r*row_size, c*col_size,50, 50);
						}
						if(r==rows-1){
							rectangle_x = (row_size-box);
						}
						
						if(c==0){
							rectangle_y = 0;
						}
						if(c==cols-1){
							rectangle_y = (col_size-box);
						}
						
						if(r < rows-1 && r > 0){
							rectangle_x = (row_size/2)-(box/2);
						}
						if(c < cols-1 && c > 0){
							rectangle_y = (col_size/2)-(box/2);
						}
						
						s = new Rectangle2D.Double(r*row_size+rectangle_x, c*col_size+rectangle_y, box, box);

						g2.fill(s);
					}
				}
			}
			//g2.drawImage(img, 0, 0, display_width, display_height, this);
			System.out.println("checking");
			
			return;
		case 2 : //Gaussian
			
			Gaussian = new GaussianFilter(gaussian_degree);
			Gaussian_img = null;
			Gaussian_img = Gaussian.filter(img,Gaussian_img);

			g2.drawImage(Gaussian_img, 0, 0, display_width, display_height, this);
			
			return;
		}

	}
	
	public void setDisplayState(int displayState) {
		this.displayState = displayState;
	}
	
	public void showBlank() {
		setDisplayState(0);
		System.out.println("preview:blankstart");
		repaint();
	}
	
	public void setBox(int box_size){
		setDisplayState(1);
		box = box_size;
		System.out.println("checkingbox-change");
		repaint();
	}
	
	///////////////////
	public void showcheckview(int row_position,int col_position,int[][] table) {
		this.row_position = row_position;
		this.col_position = col_position;
		this.table = table;
		System.out.println("preview:"+row_position +","+col_position);
		setDisplayState(1);
		repaint();
	}
	
	////////////////////
	
	public void showPreview(BufferedImage img,int gaussian_degree) {
		this.img = img;
		this.gaussian_degree = gaussian_degree;
		setDisplayState(2);
		//repaint();
		//this.setVisible(true);
		//device.setFullScreenWindow(this);
		
		//device.setFullScreenWindow(this);
		repaint();
		System.out.println("preview:"+device.getDisplayMode().getWidth()+"x"+device.getDisplayMode().getHeight());
	}
	
	public void mouseCut(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Point hotSpot = new Point(0,0);
	    BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
	    Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");        
	    setCursor(invisibleCursor);
	}
	
	public void frameClose(){
		this.dispose();
	}
	
	
	public final void AlwaysOnTop(){
		this.setAlwaysOnTop(true);
	}
	
	/*
	public BufferedImage getBufImg(){
		if(degree == 0){
			return img;
		}else{
			return Gaussian_img;
		}
	}
	*/
	
}
