import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

//2. Найти в массиве наиболее часто встречающееся число (числа, если таких несколько),
//        вывести на экран исходные данные и результаты.
public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() * (20 - 0)) + 0);
        }
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        int[] res = solution(arr);
        System.out.println("Наиболее часто встречающееся число: " + Arrays.toString(res));
    }

    public static int[] solution(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int elem : arr) {
            if (map.containsKey(elem)) {
                map.put(elem, map.get(elem) + 1);
            } else {
                map.put(elem, 1);
            }
            int v = map.get(elem);
            max = (max < v) ? v : max;
        }

        int[] res = new int[arr.length];
        int count = 0;
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (Objects.equals(max, entry.getValue())) {
                res[count++] = entry.getKey();
            }
        }
        return Arrays.copyOf(res, count);
    }
}