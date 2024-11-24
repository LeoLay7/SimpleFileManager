package filemanager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WordReplacer {
    public static void replaceFileWords(String path, String target, String replacer) {
        replaceFileWords(Paths.get(path), target, replacer);
    }
    public static void replaceFileWords(Path path, String target, String replacer) {
        List<String> lines = FileTools.readFromFile(path);
        String capitalizedTarget = target.substring(0, 1).toUpperCase() + target.substring(1);
        String capitalizedReplacer = replacer.substring(0, 1).toUpperCase() + replacer.substring(1);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String newLine = line.replace(target, replacer).replace(capitalizedTarget, capitalizedReplacer);
            lines.set(i, newLine);
        }

        FileTools.writeToFile(path, lines);

    }
}
