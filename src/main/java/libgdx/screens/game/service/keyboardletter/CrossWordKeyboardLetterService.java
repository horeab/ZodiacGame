package libgdx.screens.game.service.keyboardletter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import libgdx.screens.game.service.HintService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;
import libgdx.services.FinalWordContext;

public class CrossWordKeyboardLetterService extends KeyboardLetterService {

    private HintService hintService;

    public CrossWordKeyboardLetterService(LettersToPressService lettersToPressService,
                                          ExtraButtonsService shuffleAndBackspaceService,
                                          SubmitAndDeleteService submitAndDeleteService,
                                          FinalWordContext finalWordContext,
                                          HintService hintService) {
        super(lettersToPressService, shuffleAndBackspaceService, submitAndDeleteService, finalWordContext);
        this.hintService = hintService;
    }

    @Override
    public void renderKeyPress() {
        super.renderKeyPress();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            pressButton(hintService.getDisplayedHintButton());
        }
    }
}
