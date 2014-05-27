package ru.maskan.gwt.server.domain;

import org.joda.time.DateTime;
import org.joda.time.Period;
import ru.maskan.gwt.client.Employee;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by akonshina on 25.05.14.
 */
@Entity
@Table(name = "employee")
public class HbmEmployee {
    private static final long serialVersionUID = -607874117917352477L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String secondname;

    @Column(nullable = false)
    private Date age;

    @Column
    private String experience;

    @Column
    private String description;

    public HbmEmployee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee toGwtObject() {
        Employee e = new Employee();

        e.setId(String.valueOf(id));
        e.setFirstname(firstname);
        e.setSecondname(secondname);
        e.setLastname(lastname);
        e.setExperience(experience);
        e.setDescription(description);

        DateTime start = new DateTime(age);
        DateTime end = new DateTime(new Date());

        e.setBirthday(start.toString("dd.MM.yyyy"));

        Period p = new Period(start, end);
        e.setAge(String.valueOf(p.getYears()));

        return e;
    }

}
