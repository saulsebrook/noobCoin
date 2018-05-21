package noobchain;

import java.util.Date;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.*;
import java.util.*;

public class Block {

		public String hash;
		public String previousHash;
		private String data; //Data will be a simple message
		private long timeStamp; //as number of milliseconds
		private int nonce;
		
		//Block Constructor
		public Block(String data, String previousHash) {
			this.data = data;
			this.previousHash = previousHash;
			this.timeStamp = new Date().getTime();		
			this.hash = calculateHash(); //Making sure we do this after we set the other values
		}
		public String calculateHash() {
			String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
			return calculatedhash;
		}
		public void mineBlock(int difficulty) {
			String target = new String(new char[difficulty]).replace('\0',  '0');//Create a string with difficulty *0
			while(!hash.substring( 0, difficulty).equals(target)) {
				nonce ++;
				hash = calculateHash();
				
			}
			System.out.println("Block Mined!!! : " + hash);
				
		}
	
}
