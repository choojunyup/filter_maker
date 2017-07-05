package ex_1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jhlabs.image.GaussianFilter;

public class gui {

    static JFrame main_frame;
    static previewFrame second_frame;
	static Canvas c = null;
	static maskView maskview = null;
	static tableposition current_positon = new tableposition();
	static int current_row_position,current_col_position;
	static int maskView_size_width = 570;
	static int maskView_size_height = 280;
	static int window_width = 700;
	static int window_height = 450;
	static gridGraphic gridgraphic = null;
	static BufferedImage img = null;
	//static URL url = gui.class.getClassLoader().getResource("file.png");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dimension dim = new Dimension(window_width,window_height);
		c = new Canvas();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu displayMenu = new JMenu("display");
        
        JMenuItem configsave = new JMenuItem("save");
        JMenuItem configload = new JMenuItem("load");
        JMenuItem pngsave = new JMenuItem("png save");
        JMenuItem displayset = new JMenuItem("display setting");

		
		tableInfo gridTable = new tableInfo();  // Grid bright info
		
		//JPanel mainPanel =  new JPanel();
		
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		
		JPanel rowPanel = new JPanel();
		JPanel colPanel = new JPanel();
		
		JPanel row_col_set = new JPanel();
		
		JPanel bright_padding1 =  new JPanel();
		JPanel bright_padding2 = new JPanel();
		JPanel gaussian_padding =  new JPanel();
		
		JPanel displayset_Panel = new JPanel();
		JPanel displaymode_Panel = new JPanel();
		
		JPanel displayPanel = new JPanel(new BorderLayout());
		displayPanel.setBorder(new TitledBorder( new EtchedBorder(),"controller"));
		JPanel gaussianPanel = new JPanel(new BorderLayout());
		gaussianPanel.setBorder(new TitledBorder( new EtchedBorder(),"gaussian"));
		JPanel setPanel = new JPanel(new BorderLayout());
		setPanel.setBorder(new TitledBorder( new EtchedBorder(),"table"));
		JPanel brightPanel = new JPanel(new BorderLayout());
		brightPanel.setBorder(new TitledBorder( new EtchedBorder(),"bright"));
		JPanel boxSize = new JPanel(new BorderLayout());
		boxSize.setBorder(new TitledBorder( new EtchedBorder(),"boxSize"));
		
	    JButton previewbtn = new JButton("");    // result view
	    JButton shotbtn = new JButton("shot");   //view Start
	    JButton setbtn = new JButton("set");
	    JButton brightsetbtn = new JButton("set");
	    JButton displayshot = new JButton("shot");
	    JButton boxSizeSet = new JButton("set");
	    
	    JLabel row_l = new JLabel("row");
	    JLabel col_l = new JLabel("col");
	    JLabel degree = new JLabel("degree");
	    
	    JTextField row = new JTextField("7",8);
	    JTextField col = new JTextField("5",8);    
	    JTextField bright = new JTextField("255",4);
	    JTextField gaussiandegree = new JTextField("0", 3);
	    JTextField boxSizedegree = new JTextField("80", 3);
	    JTextField bright_label = new JTextField("",2);
	    bright_label.setEditable(false);
	    
	    SpinnerNumberModel bright_model=new SpinnerNumberModel(0, 0, 255, 1);
	    JSpinner bright_sp = new JSpinner(bright_model);
	    
	    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    //URL url = null;
	    //url = getClass().getClassLoader().getResource("icon//file.png");
	    //fileMenu.setIcon(new ImageIcon(url));
	    
	    
	    URL fileUrl = gui.class.getClassLoader().getResource("file.png");    
	    fileMenu.setIcon(new ImageIcon(fileUrl));    
	    
	    URL configsaveUrl = gui.class.getClassLoader().getResource("save.png");    
	    configsave.setIcon(new ImageIcon(configsaveUrl));
	    
	    URL  configloadUrl = gui.class.getClassLoader().getResource("Open_1.png");    
	    configload.setIcon(new ImageIcon(configloadUrl));
	    
	    URL pngsaveUrl = gui.class.getClassLoader().getResource("img_save.png");    
	    pngsave.setIcon(new ImageIcon(pngsaveUrl));
	    
