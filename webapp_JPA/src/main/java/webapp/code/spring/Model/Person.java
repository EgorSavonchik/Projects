package webapp.code.spring.Model;



import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "person")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Invalid name")
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Min(value = 0, message = "Age cant be < 0")
    @NotNull(message = "Incorrect age")
    private int age;

    @Column(name = "email")
    @Email
    private String email;

    @NotNull(message = "Age of birth is empty")
    @Min(value = 1850, message = "Incorrect year of birth")
    @Column(name = "yearofbirth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books = new ArrayList<>();

    public Person(int id, String name, int yearOfBirth, String email, int age)
    {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.age = age;
    }

    public Person()
    {

    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYearOfBirth()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
