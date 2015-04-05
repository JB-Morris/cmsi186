import java.lang.System;

public class Intpossible {

    private BooleanStack digits = new BooleanStack();
    private boolean negative = false;

    public Intpossible() {
        digits = new BooleanStack();
        digits.add(false);
    }

    public Intpossible(String s) {
        binaryConverter(s);

    }

    public static void main(String[] args){
        if(args.length == 0){
            Intpossible bigInt = new Intpossible();
        }else {
            System.out.println(args[0]);
            Intpossible bigInt = new Intpossible(args[0]);
            System.out.println(bigInt.digits);
            System.out.println(bigInt);
        }
    }

    private BooleanStack binaryConverter(String s){
        String value = s;
        StringBuilder newValue = new StringBuilder(value.length());
        if(s.charAt(0) == '-'){
            negative = true;
            for(int i = value.length() - 1; i > 1; i--){
                String charPair = value.substring(i - 1, i + 1);
                newValue.insert(0, divideBy2(charPair.charAt(1), charPair.charAt(0)));
            }
        }else{
            for(int i = value.length() - 1; i > 0; i--){
                String charPair = value.substring(i - 1, i + 1);
                System.out.println("charPair: " + charPair);
                newValue.insert(0, divideBy2(charPair.charAt(1), charPair.charAt(0)));
                if(i == 1){
                    char firstDigit = divideBy2(charPair.charAt(0), '0');
                    if(!(firstDigit == 0)) {
                        newValue.insert(0, divideBy2(charPair.charAt(0), '0'));
                    }
                }
            }
        }

        System.out.println(newValue);
        if(!(newValue.length() == 0)) {
            if ((Character.getNumericValue(newValue.charAt(newValue.length() - 1))) % 2 == 0) {
                digits.add(false);
            } else {
                digits.add(true);
            }
        }
        if(!(newValue.toString() == "0")){
            binaryConverter(newValue.toString());
        }
        System.out.println(digits);
        return digits;
    }

    public boolean equals(Object n){
        return false;
    }

    private char divideBy2(char c1, char c2){
        int currentDigit = Character.getNumericValue(c1);
        int nextDigit = Character.getNumericValue(c2);
//        System.out.println(currentDigit);
//        System.out.println(nextDigit);
        int ans;
        if(nextDigit % 2 == 0){
            ans = currentDigit / 2;
        }else{
            ans = currentDigit / 2 + 5;
        }
//        System.out.println(ans);
        char answer = Character.forDigit(ans, 10);
//        System.out.println("dividebyt2: " + answer);
        return answer;
    }

    public boolean isGreaterThan(Intpossible n) {
        return false;
    }

    public boolean isLessThan(Intpossible n) {
        return false;
    }

    public Intpossible minus(Intpossible subtrahend){
        return this;
    }

    public Intpossible mod(Intpossible divisor) {
            return this;
    }

    public Intpossible plus(Intpossible addend) {
        return this;
    }

    public Intpossible times(Intpossible factor) {
        return this;
    }

    public String toString() {
        return "test";
    }


//    private void growArray(){
//        boolean[] grownArray = new boolean[this.digits.length() * 2];
//        for(int i = 0; i <= this.digits.size(); i++) {
//            grownArray[i] = this.digits[i];
//        }
//        this.digits = grownArray;
//    }

    private class BooleanStack {

        private boolean[] stackArr;
        private int current = 0;

        protected BooleanStack(){
            stackArr = new boolean[10];
        }

        protected BooleanStack(int size){
            stackArr = new boolean[size];
        }

        protected void add(boolean value){
            if(current == stackArr.length){
                grow();
            }
            current++;
            stackArr[current] = value;
        }

        protected boolean pop(){
            boolean value = stackArr[current];
            current--;
            return value;
        }

        protected boolean peek(){
            return stackArr[current];
        }

        protected void grow(){
            boolean[] grownStack = new boolean[this.stackArr.length * 2];
            for(int i = 0; i <= this.stackArr.length; i++) {
                grownStack[i] = this.stackArr[i];
            }
            this.stackArr = grownStack;
        }


    }

}