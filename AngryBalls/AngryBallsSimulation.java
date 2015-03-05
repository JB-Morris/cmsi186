public class AngryBallsSimulation {

    double timeStamp;

    public AngryBallsSimulation(double redRadius, Vector redInitialLocation, Vector redInitialVelocity, double blueRadius, Vector blueinitialLocation, Vector blueInitialVelocity){
        Ball redBall = new Ball(redRadius, redInitialLocation, redInitialVelocity);
        Ball blueBall = new Ball(blueRadius, blueinitialLocation, blueInitialVelocity);
        this.timeStamp = 1;
    }
    public AngryBallsSimulation(double redRadius, Vector redInitialLocation, Vector redInitialVelocity, double blueRadius, Vector blueinitialLocation, Vector blueInitialVelocity, double timeStamp){
        Ball redBall = new Ball(redRadius, redInitialLocation, redInitialVelocity);
        Ball blueBall = new Ball(blueRadius, blueinitialLocation, blueInitialVelocity);
        this.timeStamp = timeStamp;
    }
}