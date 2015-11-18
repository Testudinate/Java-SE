

Разбор задачи Тернарный оператор на функциональных интерфейсах.

Метод ternaryOperator() должен вернуть экземпляр Function. Поскольку Function — функциональный интерфейс, есть следующие способы его инстанцировать:

    создать экземпляр анонимного или именованного класса, реализующего интерфейс Function;
    воспользоваться ссылкой на метод;
    написать лямбда-выражение.

Компактнее всего будет решение через лямбда-выражение, именно его и ожидает проверяющая система. В итоге решение записывается в одну строку:

return x -> condition.test(x) ? ifTrue.apply(x) : ifFalse.apply(x);


Надо помнить, что condition, ifTrue и ifFalse — это обычные объекты, у них есть методы. Если забыли, какие у них методы, то можно воспользоваться подсказкой IDE или сходить в JavaDoc/исходники интерфейсов Predicate и Function. Нельзя просто взять и написать:

return condition(x) ? ifTrue(x) : ifFalse(x); // это не скомпилируется


В задаче был дополнительный вопрос про сигнатуру метода ternaryOperator(). Почему он объявлен именно так, а не более простым способом, без всяких <? super T> и <? extends U>? Можно ведь было объявить его так:

public static <T, U> Function<T, U> ternaryOperator(
            Predicate<T> condition,
            Function<T, U> ifTrue,
            Function<T, U> ifFalse)

Если метод ternaryOperator() объявить таким способом, то код из примера не скомпилируется. В Java типы Predicate<Object> и Predicate<String> несовместимы, поэтому нельзя передать Predicate<Object> в метод, ожидающий Predicate<String>.

Это касалось <? super T>. Если же вместо <? extends U> использовать <U>, то не скомпилируется следующий пример, т.к. несовместимыми являются типы Function<Object, String> и Function<Object, CharSequence>.

Predicate<Object> condition = Objects::isNull;
Function<Object, String> ifTrue = obj -> "null";
Function<Object, String> ifFalse = Object::toString;
Function<Object, CharSequence> objectToCharSequence =
        ternaryOperator(condition, ifTrue, ifFalse);

