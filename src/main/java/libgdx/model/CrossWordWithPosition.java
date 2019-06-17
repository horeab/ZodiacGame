package libgdx.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CrossWordWithPosition {

    private int positionInCrossWord;

    private int wordLength;

    private String baseWordLetterToCross;
    private Integer baseWordIndexToCross;

    private Integer childIndexToCross;
    private String childLetterToCross;

    private String foundWord;

    public CrossWordWithPosition(int positionInCrossWord, int wordLength, Integer baseWordIndexToCross, String baseWordLetterToCross) {
        this.positionInCrossWord = positionInCrossWord;
        this.wordLength = wordLength;
        this.baseWordLetterToCross = baseWordLetterToCross;
        this.baseWordIndexToCross = baseWordIndexToCross;
    }

    public String getFoundWord() {
        return foundWord;
    }

    public void setFoundWord(String foundWord) {
        this.foundWord = foundWord;
    }

    public int getPositionInCrossWord() {
        return positionInCrossWord;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String getBaseWordLetterToCross() {
        return baseWordLetterToCross;
    }

    public Integer getBaseWordIndexToCross() {
        return baseWordIndexToCross;
    }

    public Integer getChildIndexToCross() {
        return childIndexToCross;
    }

    public String getChildLetterToCross() {
        return childLetterToCross;
    }

    public void setChildInfo(Integer childIndexToCross, String childLetterToCross) {
        this.childIndexToCross = childIndexToCross;
        this.childLetterToCross = childLetterToCross;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossWordWithPosition that = (CrossWordWithPosition) o;
        if (StringUtils.isNotBlank(foundWord)) {
            return Objects.equals(foundWord, that.foundWord);
        } else {
            return this.positionInCrossWord == that.positionInCrossWord &&
                    wordLength == that.wordLength &&
                    Objects.equals(baseWordLetterToCross, that.baseWordLetterToCross) &&
                    Objects.equals(baseWordIndexToCross, that.baseWordIndexToCross) &&
                    Objects.equals(childIndexToCross, that.childIndexToCross) &&
                    Objects.equals(childLetterToCross, that.childLetterToCross);
        }
    }

}
