public class AngryBallsSimulation {

    private double timeStamp;
    private Ball redBall;
    private Ball blueBall;
    private double grain;

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
        if(args.length == 0){
            System.out.println("Usage: java AngryBallsSimulation <red radius> <red x> <red y> <red velocity x> <red velocity y> <blue radius> <blue x> <blue y> <blue velocity x> <blue velocity y> [ <grain> ]");
            System.out.println("All sizes are in meters and the grain is in seconds. The grain is optional and devaults to 1 second if not supplied");
            return;
        }else if(args.length == 10){
            AngryBallsSimulation game = new AngryBallsSimulation(Double.parseDouble(args[0]), new Vector(Double.parseDouble(args[1]), Double.parseDouble(args[2])), new Vector(Double.parseDouble(args[3]), Double.parseDouble(args[4])), Double.parseDouble(args[5]), new Vector(Double.parseDouble(args[6]), Double.parseDouble(args[7])), new Vector(Double.parseDouble(args[8]), Double.parseDouble(args[9])));
        }else if(args.length == 11){
            AngryBallsSimulation game = new AngryBallsSimulation(Double.parseDouble(args[0]), new Vector(Double.parseDouble(args[1]), Double.parseDouble(args[2])), new Vector(Double.parseDouble(args[3]), Double.parseDouble(args[4])), Double.parseDouble(args[5]), new Vector(Double.parseDouble(args[6]), Double.parseDouble(args[7])), new Vector(Double.parseDouble(args[8]), Double.parseDouble(args[9])), Double.parseDouble(args[10]));
        }else {
            System.out.println("The arguments supplied do not match what AngryBallsSimulation expects");
            System.out.println("Usage: java AngryBallsSimulation <red radius> <red x> <red y> <red velocity x> <red velocity y> <blue radius> <blue x> <blue y> <blue velocity x> <blue velocity y> [ <grain> ]");
            System.out.println("All sizes are in meters and the grain is in seconds. The grain is optional and devaults to 1 second if not supplied");
            return;
        }
        
    }
    private void printTimeSlice(Ball r, Ball b, double grain){
        System.out.println("Red radius: " + r.getRadius() + "Red x: " + r.getLocation().x() + "Red y: " + r.getLocation().y() + "Blue radius: " + b.getRadius() + "Blue x: " + b.getLocation().x() + "Blue y: " + b.getLocation().y());
    }

    private boolean checkCollision(){
        return false;
    }

    private void move(Ball r, Ball b){

    }

    private boolean ballsOnGround(){
        return false;
    }

    private void converter(){

    }
}