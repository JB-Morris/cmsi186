public class DateCounter{
	public static boolean isLeapYear(int year) {
		return false;
	}

	public static int daysInMonth(int year, int month){
		return 0;
	}

	public static boolean isValidDate(int year, int month, int day){
		return false;
	}

	public static int daysBetween(int year0, int month0, int day0, int year1, int month1, int day1){
		return 0;
	}

	public static boolean hasLeapSecond(int year){
		return false;
	}

	public static boolean hasLeapSecond(int year, int month, int day){
		return false;
	}

	public static void main(String[] args){
		int dates[] = new int[5];
		if(args[0] == null){
			System.out.println("Usage: java DateCounter <year0> <month0> <day0> <year1> <month1> <day1>");
		}
		for(int i = 0; i > 5; i++){
			int input = Integer.parseInt(args[i]);
			dates[i] = input;
		}
		 daysBetween(dates[0], dates[1], dates[2], dates[3], dates[4], dates[5]);
	}

	private int daysPerMonth(int month){
		return 0;

	}
}