package libgdx.implementations.iq;


import libgdx.constants.GameIdEnum;
import libgdx.game.Game;
import libgdx.game.ScreenManager;
import libgdx.game.external.AppInfoService;
import libgdx.game.external.BillingService;
import libgdx.game.external.FacebookService;
import libgdx.screens.AbstractScreen;

public class IqGame extends Game<AppInfoService,
        IqGameMainDependencyManager,
        IqGameDependencyManager,
        AbstractScreen,
        ScreenManager,
        GameIdEnum
        > {

    public IqGame(FacebookService facebookService,
                  BillingService billingService,
                  AppInfoService appInfoService) {
        super(facebookService, billingService, appInfoService, new IqGameMainDependencyManager());
    }

    public IqGameDependencyManager getDependencyManager() {
        return getSubGameDependencyManager();
    }

    public static IqGame getInstance() {
        return (IqGame) Game.getInstance();
    }

    @Override
    protected void displayScreenAfterAssetsLoad() {
        ScreenManager screenManager = getScreenManager();
        screenManager.showMainScreen();
    }
}
