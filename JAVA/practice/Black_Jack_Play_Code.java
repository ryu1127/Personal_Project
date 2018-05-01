import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Black_Jack_Play_Code {

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		
		
		//사용자와 컴퓨터의 배팅 머니 
		int user_money = 0;
		int com_money=0;
		
		//배팅 머니
		int batting_money=0;
		
		//받은 카드의 수
		int user_num=0;
		int com_num=0;
		
		//stay check
		int Victory=0;		//승패에 대한 정보 변수
		int exit =0; // 종료조건
		
		//파일에서 저장된 정보 불러오기
		
		user_money = load_user_money();
		com_money = 200 - user_money;
		
		
		while(true){
		System.out.println("Start Game...");
		//사용자와 컴퓨터의 보유 카드 배열
		int[] user_card = new int[10];
		int[] com_card = new int[10];
		display(user_money,user_card,com_money,com_card,batting_money);
		//게임 시작이므로 배팅하고 카드2장씩 받기
		user_money -=10;
		com_money -=10;
		batting_money +=20;
		
		System.out.println("Batting Success");
		recieve_Card(user_card,user_num);
		user_num++;
		
		System.out.println("user_card Success");
		recieve_Card(com_card,com_num);
		com_num++;
		
		System.out.println("com_card Success");
		recieve_Card(user_card,user_num);
		user_num++;
		
		System.out.println("user_card Success");
		recieve_Card(com_card,com_num);
		com_num++;
		
		System.out.println("com_card Success");
		display(user_money,user_card,com_money,com_card,batting_money);
		//첫 카드가 11인지 판별
		System.out.println("checking user_card...");
		if(user_card[0]==11){
			System.out.println("Do you want to change 11 to 1?");
			System.out.print("Yes(1), No(0) : ");
			int c=-1;
			c= s.nextInt();
			if(c==1){
				user_card[0]=1;
			}
		}
		System.out.println("checking com_card...");
		if(com_card[0]==11){
			System.out.println("Do you want to change 11 to 1?");
			System.out.print("Yes(1), No(0) : ");
			int c=-1;
			c= s.nextInt();
			if(c==1){
				com_card[0]=1;
			}
		}
		//사용자의 hit or stay 선택
		int choice=1;
		while(choice==1){
			System.out.print("Hit(1) or Stay(0)? : ");
			choice = s.nextInt();
			if(choice==1) {
				recieve_Card(user_card,user_num);
				user_num++;
				display(user_money,user_card,com_money,com_card,batting_money);
				int user_score=0;
			
				for(int i=0;i<user_card.length;i++){
					user_score+=user_card[i];
				}
				
				
			}
			else {
				choice = 1;
				break;
			}
		}
		display(user_money,user_card,com_money,com_card,batting_money);
		while(choice==1){
			System.out.print("Hit(1) or Stay(0)? : ");
			choice = s.nextInt();
			if(choice==1) {
				recieve_Card(com_card,com_num);
				com_num++;
				display(user_money,user_card,com_money,com_card,batting_money);
				
			}
			else {
				choice = 1;
				break;
			}
		}
		display(user_money,user_card,com_money,com_card,batting_money);
		//Decision
		Victory = Decision(user_card, user_num,com_card,com_num);
		if(Victory==2){
			System.out.println("user win!");
			user_money += batting_money;
			batting_money=0;
		}
		else if(Victory==1) {
			System.out.println("com win!");
			com_money += batting_money;
			batting_money=0;
		}
		else System.out.println("Draw!");
		System.out.println("Restart(1)? or Exit(0)? ");
		user_num=0;
		com_num=0;
		if(user_money==0){
			System.out.println("Finally Victory User!");
			user_money=100;
			com_money=100;
			save_point(user_money,com_money,batting_money);
			break;
		}
		else if(com_money==0){
			System.out.println("Finally Victory Com!");
			user_money=100;
			com_money=100;
			save_point(user_money,com_money,batting_money);
			break;
		}
		exit = s.nextInt();
		if(exit==0){
			save_point(user_money,com_money,batting_money);
			break;
		}
	}
		s.close();
		return;
}


//카드를 나눠줌
public static void recieve_Card(int[] card,int num){
	int random;
	Random r = new Random();
	random = r.nextInt(11)+1;
	card[num]=random;
	System.out.println("This Card : "+card[num]);
}
//게임머니를 불러오는 함수
public static int load_user_money() throws Exception{
	String temp=null;
	FileReader sv = new FileReader("C:/Users/Ryu Dongheon/workspace/Black_Jack_Play/save.txt");
	BufferedReader save = new BufferedReader(sv);
	int user_money;
	//유저의 돈과 컴퓨터의 돈 배팅된 돈을 모두 읽어온다.
	temp = save.readLine();
	user_money = Integer.parseInt(temp);
	
	save.close();
	sv.close();
	return user_money;
}
//게임머니 저장하는 함수
public static void save_point(int user_money,int com_money, int batting_money) throws Exception{
	FileWriter fw = new FileWriter("C:/Users/Ryu Dongheon/workspace/Black_Jack_Play/save.txt");
	BufferedWriter bw = new BufferedWriter(fw);
	bw.write(user_money+"");
	bw.newLine();
	bw.write(com_money+"");
	bw.newLine();
	bw.write(batting_money+"");
	bw.close();
	fw.close();
}
//배팅하기
public static void batting(int user_money, int com_money, int batting_money){
	batting_money += 20;
	user_money -= 10;
	com_money -= 10;
}
//현재 카드상태등 정보 보여주기
public static void display(int user_money,int[] user_card,int com_money, int[] com_card, int batting_money){
	System.out.println("user_money : "+user_money);
	System.out.print("user_card : ");
	for(int i=0;i<user_card.length;i++){
		System.out.print(user_card[i]+" ");
	}
	System.out.println();
	System.out.println("com_money : "+com_money);
	System.out.print("com_card : ");
	for(int i=0;i<com_card.length;i++){
		System.out.print(com_card[i]+" ");
	}
	System.out.println();
	System.out.println("batting_money : "+batting_money);
}
//첫 카드가 11일 경우 1로 바꾸는 함수
public static void check_change(int[] card){//안씀
	Scanner s = new Scanner(System.in);
	if(card[0]==11){
		System.out.println("Do you want to change 11 to 1?");
		System.out.print("Yes(1), No(0) : ");
		int c=-1;
		c= s.nextInt();
		if(c==1){
			card[0]=1;
		}
	}
	s.close();
}
//Hit or Stay
public static int hit_stay(int[] card,int num){//안씀
	Scanner sx = new Scanner(System.in);
	int choice=0;
	System.out.print("Hit(1) or Stay(0)? : ");
	choice = sx.nextInt();
	if(choice==1) {
		recieve_Card(card,num);
		num++;
	}
	sx.close();
	return choice;
}
//게임 판정
public static int Decision(int[] user_card, int user_num, int[] com_card, int com_num){//유저 승리 2, 컴퓨터승리 1, 무승부 0
	int user_score=0;
	int com_score=0;
	for(int i=0;i<user_card.length;i++){
		user_score+=user_card[i];
	}
	for(int i=0;i<com_card.length;i++){
		com_score+=com_card[i];
	}
	if(user_score>21){
		if(com_score>21)	return 0;
		else return 1;
	}
	else if(com_score>21) return 2;
	
	else if(21-user_score < 21-com_score) return 2;
	else if(21-user_score > 21-com_score) return 1;
	else {		//user_score = com_score
		if(user_num>com_num) return 1;
		else if(user_num<com_num) return 2;
		else return 0;
	}
}












}