import java.io.*;
import java.util.Scanner;//Using Scanner to read Files

public class PersonDataManager {
    //2 private data fields
    private Person[] people;
    private int size;

    //No-argument constructor
    public PersonDataManager() {
        people = new Person[10];
        size = 0;
    }

    //getCapacity method
    public int getCapacity() {
        return people.length;
    }

    public PersonDataManager(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        size = 0;
        people = new Person[initialCapacity];
    }

    //ensureCapacity of a bag
    public void ensureCapacity(int minCapacity) {
        Person[] temp_people;
        if (people.length < minCapacity) {
            temp_people = new Person[minCapacity];
            System.arraycopy(people, 0, temp_people, 0, people.length);
            people = temp_people;
        }
    }

    public int getSize() {
        return size;
    }

    //importing csv file then save it on people[] person
    public boolean buildFromFile(String location) {
        try {
            //Create a File object with the file path
            File newfile = new File(location);

            //Create a scanner object to read from file
            Scanner sc = new Scanner(newfile);
            //Skip reading the header of CSV
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            //loop through each line in the file
            while (sc.hasNextLine()) {
                if (size == people.length) {
                    ensureCapacity(size * 2 + 1);
                }
                String line = sc.nextLine();
                String[] data = line.split(",\\s*");

                if (data.length < 5) continue;
                String name = data[0].replace("\"", "");
                String gender = data[1].replace("\"", "");
                int new_age = Integer.parseInt(data[2]);
                double new_height = Double.parseDouble(data[3]);
                double new_weight = Double.parseDouble(data[4]);
                Person p = new Person(name, gender, new_age, new_height, new_weight);
                people[size] = p;
                size++;
            }
            sc.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + location);
            return false;
        }
    }

    //addPerson method
    //If the person already appears, by checking if there is the same name, weight,..., throws PersonAlreadyExistsException
    public boolean addPerson(Person newPerson) throws PersonAlreadyExistsException {
        for (int i = 0; i < size; i++) {
            if (people[i].getName().equalsIgnoreCase(newPerson.getName()) && people[i].getAge() == newPerson.getAge()
                    && people[i].getWeight() == newPerson.getWeight() && people[i].getHeight() == newPerson.getHeight()
                    && people[i].getGender().equalsIgnoreCase(newPerson.getGender())) {
                throw new PersonAlreadyExistsException("Person Already Exists");
            }
        }
        //if capacity is small, resizing the bag
        if (people.length == size) {
            ensureCapacity(size * 2 + 1);
        }
        people[size] = newPerson;
        size++;
        return true;
    }

    //getPerson method.
    //if person does not exist, throw PersonDoesNotExistException
    public String getPerson(String name) throws PersonDoesNotExistException {
        for (int i = 0; i < size; i++)
            if (name.equalsIgnoreCase(people[i].getName()))
                return people[i].toString();
        throw new PersonDoesNotExistException("Person Does Not Exist");
    }

    //Remove person with given name. If name is removed, the last person on the bag is moved to the empty location
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
    //Print Table of Data
    public String printTable() {
        String s = new String();
        for (int i = 0; i < size; i++) {
            s += people[i].getName() + " " + people[i].getGender() + " " +
                    people[i].getAge() + " " + people[i].getHeight() + " " +
                    people[i].getWeight() + "\n";
        }
        return s;
    }
    //Save to File with Header.
    public void savetoFile(String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("Name" + "," + "Gender" + "," + "Age" + "," + "Height" + "," + "Weight" + "\n");
            for (int i = 0; i < size; i++) {
                bw.write(people[i].getName() + "," +
                        people[i].getGender() + "," +
                        people[i].getAge() + "," +
                        people[i].getHeight() + "," +
                        people[i].getWeight());
                bw.newLine();
            }

        }
    }
}