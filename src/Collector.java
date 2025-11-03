import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collector {
    public Map<File, List<File>> filesMap;

    public Collector() {
        this.filesMap = new HashMap<>();
    }

    public void collectJavaFiles(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();
        List<File> subFolders = new ArrayList<>();
        List<File> javaFiles = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                subFolders.add(file);
            }
            else if (file.isFile() && file.getName().contains(".java")) {
                javaFiles.add(file);
            }
        }

        if (!javaFiles.isEmpty()) {
            this.filesMap.put(folder, javaFiles);
        }

        for (File subFolder : subFolders) {
            collectJavaFiles(path + "/" + subFolder.getName());
        }
    }

    public Map<File, List<File>> getJavaFiles() {
        return this.filesMap;
    }
}
