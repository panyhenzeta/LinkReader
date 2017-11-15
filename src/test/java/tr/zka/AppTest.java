package tr.zka;

import junit.framework.TestCase;
import tr.zka.cheksum.impl.Md5Checksum;
import tr.zka.validator.impl.LinkValidator;

import java.security.NoSuchAlgorithmException;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase

{
    private String content = "Hello World";
    private String checksum = "sQqNsWTgdUEFt6mb5y4/5Q==";

    private String url = "https://stackoverflow.com/questions/6119392/junit-testing-for-a-boolean-method";

    public void testChecksum() throws NoSuchAlgorithmException {
        Md5Checksum md5Checksum = new Md5Checksum();

        String generatedChecksum = md5Checksum.computeCheksum(content);

        assertTrue(checksum.equals(generatedChecksum) || generatedChecksum.equals(checksum));

    }

    public void testLinkValidator() {
        boolean isValid = new LinkValidator().isValidLink(url);
        assertEquals(isValid, true);
    }


}
