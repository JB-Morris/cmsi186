import java.lang.*;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class Intpossible {

    private BooleanStack digits = new BooleanStack();
    private boolean negative = false;

    public Intpossible() {
        digits = new BooleanStack();
        digits.add(false);
    }

    public Intpossible(String s) {
        //add check for letters method
        binaryConverter(s);

    }

    public static void main(String[] args){
        if(args.length == 0){
            Intpossible bigInt = new Intpossible();
        }else {
//            System.out.println(args[0]);
            Intpossible bigInt = new Intpossible(args[0]);
            System.out.println("digits: " + bigInt.digits.toString());
            System.out.println(bigInt);
        }
    }

    private BooleanStack binaryConverter(String s){
        String value = s;
        StringBuilder newValue = new StringBuilder(value.length());
//        if(Character.getNumericValue(value.charAt(value.length() - 1)) % 2 == 0){
//            digits.add(false);
//        }else {
//            digits.add(true);
//        }
        if(s.charAt(0) == '-'){
            negative = true;
            for(int i = value.length() - 1; i > 1; i--){
                String charPair = value.substring(i - 1, i + 1);
                newValue.insert(0, divideBy2(charPair.charAt(1), charPair.charAt(0)));
            }
        }else if(value.length() > 1){
            for(int i = value.length() - 1; i > 0; i--){
                String charPair = value.substring(i - 1, i + 1);
//                System.out.println("charPair: " + charPair);
                newValue.insert(0, divideBy2(charPair.charAt(1), charPair.charAt(0)));
                if(i == 1){
                    char firstDigit = divideBy2(charPair.charAt(0), '0');
                    if(!(firstDigit == '0')) {
                        newValue.insert(0, divideBy2(charPair.charAt(0), '0'));
                    }
                }
            }
        }else if(value.length() == 1){
//            System.out.println("made it here!");
            char firstDigit = divideBy2(s.charAt(0), '0');
            if(!(firstDigit == '0')) {
                newValue.insert(0, divideBy2(s.charAt(0), '0'));
            }else newValue.insert(0, '0');
        }
//        System.out.println("Value: " + value);
//        System.out.println("newValue: " + newValue);

        if(!(value.charAt(0) == '0')) {
            if ((Character.getNumericValue(value.charAt(value.length() - 1))) % 2 == 0) {
                digits.add(false);
            } else {
                digits.add(true);
            }
        }
        if(!(newValue.charAt(0) == '0')){
            binaryConverter(newValue.toString());
        }else {

        }
//        System.out.println(digits.stackArr.toString());
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
        return binaryReader(this.digits);
    }

//    private String binaryToDecimal(BooleanStack b){
//        int decimal = 0;
//        StringBuilder binary = new StringBuilder(b.toString());
//        int power = 0;
//        while(binary.length() != 0){
//            int lastDigit = Character.getNumericValue(binary.charAt(binary.length() - 1));
//            decimal += lastDigit*Math.pow(2, power);
//            power++;
//            binary.deleteCharAt(binary.length() - 1);
//        }
//        return "test";
//
//    }

    private String binaryReader(BooleanStack b){
        System.out.println("binary reader process started...");
        boolean current = false;
        StringBuilder value = new StringBuilder(b.getSize()/2);
        value.append('0');
        while(!(b.getSize() == 1)){
            System.out.println("                CURRENT VALUE: " + value);
            if(b.pop()){
                value = addOne(value);
                value = doubler(value);
            }else{
                value = doubler(value);
            }
        }
        if(b.pop()){
            value = addOne(value);
        }
        return value.toString();
    }

    private StringBuilder doubler(StringBuilder s){
        System.out.println("doubler process started...");
        int charValue;
        Boolean carry = false;
//
//        for(int i = 0; i < s.length(); i++){
//            int j = i;
//            charValue = Character.getNumericValue(s.charAt(i));
//            charValue += charValue;
//
//        }
        for(int i = s.length() - 1; i >= 0; i--) {
            charValue = Character.getNumericValue(s.charAt(i));
            System.out.println("preDoubled: " + charValue);
            charValue += charValue;
            if(carry){
                charValue += 1;
                carry = false;
            }
            System.out.println("postDoubled: " + charValue);
            if(charValue > 9){
                carry = true;
                charValue -= 10;
                s.setCharAt(i, Character.forDigit(charValue, 10));
                System.out.println("CHECK:" + s);
                if(i > 0) {
//                    s = addOne(s, i); //this may be where the method breaks
                }else{
                    s.insert(0, '1');
                    return s;
                }
            }else{
                s.setCharAt(i, Character.forDigit(charValue, 10));
            }
            System.out.println("VALUE (postDoubler) " + s);
        }return s;
    }

    private StringBuilder addOne(StringBuilder s){
        System.out.println("add one process started...");
        int charValue = Character.getNumericValue(s.charAt(s.length() - 1));
        System.out.println("preAdd: " + charValue);
        charValue += 1;
        System.out.println("postAdd: " + charValue);
        if(charValue > 9){
            charValue -= 10;
            s = addOne(s, s.length() - 2);
        }
        s.setCharAt(s.length() - 1, Character.forDigit(charValue, 10));
        System.out.println("VALUE (postAddOne) " + s);
        return s;
    }

    private StringBuilder addOne(StringBuilder s, int index){
        System.out.println("cary process started...");
        System.out.println("preCarry: " + s);
        int charValue = Character.getNumericValue(s.charAt(index));
        charValue += 1;
        if(charValue > 9){
            charValue -= 10;
            s = addOne(s, index - 1); //currently inspecting
        }
        s.setCharAt(index, Character.forDigit(charValue, 10));
        System.out.println("VALUE (postCarry): " + s);
        return s;
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
        private int max = 0;

        protected BooleanStack(){
            stackArr = new boolean[10];
        }

        protected BooleanStack(int size){
            stackArr = new boolean[size];
        }


        protected void add(boolean value){
            if(current == stackArr.length - 1){
                grow();
            }
            current++;
            max++;
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
            for(int i = 0; i <= this.stackArr.length - 1; i++) {
                grownStack[i] = this.stackArr[i];
            }
            this.stackArr = grownStack;
        }

        protected boolean isEmpty(){
            if(current == 0){
                return true;
            }else return false;
        }

        public int getSize(){
            return this.current;
        }

        protected void reset(){
            current = max;
        }

        protected void clear(){
            current = 0;
            max = 0;
        }


        public boolean[] getStackArr(){
            return this.stackArr;
        }

        @Override
        public String toString() {
            int currentPointer = this.current;
            StringBuilder binaryRepresentation = new StringBuilder(currentPointer + 1);
            while(!this.isEmpty()){
                if(this.pop()){
                    binaryRepresentation.append('1');
                }else{
                    binaryRepresentation.append('0');
                }
            }
            current = currentPointer;
            return binaryRepresentation.toString();
        }
    }

}