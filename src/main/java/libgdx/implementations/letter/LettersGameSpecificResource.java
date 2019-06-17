package libgdx.implementations.letter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.game.Game;
import libgdx.resources.SpecificResource;

public enum LettersGameSpecificResource implements SpecificResource {

    // @formatter:off
    specific_labels("labels/labels", I18NBundle.class),

    campaign_level_0_0("campaign/levels/l_0/level_0_0.png", Texture.class),
    campaign_level_0_1("campaign/levels/l_0/level_0_1.png", Texture.class),
    campaign_level_0_2("campaign/levels/l_0/level_0_2.png", Texture.class),
    campaign_level_0_3("campaign/levels/l_0/level_0_3.png", Texture.class),
    campaign_level_0_4("campaign/levels/l_0/level_0_4.png", Texture.class),
    campaign_level_0_5("campaign/levels/l_0/level_0_5.png", Texture.class),
    ;
    // @formatter:on

    private String path;
    private Class<?> classType;

    LettersGameSpecificResource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public String getPath() {
        return Game.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + path;
    }

}
