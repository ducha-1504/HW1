import java.io.*;
import java.util.Scanner;
public class PersonManager {
    public static void main(String[] args) throws FileNotFoundException {
        PersonDataManager manager = new PersonDataManager();
        Scanner sc = new Scanner(System.in);
        String menu = "";
        String filename = "";
        while (!menu.equalsIgnoreCase("Q")) {
            System.out.println("Menu:");
            System.out.println("(I) Import from File");
            System.out.println("(A) Add Person");
            System.out.println("(R) Remove Person");
            System.out.println("(G) Get Info on Person");
            System.out.println("(P) Print Table");
            System.out.println("(S) Save to File");
            System.out.println("(Q) Quit");
            System.out.println("Enter choice: ");
            menu = sc.next();
            switch (menu.toUpperCase()) {
                case "I":
                    System.out.println("Enter file name: ");
                    filename = sc.next();
                    if(!filename.toLowerCase().endsWith(".csv")) {
                        System.out.println("Invalid file name. Try again");
                        continue;
                    }
                    try {
                        manager.buildFromFile(filename);
                        System.out.println("File imported successfully");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    }
                    break;
            }
        }
    }
}
