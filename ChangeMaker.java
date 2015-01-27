public class ChangeMaker {

	public static void main(String[] args) {
		int ammount;
		try{
			ammount = Integer.parseInt(args[0]);
			if(ammount < 0){
				System.out.println("Cannot make change for negative cents.");

			}else{
				int[] result = getChange(ammount);
				System.out.println("Quarters: " + result[0]);
				System.out.println("Dimes: " + result[1]);
				System.out.println("Nickels: " + result[2]);
				System.out.println("Pennies: " + result[3]);
			}
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java ChangeMaker <ammount in cents>");
		} catch (NumberFormatException num) {
			System.out.println("Supplied value is not an integer");
		}
		// int ammount = Integer.parseInt(args[0]);
		

	}

	public static int getQuarters(int cents){
		int quarters = cents / 25;
		// ammount = cents % 25;
		return quarters;
	}

	public static int getDimes(int cents){
		int dimes = cents / 10;
		// ammount = cents % 10;
		return dimes;
	}

	public static int getNickels(int cents){
		int nickels = cents / 5;
		// ammount = cents % 5;
		return nickels;		
	}

	public static int getPennies(int cents){
		return cents;
	}

	public static int[] getChange(int cents){
		int[] change = new int[4];
		change[0] = getQuarters(cents);
		cents = cents % 25;
		change[1] = getDimes(cents);
		cents = cents % 10;
		change[2] = getNickels(cents);
		cents = cents % 5;
		change[3] = getPennies(cents);
		// System.out.println(change);
		return change;
	}
}