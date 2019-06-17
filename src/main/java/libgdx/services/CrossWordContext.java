package libgdx.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import libgdx.model.CrossWordWithPosition;
import libgdx.screens.game.gametype.GameType;
import libgdx.screens.game.service.HintService;
import libgdx.screens.game.service.crossworddisplay.CrossWordDisplayService;
import libgdx.screens.game.service.keyboardletter.CrossWordKeyboardLetterService;
import libgdx.screens.game.service.keyboardletter.KeyboardLetterService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.screenbackground.ScreenBackgroundCreatorService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;
import libgdx.services.matrix.MatrixContext;

public class CrossWordContext extends Context {

    private GameType gameType;

    private LettersToPressService lettersToPressService;
    private CrossWordDisplayService crossWordDisplayService;
    private ScreenBackgroundCreatorService screenBackgroundCreatorService;
    private SubmitAndDeleteService submitAndDeleteService;
    private ExtraButtonsService shuffleAndBackspaceService;
    private MatrixContext matrixContext;
    private HintService hintService;
    private Set<String> allPossibleCorrectWords;

    private String[][] crossWordMatrix;

    public CrossWordContext(int totalCrossWords,
                            int totalNrOfLetters,
                            List<String[][]> alreadyPlayedMatrix,
                            GameType gameType) {
        super(totalCrossWords, totalNrOfLetters);
        this.gameType = gameType;
        initCrossWordMatrix(totalCrossWords, totalNrOfLetters, alreadyPlayedMatrix);
    }

    private void initCrossWordMatrix(int totalCrossWords,
                                     int totalNrOfLetters,
                                     List<String[][]> alreadyPlayedMatrix) {
        int tries = 20;
        this.matrixContext = new MatrixContext(totalCrossWords, totalNrOfLetters);
        this.crossWordMatrix = matrixContext.getMatrix();
        while (alreadyPlayedMatrix.contains(this.crossWordMatrix) && tries > 0) {
            this.matrixContext = new MatrixContext(totalCrossWords, totalNrOfLetters);
            this.crossWordMatrix = matrixContext.getMatrix();
            tries--;
        }
        this.allPossibleCorrectWords = new HashSet<>(new LettersGameService(getTotalCrossWords(), getTotalNrOfLetters()).getCommonLettersWords(matrixContext.getGameLetters()));
    }

    @Override
    public ExtraButtonsService getShuffleAndBackspaceService() {
        if (shuffleAndBackspaceService == null) {
            shuffleAndBackspaceService = gameType.getExtraButtonsService(this);
        }
        return shuffleAndBackspaceService;
    }

    public HintService getHintService() {
        if (hintService == null) {
            hintService = new HintService(this);
        }
        return hintService;
    }

    @Override
    public SubmitAndDeleteService getSubmitAndDeleteService() {
        if (submitAndDeleteService == null) {
            submitAndDeleteService = gameType.getSubmitAndDeleteService(this);
        }
        return submitAndDeleteService;
    }

    @Override
    public LettersToPressService getLettersToPressService() {
        if (lettersToPressService == null) {
            lettersToPressService = gameType.getLettersToPressService(this);
        }
        return lettersToPressService;
    }

    @Override
    public KeyboardLetterService getKeyboardLetterService(FinalWordContext finalWordContext) {
        if (keyboardLetterService == null) {
            keyboardLetterService = new CrossWordKeyboardLetterService(getLettersToPressService(), getShuffleAndBackspaceService(), getSubmitAndDeleteService(), finalWordContext, getHintService());
        }
        return keyboardLetterService;
    }

    public CrossWordDisplayService getCrossWordDisplayService() {
        if (crossWordDisplayService == null) {
            crossWordDisplayService = gameType.getCrossWordDisplayService(this);
        }
        return crossWordDisplayService;
    }

    public int getTotalCrossWords() {
        return matrixContext.getTotalCrossWords();
    }

    public Set<String> getAllPossibleCorrectWords() {
        return allPossibleCorrectWords;
    }

    public int getTotalNrOfLetters() {
        return matrixContext.getTotalNrOfLetters();
    }

    public List<String> getGameLetters() {
        return matrixContext.getGameLetters();
    }

    public List<CrossWordWithPosition> getAllCrossWords() {
        return matrixContext.getAllCrossWords();
    }

    public String[][] getCrossWordMatrix() {
        return crossWordMatrix;
    }

}
