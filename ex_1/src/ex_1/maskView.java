package ex_1;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class maskView extends JPanel{
	Graphics2D g2 = null;
	private BufferedImage img;
	int row=1;
	int col=1;
	
	Shape s;
	
	double row_size;
	double col_size;

	//int click_x=0;
	//int click_y=0; 
	
	int row_positon;
	int col_positon;
	
	int view_width,view_heigth;
	int ViewState = 0;
	
	int bright = 255;
	
	int[][] table;
	
	public maskView(int view_width,int view_heigth){
		this.view_width = view_width;
		this.view_heigth = view_heigth;

	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g2 = (Graphics2D)g;
		initGirdmap();
		//rows = view_width/row;
		//cols = view_heigth/col;
		
		switch (ViewState) {
		
		case 0 : //init
			return;
		case 1 : //Bright_view
			System.out.println("Bright_view");
			/*
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));
			g2.drawRect((row_positon-1)*rows, (col_positon-1)*cols, rows, cols);
			g2.setColor(new Color(bright,bright,bright));
			g2.fillRect((row_positon-1)*rows, (col_positon-1)*cols, rows, cols);
			*/
			return;
		
		case 2 : //Click				
			System.out.println("Click");
			
			g2.setColor(Color.RED);
			s = new Rectangle2D.Double((row_positon-1)*row_size,(col_positon-1)*col_size, row_size, col_size);
			g2.draw(s);
			//g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));
			//g2.drawRect((row_positon-1)*rows, (col_positon-1)*cols, rows, cols);
			
			return;
		}
		
	}
	
	public int getRows(){
		return row;
	}
	
	public int getCols(){
		return col;
	}

	public void setTable(int[][] table){
		this.table = table;
		this.row = table.length;
		this.col = table[0].length;
		row_size = view_width/row;
	    col_size = view_heigth/col;
		
		System.out.println(row+"-"+col);
		/*
		table = new int[row][col];
		
		for(int x=0 ; x < row ; x++){
			for(int y=0 ; y < col ; y++){
				table[x][y] = 255;
			}
		}
		*/
		//ViewState = 0;
		this.repaint();
	}
	
	public void setClickGrid(int row_positon, int col_positon){
		this.row_positon = row_positon;
		this.col_positon = col_positon;
		if(row_positon >=0 || col_positon>=0){
			ViewState = 2;
			this.repaint();
		}
	}
	/*
	public int getClickGrid_RowPositon(){
		return click_row;
	}
	
	public int getClickGrid_ColPositon(){
		return click_col;
	}
	*/
	public void setBright(int bright){
		ViewState = 1;
		if(row_positon >=0 || col_positon>=0){
			this.bright = bright;
			this.repaint();
		}
	}
	
	public void setBrightGrid(int[][] table){
		this.table = table;
	}
	
	void initGirdmap(){
		g2.setBackground(Color.WHITE);
		g2.clearRect(0, 0, view_width, view_heigth);
		
		//double row_size = view_width/row;
	    //double col_size = view_heigth/col;
	    double row_p = row_size;
	    double col_p = col_size;
	    for(int r=0 ; r<row ;r++){
	    	for(int c=0 ; c<col ;c++){
		        if((row-1)==r){
		        		row_size = view_width-row_size*(row-1);
		        }
		        if((col-1)==c){
		        		col_size = view_heigth-col_size*(col-1);
		        }
		        g2.setStroke(new BasicStroke(2));// ¼±ÀÇ ±½±â 1~5
		        g2.setColor(Color.BLACK);
		        //int color = table[r][c];
                //g2.setColor(new Color(color,color,color));
		        s = new Rectangle2D.Double(row_p*r,col_p*c, row_size, col_size);
		        g2.draw(s);
		        
		        
		        int color = table[r][c];
                g2.setColor(new Color(color,color,color));
		        s = new Rectangle2D.Double(row_p*r+2,col_p*c+2, row_size-2, col_size-2);
		        g2.fill(s);
		        
		        row_size = view_width/row;
		        col_size = view_heigth/col;  	
		        
		    }
	    }
	    //g2.dispose();
	    System.out.println("viewinit");
	}


}
