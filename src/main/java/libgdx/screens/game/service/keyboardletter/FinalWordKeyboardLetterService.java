package libgdx.screens.game.service.keyboardletter;

import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;
import libgdx.services.FinalWordContext;

public class FinalWordKeyboardLetterService extends KeyboardLetterService {

    public FinalWordKeyboardLetterService(LettersToPressService lettersToPressService,
                                          ExtraButtonsService shuffleAndBackspaceService,
                                          SubmitAndDeleteService submitAndDeleteService,
                                          FinalWordContext finalWordContext) {
        super(lettersToPressService, shuffleAndBackspaceService, submitAndDeleteService,finalWordContext);
    }

}
