package converters;

import java.util.ArrayList;

public class WsanToFenConverter {
	public static void main(String args[]){
//		String fen1 = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
//		String san = "e4";
//		String refen = sanFenConverter(fen1,san);
//		String sanm = "h4";
//		String refenm = sanFenConverter(refen,sanm);
//		String san2 = "Rh3";
//		String refen2 = sanFenConverter(refenm,san2);
//		String san3 = "a5";
//		String refen3 = sanFenConverter(refen2,san3);
//		String san4 = "a6";
//		String refen4 = sanFenConverter(refen3,san4);
//		String san5 = "Rha3";
//		String refen5 = sanFenConverter(refen4,san5);
//		String san6 = "Ra5";
//		String refen6 = sanFenConverter(refen5,san6);
//		String san7 = "R1xa3";
//		String refen7 = sanFenConverter(refen6,san7);
//		String san8 = "e4";
//		String refen8 = sanFenConverter(refen7,san8);
//		String san9 = "Nc3";
//		String refen9 = sanFenConverter(refen8,san9);
//		String san10 = "Nce2";
//		String refen10 = sanFenConverter(refen9,san10);
//		String san11 = "Nf4";
//		String refen11 = sanFenConverter(refen10,san11);
//		String san12 = "Kxe2";
//		String refen12 = sanFenConverter(refen11,san12);
//		String refen13 = sanFenConverter("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR","d4");
	}
	
	public String sanFenConverter(String fen , String san){
		char[] letters = {'a','b','c','d','e','f','g','h'};
		ArrayList<Character> larr = new ArrayList<Character>();
		for(char i : letters){
			larr.add(i);
		}
		char[] nums = {'1','2','3','4','5','6','7','8'};
		
		int m=0;
		int n=0;
		String[][] mat1 = new String[8][8];
		for(int i=0;i<fen.length();i++){
			if(Character.isDigit(fen.charAt(i))){
				int num = Integer.parseInt(String.valueOf(fen.charAt(i)));
				for(int j=0;j<num;j++){
					mat1[m][n] = ".";
					n++;
				}
				
			}
			else if(Character.isLetter(fen.charAt(i))){
				mat1[m][n] = Character.toString(fen.charAt(i));
				if(Character.isUpperCase(fen.charAt(i))){
					
				}
				n++;
			}
			else{
				m++;
				n=0;
			}
		}
//		for(int i=0;i<8;i++){
//			for(int j=0;j<8;j++){
//				System.out.print(mat1[i][j]+" ");
//	
//				
//			}
//			System.out.print("\n");
//		}
//		System.out.print("\n");
//		System.out.print("\n");
		for(int i=0;i<san.length();i++){
			if(san.charAt(i)=='+'){
				san = san.substring(0,san.length()-1);
				
			}
			
		}
		if((san.charAt(0)=='a'||san.charAt(0)=='b'||san.charAt(0)=='c'||san.charAt(0)=='d'||san.charAt(0)=='e'||san.charAt(0)=='f'||san.charAt(0)=='g'||san.charAt(0)=='h')&&san.length()==2){
			
			
			if(san.charAt(1)=='1'||san.charAt(1)=='2'||san.charAt(1)=='3'||san.charAt(1)=='4'||san.charAt(1)=='5'||san.charAt(1)=='6'||san.charAt(1)=='7'||san.charAt(1)=='8'){
				int x = larr.indexOf(san.charAt(0));
				int y = Integer.parseInt(String.valueOf(san.charAt(1)))+1;
				int counter = 1;
				for(int i=7;counter<=y;i--){
					counter++;
					if((mat1[i][x]).equals("P")){
						mat1[i][x]=".";
					}
					if(counter==y){
						mat1[i][x]="P";
					}
					
				}
			}
		}
		else if((san.charAt(0)=='K'||san.charAt(0)=='Q')&&(san.length()==3)){
			if(san.charAt(2)=='1'||san.charAt(2)=='2'||san.charAt(2)=='3'||san.charAt(2)=='4'||san.charAt(2)=='5'||san.charAt(2)=='6'||san.charAt(2)=='7'||san.charAt(2)=='8'){
				String ch = Character.toString(san.charAt(0));
				int x = larr.indexOf(san.charAt(1));
				int y = Integer.parseInt(String.valueOf(san.charAt(2)))+1;
				int counter = 1;
				for(int i=7;counter<=7;i--){
					counter++;
					for(int j=0;j<8;j++){
							if((mat1[i][j]).equals(ch)){
							mat1[i][j]=".";
						}
					}
					
					if(counter==y){
						mat1[i][x]=ch;
					}
					
				}
			}
		}
		else if((san.charAt(0)=='R')&&san.length()==3){
			int x = larr.indexOf(san.charAt(1));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(2)));
			
