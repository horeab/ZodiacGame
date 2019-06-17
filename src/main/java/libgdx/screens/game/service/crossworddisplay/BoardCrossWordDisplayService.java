package libgdx.screens.game.service.crossworddisplay;

import libgdx.services.CrossWordContext;
import libgdx.utils.ScreenDimensionsManager;

public class BoardCrossWordDisplayService extends CrossWordDisplayService {

    public BoardCrossWordDisplayService(CrossWordContext crossWordContext) {
        super(crossWordContext);
    }

    @Override
    float getMaxScreenHeight() {
        return ScreenDimensionsManager.getScreenHeightValue(43);
    }

}
