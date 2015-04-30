public class MakeOptimalChange {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {
            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.");
                System.out.println();
                printUsage();
                return;
            }

            String[] denominationStrings = args[0].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.");
                    System.out.println();
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.");
                        System.out.println();
                        printUsage();
                        return;
                    }
                }
            }

            Tally change = makeOptimalChange(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            printUsage();
        }
    }

    public static Tally makeOptimalChange(int[] denominations, int amount) {
//        implement me!
        Tally[][] tallyTable = new Tally[denominations.length][amount + 1];
        int[] data = new int[denominations.length];
        for(int i = 0; i < denominations.length; i++){
            data[i] = 0;
        }
        for(int i = 0; i < denominations.length; i++){
            tallyTable[i][0] = new Tally(data);
        }
        Tally result = tallyTable[0][0];;
        for(int dIndex = 0; dIndex <= denominations.length - 1; dIndex++){
//            System.out.println("Denomination Loop Start: dIndex = " + dIndex);
            for(int aIndex = 1; aIndex <= amount; aIndex++){
//                System.out.println("Amount Loop Start: aIndex = " + aIndex);
                if(aIndex >= denominations[dIndex]){
                    tallyTable[dIndex][aIndex] = new Tally(denominations.length);
                    tallyTable[dIndex][aIndex].setElement(dIndex, 1);
//                    find difference
                    int differenceIndex = aIndex - denominations[dIndex];
                    if(!(tallyTable[dIndex][differenceIndex].isImpossible())){
                        Tally sum = tallyTable[dIndex][aIndex].add(tallyTable[dIndex][differenceIndex]);
                        tallyTable[dIndex][aIndex] = sum;
                    }else{
                        tallyTable[dIndex][aIndex] = Tally.IMPOSSIBLE;
                    }
                    if((dIndex > 0) && (tallyTable[dIndex][aIndex].isImpossible())){
                        tallyTable[dIndex][aIndex] = tallyTable[dIndex - 1][aIndex];
                    }else if((dIndex > 0) && !(tallyTable[dIndex - 1][aIndex].isImpossible()) && (tallyTable[dIndex - 1][aIndex].total() < tallyTable[dIndex][aIndex].total())){
                        tallyTable[dIndex][aIndex] = tallyTable[dIndex - 1][aIndex];
                    }
                    result = tallyTable[dIndex][aIndex];
                }else if(dIndex > 0){
                    tallyTable[dIndex][aIndex] = tallyTable[dIndex - 1][aIndex];
                    result = tallyTable[dIndex][aIndex];
                }else{
//                    tallyTable[dIndex][aIndex] = new Tally(0);
//                    tallyTable[dIndex][aIndex] = tallyTable[dIndex][aIndex].IMPOSSIBLE;
                    tallyTable[dIndex][aIndex] = Tally.IMPOSSIBLE;
                    result = tallyTable[dIndex][aIndex];
                }
//                System.out.println("result = " + result);
            }
        }


        return result;
    }

    public static Tally makeOptimalChangeRecursive(int[] denominations, int amount){
        Tally[][] tallyTable = new Tally[denominations.length][amount + 1];
        int[] data = new int[denominations.length];
        for(int i = 0; i < denominations.length; i++){
            data[i] = 0;
        }
        for(int i = 0; i < denominations.length; i++){
            tallyTable[i][0] = new Tally(data);
        }
        return getTallyForCell(denominations.length - 1, amount, tallyTable, denominations);
    }

    private Tally getTallyForCell(int denominationIndex, int amountIndex, Tally[][] table, int[] denominations){
        if(table[denominationIndex][amountIndex] != null){
            return table[denominationIndex][amountIndex];
        }else{
            if(amountIndex >= denominations[denominationIndex]){
                table[denominationIndex][amountIndex] = new Tally(denominations.length);
                tallyTable[dIndex][aIndex].setElement(dIndex, 1);
//                table[denominationIndex][amountIndex].add(table[denominationIndex][amountIndex - denominations[denominationIndex]]);

                int differenceIndex = amountIndex - denominations[denominationIndex];
                if(!(table[denominationIndex][differenceIndex].isImpossible())){
                    Tally sum = table[denominationIndex][amountIndex].add(getTallyForCell(denominationIndex - 1, differenceIndex, table, denominations));
                    table[denominationIndex][amountIndex] = sum;
                }else{
                    table[denominationIndex][amountIndex] = Tally.IMPOSSIBLE;
                }
                if((denominationIndex > 0) && (table[denominationIndex][amountIndex].isImpossible())){
                    table[denominationIndex][amountIndex] = getTallyForCell(denominationIndex - 1, amountIndex, table, denominations);
                }else if((denominationIndex > 0) && !(table[denominationIndex - 1][amountIndex].isImpossible()) && (table[denominationIndex - 1][amountIndex].total() < table[denominationIndex][amountIndex].total())){
                    table[denominationIndex][amountIndex] = getTallyForCell(denominationIndex - 1, amountIndex, table, denominations);
                }
                result = table[denominationIndex][amountIndex];
            }else if(denominationIndex > 0){
//                table[denominationIndex][amountIndex] = table[denominationIndex - 1][amountIndex];
                table[denominationIndex][amountIndex] = getTallyForCell(denominationIndex - 1, amountIndex, table, denominations);
                result = table[denominationIndex][amountIndex];
            }else{
                table[denominationIndex][amountIndex] = Tally.IMPOSSIBLE;
                result = table[denominationIndex][amountIndex];
            }
            return result;
        }

    }

    private static void printUsage() {
        System.out.println("Usage: java MakeOptimalChange <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}
