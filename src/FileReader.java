import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FileReader {
    private String fileName;

    //Empty constructor
    public FileReader () {
        fileName = null;
    }
    // Setter
    public void setFileName (String fileName) {
        this.fileName = fileName;
    }
    // Getter
    public String getFileName () {
        return fileName;
    }

    //********************************* Methods *******************************
    public Event[] readFile() throws IOException {
        Scanner fileR = new Scanner (new File (this.fileName));
        // Initilize Events array
        Event [] listEvents = new Event[ getNumEvents() ]; // The size of the array is the number of lines in file
        String [] token;
        String currLine = "";
        int i = 0;
        Concessions c; //Declare Concession object
        float concessionTax = 0;
        fileR.nextLine(); // Skip header
        while (fileR.hasNext()) {
            currLine = fileR.nextLine();
            token = currLine.split(",");
            // token[0] = Event name
            // token[1] = Venue name
            // token[2] = Event eventAddress
            // token[3] = Ticket Price
            // token[4] = Tickets Available
            // token[5] = Concession tax
            listEvents[i] = new Event();
            // Set the attributes of the event object at that position of the array using setters
            listEvents[i].setEventName(token[0]);
            listEvents[i].setVenueName(token[1]);
            listEvents[i].setEventAddress(token[2]);
            listEvents[i].setTicketPrice(Float.parseFloat(token[3]));
            listEvents[i].setTicketsAvailable(Integer.parseInt(token[4]));
            // Need to create new concession object for every event object
            c = new Concessions ();
            concessionTax = Float.parseFloat(token[5]);
            c.setTaxToPrice( concessionTax ); // Parse from string to float
            listEvents[i].setEventConcession(c);
            i++;
        }
        return listEvents; // Returns an array of objects already filled
    }

    public int getNumEvents () throws IOException {
        Scanner readFile = new Scanner (new File (this.fileName));
        int count = 0;
        readFile.nextLine(); // Skip header
        while (readFile.hasNext()) {
            count++; // Increment count by one
            readFile.nextLine();
        }
        return count;
    }

} // End of class
