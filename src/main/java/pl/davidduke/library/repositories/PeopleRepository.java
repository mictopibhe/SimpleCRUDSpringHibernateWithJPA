package pl.davidduke.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.davidduke.library.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
