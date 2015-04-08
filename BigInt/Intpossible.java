import java.lang.*;
import java.lang.Boolean;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class Intpossible {

    private BooleanDeque digits = new BooleanDeque();
    private boolean negative = false;

    public Intpossible() {
        digits = new BooleanDeque();
        digits.add(false);
    }

    public Intpossible(String s) {
        for(int i = 0; i < s.length(); i++){

            if((!(Character.isDigit(s.charAt(i)))) && (s.charAt(i) != '+') && (s.charAt(i) != '-') && (s.charAt(i) != ' ')){
                throw new IllegalArgumentException("Intpossible is displased with your input choice.");
            }
        }
        binaryConverter(s);

    }

    public BooleanDeque getDigits(){
        return this.digits;
    }

    public boolean isNegative(){
        return this.negative;
    }

    public static void main(String[] args){
        if(args.length == 0){
            Intpossible bigInt = new Intpossible();
        }else {
            Intpossible bigInt = new Intpossible(args[0]);
            System.out.println("digits: " + bigInt.digits.toString());
            System.out.println(bigInt);
            Intpossible addend = new Intpossible("8");
            System.out.println("subtract: " + bigInt.minus(addend));
            System.out.println("subtract: " + bigInt.minus(addend));
            Intpossible eCheck = new Intpossible("8");
            if(addend.equals(eCheck)){
                System.out.println("SUCCESS BITCHES!!");
            }
            Intpossible gCheck = new Intpossible("20");
            if(gCheck.isGreaterThan(eCheck)){
                System.out.println("GREATER THAN WORKS!");
            }
            if(eCheck.isLessThan(gCheck)){
                System.out.println("LESS THAN WORKS!");
            }

            System.out.println("digits: " + bigInt.digits.toString());
        }
    }

    private BooleanDeque binaryConverter(String s){
        String screened = s.trim();
        int index = 0;
        String value;
        if((screened.charAt(index) == '+' || screened.charAt(index) == '-')){
            if(screened.charAt(index) == '-'){
                negative = true;
            }
            int i = 0;
            while(screened.charAt(index + i) == '0'){
                i++;
            }
            value = screened.substring(index + 1 + i);
        }else{
            int i = 0;
            while(screened.charAt(index + i) == '0' && screened.length() > 1){
                i++;
            }
            value = screened.substring(index + i);
        }
        String zero = "0";
        if(value.equals(zero)){
            this.digits.add(false);
            return digits;
        }

        StringBuilder newValue = new StringBuilder(value.length());

        if(value.length() > 1){
            for(int i = value.length() - 1; i > 0; i--){
                String charPair = value.substring(i - 1, i + 1);
                newValue.insert(0, divideBy2(charPair.charAt(1), charPair.charAt(0)));
                if(i == 1){
                    char firstDigit = divideBy2(charPair.charAt(0), '0');
                    if(!(firstDigit == '0')) {
                        newValue.insert(0, divideBy2(charPair.charAt(0), '0'));
                    }
                }
            }
        }else if(value.length() == 1){
            char firstDigit = divideBy2(value.charAt(0), '0');
            if(!(firstDigit == '0')) {
                newValue.insert(0, divideBy2(value.charAt(0), '0'));
            }else newValue.insert(0, '0');
        }
        if(!(value.charAt(0) == '0')) {
            if ((Character.getNumericValue(value.charAt(value.length() - 1))) % 2 == 0) {
                digits.add(false);
            } else {
                digits.add(true);
            }
        }
        if(!(newValue.charAt(0) == '0')){
            binaryConverter(newValue.toString());
        }
        return digits;
    }

    private char divideBy2(char c1, char c2){
        int currentDigit = Character.getNumericValue(c1);
        int nextDigit = Character.getNumericValue(c2);
        int ans;
        if(nextDigit % 2 == 0){
            ans = currentDigit / 2;
        }else{
            ans = currentDigit / 2 + 5;
        }
        char answer = Character.forDigit(ans, 10);
        return answer;
    }

    public boolean equals(Object n){
        if(this == n){
            return true;
        }
        if(n == null){
            return false;
        }
        if(getClass() != n.getClass()){
            return false;
        }

        Intpossible other = (Intpossible)n;
        if(!(this.toString().equals(other.toString()))){
            return false;
        }

        if(this.isNegative() != other.isNegative()){
            return false;
        }
        return true;
    }

    protected void setNegative(boolean b){
        this.negative = b;
    }

    protected void setNegative(){
        this.negative = !(this.negative);
    }

    public boolean isGreaterThanAbsolute(Intpossible n) {
        System.out.println("Absolute greater than process starting... ");
        System.out.println(n.toString());
        Intpossible x = new Intpossible(n.toString());
        if(this.getDigits().getSize() > n.getDigits().getSize()){
            return true;
        }else{
            //itterate through binary and compare
            while(this.digits.pop() && x.digits.pop()){
            }
            if(this.digits.peek()){
                this.digits.reset();
                return true;
            }else{
                this.digits.reset();
                return false;
            }
        }
    }

    public boolean isGreaterThan(Intpossible n){
//        System.out.println("greater than process starting... ");
        System.out.println(n.toString());
        boolean result;
        if(this.equals(n)){
//            System.out.println("equals");
            return false;
        }else if((this.isNegative() == n.isNegative()) && this.isNegative()){
//            System.out.println("both negative");
            result = !(this.isGreaterThanAbsolute(n));
        }else if(this.isNegative() && !(n.isNegative())){
//            System.out.println("this is negative");
            result = false;
        }else if(!(this.isNegative()) && n.isNegative()){
//            System.out.println("other is negative");
            result = true;
        }else{
            result = this.isGreaterThanAbsolute(n);
        }
        return result;
    }

    public boolean isLessThan(Intpossible n) {
        if(this.equals(n)){
            return false;
        }
        boolean result = !(this.isGreaterThanAbsolute(n));
        return result;
    }

    public Intpossible minus(Intpossible subtrahend){
        BooleanDeque result = new BooleanDeque();
        Intpossible subtrahend1 = new Intpossible(subtrahend.toString());
        if(this.isNegative() && !(subtrahend.isNegative())){
            return this.plus(subtrahend);
        }
        boolean carry = false;
        int loop;
        if(this.digits.getSize() > subtrahend1.getDigits().getSize()){
            for(int i = 0; i < this.digits.getSize() - subtrahend1.getDigits().getSize(); i++){
                subtrahend1.getDigits().zeroPad();
            }
        }else if(this.digits.getSize() < subtrahend.getDigits().getSize()){
            //pad subtrahend and make this negative
        }
        loop = this.getDigits().getSize();
        for(int i = 0; i < loop; i++) {
            if (subtrahend1.getDigits().isEmpty()) {
                subtrahend1.getDigits().add(false);
            }
            if (this.getDigits().isEmpty()) {
                this.getDigits().add(false);
            }
            boolean x = subtrahend1.getDigits().popBottom();
            boolean y = this.getDigits().popBottom();
//            System.out.println("x: " + !x + " y: " + y + " carry: " + carry);
            if (!x && y && carry) {
//            1 + 1 + 1 = 11
//                System.out.println("1 + 1 + 1");
                carry = true;
                result.add(true);
            } else if ((!x && y && !carry) || ((x ^ !y) && carry)) {
//            1 + 1 + 0 || 1 + 0 + 1 = 10
//                System.out.println("1 + 1 + 0");
                carry = true;
                result.add(false);
            } else if (((!x ^ y) && !carry) || ((!(!x || y)) && carry)) {
//            1 + 0 + 0 || 0 + 0 + 1 = 01
//                System.out.println("1 + 0 + 0");
                carry = false;
                result.add(true);
            } else {
//            0 + 0 + 0 = 0
                carry = false;
                result.add(false);
            }
        }
        this.digits = result;
        if(carry){
            Intpossible one = new Intpossible("1");
            this.plus(one);
        }
        subtrahend.digits.reset();
        this.digits.reset();
        return this;
    }

    public Intpossible mod(Intpossible divisor) {
            return this;
    }

    public Intpossible plus(Intpossible addend) {
        BooleanDeque result = new BooleanDeque();
        Intpossible addend1 = addend;
        boolean carry = false;
        int loop;
        if(addend1.getDigits().getSize() > this.getDigits().getSize()){
            loop = addend1.getDigits().getSize();
        }else{
            loop = this.getDigits().getSize();
        }
        for(int i = 0; i < loop; i++) {
            if (addend1.getDigits().isEmpty()) {
                addend1.getDigits().zeroPad();
            }
            if (this.getDigits().isEmpty()) {
                this.getDigits().zeroPad();
            }
            boolean x = addend1.getDigits().popBottom();
            boolean y = this.getDigits().popBottom();
//            System.out.println("x: " + x + " y: " + y + " carry: " + carry);
            if (x && y && carry) {
//            1 + 1 + 1 = 11
//                System.out.println("1 + 1 + 1");
                carry = true;
                result.add(true);
            } else if ((x && y && !carry) || ((x ^ y) && carry)) {
//            1 + 1 + 0 || 1 + 0 + 1 = 10
//                System.out.println("1 + 1 + 0");
                carry = true;
                result.add(false);
            } else if (((x ^ y) && !carry) || ((!(x || y)) && carry)) {
//            1 + 0 + 0 || 0 + 0 + 1 = 01
//                System.out.println("1 + 0 + 0");
                carry = false;
                result.add(true);
            } else {
//            0 + 0 + 0 = 0
                carry = false;
                result.add(false);
            }
        }
        if(carry){
            result.add(true);
        }

        this.digits = result;
        addend.digits.reset();
        this.digits.reset();
        return this;
    }

    public Intpossible times(Intpossible factor) {
        boolean carry = false;
        while(!(factor.getDigits().isEmpty())){
            if(factor.getDigits().pop() && true){
                carry = (this.digits.pop());

            }
        }
        return this;
    }

    public String toString() {
        return binaryReader(this.digits);
    }

    private String binaryReader(BooleanDeque b){
//        System.out.println("binary reader process started...");
        boolean isZero = false;
        String zero = "0";
        if(b.toString().equals(zero)){
            isZero = true;
        }
        boolean current = false;
        StringBuilder value = new StringBuilder(b.getSize()/2);
        value.append('0');
        while(!(b.getSize() == 1)){
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
        if(!(isZero)) {
            if (negative) {
                value.insert(0, '-');
            } else {
                value.insert(0, '+');
            }
        }
        b.reset();
        return value.toString();
    }

    private StringBuilder doubler(StringBuilder s){
//        System.out.println("doubler process started...");
        int charValue;
        Boolean carry = false;
        for(int i = s.length() - 1; i >= 0; i--) {
            charValue = Character.getNumericValue(s.charAt(i));
            charValue += charValue;
            if(carry){
                charValue += 1;
                carry = false;
            }
            if(charValue > 9){
                carry = true;
                charValue -= 10;
                s.setCharAt(i, Character.forDigit(charValue, 10));
                if(i > 0) {
                }else{
                    s.insert(0, '1');
                    return s;
                }
            }else{
                s.setCharAt(i, Character.forDigit(charValue, 10));
            }
        }return s;
    }

    private StringBuilder addOne(StringBuilder s){
        int charValue = Character.getNumericValue(s.charAt(s.length() - 1));
        charValue += 1;
        if(charValue > 9){
            charValue -= 10;
            s = addOne(s, s.length() - 2);
        }
        s.setCharAt(s.length() - 1, Character.forDigit(charValue, 10));
        return s;
    }

    private StringBuilder addOne(StringBuilder s, int index){
        int charValue = Character.getNumericValue(s.charAt(index));
        charValue += 1;
        if(charValue > 9){
            charValue -= 10;
            s = addOne(s, index - 1); //currently inspecting
        }
        s.setCharAt(index, Character.forDigit(charValue, 10));
        return s;
    }



    private class BooleanDeque {
//        stack has most significant bits on top
//        top of stack is end of array

        private boolean[] dequeArr;
        private int current = 0;
        private int max = 0;
        private int currentBottom = 0;

        protected BooleanDeque(){
            dequeArr = new boolean[10];
        }

        protected BooleanDeque(int size){
            dequeArr = new boolean[size];
        }


        protected void add(boolean value){
            if(current == dequeArr.length - 1){
                grow();
            }
            current++;
            max++;
            dequeArr[current] = value;
        }

        protected void zeroPad(){
            if(current == dequeArr.length - 1){
                grow();
            }
            current++;
            dequeArr[current] = false;
        }

        protected void addBottom(boolean value){
            if(current == dequeArr.length - 1){
                grow();
            }
            current++;
            max++;
            this.shiftLeft();
            this.dequeArr[0] = value;
        }


        protected boolean pop(){
            boolean value = dequeArr[current];
            current--;
            return value;
        }

        protected boolean popBottom(){
            currentBottom++;
            boolean value = dequeArr[currentBottom];
            return value;
        }

        protected boolean peek(){
            return dequeArr[current];
        }

        protected void grow(){
            boolean[] grownStack = new boolean[this.dequeArr.length * 2];
            for(int i = 0; i <= this.dequeArr.length - 1; i++) {
                grownStack[i] = this.dequeArr[i];
            }
            this.dequeArr = grownStack;
        }

        protected boolean isEmpty(){
            if(current == currentBottom){
                return true;
            }else return false;
        }

        public int getSize(){
            return this.current;
        }

        public int getCurrentBottom(){
            return this.currentBottom;
        }

        protected void reset(){
            current = max;
            currentBottom = 0;
        }

        protected void clear(){
            current = 0;
            max = 0;
        }

        protected void shiftRight(){
            this.pop();
        }

        protected void shiftLeft(){
            boolean[] oldArr = dequeArr;
            boolean[] newArr = new boolean[dequeArr.length + 1];
            newArr[0] = false;
            for(int i = 0; i <= oldArr.length - 1; i++){
                newArr[i + 1] = oldArr[i];
            }
            this.max++;
            this.current++;
            this.dequeArr = newArr;
        }


        public boolean[] getStackArr(){
            return this.dequeArr;
        }

        public boolean getStackArr(int index){
            return this.dequeArr[index];
        }

        public void setDequeArr(BooleanDeque b){
            this.clear();
            for(int i = 0; i <= b.getSize() - 1; i++){
                this.add(b.popBottom());
            }
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