package Assignment.Project2;

import java.util.Objects;

public class DataPoint {
    private String state;
    private String gender;
    private Integer year;
    private String name;
    private Integer occurrence;

    public DataPoint(String state, String gender, Integer year, String name, Integer occurrence) {
        this.state = state;
        this.gender = gender;
        this.year = year;
        this.name = name;
        this.occurrence = occurrence;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Integer occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "state='" + state + '\'' +
                ", gender='" + gender + '\'' +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", occurrence=" + occurrence +
                '}';
    }
}
