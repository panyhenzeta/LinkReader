package tr.zka.model;

/**
 * LinkContent is a model for representing url link
 * which has
 * url value as link
 * content
 * computed checksum
 * **/

public class LinkContent {
    private String link;
    private String content;
    private String checksum;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
