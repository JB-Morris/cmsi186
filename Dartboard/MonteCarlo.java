public class MonteCarlo{

    private Type type;
    private int darts;
    private int upperBound;
    private int lowerBound;
    private double[] coefficients;

    public MonteCarlo(String type, double[] coefficients, int upperB, int lowerB){
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
        }
        this.upperBound = upperB;
        this.lowerBound = lowerB;
        this.coefficients = coefficients;
        this.darts = 10000;
        estimate();
    }

    public MonteCarlo(String type, double[] coefficients, int upperB, int lowerB, int darts){
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
        }
        this.upperBound = upperB;
        this.lowerBound = lowerB;
        this.coefficients = coefficients;
        this.darts = darts;
        estimate();
    }

    private double estimate(){
//        determine type
        return 0.0;
    }

    private enum Type{
        POLY, SIN, LN
    }

}