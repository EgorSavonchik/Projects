package webapp.code.spring.Services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapp.code.spring.Model.Person;
import webapp.code.spring.Repositories.PeopleRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class PeopleService
{
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository)
    {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll()
    {
        return peopleRepository.findAll();
    }

    @Transactional
    public Person findById(int id)
    {
        Person person = peopleRepository.findById(id).orElse(null);
        Hibernate.initialize(person.getBooks());

        return person;
    }

    @Transactional
    public void add(Person person)
    {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person)
    {
        person.setId(id);

        peopleRepository.save(person);
    }

    @Transactional
    public void deleteById(int id)
    {
        peopleRepository.deleteById(id);
    }
}
