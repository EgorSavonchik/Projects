package webapp.code.spring.ControllerHTML;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.code.spring.Model.Person;
import webapp.code.spring.Services.PeopleService;
import webapp.code.spring.Util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController
{
    private final PersonValidator personValidator;
    private final PeopleService peopleService;
    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService)
    {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String show(Model model)
    {
        model.addAttribute("people", peopleService.findAll());

        return "people/show";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("person", peopleService.findById(id));
        model.addAttribute("peopleBooks", peopleService.findById(id).getBooks());

        return "people/index";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person)
    {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult errors)
    {
        personValidator.validate(person, errors);

        if(errors.hasErrors())
        {
            return "/people/new";
        }

        peopleService.add(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id)
    {
        model.addAttribute("person", peopleService.findById(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String correct(@ModelAttribute("person") @Valid Person person, BindingResult errors,
                          @PathVariable("id") int id)
    {
        personValidator.validate(person, errors);

        if(errors.hasErrors())
        {
            return "people/edit";
        }

        peopleService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id)
    {
        peopleService.deleteById(id);

        return "redirect:/people";
    }
}