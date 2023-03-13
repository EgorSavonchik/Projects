package webapp.code.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapp.code.spring.Model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>
{

}
