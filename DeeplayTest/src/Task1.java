import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//1. Заполнить массив случайными целыми числами. Вывести массив на экран. Переупорядочить
//        в этом массиве элементы следующим образом: сначала по не убыванию нечетные числа,
//        потом нули, потом прочие числа по не возрастанию. Вывести массив на экран.
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() * (20 - 0)) + 0);
        }
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        int[] res1 = solution1(arr);
        int[] res2 = solution2(arr);
        System.out.println("Итоговый массив решения 1: " + Arrays.toString(res1));
        System.out.println("Итоговый массив решения 2: " + Arrays.toString(res2));

    }

    public static int[] solution1(int[] arr) {
        int i = 0, j = arr.length - 1, size = arr.length;
        int countZero = 0, countLeft = 0, countRight = 0;
        int[] res = new int[size];
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] % 2 != 0) {
                while (i < countLeft) {
                    if (arr[k] <= res[i]) break;
                    i++;
                }
                if (countLeft - i > 0) {
                    for (int m = countLeft; m >= i + 1; m--) {
                        res[m] = res[m - 1];
                    }
                }
                res[i] = arr[k];
                countLeft++;
                i = 0;
            } else if (arr[k] == 0) {
                countZero++;
            } else if (arr[k] % 2 == 0 && arr[k] != 0) {
                while (j > size - countRight - 1) {
                    if (arr[k] <= res[j] || res[j] == 0) break;
                    j--;
                }
                if (countRight - (size - j - 1) > 0) {
                    for (int m = size - countRight - 1; m < j; m++) {
                        res[m] = res[m + 1];
                    }
                }
                res[j] = arr[k];
                countRight++;
                j = arr.length - 1;
            }
        }
        while (countZero != 0) {
            if (countLeft < size) res[countLeft++] = 0;
            countZero--;
        }
        return res;
    }

    public static int[] solution2(int[] arr) {
        int[] res = new int[arr.length];
        int count = 0;
        PriorityQueue<Integer> left = new PriorityQueue<>();
        PriorityQueue<Integer> right = new PriorityQueue<>(Collections.reverseOrder());
        int countZero = 0;
        for (int num : arr) {
            if (num % 2 != 0) {
                left.add(num);
            } else if (num == 0) {
                countZero++;
            } else {
                right.add(num);
            }
        }
        while (left.size() != 0) {
            res[count++] = left.peek();
            left.remove();
        }
        while (countZero != 0) {
            res[count++] = 0;
            countZero--;
        }
        while (right.size() != 0) {
            res[count++] = right.peek();
            right.remove();
        }
        return res;
    }
}