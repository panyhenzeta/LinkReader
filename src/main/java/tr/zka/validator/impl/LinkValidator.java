package tr.zka.validator.impl;

public class LinkValidator extends MainValidator {

    public boolean isValidLink(String link) {
        if (!isValidURI(link) ||
                (isValidURI(link) && (hasPort(link) || hasParameters(link))))
            return false;
        return true;
    }
}
