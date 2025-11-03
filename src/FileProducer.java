import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileProducer {
    public Map<File, List<File>> filesMap;
    public String targetPath;
    public String name;
    public File file;

    public FileProducer(Map<File, List<File>> filesMap, String targetPath, String name) {
        this.filesMap = filesMap;
        this.targetPath = targetPath;
        this.name = name;
    }

    public void createFile() throws IOException {
        this.file = new File(targetPath + "/" + this.name + ".html");
        if (file.createNewFile()) {
            System.out.println("Archivo creado correctamente.");
        }
        else {
            System.out.println("El archivo ya existe.");
        }
    }

    public void writeFile() {
        Set<File> keys = this.filesMap.keySet();
        String sal = "";
        for (File key : keys) {
            String folderName = key.getName();
            List<File> files = this.filesMap.get(key);
            for (File file : files) {
                sal += "<div>\n  <h1>/" + folderName + "/" + file.getName() + "</h1>\n";
                sal += "  <pre>\n";
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        line = line.replace("<", "&lt;");
                        line = line.replace(">", "&gt;");
                        sal += "    " + line + "\n";
                    }
                }
                catch (IOException exception) {
                    System.out.println("Error al leer en el archivo.");
                }
                sal += "  </pre>\n</div>\n";
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(sal);
        }
        catch (IOException exception) {
            System.out.println("Error al escribir en el archivo.");
            exception.printStackTrace();
        }
    }
}
