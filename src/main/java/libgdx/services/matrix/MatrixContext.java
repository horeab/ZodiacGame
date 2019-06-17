package libgdx.services.matrix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import libgdx.game.Game;
import libgdx.model.CrossWordWithPosition;
import libgdx.services.CrossWordMatrixEncoder;
import libgdx.services.CrossWordService;
import libgdx.services.LettersGameService;

public class MatrixContext {

    private String[][] matrix;
    private int totalCrossWords;
    private int totalNrOfLetters;
    private List<CrossWordWithPosition> allCrossWords = new ArrayList<>();
    private List<String> gameLetters;

    public MatrixContext(int totalCrossWords, int totalNrOfLetters) {
        this.totalCrossWords = totalCrossWords;
        this.totalNrOfLetters = totalNrOfLetters;
        initFields();
    }

    private void initFields() {
        String[] split = getMatrixString(totalCrossWords, totalNrOfLetters).split(MatrixFileWriter.ALL_SPLIT, -1);
        this.allCrossWords = createAllCrossWords(split[1]);
        this.gameLetters = initGameLetters(split[0]);
        System.out.println(new LettersGameService(totalCrossWords, totalNrOfLetters).getCommonLettersWords(gameLetters));
        this.matrix = buildWordMatrix(allCrossWords);
    }

    private List<String> initGameLetters(String gameLettersString) {
        return new Gson().fromJson(gameLettersString, new TypeToken<List<String>>() {
        }.getType());
    }

    private String[][] buildWordMatrix(Collection<CrossWordWithPosition> crossWords) {
        boolean isHorizontal = true;
        CrossWordService crossWordService = new CrossWordService();
        Map<CrossWordWithPosition, Pair<Integer, Integer>> wordsWithPositions = crossWordService.getPosition(crossWords);
        String[][] matrix = CrossWordService.createEmptyCrosswordMatrix(wordsWithPositions);
        for (Map.Entry<CrossWordWithPosition, Pair<Integer, Integer>> entry : wordsWithPositions.entrySet()) {
            CrossWordWithPosition key = entry.getKey();
            for (int j = 0; j < key.getWordLength(); j++) {
                int iIndex = entry.getValue().getRight() + (isHorizontal ? 0 : j);
                int jIndex = entry.getValue().getLeft() + (isHorizontal ? j : 0);
                String value = key.getChildIndexToCross() != null && j == key.getChildIndexToCross() ? key.getChildLetterToCross() : "";
                value = key.getBaseWordIndexToCross() != null && j == key.getBaseWordIndexToCross() ? key.getBaseWordLetterToCross() : value;
                matrix[iIndex][jIndex]
                        = new CrossWordMatrixEncoder().encodeValue(value, matrix[iIndex][jIndex], key.getPositionInCrossWord(), isHorizontal);
            }
            isHorizontal = !isHorizontal;
        }
        return matrix;
    }

    private List<CrossWordWithPosition> createAllCrossWords(String allCrossWordsString) {
        return new ArrayList<CrossWordWithPosition>((Set<CrossWordWithPosition>)new Gson().fromJson(allCrossWordsString, new TypeToken<Set<CrossWordWithPosition>>() {
        }.getType()));
    }

    private String getMatrixString(int totalWords, int totalLetters) {
        FileHandle fileHandle = Gdx.files.internal(getFilePath(totalWords, totalLetters));
        while (!fileHandle.exists()) {
            totalWords--;
            fileHandle = Gdx.files.internal(getFilePath(totalWords, totalLetters));
        }
        Scanner scanner = new Scanner(fileHandle.readString());
        int lineNr = 0;
        int randomVal = new Random().nextInt(Integer.valueOf(scanner.nextLine())) + 1;
        String matrixString = null;
        while (scanner.hasNextLine()) {
            matrixString = scanner.nextLine();
            if (lineNr == randomVal) {
                break;
            }
            lineNr++;
        }
        scanner.close();
        return matrixString;
    }

    private String getFilePath(int totalWords, int totalLetters) {
        return Game.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + "matrix/" + Game.getInstance().getAppInfoService().getLanguage() + "/" + totalWords + "_" + totalLetters + ".txt";
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public int getTotalCrossWords() {
        return totalCrossWords;
    }

    public List<String> getGameLetters() {
        return new ArrayList<>(gameLetters);
    }

    public List<CrossWordWithPosition> getAllCrossWords() {
        return allCrossWords;
    }

    public int getTotalNrOfLetters() {
        return totalNrOfLetters;
    }
}
