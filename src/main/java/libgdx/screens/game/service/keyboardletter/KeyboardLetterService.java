package libgdx.screens.game.service.keyboardletter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.HashMap;
import java.util.Map;

import libgdx.controls.button.MyButton;
import libgdx.model.LetterButton;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;
import libgdx.services.FinalWordContext;
import libgdx.services.LettersGameService;

public abstract class KeyboardLetterService {

    private Map<Integer, String> keysWithLetters = new HashMap<>();

    private LettersGameService lettersGameService;
    private LettersToPressService lettersToPressService;
    private SubmitAndDeleteService submitAndDeleteService;
    private FinalWordContext finalWordContext;

    KeyboardLetterService(LettersToPressService lettersToPressService,
                          ExtraButtonsService shuffleAndBackspaceService,
                          SubmitAndDeleteService submitAndDeleteService,
                          FinalWordContext finalWordContext) {
        prepareKeysWithLetters();
        this.lettersToPressService = lettersToPressService;
        this.submitAndDeleteService = submitAndDeleteService;
        this.finalWordContext = finalWordContext;
        this.lettersGameService = new LettersGameService(finalWordContext.getTotalCrossWords(), finalWordContext.getTotalNrOfLetters());
    }

    public void renderKeyPress() {
        for (Map.Entry<Integer, String> entry : keysWithLetters.entrySet()) {
            int index = 0;
            int amountOfElemInList = lettersGameService.getAmountOfElemInList(lettersToPressService.getPressedLetters(), entry.getValue());
            while (index <= amountOfElemInList) {
                String buttonKeyToPress = entry.getValue() + index;
                index++;
                LetterButton letterButton = lettersToPressService.getLetterButtons().get(buttonKeyToPress);
                if (letterButton != null && letterButton.getMyButton().getTouchable() != Touchable.disabled && !letterButton.getMyButton().isPressed() && Gdx.input.isKeyJustPressed(entry.getKey())) {
                    pressButton(letterButton.getMyButton());
                    break;
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            pressButton(submitAndDeleteService.getSubmitBtn());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.FORWARD_DEL)) {
            pressButton(submitAndDeleteService.getDeleteBtn());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            pressButton(finalWordContext.getFinalWordService().getFinalWordCells().get(0).getLetterBtn());
        }
        submitAndDeleteService.processSubmitDeleteBtnVisibility();
    }

    private void prepareKeysWithLetters() {
        keysWithLetters.put(Input.Keys.A, "a");
        keysWithLetters.put(Input.Keys.B, "b");
        keysWithLetters.put(Input.Keys.C, "c");
        keysWithLetters.put(Input.Keys.D, "d");
        keysWithLetters.put(Input.Keys.E, "e");
        keysWithLetters.put(Input.Keys.F, "f");
        keysWithLetters.put(Input.Keys.G, "g");
        keysWithLetters.put(Input.Keys.H, "h");
        keysWithLetters.put(Input.Keys.I, "i");
        keysWithLetters.put(Input.Keys.J, "j");
        keysWithLetters.put(Input.Keys.K, "k");
        keysWithLetters.put(Input.Keys.L, "l");
        keysWithLetters.put(Input.Keys.M, "m");
        keysWithLetters.put(Input.Keys.N, "n");
        keysWithLetters.put(Input.Keys.O, "o");
        keysWithLetters.put(Input.Keys.P, "p");
        keysWithLetters.put(Input.Keys.Q, "q");
        keysWithLetters.put(Input.Keys.R, "r");
        keysWithLetters.put(Input.Keys.S, "s");
        keysWithLetters.put(Input.Keys.T, "t");
        keysWithLetters.put(Input.Keys.U, "u");
        keysWithLetters.put(Input.Keys.V, "v");
        keysWithLetters.put(Input.Keys.W, "w");
        keysWithLetters.put(Input.Keys.X, "x");
        keysWithLetters.put(Input.Keys.Y, "y");
        keysWithLetters.put(Input.Keys.Z, "z");
    }

    void pressButton(MyButton button) {
        if (button != null) {
            InputEvent event = new InputEvent();
            event.setType(InputEvent.Type.touchDown);
            button.fire(event);
            event.setType(InputEvent.Type.touchUp);
            button.fire(event);
        }
    }
}
