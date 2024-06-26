# auto-logging-spring-boot-starter
Позволяет логгировать все входящие и исходящие HTTP запросы в приложении.
Логгирование включает в себя:
 - входящий запрос: [метод запроса][URL][список заголовков][список параметров];
 - исходящий запрос: [код ответа][список заголовков][время обработки запроса];

## Стек технологий
- ![Java 17](https://img.shields.io/badge/Java-17-blue)
- ![Spring 3.2.6](https://img.shields.io/badge/Spring%20Boot%203.2.6-white?style=flat&logo=Spring)
- ![Maven 4.0.0](https://img.shields.io/badge/Maven%204.0.0-white?style=flat&logo=Apache%20Maven&logoColor=red
  )
- ![Lombok 1.18.32](https://img.shields.io/badge/Lombok%201.18.32-white?style=flat
  )

## Окружение
- Java 17
- Apache Maven 4.0.0

## Настройка уровня логгирования
Необходимо прописать настройки в файл application.properties: <br>
logging.enabled=true/false - включение/выключение логгирования <br>
logging.log-level=info/error/warm - установка уровня логгирования <br>

## Настройка формата вывода логов
Необходимо прописать настройки в файл application.properties: <br>
logging.request-method=true/false - включение/выключение логгирования метода запроса <br>
logging.request-url=true/false - включение/выключение логгирования URL запроса <br>
logging.request-headers=true/false - включение/выключение логгирования списка заголовков запроса <br>
logging.request-param=true/false - включение/выключение логгирования параметров запроса <br>

logging.response-status=true/false - включение/выключение логгирования кода ответа <br>
logging.response-headers=true/false - включение/выключение логгирования списка заголовков ответа <br>
logging.response-processing-time=true/false - включение/выключение логгирования времени обработки запроса <br>

## Сборка стартера
Для сборки проекта и добавления в локальный репозиторий выполнить в терминале:
```
  mvn clean install
```

## Подключение к проекту
Для подключения к проекту добавьте зависимость:

```
	<dependency>
	    <groupId>t1.home</groupId>
	    <artifactId>auto-logging-spring-boot-starter</artifactId>
	    <version>0.0.1-SNAPSHOT</version>
	</dependency>
```

## Контакты

email: gretskih@mail.ru <br/>
telegram: @Anatolij_gr