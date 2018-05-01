import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;

public class LineReader {

	public static void main(String[] args) throws Exception{
		
		//1줄씩 출력
		//엔터 입력시에만 다음줄 출력
		//사용자가 "exit"+enter을 입력할때 프로그램 종료
		//프로그램 종료시 읽던 행번호를 파일로 저장
		//다시 프로그램열면 읽던줄을 보여줌
		//마지막줄까지 보여준뒤 다시 Enter입력하면 첫줄을 다시 보여준다.

		Scanner s = new Scanner(new File("C:/Users/Ryu Dongheon/Desktop/hong.txt"));
		Scanner sc = new Scanner(System.in);
		String str="";
		
		//Scanner check = new Scanner(new File("C:/Users/Ryu Dongheon/Desktop/check.txt"));
		int temp =0;
		//temp = check.nextInt();
		FileReader fr = new FileReader("C:/Users/Ryu Dongheon/Desktop/check.txt");
		temp = fr.read()-48;
		System.out.println(temp);
		fr.close();
		//check.close();
		String exit = "exit";
		String inString = "";
		
		int i=0;
		
		//FileInputStream fis = new FileInputStream("C:/Users/Ryu Dongheon/Desktop/hong.txt");
		
		while(true){
			if(temp > i){
			str = s.nextLine();
			i++;
			continue;
			}
			else{
			str = s.nextLine();
			System.out.println(str);
			i++;
			inString = sc.nextLine();
			
				if(exit.equals(inString)){
					System.out.println("종료합니다...");
					FileWriter fw = new FileWriter("C:/Users/Ryu Dongheon/Desktop/check.txt");
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(""+i);
					System.out.println(i);
					bw.close();
					fw.close();
					break;
				}
				if(!s.hasNextLine()){
				s = new Scanner(new File("C:/Users/Ryu Dongheon/Desktop/hong.txt"));
				}
			}
		}
		s.close();
		
		return;
		
	}

}
