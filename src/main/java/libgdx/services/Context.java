package libgdx.services;

import libgdx.screens.game.service.keyboardletter.KeyboardLetterService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;

public abstract class Context {

    private int totalCrossWords;
    private int totalNrOfLetters;

    public Context(int totalCrossWords, int totalNrOfLetters) {
        this.totalCrossWords = totalCrossWords;
        this.totalNrOfLetters = totalNrOfLetters;
    }

    public int getTotalCrossWords() {
        return totalCrossWords;
    }

    public int getTotalNrOfLetters() {
        return totalNrOfLetters;
    }

    KeyboardLetterService keyboardLetterService;

    public abstract ExtraButtonsService getShuffleAndBackspaceService();

    public abstract SubmitAndDeleteService getSubmitAndDeleteService();

    public abstract LettersToPressService getLettersToPressService();

    public abstract KeyboardLetterService getKeyboardLetterService(FinalWordContext finalWordContext);

}
