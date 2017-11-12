package tr.zka;

import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkValidator {

    public static boolean isValidLink(String link) {
        if (!isValidURI(link) ||
                (isValidURI(link) && (hasPort(link) || hasParameters(link))))
            return false;
        return true;
    }

    private static boolean isValidURI(String link) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(link);
    }

    private static boolean hasPort(String link) {
        boolean hasPort = true;
        try {
            URL url = new URL(link);
            if (url.getPort() == -1)
                hasPort = false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return hasPort;
    }

    private static boolean hasParameters(String link) {
        if (link.contains("?") || link.contains("#"))
            return true;
        return false;
    }
}
