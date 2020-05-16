# autotests_example

Тест на проверку поиска товара в яндекс.маркете
- Зайти на яндекс.маркет
- Вбить в поиск товар “item”
- Открыть первый товар, появившийся в списке
- Проверить, что в карточке товара присутствует текст “item”

Примечание: item - передаем значение из терминала, т.е. тест должен работать с любым товаром

Запуск через gradle YandexMarketTests -DyandexMarketItem="ssd" , где "ssd" это нужный item.


Тест на редактирование своего профиля в facebook/vk/ok (на выбор)
- Логинимся (передаем адрес, логин и пароль из командной строки)
- Заходим в профиль
- Редактируем какой-нибудь пункт, сохраняем
- Заходим в профиль - проверяем что сохранения применились

Запуск через gradle instagramTests -Dlogin="вашлогин" -Dpass="ваш пароль".
