package ex_1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import com.jhlabs.image.GaussianFilter;

public class gridGraphic {
	private BufferedImage img;
	//BufferedImage Gaussian_img;
	//GaussianFilter Gaussian;
	int[][] table;
	int rows;
	int cols;
	int degree = 0;
	int img_width = 1920;  //define FHD
	int img_height = 1080;
	Shape s;
	public BufferedImage getImage(){
		return img;
	}
	
	public void setSize(int img_width,int img_height){
		this.img_width = img_width;
		this.img_height = img_height;
	}
	public void paintImage(int[][] table){
		this.table = table;
		rows = table.length;
		cols = table[0].length;
		img =  new BufferedImage(img_width,img_height, BufferedImage.TYPE_INT_RGB);
		Graphics2D img_g = img.createGraphics();
		
		//Gaussian = new GaussianFilter(degree);
		
		img_g.setBackground(Color.WHITE);
	    img_g.clearRect(0, 0, img_width, img_height);
	    //img_g.setStroke(new BasicStroke(2));// ¼±ÀÇ ±½±â 1~5

	    double row_size = img_width/rows;
	    double col_size = img_height/cols;
	    double row_p = row_size;
	    double col_p = col_size;
	    for(int c=0 ; c<cols ;c++){
	    	for(int r=0 ; r<rows ;r++){
		        if((rows-1)==r){
		        		row_size = img_width-row_size*(rows-1);
		        }
		        if((cols-1)==c){
		        		col_size = img_height-col_size*(cols-1);
		        }
		        //img_g.setStroke(new BasicStroke(2));// ¼±ÀÇ ±½±â 1~5
		        int b = table[r][c];
				img_g.setColor(new Color(b,b,b));
		        s = new Rectangle2D.Double(row_p*r,col_p*c, row_size, col_size);
		        row_size = img_width/rows;
		        col_size = img_height/cols;
		        	
		        img_g.fill(s);
		    }
	    }
	    img_g.dispose();
	    
	    //Gaussian_img = Gaussian.filter(img,Gaussian_img);


	}
}
