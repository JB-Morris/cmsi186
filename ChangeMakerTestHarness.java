public class ChangeMakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_getQuarters();
        test_getDimes();
        test_getNickels();
        test_getPennies();
        test_getChange();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    private static void test_getQuarters() {
        System.out.println("Testing getQuarters...");

        try {
            displaySuccessIfTrue(4 == ChangeMaker.getQuarters(100));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == ChangeMaker.getQuarters(110));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(7 == ChangeMaker.getQuarters(180));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == ChangeMaker.getQuarters(52));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getQuarters(0));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getQuarters(7));
        } catch(Exception exc) {
            displayFailure();
        }
    }

    private static void test_getDimes() {
        System.out.println("Testing getDimes...");

        try {
            displaySuccessIfTrue(10 == ChangeMaker.getDimes(100));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(11 == ChangeMaker.getDimes(110));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(18 == ChangeMaker.getDimes(185));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(5 == ChangeMaker.getDimes(52));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getDimes(0));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getDimes(7));
        } catch(Exception exc) {
            displayFailure();
        }
    }

    private static void test_getNickels() {
        System.out.println("Testing getNickels...");

        try {
            displaySuccessIfTrue(20 == ChangeMaker.getNickels(100));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(22 == ChangeMaker.getNickels(112));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(37 == ChangeMaker.getNickels(185));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(10 == ChangeMaker.getNickels(52));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getNickels(0));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getNickels(4));
        } catch(Exception exc) {
            displayFailure();
        }
    }

    private static void test_getPennies() {
        System.out.println("Testing getPennies...");

        try {
            displaySuccessIfTrue(100 == ChangeMaker.getPennies(100));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(112 == ChangeMaker.getPennies(112));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(185 == ChangeMaker.getPennies(185));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(52 == ChangeMaker.getPennies(52));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == ChangeMaker.getPennies(0));
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == ChangeMaker.getPennies(4));
        } catch(Exception exc) {
            displayFailure();
        }
    }

    private static void test_getChange() {
        System.out.println("Testing getChange...");
        int[] change;

        try {
            change = ChangeMaker.getChange(100);
            displaySuccessIfTrue(4 == change.length &&
                    4 == change[0] &&
                    0 == change[1] &&
                    0 == change[2] &&
                    0 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(112);
            displaySuccessIfTrue(4 == change.length &&
                    4 == change[0] &&
                    1 == change[1] &&
                    0 == change[2] &&
                    2 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(185);
            displaySuccessIfTrue(4 == change.length &&
                    7 == change[0] &&
                    1 == change[1] &&
                    0 == change[2] &&
                    0 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(52);
            displaySuccessIfTrue(4 == change.length &&
                    2 == change[0] &&
                    0 == change[1] &&
                    0 == change[2] &&
                    2 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(0);
            displaySuccessIfTrue(4 == change.length &&
                    0 == change[0] &&
                    0 == change[1] &&
                    0 == change[2] &&
                    0 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(4);
            displaySuccessIfTrue(4 == change.length &&
                    0 == change[0] &&
                    0 == change[1] &&
                    0 == change[2] &&
                    4 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }

        try {
            change = ChangeMaker.getChange(43);
            displaySuccessIfTrue(4 == change.length &&
                    1 == change[0] &&
                    1 == change[1] &&
                    1 == change[2] &&
                    3 == change[3]);
        } catch(Exception exc) {
            displayFailure();
        }
    }

}
