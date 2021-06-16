import com.siberteam.edu.zernest.wsorter.sorters.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class SortersTest {
    ArrayList<String> dictionary;

    @Before
    public void setUp() {
        dictionary = new ArrayList<>();

        dictionary.add("евроотремонтированная");
        dictionary.add("араке");
        dictionary.add("неестественною");
        dictionary.add("безусловно");
        dictionary.add("ясенево");
        dictionary.add("несецкого");
        dictionary.add("исчезнет");
        dictionary.add("несу");
        dictionary.add("мцк");
        dictionary.add("обозом");
    }

    @Test
    public void testAlphabeticalSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("араке");
        expectedList.add("безусловно");
        expectedList.add("евроотремонтированная");
        expectedList.add("исчезнет");
        expectedList.add("мцк");
        expectedList.add("неестественною");
        expectedList.add("несецкого");
        expectedList.add("несу");
        expectedList.add("обозом");
        expectedList.add("ясенево");

        Comparator<String> comparator = new AlphabeticalSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }

    @Test
    public void testReverseAlphabeticalSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("ясенево");
        expectedList.add("обозом");
        expectedList.add("несу");
        expectedList.add("несецкого");
        expectedList.add("неестественною");
        expectedList.add("мцк");
        expectedList.add("исчезнет");
        expectedList.add("евроотремонтированная");
        expectedList.add("безусловно");
        expectedList.add("араке");

        Comparator<String> comparator = new ReverseAlphabeticalSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }

    @Test
    public void testDuplicateLettersSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("фошу");   //0 duplicates
        expectedList.add("фф");
        expectedList.add("шошш");
        expectedList.add("aaaaa");
        expectedList.add("абабабаб");   //6 duplicates

        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("фошу");
        actualList.add("абабабаб");
        actualList.add("aaaaa");
        actualList.add("фф");
        actualList.add("шошш");

        Comparator<String> comparator = new DuplicateLettersSorter();
        actualList.sort(comparator);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void testLengthSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("мцк");
        expectedList.add("несу");
        expectedList.add("араке");
        expectedList.add("обозом");
        expectedList.add("ясенево");
        expectedList.add("исчезнет");
        expectedList.add("несецкого");
        expectedList.add("безусловно");
        expectedList.add("неестественною");
        expectedList.add("евроотремонтированная");

        Comparator<String> comparator = new LengthSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }

    @Test
    public void testReverseLengthSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("евроотремонтированная");
        expectedList.add("неестественною");
        expectedList.add("безусловно");
        expectedList.add("несецкого");
        expectedList.add("исчезнет");
        expectedList.add("ясенево");
        expectedList.add("обозом");
        expectedList.add("араке");
        expectedList.add("несу");
        expectedList.add("мцк");


        Comparator<String> comparator = new ReverseLengthSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }

    @Test
    public void testAlphabeticalReverseWordsSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("араке");
        expectedList.add("мцк");
        expectedList.add("обозом");
        expectedList.add("ясенево");
        expectedList.add("несецкого");
        expectedList.add("безусловно");
        expectedList.add("исчезнет");
        expectedList.add("несу");
        expectedList.add("неестественною");
        expectedList.add("евроотремонтированная");


        Comparator<String> comparator = new AlphabeticalReverseWordsSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }

    @Test
    public void testVowelsSorter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("мцк");
        expectedList.add("несу");
        expectedList.add("араке");
        expectedList.add("исчезнет");
        expectedList.add("обозом");
        expectedList.add("безусловно");
        expectedList.add("ясенево");
        expectedList.add("несецкого");
        expectedList.add("неестественною");
        expectedList.add("евроотремонтированная");

        Comparator<String> comparator = new VowelsSorter();
        dictionary.sort(comparator);

        Assert.assertEquals(expectedList, dictionary);
    }
}
