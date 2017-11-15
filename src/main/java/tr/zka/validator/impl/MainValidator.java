package tr.zka.validator.impl;

import org.apache.commons.validator.routines.UrlValidator;
import tr.zka.validator.IHasParameters;
import tr.zka.validator.IHasPort;
import tr.zka.validator.IValidURL;

import java.net.MalformedURLException;
import java.net.URL;

public class MainValidator implements IValidURL, IHasPort, IHasParameters {

    //Checks if url is valid
    public boolean isValidURI(String link) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(link);
    }

    //Checks url has port
    public boolean hasPort(String link) {
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

    /** Checks url has parameters
     * ? -> for query
     * # -> for fragments
     **/
    public boolean hasParameters(String link) {
        if (link.contains("?") || link.contains("#"))
            return true;
        return false;
    }
}
