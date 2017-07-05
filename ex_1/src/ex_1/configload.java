package ex_1;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class configload implements ActionListener{
	maskView mv;
	tableInfo gridtable;
	gridGraphic gridgraphic;
	JTextField row;
	JTextField col;
	JTextField gaussian;
	int gaussian_degree=0;
	
	public configload(maskView mv,tableInfo gridtable,gridGraphic gridgraphic,JTextField row,JTextField col,JTextField gaussian) {
		this.mv = mv;
		this.gridtable = gridtable;
		this.gridgraphic = gridgraphic;
		this.gaussian = gaussian;
		this.row = row;
		this.col = col;
	}

	public void actionPerformed(ActionEvent e) {
	
		int[][] table = null;
		
		if((table = loadTable(Load_path()))!=null){
			gridtable.setTable(table);
			gridgraphic.paintImage(table);
			mv.setTable(table);
			row.setText(""+table.length);
			col.setText(""+table[0].length);
			gaussian.setText(""+gaussian_degree);
		}else{
			System.out.println("load error");
		}
		//System.out.println("gaussian_degree:"+gaussian_degree);
		
		/*
		int[][] t = gridtable.getTable();
		for(int r=0 ; r<t.length;r++){
			for(int c=0 ; c<t[0].length;c++){
				System.out.print(t[r][c]+" ");
			}
			System.out.println();
		}
		*/
	}
	
	public int[][] loadTable(String Path){
		
		int row=1,col=1;
		int[][] table = null;
		 
		String content = "",rd = "";
		StringTokenizer st = null;
		int flag_r = 0,flag_c = 0;
		int flag_table_set = 0;
		int x=0, y=0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(Path));
			while((rd = reader.readLine())!=null){
				st = new StringTokenizer(rd);
				String s = "";
				s = st.nextToken();
				
				if((flag_r*flag_c)==1 && flag_table_set==0){
					table =  new int[row][col];
					flag_table_set = 1;
				}
				
				while(st.hasMoreTokens()){
					if(s.equals("r")){
						row = Integer.parseInt(st.nextToken());
						flag_r = 1; //get
					}
					else if(s.equals("c")){
						col = Integer.parseInt(st.nextToken());
						flag_c = 1; //get
					}
					else if(s.equals("d")){
						gaussian_degree = Integer.parseInt(st.nextToken());
					}
					else if(s.equals("$")){
						if((flag_r*flag_c)==1){
							String cc = st.nextToken();
	 
							table[x][y]= Integer.parseInt(cc);
	 
							x++;
							if(x == row){
								x=0;
								y++;
							}
						}
					}
					else{
						System.out.println("error");
						JOptionPane.showMessageDialog(null, "this is not 'xxx.config' file !!");
						return null;
					}
				}
			}
		  } catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		  }
		 
		 return table;
	}
	
	public String Load_path(){
		FileDialog saveOpen = new FileDialog(new JFrame(), "문서불러오기", FileDialog.LOAD);
		saveOpen.setVisible(true);
		String fileDir = null;
		String fileName = null;
		
		if((fileDir = saveOpen.getDirectory())==null){
			return null;
		}

		if((fileName = saveOpen.getFile())==null){
			return null;
		}
		String saveFilename = fileDir+ fileName ;
		
		System.out.println(saveFilename);
		return saveFilename;
	}
	
}