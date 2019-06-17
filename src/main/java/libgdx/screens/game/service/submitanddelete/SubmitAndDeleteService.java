package libgdx.screens.game.service.submitanddelete;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.game.Game;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;
import libgdx.screen.AbstractScreen;
import libgdx.screens.game.service.WordAnimationService;
import libgdx.screens.game.service.letters.LettersToPressService;
import libgdx.utils.ScreenDimensionsManager;

public abstract class SubmitAndDeleteService {

    private MyButton deleteBtn;
    private MyButton submitBtn;

    WordAnimationService wordAnimationService;
    LettersToPressService lettersToPressService;

    SubmitAndDeleteService(LettersToPressService lettersToPressService) {
        this.lettersToPressService = lettersToPressService;
        this.wordAnimationService = new WordAnimationService();
    }

    abstract String getPressedWord();

    abstract boolean isSubmitBtnVisible();

    public abstract float getButtonsY();

    public abstract void onClickSubmitBtn();

    public abstract boolean processGameFinished();

    public void createSubmitDeleteBtn() {
        if (submitBtn == null && deleteBtn == null) {
            MyButton submitBtn = this.submitBtn = createSubmitBtn();
            MyButton deleteBtn = this.deleteBtn = createDeleteBtn();
            float y = getButtonsY();
            submitBtn.setY(y);
            submitBtn.setX(getCenterScreen());
            deleteBtn.setY(y);
            deleteBtn.setX(getCenterScreen());
            AbstractScreen abstractScreen = Game.getInstance().getAbstractScreen();
            abstractScreen.addActor(submitBtn);
            abstractScreen.addActor(deleteBtn);
        } else {
            submitBtn.setVisible(true);
            deleteBtn.setVisible(true);
        }
    }

    public void bringButtonsToFront() {
        submitBtn.toFront();
        deleteBtn.toFront();
    }

    public void hideSubmitDeleteBtn() {
        submitBtn.setVisible(false);
        deleteBtn.setVisible(false);
    }

    private float getCenterScreen() {
        return ScreenDimensionsManager.getScreenWidth() / 2 - LettersGameButtonSize.SUBMIT_DELETE_BUTTON.getWidth() / 2;
    }

    private MyButton createDeleteBtn() {
        MyButton deleteBtn = new ButtonBuilder().setButtonSkin(LettersGameButtonSkin.DELETE_BTN).setFixedButtonSize(LettersGameButtonSize.SUBMIT_DELETE_BUTTON).build();
        deleteBtn.addAction(Actions.fadeOut(0));
        deleteBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                lettersToPressService.movePressedLetterButtonsToOriginal();
            }
        });
        deleteBtn.setVisible(false);
        return deleteBtn;
    }

    private MyButton createSubmitBtn() {
        MyButton submitBtn = new ButtonBuilder().setButtonSkin(LettersGameButtonSkin.SUBMIT_BTN).setFixedButtonSize(LettersGameButtonSize.SUBMIT_DELETE_BUTTON).build();
        submitBtn.addAction(Actions.fadeOut(0));
        submitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onClickSubmitBtn();
            }
        });
        submitBtn.setVisible(false);
        return submitBtn;
    }

    public void processSubmitDeleteBtnVisibility() {
        if (deleteBtn != null && submitBtn != null) {
            processVisibility(lettersToPressService.areAnyButtonsPressed(), deleteBtn);
            boolean submitBtnVisible = isSubmitBtnVisible();
            if (submitBtnVisible) {
                float submitBtnMoveToX = getCenterScreen() - submitBtn.getWidth() / 1.6f;
                float deleteBtnMoveToX = getCenterScreen() + deleteBtn.getWidth() / 1.6f;
                if (submitBtn.getX() != submitBtnMoveToX) {
                    submitBtn.addAction(Actions.moveTo(submitBtnMoveToX, submitBtn.getY(), LettersToPressService.MOVE_BUTTON_DURATION));
                }
                if (deleteBtn.getX() != deleteBtnMoveToX) {
                    deleteBtn.addAction(Actions.moveTo(deleteBtnMoveToX, deleteBtn.getY(), LettersToPressService.MOVE_BUTTON_DURATION));
                }
            } else {
                float deleteBtnMoveToX = getCenterScreen();
                if (deleteBtn.getX() != deleteBtnMoveToX) {
                    deleteBtn.addAction(Actions.moveTo(deleteBtnMoveToX, deleteBtn.getY(), LettersToPressService.MOVE_BUTTON_DURATION));
                }
            }
            processVisibility(submitBtnVisible, submitBtn);
        }
    }

    private void processVisibility(boolean visible, MyButton myButton) {
        float duration = 0.1f;
        if (!visible) {
            myButton.addAction(Actions.sequence(Actions.fadeOut(duration)));
        }

        if (visible) {
            myButton.setVisible(true);
            myButton.addAction(Actions.sequence(Actions.fadeIn(duration)));
        }
    }

    public MyButton getDeleteBtn() {
        return deleteBtn;
    }

    public MyButton getSubmitBtn() {
        return submitBtn;
    }

}
