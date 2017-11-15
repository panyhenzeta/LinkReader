package tr.zka;

import tr.zka.file.process.FileProcesses;

public class App {

    public static void main(String[] args) {
        FileProcesses fileProcesses = new FileProcesses();
        fileProcesses.getFilePathFromUser();



/*        LinkFileHebele linkFileHebele = new LinkFileHebele();
        linkFileHebele.readFile(filePath);
        linkFileHebele.findLinksInFile();
        linkFileHebele.validateLinksAndRemove();
        linkFileHebele.fetchLinksData();
        linkFileHebele.printLinks();*/
    }

}
