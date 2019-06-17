package libgdx.screens.game.service.submitanddelete;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import libgdx.model.CrossWordWithPosition;
import libgdx.screens.game.service.Utils;
import libgdx.screens.game.service.crossworddisplay.HighlightCrossWordDisplayService;
import libgdx.services.CrossWordContext;
import libgdx.services.CrossWordMatrixEncoder;
import libgdx.services.CrossWordService;

public class HighlightCrosswordSubmitAndDeleteService extends CrosswordSubmitAndDeleteService {

    private HighlightCrossWordDisplayService crossWordDisplayService;
    private CrossWordContext crossWordContext;

    public HighlightCrosswordSubmitAndDeleteService(CrossWordContext crossWordContext) {
        super(crossWordContext);
        this.crossWordContext = crossWordContext;
        this.crossWordDisplayService = (HighlightCrossWordDisplayService) crossWordContext.getCrossWordDisplayService();
    }

    @Override
    public String getPressedWord() {
        if (crossWordDisplayService.getActiveHighlightedCrossword() != null) {
            return Utils.getPressedWordForAlreadyDisplayedLetters(crossWordContext.getLettersToPressService().getPressedLetters(), crossWordDisplayService.getCellsForPositionInCrossword(crossWordDisplayService.getActiveHighlightedCrossword().getPositionInCrossWord()));
        }
        return null;
    }

    @Override
    public float getButtonsY() {
        return Utils.getHighlightSubmitDeleteShuffleBackspaceToPressButtonsY();
    }

    @Override
    CrossWordWithPosition isWordCorrect(String pressedWord) {
        CrossWordWithPosition activeHighlightedCrossword = crossWordDisplayService.getActiveHighlightedCrossword();
        Set<String> allPossibleCorrectWords = new HashSet<>(crossWordContext.getAllPossibleCorrectWords());
        return allPossibleCorrectWords.contains(pressedWord) && verifyMatrix(pressedWord, activeHighlightedCrossword.getPositionInCrossWord()) ? activeHighlightedCrossword : null;
    }

    private boolean verifyMatrix(String pressedWord, int positionInCrossWord) {
        boolean isValid = false;
        int tries = 30;
        for (int t = 0; t < tries; t++) {
            CrossWordService crossWordService = new CrossWordService();
            String[][] matrix = crossWordService.buildWordMatrix(crossWordContext.getAllPossibleCorrectWords(), crossWordContext.getAllCrossWords());
            Map<Integer, String> wordsForPosition = crossWordService.getWordsForPosition(crossWordContext.getTotalCrossWords(), matrix);
            boolean wordsContainInvalid = false;
            for (String word : wordsForPosition.values()) {
                if (!crossWordContext.getAllPossibleCorrectWords().contains(word)) {
                    wordsContainInvalid = true;
                    break;
                }
            }
            if (!wordsContainInvalid && wordsForPosition.get(positionInCrossWord).equals(pressedWord)) {
                isValid = true;
            }
            if (isValid) {
                break;
            }
        }
        return isValid;
    }

    @Override
    boolean isSubmitBtnVisible() {
        if (crossWordDisplayService.getActiveHighlightedCrossword() != null) {
            return getPressedWord().length() == crossWordDisplayService.getActiveHighlightedCrossword().getWordLength();
        }
        return false;
    }


}
