# Задача Магазин
Нужно написать программу-магазин, в которой пользователи заказывают товары. Вам предоставляется свобода в продумывании функциональности вашей программы, как и в проектировании её структуры. В процессе реализации вы должны применить принцип избегания магических чисел, DRY и как минимум 4 из 5 принципов SOLID, причём явно комментариями в коде или при отправке решения указать по одному примеру применения каждого принципа в вашем решении со ссылками на конкретные места кода на гитхабе.

## Описание
В данной программе я использовал принципы SOLID, а также использовал правила чистого кода Magic и DRY

Возможностей программы:
* Сделать заказ в онлайн магазине или же в офлайн
* Офлайн магазин:
  - Посмотреть все товары
  - Посмотреть товары по типу
  - Посмотреть товары одного типа
  - Получить информацию о товаре по имени
  - Сделать заказ и узнать его стоимость, содержимое и дату оформления
  - Узнать информацию о заказах
* Онлайн магазин:
  - Все функции обычного магазина
  - Создать аккаунт
  - Сделать доставку по адресу

  
### Magic:
* https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/ConsoleApp.java#L45-46  - Перед каждой конструкцие switch() вызывается метод, который принимает в аргумент число предполагаемых case

### DRY:
* https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/ConsoleApp.java#L25-L36 - Перед каждой конструкцие switch() вызывается метод, который принимает в аргумент число предполагаемых case  
* https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/ConsoleApp.java#L133-L153  - функция сбора заказа одинакова для обоих магазинов, поэтому, чтобы не повторять код каждый раз, сделан отдельный метод
* https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/ConsoleApp.java#L155-L175  - функция показа продуктов также эдентична для обоих магазинов

### SOLID:
* Single-responsibility principle - каждый класс отвечает только за то, за что должен отвечать, поэтому Store и OnlineStore разъединены, так как в онлайн магазине вы можете заказать доставку, а в обычном нет https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/store/OnlineStore.java#L43-L65
* Open-closed principle  - классы Store и OnlineStore сделаны по этому принципу, а строки https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/ConsoleApp.java#L133-L173  это подтверждают
* Liskov substitution principle - классы Store и OnlineStore сделаны по этому принципу, объекты OnlineStore полностью использует методы Store https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/store/Store.java
* Interface segregation principle - классы User и Customer разделены не случайно, они хоть и несут за собой некий общий смысл, однако одно пользователь интернет магазина с паролем и адресом, другое же обычный покупатель с именем https://github.com/Beruf20yo/ShopSolid/blob/main/src/main/java/ru/example/customer/User.java
