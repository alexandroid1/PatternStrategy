# PatternStrategy
Pattern Strategy implementation

<h2>Паттерн “Стратегия” — или опять про уток</h2>

(По книге O’Reilly — Head First — "Design Patterns")

На мой взгляд, книга O’Reilly "Head First — Design Patterns" - одна из лучших книг по паттернам - но когда я читал ее, у меня были кое-где неясности, и поэтому я решил написать эту статью. 

Паттерн <b><i>стратегия</i></b> — самый простой из паттернов.

<i>Паттерн <b><i>Стратегия</i></b> определяет семейство алгоритмов, инкапсулирует каждый из них и обеспечивает их взаимозаменяемость. Он позволяет модифицировать алгоритмы независимо от их использования на стороне клиента.</i>

Для примера возьмем класс «<i>утка</i>» с методами «<i>квак</i>» и «<i>показать</i>».

<img src="https://i.gyazo.com/cecd8a27350d3acd5655672484d0219c.png" alt="image"/>

Класс утка — <i>суперкласс</i>.
От него мы будем наследовать.

Пусть у нас будут еще две утки — <i>WildDuck</i> и <i>CityDuck</i>.

<img src="https://i.gyazo.com/72b591b408fec7ee5ae8ca1e317c9fe4.png" alt="image"/>

У всех уток есть метод «<i>display</i>».
Добавим еще резиновую утку, а в наш суперкласс добавим метод <i>fly()</i> -  «<i>летать</i>».

<img src="https://i.gyazo.com/d761a30bc0385b3a9783102d788e1046.png" alt="image"/>

Добавим еще несколько уток.

<img src="https://i.gyazo.com/df82d43966b6f34c05ab4901be32a726.png" alt="image"/>

Но резиновые утки не летают.
А у <i>CloudDuck</i> и <i>MountainDuck</i> один и тот же способ полета.

Т.е. у <i>CloudDuck</i> и <i>MountainDuck</i> будет одинаковый метод <i>fly()</i>, но отличающийся от метода <i>fly()</i> утки  <i>CloudDuck</i>.
Значит нужен другой класс для нового способа полета. Но это неправильный путь.

А что если нам нужно добавить метод <i>eat()</i>, и у разных видов уток он будет иметь разную реализацию? У нас в джаве нет множественного наследования (см. <a href="http://info.javarush.ru/translation/2013/10/22/%D0%9C%D0%BD%D0%BE%D0%B6%D0%B5%D1%81%D1%82%D0%B2%D0%B5%D0%BD%D0%BD%D0%BE%D0%B5-%D0%BD%D0%B0%D1%81%D0%BB%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5-%D0%B2-Java-%D0%9A%D0%BE%D0%BC%D0%BF%D0%BE%D0%B7%D0%B8%D1%86%D0%B8%D1%8F-%D0%B2-%D1%81%D1%80%D0%B0%D0%B2%D0%BD%D0%B5%D0%BD%D0%B8%D0%B8-%D1%81-%D0%9D%D0%B0%D1%81%D0%BB%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5%D0%BC.html">Ромбовидная проблема</a>). Поэтому нам нужно другое решение.

Перейдем к решению проблемы.

Все наши классы — это клиенты для алгоритмов и они используют различные алгоритмы для реализации метода <i>fly()</i>.

У нас есть алгоритм для метода <i>fly()</i> и алгоритм для метода <i>Quack()</i>. То есть мы должны создать стратегию для метода <i>fly()</i> и для метода <i>Quack()</i>. Мы должны извлечь эти методы чтобы использовать различные реализации.

Создадим интерфейс <i>IQuackBehaviour</i> для метода <i>quack()</i>.

<img src="https://i.gyazo.com/4f6586bd39b6875a12fc470dbd1eaeb5.png" alt="image"/>

И создадим интерфейс <i>IFlyBehaviour</i> для метода <i>fly()</i>.

<img src="https://i.gyazo.com/d85163df9b679c5d8ea659833d36158c.png" alt="image"/>

Возможно даже было бы сделать из этих двух интерфейсов один интерфейс <i>IDuckBehaviour</i>.

Таким образом мы вынесли поведение уток за пределы класса Duck.

Интерфейс поведения будет реализовываться классами поведения, а не классом <i>Duck</i>.

Каждая утка будет иметь свой <i>IFlyBehaviour</i> и свой <i>IQuackBehaviour</i>.

<img src="https://i.gyazo.com/6ffd8fb0b6ccdc0c74bf1af0ae49b70e.png" alt="image"/>

Класс <i>SimpleQuack</i> реализует интерфейс <i>IQuackBehaviour</i>.