	    URL gridUrl = gui.class.getClassLoader().getResource("grid.png");    
	    previewbtn.setIcon(new ImageIcon(gridUrl));
	    
	    /*
	    fileMenu.setIcon(new ImageIcon("icon/file.png"));	
	    configsave.setIcon(new ImageIcon("icon/save.png"));
	    configload.setIcon(new ImageIcon("icon/open_1.png"));
	    pngsave.setIcon(new ImageIcon("icon/img_save.png"));
	    previewbtn.setIcon(new ImageIcon("icon/grid.png"));
	    */
	    previewbtn.setBackground(Color.GRAY);
	    
	    displayshot.setBackground(Color.GREEN);
	    displayshot.setBorder(new JButton().getBorder());
	    
	    URL onUrl = gui.class.getClassLoader().getResource("on.png");    
	    displayshot.setIcon(new ImageIcon(onUrl));
	    //displayshot.setIcon(new ImageIcon("icon/on.png"));
	    
	    fileMenu.add(configsave);
	    fileMenu.add(configload);
	    fileMenu.addSeparator();
	    fileMenu.add(pngsave);
	    
	    displayMenu.add(displayset);
	    
	    bright_label.setBackground(new Color(255,255,255));

	    menuBar.add(fileMenu);
	    //menuBar.add(displayMenu);
	    menuBar.add(Box.createHorizontalGlue());
	    menuBar.add(displayshot);
	    
//////////////////////////////////////init////////////////////////////////////////////////
	    main_frame = new JFrame("filter_maker");
		second_frame =  new previewFrame();
		gridgraphic = new gridGraphic();
		maskview =  new maskView(maskView_size_width,maskView_size_height);
		
		gridTable.setTableSize(7, 5);   //table init
		maskview.setTable(gridTable.getTable());  //table view init
		
		gridgraphic.setSize(1920, 1080); //img size init
		gridgraphic.paintImage(gridTable.getTable());  //img maker and save
		
		previewbtn.setEnabled(false);
		boxSizeSet.setEnabled(false);
		//////////////////////////////////////////////////////////////////////////////////////////
		
		previewbtn.addActionListener(new preview_button(second_frame,gridgraphic,gaussiandegree));
		boxSizeSet.addActionListener(new boxSizeSet_button(second_frame,boxSizedegree));
		displayshot.addActionListener(new shot_button(displayshot,second_frame,previewbtn,boxSizeSet));
		pngsave.addActionListener(new pngsave(main_frame,gridgraphic,gaussiandegree,gridTable));
		configsave.addActionListener(new configsave(gridTable,main_frame,gaussiandegree));
		
		setbtn.addActionListener(new set_button(maskview,gridTable,gridgraphic,row,col,gaussiandegree));
		configload.addActionListener(new configload(maskview,gridTable,gridgraphic,row,col,gaussiandegree));
		
		brightsetbtn.addActionListener(new brightset_button(maskview,second_frame,gridTable,gridgraphic,current_positon,bright,bright_label,bright_sp));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		//displayset_Panel.add(BorderLayout.WEST,setectdisplaybtn);
		//displayset_Panel.add(BorderLayout.EAST,shotbtn);
		//displaymode_Panel.add(BorderLayout.WEST,previewbtn);
		//displaymode_Panel.add(BorderLayout.EAST,closebtn);
		boxSize.add(BorderLayout.NORTH,boxSizedegree);
		boxSize.add(BorderLayout.SOUTH,boxSizeSet);
		
		displayPanel.add(BorderLayout.SOUTH,boxSize);
		displayPanel.add(BorderLayout.CENTER,previewbtn);
		//displayPanel.add(BorderLayout.EAST,closebtn);
		
		gaussian_padding.add(BorderLayout.WEST,gaussiandegree);
		gaussian_padding.add(BorderLayout.EAST,degree);
		gaussianPanel.add(BorderLayout.WEST,gaussian_padding);
		//gaussianPanel.add(BorderLayout.EAST,degree);
		
