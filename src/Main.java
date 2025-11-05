import java.io.File;
import java.nio.file.Files;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //String baseFolderPath = "/home/jose/taller/aparte/CodeCollector-project";
        System.out.println("Escribe la ruta del directorio del que quieres recopilar los archivos:");
        String baseFolderPath = scanner.nextLine();
        Collector collector = new Collector();
        System.out.println("Escribe una de estas opciones:\n\t- .java\n\t- application.properties\n\t- pom.xml");
        String fileType = scanner.nextLine();
        collector.collectFiles(baseFolderPath, fileType);
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

        System.out.println("Introduce el nombre del archivo en el que quieres guardar el texto de los archivos recopilados:");
        String fileName = scanner.nextLine();
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