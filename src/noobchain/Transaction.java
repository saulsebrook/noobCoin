package noobchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.*;
import java.util.*;

public class Transaction {
		
		public String transactionID; //This is also the hash of the transaction
		public PublicKey sender; //senders address/public key
		public PublicKey recipient; //Recipients address/public key
		public float value;
		public byte[] signature; // This is to prevent other people spending funds in out wallet
		
		public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
		
		private static int sequence = 0; // a rough count of how many transactions have been generated
		
		//Constructor
		public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
			this.sender = from;
			this.recipient = to;
			this.value = value;
			this.inputs = inputs;
			
		}
		//This calculates the transaction hash
		public String calulateHash() {
			sequence++; //Increase the sequence to avoid 2 identical transactions having the same hash
			return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value) + sequence);
			
		}
		//Signs all the data we dont want to be tampered with
		public void generateSignature(PrivateKey privateKey) {
			String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value);
			signature = StringUtil.applyECDSASig(privateKey, data);
			
		}
		//Verifies the data we signed hasnt been tampered with
		public boolean verifySignature() {
			String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float(value);
			return StringUtil.verifyECDSASig(sender, data, signature);
			
		}
}
