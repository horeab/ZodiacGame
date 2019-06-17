package libgdx.services;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;

public class BackButtonBuilder  extends ButtonBuilder{

    @Override
    public MyButton build() {
        MyButton goBackBtn = new ButtonBuilder().setButtonSkin(LettersGameButtonSkin.BACK).setFixedButtonSize(LettersGameButtonSize.SUBMIT_DELETE_BUTTON).build();
        goBackBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Game.getInstance().getAbstractScreen().onBackKeyPress();
            }
        });
        return goBackBtn;
    }
}
