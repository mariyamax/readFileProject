# Чтение файла из архива и группировка данных

Требования
Проект собирался с java 11 и запускается в случае, если она стоит JAVA_PATH
Проверить через java -version в консоли
Должен быть доступ в интернет для скачивания архива
Убедиться, что есть права на запись в файле вывода

Запуск
java -jar -Xmx1G app.jar _файл вывода_
Если файл вывода не задан вывод осуществиться в текущую директорию (если есть права)
в file.txt
Для проверки ситуации с неправильным выводом можно задать неккоректное имя 
несуществующего файла (например латиницей)
