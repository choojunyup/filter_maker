package ex_1;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class configsave implements ActionListener{
	tableInfo gridtable;
	JFrame f;
	JTextField gaussian_degree;
	public configsave(tableInfo gridtable,JFrame f,JTextField gaussian_degree) {
		this.gridtable = gridtable;
		this.f = f;
		this.gaussian_degree = gaussian_degree;
	}

	public void actionPerformed(ActionEvent e) {
		String path = null;
		path = Save_path();
		int[][] table = gridtable.getTable();
		
		if(path != null){   //path 
		
			try {
				BufferedWriter save = new BufferedWriter(new FileWriter(path));
				int rows = table.length;
				int cols = table[0].length;
				 
				String row_st = "r "+rows;
				save.write(row_st);
				save.newLine();
				 
				String col_st = "c "+cols;
				save.write(col_st);
				save.newLine();
				
				String gua_st = "d "+gaussian_degree.getText();
				save.write(gua_st);
				save.newLine();
				 
				for(int c = 0; c < cols; c++){
					for(int r =0; r < rows; r++){
							
								save.write("$ "+table[r][c]);
							save.newLine();
					}
				}	 
				///////////file content////////////
				// r 3    //row length
				// c 3    //col length
				// $ 255
				// $ 255 
				//   ...
				// $ 255
				////////////////////////////////////
				
				save.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public String Save_path(){
		FileDialog saveOpen = new FileDialog(f, "문서저장",FileDialog.SAVE);
		saveOpen.setVisible(true);
		String fileDir = null;
		String fileName = null;
		
		if((fileDir = saveOpen.getDirectory())==null){
			return null;
		}

		if((fileName = saveOpen.getFile())==null){
			return null;
		}
		
		String saveFilename = fileDir+"\\"+fileName+".config"; 
		return saveFilename;
	}
}
