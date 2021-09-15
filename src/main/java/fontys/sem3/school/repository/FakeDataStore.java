package fontys.sem3.school.repository;

import fontys.sem3.school.model.Country;
import fontys.sem3.school.model.Student;

import java.util.ArrayList;
import java.util.List;

public class FakeDataStore {

    private final List<Country> countryList = new ArrayList<>();
    private final List<Student> studentList = new ArrayList<>();

    public FakeDataStore() {
        // work this out better, add few more countries and students
        Country netherlands = new Country("NL", "The Netherlands");
        Country bulgaria = new Country("BG", "Bulgaria");
        Country china = new Country("CHN", "China");
        countryList.add(netherlands);
        countryList.add(bulgaria);
        countryList.add(china);

        studentList.add(new Student(1, "Joe Smith",netherlands));
        studentList.add(new Student(2, "Ann Johnsson", bulgaria));
        studentList.add(new Student(3, "Ann Johnsson", bulgaria));
        studentList.add(new Student(4, "Miranda Winslet", china));
    }

    public List<Student> getStudents() {
        return studentList;
    }

    public List<Student> getStudents(Country country) {
        List<Student> filetered = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getCountry().equals(country)) {
                filetered.add(student);
            }
        }
        return filetered;
    }

    public Student getStudent(int nr) {
        for (Student student : studentList) {
            if (student.getStudentNumber() == nr)
                return student;
        }
        return null;
    }

    public boolean deleteStudent(int stNr) {
        Student student = getStudent(stNr);
        if (student == null){
            return false;
        }

        return studentList.remove(student);
    }


    public boolean add(Student student) {
        if (this.getStudent(student.getStudentNumber()) != null){
               return false;
        }
        studentList.add(student);
        return true;
    }

    public boolean update(Student student) {
        Student old = this.getStudent(student.getStudentNumber());
        if (old == null) {
            return false;
        }
        old.setName(student.getName());
        old.setCountry(student.getCountry());
        return true;
    }

    public Country getCountry(String countryCode) {
        for (Country country : countryList) {
            if (country.getCode().equals(countryCode)) {
                return country;
            }
        }
        return null;
    }

    public List<Country> getCountries() {
        return countryList;
    }

    public boolean deleteCountry(String id) {
        Country country = getCountry(id);
        if (country == null){
            return false;
        }

        return countryList.remove(country);
    }

    public boolean createCountry(Country country){
        if(this.getCountry(country.getCode()) != null){
            return false;
        }
        countryList.add(country);
        return true;
    }

    public boolean updateCountry(Country country){
        Country old = this.getCountry(country.getCode());
        if (old == null) {
            return false;
        }
        old.setName(country.getName());
        return true;
    }

}
