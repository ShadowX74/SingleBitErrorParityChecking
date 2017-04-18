/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paritychecking;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ShadowX
 */
public class ParityChecking {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    static Scanner scan = new Scanner(System.in);
    static ArrayList<int[]> phrase = new ArrayList();
    static ArrayList<int[]> check = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Would you like to A)create or B)insert a parity?");
        String input = scan.nextLine().toUpperCase();
        if (input.equals("A")) {
            setup();
            createParity();
            for (int[] letters : phrase) {
                tostring(letters);
            }
        } else if (input.equals("B")) {
            insert();
            checkParity();
        }
    }

    public static void tostring(int[] letters) {
        for (int i : letters) {
            System.out.print(i + "");
        }
        System.out.println("");
    }

    public static void tostring(int[] letters, ArrayList<Integer> x) {
        for (int i = 0; i < letters.length; i++) {
            boolean out = false;
            for (Integer j : x) {
                if (i == j) {
                    out = true;
                }
            }
            if (!out) {
                System.out.print(letters[i] + "");
            } else {
                System.out.print(ANSI_RED + letters[i] + "" + ANSI_RESET);
            }
        }
        System.out.println("");
    }

    public static void setup() {
        System.out.println("How many characters are there?");
        int chars = scan.nextInt();
        for (int i = 0; i < chars; i++) {
            System.out.println("What is the ASCII value for character " + (i + 1) + "?");
            int value = scan.nextInt();
            int[] letterParity = new int[11];
            letterParity[10] = value % 10;
            value /= 10;
            letterParity[9] = value % 10;
            value /= 10;
            letterParity[8] = value % 10;
            value /= 10;
            letterParity[6] = value % 10;
            value /= 10;
            letterParity[5] = value % 10;
            value /= 10;
            letterParity[4] = value % 10;
            value /= 10;
            letterParity[2] = value % 10;
            phrase.add(letterParity);
        }
    }

    public static void createParity() {
        for (int[] letter : phrase) {
            //Check bit 1
            if ((letter[2] + letter[4] + letter[6] + letter[8] + letter[10]) % 2 == 0) {
                letter[0] = 0;
            } else {
                letter[0] = 1;
            }
            //Check bit 2
            if ((letter[2] + letter[5] + letter[6] + letter[9] + letter[10]) % 2 == 0) {
                letter[1] = 0;
            } else {
                letter[1] = 1;
            }
            //Check bit 4
            if ((letter[5] + letter[6]) % 2 == 0) {
                letter[3] = 0;
            } else {
                letter[3] = 1;
            }
            //Check bit 8
            if ((letter[8] + letter[9] + letter[10]) % 2 == 0) {
                letter[7] = 0;
            } else {
                letter[7] = 1;
            }
        }
    }

    public static void insert() {
        System.out.println("How many characters are there?");
        int chars = scan.nextInt();
        for (int i = 0; i < chars; i++) {
            System.out.println("What is the parity value for character " + (i + 1) + "?");
            int value = scan.nextInt();
            int[] letterParity = new int[11];
            letterParity[10] = value % 10;
            value /= 10;
            letterParity[9] = value % 10;
            value /= 10;
            letterParity[8] = value % 10;
            value /= 10;
            letterParity[7] = value % 10;
            value /= 10;
            letterParity[6] = value % 10;
            value /= 10;
            letterParity[5] = value % 10;
            value /= 10;
            letterParity[4] = value % 10;
            value /= 10;
            letterParity[3] = value % 10;
            value /= 10;
            letterParity[2] = value % 10;
            value /= 10;
            letterParity[1] = value % 10;
            value /= 10;
            letterParity[0] = value % 10;
            phrase.add(letterParity);
        }
    }

    public static void checkParity() {
        for (int i = 0; i < phrase.size(); i++) {
            check.add(new int[11]);
            for (int j : phrase.get(i)) {
                check.get(i)[j] = phrase.get(i)[j];
            }
            //Check bit 1
            if ((phrase.get(i)[2] + phrase.get(i)[4] + phrase.get(i)[6] + phrase.get(i)[8] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[0] = 0;
            } else {
                check.get(i)[0] = 1;
            }
            //Check bit 2
            if ((phrase.get(i)[2] + phrase.get(i)[5] + phrase.get(i)[6] + phrase.get(i)[9] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[1] = 0;
            } else {
                check.get(i)[1] = 1;
            }
            //Check bit 4
            if ((phrase.get(i)[4] + phrase.get(i)[5] + phrase.get(i)[6]) % 2 == 0) {
                check.get(i)[3] = 0;
            } else {
                check.get(i)[3] = 1;
            }
            //Check bit 8
            if ((phrase.get(i)[8] + phrase.get(i)[9] + phrase.get(i)[10]) % 2 == 0) {
                check.get(i)[7] = 0;
            } else {
                check.get(i)[7] = 1;
            }
        }

        for (int i = 0; i < phrase.size(); i++) {
            int wrong = 0;
            ArrayList<Integer> bits = new ArrayList<Integer>();
            //Check bit 1
            if (phrase.get(i)[0] != check.get(i)[0]) {
                wrong++;
                bits.add(0);
            }
            //Check bit 2
            if (phrase.get(i)[1] != check.get(i)[1]) {
                wrong++;
                bits.add(1);
            }
            //Check bit 4
            if (phrase.get(i)[3] != check.get(i)[3]) {
                wrong++;
                bits.add(3);
            }
            //Check bit 8
            if (phrase.get(i)[7] != check.get(i)[7]) {
                wrong++;
                bits.add(7);
            }
            ArrayList<Integer> wrongbits = new ArrayList<Integer>();
            ArrayList<Integer> checkbits = new ArrayList<Integer>();
            checkbits.add(0);
            checkbits.add(1);
            checkbits.add(3);
            checkbits.add(7);
            if (wrong == 0) {
                tostring(phrase.get(i));
            } else if (wrong == 1) {
                tostring(phrase.get(i), bits);
            } else {
                if (bits.contains(0) && bits.contains(1)) {
                    wrongbits.add(2);
                }
                if (bits.contains(0) && bits.contains(3)) {
                    wrongbits.add(4);
                }
                if (bits.contains(1) && bits.contains(3)) {
                    wrongbits.add(5);
                }
                if (bits.contains(0) && bits.contains(1) && bits.contains(3)) {
                    wrongbits.add(6);
                }
                if (bits.contains(0) && bits.contains(7)) {
                    wrongbits.add(8);
                }
                if (bits.contains(1) && bits.contains(7)) {
                    wrongbits.add(9);
                }
                if (bits.contains(0) && bits.contains(1) && bits.contains(7)) {
                    wrongbits.add(10);
                }
                tostring(phrase.get(i), wrongbits);
            }
        }
    }
}
