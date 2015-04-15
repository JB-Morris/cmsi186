public class PiEstimator{

    private int darts;
    private int totalDarts;
    private final int SQUARE_SIDE = 2;
    private final int RADIUS = SQUARE_SIDE / 2;
    private int hits = 0;
    private int misses = 0;
    private boolean hasRun = false;

//    x^2 + y*2 < 1 (definition of a circle)

    public PiEstimator(){
        darts = 10000;
        totalDarts = darts;
    }

    public PiEstimator(int d){
        darts = d;
        totalDarts = darts;
    }

    public void run(){
        this.hasRun = true;
        while (this.darts > 0){
            this.throwDart();
            this.darts--;
        }
    }

    public int getDarts(){
        return this.darts;
    }
    public int getTotalDarts(){
        return this.totalDarts;
    }

    public int getHits(){
        return this.hits;
    }

    private void throwDart(){
        double guessX = SQUARE_SIDE * Math.random() - 1;
        double guessY = SQUARE_SIDE * Math.random() - 1;
        if(withinCircle(guessX, guessY)){
            System.out.println(guessX + " " + guessY + " in");
            hits++;
        }else{
            System.out.println(guessX + " " + guessY + " out");
            misses++;
        }

    }

    private double calculatePercentage(){
        double hitsDouble = this.getHits();
        double totalDartsDouble = this.getTotalDarts();
        double percentage = hitsDouble / totalDartsDouble;
        return percentage;
    }

    private double estimatePi(){
        double result;
        if(this.hasRun){
            result = SQUARE_SIDE * SQUARE_SIDE * (calculatePercentage());
            return result;
        }else{
            this.run();
            result = SQUARE_SIDE * SQUARE_SIDE * (calculatePercentage());
            return result;
        }

    }

    private boolean withinCircle(double x, double y){
        if((x * x + y * y) < 1){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        PiEstimator estimate;
        if(args.length == 0){
            estimate = new PiEstimator();
        }else {
            int arg = Integer.parseInt(args[0]);
            estimate = new PiEstimator(arg);
        }
        System.out.println("start");
        estimate.run();
        System.out.println("end");
        System.out.println("Darts: " + estimate.getTotalDarts() + " Hits: " + estimate.getHits() + " Pi estimate: " + estimate.estimatePi());
    }
}