import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Temperature Converter!");
        char choice;

        do {
            System.out.print("Enter a temperature value: ");
            double temperature = scanner.nextDouble();

            System.out.print("Enter the unit of measurement (C or F): ");
            char unit = scanner.next().charAt(0);

            double convertedTemperature;
            if (unit == 'C' || unit == 'c') {
                // Convert Celsius to Fahrenheit
                convertedTemperature = (temperature * 9 / 5) + 32;
                System.out.println("Converted Celsius temperature in Fahrenheit: " + convertedTemperature + "°F");
            } else if (unit == 'F' || unit == 'f') {
                // Convert Fahrenheit to Celsius
                convertedTemperature = (temperature - 32) * 5 / 9;
                System.out.println("Converted Fahrenheit temperature in Celsius: " + convertedTemperature + "°C");
            } else {
                System.out.println("Invalid unit. Please enter 'C' or 'F'.");
            }

            System.out.print("Do you want to convert another temperature? (Y/N): ");
            choice = scanner.next().charAt(0);
        } while (choice == 'Y' || choice == 'y');

        System.out.println("Thank you for using the Temperature Converter!");
        scanner.close();
    }
}
