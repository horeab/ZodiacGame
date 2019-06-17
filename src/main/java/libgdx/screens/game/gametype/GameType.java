package libgdx.screens.game.gametype;

import libgdx.screens.game.service.crossworddisplay.CrossWordDisplayService;
import libgdx.screens.game.service.crossworddisplay.HighlightCrossWordDisplayService;
import libgdx.screens.game.service.extrabuttons.ExtraButtonsService;
import libgdx.screens.game.service.extrabuttons.HighlightCrosswordExtraButtonsService;
import libgdx.screens.game.service.letters.HighlightCrosswordLettersToPressService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.screens.game.service.screenbackground.HighlightCrosswordScreenBackgroundCreatorService;
import libgdx.screens.game.service.screenbackground.ScreenBackgroundCreatorService;
import libgdx.screens.game.service.submitanddelete.HighlightCrosswordSubmitAndDeleteService;
import libgdx.screens.game.service.submitanddelete.SubmitAndDeleteService;
import libgdx.services.CrossWordContext;

public enum GameType {

    HIGHLIGHTED_CROSSWORD,
    FREE_CROSSWORD,
    BOARD_CROSSWORD;

    public LettersToPressService getLettersToPressService(CrossWordContext crossWordContext) {
        LettersToPressService service = null;
        if (this == HIGHLIGHTED_CROSSWORD) {
            service = new HighlightCrosswordLettersToPressService(crossWordContext);
        }
        return service;
    }

    public CrossWordDisplayService getCrossWordDisplayService(CrossWordContext crossWordContext) {
        CrossWordDisplayService service = null;
        if (this == HIGHLIGHTED_CROSSWORD) {
            service = new HighlightCrossWordDisplayService(crossWordContext);
        }
        return service;
    }

    public SubmitAndDeleteService getSubmitAndDeleteService(CrossWordContext crossWordContext) {
        SubmitAndDeleteService service = null;
        if (this == HIGHLIGHTED_CROSSWORD) {
            service = new HighlightCrosswordSubmitAndDeleteService(crossWordContext);
        }
        return service;
    }

    public ScreenBackgroundCreatorService getScreenBackgroundCreatorService() {
        ScreenBackgroundCreatorService service = null;
        if (this == HIGHLIGHTED_CROSSWORD) {
            service = new HighlightCrosswordScreenBackgroundCreatorService();
        }
        return service;
    }

    public ExtraButtonsService getExtraButtonsService(CrossWordContext crossWordContext) {
        ExtraButtonsService service = null;
        if (this == HIGHLIGHTED_CROSSWORD) {
            service = new HighlightCrosswordExtraButtonsService(crossWordContext);
        }
        return service;
    }
}
