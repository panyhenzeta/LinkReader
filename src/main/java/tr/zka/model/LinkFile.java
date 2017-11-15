package tr.zka.model;

import java.util.List;

public class LinkFile {
    private String path;
    private String content;
    private List<LinkContent> linkContentList;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<LinkContent> getLinkContentList() {
        return linkContentList;
    }

    public void setLinkContentList(List<LinkContent> linkContentList) {
        this.linkContentList = linkContentList;
    }
}
