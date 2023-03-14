package webapp.code.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.code.spring.Model.Book;
import webapp.code.spring.Model.Person;
import webapp.code.spring.Repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Service
public class BookService
{
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public Book findById(int id)
    {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void add(Book book)
    {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book)
    {
        book.setBook_id(id);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteById(int id)
    {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setOwner(int id, Person person)
    {
        Book book = findById(id);

        book.setOwner(person);
    }

    public List<Book> findBooksByNameIsStartingWith(String str)
    {
        if(str.isBlank()) //don't output empty query
        {
            return null;
        }

        return bookRepository.findBooksByNameIsStartingWith(str);
    }

    public List<Book> findAllBySortYear()
    {
        return bookRepository.findAll(Sort.by("year"));
    }

    public List<Book> findWithPagination(int page, int books_per_page)
    {
        return new ArrayList<Book>(bookRepository.findAll(PageRequest.of(page, books_per_page))
                .getContent());
    }
}
