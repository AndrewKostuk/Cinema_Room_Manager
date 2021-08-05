package cinema;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static char[][] createCinema() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows:\n> ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        int seatsPerRow = scanner.nextInt();
        char[][] cinema = new char[rows][seatsPerRow];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(cinema[i], 'S');
        }
        return cinema;
    }

    public static void showMenu() {
        System.out.print(
                "1. Show the seats\n" +
                        "2. Buy a ticket\n" +
                        "3. Statistics\n" +
                        "0. Exit\n" +
                        "> ");
    }

    public static void showTheSeats(char[][] seats) {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1 + " ");
            for (char seat : seats[i]) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void buyATicket(char[][] seats) {
        int rows = seats.length;
        int seatsPerRow = seats[0].length;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a row number:\n> ");
            int chosenRow = scanner.nextInt();
            System.out.print("Enter a seat number in that row:\n> ");
            int chosenSeat = scanner.nextInt();
            if (chosenRow > rows || chosenSeat > seatsPerRow) {
                System.out.println("Wrong input!");
            } else if (seats[chosenRow - 1][chosenSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                seats[chosenRow - 1][chosenSeat - 1] = 'B';
                int price = (rows * seatsPerRow > 60 && chosenRow > rows / 2) ? 8 : 10;
                System.out.println("Ticket price: $" + price);
                break;
            }
        }
    }

    public static void showStatistics(char[][] seats) {
        int rows = seats.length;
        int seatsPerRow = seats[0].length;
        int purchasedTickets = 0;
        int totalSeats = rows * seatsPerRow;
        int currentIncome = 0;
        int totalIncome = totalSeats <= 60
                ? totalSeats * 10
                : rows / 2 * seatsPerRow * 10 + (rows - rows / 2) * seatsPerRow * 8;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                if (seats[i][j] == 'B') {
                    purchasedTickets++;
                    currentIncome += (totalSeats > 60 && i + 1 > rows / 2) ? 8 : 10;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", (double) purchasedTickets / totalSeats * 100);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static boolean chooseAction(char[][] seats) {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                return true;
            case 1:
                showTheSeats(seats);
                break;
            case 2:
                buyATicket(seats);
                break;
            case 3:
                showStatistics(seats);
                break;
            default:
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean stop = false;
        char[][] seats = createCinema();
        while (!stop) {
            stop = chooseAction(seats);
        }
    }
}