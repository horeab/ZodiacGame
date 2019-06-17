package libgdx.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import libgdx.campaign.CampaignLevel;
import libgdx.screens.game.service.HintService;
import libgdx.screens.game.service.finalword.FinalWordService;
import libgdx.screens.game.service.keyboardletter.FinalWordKeyboardLetterService;
import libgdx.screens.game.service.keyboardletter.KeyboardLetterService;
import libgdx.screens.game.service.letters.FinalWordLettersToPressService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.extrabuttons.FinalWordExtraButtonsService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.FinalWordSubmitAndDeleteService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;

public class FinalWordContext extends Context {

    private static final int TOTAL_GAME_LETTERS_NR = 10;
    private String finalWord;

    private LettersToPressService finalWordLettersToPressService;
    private SubmitAndDeleteService finalWordSubmitAndDeleteService;
    private ExtraButtonsService finalWordShuffleAndBackspaceService;
    private FinalWordService finalWordService;
    private float boardSubmitDeleteShuffleBackspaceToPressButtonsY;

    private CampaignLevel campaignLevel;

    public FinalWordContext(int totalCrossWords,
                            int totalNrOfLetters,
                            CampaignLevel campaignLevel,
                            float boardSubmitDeleteShuffleBackspaceToPressButtonsY) {
        super(totalCrossWords, totalNrOfLetters);
        this.finalWord = getFinalWord(totalCrossWords, totalNrOfLetters);
        this.campaignLevel = campaignLevel;
        this.boardSubmitDeleteShuffleBackspaceToPressButtonsY = boardSubmitDeleteShuffleBackspaceToPressButtonsY;
    }

    private String getFinalWord(int totalCrossWords,
                                int totalNrOfLetters) {
        List<String> allWords = new ArrayList<>(new LettersGameService(totalCrossWords, totalNrOfLetters).getAllWords(false));
        String word = allWords.get(new Random().nextInt(allWords.size()));
        while (word.length() > TOTAL_GAME_LETTERS_NR || word.length() < 6) {
            word = allWords.get(new Random().nextInt(allWords.size()));
        }
        return word;
    }

    @Override
    public ExtraButtonsService getShuffleAndBackspaceService() {
        if (finalWordShuffleAndBackspaceService == null) {
            finalWordShuffleAndBackspaceService = new FinalWordExtraButtonsService(getLettersToPressService(), boardSubmitDeleteShuffleBackspaceToPressButtonsY);
        }
        return finalWordShuffleAndBackspaceService;
    }

    public FinalWordService getFinalWordService() {
        if (finalWordService == null) {
            finalWordService = new FinalWordService(finalWord);
        }
        return finalWordService;
    }

    @Override
    public SubmitAndDeleteService getSubmitAndDeleteService() {
        if (finalWordSubmitAndDeleteService == null) {
            finalWordSubmitAndDeleteService = new FinalWordSubmitAndDeleteService(
                    getLettersToPressService(),
                    finalWordService,
                    campaignLevel,
                    boardSubmitDeleteShuffleBackspaceToPressButtonsY
            );
        }
        return finalWordSubmitAndDeleteService;
    }

    @Override
    public LettersToPressService getLettersToPressService() {
        if (finalWordLettersToPressService == null) {
            List<String> gameLetters = getGameLetters();
            Collections.shuffle(gameLetters);
            finalWordLettersToPressService = new FinalWordLettersToPressService(gameLetters.size(), gameLetters, getFinalWordService().getFinalWordCells());
        }
        return finalWordLettersToPressService;
    }

    private List<String> getGameLetters() {
        List<String> gameLetters = Utils.textToStringChar(finalWord);
        List<String> alphabet = Utils.textToStringChar(Utils.ALPHABET);
        while (gameLetters.size() < TOTAL_GAME_LETTERS_NR) {
            gameLetters.add(alphabet.get(new Random().nextInt(alphabet.size())));
        }
        return gameLetters;
    }

    public Runnable onDestroyProcessControlsVisibility(final LettersToPressService lettersToPressService, final ExtraButtonsService shuffleAndBackspaceService) {
        return new Runnable() {
            @Override
            public void run() {
                shuffleAndBackspaceService.bringExtraButtonsToFront();
                lettersToPressService.showLetterButtons();
                getLettersToPressService().hideLetterButtons();
                getShuffleAndBackspaceService().hideExtraButtons();
                getSubmitAndDeleteService().hideSubmitDeleteBtn();
            }
        };
    }

    public Runnable onCreateProcessControlsVisibility(final LettersToPressService lettersToPressService, final SubmitAndDeleteService submitAndDeleteService, final ExtraButtonsService shuffleAndBackspaceService, final HintService hintService) {
        return new Runnable() {
            @Override
            public void run() {
                hintService.fadeOutDisplayedButton();
                shuffleAndBackspaceService.hideExtraButtons();
                lettersToPressService.hideLetterButtons();
                submitAndDeleteService.processSubmitDeleteBtnVisibility();
                getSubmitAndDeleteService().createSubmitDeleteBtn();
                getSubmitAndDeleteService().bringButtonsToFront();
                getShuffleAndBackspaceService().createExtraButtons();
                getShuffleAndBackspaceService().bringExtraButtonsToFront();
                getLettersToPressService().showLetterButtons();
                getLettersToPressService().bringButtonsToFront();
            }
        };
    }

    @Override
    public KeyboardLetterService getKeyboardLetterService(FinalWordContext finalWordContext) {
        if (keyboardLetterService == null) {
            keyboardLetterService = new FinalWordKeyboardLetterService(getLettersToPressService(), getShuffleAndBackspaceService(), getSubmitAndDeleteService(), finalWordContext);
        }
        return keyboardLetterService;
    }
}
