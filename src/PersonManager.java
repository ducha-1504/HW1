import java.io.*;
import java.util.Scanner;
public class PersonManager {
    public static void main(String[] args)  {
        PersonDataManager manager = new PersonDataManager();
        Scanner sc = new Scanner(System.in);
        String menu = "";
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
                    while (true) {
                        System.out.println("Enter file name: ");
                        String filename = sc.next();
                        if (!filename.toLowerCase().endsWith(".csv")) {
                            System.out.println("Invalid file name. Try again with file name end with csv: ");
                            continue;
                        }
                        if(manager.buildFromFile(filename)) {
                            System.out.println("File has been built");
                            break;
                        }
                        else {
                            System.out.println("File does not exist. Try again");
                        }
                    }
                break;
                case "A":
                        System.out.println("Add Person in order: Name, Gender (M/F), Age, Height(inch), Weight");
                        Person p = new Person();
                        p.setName(sc.next());
                        p.setGender(sc.next());

                        //Make sure Age is int type and not crash here.
                        if (sc.hasNextInt()) {
                        p.setAge(sc.nextInt());
                        } else {
                        System.out.println("Invalid age. Must be a number.");
                        sc.next(); // clear bad input
                        break;
                        }

                        //Make sure height is double type and not crash here.
                        if (sc.hasNextDouble()) {
                        p.setHeight(sc.nextDouble());
                        } else {
                        System.out.println("Invalid height.");
                        sc.next();
                        break;
                        }

                        //Make sure Weight is double type and not crash here.
                        if (sc.hasNextDouble()) {
                        p.setWeight(sc.nextDouble());
                        } else {
                        System.out.println("Invalid weight.");
                        sc.next();
                        break;
                        }
                        try {
                            manager.addPerson(p);
                            System.out.println("Person has been added");
                        } catch (PersonAlreadyExistsException e) {
                            System.out.println("Person already exists");
                        }
                        break;
                case "R":
                    System.out.println("Please type person you want to remove: ");
                    String remove_name = sc.next();
                    char[] charArray = remove_name.toCharArray();
                    if(!Character.isUpperCase(charArray[0])) {
                        System.out.println("Invalid person name. Try again with uppercase first letter");
                    }
                    try{
                        manager.removePerson(remove_name);
                        System.out.println("Person has been removed");
                        break;
                    } catch (PersonDoesNotExistException e) {
                        System.out.println("Person does not exist");
                    }
                    break;
                case "G":
                    System.out.println("Please type person you want to get info: ");
                    String info = sc.next();
                    try{
                        System.out.println(manager.getPerson(info));
                    } catch (PersonDoesNotExistException e) {
                        System.out.println("Person does not exist");
                    }
                    break;
                case "P":
                    System.out.println(manager.printTable());
                    break;
                case "S":
                    System.out.println("Enter the name of the file you want to save (end with csv): ");
                    String save_name = sc.next();
                    if (!save_name.toLowerCase().endsWith(".csv")) {
                        System.out.println("Invalid file name. Try again");
                        continue;
                    }
                    try{
                        manager.savetoFile(save_name);
                        System.out.println("Saved successfully to " + save_name);
                    } catch(IOException e) {
                        System.out.println("File could not be saved");
                    }
                    break;
            }
        }
    }
}

