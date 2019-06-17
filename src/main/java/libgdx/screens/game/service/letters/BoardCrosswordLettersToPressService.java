package libgdx.screens.game.service.letters;

import libgdx.controls.button.MyButton;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.game.service.Utils;
import libgdx.services.CrossWordContext;
import libgdx.utils.ScreenDimensionsManager;

public class BoardCrosswordLettersToPressService extends LettersToPressService {

    private CrossWordContext crossWordContext;

    public BoardCrosswordLettersToPressService(CrossWordContext crossWordContext) {
        super(crossWordContext.getTotalNrOfLetters(), crossWordContext.getGameLetters());
        this.crossWordContext = crossWordContext;
    }

    @Override
    public boolean executeLetterButtonClick() {
        return !crossWordContext.getCrossWordDisplayService().getCells().isEmpty();
    }

    @Override
    public float buttonMoveToX(MyButton myButton) {
        int amountToIncrement = letterPositionService.getAmountToIncrement(pressedLetterButtons.size(), MAX_LETTERS_TO_PRESS_ON_ROW);
        float horizontalGeneralMarginDimen = MainDimen.horizontal_general_margin.getDimen();
        return horizontalGeneralMarginDimen + getLetterButtonSize().getWidth() * amountToIncrement + (horizontalGeneralMarginDimen / 3) * amountToIncrement;
    }

    @Override
    public float buttonMoveToY(MyButton myButton) {
        float moveToY = ScreenDimensionsManager.getScreenHeightValue(22);
        if (pressedLetterButtons.size() > MAX_LETTERS_TO_PRESS_ON_ROW) {
            moveToY = moveToY - letterPositionService.getLettersVerticalMargin();
        }
        return moveToY;
    }

    @Override
    public float getInitialButtonsY() {
        return Utils.getLettersToPressButtonsY();
    }
}
