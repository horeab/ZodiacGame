package libgdx.game;


import libgdx.constants.GameIdEnum;
import libgdx.game.external.AppInfoService;
import libgdx.game.external.BillingService;
import libgdx.game.external.FacebookService;
import libgdx.game.external.LoginService;
import libgdx.implementations.letter.LettersGameDependencyManager;
import libgdx.screens.AbstractScreen;
import libgdx.services.ScreenManager;

public class LettersGame extends Game<AppInfoService,
        LettersGameMainDependencyManager,
        LettersGameDependencyManager,
        AbstractScreen,
        ScreenManager,
        GameIdEnum
        > {

    public LettersGame(FacebookService facebookService,
                       BillingService billingService,
                       AppInfoService appInfoService) {
        super(facebookService, billingService, appInfoService, new LettersGameMainDependencyManager());
    }

    public LettersGameDependencyManager getDependencyManager() {
        return getSubGameDependencyManager();
    }

    public static LettersGame getInstance() {
        return (LettersGame) Game.getInstance();
    }

    @Override
    protected void displayScreenAfterAssetsLoad() {
        ScreenManager screenManager = getScreenManager();
        screenManager.showMainScreen();
    }
}
