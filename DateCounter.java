public class DateCounter{

	private static int days;

	public static void main(String[] args){
		int[] dates = new int[6];
		try{

			for(int i = 0; i <= 5; i++){
				dates[i] = Integer.parseInt(args[i]);


			}
		}catch	(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java DateCounter <year0> <month0> <day0> <year1> <month1> <day1>");	
		}
		if(isValidDate(dates[0], dates[1], dates[2]) == false || isValidDate(dates[3], dates[4], dates[5]) == false){
			System.out.println("Not Valid Date");
		}else if(isValidDate(dates[0], dates[1], dates[2]) == true || isValidDate(dates[3], dates[4], dates[5]) == true){	
			System.out.println("Leap year? " + isLeapYear(dates[0]));
			System.out.println("Days in first month: " + daysInMonth(dates[0], dates[1]));
			System.out.println("Days between dates: " + daysBetween(dates[0], dates[1], dates[2], dates[3], dates[4], dates[5]));
		}
	}


	public static boolean isLeapYear(int year) {
		if(year % 4 != 0){
			return false;
		}else if(year % 100 != 0){
			return true;
		}else if(year % 400 != 0){
			return false;
		}else return true;
	}

	public static int daysInMonth(int year, int month){
		int d = 0;
		switch (month){
			case 1: d = 31;
					break;
			case 2: if(isLeapYear(year) == true){
		 			d = 29;
		 			}else d = 28;
		 			break;
		 	case 3: d = 31;
		 			break;
		 	case 4: d = 30;
		 			break;
		 	case 5: d = 31;
		 			break;
		 	case 6: d = 30;
		 			break;
		 	case 7: d = 31;
		 			break;
		 	case 8: d = 31;
		 			break;
		 	case 9: d = 30;
		 			break;
		 	case 10: d = 31;
		 			break;
		 	case 11: d = 30;
		 			break;
		 	case 12: d = 31;
		 			break;		
		}



		// if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
		// 	return 31;
		// }else if(month == 4 || month == 6 || month == 9 || month == 11){
		// 	return 30;
		// }else if(month == 2){
		// 	if(isLeapYear(year) == true){
		// 		return 29;
		// 	}else return 28;
		// }else return 0;
		return d;
	}

	public static boolean isValidDate(int year, int month, int day){
		if(year < 0 || month < 1 || month > 12){
			return false;
		}else if(daysInMonth(year, month) < day || day < 1){
			return false;
		}else return true;
	}

	public static int daysBetween(int year0, int month0, int day0, int year1, int month1, int day1){
		int dateA[] = new int[3];
		int dateB[] = new int[3];
		
		boolean yearChanged = false;
		if(checkFirst(year0, month0, day0, year1, month1, day1) == true){
			dateA[0] = year0;
			dateA[1] = month0;
			dateA[2] = day0;
			dateB[0] = year1;
			dateB[1] = month1;
			dateB[2] = day1;
		}else if(checkFirst(year0, month0, day0, year1, month1, day1) == false){
			dateA[0] = year1;
			dateA[1] = month1;
			dateA[2] = day1;
			dateB[0] = year0;
			dateB[1] = month0;
			dateB[2] = day0;
		}if(dateB[0] - dateA[0] > 1){
			days = 365 * (dateB[0] - dateA[0]);
			if(dateB[1] - dateA[1] > 1){
				for(int i = dateA[1]; i != dateB[1]; i++){
					if(i > 12){
						i = 1;
						dateA[0] = dateA[0] + 1;
						yearChanged = true;
					}
					days = days + daysInMonth(dateA[0], i);
					System.out.println("for loop days in each month");
				}
				days = days - dateA[3] + dateB[3];
			}
		}
		return days;
	}

	public static boolean hasLeapSecond(int year){
		if((1972 <= year && year <= 1979) || (1981 <= year && year <= 1983) || year == 1985 || year == 1987 || (1989 <= year && year <= 1990) || (1992 <= year && year <= 1995) || (1997 <= year && year <= 1998) || year == 2005 || year == 2008 || year == 2012 || year == 2015){
			return true;
		}else return false;

	}

	public static boolean hasLeapSecond(int year, int month, int day){
		if(hasLeapSecond(year) == false){
			return false;
		}
		// else if(month == 6 || month != 12){

		// }
		// fix later
		return true;
	}
	

	private static boolean checkFirst(int year0, int month0, int day0, int year1, int month1, int day1){
		if(year0 < year1){
			return true;
		}else if(year0 > year1){
			return false;
		}else if(month0 < month1){
			return true;
		}else if(month0 > month1){
			return false;
		}else if(day0 < day1){
			return true;
		}else if(day0 > day1){
			return false;
		}else return true;
	}

	// private static int dayCounter(int year0, int month0, int day0, int year1, int month1, int day1){

	// 	while()
	// 		// while current date is not date B, increment up and count up a day

	// }



	

}
// public class DateLog{

// 	public DateLog(){

// 	}

// 	private static void dateLog(int year, int month, int day){
// 			int year = year;
// 			int month = month;
// 			int day = day;
// 		}

// 		public static int getYear(){
// 			return dateLog().year;
// 		}
// 		public static int getMonth(){
// 			return dateLog().month;
// 		}
// 		public static int getDay(){
// 			return dateLog().day;
// 		}
// }