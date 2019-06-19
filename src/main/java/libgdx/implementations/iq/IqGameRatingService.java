package libgdx.implementations.iq;

import libgdx.controls.popup.RatingPopup;
import libgdx.controls.popup.RatingService;
import libgdx.screen.AbstractScreen;

public class IqGameRatingService extends RatingService {


    public IqGameRatingService(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    protected RatingPopup createRatingPopup() {
        return null;
    }
}
