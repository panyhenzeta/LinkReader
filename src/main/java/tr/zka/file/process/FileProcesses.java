package tr.zka.file.process;

import tr.zka.model.LinkFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileProcesses {
    private static LinkFile linkFile;
    public static final String ANSI_RED = "\u001B[31m";

    public FileProcesses() {
        linkFile = new LinkFile();
    }

    public void getFilePathFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter file path: ");
        String filePath = sc.nextLine();

        linkFile.setPath(filePath);
        readFile(filePath);
    }

    private void readFile(String filePath)  {
        try (FileReader fr = new FileReader(filePath)) {
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
                sb.append(System.lineSeparator());
            }
            linkFile.setContent(sb.toString());
        } catch (FileNotFoundException fnf) {
            System.out.println(ANSI_RED + "There is no file path like " + filePath + ANSI_RED);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public LinkFile getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(LinkFile linkFile) {
        this.linkFile = linkFile;
    }


}
