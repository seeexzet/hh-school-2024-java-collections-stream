package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    Map<Integer, String> areaIdToNameMap = areas.stream()
            .collect(Collectors.toMap(Area::getId, Area::getName)); // быстрый доступ к региону по id

    return persons.stream()
            .flatMap(person -> { // обработка списка регионов
              String personName = person.firstName();
              Set<Integer> areaIds = personAreaIds.get(person.id());
              return areaIds == null ?
                      Stream.empty() :  // используем поток
                      areaIds.stream()
                              .map(areaId -> areaIdToNameMap.get(areaId))
                              .filter(areaName -> areaName != null)
                              .map(areaName -> personName + " - " + areaName);
            })
            .collect(Collectors.toSet());  // переводим поток эл-тов в множ-во
  }
}