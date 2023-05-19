import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//4. Написать программу распределения данного набора целых чисел (возможны
//        повторяющиеся числа) на заданное число групп так, чтобы были равными суммы
//        чисел, входящих в каждую группу. Если это сделать невозможно, то программа
//        определяет этот факт. Вывести на экран исходные данные и результаты. Задачу
//        необходимо решить точно: не эвристический, а точный алгоритм. Допустимо
//        использовать полный перебор всех вариантов распределения чисел по группам.
public class Task4 {
    private static int middleNumber = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Размер массива: ");
        int n = sc.nextInt();
        System.out.print("Количество групп: ");
        int group = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() * (20 - 0)) + 0);
            middleNumber += arr[i];
        }
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        if (middleNumber % group != 0) {
            System.out.println("Невозможно разделить на " + group + " группы");
        } else {
            middleNumber /= group;
            int[] groupSums = new int[group];
            List<List<Integer>> groups = new ArrayList<>();
            for (int i = 0; i < group; i++) {
                groups.add(new ArrayList<>());
            }
            boolean res = solution(arr, 0, groupSums, groups);
            if (res) {
                System.out.println("Результат:");
                for (List<Integer> elem : groups) {
                    System.out.println(elem);
                }
            } else {
                System.out.println("Невозможно разделить на " + group + " группы");
            }
        }
    }

    private static boolean solution(int[] numbers, int idx, int[] groupSums, List<List<Integer>> groups) {
        if (idx == numbers.length) {
            return true;
        }
        int num = numbers[idx];
        for (int i = 0; i < groupSums.length; i++) {
            if (groupSums[i] + num <= middleNumber) {
                groupSums[i] += num;
                groups.get(i).add(num);

                if (solution(numbers, idx + 1, groupSums, groups)) {
                    return true;
                }
                groupSums[i] -= num;
                groups.get(i).remove(groups.get(i).size() - 1);
            }
        }
        return false;
    }
}