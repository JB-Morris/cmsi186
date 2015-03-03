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
        System.out.println("testing Vector X and Y...");
        double testX = 5;
        double testY = 5;
        Vector vector = new Vector(testX, testY);
        try {
            displaySuccessIfTrue(vector.x() == testX && vector.y() == testY);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void testVectorAdd() {
        System.out.println("testing vector add method...");
        double testX = 5;
        double testY = 5;
        Vector vector = new Vector(testX, testY);
        Vector vectorAddTest = new Vector(5,5);
        vector.add(vectorAddTest);
        try {
            displaySuccessIfTrue(vector.x() == 10 && vector.y() == 10);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void testBallRadius(){
        System.out.println("testing ball radius...");
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
        try {
            displaySuccessIfTrue(ball.getRadius() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void testBallLocation() {
        System.out.println("testing ball location...");
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
        Vector locationTest = new Vector(0,0);
        try {
            displaySuccessIfTrue(ball.getLocation().equals(locationTest));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
    }

    private static void testBallAcceleration() {
        System.out.println("testing ball acceleration...");
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
        Vector acceleration = new Vector(0,0);
        ball.accelerate(acceleration, 1);
//      expected 4.8
    }

    private static void testBallMove() {
        System.out.println("testing ball movement... ");
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(5,5);
        Ball ball = new Ball(radius, location, initialVelocity);
        ball.move(1);
//        expected ball.location.x() = 5 ...y() = 5
    }





}