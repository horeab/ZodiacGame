package libgdx.screens.mainmenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.controls.popup.MyPopup;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;

class HowToPlayPopup extends MyPopup<AbstractScreen, AbstractScreenManager> {

    public HowToPlayPopup(AbstractScreen abstractScreen) {
        super(abstractScreen);
    }

    @Override
    public void addButtons() {
        MyButton backBtn = new ButtonBuilder().setButtonSkin(MainButtonSkin.DEFAULT).setText("OK").build();
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hide();
            }
        });
        addButton(backBtn);
    }

    @Override
    protected String getLabelText() {
        return "Keyboard controls:\n" +
                "- A-Z: Select letter\n" +
                "- ENTER: Enter word\n" +
                "- SPACE: Shuffle letters\n" +
                "- UP: Reselect last entered word\n" +
                "- BACKSPACE: Remove last selected letter\n" +
                "- DELETE: Remove all selected letters";
    }
}
