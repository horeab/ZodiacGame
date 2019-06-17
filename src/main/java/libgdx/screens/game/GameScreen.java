package libgdx.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Container;

import java.util.ArrayList;
import java.util.List;

import libgdx.campaign.CampaignLevel;
import libgdx.controls.ScreenRunnable;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.model.LetterButton;
import libgdx.screen.AbstractScreenManager;
import libgdx.screens.AbstractScreen;
import libgdx.screens.game.gametype.GameType;
import libgdx.screens.game.service.HintService;
import libgdx.screens.game.service.keyboardletter.KeyboardLetterService;
import libgdx.services.CrossWordContext;
import libgdx.services.FinalWordContext;
import libgdx.utils.SoundUtils;
import libgdx.utils.model.RGBColor;

public class GameScreen extends AbstractScreen {

    private static final GameType GAME_TYPE = GameType.HIGHLIGHTED_CROSSWORD;
    //    private int totalCrossWords = 10;
//    private int totalNrOfLetters = 16;
    private int totalCrossWords = 2;
    private int totalNrOfLetters = 4;
    private float lastTimeHintDisplayed = 0f;

    private CrossWordContext crossWordContext;
    private FinalWordContext finalWordContext;

    private CampaignLevel campaignLevel;

    private List<String[][]> alreadyPlayedMatrix = new ArrayList<>();

    public GameScreen(CampaignLevel campaignLevel) {
        this.campaignLevel = campaignLevel;
    }

    @Override
    public void buildStage() {
        setBackgroundColor(RGBColor.LIGHT_BLUE);
        totalNrOfLetters = totalNrOfLetters + (2 * campaignLevel.getIndex());

        final GameScreen abstractScreen = this;
        addActor(GAME_TYPE.getScreenBackgroundCreatorService().createBackgroundTable());
        final MyWrappedLabel loadingLabel = AbstractScreenManager.addLoadingLabel(this);
        new Thread(new ScreenRunnable(abstractScreen) {
            @Override
            public void executeOperations() {
                crossWordContext = new CrossWordContext(totalCrossWords, totalNrOfLetters, alreadyPlayedMatrix, GAME_TYPE);
                Gdx.app.postRunnable(new ScreenRunnable(abstractScreen) {
                    @Override
                    public void executeOperations() {
                        abstractScreen.addAction(AbstractScreenManager.removeLoadingLabelAction(abstractScreen, loadingLabel));
                        finalWordContext = new FinalWordContext(totalCrossWords, totalNrOfLetters, campaignLevel, crossWordContext.getSubmitAndDeleteService().getButtonsY());
                        for (LetterButton letterButton : crossWordContext.getLettersToPressService().getLetterButtons().values()) {
                            addActor(letterButton.getMyButton());
                        }
                        finalWordContext.getFinalWordService().addTries();
                        finalWordContext.getFinalWordService().addFinalWordLettersToScreen();
                        crossWordContext.getCrossWordDisplayService().createCrossWordMatrix();
                        crossWordContext.getShuffleAndBackspaceService().createExtraButtons();
                        crossWordContext.getSubmitAndDeleteService().createSubmitDeleteBtn();
                        finalWordContext.getFinalWordService().addListenersFinalWordCells(
                                finalWordContext.onCreateProcessControlsVisibility(crossWordContext.getLettersToPressService(), crossWordContext.getSubmitAndDeleteService(), crossWordContext.getShuffleAndBackspaceService(), crossWordContext.getHintService()),
                                finalWordContext.onDestroyProcessControlsVisibility(crossWordContext.getLettersToPressService(), crossWordContext.getShuffleAndBackspaceService()));
                        alreadyPlayedMatrix.add(crossWordContext.getCrossWordMatrix());
                        System.out.println(finalWordContext.getFinalWordService().getFinalWord());
                    }
                });
            }
        }).start();
    }

    public void goToNextStage() {
        finalWordContext.getFinalWordService().displayRandomLetter();
        if (!finalWordContext.getSubmitAndDeleteService().processGameFinished()) {
            totalCrossWords++;
            crossWordContext.getHintService().fadeOutDisplayedButton();

            final GameScreen abstractScreen = this;
            final MyWrappedLabel loadingLabel = AbstractScreenManager.addLoadingLabel(this);
            new Thread(new ScreenRunnable(abstractScreen) {
                @Override
                public void executeOperations() {
                    crossWordContext = new CrossWordContext(totalCrossWords, totalNrOfLetters, alreadyPlayedMatrix, GAME_TYPE);
                    Gdx.app.postRunnable(new ScreenRunnable(abstractScreen) {
                        @Override
                        public void executeOperations() {
                            abstractScreen.addAction(AbstractScreenManager.removeLoadingLabelAction(abstractScreen, loadingLabel));
                            for (LetterButton letterButton : crossWordContext.getLettersToPressService().getLetterButtons().values()) {
                                addActor(letterButton.getMyButton());
                            }
                            crossWordContext.getCrossWordDisplayService().createCrossWordMatrix();
                            crossWordContext.getShuffleAndBackspaceService().createExtraButtons();
                            crossWordContext.getSubmitAndDeleteService().createSubmitDeleteBtn();
                            finalWordContext.getFinalWordService().addListenersFinalWordCells(
                                    finalWordContext.onCreateProcessControlsVisibility(crossWordContext.getLettersToPressService(), crossWordContext.getSubmitAndDeleteService(), crossWordContext.getShuffleAndBackspaceService(), crossWordContext.getHintService()),
                                    finalWordContext.onDestroyProcessControlsVisibility(crossWordContext.getLettersToPressService(), crossWordContext.getShuffleAndBackspaceService()));
                            alreadyPlayedMatrix.add(crossWordContext.getCrossWordMatrix());
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        KeyboardLetterService keyboardLetterService = getKeyboardLetterService();
        if (keyboardLetterService != null) {
            keyboardLetterService.renderKeyPress();
        }
        if (getMillisPassedSinceScreenDisplayed() > lastTimeHintDisplayed + HintService.FADE_OUT_DURATION * 2 && crossWordContext != null && crossWordContext.getCrossWordMatrix().length > 0) {
            crossWordContext.getHintService().displayHint(finalWordContext.getFinalWordService().isFinalWordContextActive());
            lastTimeHintDisplayed = getMillisPassedSinceScreenDisplayed();
        }
    }

    private KeyboardLetterService getKeyboardLetterService() {
        if (finalWordContext != null && finalWordContext.getFinalWordService().isFinalWordContextActive()) {
            return finalWordContext.getKeyboardLetterService(finalWordContext);
        } else if (crossWordContext != null && finalWordContext != null && crossWordContext.getKeyboardLetterService(finalWordContext) != null) {
            return crossWordContext.getKeyboardLetterService(finalWordContext);
        }
        return null;
    }

    @Override
    protected void setBackgroundContainer(Container<Group> backgroundContainer) {
    }

    @Override
    public void onBackKeyPress() {
        if (finalWordContext != null && finalWordContext.getFinalWordService().isFinalWordContextActive()) {
            finalWordContext.getFinalWordService().destroyControlPanel(finalWordContext.onDestroyProcessControlsVisibility(crossWordContext.getLettersToPressService(), crossWordContext.getShuffleAndBackspaceService()));
        } else {
            screenManager.showCampaignScreen();
        }
    }

}
