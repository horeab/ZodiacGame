package libgdx.screens.game.service.screenbackground;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.graphics.GraphicUtils;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;

public abstract class ScreenBackgroundCreatorService {


    public Table createBackgroundTable() {
        Table table = createAlContentTable();
        float verticalGeneralMarginDimen = MainDimen.vertical_general_margin.getDimen();
        Table contentTable = new Table();
        contentTable.add().growY().row();
        Table lettersTableContainer = new Table();
        contentTable.add(lettersTableContainer).growX().padBottom(verticalGeneralMarginDimen).row();
        Table lettersBackgroundTable = new Table();
        lettersBackgroundTable.setHeight(getLettersTableHeight());
        table.add(contentTable).grow().row();
        table.add(lettersBackgroundTable).height(lettersBackgroundTable.getHeight()).growX();
        return table;
    }

    abstract float getLettersTableHeight();

    abstract Res getAllBackgroundResource();

    private Table createAlContentTable() {
        Table table = new Table();
        table.setBackground(GraphicUtils.getNinePatch(getAllBackgroundResource()));
        table.setFillParent(true);
        return table;
    }
}
