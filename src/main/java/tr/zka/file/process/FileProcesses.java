package tr.zka.file.process;

import tr.zka.cheksum.impl.Md5Checksum;
import tr.zka.model.LinkContent;
import tr.zka.model.LinkFile;
import tr.zka.service.LinkService;
import tr.zka.validator.impl.LinkValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcesses {
    private static LinkFile linkFile;

    private final String URL_REGEX = "((https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
    private final String ANSI_RED = "\u001B[31m";

    public FileProcesses() {
        linkFile = new LinkFile();
    }

    public void getFilePathFromUser() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter file path: ");
        String filePath = sc.nextLine();

        linkFile.setPath(filePath);
        readFile(filePath);
    }

    private void readFile(String filePath) throws FileNotFoundException {
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
            throw new FileNotFoundException("There is no file path like " + filePath);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void findLinksInFile() {
        String content = linkFile.getContent();
        Pattern pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(content);

        List<LinkContent> linkContentList = new ArrayList<>();
        LinkContent linkContent = null;
        String url;
        while (urlMatcher.find()) {
            url = content.substring(urlMatcher.start(0), urlMatcher.end(0));
            linkContent = createLinkContentWithLink(url);
            linkContentList.add(linkContent);
        }
        linkFile.setLinkContentList(linkContentList);
        validateLinksAndRemove();
    }

    private LinkContent createLinkContentWithLink(String url) {
        LinkContent linkContent = new LinkContent();
        linkContent.setLink(url);
        return linkContent;
    }

    private void validateLinksAndRemove() {
        List<LinkContent> linkContents = linkFile.getLinkContentList();

        if (linkContents != null && linkContents.size() > 0) {
            Predicate<LinkContent> linkPredicate = l -> !new LinkValidator().isValidLink(l.getLink());
            linkContents.removeIf(linkPredicate);
        }
    }

    /**
     * execute thread with available cpu cores
     */
    public void fetchLinksData() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<String> content;
        for (LinkContent linkContent : linkFile.getLinkContentList()) {
            System.out.println("Fetching data from link " + linkContent.getLink());
            try {
                content = executorService.submit(new LinkService(linkContent.getLink()));
                linkContent.setContent(content.get());
                linkContent.setChecksum(new Md5Checksum().computeCheksum(content.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fetched datas");
    }


    public void printLinks() {
        for (LinkContent linkContent : linkFile.getLinkContentList()) {
            System.out.println("Link: " + linkContent.getLink());
            System.out.println("Checksum: " + linkContent.getChecksum());
            System.out.println();
        }
    }

    public LinkFile getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(LinkFile linkFile) {
        this.linkFile = linkFile;
    }


}