		rowPanel.add(BorderLayout.WEST,row_l);
		rowPanel.add(BorderLayout.EAST,row);
		colPanel.add(BorderLayout.WEST,col_l);
		colPanel.add(BorderLayout.EAST,col);
		row_col_set.add(BorderLayout.WEST,rowPanel);
		row_col_set.add(BorderLayout.CENTER,colPanel);
		row_col_set.add(BorderLayout.EAST,setbtn);
		setPanel.add(BorderLayout.CENTER,row_col_set);
		
		
		bright_padding2.add(BorderLayout.WEST,bright_label);
		bright_padding2.add(BorderLayout.EAST,bright_sp);
		
		bright_padding1.add(BorderLayout.WEST,bright_padding2);
		bright_padding1.add(BorderLayout.CENTER,bright);
		bright_padding1.add(BorderLayout.EAST,brightsetbtn);
		
		brightPanel.add(BorderLayout.CENTER,bright_padding1);
		//brightPanel.add(BorderLayout.EAST,brightsetbtn);
		//brightPanel.add(BorderLayout.WEST,bright_label);
		
		//filePanel.add(BorderLayout.WEST,configloadbtn);
		//filePanel.add(BorderLayout.CENTER,configsavebtn);
		//filePanel.add(BorderLayout.EAST,pngsavebtn);
		
		northPanel.add(BorderLayout.WEST,setPanel);
		northPanel.add(BorderLayout.CENTER,brightPanel);
		northPanel.add(BorderLayout.EAST,gaussianPanel);
		
		/*
		mainPanel.add(BorderLayout.NORTH,northPanel);
		mainPanel.add(BorderLayout.CENTER,maskview);
		mainPanel.add(BorderLayout.SOUTH,southPanel);
		mainPanel.add(BorderLayout.WEST,new JPanel());
		mainPanel.add(BorderLayout.EAST,displayPanel);
		*/
		//southPanel.add(BorderLayout.WEST,filePanel);
		//southPanel.add(BorderLayout.EAST,displayPanel);
		//URL mainUrl = gui.class.getClassLoader().getResource("screen.png");    
	    //fileMenu.setIcon(new ImageIcon(fileUrl));
		
		//Toolkit kit = Toolkit.getDefaultToolkit();
        //main_frame.setIconImage(kit.getImage("icon/screen.png"));   //icon
		URL mainUrl = gui.class.getClassLoader().getResource("screen.png");  
	    main_frame.setIconImage(new ImageIcon(mainUrl).getImage());
		main_frame.setJMenuBar(menuBar);
		//main_frame.getContentPane().add(BorderLayout.NORTH,mainPanel);
		
		main_frame.getContentPane().add(BorderLayout.NORTH,northPanel);
		main_frame.getContentPane().add(BorderLayout.CENTER,maskview);
		main_frame.getContentPane().add(BorderLayout.SOUTH,southPanel);
		main_frame.getContentPane().add(BorderLayout.WEST,new JPanel());
		main_frame.getContentPane().add(BorderLayout.EAST,displayPanel);
		
		main_frame.setResizable(false);
		main_frame.setPreferredSize(dim);
		main_frame.pack();
		main_frame.setVisible(true);
		
