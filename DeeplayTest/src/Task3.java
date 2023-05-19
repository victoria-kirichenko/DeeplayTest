import java.util.Random;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        int[] player1 = new int[3];
        int[] player2 = new int[3];
        int player1Wins = 0;
        int player2Wins = 0;
        int draws = 0;

        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            player1[i] = random.nextInt(6) + 1;
            player2[i] = random.nextInt(6) + 1;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество бросков кубика (не менее 3): ");
        int numThrows = sc.nextInt();
        numThrows = (numThrows < 3) ? 10 : numThrows;
        System.out.print("Введите количество партий: ");
        int numGame = sc.nextInt();

        for (int i = 0; i < numGame; i++) {
            int[] diceThrows = new int[numThrows];

            for (int j = 0; j < numThrows; j++) {
                diceThrows[j] = random.nextInt(6) + 1;
            }

            int player1Score = calculateScore(diceThrows, player1);
            int player2Score = calculateScore(diceThrows, player2);

            if (player1Score > player2Score) {
                player1Wins++;
            } else if (player2Score > player1Score) {
                player2Wins++;
            } else {
                draws++;
            }
        }

        double player1WinProbability = (double) player1Wins / numGame * 100.0;
        double player2WinProbability = (double) player2Wins / numGame * 100.0;
        double drawProbability = (double) draws / numGame * 100.0;

        System.out.println("Вероятность победы игрока 1: " + player1WinProbability + " %");
        System.out.println("Вероятность победы игрока 2: " + player2WinProbability + " %");
        System.out.println("Вероятность ничьей: " + drawProbability + " %");
    }

    private static int calculateScore(int[] cube, int[] player) {
        int score = 0;
        int i = 0, len = cube.length;
        while (i < len) {
            if (i + 2 >= len) break;
            if (cube[i] == player[0] && cube[i + 1] == player[1] && cube[i + 2] == player[2]) {
                score++;
                i += 3;
            } else {
                i++;
            }
        }
        return score;
    }
}
