package libgdx.constants;

import libgdx.implementations.skelgame.SkelGameLabel;

import java.util.Arrays;
import java.util.List;

import static libgdx.constants.Element.*;
import static libgdx.implementations.skelgame.SkelGameLabel.*;

public enum Zodiac {

    aries(ZodiacComp.aries, fire, Planet.mars, Arrays.asList(determined, effective, ambitious)),
    leo(ZodiacComp.leo, fire, Planet.sun, Arrays.asList(warm, generous, faithful)),
    sagittarius(ZodiacComp.sagittarius, fire, Planet.jupiter, Arrays.asList(philosophical, optimist)),

    taurus(ZodiacComp.taurus, earth, Planet.venus, Arrays.asList(security, patience)),
    virgo(ZodiacComp.virgo, earth, Planet.mercury, Arrays.asList(analytical, observant, thoughtful)),
    capricorn(ZodiacComp.capricorn, earth, Planet.saturn, Arrays.asList(determined, dominant, practical)),

    gemini(ZodiacComp.gemini, wind, Planet.mercury, Arrays.asList(communicative, changeable, intelligent)),
    libra(ZodiacComp.libra, wind, Planet.venus, Arrays.asList(truth, beauty, perfection)),
    aquarius(ZodiacComp.aquarius, wind, Planet.uranus, Arrays.asList(intellectual, humanitarian, duplicitous)),

    cancer(ZodiacComp.cancer, water, Planet.moon, Arrays.asList(emotional, diplomatic, impulsive)),
    scorpio(ZodiacComp.scorpio, water, Planet.pluto, Arrays.asList(purposeful, restless)),
    pisces(ZodiacComp.pisces, water, Planet.neptune, Arrays.asList(imagination, indecision)),
    ;

    private ZodiacComp zodiacComp;
    private Element element;
    private Planet planet;
    private List<SkelGameLabel> attrs;

    Zodiac(ZodiacComp zodiacComp, Element element, Planet planet, List<SkelGameLabel> attrs) {
        this.zodiacComp = zodiacComp;
        this.element = element;
        this.planet = planet;
        this.attrs = attrs;
    }

    public static Zodiac getZodiac(int day, int month) {
        Zodiac sign = null;
        if (month == 1) {
            if (day < 20)
                sign = capricorn;
            else if (day < 31)
                sign = aquarius;
        } else if (month == 2) {
            if (day < 19)
                sign = aquarius;
            else if (day < 29)
                sign = pisces;
        } else if (month == 3) {
            if (day < 21)
                sign = pisces;
            else if (day < 31)
                sign = aries;
        } else if (month == 4) {
            if (day < 20)
                sign = aries;
            else if (day < 30)
                sign = taurus;
        } else if (month == 5) {
            if (day < 21)
                sign = taurus;
            else if (day < 31)
                sign = gemini;
        } else if (month == 6) {
            if (day < 21)
                sign = gemini;
            else if (day < 30)
                sign = cancer;
        } else if (month == 7) {
            if (day < 23)
                sign = cancer;
            else if (day < 31)
                sign = leo;
        } else if (month == 8) {
            if (day < 23)
                sign = leo;
            else if (day < 31)
                sign = virgo;
        } else if (month == 9) {
            if (day < 23)
                sign = virgo;
            else if (day < 30)
                sign = libra;
        } else if (month == 10) {
            if (day < 23)
                sign = libra;
            else if (day < 31)
                sign = scorpio;
        } else if (month == 11) {
            if (day < 22)
                sign = scorpio;
            else if (day < 30)
                sign = sagittarius;
        } else if (month == 12) {
            if (day < 22)
                sign = sagittarius;
            else if (day < 31)
                sign = capricorn;
        }
        return sign;
    }

    public List<SkelGameLabel> getAttrs() {
        return attrs;
    }

    public Element getElement() {
        return element;
    }

    public Planet getPlanet() {
        return planet;
    }

    public ZodiacComp getZodiacComp() {
        return zodiacComp;
    }
}
