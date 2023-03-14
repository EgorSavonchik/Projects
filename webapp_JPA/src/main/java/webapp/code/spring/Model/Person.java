package webapp.code.spring.Model;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "person")
@Validated
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
    @NotNull(message = "Age must not be empty")
    @Min(value = 0, message = "Age cant be < 0")
    private Integer age;

    @Column(name = "email")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Incorrect email")
    private String email;

    @Column(name = "yearofbirth")
    @NotNull(message = "Age of birth must not be empty")
    @Min(value = 1850, message = "Incorrect year of birth")
    private Integer yearOfBirth;

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

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
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

    public Integer getYearOfBirth()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth)
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
