Реализуйте метод, который возвращает букву, стоящую в таблице UNICODE после символа "\" (обратный слэш) на расстоянии a:

В качестве примера написано заведомо неправильное выражение. Исправьте его.

Sample Input 1:

15

Sample Output 1:

k


Sample Input 2:

8

Sample Output 2:

d


public char charExpression(int a) {
    int s = '\\';
    int total = s + a;
    char unicode = (char) total;
    return unicode;
}
