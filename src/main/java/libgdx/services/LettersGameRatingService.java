package libgdx.services;

import libgdx.controls.popup.RatingPopup;
import libgdx.controls.popup.RatingService;
import libgdx.screen.AbstractScreen;

public class LettersGameRatingService extends RatingService {


    public LettersGameRatingService(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    protected RatingPopup createRatingPopup() {
        return null;
    }
}
