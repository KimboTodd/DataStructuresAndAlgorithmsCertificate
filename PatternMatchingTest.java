import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;

public class PatternMatchingTest {
    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    @Test
    public void testBuildLastTableWithEmptyPattern() {
        String pattern = "";
        Map<Character, Integer> table = PatternMatching.buildLastTable(pattern);
        Assert.assertEquals(0, table.size());
    }

    @Test
    public void testBuildLastTableWithSingleCharacterPattern() {
        String pattern = "a";
        Map<Character, Integer> table = PatternMatching.buildLastTable(pattern);
        Assert.assertEquals(1, table.size());
        Assert.assertEquals(Integer.valueOf(0), table.get('a'));
    }

    @Test
    public void testBuildLastTableWithRepeatedCharacters() {
        String pattern = "abacabadabacaba";
        Map<Character, Integer> table = PatternMatching.buildLastTable(pattern);
        Assert.assertEquals(4, table.size());
        Assert.assertEquals(Integer.valueOf(14), table.get('a'));
        Assert.assertEquals(Integer.valueOf(13), table.get('b'));
        Assert.assertEquals(Integer.valueOf(11), table.get('c'));
        Assert.assertEquals(Integer.valueOf(7), table.get('d'));
    }

    @Test
    public void testBuildLastTableWithNoRepeatingCharacters() {
        String pattern = "abcdefg";
        Map<Character, Integer> table = PatternMatching.buildLastTable(pattern);
        Assert.assertEquals(7, table.size());
        Assert.assertEquals(Integer.valueOf(0), table.get('a'));
        Assert.assertEquals(Integer.valueOf(1), table.get('b'));
        Assert.assertEquals(Integer.valueOf(2), table.get('c'));
        Assert.assertEquals(Integer.valueOf(3), table.get('d'));
        Assert.assertEquals(Integer.valueOf(4), table.get('e'));
        Assert.assertEquals(Integer.valueOf(5), table.get('f'));
        Assert.assertEquals(Integer.valueOf(6), table.get('g'));
    }

    @Test
    public void testBoyerMooreWithEmptyPattern() {
        CharSequence text = "This is a test string";
        CharSequence pattern = "";
        List<Integer> expected = new ArrayList<Integer>();
        List<Integer> result = PatternMatching.boyerMoore(pattern, text, new CharacterComparator());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testBoyerMooreWithNonEmptyPatternAndNoMatch() {
        CharSequence text = "This is a test string";
        CharSequence pattern = "hello";
        List<Integer> expected = new ArrayList<Integer>();
        List<Integer> result = PatternMatching.boyerMoore(pattern, text, new CharacterComparator());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testBoyerMooreWithMatch() {
        CharSequence text = "hello";
        CharSequence pattern = "hello";
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        List<Integer> result = PatternMatching.boyerMoore(pattern, text, new CharacterComparator());
        Assert.assertEquals(expected, result);
    }

     @Test
    public void testBoyerMooreWithNonEmptyPatternAndSingleMatch() {
        CharSequence text = "This is a test string";
        CharSequence pattern = "test";
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(10));
        List<Integer> result = PatternMatching.boyerMoore(pattern, text, new CharacterComparator());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testBoyerMooreWithNonEmptyPatternAndMultipleMatches() {
        CharSequence text = "abababab";
        CharSequence pattern = "aba";
        List<Integer> expected = new ArrayList<Integer>(Arrays.asList(0, 2, 4));
        List<Integer> result = PatternMatching.boyerMoore(pattern, text, new CharacterComparator());
        Assert.assertEquals(expected, result);
    }
}