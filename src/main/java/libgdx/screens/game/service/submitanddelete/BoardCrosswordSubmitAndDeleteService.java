package libgdx.screens.game.service.submitanddelete;

import libgdx.model.CrossWordWithPosition;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import libgdx.screens.game.service.Utils;
import libgdx.services.CrossWordContext;

public class BoardCrosswordSubmitAndDeleteService extends CrosswordSubmitAndDeleteService {

    private CrossWordContext crossWordContext;
    private List<CrossWordWithPosition> correctWords;

    public BoardCrosswordSubmitAndDeleteService(CrossWordContext crossWordContext) {
        super(crossWordContext);
        this.crossWordContext = crossWordContext;
        this.correctWords = crossWordContext.getAllCrossWords();
    }

    @Override
    public String getPressedWord() {
        return StringUtils.join(crossWordContext.getLettersToPressService().getPressedLetters().toArray());
    }

    //TODO not supported
    @Override
    CrossWordWithPosition isWordCorrect(String pressedWord) {
//        return crossWordContext.getAllPossibleCorrectWords().contains(pressedWord) ? activeHighlightedCrossword : null;
//
//        crossWordContext.getAllPossibleCorrectWords().contains(pressedWord)
//        for (CrossWordWithPosition crossWord : correctWords) {
//            if (crossWord.getWord().equals(pressedWord)) {
//                return crossWord;
//            }
//        }
        return null;
    }

    @Override
    boolean isSubmitBtnVisible() {
        return lettersToPressService.areAnyButtonsPressed();
    }

    @Override
    public float getButtonsY() {
        return Utils.getBoardSubmitDeleteShuffleBackspaceToPressButtonsY();
    }
}
