import java.util.Scanner;

public class PalindromeChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueChecking;

        do {
            System.out.println("Enter a word to check if it is a palindrome:");
            String input = scanner.nextLine();
            String cleanedInput = cleanString(input);

            if (isPalindrome(cleanedInput)) {
                System.out.println("The word \"" + input + "\" is a palindrome.");
            } else {
                System.out.println("The word \"" + input + "\" is not a palindrome.");
            }

            System.out.println("Do you want to check another word ? (y/n):");
            continueChecking = scanner.nextLine().trim().toLowerCase();

        } while (continueChecking.equals("y"));

        scanner.close();
        System.out.println("Palindrome checking ended.");
    }

    private static String cleanString(String str) {
        return str.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    private static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
