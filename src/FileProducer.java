import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileProducer {
    public Map<File, List<File>> filesMap;
    public String targetPath;
    public String name;
    public File file;
    public String baseFolderPath;

    public FileProducer(Map<File, List<File>> filesMap, String baseFolderPath, String targetPath, String name) {
        this.filesMap = filesMap;
        this.targetPath = targetPath;
        this.name = name;
        this.baseFolderPath = baseFolderPath;
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
        //Set<File> keys = this.filesMap.keySet()
        String sal = """
                <head>
                    <meta charset="UTF-8"/>
                    <style>
                        pre {
                            margin: 1rem;
                            padding: 1rem;
                            border: 1px solid black;
                            page-break-inside: avoid;
                        }
                    </style>
                    <link rel="stylesheet" href="style.css"/>
                </head>
                """;
        for (Map.Entry<File, List<File>> entry : filesMap.entrySet()) {
            File key = entry.getKey();
            List<File> files = this.filesMap.get(key);
            for (File file : files) {
                sal += "<div>\n  <h2>" + file.getPath().substring(baseFolderPath.length())/* + "/" + file.getName()*/ + "</h2>\n";
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
