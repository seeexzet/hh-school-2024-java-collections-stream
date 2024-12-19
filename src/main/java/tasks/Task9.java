package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Далее вы увидите код, который специально написан максимально плохо.
Постарайтесь без ругани привести его в надлежащий вид
P.S. Код в целом рабочий (не везде), комментарии оставлены чтобы вам проще понять чего же хотел автор
P.P.S Здесь ваши правки необходимо прокомментировать (можно в коде, можно в PR на Github)
 */
public class Task9 {

  private long count;

  // Костыль, эластик всегда выдает в топе "фальшивую персону".
  // Конвертируем начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream() // стрим позволит не менять исходный список persons
            .skip(1) //  пропуск фальшивой персоны
            .map(Person::firstName)
            .collect(Collectors.toList());
  }

  // Зачем-то нужны различные имена этих же персон (без учета фальшивой разумеется)
  public Set<String> getDifferentNames(List<Person> persons) {
    return getNames(persons).stream()
            .collect(Collectors.toCollection(HashSet::new)); // позволяет сразу гарантировать уникальность имен
  }

  // Тут фронтовая логика, делаем за них работу - склеиваем ФИО
  public String convertPersonToString(Person person) {
    return Stream.of(person.secondName(), person.firstName(), person.middleName()) // поток из строк
            .filter(part -> part != null) // фильтр от null
            .collect(Collectors.joining(" ")); // склеиваем оставшиеся части через пробел
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = persons.stream() // построение словаря через стрим
            .collect(Collectors.toMap(
                    Person::id,
                    this::convertPersonToString
            ));

    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> setOfPersons1 = new HashSet<>(persons1); // HashSet даст быстрее проверить наличие элементов

    for (Person person : persons2) {
      if (setOfPersons1.contains(person)) {
        return true; // возвращение true при первой находке
      }
    }
    return false; // не нашли совпадений
  }

  // Посчитать число четных чисел
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0) // Фильтрация по чётным
            .count(); // Подсчёт количества и возврат
  }

  // Загадка - объясните почему assert тут всегда верен
  // Пояснение в чем соль - мы перетасовали числа, обернули в HashSet, а toString() у него вернул их в сортированном порядке
  void listVsSet() {
    List<Integer> integers = IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
    List<Integer> snapshot = new ArrayList<>(integers);
    Collections.shuffle(integers);
    Set<Integer> set = new HashSet<>(integers);
    assert snapshot.toString().equals(set.toString()); // предположу, что .toString для HashSet выводит элементы
                                                      // в том порядке, в каком они туда попали
  }
}
