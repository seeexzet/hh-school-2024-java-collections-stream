package tasks;

import common.Person;
import common.PersonService;

import java.util.*;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимптотику работы
 */
public class Task1 {

  private final PersonService personService;

  public Task1(PersonService personService) {
    this.personService = personService;
  }

  public List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = personService.findPersons(personIds);

    // Создаем мапу для удобного маппинга ID на Person
    Map<Integer, Person> personMap = new HashMap<>();
    for (Person person : persons) {
      personMap.put(person.id(), person); // В лекциях говорилось, что это плохой вариант и нужна функция,
                                          // но указания менять Person.java не было, поэтому не стал
    }

    List<Person> ordPersons = new ArrayList<>();
    for (Integer id : personIds) {
      ordPersons.add(personMap.get(id));
    }

    return ordPersons;
  }
}

// реализация за O(n)