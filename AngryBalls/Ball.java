public class Ball {

    // Declare my instance variables!
    private double radius;
    private Vector location;
    private Vector velocity;
    public static final Vector GRAVITY = new Vector(0, -9.8);
    
    public Ball(double radius, Vector location, Vector initialVelocity) {
        // Implement me!
        this.radius = radius;
        this.location = new Vector(location.x(), location.y());
        this.velocity = new Vector(initialVelocity.x(), initialVelocity.y());
    }

    public Vector getLocation() {
        // Implement me!
        return this.location;
    }

    public double getRadius() {
        // Implement me!
        return this.radius;
    }
    
    public void accelerate(Vector acceleration, double grain) {
        // Implement me!
        this.velocity.add(acceleration.scale(grain));
//        this.velocity.add(GRAVITY.scale(grain));
    }

    public void move(double grain) {
        // Implement me!
        this.location.add(velocity.scale(grain));
    }
}
