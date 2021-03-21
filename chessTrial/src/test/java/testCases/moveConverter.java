import java.util.*;
class converter{
	public static void main(String args[]){
		String fen1 = "rnbqkb1r/pp3p1p/2p2np1/3pP3/4P3/5N2/PPPPB1PP/RNBQK2R";               // Main Method to test Different FENs
		String fen2 = "rnbqkb1r/pp3p1p/2p2np1/3pP3/4P3/5N2/PPPPB1PP/RNBQ1RK1";              
		convert(fen1,fen2);
		
	}
	public static void convert(String a, String b){                                            //Convert FEN to Board Position and output Move Coordinates
		String[] arr1 = a.split("/");
		String[] arr2 = b.split("/");
		int m=0;
		int n=0;
		String[][] mat1 = new String[8][8];
		String[][] mat2 = new String[8][8];
		ArrayList<String> arrayList1 = new ArrayList<>();
		ArrayList<String> arrayList2 = new ArrayList<>();
		for(int i=0;i<a.length();i++){
			if(Character.isDigit(a.charAt(i))){
				int num = Integer.parseInt(String.valueOf(a.charAt(i)));
				for(int j=0;j<num;j++){
					mat1[m][n] = ".";
					n++;
				}
				
			}
			else if(Character.isLetter(a.charAt(i))){
				mat1[m][n] = Character.toString(a.charAt(i));
				if(Character.isUpperCase(a.charAt(i))){
					arrayList1.add(Integer.toString(m)+Integer.toString(n));
				}
				n++;
			}
			else{
				m++;
				n=0;
			}
		}
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				System.out.print(mat1[i][j]+" ");
	
				
			}
			System.out.print("\n");
		}
		
		System.out.print("\n\n");
		m=0;
		n=0;
		
		for(int i=0;i<b.length();i++){
			if(Character.isDigit(b.charAt(i))){
				int num = Integer.parseInt(String.valueOf(b.charAt(i)));
				for(int j=0;j<num;j++){
					mat2[m][n] = ".";
					n++;
				}
			}
			else if(Character.isLetter(b.charAt(i))){
				mat2[m][n] = Character.toString(b.charAt(i));
				if(Character.isUpperCase(b.charAt(i))){
					arrayList2.add(Integer.toString(m)+Integer.toString(n));
				}
				n++;
			}
			else{
				m++;
				n=0;
			}
		}
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				System.out.print(mat2[i][j]+" ");
				
			}
			System.out.print("\n");
		}
		for(String s : arrayList1){
			if(!arrayList2.contains(s)){
				System.out.println(s);                                                          // Output source coordinates of piece to be moved     
			}
		}
		
		for(String s : arrayList2){
			if(!arrayList1.contains(s)){                                                            // Output destination coordinates of piece to be moved
				System.out.println(s);
			}
		}
		
		
		
		
	}
}
