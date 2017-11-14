package tr.zka;

public class App {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\zka\\Desktop\\CatCoder\\6 - LinkReader\\README.md";

        LinkFile linkFile = new LinkFile();
        linkFile.readFile(filePath);
        linkFile.findLinksInFile();
        linkFile.validateLinksAndRemove();
        linkFile.fetchLinksData();
        linkFile.printLinks();
    }
}
