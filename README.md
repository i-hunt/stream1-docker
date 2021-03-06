## Стрим "Docker для кандидата"

Структура папок:
* AccountService - микросервис для создания аккаунтов и балансов
* AnalyticsService - микросервис для подсчета суммы всех балансов
* mysql - микросервис БД mysql
* postgres - микросервис БД postgres
* ppt - ![Презентация](ppt/ppt.pdf "Презентация")

* tests/postman/ - коллекция авто-тестов REST API

## Архитектурная диаграмма
![Микросервисы](ppt/architecture.png?raw=true)


## Инструкция по сборке
1. Выкачайте проект
```
git clone git@github.com:i-hunt/stream1-docker.git

cd stream1-docker
```

2. Установите openjdk (https://openjdk.java.net/install/)

3. Установите maven (https://maven.apache.org/install.html)

4. Установите docker (https://docs.docker.com/get-docker/)

5. Установите docker-compose (https://docs.docker.com/compose/install/)

6. Запустите компиляцию исходников и сборку докер образов

```
./rebuild.sh
```


7. Установите Postman (https://www.postman.com/downloads/)

8. Загрузите в Postman коллекцию автотестов tests/postman/Docker Stream.postman_collection.json

9. Прогоните  автотесты (там где-то кнопка Run должна быть в Postman'е)
