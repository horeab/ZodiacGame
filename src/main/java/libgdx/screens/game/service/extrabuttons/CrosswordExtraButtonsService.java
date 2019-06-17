package libgdx.screens.game.service.extrabuttons;

import libgdx.services.CrossWordContext;

public abstract class CrosswordExtraButtonsService extends ExtraButtonsService {

    CrosswordExtraButtonsService(CrossWordContext crossWordContext) {
        super(crossWordContext.getLettersToPressService());
    }

}
