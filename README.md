Приложение для получения средней температуры в Академгородке за период дней. <br />
База данных - Postgres в Docker. Для подключения базы нужно прописать в корне проекта консольную команду docker-compose up <br />
Серверная часть - Spring Boot + Spring Data Jpa. Для запуска приложения нужно прописать в корне проекта консольную команду mvn spring-boot:run <br />
Способ передачи информации - json по http, http порт - 8080. Документация к rest api - в swagger - http://localhost:8080/swagger-ui/ <br />
Приложение обращается к ресурсу http://pogoda.atpm-air.ru/data.aspx для получения исторических данных по температуре. Наблюдения по температуре ведутся с 11 марта 2016 года. <br />
Тесты написаны для веб-слоя и слоя-сервиса.
