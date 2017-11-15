package tr.zka.cheksum.impl;

import tr.zka.cheksum.IChecksum;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Checksum implements IChecksum {
    private final String MESSAGE_DIGEST = "MD5";
    private MessageDigest messageDigest;

    public Md5Checksum() throws NoSuchAlgorithmException {
        setMessageDigest(MESSAGE_DIGEST);
    }

    //compute Base64 Md5 Checksum
    @Override
    public String computeCheksum(String content) {
        String checksum = null;
        MessageDigest md = getMessageDigest();

        md.update(content.getBytes());
        checksum = new sun.misc.BASE64Encoder().encode(md.digest());
        return checksum;
    }

    @Override
    public MessageDigest getMessageDigest() {
        return messageDigest;
    }

    @Override
    public void setMessageDigest(String messageDigest) throws NoSuchAlgorithmException {
        try {
            this.messageDigest = MessageDigest.getInstance(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("No such algorithm like" + messageDigest);
        }
    }

}
