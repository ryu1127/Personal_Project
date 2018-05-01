import java.util.Scanner;

public class ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int p_year=2016;		//���� ��¥
		int p_month = 1;
		int p_day = 1;
		
		int m_year=2015;		//��º�ȯ�� 2016�� 1�� 1���� ���³�¥
		int m_month=11;
		int m_day=22;
		
		int day_check=0;		//���� �������� �˱� ���� ����
		int lunar_day=0;		//���� ��¥�� ���������� ����
		
		boolean yoon = true;	//�������� �ƴ��� �Ǻ��ϴ� ����
		
		Scanner s = new Scanner(System.in);		//Scanner��ü �ҷ�����
		
		System.out.print("�⵵  : ");
		try{p_year = s.nextInt();}				//try�� �ȿ� ����. try�� �ȿ��� ���� �߻��� catch�� �Ѿ��.
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(p_year!=2016){						//�Է� Ÿ���� �ǰ� �����Ͽ����� ��¥�� ������ �߸� �� ��� 
			System.out.println("Range Error! ");
			return;
		}
		
		
		System.out.print("��  : ");
		try{p_month = s.nextInt();}				//�⵵�� ���� ���
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(!(p_month>=1&&p_month<=12)){
			System.out.println("Range Error! ");
			return;
		}
		
		System.out.println("��  : ");
		try{p_day = s.nextInt();}				//�⵵�� ���� ���
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(!(p_day>=1&&p_day<=month_day(p_year,p_month))){
			System.out.println("Range Error! ");
			return;
		}
		
		day_check = day_cal(p_year,p_month,p_day)%7;		//���� ���
		switch(day_check){									//0001.1.1�� �������� �Ͽ� �� ��¥�� ���� ������ ������ ����
		case 0:
			System.out.println("������");
			break;
		case 1:
			System.out.println("ȭ����");
			break;
		case 2:
			System.out.println("������");
			break;
		case 3:
			System.out.println("�����");
			break;
		case 4:
			System.out.println("�ݿ���");
			break;
		case 5:
			System.out.println("�����");
			break;
		case 6:
			System.out.println("�Ͽ���");
			break;
		default:
			System.out.println("error");
			break;
		};
		lunar_day = day_change(p_year,p_month,p_day,m_year,m_month,m_day);	//ex20161017�� ��ȯ�ϹǷ� �����ϴ� ���� �ʿ�
		p_day = lunar_day%100;												//day_change = 20160924�� ��������Ƿ� �������� ���ϴ� ������ ���� ��¥ ����
		lunar_day = lunar_day/100;
		p_month = lunar_day%100;
		p_year = lunar_day/100;												//���ƴ� ������ �ٽ� ����
		
		System.out.println("���� ��¥ : "+p_year+"�� "+p_month+"�� "+p_day+"�� ");	//���³�¥ ���
		
		
		
		
		
	}
	
	
	//������ ���(��� �� �� ���)
	public static int day_cal(int year,int month, int day){
		
		int Yoon_day = 0;				//���⿡ ���� �߰��� ��
		int last_day = 0;				//���� �� �� �ޱ����� ��¥�� ����  �߰��� ��
		int day_of_week =0;				//�� �׳������� ��� �ϼ� 
		
		for(int i=1;i<year;i++){		//�����϶����� �Ϸ羿 ���Ѵ�.
			if(isYoon(i)==true){
				Yoon_day++;
			}
		}
		
		for(int i=1;i<month;i++){		//���� �� �������� ��ü ��¥�� ���Ѵ�.
			last_day += month_day(year,i);
		}
		day_of_week = (year-1)*365 + Yoon_day + last_day + day-1 ;		//�� ����ϼ��� ���Ѵ�.
		
		return day_of_week;					//��ȯ
	}
	
	//�� ���� ��ĥ���� ���(���)
	public static int month_day(int year, int month){
		if(month == 4 || month == 6 || month==9 || month == 11){		//�޺��� ������ ��¥�� ���ǹ����� ����
			return 30;
		}
		else if(month==2&&isYoon(year)==true){
			return 29;
		}
		else if(month==2&&isYoon(year)==false){
			return 28;
		}
		else return 31;
	}
	
	//�� ���� ��ĥ���� ���(����)
	public static int month_day_lunar(int year, int month){
		
			if(month ==1 || month == 3 || month == 6 || month == 8 || month == 9 || month == 11) return 30;
			else return 29;
	}
	
	//�������� üũ���ִ� �Լ� ( ���Ⱚ�� ���������� ���� ��Ų��.)
	public static boolean isYoon(int year){		
		
		boolean yoon = true;
		
		if(year%4==0&&year%100!=0||year%400==0) yoon = true;			//������ ����
		else yoon = false;
		
	return yoon;
	}
	
	//���³�¥ ����� ���� ��� �� �� ��� -> 2016.1.1 ���� �������� ���
		public static int day_cal_2016(int year,int month, int day){
			
			int last_day = 0;								//���� ��³�¥�� ���ϴ� ��İ� �Ȱ���.
			int day_of_week =0;
			
			for(int i=1;i<month;i++){
				last_day += month_day(year,i);
			}
			day_of_week = last_day + day-1 ;
			
			return day_of_week;
		}
		
		//���� ��¥ ���� �Լ�
		public static int day_change(int year, int month, int day,int l_year,int l_month,int l_day){
			
			int lyear = l_year;			//���� ���� ��¥�� �޾Ƽ� ���Ƿ� ����
			int lmonth = l_month;
			int lday = l_day;
			
			int temp = day_cal_2016(year,month,day); //����ϼ� ����(2016.1.1����)
			for(int i = 1;i<=temp;i++){
				if(lyear == 2015){
					if(lmonth == 12 && lday == 29){	//2015�⵵�� ������ ��
						lyear +=1;
						lmonth = 1;
						lday =1;
					}
					else if(lmonth == 11&& lday == 30){	//2015�⵵ 11���� ���������ΰ�� 
						lmonth +=1;
						lday = 1;
					}
					else lday++;			//2015�⵵�� ���������� �ƴѰ��
				}
				else{						//2016�⵵�� ���
					if(lday == month_day_lunar(lyear,lmonth)){		//���� ī��Ʈ ���� ���� �� ���� �������� �ƴѰ��
						lmonth++;
						lday =1;
					}
					else lday++;
				}
			}
			lyear = lyear * 10000;					//��ȯ�� ���� ���� ��ȯ
			lmonth = lmonth * 100;
			return lyear+lmonth+lday; 		//��¥�� 2016�� 10�� 17���̸� 20161017 ���� ����
		}
		
}

