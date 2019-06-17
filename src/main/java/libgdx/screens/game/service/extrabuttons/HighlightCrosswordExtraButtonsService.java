package libgdx.screens.game.service.extrabuttons;

import libgdx.screens.game.service.Utils;
import libgdx.services.CrossWordContext;

public class HighlightCrosswordExtraButtonsService extends CrosswordExtraButtonsService {

    public HighlightCrosswordExtraButtonsService(CrossWordContext crossWordContext) {
        super(crossWordContext);
    }

    @Override
    public float getButtonsY() {
        return Utils.getHighlightSubmitDeleteShuffleBackspaceToPressButtonsY();
    }
}
