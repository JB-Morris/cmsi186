public class AngryBallsTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        testVectorXY();
        testVectorAdd();
        testBallRadius();
        testBallLocation();
        testBallAcceleration();
        testBallMove();

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
        testX = 3.8;
        testY = 5.8;
        Vector vector2 = new Vector(testX, testY);
        try {
            displaySuccessIfTrue(vector2.x() == testX && vector2.y() == testY);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        testX = 3000.238;
        testY = 5293.123;
        Vector vector3 = new Vector(testX, testY);
        try {
            displaySuccessIfTrue(vector3.x() == testX && vector3.y() == testY);
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
            displaySuccessIfTrue(vector.x() == (testX + vectorAddTest.x()) && vector.y() == (testY + vectorAddTest.y()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        testX = 7.25;
        testY = 20.8;
        Vector vector2 = new Vector(testX, testY);
        Vector vectorAddTest2 = new Vector(50.94, 85.32);
        vector2.add(vectorAddTest2);
        try {
            displaySuccessIfTrue(vector2.x() == (testX + vectorAddTest2.x()) && vector2.y() == (testY + vectorAddTest2.y()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        testX = 1000;
        testY = 1000;
        Vector vector3 = new Vector(testX, testY);
        Vector vectorAddTest3 = new Vector(-500, -500);
        vector3.add(vectorAddTest3);
        try {
            displaySuccessIfTrue(vector3.x() == (testX + vectorAddTest3.x()) && vector3.y() == (testY + vectorAddTest3.y()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        testX = 500.3;
        testY = 500.5;
        Vector vector4 = new Vector(testX, testY);
        Vector vectorAddTest4 = new Vector(-50.94, -85.32);
        vector4.add(vectorAddTest4);
        try {
            displaySuccessIfTrue(vector4.x() == (testX + vectorAddTest4.x()) && vector4.y() == (testY + vectorAddTest4.y()));
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
        radius = 5.123;
        Ball ball2 = new Ball(radius, location, initialVelocity);
        try {
            displaySuccessIfTrue(ball2.getRadius() == radius);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        radius = 1234.567;
        Ball ball3 = new Ball(radius, location, initialVelocity);
        try {
            displaySuccessIfTrue(ball3.getRadius() == radius);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void testBallLocation() {
        System.out.println("testing ball location...");
        double radius = 5;
        Vector location = new Vector(0,0);
        Vector initialVelocity = new Vector(0,0);
        Ball ball = new Ball(radius, location, initialVelocity);
        Vector locationTest = new Vector(0,0);
        try {
            displaySuccessIfTrue(ball.getLocation().x() == locationTest.x() && ball.getLocation().y() == locationTest.y());
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }
        Vector location2 = new Vector(123.456, 789.10112);
        Ball ball2 = new Ball(radius, location2, initialVelocity);
        Vector locationTest2 = new Vector(123.456, 789.10112);
        try {
            displaySuccessIfTrue(ball2.getLocation().x() == locationTest2.x() && ball2.getLocation().y() == locationTest2.y());
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