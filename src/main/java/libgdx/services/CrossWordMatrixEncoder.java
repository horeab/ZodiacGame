package libgdx.services;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

import libgdx.resources.Res;
import libgdx.resources.Resource;

public class CrossWordMatrixEncoder {

    private final static String SECTION_DELIMITER = ":";
    private final static String ELEM_DELIMITER = "##";
    private static final String HORIZONTAL_ENCODE = "H";
    private static final String VERTICAL_ENCODE = "V";
    private static final int LETTER_STRING_INDEX = 0;
    private static final int DIMENSION_INDEX = 1;
    private static final int POSITION_IN_CROSSWORD_INDEX = 2;

    public String encodeValue(String value, String previousValue, int positionInCrossWord, boolean isHorizontal) {
        String toReturn;
        String dimension = isHorizontal ? HORIZONTAL_ENCODE : VERTICAL_ENCODE;
        if (StringUtils.isNotBlank(previousValue)) {
            String[] vals = previousValue.split(SECTION_DELIMITER,-1);
            vals[DIMENSION_INDEX] = vals[DIMENSION_INDEX] + ELEM_DELIMITER + dimension;
            vals[POSITION_IN_CROSSWORD_INDEX] = vals[POSITION_IN_CROSSWORD_INDEX] + ELEM_DELIMITER + positionInCrossWord;
            toReturn = StringUtils.join(vals, SECTION_DELIMITER);
        } else {
            toReturn = value + SECTION_DELIMITER + dimension + SECTION_DELIMITER + positionInCrossWord;
        }
        return toReturn;
    }

    public String decodeLetterValue(String value) {
        return value.split(SECTION_DELIMITER,-1)[LETTER_STRING_INDEX];
    }

    private String[] getDimensions(String value) {
        return value.split(SECTION_DELIMITER,-1)[DIMENSION_INDEX].split(ELEM_DELIMITER);
    }
    public String[] getPositionsInCrossWord(String value) {
        return value.split(SECTION_DELIMITER)[POSITION_IN_CROSSWORD_INDEX].split(ELEM_DELIMITER,-1);
    }

    public Res getCellBackgroundResToDisplay(String value) {
        Res res;
        String[] dimensions = getDimensions(value);
        boolean isHorizontal = Arrays.asList(dimensions).contains(HORIZONTAL_ENCODE);
        boolean isVertical = Arrays.asList(dimensions).contains(VERTICAL_ENCODE);
        if (isHorizontal && isVertical) {
            res = Resource.crossword_cross;
        } else {
            res = isHorizontal ? Resource.crossword_horizontal : Resource.crossword_vertical;
        }
        return res;
    }
}
