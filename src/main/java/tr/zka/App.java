package tr.zka;

import tr.zka.file.process.FileProcesses;

import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        processFile();
    }

    private static void processFile() throws FileNotFoundException {
        FileProcesses fileProcesses = new FileProcesses();
        fileProcesses.getFilePathFromUser();
        fileProcesses.findLinksInFile();
        fileProcesses.fetchLinksData();

        fileProcesses.printLinks();
    }
}
