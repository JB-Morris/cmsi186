public class Vector {

    // Declare my instance variables!
    private double x;
    private double y;
    
    public Vector(double x, double y) {
        // Impelemnt me!
        this.x = x;
        this.y = y;
    }
    
    public double x() {
        // Implement me!
        return this.x;

    }
    
    public double y() {
        // Implement me!
        return this.y;
    }

    public Vector add(Vector v) {
        // Implement me!
        this.x += v.x();
        this.y += v.y();
        return this;

    }

    public Vector scale(double magnitude) {
        // Implement me!
        this.x *= magnitude;
        this.y *= magnitude;
        return this;

    }

}
