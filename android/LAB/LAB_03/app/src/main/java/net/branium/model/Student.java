package net.branium.model;

import java.util.Objects;

public class Student {
    private String id;
    private String fullName;
    private double math;
    private double physic;
    private double chemistry;

    public Student() {
    }

    public Student(String id, String fullName, double math, double physic, double chemistry) {
        this.id = id;
        this.fullName = fullName;
        this.math = math;
        this.physic = physic;
        this.chemistry = chemistry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getPhysic() {
        return physic;
    }

    public void setPhysic(double physic) {
        this.physic = physic;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", math=" + math +
                ", physic=" + physic +
                ", chemistry=" + chemistry +
                '}';
    }

    public double getTotalMark() {
        return math + physic + chemistry;
    }

    public double getAverageMark() {
        double totalMark = getTotalMark();
        return totalMark / 3;
    }
}
