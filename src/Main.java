import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Definiowanie potrzebnych zmiennych
        int maxNumber = 24;
        int numbersCount = 6;
        int[] userNumbers = new int[numbersCount];
        int[] winningNumbers = new int[numbersCount];
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // Określenie wartości nagród
        String[] rewards = new String[numbersCount];
        for (int index = 0; index < rewards.length; index++) {
            int reward = maxNumber-numbersCount;
            reward *= (int)Math.pow(7, index);
            rewards[index] = String.valueOf(reward) + "$";
        }
        System.out.println("### Witaj w naszej loterii. ###");
        System.out.println("Zagraj o niesamowite nagrody:");
        System.out.println(Arrays.toString(rewards));

        // Pobranie liczb od urzytkownika
        for (int index = 0; index < numbersCount; index++) {
            int givenNumber;

            do {
                System.out.print("Podaj " +(index+1)+ " liczbę z zakresu <1-" +maxNumber+ ">: ");
                givenNumber = scanner.nextInt();
            } while (givenNumber < 1 || givenNumber > maxNumber);

            userNumbers[index] = givenNumber;
        }
        scanner.close();
        // Sortowanie i wyświetlenie wytypowanych liczb
        Arrays.sort(userNumbers);
        System.out.println("Twoje liczby: " + Arrays.toString(userNumbers));

        // Wylosowanie liczb
        System.out.println("Losuje liczby...");
        random.nextInt(24);
        for (int index = 0; index < winningNumbers.length; index++) {
            int newRandomNumber;
            // Kod poniżej wraz z pętlą "do-while" jest potrzebny, aby losować wartości bez powtórzeń
            boolean isNumberAlreadyExists;
            do {
                isNumberAlreadyExists = false;
                newRandomNumber = random.nextInt(maxNumber) + 1;
                for (int winningNumber : winningNumbers) {
                    if (newRandomNumber == winningNumber) {
                        isNumberAlreadyExists = true;
                        break;
                    };
                }
            } while (isNumberAlreadyExists);
            winningNumbers[index] = newRandomNumber;
        }
        // Sortowanie i wyświetlenie wylosowanych liczb
        Arrays.sort(winningNumbers);
        System.out.println("Wylosowane liczby to: " + Arrays.toString(winningNumbers));

        // Sprawdzenie ile liczb trafił gracz
        int wonNumbersCount = 0;
        for (int userNumber : userNumbers) {
            for (int winningNumber : winningNumbers) {
                if (userNumber == winningNumber) {
                    wonNumbersCount++;
                }
            }
        }

        // Informacje o nagrodzie
        if (wonNumbersCount > 0) {
            System.out.println("\nGratulacje liczba poprawnych typowań to: " + wonNumbersCount);
            System.out.println("Tym samym wygrywasz: " + rewards[wonNumbersCount-1]);
        } else {
            System.out.println("\n... niestety nie udało się tym razem, spróbuj jeszcze raz..");
        }
    }
}
