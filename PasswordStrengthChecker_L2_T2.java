import java.util.Random;
import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String checkAnotherPassword;

        do {
            System.out.print("Enter a password to check its strength: ");
            String password = scanner.nextLine();

            String strength = checkPasswordStrength(password);
            System.out.println("Password strength: " + strength);

            if (!strength.equals("Strong")) {
                String suggestedPassword = generateStrongPassword(12);
                System.out.println("Instead of your password you can also use our suggested strong password for more security");
                System.out.println("Suggested strong password: " + suggestedPassword);
            }

            System.out.print("Would you like to check another password? (y/n): ");
            checkAnotherPassword = scanner.nextLine();
        } while (checkAnotherPassword.equalsIgnoreCase("y"));

        System.out.println("Thank you for using me!");
        scanner.close();
    }

    private static String checkPasswordStrength(String password) {
        int length = password.length();
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (isSpecialCharacter(c)) {
                hasSpecial = true;
            }
        }

        if (length >= 8 && hasUppercase && hasLowercase && hasNumber && hasSpecial) {
            return " Your password is Strong";
        } else if (length >= 6 && ((hasUppercase && hasLowercase) || (hasUppercase && hasNumber) || (hasLowercase && hasNumber))) {
            return " Your password is Moderate";
        } else {
            return " Your password is Weak";
        }
    }

    private static boolean isSpecialCharacter(char c) {
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";
        return specialCharacters.indexOf(c) >= 0;
    }

    private static String generateStrongPassword(int length) {
        String numbers = "0123456789";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";

        String allowedChars = numbers + lowercaseLetters + uppercaseLetters + specialCharacters;

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        password.append(numbers.charAt(random.nextInt(numbers.length())));
        password.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < length; i++) {
            password.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }

        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        Random random = new Random();

        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            char temp = characters[index];
            characters[index] = characters[i];
            characters[i] = temp;
        }

        return new String(characters);
    }
}
