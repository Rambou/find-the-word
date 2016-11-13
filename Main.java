import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Δημιουργία δομής μοναδικών λέξεων
        ArrayList<Word> wordList = new ArrayList<>();
        wordList.add(new Word("car", "A road vehicle, typically with four wheels, powered by an internal combustion engine and able to carry a small number of people."));
        wordList.add(new Word("computer", "An electronic device which is capable of receiving and process information."));
        wordList.add(new Word("ram", "An uncastrated male sheep."));
        wordList.add(new Word("network", "A group or system of interconnected people or things."));
        wordList.add(new Word("language", "A system of communication used by a particular country or community."));
        wordList.add(new Word("university", "A high-level educational institution in which students study for degrees and academic research is done."));
        wordList.add(new Word("dog", "A domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, non-retractile claws, and a barking, howling, or whining voice."));
        wordList.add(new Word("facebook", "A huge social network we use."));
        wordList.add(new Word("karlovasi", "A village name in an island we all love."));

        // Εισαγωγή στοιχείων χρήστη με έλεγχο εγκυρότητας
        System.out.println("ΚΑΛΩΣΗΡΘΑΤΕ ΣΤΟ ΠΑΙΧΝΙΔΙ «ΒΡΕΣ ΤΙΣ ΛΕΞΕΙΣ»\n");
        System.out.print("Give you name: ");
        String playerName = input.next();
        // Εισαγωγή επιπέδου
        System.out.print("Δώσε επίπεδο δυσκολίας 1 για εύκολο, 2 για δύσκολο: ");
        while (!input.hasNextInt()) input.next();
        int level = input.nextInt();

        // Αρχικοποίηση παιχνιδιού
        Game g = new Game(playerName, level, wordList, input);

        // Επανάληψη μέχρι ο παίκτης να αποφασίσει να φύγει
        while (true) {
            // Αρχικοποίηση νέου παιχνιδιού
            g.initialize();

            // εκκίνηση παιχνιδιού
            g.run();

            // ρώτα τον παίκτη αν θέλει να ξαναπαίξει
            // με έλεγχο εγκυρότητας εισόδου μέσω pattern
            System.out.println(playerName + " θες να ξαναπαίξεις; (y/n)");
            String pattern = "(?:y|n)(.*)";
            Pattern p = Pattern.compile(pattern);
            // Επανάληψη μέχρι η είσοδο να ταιριάζει το pattern
            while (!input.hasNext(p)) input.next();
            String playAgain = input.next(p);

            // Εάν η απάντηση είναι αρνητική τότε βγές από το παιχνίδι
            if (playAgain.equals("n")) {
                break;
            }
        }
    }

}
