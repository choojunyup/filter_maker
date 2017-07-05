package ex_1;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jhlabs.image.GaussianFilter;

public class pngsave implements ActionListener{
	//previewFrame pf;
	BufferedImage img;
	gridGraphic gridgraphic;
	JTextField gaussiandegree;
	tableInfo table;
	JFrame main_frame;
	
	//JFrame f;
	
	int png_width=1920;
	int png_height=1080;
	
	BufferedImage Gaussian_img = null;
	GaussianFilter Gaussian = null;
	
	public pngsave(JFrame main_frame,gridGraphic gridgraphic,JTextField gaussiandegree,tableInfo table){
		//this.pf = pf;
		this.main_frame = main_frame;
		this.gridgraphic = gridgraphic;
		this.gaussiandegree = gaussiandegree;
		this.table = table;
	}
	
	public String Save_path(){
		FileDialog saveOpen = new FileDialog(main_frame, "png파일저장",FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir=null;
		String fileName=null;
		
		if((fileDir = saveOpen.getDirectory())==null){
			return null;
		}

		if((fileName = saveOpen.getFile())==null){
			return null;
		}
		String saveFilename = fileDir+"\\"+fileName+".png"; 
		return saveFilename;
	}
	
	public int img_Size_Select(){
		String[] displaySize = { 
				                  "1024 x 768",
				                  "1600 x 1200",
				                  "1920 x 1080",
				                  "2560 x 1440"
				                  
							   };
		String s = (String)JOptionPane.showInputDialog(null,"save image size","Choose size",JOptionPane.PLAIN_MESSAGE,null,displaySize,displaySize[2]);
		if( s == null){
			return 0;
		}
		if(s.equals("1024 x 768")){
			png_width = 1024;
			png_height = 768;
			return 1;
		}if(s.equals("1600 x 1200")){
			png_width = 1600;
			png_height = 1200;
			return 1;
		}if(s.equals("1920 x 1080")){
			png_width = 1920;
			png_height = 1080;
			return 1;
		}if(s.equals("2560 x 1440")){
			png_width = 2560;
			png_height = 1440;
			return 1;
		}
		return 0;
	}
	public void actionPerformed(ActionEvent e) {
				
		try{
				int result = img_Size_Select();
				System.out.println("result:"+result);
				String savepath = null;
				if(result == 1){  // select display size
					savepath = Save_path();
				}
				
			  	if(savepath != null){
			  		System.out.println(savepath+"--"+png_width+"--"+png_height);
					//////////////////////paint//////////////////////////
					gridgraphic.setSize(png_width,png_height);
					gridgraphic.paintImage(table.getTable());
					img = gridgraphic.getImage();
					//////////////////////paint//////////////////////////
					
					///////////////////Guassian//////////////////////////
					Gaussian = new GaussianFilter(Integer.parseInt(gaussiandegree.getText()));
					Gaussian_img = Gaussian.filter(img,Gaussian_img);
					
					while(true){
						if(Gaussian_img!=null){
							break;
						}
					}
					//System.out.println(Gaussian_img.getType());
					///////////////////Guassian//////////////////////////
					
					File file = new File(savepath);
					ImageIO.write(Gaussian_img, "png", file);
					JOptionPane.showMessageDialog(main_frame, "image make complete!!");
			  	}
			  	else{
			  		System.out.println("no make img");
			  	}
	      }catch(Exception e1){
	    	  e1.printStackTrace();
	    }

	}
	
}
