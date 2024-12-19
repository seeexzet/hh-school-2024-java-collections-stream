package tasks;

import common.Person;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 {

  public static List<Person> sort(Collection<Person> persons) {
    return persons.stream() // вывод сразу, без лишнего присваивания
            .sorted(Comparator
                    .comparing(Person::secondName) // сначала по фамилии
                    .thenComparing(Person::firstName) // если равны - по имени
                    .thenComparing(Person::createdAt)) // если равны - по дате создания
            .collect(Collectors.toList());
  }
}