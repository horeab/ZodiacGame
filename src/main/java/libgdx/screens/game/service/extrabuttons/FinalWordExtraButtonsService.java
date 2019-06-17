package libgdx.screens.game.service.extrabuttons;

import libgdx.screens.game.service.letters.LettersToPressService;

public class FinalWordExtraButtonsService extends ExtraButtonsService {

    private float buttonsY;

    public FinalWordExtraButtonsService(LettersToPressService lettersToPressService, float buttonsY) {
        super(lettersToPressService);
        this.buttonsY = buttonsY;
    }

    @Override
    public float getButtonsY() {
        return buttonsY;
    }
}
