import java.io.File;
import java.util.*;

public class Collector {
    public Map<File, List<File>> filesMap;

    public Collector() {
        this.filesMap = new LinkedHashMap<>();
    }

    public void collectFiles(String path, String typeFile) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        List<File> subFolders = new ArrayList<>();
        List<File> javaFiles = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                subFolders.add(file);
            }
            else if (file.isFile() && file.getName().contains(typeFile)) {
                javaFiles.add(file);
            }
        }

        if (!javaFiles.isEmpty()) {
            this.filesMap.put(folder, javaFiles);
        }

        for (File subFolder : subFolders) {
            collectFiles(path + "/" + subFolder.getName(), typeFile);
        }
    }

    public Map<File, List<File>> getFiles() {
        return this.filesMap;
    }
}
