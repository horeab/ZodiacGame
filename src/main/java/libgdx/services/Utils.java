package libgdx.services;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

import libgdx.controls.button.MyButton;
import libgdx.screens.game.service.crossworddisplay.CrossWordDisplayService;

public class Utils {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static void scaleDownButton(MyButton button) {
        scaleButton(button, -1);
    }

    public static void scaleUpButton(MyButton button) {
        scaleButton(button, 1);
    }

    private static void scaleButton(MyButton button, int i) {
        button.setOrigin(Align.center);
        button.addAction(Actions.sequence(Actions.scaleBy(i, i, CrossWordDisplayService.scaleDownDuration())));
    }

    public static RunnableAction createRunnableAction(Runnable runnable) {
        RunnableAction removeActorAction = new RunnableAction();
        removeActorAction.setRunnable(runnable);
        return removeActorAction;
    }

    public static List<String> textToStringChar(String text) {
        char[] chars = text.toCharArray();
        List<String> standardLetter = new ArrayList<>();
        for (char c : chars) {
            standardLetter.add(Character.toString(c));
        }
        return standardLetter;
    }


    public static RunnableAction createRemoveActorAction(final Actor actor) {
        return createRunnableAction(new Runnable() {
            @Override
            public void run() {
                actor.remove();
            }
        });
    }
}
