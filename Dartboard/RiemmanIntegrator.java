public class RiemmanIntegrator{

    private Type type;
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

    public RiemmanIntegrator(String type, double[] coefficients, double bound1, double bound2){
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
        System.out.println("result: " + estimate());
    }

    private double estimate(){
        int rectangleCount = 1;
        double currentGuess = 0;
        double pastGuess;
        double percentage = 100;
        while(percentage > 0.01){
            System.out.println("--------------------");
            System.out.println("Rectangle count: " + rectangleCount);
            double result;
            result = riemmanSum(rectangleCount);
            if(currentGuess != 0){
                pastGuess = currentGuess;
                currentGuess = result;
                percentage = Math.abs(((currentGuess - pastGuess) / pastGuess) * 100);
                rectangleCount++;
            }else{
                currentGuess = result;
                percentage = 100.0;
                rectangleCount++;
            }
            System.out.println("Current guess: " + currentGuess);
            System.out.println("--------------------");

        }
        return currentGuess;
    }

    private double riemmanSum(int rectangleCount){
        double result = 0;
        double width = (this.xUpperBound - this.xLowerBound) / rectangleCount;
        double step = width / 2;
        System.out.println("Rectange width: " + width);
        for(double i = xLowerBound + step; i <= xUpperBound - step; i += width){
            result += polynomial(i);
        }
        result *= width;
        return result;
    }

    private double createRectangle(double height){
        return 0.0;
    }

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

    private enum Type{
        POLY, SIN, LN
    }

    public static void main(String args[]){
        RiemmanIntegrator riemman;
        double[] coef = new double[args.length - 3];
        for(int i = 1; i <= args.length  - 3; i++){
            coef[i - 1] = Double.parseDouble(args[i]);
        }
        riemman = new RiemmanIntegrator(args[0], coef, Double.parseDouble(args[args.length - 2]), Double.parseDouble(args[args.length - 1]));
    }
}