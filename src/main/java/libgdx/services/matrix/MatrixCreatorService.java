package libgdx.services.matrix;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import libgdx.model.CrossWordWithPosition;
import libgdx.services.CrossWordService;
import libgdx.services.LettersGameService;

class MatrixCreatorService {

    private List<String> allPossibleCorrectWords = new ArrayList<>();
    private List<String> gameLetters = new ArrayList<>();
    private LettersGameService lettersGameService;

    private int totalCrossWords;
    private int totalNrOfLetters;

    public MatrixCreatorService(int totalCrossWords, int totalNrOfLetters) {
        this.totalCrossWords = totalCrossWords;
        this.totalNrOfLetters = totalNrOfLetters;
        this.lettersGameService = new LettersGameService(totalCrossWords, totalNrOfLetters);
    }


    Map<CrossWordWithPosition, Pair<Integer, Integer>> createCrossword() {
        List<CrossWordWithPosition> allCrossWordsWithPosition = new ArrayList<>();
        CrossWordService crossWordService = new CrossWordService();
        int tries = 100;
        while (allCrossWordsWithPosition.size() != totalCrossWords) {
            gameLetters.clear();
            allPossibleCorrectWords.clear();
            allCrossWordsWithPosition.clear();
            if (tries == 0) {
                return null;
            }
            if (prepareCorrectWordsAndGameLetters(totalCrossWords, totalNrOfLetters)) {
                allCrossWordsWithPosition = crossWordService.createAllCrossWords(new HashSet<>(allPossibleCorrectWords), totalCrossWords);
            }
            tries--;
        }
        return crossWordService.getPosition(allCrossWordsWithPosition);
    }

    private boolean prepareCorrectWordsAndGameLetters(int totalCrossWords, int totalNrOfLetters) {
        int tries = 20;
        //The crossword should have more possibilities for words
        while (allPossibleCorrectWords.size() < (totalCrossWords)) {
            gameLetters = lettersGameService.getGameLetters(totalNrOfLetters);
            allPossibleCorrectWords = lettersGameService.getCommonLettersWords(gameLetters);
            tries--;
            if (tries == 0) {
                return false;
            }
        }
        Collections.shuffle(gameLetters);
        Collections.shuffle(allPossibleCorrectWords);
        return true;
    }

    List<String> getGameLetters() {
        return gameLetters;
    }
}
