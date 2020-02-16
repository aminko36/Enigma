package file.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileTool {
    public static String getFileContent(String pathToFile) {
        StringBuilder stringBuilder = new StringBuilder(); //przechowywanie
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile))) {
            String line = reader.readLine();  //pobralismy 1sza linie z pliku
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("could not find file : " + pathToFile);
        } catch (IOException e) {
            System.out.println("Some problem with file .....: " + e);
        }
        return stringBuilder.toString();
    }
}
