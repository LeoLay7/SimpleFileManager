package filemanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {
    private Path dir;
    private ArrayList<Path> filesList = new ArrayList<>();
    private boolean isRecursion = false;
    private boolean isSingleFile = false;

    public void statistics() {
        System.out.println(dir);
        System.out.println(filesList.size());
        System.out.println(isRecursion);
        System.out.println(isSingleFile);
    }

    public FileManager() {}

    public FileManager(String dir) {
        Path tmpPath = Paths.get(dir);
        if (FileTools.isValidDirectory(tmpPath)) {
            this.dir = tmpPath;
            loadDirFiles(this.dir);
        } else if (FileTools.isTxtFile(tmpPath)) {
            isSingleFile = true;
            filesList.add(tmpPath);
        }

    }

    public FileManager(String dir, boolean isRecursion) {
        Path tmpPath = Paths.get(dir);
        if (!FileTools.isValidDirectory(tmpPath)) {
            System.out.println("Not a dir");
            return;
        }
        this.isRecursion = isRecursion;
        this.dir = tmpPath;
        loadDirFiles(this.dir);

    }

    public void setPath(String newPath) {
        Path tmpPath = Paths.get(newPath);
        if (FileTools.isValidDirectory(tmpPath)) {
            dir = tmpPath;
            if (!filesList.isEmpty()) filesList.clear();
            loadDirFiles(dir);

            isSingleFile = false;
        } else if (FileTools.isTxtFile(tmpPath)) {
            dir = null;
            isSingleFile = true;
            isRecursion = false;

            if (!filesList.isEmpty()) filesList.clear();
            filesList.add(tmpPath);
        }
    }

    public void countWords() {
        try {
            for (Path path: filesList) {
                WordCounter.printFileWordsCount(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceWords(String target, String replacer) {
        for (Path path: filesList) {
            WordReplacer.replaceFileWords(path, target, replacer);
        }
        System.out.println("words replaced");
    }

    public void filter(String target) {
        for (Path file: filesList) {
            KeywordFilter.printFilter(file, target);
        }
    }

    private void loadDirFiles(Path directory) {
        if (FileTools.isTxtFile(directory)) return;
        try (var files = Files.newDirectoryStream(directory, "*")) {
            for (Path path: files) {
                if (FileTools.isTxtFile(path)) filesList.add(path);
                if (isRecursion && FileTools.isValidDirectory(path)) loadDirFiles(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
