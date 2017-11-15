package tr.zka.cheksum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface IChecksum {
    MessageDigest getMessageDigest();

    void setMessageDigest(String messageDigest) throws NoSuchAlgorithmException;

    String computeCheksum(String content);
}
