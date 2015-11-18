Решение задачи MailService.

// MailMessage и Salary имеют практически идентичный интерфейс
// за исключением типа поля content. Давайте абстрагируем это знание в интерфейс.
public static interface Sendable<T> {
    String getFrom();
    String getTo();
    T getContent();
}


// Абстрагируем логику хранения всех элементов класса во внутренних полях класса,
// создав класс SimpleSendable. Не забудем указать реализуемый интерфейс.
public static class SimpleSendable<T> implements Sendable<T> {
    private String from, to;
    private T content;

    public SimpleSendable(String from, String to, T content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public T getContent() {
        return content;
    }
}


// Теперь объявим MailMessage и Salary, отнаследовавшись от SimpleSendable
// с конкретным параметром типа.
public static class MailMessage extends SimpleSendable<String> {
    public MailMessage(String from, String to, String content) {
        super(from, to, content);
    }
}


public static class Salary extends SimpleSendable<Integer> {
    public Salary(String from, String to, Integer content) {
        super(from, to, content);
    }
}


// forEachOrdered и forEach ожидают в качестве аргумента класс,
// реализующий интерфейс Consumer.
// Судя по исходному коду, Consumer потребляет письма с содержимым,
// соответствующим параметру класса MailService.
public static class MailService<T> implements Consumer<Sendable<T>> {

    // Если внимательно посмотреть на исходный код задания, можно заметить,
    // что логика метода get у получаемого в getMailBox Map'а при отсутствующем элементе
    // отличается от логики стандартных коллекций. Переназначим эту логику в анонимном
    // наследнике HashMap.
    private Map<String, List<T>> messagesMap = new HashMap<String, List<T>>(){
        @Override
        public List<T> get(Object key) {
            if (this.containsKey(key)) {
                // Возвращать изменяемый список во внешний мир – не очень хорошая идея
                // по причине того, что его изменение может испортить внутреннее состояние
                // словаря. Лучше оборачивать подобный вывод в
                //  Collections.unmodifiableList.
                // Однако здесь мы не можем так поступить по причине того,
                // что добавлять новые элементы в списки из accept будет неудобно.
                // Нужно реализовать дополнительный метод getMutable, который возвращал
                // бы изменяемый список, удобный для модификации.
                // Но добавить новый метод мы можем только в именованный класс.
                return super.get(key);
            } else {
                // Collections.emptyList() возвращает один и тот же экземпляр
                // неизменяемого списка. Если бы мы возвращали здесь, допустим,
                // new ArrayList<>(), то множество вызовов get по отсутвующему
                // элементу создавало бы множество лишних объектов.
                return Collections.emptyList();
            }
        }
    };

    @Override
    public void accept(Sendable<T> sendable) {
        List<T> ts = messagesMap.get(sendable.getTo());
        if (ts.size() == 0) {
            ts = new ArrayList<>();
        }
        ts.add(sendable.getContent());
        messagesMap.put(sendable.getTo(), ts);
    }

    public Map<String, List<T>> getMailBox() {
        return messagesMap;
    }
}
