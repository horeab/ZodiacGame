package libgdx.screens.game.service.screenbackground;

import libgdx.resources.Resource;
import libgdx.services.CrossWordContext;
import libgdx.utils.ScreenDimensionsManager;

public class BoardCrosswordScreenBackgroundCreatorService extends ScreenBackgroundCreatorService {
    public BoardCrosswordScreenBackgroundCreatorService(CrossWordContext crossWordContext) {
    }

    @Override
    float getLettersTableHeight() {
        return ScreenDimensionsManager.getScreenHeightValue(25);
    }

    @Override
    Resource getAllBackgroundResource() {
        return Resource.board_in_game_window_background;
    }
}
