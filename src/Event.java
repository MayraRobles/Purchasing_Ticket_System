public class Event {
    private String eventName;
    private String venueName;
    private String eventAddress;
    private float ticketPrice;
    private int ticketsAvailable;
    private Concessions eventConcession;

    // Empty constructor
    public Event () {
        eventName = null;
        venueName = null;
        eventAddress = null;
        ticketPrice = 0f;
        ticketsAvailable = 0;
        eventConcession = null;
    }
    // Setters
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }
    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }
    public void setEventConcession(Concessions eventConcession) {
        this.eventConcession = eventConcession;
    }
    // Getters
    public String getEventName () {
        return eventName;
    }
    public String getVenueName () {
        return venueName;
    }
    public String getEventAddress () {
        return eventAddress;
    }
    public float getTicketPrice () {
        return ticketPrice;
    }
    public int getTicketsAvailable() {
        return ticketsAvailable;
    }
    public Concessions getEventConcession() {
        return eventConcession;
    }

    //************************************ Methods ************************************
    public boolean isValid (int ticketsPurchased) {
        if (ticketsPurchased > this.ticketsAvailable) { // Cannot purchase more tickets then they are available
            return false;
        }
        return true;
    }

    public int purchaseTickets (int ticketsPurchased) {
        return this.ticketsAvailable - ticketsPurchased; // Updates tickets available
    }
    // To string
    public String toString (int i) {
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| EVENT %-48x |%n", i);
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| %-20s | %31s |\n", "EVENT NAME", this.eventName);
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| %-20s | %31s |\n", "VENUE NAME", this.venueName);
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| %-20s | %31s |\n", "ADDRESS", this.eventAddress);
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| %-20s | $%30s |\n", "TICKET PRICE", this.ticketPrice);
        System.out.format("+--------------------------------------------------------+%n");
        System.out.printf("| %-20s | %31s |\n", "TICKETS AVAILABLE", this.ticketsAvailable);
        System.out.format("+--------------------------------------------------------+%n\n\n");
        return "";
    }

}// End of everything
