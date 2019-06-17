package libgdx.resources;

import com.badlogic.gdx.graphics.Texture;

import libgdx.game.Game;

public enum Resource implements Res {

    // @formatter:off

    hint_btn_up("game/buttons/hint_btn_up.png", Texture.class),
    hint_btn_down("game/buttons/hint_btn_down.png", Texture.class),

    back_btn_up("game/buttons/back_btn_up.png", Texture.class),
    back_btn_down("game/buttons/back_btn_down.png", Texture.class),
    shuffle_btn_up("game/buttons/shuffle_btn_up.png", Texture.class),
    shuffle_btn_down("game/buttons/shuffle_btn_down.png", Texture.class),
    submit_btn_up("game/buttons/submit_btn_up.png", Texture.class),
    submit_btn_down("game/buttons/submit_btn_down.png", Texture.class),
    backspace_btn_up("game/buttons/backspace_btn_up.png", Texture.class),
    backspace_btn_down("game/buttons/backspace_btn_down.png", Texture.class),
    delete_btn_up("game/buttons/delete_btn_up.png", Texture.class),
    delete_btn_down("game/buttons/delete_btn_down.png", Texture.class),

    title_background("game/title_background.png", Texture.class),
    title_rays("game/title_rays.png", Texture.class),
    question_mark("game/question_mark.png", Texture.class),
    free_play("game/question_mark.png", Texture.class),
    rocket("game/rocket.png", Texture.class),
    flower("game/flower.png", Texture.class),
    trophy("game/trophy.png", Texture.class),
    press_finger("game/press_finger.png", Texture.class),

    letter_btn_up("game/buttons/letter_btn_up.png", Texture.class),
    letter_btn_down("game/buttons/letter_btn_down.png", Texture.class),
    letter_btn_hint_down("game/buttons/letter_btn_hint_down.png", Texture.class),
    letter_btn_hint_up("game/buttons/letter_btn_hint_up.png", Texture.class),
    transparent_btn("game/buttons/transparent_btn.png", Texture.class),

    final_word_known("game/crossword/final_word_known.png", Texture.class),
    final_word_unknown("game/crossword/final_word_unknown.png", Texture.class),
    final_word_unknown_press("game/crossword/final_word_unknown_press.png", Texture.class),
    crossword_horizontal("game/crossword/crossword_horizontal.png", Texture.class),
    crossword_vertical("game/crossword/crossword_vertical.png", Texture.class),
    crossword_cross("game/crossword/crossword_cross.png", Texture.class),
    found_all("game/crossword/found_all.png", Texture.class),
    remaining_horizontal("game/crossword/remaining_horizontal.png", Texture.class),
    remaining_more("game/crossword/remaining_more.png", Texture.class),
    remaining_vertical("game/crossword/remaining_vertical.png", Texture.class),

    btn_background_circle_up("game/buttons/btn_background_circle_up.png", Texture.class),
    btn_background_circle_down("game/buttons/btn_background_circle_down.png", Texture.class),
    btn_mainmenu_btn_up("game/buttons/mainmenu_btn_up.png", Texture.class),
    btn_mainmenu_btn_down("game/buttons/mainmenu_btn_down.png", Texture.class),

    board_in_game_window_background("game/board_in_game_window_background.png", Texture.class),
    highlight_in_game_window_background("game/highlight_in_game_window_background.png", Texture.class),

    gray_background("game/gray_background.png", Texture.class),
    final_word_stars_background("game/final_word_stars_background.png", Texture.class),
    final_word_background("game/final_word_background.png", Texture.class),

    fill_table("game/fill_table.png", Texture.class),

    campaign_screen_background("game/campaign_screen_background.png", Texture.class),

    //CAMPAIGN///////////////////////////////////////////////////////////////////////////////////////
    stars_table_background("game/campaign/stars_table_background.png", Texture.class),
    star_disabled("game/campaign/star_disabled.png", Texture.class),
    star_enabled("game/campaign/star_enabled.png", Texture.class),
    bomb("game/campaign/bomb.png", Texture.class),
    fire("game/campaign/fire.png", Texture.class),
    campaign_wall_explosion("game/campaign/campaign_wall_explosion.png", Texture.class),
    table_left_right_dotted_line("game/campaign/connectinglines/table_left_right_dotted_line.png", Texture.class),
    table_right_left_dotted_line("game/campaign/connectinglines/table_right_left_dotted_line.png", Texture.class),
    table_down_center_left_dotted_line("game/campaign/connectinglines/table_down_center_left_dotted_line.png", Texture.class),
    table_up_center_left_dotted_line("game/campaign/connectinglines/table_up_center_left_dotted_line.png", Texture.class),
    table_center_right_dotted_line("game/campaign/connectinglines/table_center_right_dotted_line.png", Texture.class),
    btn_campaign_disabled("game/campaign/buttons/btn_campaign_disabled.png", Texture.class),
    btn_current_level_down("game/campaign/buttons/btn_current_level_down.png", Texture.class),
    btn_current_level_up("game/campaign/buttons/btn_current_level_up.png", Texture.class),
    btn_campaign_0("game/campaign/buttons/btn_campaign_0.png", Texture.class),
    btn_campaign_1("game/campaign/buttons/btn_campaign_1.png", Texture.class),
    btn_campaign_2("game/campaign/buttons/btn_campaign_2.png", Texture.class),
    btn_campaign_3("game/campaign/buttons/btn_campaign_3.png", Texture.class),
    btn_campaign_4("game/campaign/buttons/btn_campaign_4.png", Texture.class),
    btn_campaign_5("game/campaign/buttons/btn_campaign_5.png", Texture.class),
    ///////////////////////////////////
    campaign_level_0_background("game/campaign/background/level_0_background.png", Texture.class),
    campaign_level_1_background("game/campaign/background/level_1_background.png", Texture.class),
    campaign_level_2_background("game/campaign/background/level_2_background.png", Texture.class),
    campaign_level_3_background("game/campaign/background/level_3_background.png", Texture.class),
    campaign_level_4_background("game/campaign/background/level_4_background.png", Texture.class),
    campaign_level_5_background("game/campaign/background/level_5_background.png", Texture.class),;
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
