package libgdx.model;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.controls.label.MyLabel;
import libgdx.resources.FontManager;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;
import libgdx.resources.ResourcesManager;
import libgdx.resources.dimen.MainDimen;

public class FinalWordCell implements TextCell {

    public static final float LETTER_PAD_BOTTOM = MainDimen.vertical_general_margin.getDimen() / 2;
    private Table cell;
    private MyButton letterBtn;
    private String letter;

    public FinalWordCell(Table cell, String letter) {
        this.cell = cell;
        this.letter = letter;
        MyButton button = new ButtonBuilder(letter, FontManager.calculateMultiplierStandardFontSize(1.7f)).setButtonSkin(LettersGameButtonSkin.FINAL_WORD_UNKNOWN).setFixedButtonSize(LettersGameButtonSize.FINAL_WORD_BUTTON).build();
        button.getCenterRow().padBottom(LETTER_PAD_BOTTOM);
        float btnSideDimen = button.getHeight();
        if (button.getWidth() > button.getHeight()) {
            btnSideDimen = button.getWidth();
        }
        cell.add(button).width(btnSideDimen).height(btnSideDimen);
        this.letterBtn = button;
        setVisibleTextTable(false);
    }

    @Override
    public String getTextDisplayedForCell() {
        String text = "";
        if (isTextTableVisible()) {
            text = letter;
        }
        return text;
    }

    public void setVisibleTextTable(boolean visible) {
        if (visible) {
            letterBtn.setButtonSkin(LettersGameButtonSkin.FINAL_WORD_KNOWN);
        }
        getTextTable().setVisible(visible);
    }

    private Table getTextTable() {
        return (Table) letterBtn.findActor(ButtonBuilder.CENTER_TEXT_IMAGE_ROW_NAME);
    }

    public MyButton getLetterBtn() {
        return letterBtn;
    }

    public boolean isTextTableVisible() {
        return getTextTable().isVisible();
    }

    public String getLetter() {
        return letter;
    }

    public Table getCell() {
        return cell;
    }

}