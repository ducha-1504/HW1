public class Person {
    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;

    //No-argument Constructor
    public Person(){
        this.name = "";
        this.gender = "";
        this.age = 0;
        this.height = 0;
        this.weight = 0;
    }

    //Parameterized Constructor
    public Person(String name, String gender, int age, double height, double weight){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    //Getter method
    public String getName() {return name;}
    public String getGender() {return gender;}
    public int getAge() {return age;}
    public double getHeight() {return height;}
    public double getWeight() {return weight;}

    //Setter method
    public void setName(String name) {this.name = name;}
    public void setGender(String gender) {this.gender = gender;}
    public void setAge(int age) {this.age = age;}
    public void setHeight(double height) {this.height = height;}
    public void setWeight(double weight) {this.weight = weight;}

    //toString formatted method
    public String toString(){
        return this.name + ", " + this.gender + ", " + this.age + ", " + this.height + ", " + this.weight + "\n";
    }
}