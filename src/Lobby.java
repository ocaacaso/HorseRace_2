
import java.util.ArrayList;
import java.util.Scanner;

public class Lobby {

    public static void main(String[] args) throws InterruptedException {
        try (Scanner input = new Scanner(System.in)) {
            ArrayList<ThreadCavallo> cavalli = new ArrayList<>();

            System.out.print("Inserire la lunghezza del percorso: ");
            int roadLength = input.nextInt();

            Corsa corsa = new Corsa(roadLength, cavalli);
            System.out.println("La lunghezza del percorso è " + roadLength + " metri");
            input.nextLine();  // Consume newline

            System.out.print("Inserire il numero dei cavalli partecipanti: ");
            int numCavalli = input.nextInt();
            input.nextLine();  // Consume newline

            // Loop to gather each horse's data
            while (numCavalli > 0) {
                System.out.print("Inserire il nome del cavallo: ");
                String horseName = input.nextLine();

                System.out.print("Inserire la velocità del cavallo (1 m/s - 25 m/s): ");
                int speed = input.nextInt();

                // Speed validation
                while (speed > 30) {
                    System.out.print("Velocità troppo alta. Inserire una velocità valida (1 m/s - 30 m/s): ");
                    speed = input.nextInt();
                }
                input.nextLine();  // Consume newline

                boolean isLast = (numCavalli == 1);
                ThreadCavallo cavallo = new ThreadCavallo(corsa, roadLength, horseName, speed, isLast);
                cavalli.add(cavallo);

                System.out.println(horseName + " partecipa alla gara. ");
                numCavalli--;
            }

            // Start the race
            corsa.startCorsa();
        }
    }
}
