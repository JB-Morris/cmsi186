public class Intpossible {

    private boolean[] digits;

    public Intpossible() {
        digits = new boolean[1];
        digits[0] = false;
    }

    public Intpossible(String s) {
        binaryConverter(s);

    }

    private boolean[] binaryConverter(String s){

        for(int i = s.length(); i >= 0; i--){
            s.substring(i, i);
        }

        return digits;
    }

    public boolean equals(Object n){
        return false;
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

    private class BooleanQueue {

        private boolean[] queueArr;
        private int current = 0;
        private int itterateCount = 0;

        protected BooleanQueue(){
            queueArr = new boolean[10];
        }

        protected BooleanQueue(int size){
            queueArr = new boolean[size];
        }

        protected void add(boolean value){
            if(current == queueArr.length){
                grow();
            }
            current++;
            queueArr[current] = value;
        }

        protected boolean remove(){
            boolean value = queueArr[itterateCount];
            itterateCount++;
            return value;
        }

        protected boolean peek(){
            return queueArr[itterateCount];
        }

        protected void grow(){
            boolean[] grownQueue = new boolean[this.queueArr.length * 2];
            for(int i = 0; i <= this.queueArr.length; i++) {
                grownQueue[i] = this.queueArr[i];
            }
            this.queueArr = grownQueue;
        }


    }

}