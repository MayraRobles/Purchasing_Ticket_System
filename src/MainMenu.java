import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    public static void main (String[] args) throws IOException {
        int userSelection = 1, userEvent = 1, numTickets = 0;
        float totalConcessions = 0f, total = 0f;
        char wantsConcession = 'y';
        Scanner inputUser = new Scanner (System.in);
        FileReader fileR = new FileReader();
        fileR.setFileName("events-info.csv");
        // The Event array is initialized by reading the file
        Event[] eventList = fileR.readFile();

        // Printing the title
        Scanner readTitle = new Scanner (new File ("Title.txt"));
        String currLine = "";
        while (readTitle.hasNext()) {
            currLine = readTitle.nextLine();
            System.out.println(currLine);
        }

        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("\tWelcome to PayDirt Ticket! What can I help you with today?");
        System.out.println("--------------------------------------------------------------------------------------");
        while (userSelection < 4 && userSelection > 0) {
            printMenu();
            userSelection = inputUser.nextInt();
            switch (userSelection) {
                case 1 :
                    System.out.println("All events:");
                    for (int i = 0; i < eventList.length; i ++) { //This for loop prints out the attributes of each event object in the array
                        eventList[i].toString(i + 1);
                    }
                    break;
                case 2 :
                    System.out.println("All concessions:");
                    for (int i = 0; i < eventList.length; i ++) {
                        // Get the concession object in the event object and then call the toString method for that concession
                        System.out.println(eventList[i].getEventConcession().toString(eventList[i].getEventName()));
                    }
                    break;
                case 3 :
                    System.out.println("All events:");
                    for (int i = 0; i < eventList.length; i ++) {
                        eventList[i].toString(i + 1);
                    }
                    System.out.print("Enter the number of the event you'd like to attend.\n>>");
                    userEvent = inputUser.nextInt();
                    userEvent--; // Because array starts at 0

                    if (userEvent < eventList.length && userEvent >= 0) {
                        // Used setters to get the name and price
                        System.out.println("You selected: " + eventList[userEvent].getEventName());
                        System.out.printf("Tickets for " + eventList[userEvent].getEventName()
                                + " cost $" + eventList[userEvent].getTicketPrice());
                        // Purchase tickets
                        System.out.print("\nHow many tickets would you like to buy?\n>>");
                        numTickets = inputUser.nextInt();
                        if (eventList[userEvent].isValid(numTickets)) {
                            // Get the price of the ticket which is an attribute of Event and multiply it by the number of tickets
                            float totalCostTickets = numTickets * eventList[userEvent].getTicketPrice();
                            int ticketsLeft = eventList[userEvent].purchaseTickets(numTickets);
                            System.out.println("Your purchase was successful!");
                            System.out.printf("You will be charged $%.2f", totalCostTickets);
                            System.out.print(" for tickets. There are " + ticketsLeft + " left.");
                            // Buy concessions
                            System.out.print("\nWould you like to buy concessions? (y/n)\n>>");
                            wantsConcession = inputUser.next().charAt(0);
                            while (wantsConcession == 'y' || wantsConcession == 'Y') {
                                System.out.println("Here are all the concessions available at " + eventList[userEvent].getEventName());
                                eventList[userEvent].getEventConcession().toString(eventList[userEvent].getEventName());
                                System.out.print("\nWhich concession would you like to buy? [enter name of the concession]\n>>");
                                // Flush Scanner
                                inputUser.nextLine();
                                String userConcession = inputUser.nextLine();
                                System.out.print("How many of these items would you like to purchase?\n>>");
                                int numConcessions = inputUser.nextInt();
                                String concessionLower = userConcession.toLowerCase();
                                if (concessionLower.equals("soda")) {
                                    // Get the concession object from the event object and then get the price of that concession
                                    totalConcessions += eventList[userEvent].getEventConcession().getSodaPrice() * numConcessions;
                                }
                                else if (concessionLower.equals("popcorn")) {
                                    totalConcessions = eventList[userEvent].getEventConcession().getPopcornPrice() * numConcessions;
                                }
                                else if (concessionLower.equals("hot dog")) {
                                    totalConcessions = eventList[userEvent].getEventConcession().getHotdogPrice() * numConcessions;
                                }
                                else {
                                    System.out.println("Concession not identified, try again.");
                                }
                                System.out.print("\nWould you like to buy any more concessions? (y/n)\n>>");
                                wantsConcession = inputUser.next().charAt(0);
                            } // End while
                            if (wantsConcession == 'n' || wantsConcession == 'N') {
                                System.out.printf("\nTicket charge: $%.2f", totalCostTickets);
                                System.out.printf("\nConcession charge: $%.2f", totalConcessions);
                                total += totalConcessions + totalCostTickets;
                                System.out.printf("\nTotal: $%.2f", total);
                                System.out.print("\nPlease enter a 16 digit credit card number to complete purchase\n>>");
                                // Flush
                                inputUser.nextLine();
                                String cardNumber = inputUser.nextLine();
                                if (isCreditCardNumberValid (cardNumber)) {
                                    System.out.printf("\nYour card ending in " + cardNumber.substring(11) + " was charged $%.2f", total);
                                    System.out.println(".\nThank you for your purchase and for using PaydirtTickets!");
                                    System.out.println("Goodbye!");
                                    userSelection = -1; // So that menu is not shown again
                                }
                                else {
                                    System.out.println("Credit card entered not valid.");
                                }
                            }
                            else {
                                System.out.println("Not valid. Enter y or n only.");
                            }
                        }
                        else {
                            System.out.println("Number of tickets not approved.");
                        }
                    }
                    else{
                        System.out.println("Number of event not valid. Try again");
                    }

                    break; // End of option 3
            } // End switch
        } // End while userSelection
    } //End main
    //****************************** Methods ***************************************
    public static void printMenu () {
        System.out.println("\n1. View events");
        System.out.println("2. View concessions");
        System.out.println("3. Purchase tickets and concessions");
        System.out.print("4. Exit system\n>>");
    }

    public static boolean isCreditCardNumberValid (String cardNumber) {
        // Checks if it is only digits and if it is 16 in length
        for (int i = 0; i < cardNumber.length(); i++) {
            if (!cardNumber.matches("[0-9]+") && cardNumber.length() != 16) {
                return false;
            }
        }
        return true;
    }

    public static void viewAllEventNames ( Event[] e) {
        for (int i = 0; i < e.length; i ++) { // Call the toString method of event for each event in array
            e[i].toString();
        }
    }

}// Ending curly brace

