package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
  Еще один вариант задачи обогащения
  На вход имеем коллекцию персон
  Сервис умеет по personId искать их резюме (у каждой персоны может быть несколько резюме)
  На выходе хотим получить объекты с персоной и ее списком резюме
 */
public class Task8 {
  private final PersonService personService;

  public Task8(PersonService personService) {
    this.personService = personService;
  }

  public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
    Set<Integer> personIds = persons.stream()
            .map(Person::id) // Получение id персон
            .collect(Collectors.toSet());  // для уникальности значений и соответствия findResumes

    Set<Resume> allResumes = personService.findResumes(personIds);  // Резюме персоны

    Set<PersonWithResumes> personWithResumesSet = persons.stream() // преобразование в набор и фильтрация
            .map(person -> {
              Set<Resume> resumesForPerson = allResumes.stream()
                      .filter(resume -> resume.personId().equals(person.id()))
                      .collect(Collectors.toSet());

              return new PersonWithResumes(person, resumesForPerson);
            })
            .collect(Collectors.toSet());  // преобразование потока в Set

    return personWithResumesSet;
  }
}