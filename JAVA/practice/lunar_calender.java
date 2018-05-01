import java.util.Scanner;

public class ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int p_year=2016;		//기준 날짜
		int p_month = 1;
		int p_day = 1;
		
		int m_year=2015;		//양력변환시 2016년 1월 1일인 음력날짜
		int m_month=11;
		int m_day=22;
		
		int day_check=0;		//무슨 요일인지 알기 위한 변수
		int lunar_day=0;		//음력 날짜를 형식적으로 저장
		
		boolean yoon = true;	//윤년인지 아닌지 판별하는 변수
		
		Scanner s = new Scanner(System.in);		//Scanner객체 불러오기
		
		System.out.print("년도  : ");
		try{p_year = s.nextInt();}				//try문 안에 실행. try문 안에서 예외 발생시 catch로 넘어간다.
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(p_year!=2016){						//입력 타입은 옳게 설정하였지만 날짜의 범위가 잘못 된 경우 
			System.out.println("Range Error! ");
			return;
		}
		
		
		System.out.print("월  : ");
		try{p_month = s.nextInt();}				//년도와 같은 방법
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(!(p_month>=1&&p_month<=12)){
			System.out.println("Range Error! ");
			return;
		}
		
		System.out.println("일  : ");
		try{p_day = s.nextInt();}				//년도와 같은 방법
		catch(Exception ime){
			System.out.println("Input Type Error occured!");
			return;
		}
		if(!(p_day>=1&&p_day<=month_day(p_year,p_month))){
			System.out.println("Range Error! ");
			return;
		}
		
		day_check = day_cal(p_year,p_month,p_day)%7;		//요일 계산
		switch(day_check){									//0001.1.1을 기준으로 하여 총 날짜를 나눈 값으로 요일을 측정
		case 0:
			System.out.println("월요일");
			break;
		case 1:
			System.out.println("화요일");
			break;
		case 2:
			System.out.println("수요일");
			break;
		case 3:
			System.out.println("목요일");
			break;
		case 4:
			System.out.println("금요일");
			break;
		case 5:
			System.out.println("토요일");
			break;
		case 6:
			System.out.println("일요일");
			break;
		default:
			System.out.println("error");
			break;
		};
		lunar_day = day_change(p_year,p_month,p_day,m_year,m_month,m_day);	//ex20161017을 반환하므로 분해하는 과정 필요
		p_day = lunar_day%100;												//day_change = 20160924가 들어있으므로 나머지를 구하는 과정을 통해 날짜 저장
		lunar_day = lunar_day/100;
		p_month = lunar_day%100;
		p_year = lunar_day/100;												//합쳤던 과정을 다시 분해
		
		System.out.println("음력 날짜 : "+p_year+"년 "+p_month+"월 "+p_day+"일 ");	//음력날짜 출력
		
		
		
		
		
	}
	
	
	//요일을 계산(양력 일 수 계산)
	public static int day_cal(int year,int month, int day){
		
		int Yoon_day = 0;				//윤년에 의해 추가된 날
		int last_day = 0;				//현재 달 전 달까지의 날짜에 의해  추가된 날
		int day_of_week =0;				//총 그날까지의 양력 일수 
		
		for(int i=1;i<year;i++){		//윤년일때마다 하루씩 더한다.
			if(isYoon(i)==true){
				Yoon_day++;
			}
		}
		
		for(int i=1;i<month;i++){		//현재 달 전까지의 전체 날짜를 구한다.
			last_day += month_day(year,i);
		}
		day_of_week = (year-1)*365 + Yoon_day + last_day + day-1 ;		//총 양력일수를 구한다.
		
		return day_of_week;					//반환
	}
	
	//그 달이 며칠인지 계산(양력)
	public static int month_day(int year, int month){
		if(month == 4 || month == 6 || month==9 || month == 11){		//달별로 정해진 날짜를 조건문으로 구분
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
	
	//그 달이 며칠인지 계산(음력)
	public static int month_day_lunar(int year, int month){
		
			if(month ==1 || month == 3 || month == 6 || month == 8 || month == 9 || month == 11) return 30;
			else return 29;
	}
	
	//윤년인지 체크해주는 함수 ( 윤년값을 진리값으로 리턴 시킨다.)
	public static boolean isYoon(int year){		
		
		boolean yoon = true;
		
		if(year%4==0&&year%100!=0||year%400==0) yoon = true;			//윤년의 조건
		else yoon = false;
		
	return yoon;
	}
	
	//음력날짜 계산을 위한 양력 일 수 계산 -> 2016.1.1 부터 몇일인지 계산
		public static int day_cal_2016(int year,int month, int day){
			
			int last_day = 0;								//위의 양력날짜를 구하는 방식과 똑같다.
			int day_of_week =0;
			
			for(int i=1;i<month;i++){
				last_day += month_day(year,i);
			}
			day_of_week = last_day + day-1 ;
			
			return day_of_week;
		}
		
		//음력 날짜 도출 함수
		public static int day_change(int year, int month, int day,int l_year,int l_month,int l_day){
			
			int lyear = l_year;			//기준 음력 날짜를 받아서 임의로 저장
			int lmonth = l_month;
			int lday = l_day;
			
			int temp = day_cal_2016(year,month,day); //양력일수 저장(2016.1.1부터)
			for(int i = 1;i<=temp;i++){
				if(lyear == 2015){
					if(lmonth == 12 && lday == 29){	//2015년도의 마지막 날
						lyear +=1;
						lmonth = 1;
						lday =1;
					}
					else if(lmonth == 11&& lday == 30){	//2015년도 11월의 마지막날인경우 
						lmonth +=1;
						lday = 1;
					}
					else lday++;			//2015년도중 마지막날이 아닌경우
				}
				else{						//2016년도인 경우
					if(lday == month_day_lunar(lyear,lmonth)){		//현재 카운트 중인 날이 그 달의 마지막이 아닌경우
						lmonth++;
						lday =1;
					}
					else lday++;
				}
			}
			lyear = lyear * 10000;					//반환을 위해 형식 변환
			lmonth = lmonth * 100;
			return lyear+lmonth+lday; 		//날짜가 2016년 10월 17일이면 20161017 값을 리턴
		}
		
}

