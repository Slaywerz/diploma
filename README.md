# Описание проекта
В данном проекте происходит тестирование комплексного сервиса, взаимодействующего с СУБД и API банка
## Что потребуется для запуска тестов:
* Установить актуальную версию [__*IntelliJ IDEA*__](https://www.jetbrains.com/ru-ru/idea/)
* Установить [__*git*__](https://git-scm.com/)
* Установить плагин __*Docker*__ для IntelliJ IDEA
* Установить [__*AdoptOpenJDK 11*__](https://adoptopenjdk.net/)
* Установить [__*Docker Desktop*__](https://www.docker.com/products/docker-desktop)
## Шаги для запуска тестов:
1. Запустить Docker Desktop
2. Скачать данный репозиторий командой: __*git clone*__
3. Запустить IntelliJ IDEA и открыть через неё репозиторий
4. Запустить файл __*docker-compose.yml*__ командой: __*"docker-compose up -d"*__
5. Запустить приложение командой 
#### для DB MySQL
__*java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar*__
#### для DB PostgreSQL
__*java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar*__
## Отчетная документация
1. [План автоматизации тестирования](./documentation/Plan.md)