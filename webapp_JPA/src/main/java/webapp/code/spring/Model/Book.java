package webapp.code.spring.Model;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Component
@Entity
@Table(name = "book")
public class Book
{
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;

    @NotEmpty
    @Size(min = 1, max = 50, message = "Incorrect name size")
    @Column(name = "name")
    private String name;

    @NotEmpty
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "Invalid author name")
    @Column(name = "author")
    private String author;

    @NotNull
    @Min(value = 0, message = "Incorrect year")
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "issieat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issieAt;

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        if(owner == null)
        {
            this.issieAt = null;
        }
        else
        {
            this.issieAt = new Date();
        }

        this.owner = owner;
    }

    public Book(int book_id, String name, String author, int year, Person owner)
    {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.owner = owner;
    }

    public Book()
    {

    }

    public int getBook_id()
    {
        return book_id;
    }

    public void setBook_id(int id)
    {
        this.book_id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public boolean hasOwner()
    {
        return owner == null;
    }

    public Date getIssieAt() {
        return issieAt;
    }

    public void setIssieAt(Date issieAt) {
        this.issieAt = issieAt;
    }

    public boolean isOverdue()
    {
        if(issieAt == null)
        {
            return false;
        }

        return Days.daysBetween(new DateTime(issieAt), new DateTime(new Date())).getDays() >= 10;
    }

}