			boolean found = false;
			for(int i=y;i<=7;i++){
				if(mat1[i][x].equals("Q")||mat1[i][x].equals("N")||mat1[i][x].equals("P")||mat1[i][x].equals("K")||mat1[i][x].equals("B")){
						break;
					}
				else if(mat1[i][x].equals("R")){
					mat1[i][x] = ".";
					mat1[y][x] = "R";
					found = true;
					break;
				}
			}
			if(found==false){
				for(int i=y;i>=0;i--){
					if(mat1[i][x].equals("Q")||mat1[i][x].equals("N")||mat1[i][x].equals("P")||mat1[i][x].equals("K")||mat1[i][x].equals("B")){
						break;
					}
					else if(mat1[i][x].equals("R")){
						mat1[i][x] = ".";
						mat1[y][x]="R";
						found = true;
						break;
					}
				}
			}
			if(found==false){
				for(int i=x;i<=7;i++){
					if(mat1[y][i].equals("Q")||mat1[y][i].equals("N")||mat1[y][i].equals("P")||mat1[y][i].equals("K")||mat1[y][i].equals("B")){
						break;
					}
					else if(mat1[y][i].equals("R")){
						mat1[y][i] = ".";
						mat1[y][x]="R";
						found = true;
						break;
					}
				}
			}
			if(found==false){
				for(int i=x;i>=0;i--){
					if(mat1[y][i].equals("Q")||mat1[y][i].equals("N")||mat1[y][i].equals("P")||mat1[y][i].equals("K")||mat1[y][i].equals("B")){
						break;
					}
					else if(mat1[y][i].equals("R")){
						mat1[y][i] = ".";
						mat1[y][x]="R";
						found = true;
						break;
					}
				}
			}
		}
		else if(san.charAt(0)=='B' && san.length()==3){
			int x = larr.indexOf(san.charAt(1));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(2)));
		
			int i=y;
			int j=x;
			boolean found = false;
			while(i<=7&&j<=7&&found==false){
				if(mat1[i][j].equals("B")){
					mat1[i][j] = ".";
					mat1[y][x] = "B";
					found = true;
				}
				i++;
				j++;
			}
			i=y;
			j=x;
			while(i>=0&&j>=0&&found==false){
				if(mat1[i][j].equals("B")){
					mat1[i][j] = ".";
					mat1[y][x] = "B";
					found = true;
				}
				i--;
				j--;
			}
			i=y;
			j=x;
			while(i<=7&&j>=0&&found==false){
				if(mat1[i][j].equals("B")){
					mat1[i][j] = ".";
					mat1[y][x] = "B";
					found = true;
				}
				i++;
				j--;
			}
			i=y;
			j=x;
			while(i>=0&&j<=7&&found==false){
				if(mat1[i][j].equals("B")){
					mat1[i][j] = ".";
					mat1[y][x] = "B";
					found = true;
				}
				i--;
				j++;
			}
		}
		else if(san.charAt(0)=='N'&&san.length()==3){
			int x = larr.indexOf(san.charAt(1));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(2)));
			int i=y;
			int j=x;
			boolean found = false;
			if(i+2<=7&&j-1>=0){
				if(mat1[i+2][j-1].equals("N")&&!found){
					found = true;
					mat1[i+2][j-1]=".";
					mat1[y][x]="N";
				}
			}
			if(i+2<=7&&j+1<=7){
				if(mat1[i+2][j+1].equals("N")&&!found){
					found = true;
					mat1[i+2][j+1]=".";
					mat1[y][x]="N";
				}
			}
			if(i-2>=0&&j+1<=7){
				if(mat1[i-2][j+1].equals("N")&&!found){
					found = true;
					mat1[i-2][j+1]=".";
					mat1[y][x]="N";
				}
			}
			if(i-2>=0&&j-1>=0){
				if(mat1[i-2][j-1].equals("N")&&!found){
					found = true;
					mat1[i-2][j-1]=".";
					mat1[y][x]="N";
				}
			}
			if(i-1>=0&&j-2>=0){
				if(mat1[i-1][j-2].equals("N")&&!found){
					found = true;
					mat1[i-1][j-2]=".";
					mat1[y][x]="N";
				}
			}
			if(i-1>=0&&j+2<=7){
				if(mat1[i-1][j+2].equals("N")&&!found){
					found = true;
					mat1[i-1][j+2]=".";
					mat1[y][x]="N";
				}
			}
			if(i+1<=7&&j-2>=0){
				if(mat1[i+1][j-2].equals("N")&&!found){
					found = true;
					mat1[i+1][j-2]=".";
					mat1[y][x]="N"; 
				}
			}
			if(i+1<=7&&j+2<=7){
				if(mat1[i+1][j+2].equals("N")&&!found){
					found = true;
					mat1[i+1][j+2]=".";
					mat1[y][x]="N";
				}
			}
		}
		else if(san.length()==4&&san.charAt(1)=='x'){
			if(san.charAt(0)=='a'||san.charAt(0)=='b'||san.charAt(0)=='c'||san.charAt(0)=='d'||san.charAt(0)=='e'||san.charAt(0)=='f'||san.charAt(0)=='g'||san.charAt(0)=='h'){
				int x = larr.indexOf(san.charAt(2));
				int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
				if(larr.indexOf(san.charAt(0))<x){
					int i=y+1;
					int j=x-1;
					mat1[i][j]=".";
					mat1[y][x]="P";
				}
				else if(larr.indexOf(san.charAt(0))>x){
					int i=y+1;
					int j=x+1;
					mat1[i][j]=".";
					mat1[y][x]="P";
				}
			}
			else if(san.charAt(0)=='Q'){
				String ch = Character.toString(san.charAt(0));
				int x = larr.indexOf(san.charAt(2));
				int y = 8-Integer.parseInt(String.valueOf(san.charAt(3)));
				int counter = 1;
				for(int i=7;counter<=7;i--){
					counter++;
					for(int j=0;j<8;j++){
							if((mat1[i][j]).equals(ch)){
							mat1[i][j]=".";
						}
						if(i==y){
							mat1[i][x]=ch;
						}
					}
					
				}
				
			}
			else if(san.charAt(0)=='B'){
				int x = larr.indexOf(san.charAt(2));
				int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
		
				int i=y;
				int j=x;
				boolean found = false;
				while(i<=7&&j<=7&&found==false){
					if(mat1[i][j].equals("B")){
						mat1[i][j] = ".";
						mat1[y][x] = "B";
						found = true;
					}
					i++;
					j++;
				}
				i=y;
				j=x;
				while(i>=0&&j>=0&&found==false){
					if(mat1[i][j].equals("B")){
						mat1[i][j] = ".";
						mat1[y][x] = "B";
						found = true;
					}
					i--;
					j--;
				}
				i=y;
				j=x;
				while(i<=7&&j>=0&&found==false){
					if(mat1[i][j].equals("B")){
						mat1[i][j] = ".";
						mat1[y][x] = "B";
						found = true;
					}
					i++;
					j--;
				}
				i=y;
				j=x;
				while(i>=0&&j<=7&&found==false){
					if(mat1[i][j].equals("B")){
						mat1[i][j] = ".";
						mat1[y][x] = "B";
						found = true;
					}
					i--;
					j++;
				}
			}
			else if(san.charAt(0)=='R'){
				int x = larr.indexOf(san.charAt(2));
				int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
				
				boolean found = false;
				for(int i=y;i<=7;i++){
					if(mat1[i][x].equals("R")){
						mat1[i][x] = ".";
						mat1[y][x] = "R";
						found = true;
						break;
					}
				}
				if(found==false){
					for(int i=y;i>=0;i--){
						if(mat1[i][x].equals("R")){
							mat1[i][x] = ".";
							mat1[y][x]="R";
							found = true;
							break;
						}
					}
				}
				if(found==false){
					for(int i=x;i<=7;i++){
						if(mat1[y][i].equals("R")){
							mat1[y][i] = ".";
							mat1[y][x]="R";
							found = true;
							break;
						}
					}
				}
				if(found==false){
					for(int i=x;i>=0;i--){
						if(mat1[y][i].equals("R")){
							mat1[y][i] = ".";
							mat1[y][x]="R";
							found = true;
							break;
						}
					}
				}
			}
			else if(san.charAt(0)=='N'){
				int x = larr.indexOf(san.charAt(2));
				int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
				int i=y;
				int j=x;
				boolean found = false;
				if(i+2<=7&&j-1>=0){
					if(mat1[i+2][j-1].equals("N")&&!found){
						found = true;
						mat1[i+2][j-1]=".";
						mat1[y][x]="N";
					}
				}
				if(i+2<=7&&j+1<=7){
					if(mat1[i+2][j+1].equals("N")&&!found){
						found = true;
						mat1[i+2][j+1]=".";
						mat1[y][x]="N";
					}
				}
				if(i-2>=0&&j+1<=7){
					if(mat1[i-2][j+1].equals("N")&&!found){
						found = true;
						mat1[i-2][j+1]=".";
						mat1[y][x]="N";
					}
				}
				if(i-2>=0&&j-1>=0){
					if(mat1[i-2][j-1].equals("N")&&!found){
						found = true;
						mat1[i-2][j-1]=".";
						mat1[y][x]="N";
					}
				}
				if(i-1>=0&&j-2>=0){
					if(mat1[i-1][j-2].equals("N")&&!found){
						found = true;
						mat1[i-1][j-2]=".";
						mat1[y][x]="N";
					}
				}
				if(i-1>=0&&j+2<=7){
					if(mat1[i-1][j+2].equals("N")&&!found){
						found = true;
						mat1[i-1][j+2]=".";
						mat1[y][x]="N";
					}
				}
				if(i+1<=7&&j-2>=0){
					if(mat1[i+1][j-2].equals("N")&&!found){
						found = true;
						mat1[i+1][j-2]=".";
						mat1[y][x]="N"; 
					}
				}
				if(i+1<=7&&j+2<=7){
					if(mat1[i+1][j+2].equals("N")&&!found){
						found = true;
						mat1[i+1][j+2]=".";
						mat1[y][x]="N";
					}
				}
			}
			else if(san.charAt(0)=='K'){
				int x = larr.indexOf(san.charAt(2));
				int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
				int i = y;
				int j = x;
				boolean found  = false;
				if(i-1>=0&&j-1>=0){
					if(mat1[i-1][j-1].equals("K")&&!found){
						mat1[i-1][j-1]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(i-1>=0){
					if(mat1[i-1][j].equals("K")&&!found){
						mat1[i-1][j]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(i-1>=0&&j+1<=7){
					if(mat1[i-1][j+1].equals("K")&&!found){
						mat1[i-1][j+1]=".";
						mat1[y][x]="K";
						found = true; 
					}
				}
				if(j+1<=7){
					if(mat1[i][j+1].equals("K")&&!found){
						mat1[i][j+1]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(i+1<=7&&j+1<=7){
					if(mat1[i+1][j+1].equals("K")&&!found){
						mat1[i+1][j+1]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(i+1<=7){
					if(mat1[i+1][j].equals("K")&&!found){
						mat1[i+1][j]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(i+1<=7&&j-1>=0){
					if(mat1[i+1][j-1].equals("K")&&!found){
						mat1[i+1][j-1]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				if(j-1>=0){
					if(mat1[i][j-1].equals("K")&&!found){
						mat1[i][j-1]=".";
						mat1[y][x]="K";
						found = true;
					}
				}
				
			}
		}
		else if(san.charAt(0)=='O'&&san.length()==3){
			mat1[7][7] = ".";
			mat1[7][6] = "K";
			mat1[7][5] = "R";
			mat1[7][4] = ".";
		}
		else if(san.charAt(0)=='O'&&san.length()==5){
			mat1[7][4] = ".";
			mat1[7][0] = ".";
			mat1[7][3] = "R";
			mat1[7][2] = "K";
		}
		else if(san.charAt(2)=='x'&&san.length()==5){
			int x = larr.indexOf(san.charAt(3));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(4)));
			int i = 8 - Integer.parseInt(String.valueOf(san.charAt(1)));
			String charac = Character.toString(san.charAt(0));
			for(int j=0;j<=7;j++){
				if(mat1[i][j].equals(charac)){
					mat1[i][j]=".";
					mat1[y][x]=charac;
				}
			}
		}
		else if(san.charAt(0)=='R'&&san.length()==4){
			
			int x = larr.indexOf(san.charAt(2));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
			int i = larr.indexOf(san.charAt(1));
			mat1[y][x]="R";
			mat1[y][i]=".";
		}
		else if(san.charAt(0)=='N'&&san.length()==4&&Character.isLetter(san.charAt(1))){
			int x = larr.indexOf(san.charAt(2));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
			int i = larr.indexOf(san.charAt(1));
			for(int j=0;j<=7;j++){
				if(mat1[j][i].equals("N")){
					mat1[j][i]=".";
					break;
				}
			}
			mat1[y][x]="N";
		}
		else if(san.charAt(0)=='N'&&san.length()==4&&Character.isDigit(san.charAt(1))) {
			int x = larr.indexOf(san.charAt(2));
			int y = 8 - Integer.parseInt(String.valueOf(san.charAt(3)));
			int i = 8 - Integer.parseInt(String.valueOf(san.charAt(1)));
			for(int j=0;j<=7;j++) {
				if(mat1[i][j].equals("N")) {
					mat1[i][j]=".";
					break;
				}
			}
			mat1[y][x]="N";
		}
		
		
		
//		for(int i=0;i<8;i++){
//			for(int j=0;j<8;j++){
//				System.out.print(mat1[i][j]+" ");
//	
//				
//			}
//			System.out.print("\n");
//		}
		String newfen="";
		int count=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(Character.isLetter((mat1[i][j]).charAt(0))&&count==0){
					newfen = newfen+ mat1[i][j];
				}
				else if(Character.isLetter((mat1[i][j]).charAt(0))&&count!=0){
					newfen = newfen + Integer.toString(count)+ mat1[i][j];
					count = 0;
				}
				else{
					count++;
				}
				
			}
			if(count!=0){
				newfen = newfen + Integer.toString(count);
				count = 0;
			}
			if(i!=7){
				newfen = newfen+"/";
			}
			
		}
//		System.out.println(newfen);
		
		
		
		return newfen;
	}
}
