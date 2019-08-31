package libgdx.screens.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import libgdx.constants.Zodiac;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.game.ScreenManager;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.skelgame.SkelGameLabel;
import libgdx.implementations.skelgame.SkelGameRatingService;
import libgdx.implementations.skelgame.SkelGameSpecificResource;
import libgdx.resources.FontManager;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class MainMenuScreen extends AbstractScreen<ScreenManager> {

    private BirthDatePopup birthDatePopup;

    private Zodiac myZodiac;
    private Table allZodiacTable;

    public MainMenuScreen(Zodiac myZodiac) {
        this.myZodiac = myZodiac;
    }

    @Override
    public void buildStage() {
        if (myZodiac == null) {
            new SkelGameRatingService(this).appLaunched();
        }
        birthDatePopup = new BirthDatePopup(this, myZodiac == null);
        createAllZodiacTable();
    }

    public void setMyZodiac(Zodiac myZodiac) {
        this.myZodiac = myZodiac;
        allZodiacTable.addAction(Actions.sequence(Actions.fadeOut(0.5f), Utils.createRunnableAction(new Runnable() {
            @Override
            public void run() {
                createAllZodiacTable();
            }
        })));
    }

    private void createAllZodiacTable() {
        allZodiacTable = new Table();
        allZodiacTable.setFillParent(true);
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        String text = myZodiac == null ? "Select your sign" : "Select your partner sign";
        MyWrappedLabel label = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText(text).setFontScale(FontManager.getBigFontDim()).build());
        allZodiacTable.add(label).pad(marginDimen).colspan(3).row();
        for (Zodiac zodiac : Zodiac.values()) {
            allZodiacTable.add(createZodiacTable(zodiac)).pad(marginDimen);
            if ((zodiac.ordinal() + 1) % 3 == 0) {
                allZodiacTable.row();
            }
        }
        allZodiacTable.row();
        allZodiacTable.add(new MyWrappedLabel("OR")).pad(marginDimen * 3).colspan(3).row();
        MyButton useBirthDateButton = new ButtonBuilder()
                .setSingleLineText("Birth date", FontManager.getSmallFontDim()).setDefaultButton().build();

        useBirthDateButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                birthDatePopup.addToPopupManager();
            }
        });
        allZodiacTable.add(useBirthDateButton)
                .width(marginDimen * 30).height(marginDimen * 6).colspan(3);
        allZodiacTable.setVisible(false);
        allZodiacTable.addAction(Actions.sequence(Actions.fadeOut(0f), Utils.createRunnableAction(new Runnable() {
            @Override
            public void run() {
                allZodiacTable.setVisible(true);
            }
        }), Actions.fadeIn(0.5f)));
        addActor(allZodiacTable);
    }

    private Table createZodiacTable(final Zodiac zodiac) {
        Table table = new Table();
        table.setTouchable(Touchable.enabled);
        table.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (myZodiac == null) {
                    setMyZodiac(zodiac);
                }
            }
        });
        table.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        float marginDimen = MainDimen.horizontal_general_margin.getDimen();
        float zIconDimen = marginDimen * 7;
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setFontScale(FontManager.getSmallFontDim())
                .setText(StringUtils.capitalize(SkelGameLabel.valueOf(zodiac.name()).getText())).build())).width(zIconDimen).row();
        table.add(GraphicUtils.getImage(SkelGameSpecificResource.valueOf(zodiac.name()))).pad(marginDimen).height(zIconDimen / 1.1f).width(zIconDimen);
        return table;

    }

    @Override
    public void onBackKeyPress() {
        Gdx.app.exit();
    }

}
