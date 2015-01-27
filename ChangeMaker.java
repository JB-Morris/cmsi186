public class ChangeMaker {

	public static void main(String[] args) {
		try{

			int ammount = Integer.parseInt(args[0]);
			if(ammount < 0){
				System.out.println("Cannot make change for negative cents.");
			}
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java ChangeMaker <ammount in cents>");
		}catch (NumberFormatException num) {
			System.out.println("Supplied value is not an integer");
		}
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

	public static int getNickles(int cents){
		int nickles = cents / 5;
		// ammount = cents % 5;
		return nickles;		
	}

	public static int getPennies(int cents){
		return cents;
	}

	public static int[] getChange(int cents){
		int[] change = new int[4];
		change[0] = getQuarters(cents);
		change[1] = getDimes(cents);
		change[2] = getNickles(cents);
		change[3] = getPennies(cents);
		return change;
	}
}