package libgdx.screens.game.service.letters;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MyButton;
import libgdx.model.LetterButton;
import libgdx.resources.FontManager;
import libgdx.resources.LettersGameButtonSize;
import libgdx.resources.LettersGameButtonSkin;

public class LetterButtonCreator {

    private LettersGameButtonSize lettersGameButtonSize;
    private LettersGameButtonSkin lettersGameButtonSkin;
    private List<Pair<Float, Float>> lettersCoordList;

    LetterButtonCreator(LettersGameButtonSize lettersGameButtonSize, LettersGameButtonSkin lettersGameButtonSkin, List<Pair<Float, Float>> lettersCoordList) {
        this.lettersGameButtonSize = lettersGameButtonSize;
        this.lettersGameButtonSkin = lettersGameButtonSkin;
        this.lettersCoordList = lettersCoordList;
    }

    public Map<String, LetterButton> createLetterButtons(List<String> gameLetters) {
        Map<String, LetterButton> letterButtons = new HashMap<>();
        int i = 0;
        for (final String letter : gameLetters) {
            String btnKey = getLetterBtnKey(letterButtons, letter);
            LetterButton letterBtn = createLetterBtn(letter, btnKey);
            letterButtons.put(btnKey, letterBtn);
            MyButton myButton = letterBtn.getMyButton();
            Float x = lettersCoordList.get(i).getKey();
            Float y = lettersCoordList.get(i).getValue();
            myButton.setX(x);
            myButton.setY(y);
            letterBtn.setOriginalX(x);
            letterBtn.setOriginalY(y);
            i++;
        }
        return letterButtons;
    }

    private LetterButton createLetterBtn(final String letter, final String buttonKey) {
        MyButton button = new ButtonBuilder(letter, FontManager.getBigFontDim())
                .setFixedButtonSize(lettersGameButtonSize)
                .setButtonSkin(lettersGameButtonSkin)
                .build();
        button.setTransform(true);
        return new LetterButton(buttonKey, letter, button);
    }

    private String getLetterBtnKey(Map<String, LetterButton> letterButtons, String letter) {
        int i = 0;
        String key = letter + i;
        while (letterButtons.containsKey(key)) {
            i++;
            key = letter + i;
        }
        return key;
    }
}
