import java.util.Random;
import java.util.Scanner;

class User {
    private String username;
    private double balance;

    public User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

public class BettingApp {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("===== Welcome to Java Betting App =====");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User user = new User(username, 1000.0); // Starting balance
        System.out.println("Account created with balance: $" + user.getBalance());

        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Place Bet");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: $" + user.getBalance());
                    break;

                case 2:
                    placeBet(user);
                    break;

                case 3:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        user.deposit(deposit);
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Invalid amount!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for playing!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 4);

        scanner.close();
    }

    private static void placeBet(User user) {

        if (user.getBalance() <= 0) {
            System.out.println("Insufficient balance!");
            return;
        }

        System.out.print("Enter bet amount: ");
        double betAmount = scanner.nextDouble();

        if (betAmount <= 0 || betAmount > user.getBalance()) {
            System.out.println("Invalid bet amount!");
            return;
        }

        System.out.print("Guess a number (1-10): ");
        int userGuess = scanner.nextInt();

        if (userGuess < 1 || userGuess > 10) {
            System.out.println("Invalid number!");
            return;
        }

        int winningNumber = random.nextInt(10) + 1;

        System.out.println("Winning Number: " + winningNumber);

        if (userGuess == winningNumber) {
            double winnings = betAmount * 5;
            user.deposit(winnings);
            System.out.println("🎉 You won $" + winnings);
        } else {
            user.withdraw(betAmount);
            System.out.println("❌ You lost $" + betAmount);
        }

        System.out.println("Updated Balance: $" + user.getBalance());
    }
}