		maskview.addMouseListener(new MouseAdapter(){
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	//System.out.println(e.getX());
		    	//System.out.println(e.getY());
		    	int click_x,click_y;
		    	click_x = e.getX();
		    	click_y = e.getY();
		    	int rows = maskview.getRows();
		    	int cols = maskview.getCols();
		    	int row_block_size = maskView_size_width/rows;
		    	int col_block_size = maskView_size_height/cols;
		    	
		    	current_row_position = ( click_x%row_block_size == 0 ? click_x/row_block_size:click_x/row_block_size+1);
		    	current_col_position = ( click_y%col_block_size == 0 ? click_x/row_block_size:click_y/col_block_size+1);
		    	
		    	if(current_row_position >= rows){  //row limit
		    		current_row_position = rows;
		    	}
		    	if(current_col_position >= cols){  //col limit
		    		current_col_position = cols;
		    	}
		    	
		    	current_positon.setRow_position(current_row_position);  //click positon save
		    	current_positon.setCol_position(current_col_position);  //click positon save
		    	
		    	int bright_degree = gridTable.getTable()[current_row_position-1][current_col_position-1];
		    	bright_sp.setValue(bright_degree);
		    	maskview.setClickGrid(current_row_position,current_col_position);
		    	second_frame.showcheckview(current_row_position, current_col_position,gridTable.getTable());// checking box show
		    	bright_label.setBackground(new Color(bright_degree,bright_degree,bright_degree));
		    	//bright_sp.setValue(gridTable.getTable()[current_row_position-1][current_col_position-1]);
		    	//System.out.println("x:"+current_row_position);
		    	//System.out.println("y:"+current_col_position);
		    }    
		});
		
		bright_sp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	
            	int col = current_positon.getCol_position();
            	int row = current_positon.getRow_position();
            	int[][] table = gridTable.getTable();
            	int bright_degree = (int)bright_sp.getValue();
            	if(row-1>=0 && col-1>=0){
	            	table[row-1][col-1] = bright_degree;
	            	maskview.setBright(bright_degree);
	            	maskview.setClickGrid(row,col);
	            	second_frame.showcheckview(row,col,table);
	            	bright_label.setBackground(new Color(bright_degree,bright_degree,bright_degree));
            	}
            	//second_frame.repaint();
            }
        });
		
		main_frame.addWindowListener(new WindowAdapter(){ 	   
			   @Override
			 public void windowClosing(WindowEvent e) {
			    System.out.println("close");
			    second_frame.frameClose();
			    System.exit(0); 
			}
		});



	}


}
/////////////////////////////main end///////////////////////////////////////////////////////////////////

class boxSizeSet_button implements ActionListener{
	JTextField boxSizedegree;
	previewFrame pf;
	public boxSizeSet_button(previewFrame pf ,JTextField boxSizedegree){
		this.boxSizedegree = boxSizedegree;
		this.pf = pf;
	}
	
	public void actionPerformed(ActionEvent e) {
		int size = Integer.parseInt(boxSizedegree.getText());
		if(size >= 500){
			size = 500;
		}else if(size <= 20){
			size = 20;
		}
		boxSizedegree.setText(""+size);
		pf.setBox(size);
	}
}

class shot_button implements ActionListener{
	JButton shotbtn;
	previewFrame pf;
	JButton prebtn;
	JButton boxbtn;
	int state = 1;
	public shot_button(JButton shotbtn,previewFrame pf,JButton prebtn, JButton boxbtn){
		this.shotbtn = shotbtn;
		this.pf = pf;
		this.prebtn = prebtn;
		this.boxbtn = boxbtn;
	}
	
	public void display_Dialog(){
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		String[] displayInfo = new String[gs.length];
		System.out.println("display_conut:"+displayInfo.length);
		
		for(int i = 0; i < gs.length; i++) {
			DisplayMode mode = gs[i].getDisplayMode();
			displayInfo[i] = "Display"+i+" "+mode.getWidth()+"x"+mode.getHeight();
		}
		
		//int w = pf.getdevice().getDisplayMode().getWidth(); //pre display info
		//int h = pf.getdevice().getDisplayMode().getHeight(); //pre display info
		//String d = pf.getdevice().getIDstring();
		//int s = JOptionPane.showOptionDialog(null,"now display:"+d+"-"+w+"x"+h,"Choose display",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,displayInfo,displayInfo[0]);
		
		String s = (String)JOptionPane.showInputDialog(null,"display select","Choose display",JOptionPane.PLAIN_MESSAGE,null,displayInfo,displayInfo[gs.length-1]);
		int choose_num = 0;
		for(int j = 0;j<gs.length; j++){
			if(displayInfo[j].equals(s)){
				break;
			}
			choose_num++;
		}
		
		System.out.println(choose_num);
		if(choose_num>=1){
			int x_position = 0;
			for(int i=0 ; i< choose_num ;i++){
				x_position = x_position+gs[i].getDisplayMode().getWidth();
			}
			//pf.setLocation(gs[0].getDisplayMode().getWidth()+1, 0);
			pf.setLocation(x_position+1, 0);
			pf.setExtendedState(JFrame.MAXIMIZED_BOTH);  //full screen
			pf.setDisplayDevice(gs[choose_num]);
		}else{
			pf.setLocation(0,0);
			pf.setExtendedState(JFrame.NORMAL);
			pf.setSize(800, 450);
			pf.setDisplayDevice(gs[choose_num]);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(state==1){
			display_Dialog();
			shotbtn.setText("close");
			shotbtn.setBackground(Color.RED);
			URL offUrl = gui.class.getClassLoader().getResource("off.png");    
			shotbtn.setIcon(new ImageIcon(offUrl));
			//shotbtn.setIcon(new ImageIcon("icon/off.png"));
			
			prebtn.setEnabled(true);
			boxbtn.setEnabled(true);
			
			pf.setVisible(true);
			pf.showBlank();
			state=0;
		}else{
			
			shotbtn.setText("shot");
			shotbtn.setBackground(Color.GREEN);
			URL onUrl = gui.class.getClassLoader().getResource("on.png");    
			shotbtn.setIcon(new ImageIcon(onUrl));
			//shotbtn.setIcon(new ImageIcon("icon/on.png"));
			
			prebtn.setEnabled(false);
			boxbtn.setEnabled(false);
			
			pf.dispose();
			pf.frameClose();
			state=1;
		}
	}	
}

class preview_button implements ActionListener{
	previewFrame pf;
	gridGraphic gridgraphic;
	JTextField gaussiandegree;
	public preview_button(previewFrame pf,gridGraphic gridgraphic,JTextField gaussiandegree){
		this.pf = pf;
		this.gridgraphic = gridgraphic;
		this.gaussiandegree = gaussiandegree;
	}
	public void actionPerformed(ActionEvent e) {
		pf.showPreview(gridgraphic.getImage(), Integer.parseInt(gaussiandegree.getText()));
	}	
}

class set_button implements ActionListener{
	maskView mv;
	tableInfo gridtable;
	gridGraphic gridgraphic;
	JTextField row;
    JTextField col;
    JTextField gaussiandegree;
   
