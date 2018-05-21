package noobchain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Wallet {

		public PrivateKey privateKey;
		public PublicKey publicKey;
		
		public Wallet(){
			generateKeyPair();
			
		}
		
		public void generateKeyPair() {
			try {
				KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
				//Initialise the key generator and generate a key pair
				keyGen.initialize(ecSpec, random); //256 bytes provides an acceptable security level
				KeyPair keyPair = keyGen.generateKeyPair();
				//Set the public and private keys from the key pair
				privateKey = keyPair.getPrivate();
				publicKey = keyPair.getPublic();
			}
			catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		
}
