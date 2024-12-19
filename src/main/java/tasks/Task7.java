package tasks;

import common.Company;
import common.Vacancy;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    return companies.stream() // вывод сразу, без лишнего присваивания
            .flatMap(company -> company.getVacancies().stream())
            .map(Vacancy::getTitle) // получение каждой из вакансий
            .collect(Collectors.toSet()); // преобразование в множество
  }
}