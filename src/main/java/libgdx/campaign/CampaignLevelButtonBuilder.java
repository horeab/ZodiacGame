package libgdx.campaign;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import org.apache.commons.lang3.StringUtils;

import libgdx.controls.animations.ActorAnimation;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.labelimage.LabelImage;
import libgdx.game.Game;
import libgdx.game.LettersGame;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.FontManager;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.resources.Resource;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.AbstractScreen;

public class CampaignLevelButtonBuilder extends ButtonBuilder {

    private CampaignStoreLevel level;
    private CampaignLevel levelEnum;
    private float fontDimen = FontManager.calculateMultiplierStandardFontSize(0.8f);
    private boolean levelLocked;
    private CampaignLevelEnumService campaignLevelEnumService;

    public CampaignLevelButtonBuilder(CampaignLevel levelEnum, CampaignStoreLevel level) {
        this.level = level;
        this.levelEnum = levelEnum;
        this.campaignLevelEnumService = new CampaignLevelEnumService(levelEnum);
    }

    public CampaignLevelButtonBuilder setLevelLocked(boolean levelLocked) {
        this.levelLocked = levelLocked;
        return this;
    }

    @Override
    public MyButton build() {
        setFixedButtonSize(LettersGameButtonSize.CAMPAIGN_LEVEL_ROUND_IMAGE);
        LettersGameButtonSkin buttonSkin = LettersGameButtonSkin.CAMPAIGN_LOCKED_LEVEL;
        if (level != null) {
            buttonSkin = level.getStatus() == CampaignLevelStatusEnum.FINISHED.getStatus() ? campaignLevelEnumService.getButtonSkin() : LettersGameButtonSkin.CAMPAIGN_CURRENT_LEVEL;
        }
        setButtonSkin(buttonSkin);
        addLevelInfo();
        addClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LettersGame.getInstance().getScreenManager().showGameScreen(levelEnum);
            }
        });
        MyButton myButton = super.build();
        myButton.setDisabled(levelLocked);
        if(buttonSkin==LettersGameButtonSkin.CAMPAIGN_CURRENT_LEVEL){
            myButton.setTransform(true);
            new ActorAnimation(myButton, Game.getInstance().getAbstractScreen()).animateZoomInZoomOut();
        }
        return myButton;
    }

    private void addLevelInfo() {
        Table table = new Table();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        if (level != null && level.getStatus() == CampaignLevelStatusEnum.FINISHED.getStatus()) {
            Table starsBar = createStarsBar();
            table.add(starsBar).height(starsBar.getHeight()).width(starsBar.getWidth()).padBottom(verticalGeneralMarginDimen / 2).row();
        }
        if (!levelLocked && StringUtils.isNotBlank(campaignLevelEnumService.getLabelText())) {
            table.add(createTextTable()).padTop(verticalGeneralMarginDimen / 2).row();
        }
        addCenterTextImageColumn(table);
    }

    private LabelImage createTextTable() {
        LabelImage textTable = createTextTable(campaignLevelEnumService.getLabelText(), MainDimen.horizontal_general_margin.getDimen() * 17, fontDimen);
        textTable.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        return textTable;
    }

    private Table createStarsBar() {
        Table table = new StarsBarCreator(level.getStarsWon()).createHorizontalStarsBar(MainDimen.vertical_general_margin.getDimen() * 3f);
        table.setBackground(GraphicUtils.getNinePatch(Resource.stars_table_background));
        return table;
    }
}