Мы можем сказать, что <i>Quack()</i> метод класса <i>Duck</i> передает полномочия интерфейсу <i>IQuackBehaviour</i>.

Когда мы вызываем метод <i>Quack()</i> класса <i>Duck</i> — мы запускаем метод <i>Quack()</i> класса <i>SimpleDuck</i>.

Резиновая утка не крякает, поэтому для нее будет еще одна реализация интерфейса.

<img src="https://i.gyazo.com/a8f9268932f116873326f1fca45a06e1.png" alt="image"/>

Теперь класс <i>Duck</i> не должен заботиться о реализации кваканья уток. Класс <i>Duck</i> только должен иметь доступ к реализации интерфейса <i>IQuackBehaviour</i>.

Сделаем то же для <i>IFlyBehaviour</i>.

<img src="https://i.gyazo.com/f487eec79c2eefc00cdd9ba03522aabb.png" alt="image"/>

Можем поступить аналогично для метода <i>Display()</i>.

<img src="https://i.gyazo.com/1cb3edd50d197b2d1d233e7adfedc9fc.png" alt="image"/>

<i>SipmpleQuack</i>, <i>NoQuack</i>, <i>SimpleFly</i>… — это конкретные стратегии. 

На самом деле нам теперь не нужны сублассы WildDuck и CityDuck и мы можем их убрать.

<img src="https://i.gyazo.com/e7884fae1d17ff085773daf629ae2361.png" alt="image"/>

Для удобства кодирования приведем нашу схему к следующему виду:

<img src="https://i.gyazo.com/4194864e2d08b90261a83c7572416b55.png" alt="image"/>

В IDE наши классы выглядят следующим образом:

<img src="https://i.gyazo.com/3c39d1b628aa943f35bc9b4faabb4f3c.png" alt="image"/>

Посмотрим на код.

Код класса "Реальная утка" выглядит следующим образом: 

<source lang="java">public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehaviour = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I am a real Mallard Duck ");
    }
}</source>

Абстрактная утка — наш суперкласс. Код класса "Абстрактная утка" приведен ниже:

<source lang="java">public abstract class Duck {

    public QuackBehavior quackBehavior;
    public FlyBehaviour flyBehaviour;

    public Duck(){
    }

    public abstract void display();

    public void performFly(){
        flyBehaviour.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    public void swim(){
        System.out.println("All ducks float, even decoys!");
    }

}</source>

Рассмотрим интерфейсы с описанием поведения уток.

Код интерфейса "FlyBehaviour" приведен ниже:

<source lang="java">public interface FlyBehaviour {
    public void fly();
}</source>

Код интерфейса "QuackBehavior":

<source lang="java">public interface QuackBehavior {
    public void quack();
}</source>

Ниже приведен код конкретных стратегий поведения.
Стратегия <i>FlyNoWay</i>:

<source lang="java">public class FlyNoWay implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}</source>

Стратегия <i>FlyWithWings</i>:

<source lang="java">public class FlyWithWings implements FlyBehaviour {
    @Override
    public void fly() {
        System.out.println("I am flying !");
    }
}</source>

Стратегия <i>MuteQuack</i>:

<source lang="java">public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" << Silence >> ");
    }
}</source>

Стратегия <i>Quack</i>:

<source lang="java">public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}</source>

Стратегия <i>Squeak</i>:

<source lang="java">public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println(" Squeak ");
    }
}</source>

Ниже приведен код класса "<i>мини утиный симулятор</i>": 

<source lang="java">public class MiniDuckSimulator {

    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
    }
}</source>

В данной статье мы рассмотрели самый простой из паттернов - паттерн "<b><i>Стратегия</i></b>".

Весь код можно посмотреть на моем гитхабе <a href="https://github.com/alexandroid1/PatternStrategy">https://github.com/alexandroid1/PatternStrategy</a>

Меня можно найти в соц сетях:
Мой Linkedin <a href="https://www.linkedin.com/in/alexandroid1/">https://www.linkedin.com/in/alexandroid1/</a>
Моя личная страница ВК <a href="https://vk.com/sashapavlov">https://vk.com/sashapavlov</a>
Мой Instagramm <a href="https://www.instagram.com/aleksandr_7017/">https://www.instagram.com/aleksandr_7017/</a>
Я в Facebook <a href="https://www.facebook.com/aleksandr7017">https://www.facebook.com/aleksandr7017</a>

<h2>Ссылки на источники</h2>
1.  Элизабет Фримен, Эрик Фримен, Кэти Сиерра, Берт Бейтс  - "Паттерны проектирования".
2. Strategy Pattern – Design Patterns (ep 1) - <a href="https://www.youtube.com/watch?v=v9ejT8FO-7I">https://www.youtube.com/watch?v=v9ejT8FO-7I</a>
