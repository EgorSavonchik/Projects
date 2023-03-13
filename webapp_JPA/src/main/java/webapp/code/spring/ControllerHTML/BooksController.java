package webapp.code.spring.ControllerHTML;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.code.spring.Model.Book;
import webapp.code.spring.Model.Person;
import webapp.code.spring.Services.BookService;
import webapp.code.spring.Services.PeopleService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController
{
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BookService bookService, PeopleService peopleService)
    {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String show(Model model, @RequestParam(required = false) boolean sort_by_year
            , @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer books_per_page)
    {
        if(page != null && books_per_page != null && sort_by_year)
        {
            List<Book> books = bookService.findWithPagination(page, books_per_page);
            books.sort(Comparator.comparingInt(Book::getYear));

            model.addAttribute("books", books);
        }
        else if(sort_by_year)
        {
            model.addAttribute("books", bookService.findAllBySortYear());
        }
        else if(page != null && books_per_page != null)
        {
            model.addAttribute("books", bookService.findWithPagination(page, books_per_page));
        }
        else
        {
            model.addAttribute("books", bookService.findAll());
        }

        return "books/show";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person)
    {
        model.addAttribute("book", bookService.findById(id));

        if(bookService.findById(id).getOwner() == null)
        {
            model.addAttribute("people", peopleService.findAll());
        }
        else
        {
            model.addAttribute("owner", bookService.findById(id).getOwner());
        }

        return "books/index";
    }

    @PatchMapping("/{id}/correct")
    public String setOwner(@ModelAttribute("person") Person person, @PathVariable("id") int book_id)
    {
        bookService.setOwner(book_id, person);

        return "redirect:/books";
    }

    @PostMapping("/{id}/correct")
    public String releaseBook(@PathVariable("id") int book_id)
    {
        bookService.setOwner(book_id, null);

        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book person)
    {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Book book, BindingResult errors)
    {
        if(errors.hasErrors())
        {
            return "/books/new";
        }

        bookService.add(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("book", bookService.findById(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String correct(@ModelAttribute("book") @Valid Book book, BindingResult errors,
                          @PathVariable("id") int id)
    {
        if(errors.hasErrors())
        {
            return "books/edit";
        }

        bookService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        bookService.deleteById(id);

        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String req, Model model)
    {
        if(req != null)
        {
            model.addAttribute("result", bookService.findBooksByNameIsStartingWith(req));
            System.out.println(bookService.findBooksByNameIsStartingWith(req));
        }

        System.out.println(req);

        return "books/search";
    }
}
