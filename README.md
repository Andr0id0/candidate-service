# Инструкция по запуску проекта в Docker

Для запуска проекта в Docker просто выполните следующие шаги:

1. **Клонируйте проект**

   ```bash
   git clone https://github.com/Andr0id0/candidate-service.git
   ```

2. **Запустите Docker Compose**

   В терминале перейдите в каталог проекта и выполните следующую команду:

   ```bash
   docker-compose up
   ```

   Эта команда загрузит все необходимые зависимости и запустит приложение, базу данных PostgreSQL и pgAdmin.

3. **Использование Endpoints**

   После успешного запуска проекта в Docker все Endpoints будут доступны. Для получения подробной информации об этих Endpoints перейдите по ссылке:

   [Swagger UI](http://localhost:8080/swagger-ui/index.html)

