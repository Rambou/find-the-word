import java.util.*;
import java.util.regex.Pattern;

public class Game {
    private int level;
    private String playerName;
    private Scanner input;
    private Random random;
    private HashSet<Word> RandomWords;
    private HashSet<Word> playedWords;
    private ArrayList<Word> wordList;
    private int round;
    private int tryies;

    public Game(String playerName, int level, ArrayList<Word> wordList, Scanner input) {
        this.level = level;
        this.playerName = playerName;
        this.random = new Random();
        this.playedWords = new HashSet<>();
        this.wordList = wordList;
        this.input = input;
        this.round = 0;
        this.tryies = 5;
    }

    public void initialize() {
        //Αρχικοποίησε τις προσπάθειες
        tryies = 5;

        // Αύξησε την τιμή του γύρου
        round++;
        System.out.println(round + "ος ΓΥΡΟΣ ΠΑΙΧΝΙΔΙΟΥ");

        // επιλογή τυχαίων λέξεων
        selectRandomWords();

        // Εμφάνιση των λέξεων
        showRandomWords();
    }

    private void selectRandomWords() {
        // Αν ο πάικτης παίξει όλες τις λέξεις τότε η λίστα
        // των λέξεων που έχουν παίξει μηδενίζεται και ξεκινάμε
        // από την αρχή την
        if (playedWords.size() > wordList.size() - 3) {
            playedWords = new HashSet<>();
        }

        // Αφαίρεση λέξεων που έχουν παίξει
        ArrayList<Word> listToSelectFrom = new ArrayList<>(wordList);
        listToSelectFrom.removeAll(playedWords);

        // επιλογή 3ων τυχαίων λέξων
        this.RandomWords = new HashSet<>();
        // Εισαγωγή τυχαίων λέξεων από την λίστα μέχρι να γεμίσει με 3
        // με την χρήση την hashset επιτυγχάνουμε την εισαγωγή μοναδικών
        // λέξεων στην λίστα
        while (RandomWords.size() < 3) {
            Integer randPosition = random.nextInt(listToSelectFrom.size());
            // Αν οι παίζει για πρώτη φορά και δεν υπάρχουν λέξεις που έχουν ξαναπαίξει
            // πρόσθεσε την
            RandomWords.add(listToSelectFrom.get(randPosition));
            playedWords.addAll(RandomWords);
        }
    }

    public void run() {

        ArrayList<Word> WordsToplay = new ArrayList<>(RandomWords);
        while (true) {
            // έλεγχος αν έχει ξεπεράσει τις προσπάθειες
            if (tryies < 1) {
                System.out.println("Δεν έχεις άλλες προσπάθειες. Το παιχνίδι τερμάτισε.");
                break;
            }
            if (RandomWords.isEmpty()) {
                System.out.println("Συγχαρητήρια κέρδισες!!!");
                break;
            }

            // Εμφάνιση γραμμάτων
            printWordsLetters();

            // Επιλογή λέξης
            System.out.print("Επίλεξε την λέξη που θες να μαντέψεις από το 1-3: ");
            // με έλεγχο εγκυρότητας εισόδου μέσω pattern
            String pattern = "(?:1|2|3)";
            Pattern p = Pattern.compile(pattern);
            // Επανάληψη μέχρι η είσοδο να ταιριάζει το pattern
            while (!input.hasNext(p)) input.next();
            int wordNum = input.nextInt();

            // Εισαγωγή λέξης
            System.out.print("Δώσε λέξη: ");
            String word = input.next().toUpperCase();

            Word w = (Word) (WordsToplay.toArray()[wordNum - 1]);
            if (w.getWord().equals(word)) {
                System.out.println("Μπράβο. Μαντέψατε σωστά!!!");
                // αφαίρεση γραμμάτων λέξης που βρέθηκε
                RandomWords.remove(w);
            } else {
                System.out.println("Αποτυχία, έχεις ακόμα " + --tryies + " προσπάθειες.");
            }
        }
    }

    private void showRandomWords() {
        // Εμφάνιση των λέξεων με πρώτο και τελευταίο γράμμα στο επίπεδο 1
        // Εμφάνιση των λέξεων με κανένα γράμμα στο επίπεδο 2
        for (Word w : RandomWords) {
            String format = "%-20s %20s\n";
            System.out.format(format, (level == 1) ? w.getWordWitFirstLastCharOnly() : w.getWordSpacesOnly(), w.getDescription());
        }
    }

    private void printWordsLetters() {
        // Εμφανίση γραμμάτων λέξεων ανακατεμένων
        // Βάλε όλους τους χαρακτήρες των λέξεων σε μια λίστα
        List<Character> letters = new ArrayList<>();
        for (Word w : RandomWords) {
            letters.addAll((level == 1) ? w.getCharactersWithoutFirstLast() : w.getCharacters());
        }

        // shuffle τη λίστα
        Collections.shuffle(letters);

        // ξαναφτιάξε την λέξη σε string
        StringBuilder sb = new StringBuilder();
        for (Character c : letters)
            sb.append(c + " ");
        System.out.println("\n" + sb.toString());
    }

}
