public class PrintArgumentLengths {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java PrintArgumentLengths <any number of arguments>");
			return;
		}
		for (String arg: args) {
			System.out.println("arg: " + arg + ", length: " + arg.length());
		}
	}
}