package moe.exusiai.netty;

import moe.exusiai.TinyReverseProxy;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.KeyStore;

public class SSLContextProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(SSLContextProvider.class);
    private static SSLContext sslContext = null;
    public static SSLContext get() {
        if(sslContext==null) {
            synchronized (SSLContextProvider.class) {
                if(sslContext==null) {
                    try {
                        sslContext = SSLContext.getInstance("TLS");
                        KeyStore ks = KeyStore.getInstance("PKCS12");
                        ks.load(new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(TinyReverseProxy.folder.getPath() + "/" + TinyReverseProxy.certname))), TinyReverseProxy.certpassword.toCharArray());
                        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                        kmf.init(ks, TinyReverseProxy.certpassword.toCharArray());
                        sslContext.init(kmf.getKeyManagers(), null, null);
                    } catch (Exception e) {
                        LOGGER.error("SSL Cert Error", e);
                    }
                }
            }
        }
        return sslContext;
    }
}
