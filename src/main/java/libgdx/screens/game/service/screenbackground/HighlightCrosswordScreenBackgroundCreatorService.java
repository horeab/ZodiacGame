package libgdx.screens.game.service.screenbackground;

import libgdx.resources.Resource;
import libgdx.services.CrossWordContext;
import libgdx.utils.ScreenDimensionsManager;

public class HighlightCrosswordScreenBackgroundCreatorService extends ScreenBackgroundCreatorService {
    public HighlightCrosswordScreenBackgroundCreatorService() {
    }

    @Override
    float getLettersTableHeight() {
        return ScreenDimensionsManager.getScreenHeightValue(13);
    }

    @Override
    Resource getAllBackgroundResource() {
        return Resource.highlight_in_game_window_background;
    }
}
