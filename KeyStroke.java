import java.io.*;
import java.security.*;
import java.security.cert.*;
import javax.crypto.*;

public class KeyStoreManager {
    private static final String KEYSTORE_TYPE = "JCEKS";
    private static final String KEYSTORE_FILE = "/home/zemoso/keystore.ks";
    private static final String KEYSTORE_PASSWORD = "keystorepassword";


    private SecretKey createSecretKey() throws Exception {
        KeyGenerator keyGenerator = null;
        keyGenerator = KeyGenerator.getInstance("DES");
        //keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    private KeyStore getKeyStore(String keyStoreName, char[] password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(KEYSTORE_TYPE);
        InputStream stream = null;
        keyStore.load(stream, password);
        return keyStore;
    }

    public static void main(String[] args) throws Exception {
        File keyStoreFile = new File(KEYSTORE_FILE);
        if (keyStoreFile.exists() && keyStoreFile.isFile()) {
            keyStoreFile.delete();
        }

        KeyStoreManager ksm = new KeyStoreManager();

        KeyStore keyStore = ksm.getKeyStore(KEYSTORE_FILE, KEYSTORE_PASSWORD.toCharArray());
        SecretKey secretKey = ksm.createSecretKey();
        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection("entrypassword".toCharArray());
        keyStore.setEntry("SecretKeyAlias", secretKeyEntry, passwordProtection);
        keyStore.store(new FileOutputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());

        System.out.println("Added key into store");
    }
}
