package rt;

import java.util.HashMap;
import java.util.Random;

public class Game {

    private final Integer DOORS_COUNT = 3;
    private final Integer GAMES_AMOUNT = 1000;
    private Random random;
    private HashMap<Integer, Boolean> resultsWithoutChoiceChange;
    private HashMap<Integer, Boolean> resultsAfterChoiceChange;
    private Integer winCountWithoutChoiceChange;
    private Integer winCountAfterChoiceChange;

    public Game() {
        random = new Random();
        resultsWithoutChoiceChange = new HashMap<>();
        resultsAfterChoiceChange = new HashMap<>();
        winCountWithoutChoiceChange = 0;
        winCountAfterChoiceChange = 0;
        start();
        showResults();
    }

    private void start() {

        for (int i = 0; i < GAMES_AMOUNT; i++) {
            int winnerChoice = random.nextInt(1, DOORS_COUNT + 1); // дверь, за которой находится приз
            int firstChoice = random.nextInt(1, DOORS_COUNT + 1); // дверь, которую выбрали изначально
            int doorOpenedAfterFirstChoice = 0; // дверь, которую открыл Монти
            for (int j = 1; j < DOORS_COUNT + 1; j++) {
                if (j != winnerChoice && j != firstChoice) {
                    doorOpenedAfterFirstChoice = j;
                }
            }
            int secondChoice = 0; // дверь, которую выбрали после предложения изменить выбор
            for (int j = 1; j < DOORS_COUNT + 1; j++) {
                if (j != firstChoice && j != doorOpenedAfterFirstChoice) {
                    secondChoice = j;
                }
            }
            // 1 ситуация. Выбор остался прежним
            if (winnerChoice == firstChoice) {
                resultsWithoutChoiceChange.put(i + 1, true); // истина - победа
                winCountWithoutChoiceChange++;
            } else {
                resultsWithoutChoiceChange.put(i + 1, false); // ложь - поражение
            }
            // 2 ситуация. Дверь изменена.
            if (winnerChoice == secondChoice) {
                resultsAfterChoiceChange.put(i + 1, true);
                winCountAfterChoiceChange++;
            } else {
                resultsAfterChoiceChange.put(i + 1, false);
            }
        }
    }

    private void showResults() {
        System.out.println("Games played in total: " + GAMES_AMOUNT);
        System.out.printf("Win without choice change: %.2f", (winCountWithoutChoiceChange.doubleValue() / GAMES_AMOUNT.doubleValue() * 100d));
        System.out.println(" % (" + winCountWithoutChoiceChange + " wins & " + (GAMES_AMOUNT - winCountWithoutChoiceChange) + " fails)");
        System.out.printf("Win after choice change: %.2f", (winCountAfterChoiceChange.doubleValue() / GAMES_AMOUNT.doubleValue() * 100d));
        System.out.println(" % (" + winCountAfterChoiceChange + " wins & " + (GAMES_AMOUNT - winCountAfterChoiceChange) + " fails)");
    }
}