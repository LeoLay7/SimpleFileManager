package filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTools {

    public static List<String> readFromFile(Path path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeToFile(Path path, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (String line: lines) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTxtFile(Path path) {
        return Files.isRegularFile(path) && path.toString().endsWith(".txt");
    }

    public static boolean isValidDirectory(Path path) {
        return Files.exists(path) && Files.isDirectory(path);
    }

    public static String[] splitLine(String line) {
        return line.trim().toLowerCase().split("[^\\w']+");
    }
}
