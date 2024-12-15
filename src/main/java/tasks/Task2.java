package tasks;

import common.Person;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 2
На вход принимаются две коллекции объектов Person и величина limit
Необходимо объеденить обе коллекции
отсортировать персоны по дате создания и выдать первые limit штук.
 */
public class Task2 {

  public static List<Person> combineAndSortWithLimit(Collection<Person> persons1,
                                                     Collection<Person> persons2,
                                                     int limit) {

    // Объединяем две коллекции в одну
    List<Person> combinedPersons = new ArrayList<>(persons1);
    combinedPersons.addAll(persons2);

    return combinedPersons.stream()
            .sorted(Comparator.comparing(Person::createdAt)) // Сортитровка
            .limit(limit) // выдача первых limit элементов
            .collect(Collectors.toList());
  }
}
