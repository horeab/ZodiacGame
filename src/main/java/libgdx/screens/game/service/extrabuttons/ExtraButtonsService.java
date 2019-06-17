package libgdx.screens.game.service.extrabuttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.model.LetterButton;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.services.BackButtonBuilder;
import libgdx.utils.ScreenDimensionsManager;

public abstract class ExtraButtonsService {

    private MyButton backspaceBtn;
    private MyButton shuffleBtn;
    private MyButton goBackBtn;

    private LettersToPressService lettersToPressService;

    ExtraButtonsService(LettersToPressService lettersToPressService) {
        this.lettersToPressService = lettersToPressService;
    }

    public abstract float getButtonsY();

    public void bringExtraButtonsToFront() {
        shuffleBtn.setVisible(true);
        shuffleBtn.toFront();
        goBackBtn.setVisible(true);
        goBackBtn.toFront();
    }

    public void createExtraButtons(){
        createShuffleButton();
        createGoBackButton();
    }

    private void createShuffleButton() {
        if (shuffleBtn == null) {
            shuffleBtn = new ButtonBuilder().setButtonSkin(LettersGameButtonSkin.SHUFFLE_BTN).setFixedButtonSize(LettersGameButtonSize.SUBMIT_DELETE_BUTTON).build();
            shuffleBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    List<Pair<Float, Float>> coordList = lettersToPressService.getLettersCoordList();
                    Map<String, LetterButton> letterButtons = lettersToPressService.getLetterButtons();
                    List<String> keys = new ArrayList<String>(letterButtons.keySet());
                    Collections.shuffle(keys);
                    int i = 0;
                    for (String key : keys) {
                        LetterButton letterButton = letterButtons.get(key);
                        Pair<Float, Float> coords = coordList.get(i);
                        letterButton.setOriginalX(coords.getKey());
                        letterButton.setOriginalY(coords.getValue());
                        letterButton.getMyButton().addAction(Actions.moveTo(letterButton.getOriginalX(), letterButton.getOriginalY(), 0.5f));
                        i++;
                    }
                    lettersToPressService.movePressedLetterButtonsToOriginal();
                }
            });
            shuffleBtn.setX(ScreenDimensionsManager.getScreenWidth() - MainDimen.horizontal_general_margin.getDimen() - LettersGameButtonSize.SUBMIT_DELETE_BUTTON.getWidth());
            shuffleBtn.setY(getButtonsY());
            Game.getInstance().getAbstractScreen().addActor(shuffleBtn);
        } else {
            shuffleBtn.setVisible(true);
        }
    }

    private void createGoBackButton() {
        if (goBackBtn == null) {
            goBackBtn = new BackButtonBuilder().build();
            goBackBtn.setX(MainDimen.horizontal_general_margin.getDimen());
            goBackBtn.setY(getButtonsY());
            Game.getInstance().getAbstractScreen().addActor(goBackBtn);
        } else {
            goBackBtn.setVisible(true);
        }
    }

    private void createBackspaceButton() {
        if (backspaceBtn == null) {
            backspaceBtn = new ButtonBuilder().setButtonSkin(LettersGameButtonSkin.BACKSPACE_BTN).setFixedButtonSize(LettersGameButtonSize.SUBMIT_DELETE_BUTTON).build();
            backspaceBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    lettersToPressService.moveLastPressedLetterButtonBackToOriginal();
                }
            });
            backspaceBtn.setX(ScreenDimensionsManager.getScreenWidth() - MainDimen.horizontal_general_margin.getDimen() - LettersGameButtonSize.SUBMIT_DELETE_BUTTON.getWidth());
            backspaceBtn.setY(getButtonsY());
            Game.getInstance().getAbstractScreen().addActor(backspaceBtn);
        } else {
            backspaceBtn.setVisible(true);
        }
    }

    public void hideExtraButtons() {
        shuffleBtn.setVisible(false);
        goBackBtn.setVisible(false);
    }
}
