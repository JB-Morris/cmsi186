import java.lang.IllegalArgumentException;

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
        testSimulator();

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

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(5, 5);
            Vector acceleration = new Vector(0, 0);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 1);

            displaySuccessIfTrue(ball.getVelocity().x() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(5, 5);
            Vector acceleration = new Vector(0, 0);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 1);

            displaySuccessIfTrue(ball.getVelocity().y() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(0, 0);
            Vector acceleration = new Vector(5, 5);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 1);

            displaySuccessIfTrue(ball.getVelocity().x() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(0, 0);
            Vector acceleration = new Vector(5, 5);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 1);

            displaySuccessIfTrue(ball.getVelocity().y() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(0, 0);
            Vector acceleration = new Vector(5, 5);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 2);

            displaySuccessIfTrue(ball.getVelocity().x() == 10);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(0, 0);
            Vector acceleration = new Vector(5, 5);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 2);

            displaySuccessIfTrue(ball.getVelocity().x() == 10);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0, 0);
            Vector initialVelocity = new Vector(0, 0);
            Vector acceleration = new Vector(5.25, 3.87);
            Ball ball = new Ball(radius, location, initialVelocity);

            ball.accelerate(acceleration, 1);

            displaySuccessIfTrue(ball.getVelocity().x() == 5.25 && ball.getVelocity().y() == 3.87);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }

    private static void testBallMove() {
        System.out.println("testing ball movement... ");

        try {
            double radius = 5;
            Vector location = new Vector(0,0);
            Vector initialVelocity = new Vector(5,5);
            Ball ball = new Ball(radius, location, initialVelocity);
            ball.move(1);

            displaySuccessIfTrue(ball.getLocation().x() == 5 && ball.getLocation().y() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(0,0);
            Vector initialVelocity = new Vector(5.5, 5.5);
            Ball ball = new Ball(radius, location, initialVelocity);
            ball.move(1);

            displaySuccessIfTrue(ball.getLocation().x() == 5.5 && ball.getLocation().y() == 5.5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(50, 28);
            Vector initialVelocity = new Vector(17, 35);
            Ball ball = new Ball(radius, location, initialVelocity);
            ball.move(1);

            displaySuccessIfTrue(ball.getLocation().x() == 67 && ball.getLocation().y() == 63);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }


        try {
            double radius = 5;
            Vector location = new Vector(74.55, 28.18);
            Vector initialVelocity = new Vector(17.45, 35.17);
            Ball ball = new Ball(radius, location, initialVelocity);
            ball.move(1);

            displaySuccessIfTrue(ball.getLocation().x() == 92 && ball.getLocation().y() == 63.35);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            double radius = 5;
            Vector location = new Vector(5000, 375);
            Vector initialVelocity = new Vector(500, 800);
            Ball ball = new Ball(radius, location, initialVelocity);
            ball.move(1);

            displaySuccessIfTrue(ball.getLocation().x() == 5500 && ball.getLocation().y() == 1175);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

//        expected ball.location.x() = 5 ...y() = 5
    }
    private static void testSimulator() {
        System.out.println("Testing simulation constructors...");
        Vector zero = new Vector(0, 0);
        Vector test1 = new Vector(5, 5);
        Vector subZero = new Vector(-5, -5);
        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(0, zero, zero, 0, test1, zero);
        } catch(IllegalArgumentException e){
            displaySuccessIfTrue(true);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(-5, zero, zero, 30, test1, zero);
        } catch(IllegalArgumentException e){
            displaySuccessIfTrue(true);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(5, zero, zero, 30, test1, zero, .3);
            displaySuccessIfTrue(testSim.getGrain() == .3);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(5, zero, zero, 30, test1, zero, 5.8);
            displaySuccessIfTrue(testSim.getGrain() == 5.8);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("Testing ground detection... ");

        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(5, zero, zero, 30, test1, zero, .3);
            testSim.setTimeStamp(1);
            displaySuccessIfTrue(testSim.ballOnGround(testSim.getRed()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            AngryBallsSimulation testSim = new AngryBallsSimulation(5, zero, zero, 30, test1, zero, .3);
            testSim.setTimeStamp(1);
            displaySuccessIfTrue(!testSim.ballOnGround(testSim.getBlue()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, subZero, zero, 30, test1, zero, .3);
            testSim1.setTimeStamp(1);
            displaySuccessIfTrue(testSim1.ballOnGround(testSim1.getRed()));
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("Testing simulation movement...");

        try {
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, zero, test1, 5, test1, zero);
            testSim1.move();

            displaySuccessIfTrue(testSim1.getRed().getLocation().x() == 5 && testSim1.getRed().getLocation().y() == 5);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Vector test = new Vector(5, 5.4);
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, zero, test, 5, test1, zero);
            testSim1.move();
            testSim1.move();

            displaySuccessIfTrue(testSim1.getRed().getLocation().x() == 10 && testSim1.getRed().getLocation().y() == 1);
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("Testing simulation collision detection... ");

        try {
            Vector testV = new Vector(5, 5);
            Vector testV2 = new Vector(-5, 5);
            Vector testL = new Vector(0, 5);
            Vector testL2 = new Vector(20, 5);
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, testL, testV, 5, testL2, testV2);
            testSim1.move();

            displaySuccessIfTrue(testSim1.checkCollision());
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Vector testV = new Vector(5, 5);
            Vector testV2 = new Vector(5, 5);
            Vector testL = new Vector(0, 5);
            Vector testL2 = new Vector(20, 5);
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, testL, testV, 5, testL2, testV2);
            testSim1.move();

            displaySuccessIfTrue(!testSim1.checkCollision());
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Vector testV = new Vector(0, 0);
            Vector testL = new Vector(50, 50);
            AngryBallsSimulation testSim1 = new AngryBallsSimulation(5, testL, testV, 5, testL, testV);
            testSim1.move();

            displaySuccessIfTrue(testSim1.checkCollision());
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

    }
}