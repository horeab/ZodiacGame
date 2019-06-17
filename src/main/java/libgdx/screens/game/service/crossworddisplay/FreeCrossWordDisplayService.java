package libgdx.screens.game.service.crossworddisplay;

import libgdx.services.CrossWordContext;
import libgdx.utils.ScreenDimensionsManager;

public class FreeCrossWordDisplayService extends CrossWordDisplayService {

    public FreeCrossWordDisplayService(CrossWordContext crossWordContext) {
        super(crossWordContext);
    }

    @Override
    float getMaxScreenHeight() {
        return ScreenDimensionsManager.getScreenHeightValue(50);
    }


}
