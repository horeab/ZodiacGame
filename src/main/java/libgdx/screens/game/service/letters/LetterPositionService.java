package libgdx.screens.game.service.letters;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.dimen.MainDimen;
import libgdx.utils.ScreenDimensionsManager;

public class LetterPositionService {

    private LettersGameButtonSize letterSize;
    private int nrOfLettersOnRow;
    private int totalNrOfLetters;
    private float initialY;

    public LetterPositionService(LettersGameButtonSize letterSize,
                                 int nrOfLettersOnRow,
                                 int totalNrOfLetters,
                                 float initialY) {
        this.letterSize = letterSize;
        this.nrOfLettersOnRow = nrOfLettersOnRow;
        this.totalNrOfLetters = totalNrOfLetters;
        this.initialY = initialY;
    }

    public List<Pair<Float, Float>> getLettersCoordList() {
        List<Pair<Float, Float>> list = new ArrayList<>();
        for (int i = 0; i < totalNrOfLetters; i++) {
            list.add(getLetterCoordinates(i));
        }
        return list;
    }

    private Pair<Float, Float> getLetterCoordinates(int indexInList) {
        float x = getLetterXMargin() + getLetterX(getAmountToIncrement(indexInList, nrOfLettersOnRow) + 1);
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        float y = initialY - (getRowsFilled(indexInList, nrOfLettersOnRow) * (getLettersVerticalMargin() + verticalGeneralMarginDimen / 2));
        return new MutablePair<>(x, y);
    }

    private float getLetterXMargin() {
        return (ScreenDimensionsManager.getScreenWidth() - getLetterX(nrOfLettersOnRow)) / 2;
    }

    float getLettersVerticalMargin() {
        return MainDimen.vertical_general_margin.getDimen() * 5;
    }

    private float getLetterX(int letterPos) {
        return letterSize.getWidth() * letterPos + MainDimen.horizontal_general_margin.getDimen() * letterPos;
    }

    int getAmountToIncrement(int positionInList, int nrOfLettersOnRow) {
        return getRowsFilled(positionInList, nrOfLettersOnRow) > 0 ? (positionInList - (nrOfLettersOnRow * getRowsFilled(positionInList, nrOfLettersOnRow)) - 1) : positionInList - 1;
    }

    private int getRowsFilled(int positionInList, int nrOfLettersOnRow) {
        return (int) Math.floor((positionInList) / Float.valueOf(nrOfLettersOnRow));
    }
}
