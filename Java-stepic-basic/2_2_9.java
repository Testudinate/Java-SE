Реализуйте метод, проверяющий, является ли заданное число по абсолютной величине степенью двойки.

Если для решения задачи вам требуются циклы или условные операторы, то можете вернуться
к ней после просмотра соответствующих уроков. Хотя решить можно и без них.

Воспользуйтесь предоставленным шаблоном. Декларацию класса, метод main и обработку ввода-вывода добавит проверяющая система.

 Sample Input 1:

0

Sample Output 1:

false


Sample Input 2:

1

Sample Output 2:

true


Sample Input 3:

-2

Sample Output 3:

true


/**
 * Checks if given <code>value</code> is a power of two.
 *
 * @param value any number
 * @return <code>true</code> when <code>value</code> is power of two, <code>false</code> otherwise
 */
public static boolean isPowerOfTwo(int value) {
    if (value == 0) {
            return false;
        }
        else {
            int res = Math.abs(value);
            return (res & (res - 1)) == 0;
    }
    // you implementation here
}

Побитовые операции и операции битового сдвига в Java и не только

http://www.pvsm.ru/java/39711
