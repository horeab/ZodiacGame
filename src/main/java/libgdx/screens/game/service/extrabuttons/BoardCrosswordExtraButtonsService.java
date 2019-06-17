package libgdx.screens.game.service.extrabuttons;

import libgdx.screens.game.service.Utils;
import libgdx.services.CrossWordContext;

public class BoardCrosswordExtraButtonsService extends CrosswordExtraButtonsService {

    public BoardCrosswordExtraButtonsService(CrossWordContext crossWordContext) {
        super(crossWordContext);
    }


    @Override
    public float getButtonsY() {
        return Utils.getBoardSubmitDeleteShuffleBackspaceToPressButtonsY();
    }

}
