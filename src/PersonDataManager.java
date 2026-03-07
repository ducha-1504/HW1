import java.io.*;
import java.util.Scanner;//Using Scanner to read Files

public class PersonDataManager implements Cloneable {
    private Person[] people;
    private int size;
    public PersonDataManager() {
        people = new Person[10];
        size = 0;
    }
    public int getCapacity(){
        return people.length;
    }

    public PersonDataManager(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        size = 0;
        people = new Person[initialCapacity];
    }

    public void ensureCapacity(int minCapacity) {
        Person[] temp_people;
        if (people.length < minCapacity) {
            temp_people = new Person[minCapacity];
            System.arraycopy(people, 0, temp_people, 0, people.length);
            people = temp_people;
        }
    }

    public int getSize() {return size;}

    public void buildFromFile(String location) {
        try {
            //Create a File object with the file path
            File newfile = new File(location);

            //Create a scanner object to read from file
            Scanner sc = new Scanner(newfile);

            //loop through each line in the file
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] data = line.split(",");
                int new_age = Integer.parseInt(data[2]);
                double new_height = Double.parseDouble(data[3]);
                double new_weight = Double.parseDouble(data[4]);
                Person p = new Person(data[0], data[1], new_age, new_height, new_weight);
                people[size] = p;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + location);
        }
    }
    //addPerson method
    //If the person already appears, by checking if there is the same name, weight,..., throws PersonAlreadyExistsException
    public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
        for (int i = 0; i < size; i++){
            if (people[i].getName().equalsIgnoreCase(newPerson.getName()) && people[i].getAge() == newPerson.getAge()
            && people[i].getWeight() == newPerson.getWeight() && people[i].getHeight() == newPerson.getHeight()
            && people[i].getGender().equalsIgnoreCase(newPerson.getGender())) {
                throw new PersonAlreadyExistsException("Person Already Exists");
            }
        }
        if(people.length == size) {
            ensureCapacity(size * 2+1);
        }
        people[size] = newPerson;
        size++;
    }
    //getPerson method.
    //if person does not exist, throw PersonDoesNotExistException
    public Person getPerson(String name) throws PersonDoesNotExistException{
        for (int i = 0; i < size; i++) {
            if (name.equalsIgnoreCase(people[i].getName()))
                return people[i];
        }
        throw new PersonDoesNotExistException("Person Does Not Exist");
    }
    public boolean removePerson(String name) throws PersonDoesNotExistException {
        int index = 0;
        while ((index < size) && !(people[index].getName().equalsIgnoreCase(name)))
            index++;
        if (index == size) {
                throw new PersonDoesNotExistException("Person Does Not Exist");
        } else {
                size--;
                people[index] = people[size];
                return true;
        }
    }
    public void printTable(){
            for (int i = 0; i < size; i++) {
                people[i].toString();
        }
    }
    public void savetoFile(String filename){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename));) {
            for (int i = 0; i < size; i++) {
                bw.write(people[i].getName() + "," +
                        people[i].getGender() + "," +
                        people[i].getAge() + "," +
                        people[i].getHeight() + "," +
                        people[i].getWeight());
                bw.newLine();
            }
            System.out.println("Saved successfully to " + filename);
        } catch(IOException e) {
            System.out.println("Error Saving File");
        }
    }
}