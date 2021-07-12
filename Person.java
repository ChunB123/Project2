package Assignment.Project2;

public class Person {
    private String name;
    private String Gender;
    private String state;

    public Person(){}

    public Person(String name, String gender, String state) {
        this.name = name;
        Gender = gender;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
