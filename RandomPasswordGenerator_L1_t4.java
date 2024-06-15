import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomPasswordGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean generateAnother = true;

        while (generateAnother) {
            System.out.print("Enter the desired length of the password: ");
            int length = scanner.nextInt();

            System.out.print("How many lowercase letters you want: ");
            int lowercaseCount = scanner.nextInt();

            System.out.print("How many uppercase letters you want: ");
            int uppercaseCount = scanner.nextInt();

            System.out.print("How many numbers you want: ");
            int numberCount = scanner.nextInt();

            System.out.print("How many special characters you want: ");
            int specialCount = scanner.nextInt();

            if (lowercaseCount + uppercaseCount + numberCount + specialCount > length) {
                System.out.println("The sum of specified counts exceeds the desired length. Please try again.");
                continue;
            }

            String password = generatePassword(length, lowercaseCount, uppercaseCount, numberCount, specialCount);
            System.out.println("Generated Password: " + password);
            System.out.println("Are you ok with the password or want to generate another password;");

            System.out.print("Generate another password? (yes/no): ");
            String response = scanner.next();
            generateAnother = response.equalsIgnoreCase("yes");
        }

        scanner.close();
    }

    private static String generatePassword(int length, int lowercaseCount, int uppercaseCount, int numberCount, int specialCount) {
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_+=<>?";

        Random random = new Random();
        List<Character> passwordChars = new ArrayList<>();

        for (int i = 0; i < lowercaseCount; i++) {
            passwordChars.add(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
        }
        for (int i = 0; i < uppercaseCount; i++) {
            passwordChars.add(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        }
        for (int i = 0; i < numberCount; i++) {
            passwordChars.add(numberChars.charAt(random.nextInt(numberChars.length())));
        }
        for (int i = 0; i < specialCount; i++) {
            passwordChars.add(specialChars.charAt(random.nextInt(specialChars.length())));
        }

        while (passwordChars.size() < length) {
            String allChars = lowercaseChars + uppercaseChars + numberChars + specialChars;
            passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        StringBuilder password = new StringBuilder(length);
        while (!passwordChars.isEmpty()) {
            int index = random.nextInt(passwordChars.size());
            password.append(passwordChars.remove(index));
        }

        return password.toString();
    }
}
