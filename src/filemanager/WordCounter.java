package filemanager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordCounter {
    public static Map<String, Integer> countWords(Path path) {
        return countWords(FileTools.readFromFile(path));
    }

    public static Map<String, Integer> countWords(List<String> lines) {
        Map<String, Integer> words = new HashMap<>();
        for (String line: lines) {
            String[] splittedLine = FileTools.splitLine(line);
            for (String word: splittedLine) {
                words.computeIfAbsent(word, k -> 0);
                words.compute(word, (k, v) -> v + 1);
            }
        }
        return words;
    }

    public static void printFileWordsCount(Path path) throws IOException {
        if (!FileTools.isTxtFile(path)) {
            System.out.println("File " + path.getFileName() + "is not .txt");
            return;
        }
        Map<String, Integer> words = WordCounter.countWords(path);
        System.out.println("Filename: " + path.getFileName() + "\n");

        if (words.isEmpty()) {
            System.out.println("File is empty");
        }

        for (Map.Entry<String, Integer> entry: words.entrySet()) {
            System.out.println("Word: " + entry.getKey());
            System.out.println("Count: " + entry.getValue());
            System.out.println();
        }
    }

    public static void printFileWordsCount(String path) throws IOException {
        printFileWordsCount(Paths.get(path));
    }
}
