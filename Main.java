import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[]args) throws FileNotFoundException {
        List<String> names = new ArrayList<String>();
        
        // Replace code's URL with complete URL of your input file
        File file = new File("C:/Users/Gideon Swee/Documents/My Stuff/Job Applications/Programming Test/RoyalRumble/RoyalRumble/testinput2.txt");
        
        try (Scanner fileScanner = new Scanner(file)) {
          // Check if lines are scanned
          while (fileScanner.hasNextLine()) {
              // Add to list of names to sort
              names.add(fileScanner.nextLine());
          }
          fileScanner.close();
        
        }
        //System.out.println(names);
        
        System.out.println(new RoyalRumble().getSortedList(names));
        
      }
}