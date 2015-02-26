public class AngryBallsTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        testVectorXY();

        testBallRadius();
        testBallLocation();

        System.out.println(successes + "/" + attempts + " tests passed");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void testVectorXY() {
        double testX = 5;
        double testY = 5;
        Vector vector = new Vector(testX, testY);
        

    }

    private static void testVectorAdd() {
        double testX = 5;
        double testY = 5;
        Vector vector = new Vector(testX, testY);
    }

    private static void testBallRadius(){
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
    }

    private static void testBallLocation() {
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
    }





}