import java.lang.Double;
import java.lang.IllegalArgumentException;
import java.lang.Integer;

public class MonteCarlo{

    private Type type;
    private int darts;
    private int totalDarts;
    private double xUpperBound;
    private double xLowerBound;
    private double yUpperBound;
    private double yLowerBound;
//    dont let lower bound drop below zero
//    add way to calculate max and min values
    private double maximuminRange;
    private double minimumInRange;
//    note, coefficient array lists coefficients backwards
    private double[] coefficients;
    private int hitCount = 0;

    public MonteCarlo(String type, double[] coefficients, double bound1, double bound2){
        switch (type){
            case "poly":
                this.type = Type.POLY;
                break;
            case "sin":
                this.type = Type.SIN;
                break;
            case "ln":
                this.type = Type.LN;
                break;
            default:
                throw new IllegalArgumentException("Function not supported.");
        }
        if(bound1 > bound2){
            this.xUpperBound = bound1;
            this.xLowerBound = bound2;
        }else{
            this.xUpperBound = bound2;
            this.xLowerBound = bound1;
        }
        this.coefficients = coefficients;
        this.darts = 10000;
//        this.totalDarts = this.darts;

        estimate();
    }

    public MonteCarlo(String type, double[] coefficients, double bound1, double bound2, int darts){
        switch (type){
            case "poly":
                this.type = Type.POLY;
                break;
            case "sin":
                this.type = Type.SIN;
                break;
            case "ln":
                this.type = Type.LN;
                break;
            default:
                throw new IllegalArgumentException("Function not supported.");
        }
        if(bound1 > bound2){
            this.xUpperBound = bound1;
            this.xLowerBound = bound2;
        }else{
            this.xUpperBound = bound2;
            this.xLowerBound = bound1;
        }
        this.coefficients = coefficients;
        this.darts = darts;
//        this.totalDarts = this.darts;
        estimate();
    }

    private double estimate(){
        double result = 0;
        calculateMinMax();
        System.out.println("start");
        for(int i = 0; i < this.darts; i++){
            throwDart();
        }
        result = calculatePrecentage();
        System.out.println("end");
        System.out.println("Estimate: " + result);

//        may need to move code below into throw dart method

        return result;
    }

    public void calculateMinMax(){
        System.out.println("Estimating maximum and minimum... ");
        double maximum = polynomial(this.xLowerBound);
        double minimum = polynomial(this.xLowerBound);
        double current;
        for (double i = xLowerBound; i <= xUpperBound; i += (xUpperBound - xLowerBound)/1000000){
            if(this.type == Type.POLY){
                current = polynomial(i);
                if(current > maximum){
                    maximum = current;
                }
                if(current < minimum){
                    minimum = current;
                }
            }
        }
        this.maximuminRange = maximum;
        this.minimumInRange = minimum;
        double span = maximum - minimum;
        this.yUpperBound = maximum + (span / 10);
        this.yLowerBound = minimum - (span / 10);
        System.out.println("Minimum in range: " + minimum);
        System.out.println("Maximum in range: " + maximum);
        System.out.println("Final lower bound: " + this.yLowerBound);
        System.out.println("Final upper bound: " + this.yUpperBound);

    }

    private double calculatePrecentage(){
        double dartDivisor = this.darts;
//        System.out.println("dartDivisor = " + dartDivisor + " hit count = " + this.hitCount);
//        System.out.println("hit count: " + this.hitCount);
        double result = ((xUpperBound - xLowerBound) * (yUpperBound - yLowerBound)) * this.hitCount/dartDivisor;
        return result;
    }

