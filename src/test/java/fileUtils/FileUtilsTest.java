package fileUtils;

import file.utils.FileTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileUtilsTest {
    private final int NUMBER_OF_FILE_LINES = 2;
    private final String PATHTOFILE = this.getClass().getResource("/filesUtils/test.txt").getPath();
    private final String FIRST_LINE = "Ala ma kota, kot ma Ale.";


    @Test
    protected void testIfNumberOfLineAreEquals() {
        String fileContent = FileTool.getFileContent(PATHTOFILE);
        String[] split = fileContent.split("\n");
        System.out.println(split.length);
        Assertions.assertEquals(NUMBER_OF_FILE_LINES, split.length);
    }
    @Test
    protected void howDoesSplitWorks() {
        String textToSplit = "ANANAS";
        String[] split = textToSplit.split("A");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(split.length);
    }

    @Test
    protected void EqualContentFirstLine() {
        String fileContent = FileTool.getFileContent(PATHTOFILE);
        String[] split = fileContent.split("\n");
        Assertions.assertEquals(FIRST_LINE, split[0]);

    }

}
