## Стрим "Docker для кандидата"

Структура папок:
* AccountService - микросервис для создания аккаунтов и балансов
* AnalyticsService - микросервис для подсчета суммы всех балансов
* mysql - микросервис БД mysql
* postgres - микросервис БД postgres
* ppt - ![Презентация](ppt/ppt.pdf "Презентация")

* tests/postman/ - коллекция авто-тестов REST API
* Запись стрима:  ![Видео](https://www.youtube.com/watch?v=_kV799129u8&list=PLXuDKLBD7gUzdBEf6EOqPR7y-glNYjJK7 "Docker для кандидата")

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

4. Запустите компиляцию исходников и сборку докер образов

```
./rebuild.sh
```


5. Установите Postman (https://www.postman.com/downloads/)

6. Загрузите в Postman коллекцию автотестов tests/postman/Docker Stream.postman_collection.json

7. Прогоните  автотесты (там где-то кнопка Run должна быть в Postman'е)
