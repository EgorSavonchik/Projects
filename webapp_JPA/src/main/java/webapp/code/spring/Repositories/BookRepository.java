package webapp.code.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.code.spring.Model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
    List<Book> findBooksByNameIsStartingWith(String str);
}
