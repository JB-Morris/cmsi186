public class AngryBallsSimulation {

    private double timeStamp = 0;
    private Ball redBall;
    private Ball blueBall;
    private double grain;
    private Vector redCollision;
    private Vector blueCollision;
    private double collisionTimeStamp;
    private boolean collision = false;
    public static final Vector GRAVITY = new Vector(0, -9.8);

    public AngryBallsSimulation(double redRadius, Vector redInitialLocation, Vector redInitialVelocity, double blueRadius, Vector blueInitialLocation, Vector blueInitialVelocity){
        redBall = new Ball(redRadius, redInitialLocation, redInitialVelocity);
        blueBall = new Ball(blueRadius, blueInitialLocation, blueInitialVelocity);
        this.grain = 1;
    }
    public AngryBallsSimulation(double redRadius, Vector redInitialLocation, Vector redInitialVelocity, double blueRadius, Vector blueInitialLocation, Vector blueInitialVelocity, double grain){
        redBall = new Ball(redRadius, redInitialLocation, redInitialVelocity);
        blueBall = new Ball(blueRadius, blueInitialLocation, blueInitialVelocity);
        this.grain = grain;
    }

    public static void main(String[] args){
        AngryBallsSimulation game;
        if(args.length == 0){
            System.out.println("Usage: java AngryBallsSimulation <red radius> <red x> <red y> <red velocity x> <red velocity y> <blue radius> <blue x> <blue y> <blue velocity x> <blue velocity y> [ <grain> ]");
            System.out.println("All sizes are in meters and the grain is in seconds. The grain is optional and devaults to 1 second if not supplied");
            return;
        }else if(args.length == 10){
            game = new AngryBallsSimulation(Double.parseDouble(args[0]), new Vector(Double.parseDouble(args[1]), Double.parseDouble(args[2])), new Vector(Double.parseDouble(args[3]), Double.parseDouble(args[4])), Double.parseDouble(args[5]), new Vector(Double.parseDouble(args[6]), Double.parseDouble(args[7])), new Vector(Double.parseDouble(args[8]), Double.parseDouble(args[9])));
        }else if(args.length == 11){
            game = new AngryBallsSimulation(Double.parseDouble(args[0]), new Vector(Double.parseDouble(args[1]), Double.parseDouble(args[2])), new Vector(Double.parseDouble(args[3]), Double.parseDouble(args[4])), Double.parseDouble(args[5]), new Vector(Double.parseDouble(args[6]), Double.parseDouble(args[7])), new Vector(Double.parseDouble(args[8]), Double.parseDouble(args[9])), Double.parseDouble(args[10]));
        }else {
            System.out.println("The arguments supplied do not match what AngryBallsSimulation expects");
            System.out.println("Usage: java AngryBallsSimulation <red radius> <red x> <red y> <red velocity x> <red velocity y> <blue radius> <blue x> <blue y> <blue velocity x> <blue velocity y> [ <grain> ]");
            System.out.println("All sizes are in meters and the grain is in seconds. The grain is optional and devaults to 1 second if not supplied");
            return;
        }
        game.play();
        System.out.println("end");
        if(game.collision){
            System.out.println("The Balls collided at timestamp " + game.collisionTimeStamp + " with the red ball at (" + game.redCollision.x() + ", " + game.redCollision.y() + ") and the blue ball at (" + game.blueCollision.x() + ", " + game.blueCollision.y() + ").");
        }else System.out.println("The balls did not collide.");
    }
    private void printTimeSlice(Ball r, Ball b, double grain){
//        System.out.println("Red radius: " + r.getRadius() + " Red x: " + r.getLocation().x() + " Red y: " + r.getLocation().y() + " Blue radius: " + b.getRadius() + " Blue x: " + b.getLocation().x() + " Blue y: " + b.getLocation().y());
        System.out.println(r.getRadius() + " " + r.getLocation().x() + " " + r.getLocation().y() + " " + b.getRadius() + " " + b.getLocation().x() + " " + b.getLocation().y());
    }

    private boolean checkCollision(){
        double distance = Math.pow((redBall.getLocation().x() - blueBall.getLocation().x()) * (redBall.getLocation().x() - blueBall.getLocation().x()) + (redBall.getLocation().y() - blueBall.getLocation().y()) * (redBall.getLocation().y() - blueBall.getLocation().y()), 0.5);
        if((redBall.getRadius() >= blueBall.getRadius() && distance <= redBall.getRadius() - blueBall.getRadius()) || (blueBall.getRadius() >= redBall.getRadius() && distance <= blueBall.getRadius() - redBall.getRadius()) || (redBall.getLocation().x() == blueBall.getLocation().x() && redBall.getLocation().y() == blueBall.getLocation().y())){
            redCollision = new Vector(redBall.getLocation().x(), redBall.getLocation().y());
            blueCollision = new Vector(blueBall.getLocation().x(), blueBall.getLocation().y());
            collisionTimeStamp = this.timeStamp;
            collision = true;
            return true;
        }else if(distance > (redBall.getRadius() + blueBall.getRadius())){
            return false;
        }else{
            redCollision = new Vector(redBall.getLocation().x(), redBall.getLocation().y());
            blueCollision = new Vector(blueBall.getLocation().x(), blueBall.getLocation().y());
            collisionTimeStamp = this.timeStamp;
            collision = true;
            return true;
        }
    }

    private void move(){
        if(!ballOnGround(redBall)){
            redBall.move(grain);
            redBall.accelerate(GRAVITY, grain);
        }
        if(!ballOnGround(blueBall)){
            blueBall.move(grain);
            blueBall.accelerate(GRAVITY, grain);
        }
        timeStamp++;
    }

    private boolean ballOnGround(Ball b){
        if(timeStamp > 0){
            if(b.getLocation().y() <= 0){
                b.getLocation().zeroY();
                return true;
            }else return false;
        }
        return false;
    }

    private void play() {
        while (!ballOnGround(redBall) || !ballOnGround(blueBall)) {
            checkCollision();
            printTimeSlice(redBall, blueBall, grain);
            move();
        }
        printTimeSlice(redBall, blueBall, grain);
    }

    public double getTimeStamp(){
        return timeStamp;
    }
    public Ball getRed(){
        return redBall;
    }
    public Ball getBlue(){
        return blueBall;
    }
    public double getGrain(){
        return grain;
    }
    public void setTimeStamp(double t){
        timeStamp = t;
    }
    public void setRedBall(double r, Vector l, Vector v){
        redBall = new Ball(r, l, v);
    }
    public void setBlueBall(double r, Vector l, Vector v){
        blueBall = new Ball(r, l, v);
    }
    public void setGrain(double g){
        grain = g;
    }
}