import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Word {
    private String word;
    private String description;

    // αρχικοποίηση constructor
    public Word(String word, String description) {
        this.word = word.toUpperCase();
        this.description = description;
    }

    // Δημιουργία getter
    public String getDescription() {
        return description;
    }

    public String getWord() {
        return word;
    }

    public Integer length() {
        return word.length();
    }

    // Επιστροφή του χαρακτήρα στην πρώτη θέση
    public char getFirstCharacter() {
        return word.charAt(0);
    }

    // Επιστροφή του χαρακτήρα στην τελευταία θέση
    public char getLastCharacter() {
        return word.charAt(word.length() - 1);
    }

    public String getWordWitFirstLastCharOnly() {
        StringBuilder sb = new StringBuilder();
        // Εκτύπωση λέξεων με το πρώτο και τελευταίο γράμμα
        sb.append(getFirstCharacter() + " ");
        for (int i = 0; i < length() - 2; i++) {
            sb.append("_ ");
        }
        sb.append(getLastCharacter());
        return sb.toString();
    }

    public String getWordSpacesOnly() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            sb.append("_ ");
        }
        return sb.toString();
    }

    public List<Character> getCharacters() {
        List<Character> l = new ArrayList<>();
        // Για κάθε χαρακτήρα της λέξης βάλτο μέσα σε λίστα
        for (char c : word.toCharArray())
            l.add(c);
        return l;
    }

    public List<Character> getCharactersWithoutFirstLast() {
        List<Character> l = new ArrayList<>();
        // Για κάθε χαρακτήρα της λέξης βάλτο μέσα σε λίστα
        for (char c : word.toCharArray())
            l.add(c);
        // Αφαίρεση πρώτου και τελευταίου γράμματος
        l.remove(word.toCharArray().length - 1);
        l.remove(0);
        return l;
    }

    @Override
    public String toString() {
        return word;
    }
}
