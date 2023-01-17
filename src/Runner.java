import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Runner {

    public static void main(String[] args) {
        guessMe();


    }

    public static void guessMe() {
        System.out.println("-".repeat(70) + "\n" + " ".repeat(25) + "WELCOME TO GUESSME\n" + "-".repeat(70));
        System.out.println(" ".repeat(28) + "How to play?");
        System.out.println("\t*\tSelect how many digits the number will be");
        System.out.println("\t*\tGuess it");
        System.out.println("\t*\tIf a digit in your guess is within the \"number\" and\n\t\tAlso is exactly where it should be: \n\t\tYou will see (+) in the result");
        System.out.println("\t*\tIf a digit in your guess is within the \"number\" \n\t\tBut is not exactly where it should be: \n\t\tYou will see (-) in the result");
        System.out.println("\t*\tIf a digit in your guess is not within the \"number\"\n\t\tYou will not see any information about that digit");
        System.out.println("-".repeat(70));
        Scanner scanner = new Scanner(System.in);
        int length;
        do {
            System.out.print("Select how many digits the number will be < cannot be 0 > : ");
            length = scanner.nextInt();
        } while (length == 0);

        String number = "";
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                number += ThreadLocalRandom.current().nextInt(1, 10);
            } else {
                number += ThreadLocalRandom.current().nextInt(0, 10);
            }
        }

        String guess = "";
        String popUp = "";
        StringBuilder numberCheck;
        StringBuilder guessCheck;
        String lastCheck = "";
        int counter = 0;

        do {
            System.out.print("Guess it :");
            guess = scanner.next();
            if (guess.length() > number.length() || guess.length() < number.length()) {
                System.out.println("The number < " + length + " > digits but your guess is < " + guess.length() + " > digits");
                System.out.println("Your guess must have the same number of digits as the number");
                continue;
            } else if (guess.charAt(0) == 48) {
                System.out.println("Starts with 0? Let's not fool each other");
                continue;
            }

            numberCheck = new StringBuilder(number);
            guessCheck = new StringBuilder(guess);

            for (int i = 0; i < length; i++) {
                if (number.charAt(i) == guess.charAt(i)) {
                    numberCheck.setCharAt(i, '*');
                    guessCheck.setCharAt(i, '#');
                    popUp += "(+)";
                }
            }

            for (int i = 0; i < length; i++) {
                lastCheck += numberCheck;
                if (lastCheck.contains(guessCheck.substring(i, i + 1))) {
                    numberCheck.setCharAt(numberCheck.indexOf(guessCheck.substring(i, i + 1)), '*');
                    popUp += "(-)";
                }
                lastCheck = "";
            }

            System.out.println(popUp);

            numberCheck.delete(0, length);
            guessCheck.delete(0, length);
            popUp = "";
            counter++;

            if (number.equals(guess)) {
                System.out.println("-".repeat(70) + "\n" + " ".repeat(13) + "CONGRATULATIONS! You found it on your " + counter + ". guess\n" + "-".repeat(70));
                System.out.println();
                System.out.println(" ".repeat(23) + "Do you want to play again?\n" + " ".repeat(28) + "< Y >      < N >");
                String playAgain = "";
                do {
                    System.out.print("\tYour answer : ");
                    playAgain = scanner.next();
                } while (!(playAgain.equalsIgnoreCase("y") || playAgain.equalsIgnoreCase("n")));
                System.out.println();

                if (playAgain.equalsIgnoreCase("Y")) {
                    do {
                        System.out.print("Select how many digits the number will be < cannot be 0 > : ");
                        length = scanner.nextInt();
                    } while (length == 0);

                    number = "";
                    for (int i = 0; i < length; i++) {
                        if (i == 0) {
                            number += ThreadLocalRandom.current().nextInt(1, 10);
                        } else {
                            number += ThreadLocalRandom.current().nextInt(0, 10);
                        }
                    }
                    counter = 0;
                } else if (playAgain.equalsIgnoreCase("N")) {
                    break;
                }
            }

        } while (true);


        System.out.println(" ".repeat(13) + "If you decide the number, do you want me to guess?\n" + " ".repeat(28) + "< Y >      < N >");
        String answer = "";
        do {
            System.out.print("\tYour answer : ");
            answer = scanner.next();
        } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));
        System.out.println();


        if (answer.equalsIgnoreCase("Y")) {
            System.out.println("\tYou have to wait a little. Let's see you at v.2.0");
        } else if (answer.equalsIgnoreCase("N")) {
            System.out.println("\tAll right\n");
        }
        System.out.println("-".repeat(70) + "\n" + " ".repeat(26) + "THANKS FOR PLAYING\n" + "-".repeat(70));

        scanner.close();
    }
}


