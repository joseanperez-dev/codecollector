import java.io.File;
import java.nio.file.Files;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        //String baseFolderPath = "/home/jose/taller/aparte/CodeCollector-project";
        String baseFolderPath = args[0];
        Collector collector = new Collector();
        collector.collectFiles(baseFolderPath, ".xml");
        collector.collectFiles(baseFolderPath, ".properties");
        collector.collectFiles(baseFolderPath, ".yml");
        collector.collectFiles(baseFolderPath, ".java");
        Map<File, List<File>> filesMap = collector.getFiles();
        Set<File> keys = filesMap.keySet();
        for (File folder : keys) {
            List<File> fileList = filesMap.get(folder);
            System.out.println(folder.getName());
            for (File file : fileList) {
                System.out.println("- " + file.getName());
            }
        }

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();

        // System.out.println("Introduce el nombre del archivo en el que quieres guardar el texto de los archivos recopilados:");
        String fileName = args[1];
        FileProducer fileProducer = new FileProducer(filesMap, baseFolderPath, ".", fileName);
        try {
            fileProducer.createFile();
            fileProducer.writeFile();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


}