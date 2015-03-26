public class Intpossible {

    private boolean[] digits;
    private boolean negative = false;

    public Intpossible() {
        digits = new boolean[1];
        digits[0] = false;
    }

    public Intpossible(String s) {
        binaryConverter(s);

    }

    private boolean[] binaryConverter(String s){
        String value = s;
        String newValue = value;
        if(s.charAt(0 == "-")){
            negative = true;
            for(int i = value.length() - 1; i > 0; i--){
                String charPair = value.substring(i, i + 1);
                divideBy2(charPair.charAt(1), charPair.charAt(0));
            }
        }else{
            for(int i = value.length() - 1; i >= 0; i--){
                String charPair = value.substring(i, i + 1);
                divideBy2(charPair.charAt(1), charPair.charAt(0));
            }
        }


        return digits;
    }

    public boolean equals(Object n){
        return false;
    }

    private char divideBy2(char c1, char c2){
        int currentDigit = Character.getNumericValue(c1);
        int nextDigit = Character.getNumericValue(c2);
        int ans;
        if(c2 % 2 == 0){
            ans = c1 / 2;
        }else{
            ans = c1 / 2 + 5;
        }
        char answer = (char)ans;
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