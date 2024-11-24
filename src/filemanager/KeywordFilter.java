package filemanager;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordFilter {
    public static List<String> filter(Path path, String target) {
        return filter(FileTools.readFromFile(path), target);
    }

    public static List<String> filter(List<String> lines, String target) {
        if (lines == null || target == null) {
            throw new IllegalArgumentException("Lines and target must not be null");
        }
        return lines.stream()
                .filter(line -> line.contains(target))
                .collect(Collectors.toList());
    }

    public static void printFilter(Path path, String target) {
        List<String> lines = KeywordFilter.filter(path, target);
        System.out.println("Results for target - " + target);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ". " + lines.get(i));
        }
    }
}
