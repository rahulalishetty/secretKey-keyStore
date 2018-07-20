import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class KeyStroke {
    public static void main(String[] args) throws IOException {
        try {
            KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
            keyGenerator.init(64);
            SecretKey key=keyGenerator.generateKey();
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            FileInputStream fis=null;
            char[] password="keystorepassword".toCharArray();
            try{
                fis= new FileInputStream("/home/zemoso/desKeyStore");
                keyStore.load(fis,password);
            }catch (CertificateException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(fis!=null)
                    fis.close();
            }
        } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
        } catch (KeyStoreException e1) {
                e1.printStackTrace();
        }
    }
}
