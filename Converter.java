import javax.swing.*;
import java.util.Scanner;
import java.awt.GridLayout;

public class Converter {

    public static void main(String[] args) {
        openUI();
        Scanner scanner = new Scanner(System.in);
        boolean conversion  = true;

        while (conversion) {
            System.out.print("Bitte gebe einen Betrag in Euro ein: ");
            if (scanner.hasNextDouble()) {
                double money = scanner.nextDouble();

                int choice;
                while (true) {
                    System.out.println("Wählen Sie eine Währung:");
                    System.out.println("1. Dollar");
                    System.out.println("2. Lira");
                    System.out.println("3. Dänische Krone");
                    System.out.println("4. Beenden");

                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        if (choice == 1 || choice == 2 || choice == 3 || choice == 4) {
                            break;
                        } else {
                            System.out.println("Ungültige Auswahl. Bitte wählen Sie eine andere Option.");
                        }
                    } else {
                        System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl zwischen 1 und 4 ein.");
                        scanner.next();
                    }
                }

                switch (choice) {
                    case 1:
                        convertToDollars(money);
                        break;
                    case 2:
                        convertToLira(money);
                        break;
                    case 3:
                        convertToKrone(money);
                        break;
                    case 4:
                        conversion = false;
                        break;
                    default:
                        System.out.println("Ungültige Eingabe. Bitte wählen Sie erneut.");
                }
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
                scanner.next();
            }
        }
    }

    public static void openUI() {

        JFrame frame = new JFrame("Währungsumrechner");
        frame.setSize(700, 400);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel euroLabel = new JLabel("Betrag in Euro:");
        JTextField euroInput = new JTextField();
        JLabel currencyLabel = new JLabel("Wählen Sie eine Währung:");
        JComboBox<String> currencyOptions = new JComboBox<>(new String[]{"Dollar", "Lira", "Dänische Krone"});
        JButton convertButton = new JButton("Umwandeln");
        JTextArea resultArea = new JTextArea();

        convertButton.addActionListener(e -> {
            String input = euroInput.getText().replace(",", ".");

            try {
                double euroAmount = Double.parseDouble(input);
                int selectedCurrency = currencyOptions.getSelectedIndex();
                double convertedAmount = switch (selectedCurrency) {
                    case 0 -> euroAmount * 1.06;
                    case 1 -> euroAmount * 29.48;
                    case 2 -> euroAmount * 7.46;
                    default -> 0;
                };

                resultArea.setText(String.format("Der Betrag in %s: %.2f", currencyOptions.getSelectedItem(), convertedAmount));

            } catch (NumberFormatException ex) {
                resultArea.setText("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
            }
        });

        panel.add(euroLabel);
        panel.add(euroInput);
        panel.add(currencyLabel);
        panel.add(currencyOptions);
        panel.add(convertButton);
        panel.add(resultArea);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void convertToDollars(double euro) {
        double dollars = euro * 1.06;
        System.out.printf("Der Betrag in Dollar: %.2f%n", dollars);
    }

    public static void convertToLira(double euro) {
        double lira = euro * 29.48;
        System.out.printf("Der Betrag in Lira: %.2f%n", lira);
    }

    public static void convertToKrone(double euro) {
        double krone = euro * 7.46;
        System.out.printf("Der Betrag in Dänische Krone: %.2f%n", krone);
    }
}
