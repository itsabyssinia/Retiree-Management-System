package cs143.ui;

import cs143.business.RetireeManager;
import cs143.domain.Retiree;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main class UI of the program
 *
 */
public class SsnMapApp {

    private static Scanner scanIn = new Scanner(System.in);
    private static RetireeManager rm = new RetireeManager();

    /**
     * The main driver for the program.
     *
     * @param args - The command line arguments, if any
     */
    public static void main(String[] args) {
        long input = 0;
        do {
            input = displayMenu();
            if (input == 0) {
                delete();
            } else if (input == 1) {
                add();
            } else if (input > 1) {
                get(input);
            }
        } while (input >= 0);
    }

    /**
     * Display the menu to the console
     *
     * @return the number of a specific option or the number of the Social
     * Security Number
     */
    public static long displayMenu() {
        long selection = 0L;
        long ssn = 0L;
        long min = 99999999L;
        long max = 999999999L;
        boolean checked = false;
        do {
            System.out.println("Select an option: ");
            System.out.println(" -1 - Exit");
            System.out.println(" 0 - Delete a Retiree");
            System.out.println(" 1 - Add a Retiree");
            System.out.println(" SSN - Get Monthly Benefits");
            System.out.print("> ");
            try {
                selection = scanIn.nextLong();
                if (selection == -1) {
                    System.out.println("Terminating the program...");
                    checked = true;
                } else if (selection == 0) {
                    checked = true;
                } else if (selection == 1) {
                    checked = true;
                } else if (selection > min && selection <= max) {
                    checked = true;
                } else {
                    System.out.println("Input error. Please select an "
                            + "appropriate option from above!\n");
                    checked = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Input error. Please select an "
                        + "appropriate option from above!\n");
                scanIn.nextLine();
                checked = false;
            }
        } while (checked == false);
        return selection;
    }

    /**
     * Delete a Retiree
     */
    public static void delete() {
        long ssn = 0L;
        long min = 99999999L;
        long max = 999999999L;
        boolean checked = false;
        do {
            //get the information of the Retiree from the user
            System.out.println("Enter the social security number "
                    + "of the Retiree: ");
            try {
                ssn = scanIn.nextLong();
                if (ssn > min && ssn <= max) {
                    checked = true;
                    //check if the Retiree is deleted succesfully or not
                    if (rm.delete(ssn) == true) {
                        System.out.println("The Retiree has been deleted "
                                + "successfully.");
                    } else {
                        System.out.println("Unable to remove the following "
                                + "Retiree.");
                    }

                } else {
                    System.out.println("Input error. Please enter an "
                            + "appropriate Social Security Number.\n");
                    checked = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Input error. Please enter an "
                        + "appropriate Social Security Number.\n");
                scanIn.nextLine();
                checked = false;
            }
        } while (checked == false);

    }

    /**
     * Add a Retiree
     */
    public static void add() {
        boolean checked1 = false;
        boolean checked2 = false;
        boolean checked = false;
        double benefit = 0;
        long ssn = 0L;
        long min = 1000000L;
        long max = 999999999L;
        Scanner scanner = new Scanner(System.in);
        //get the name of the Retiree from the user
        String name = "";
        // System.out.println("Enter the name of the Retiree: ");
        // String name = scanner.nextLine();
        do {
            System.out.println("Enter the retirees name: ");
            try {
                name = scanner.nextLine();
                if (name.matches("^([A-z\\'\\.-ᶜ]*(\\s))+[A-z\\'\\.-ᶜ]*$")) {
                    checked1 = true;
                } else {
                    System.out.println("Enter a name without symbols or numbers"
                            + " please.");
                    checked1 = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter an "
                        + "appropriate name!\n");
                scanIn.nextLine();
            }
        } while (checked1 == false);
        do {
            System.out.println("Enter the social security number "
                    + "of the Retiree: ");
            try {
                ssn = scanIn.nextLong();
                if (ssn > min && ssn <= max) {
                    checked1 = true;
                } else {
                    System.out.println("Please enter an "
                            + "appropriate Social Security Number!\n");
                    checked1 = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter an "
                        + "appropriate Social Security Number!\n");
                scanIn.nextLine();
            }
        } while (checked1 == false);
//get the amount of benefit for that Retiree
        do {
            System.out.println("Enter the benefit money of the Retiree: ");
            try {
                benefit = scanIn.nextDouble();
                if (ssn > min) {
                    checked2 = true;
                } else {
                    System.out.println("Please enter an "
                            + "appropriate Social Security Number!\n");
                    checked2 = false;
                }
            } catch (InputMismatchException ime) {
                System.out.println("Please enter an "
                        + "appropriate number!\n");
                scanIn.nextLine();
            }
        } while (checked2 == false);

        Retiree r = new Retiree(ssn, name, benefit);
        checked = rm.add(r);
//check if the Retiree is added succesfully or not
        if (checked == true) {
            System.out.println("The Retiree has been added successfully.");
        } else {
            System.out.println("The Retiree is already existed. "
                    + "Please try again!");
        }
    }

    /**
     * Get the monthly benefit from the Social Security Number
     *
     * @param ssn the Social Security Number
     */
    public static void get(long ssn) {
        if (rm.get(ssn) == null) {
            System.out.println("Retiree does not exist in the system!");
        } else {
            System.out.println(rm.get(ssn).toString());
        }
    }

}
