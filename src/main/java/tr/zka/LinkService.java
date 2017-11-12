package tr.zka;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LinkService implements Runnable{
    private LinkContent linkContent;

    private final int BUFFER_SIZE = 10000;
    private final String MESSAGE_DIGEST = "MD5";

    public LinkService(LinkContent linkContent){
        this.linkContent = linkContent;
    }

    @Override
    public void run() {
        try {
            sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendGet() throws Exception {
        String url = linkContent.getLink();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        InputStream is = response.getEntity().getContent();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[BUFFER_SIZE];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        buffer.toByteArray();

        System.out.println(url);
        setLinkContent(buffer.toString());
    }

    private void setLinkContent(String content){
        linkContent.setContent(content);
        linkContent.setMd5Checksum(computeMd5Checksum(content));
    }

    public String computeMd5Checksum(String content) {
        String checksum = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MESSAGE_DIGEST);
            md.update(content.getBytes());
            checksum = new sun.misc.BASE64Encoder().encode(md.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        System.out.println(checksum);
        return checksum;
    }


}
