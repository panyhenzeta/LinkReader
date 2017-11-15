package tr.zka;

import tr.zka.cheksum.Md5Checksum;
import tr.zka.model.LinkContent;
import tr.zka.service.LinkService;
import tr.zka.validator.LinkValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkFile {
    private String fileContent;
    private List<LinkContent> linkContentList;
    private final String URL_REGEX = "((https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
    private final String MESSAGE_DIGEST = "MD5";

    public LinkFile() {
        linkContentList = new ArrayList<>();
    }

    public void readFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                sb.append(sCurrentLine);
                sb.append(System.lineSeparator());
            }
            setFileContent(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findLinksInFile() {
        String content = getFileContent();
        Pattern pattern = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(content);

        LinkContent linkContent;
        String url;
        while (urlMatcher.find()) {
            url = content.substring(urlMatcher.start(0), urlMatcher.end(0));
            linkContent = createLinkContentWithLink(url);
            linkContentList.add(linkContent);
        }
    }

    private LinkContent createLinkContentWithLink(String url) {
        LinkContent linkContent = new LinkContent();
        linkContent.setLink(url);
        return linkContent;
    }

    public void printLinks() {
        for (LinkContent linkContent : linkContentList) {
            System.out.println(linkContent.getLink());
            System.out.println(linkContent.getChecksum());
        }
    }

    public void validateLinksAndRemove() {
        Predicate<LinkContent> linkPredicate = l -> !new LinkValidator().isValidLink(l.getLink());
        linkContentList.removeIf(linkPredicate);
    }

    public void fetchLinksData() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<String> content;
        for (LinkContent linkContent : linkContentList) {
            try {
                content = executorService.submit(new LinkService(linkContent.getLink()));
                linkContent.setContent(content.get());
                linkContent.setChecksum(new Md5Checksum().computeCheksum(content.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public List<LinkContent> getLinkContentList() {
        return linkContentList;
    }

    public void setLinkContentList(List<LinkContent> linkContentList) {
        this.linkContentList = linkContentList;
    }
}
