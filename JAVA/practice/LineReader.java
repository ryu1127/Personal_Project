import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;

public class LineReader {

	public static void main(String[] args) throws Exception{
		
		//1�پ� ���
		//���� �Է½ÿ��� ������ ���
		//����ڰ� "exit"+enter�� �Է��Ҷ� ���α׷� ����
		//���α׷� ����� �д� ���ȣ�� ���Ϸ� ����
		//�ٽ� ���α׷����� �д����� ������
		//�������ٱ��� �����ص� �ٽ� Enter�Է��ϸ� ù���� �ٽ� �����ش�.

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
					System.out.println("�����մϴ�...");
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