    private void throwDart(){
        double actualY = 0;
        double guessX = ((xUpperBound - xLowerBound) * Math.random() + xLowerBound);
        double guessY = ((yUpperBound - yLowerBound) * Math.random() + yLowerBound);
        switch (this.type){
            case POLY:
                actualY = polynomial(guessX);
                break;
            case SIN:
                actualY = sine(guessX);
                break;
            case LN:
                actualY = naturalLog(guessX);
                break;
        }
        if(actualY <= 0){
            if(guessY >= actualY && guessY <= 0){
                this.hitCount++;
//                System.out.println("HIT!");
//                System.out.println("guess x: " + guessX);
//                System.out.println("guess y: " + guessY);
//                System.out.println("actual y: " + actualY);
//                System.out.println("--------------------------------");
                System.out.println(guessX + " " + guessY + " " + "in");
            }else{
//                System.out.println("guess x: " + guessX);
//                System.out.println("guess y: " + guessY);
//                System.out.println("actual y: " + actualY);
//                System.out.println("--------------------------------");
                System.out.println(guessX + " " + guessY + " " + "out");
            }
//            System.out.println(guessX + " " + guessY + " " );
        }else if(actualY >= 0){
            if(guessY <= actualY && guessY > 0){
                this.hitCount++;
//                System.out.println("HIT!");
//                System.out.println("guess x: " + guessX);
//                System.out.println("guess y: " + guessY);
//                System.out.println("actual y: " + actualY);
//                System.out.println("--------------------------------");
                System.out.println(guessX + " " + guessY + " " + "in");
            }else{
//                System.out.println("guess x: " + guessX);
//                System.out.println("guess y: " + guessY);
//                System.out.println("actual y: " + actualY);
//                System.out.println("--------------------------------");
                System.out.println(guessX + " " + guessY + " " + "out");
            }

        }

    }

//returns y value for polynomial given an x value
    private double polynomial(double xValue){
//        System.out.println("coefficients: " + Arrays.toString(coefficients));
        int exponent = 0;
        double yValue = 0;
        for(int i = this.coefficients.length - 1; i >= 0; i--){
            if(exponent == 0){
                yValue += coefficients[i];
                exponent++;
            }else{
                double coefficient = coefficients[i];
                double xPow = Math.pow(xValue, exponent);
                yValue += (coefficient * xPow);
                exponent++;
            }
        }
        return yValue;
    }

    private double sine(double xValue){
        return 0.0;
    }

    private double naturalLog(double xValue){
        return 0.0;
    }

    private enum Type{
        POLY, SIN, LN
    }

    public static void main(String[] args){
        if(args.length < 3){
            System.out.println("Usage: <function type>, <coefficent 1> ... <coefficient n>, <bound 1>, <bound 2>, 'total= ' <number of darts> (optional)");
        }
        MonteCarlo monty;
        double[] coef;
        String lastArg = args[args.length - 1];
//        System.out.println(lastArg.substring(0, 6));
        String totalEquals = "total=";
        if(lastArg.length() > 6 && lastArg.substring(0, 6).equals(totalEquals)){
            int dartCount = Integer.parseInt(lastArg.substring(6));
            coef = new double[args.length - 4];
//            int counter = 0;
            for(int i = 1; i<= args.length - 4; i++){
                coef[i - 1] = Double.parseDouble(args[i]);
            }
//            for(int i = args.length - 2; i > 0; i--){
//                coef[counter] = Double.parseDouble(args[i]);
//                counter++;
//            }
            monty = new MonteCarlo(args[0], coef, Double.parseDouble(args[args.length - 3]), Double.parseDouble(args[args.length - 2]), dartCount);
        }else{
            coef = new double[args.length - 3];
//            int counter = 0;
            for(int i = 1; i <= args.length - 3; i++){
//                for(int i = args.length - 3; i > 0; i--)
                coef[i - 1] = Double.parseDouble(args[i]);
//                counter++;
            }
            monty = new MonteCarlo(args[0], coef, Double.parseDouble(args[args.length - 2]), Double.parseDouble(args[args.length - 1]));
        }
//        System.out.println("x^2 + x + 1 " + monty.polynomial(7));

    }
}