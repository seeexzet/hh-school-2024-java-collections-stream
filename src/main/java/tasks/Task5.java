package tasks;

import common.ApiPersonDto;
import common.Person;
import common.PersonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Задача 5
Расширим предыдущую задачу.
Есть список персон, и словарь сопоставляющий id каждой персоны и id региона
Необходимо выдать список персон ApiPersonDto, с правильно проставленными areaId
Конвертер одной персоны дополнен!
 */
public class Task5 {

  private final PersonConverter personConverter;

  public Task5(PersonConverter personConverter) {
    this.personConverter = personConverter;
  }

  public List<ApiPersonDto> convert(List<Person> persons, Map<Integer, Integer> personAreaIds) {
    // Преобразуем список Person в список ApiPersonDto с указанием areaId
    List<ApiPersonDto> apiPersonDtos = persons.stream()
            .map(person -> {
              ApiPersonDto dto = personConverter.convert(person); // преобразование
              Integer areaId = personAreaIds.get(person.id()); // areaId берётся из person, по id
              dto.setAreaId(areaId); // присваваем текущему экземпляру id региона
              return dto;
            })
            .collect(Collectors.toList());

    return apiPersonDtos;
  }
}
