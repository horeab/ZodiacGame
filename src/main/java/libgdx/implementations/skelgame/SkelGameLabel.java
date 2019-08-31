package libgdx.implementations.skelgame;

import libgdx.game.Game;
import libgdx.resources.gamelabel.GameLabelUtils;
import libgdx.resources.gamelabel.SpecificPropertiesUtils;

public enum SkelGameLabel implements libgdx.resources.gamelabel.GameLabel {

    level_finished,
    level_failed,
    game_finished,
    go_back,
    next_level,
    play_again,

    determined,
    effective,
    ambitious,
    security,
    patience,
    communicative,
    intelligent,
    changeable,
    emotional,
    diplomatic,
    impulsive,
    warm,
    generous,
    faithful,
    analytical,
    observant,
    thoughtful,
    truth,
    beauty,
    perfection,
    restless,
    purposeful,
    philosophical,
    optimist,
    dominant,
    practical,
    intellectual,
    humanitarian,
    duplicitous,
    imagination,
    indecision,

    aries,
    leo,
    sagittarius,
    taurus,
    virgo,
    capricorn,
    gemini,
    libra,
    aquarius,
    cancer,
    scorpio,
    pisces,

    sun,
    mercury,
    venus,
    moon,
    mars,
    jupiter,
    saturn,
    uranus,
    neptune,
    pluto

    ;

    @Override
    public String getText(Object... params) {
        String language = Game.getInstance().getAppInfoService().getLanguage();
        return GameLabelUtils.getText(getKey(), language, GameLabelUtils.getLabelRes(language).getPath(), params);
    }

    @Override
    public String getKey() {
        return name().toLowerCase();
    }
}
