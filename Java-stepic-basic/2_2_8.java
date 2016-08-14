Укажите преобразования типов, которые компилятор делает автоматически.
Другими словами, для каких преобразований не требуется явно писать в программе оператор приведения типа?


int -> long
long -> float
byte -> char
char -> Character
String -> int
int -> boolean
char -> int
float -> long


import java.math.*;
public class Main {
    public static void main(String[] args) {
        float a = 1222222222222L;    // long -> float
        int b = 'b';                 // char -> int
        Character c = 'b';           // char -> Character
        long d = 3213;               // int -> long
        }
}
