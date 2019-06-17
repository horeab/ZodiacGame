package service;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.*;

import libgdx.model.CrossWordWithPosition;
import libgdx.screens.game.gametype.GameType;
import libgdx.services.CrossWordContext;
import libgdx.services.CrossWordService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CrossWordServiceTest extends TestMain {

    private CrossWordService crossWordService = new CrossWordService();

    @Test
    public void createAllCrossWordsFromFile() {
//        for (int i = 0; i < 20; i++) {
//            int totalCrossWords = 5;
//            List<CrossWordWithPosition> allCrossWords = crossWordService.createAllCrossWords(new LettersGameService().getAllWords(), totalCrossWords);
//            assertEquals(totalCrossWords, allCrossWords.size());
//            for (CrossWordWithPosition word : allCrossWords) {
//                Set<String> childrenWords = crossWordService.findCommonLetterWords(new LettersGameService().getAllWords(), word.getCrossWord().getWord());
//                for (String childWord : childrenWords) {
////                    assertEquals(word.getWord().toCharArray()[word.getBaseWordIndexToCross()], childWord.toCharArray()[word.getChildIndexToCross()]);
////                    assertTrue(word.getPositionInCrossWord() < allCrossWords.size() - 1 && childWord.length() - 2 >= crossWordService.getCrosswordForIndex(allCrossWords, word.getPositionInCrossWord() + 1).getBaseWordIndexToCross());
//                }
//            }
//        }
    }

    private CrossWordWithPosition createCrossWordWithPosition(String word, int baseWordPositionToCross, int childPositionToCross, int positionInCrossWord) {
        CrossWordWithPosition crossWordWithPosition = new CrossWordWithPosition(positionInCrossWord, word.length(), baseWordPositionToCross, String.valueOf(word.toCharArray()[baseWordPositionToCross]));
        crossWordWithPosition.setChildInfo(childPositionToCross, String.valueOf(word.toCharArray()[childPositionToCross]));
        return crossWordWithPosition;
    }

    @Test
    public void getPosition() {
        CrossWordWithPosition crossWord1 = createCrossWordWithPosition("arbore", 0, 5, 0);
        CrossWordWithPosition crossWord2 = createCrossWordWithPosition("cerb", 1, 3, 1);
        CrossWordWithPosition crossWord3 = createCrossWordWithPosition("ciob", 3, 1, 2);
        CrossWordWithPosition crossWord4 = createCrossWordWithPosition("iguana", 0, 4, 3);
        CrossWordWithPosition crossWord5 = createCrossWordWithPosition("cabana", 4, 0, 4);

        Map<CrossWordWithPosition, Pair<Integer, Integer>> pos = crossWordService.getPosition(Arrays.asList(crossWord1, crossWord2, crossWord3, crossWord4, crossWord5));

        assertEquals(Integer.valueOf(1), pos.get(crossWord1).getLeft());
        assertEquals(Integer.valueOf(1), pos.get(crossWord1).getRight());

        assertEquals(Integer.valueOf(6), pos.get(crossWord2).getLeft());
        assertEquals(Integer.valueOf(0), pos.get(crossWord2).getRight());

        assertEquals(Integer.valueOf(3), pos.get(crossWord3).getLeft());
        assertEquals(Integer.valueOf(3), pos.get(crossWord3).getRight());

        assertEquals(Integer.valueOf(4), pos.get(crossWord4).getLeft());
        assertEquals(Integer.valueOf(3), pos.get(crossWord4).getRight());

        assertEquals(Integer.valueOf(0), pos.get(crossWord5).getLeft());
        assertEquals(Integer.valueOf(7), pos.get(crossWord5).getRight());

        Pair<Integer, Integer> maxWidthAndMaxHeight = CrossWordService.getMaxWidthAndMaxHeight(pos);
        assertEquals(Integer.valueOf(7), maxWidthAndMaxHeight.getLeft());
        assertEquals(Integer.valueOf(9), maxWidthAndMaxHeight.getRight());

        crossWord1 = createCrossWordWithPosition("caine", 0, 1, 0);
        crossWord2 = createCrossWordWithPosition("iguana", 3, 0, 1);

        pos = crossWordService.getPosition(Arrays.asList(crossWord1, crossWord2));

        assertEquals(Integer.valueOf(0), pos.get(crossWord1).getLeft());
        assertEquals(Integer.valueOf(3), pos.get(crossWord1).getRight());

        assertEquals(Integer.valueOf(1), pos.get(crossWord2).getLeft());
        assertEquals(Integer.valueOf(0), pos.get(crossWord2).getRight());
    }

    @Test
    public void getHorizontalValue() {
//        Map<CrossWordWithPosition, Pair<Integer, Integer>> pos = new HashMap<>();
//        pos.put(new CrossWordWithPosition(new CrossWord(0, ""), 0), new MutablePair<>(0, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(1, ""), 0), new MutablePair<>(2, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(2, ""), 0), new MutablePair<>(2, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(3, ""), 0), new MutablePair<>(2, 1));
//        assertEquals(-1, crossWordService.getHorizontalValue(-3, 4, pos));
//
//        pos = new HashMap<>();
//
//        pos.put(new CrossWordWithPosition(new CrossWord(0, ""), 0), new MutablePair<>(1, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(1, ""), 0), new MutablePair<>(1, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(2, ""), 0), new MutablePair<>(3, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(3, ""), 0), new MutablePair<>(3, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(4, ""), 0), new MutablePair<>(0, 1));
//        pos.put(new CrossWordWithPosition(new CrossWord(5, ""), 0), new MutablePair<>(0, 1));
//        assertEquals(-3, crossWordService.getHorizontalValue(-3, 6, pos));
    }

    @Test
    public void getCrossWordMatrix() {
//        Map<CrossWord, Pair<Integer, Integer>> pos = crossWordService.getPosition(createCrosswords());
//        printMatrix(crossWordService.getCrossWordMatrix(pos));
    }

    /*
      c
 arborele
      r
   ciob
    g
    u
    a
cabana
    a
     */
    private List<CrossWordWithPosition> createCrosswords() {
        List<String> words = words();
        CrossWordWithPosition crossWord1 = createCrossWordWithPosition(words.get(0), 0, 5, 0);
        CrossWordWithPosition crossWord2 = createCrossWordWithPosition(words.get(1), 1, 3, 1);
        CrossWordWithPosition crossWord3 = createCrossWordWithPosition(words.get(2), 3, 1, 2);
        CrossWordWithPosition crossWord4 = createCrossWordWithPosition(words.get(3), 0, 4, 3);
        CrossWordWithPosition crossWord5 = createCrossWordWithPosition(words.get(4), 4, 0, 4);
        return Arrays.asList(crossWord1, crossWord2, crossWord3, crossWord4, crossWord5);
    }

    private List<String> words() {
        return new ArrayList<>(Arrays.asList("arborele", "cerb", "ciob", "iguana", "cabana"));
    }

    @Test
    public void createNewCrossWordMatrixContext() {
        for (int i = 0; i < 10; i++) {
            CrossWordContext crossWordContext = new CrossWordContext(5, 10, new ArrayList<>(), GameType.HIGHLIGHTED_CROSSWORD);
            String[][] matrix = crossWordContext.getCrossWordMatrix();
            printMatrix(matrix);
        }
    }

    private HashSet<String> getAllWordsWithAdded(String added) {
        HashSet<String> result = new HashSet<>(words());
        result.add(added);
        return result;
    }

    @Test
    public void isGeneratedMatrixValid() {
        List<CrossWordWithPosition> crosswords = createCrosswords();
        crosswords.get(crosswords.size() - 1).setChildInfo(2, "b");

        String wordToTest = "cerbx";
        CrossWordWithPosition addedCrossWord = createCrossWordWithPosition(wordToTest, 3, 0, 5);
        assertTrue(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));

        crosswords.get(crosswords.size() - 1).setChildInfo(1, "a");
        //the letter where they overlap is 'a'
        wordToTest = "a12345a";
        addedCrossWord = createCrossWordWithPosition(wordToTest, 6, 0, 5);
        assertTrue(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));

        crosswords.get(crosswords.size() - 1).setChildInfo(1, "a");
        //the letter where they overlap should be 'a' but it's 'x'
        wordToTest = "x12345a";
        addedCrossWord = createCrossWordWithPosition(wordToTest, 6, 0, 5);
        assertFalse(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));

        crosswords.get(crosswords.size() - 1).setChildInfo(2, "b");
        //the letter where they overlap is 'r', but should fail because '2' is to close to 'c' in the word 'ciob'
        wordToTest = "r12345b";
        addedCrossWord = createCrossWordWithPosition(wordToTest, 6, 0, 5);
        assertFalse(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));

        crosswords.get(crosswords.size() - 1).setChildInfo(3, "a");
        //the letters are to close to each other
        wordToTest = "cat";
        addedCrossWord = createCrossWordWithPosition(wordToTest, 1, 0, 5);
        assertFalse(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));

        crosswords.get(crosswords.size() - 1).setChildInfo(4, "n");
        //vertical letters overlap
        wordToTest = "naa";
        addedCrossWord = createCrossWordWithPosition(wordToTest, 0, 0, 5);
        assertFalse(crossWordService.isGeneratedMatrixValid(getAllWordsWithAdded(wordToTest), crosswords, 6, addedCrossWord));
    }

    @Test
    public void buildWordMatrix() {
        List<CrossWordWithPosition> crosswords = createCrosswords();
        for (int i = 0; i < crosswords.size(); i++) {
            crosswords.get(i).setFoundWord(words().get(i));
        }
        String[][] matrix = crossWordService.buildWordMatrix(words(), crosswords);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printMatrix(String[][] matrix) {
//        CrossWordMatrixEncoder crossWordMatrixEncoder = new CrossWordMatrixEncoder();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                String val = matrix[i][j] == null ? "X" : matrix[i][j];
//                System.out.print(crossWordMatrixEncoder.decodeLetterValue(val));
//            }
//            System.out.println();
//        }
    }

    @Test
    public void removeLetterFromCrossWord() {
//        CrossWord crossWord = new CrossWord(0, "123456", 2, 2);
//        crossWordService.removeLetterFromCrossWord(crossWord, 1);
//        assertEquals("13456", crossWord.getWord());
//        assertEquals(Integer.valueOf(1), crossWord.getBaseWordIndexToCross());
//        assertEquals(Integer.valueOf(2), crossWord.getChildIndexToCross());
//
//        crossWord = new CrossWord(0, "123456", 2, 2);
//        crossWordService.removeLetterFromCrossWord(crossWord, 4);
//        assertEquals("12346", crossWord.getWord());
//        assertEquals(Integer.valueOf(2), crossWord.getBaseWordIndexToCross());
//        assertEquals(Integer.valueOf(2), crossWord.getChildIndexToCross());
    }

    @Test
    public void isRemoveLetterValidForWord() {
        assertFalse(crossWordService.isRemoveLetterValidForWord(new HashSet<>(Arrays.asList("123456", "123546", "12456")), "123456", 2, 2));
        assertTrue(crossWordService.isRemoveLetterValidForWord(new HashSet<>(Arrays.asList("123456", "123546", "12456")), "123456", 3, 2));
        assertFalse(crossWordService.isRemoveLetterValidForWord(new HashSet<>(Arrays.asList("123456", "123546", "12456")), "123456", 3, 3));
    }

    @Test
    public void createAllCrossWords() {
        int testsToRun = 20;
        int runTests = 0;
        for (int i = 0; i < testsToRun; i++) {
            List<CrossWordWithPosition> list = crossWordService.createAllCrossWords(new HashSet<>(Arrays.asList("084975x", "123456yy", "123546zzz")), 3);
            for (CrossWordWithPosition crossWord : list) {
                if (crossWord.getPositionInCrossWord() == 0) {
                    CrossWordWithPosition child = crossWordService.getCrosswordForIndex(list, 1);
                    assertEquals(Integer.valueOf(0), crossWord.getBaseWordIndexToCross());
                    assertEquals(1, child.getPositionInCrossWord());
                    assertEquals(child.getBaseWordLetterToCross(), crossWord.getChildLetterToCross());
                } else if (crossWord.getPositionInCrossWord() == 1) {
                    CrossWordWithPosition child = crossWordService.getCrosswordForIndex(list, 2);
                    assertEquals(2, child.getPositionInCrossWord());
                    assertEquals(child.getBaseWordLetterToCross(), crossWord.getChildLetterToCross());
                } else if (crossWord.getPositionInCrossWord() == 2) {
                    CrossWordWithPosition child = crossWordService.getCrosswordForIndex(list, 3);
                    assertNull(child);
                } else {
                    fail();
                }
            }
            runTests++;
        }
        assertEquals(testsToRun, runTests);
    }

    @Test
    public void findCommonLetterWords() {
        assertEquals(new HashSet<>(Arrays.asList("1234", "245678")),
                crossWordService.findCommonLetterWords(new HashSet<>(Arrays.asList("1234", "245678")), "290"));

        assertEquals(new HashSet<>(Arrays.asList("1234")),
                crossWordService.findCommonLetterWords(new HashSet<>(Arrays.asList("1234", "45678")), "290"));


        assertEquals(new HashSet<>(Collections.emptyList()),
                crossWordService.findCommonLetterWords(new HashSet<>(Arrays.asList("1324", "45678")), "290"));

    }

    //LEFT - Parent info
    //RIGHT - Child Info
    @Test
    public void getRandomMatchingLetterPosition() {
        MutablePair<MutablePair<Integer, String>, MutablePair<Integer, String>> pos = crossWordService.getRandomMatchingLetterPositionChildForParentAndBaseForChild("ydzxy", "abcde");
        assertEquals(Integer.valueOf(3), pos.getLeft().getLeft());
        assertEquals("d", pos.getLeft().getRight());
        assertEquals(Integer.valueOf(1), pos.getRight().getLeft());
        assertEquals("d", pos.getRight().getRight());

        //Should not be the last two letters, always 'a'
        for (int i = 0; i < 20; i++) {
            pos = crossWordService.getRandomMatchingLetterPositionChildForParentAndBaseForChild("abc", "bcade");
            assertEquals(Integer.valueOf(2), pos.getLeft().getLeft());
            assertEquals("a", pos.getLeft().getRight());
            assertEquals(Integer.valueOf(0), pos.getRight().getLeft());
            assertEquals("a", pos.getRight().getRight());
        }

        //Should not find a solution because only the last two letters are valid
        for (int i = 0; i < 20; i++) {
            pos = crossWordService.getRandomMatchingLetterPositionChildForParentAndBaseForChild("xbc", "bcade");
            assertEquals(Integer.valueOf(-1), pos.getLeft().getLeft());
            assertNull(pos.getLeft().getRight());
            assertEquals(Integer.valueOf(-1), pos.getRight().getLeft());
            assertNull(pos.getRight().getRight());
        }
    }
}
