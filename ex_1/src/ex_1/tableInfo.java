package ex_1;

public class tableInfo {
	int row;
	int col;
	int[][] table = null; 
	
	public tableInfo(){
	}
	
 	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int[][] getTable() {
		return table;
	}
	public void setTable(int[][] table) {
		this.table = table;
	}
	public void setTableSize(int row,int col){
		this.row = row;
		this.col = col;
		int[][] table_1 = new int[row][col];
		table  = table_1;
		//table = new int[row][col];
		/*
		System.out.println("row="+table.length);
		System.out.println("col="+table[0].length);
		System.out.println("set");
		*/
		BrightReset();
	}
	
	public void BrightReset() {
		for(int c=0 ; c < table[0].length ; c++){
			for(int r=0 ; r < table.length ; r++){
				table[r][c] = 255;
			}
		}
	}
	
	public void setBright(int row_positon,int col_positon,int bright){
		if(bright>=0 && row_positon-1>=0 && col_positon>=0){
			table[row_positon-1][col_positon-1] = bright;
		}
		/*
		for(int y=0 ; y < col ; y++){
			for(int x=0 ; x < row ; x++){
				System.out.print(table[x][y]);
				System.out.print("-");
			}
			System.out.println();
		}
		*/
	}
	
}
