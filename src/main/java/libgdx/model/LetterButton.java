package libgdx.model;

import libgdx.controls.button.MyButton;

public class LetterButton {

    private String key;
    private String letter;
    private MyButton myButton;
    private float originalX;
    private float originalY;

    public LetterButton(String key, String letter, MyButton myButton) {
        this.key = key;
        this.letter = letter;
        this.myButton = myButton;
    }

    public MyButton getMyButton() {
        return myButton;
    }

    public String getLetter() {
        return letter;
    }

    public float getOriginalX() {
        return originalX;
    }

    public float getOriginalY() {
        return originalY;
    }

    public void setOriginalX(float originalX) {
        this.originalX = originalX;
    }

    public void setOriginalY(float originalY) {
        this.originalY = originalY;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMyButton(MyButton myButton) {
        this.myButton = myButton;
    }
}
