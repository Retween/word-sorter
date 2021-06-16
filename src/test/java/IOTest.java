import com.siberteam.edu.zernest.wsorter.InputStreamToListReader;
import com.siberteam.edu.zernest.wsorter.ListToOutputStreamWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class IOTest {
    ArrayList<String> words;

    @Before
    public void setUp() {
        words = new ArrayList<>();
    }

    @Test
    public void testReader() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("напишешь");
        expectedList.add("напиши");
        expectedList.add("войсках");
        expectedList.add("стекло");
        expectedList.add("плюшкиным");
        expectedList.add("выезда");

        File file = new File("/home/retw/IdeaProjects" +
                "/word-sorter/src/main/resources/test.txt");

        try (InputStream inputStream = new FileInputStream(file)) {
            InputStreamToListReader reader =
                    new InputStreamToListReader(inputStream);
            Assert.assertEquals(expectedList, reader.getWordsList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriter() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("напишешь");
        expectedList.add("напиши");
        expectedList.add("войсках");
        expectedList.add("стекло");
        expectedList.add("плюшкиным");
        expectedList.add("выезда");

        File file = new File("/home/retw/IdeaProjects" +
                "/word-sorter/src/main/resources/test2.txt");

        try (OutputStream outputStream = new FileOutputStream(file);
             InputStream inputStream = new FileInputStream(file)) {
            ListToOutputStreamWriter writer =
                    new ListToOutputStreamWriter(outputStream);
            writer.writeListToFile(expectedList);

            InputStreamToListReader reader =
                    new InputStreamToListReader(inputStream);

            Assert.assertEquals(expectedList, reader.getWordsList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
