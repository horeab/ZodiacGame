package libgdx.resources;

import com.badlogic.gdx.graphics.Texture;

import libgdx.game.Game;

public enum Resource implements Res {

    // @formatter:off

    title_background("game/title_background.png", Texture.class),
    title_rays("game/title_rays.png", Texture.class),

    //CAMPAIGN///////////////////////////////////////////////////////////////////////////////////////
    stars_table_background("game/campaign/stars_table_background.png", Texture.class),
    star_disabled("game/campaign/star_disabled.png", Texture.class),
    star_enabled("game/campaign/star_enabled.png", Texture.class),
    btn_campaign_disabled("game/campaign/buttons/btn_campaign_disabled.png", Texture.class),
    btn_current_level_down("game/campaign/buttons/btn_current_level_down.png", Texture.class),
    btn_current_level_up("game/campaign/buttons/btn_current_level_up.png", Texture.class),;
    // @formatter:on

    private String path;
    private Class<?> classType;

    Resource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public String getPath() {
        return Game.getInstance().getAppInfoService().getResourcesFolder() + path;
    }

    public String getRawPath() {
        return path;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }


}
