package tr.zka;

import tr.zka.file.process.FileProcesses;

import java.io.FileNotFoundException;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        FileProcesses fileProcesses = new FileProcesses();
        fileProcesses.getFilePathFromUser();
        fileProcesses.findLinksInFile();


/*        LinkFileHebele linkFileHebele = new LinkFileHebele();
        linkFileHebC:\Users\zka\Desktop\CatCoder\6 - LinkReader\README.mdele.readFile(filePath);
        linkFileHebele.findLinksInFile();
        linkFileHebele.validateLinksAndRemove();
        linkFileHebele.fetchLinksData();
        linkFileHebele.printLinks();*/
    }

}