	public set_button(maskView mv,tableInfo gridtable,gridGraphic gridgraphic,JTextField row,JTextField col,JTextField gaussiandegree){
		this.mv = mv;
		this.gridtable = gridtable;
		this.gridgraphic = gridgraphic;
		this.row = row;
		this.col = col;
		this.gaussiandegree = gaussiandegree;
		
	}
	public void actionPerformed(ActionEvent e) {
		gridtable.setTableSize(Integer.parseInt(row.getText()) , Integer.parseInt(col.getText()));
		//gridtable.BrightReset();
		gridgraphic.paintImage(gridtable.getTable());
		gaussiandegree.setText("0");;
		mv.setTable(gridtable.getTable());
	}	
}

class brightset_button implements ActionListener{
	maskView mv;
	previewFrame pf;
	tableInfo gridtable;
	tableposition current_positon;
	gridGraphic gridgraphic;
	int row_position = 0;
	int col_position = 0;
    JTextField bright;
    JTextField bright_label;
    JSpinner bright_sp;
    
	public brightset_button(maskView mv,previewFrame pf, tableInfo gridtable,gridGraphic gridgraphic,tableposition current_positon, JTextField bright, JTextField bright_label, JSpinner bright_sp){
		this.gridtable = gridtable;
		this.pf = pf;
		this.current_positon = current_positon;
		this.gridgraphic = gridgraphic;
		this.bright = bright;
		this.bright_label = bright_label;
		this.mv = mv;
		this.bright_sp = bright_sp;
		
	}
	public void actionPerformed(ActionEvent e) {
		row_position = current_positon.getRow_position();
		col_position = current_positon.getCol_position();
		System.out.println("-"+row_position+"-"+col_position);
		
		int bright_state = Integer.parseInt(bright.getText());
		if(bright_state>=255){            //bright limit
			bright_state = 255;
		}else if(bright_state<=0){
			bright_state = 0;
		}
		//if(row_position>=0 && col_position>=0 && bright_state>=0){
			bright_label.setBackground(new Color(bright_state,bright_state,bright_state));
			mv.setBright(bright_state);
			gridtable.setBright(row_position,col_position,bright_state);
			bright.setText(""+bright_state);
			bright_sp.setValue(bright_state);
			
			gridgraphic.paintImage(gridtable.getTable()); // img_save
			mv.setBrightGrid(gridtable.getTable());
			pf.repaint();
		//}
	}	
}
