
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Corsa {

    public int roadLength;
    public ArrayList<ThreadCavallo> cavalli;
    public ArrayList<String> ranking = new ArrayList<>(Arrays.asList("vuoto", "vuoto", "vuoto"));

    public Corsa(int roadLength, ArrayList<ThreadCavallo> cavalli) {
        this.roadLength = roadLength;
        this.cavalli = cavalli;
    }


    public ArrayList<ThreadCavallo> getCavalli() {
        return cavalli;
    }

    // Starts the race and waits for all horse threads to finish
    public void startCorsa() throws InterruptedException {
        ArrayList<Thread> threadCavalli = new ArrayList<>();

        for (ThreadCavallo cavallo : cavalli) {
            Thread thread = new Thread(cavallo);
            threadCavalli.add(thread);
            thread.start();
        }

        try {
            for (Thread thread : threadCavalli) {
                thread.join();
            }
        } catch (Exception e) {
            System.err.println("Error during thread join: " + e.getMessage());
        }

        System.out.println("La gara è terminata");
        System.out.printf("La classifica è %nAl primo posto: %s%nAl secondo posto: %s%nAl terzo posto: %s%n",
                          ranking.get(0), ranking.get(1), ranking.get(2));

        // Prompt for file name to save the results
        String filename;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Inserire il nome del file dove salvare le modifiche:");
            filename = input.nextLine();
        }

        try {
            new saveRanking(filename + ".txt", ranking);
        } catch (IOException ex) {
            System.err.println("Errore di I/O: " + ex.getMessage());
            System.exit(1);
        }
    }

    // Updates the ranking with the specified horse's name
    public void generateRanking(String horse) {
        boolean horseAdded = false;

        for (int pos = 0; pos < ranking.size(); pos++) {
            if (ranking.get(pos).equals("vuoto")) {
                ranking.set(pos, horse);
                horseAdded = true;
                break;
            }
        }

        if (!horseAdded) {
            ranking.add(horse);
        }
    }
}
