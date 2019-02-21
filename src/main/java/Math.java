/**
 * created by kozloff 3.05.2018
 */

//Дополнительный класс с математическими вычислениями
// Не работает с числами 0-9 (пока не знаю, почему)
public class Math {

    int cache[] = new int[100];

    int fibo(int n) {
        if (cache[n] == 0) {
            if (n == 1 || n == 2) {
                cache[n] = 1;
            } else {
                cache[n] = fibo(n - 1) + fibo(n - 2);
            }
        }
        return cache[n];
    }

    int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
