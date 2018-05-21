package noobchain;

import java.security.Security;
import java.util.ArrayList;
//import java.util.Base64;
import java.util.HashMap;
//import com.google.gson.GsonBuilder;
import java.util.Map;

public class NoobChain {

	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	public static Wallet walletA;
	public static Wallet walletB;
	
	
	public static void main(String[] args) {
		//Setup bouncy castle as a security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); //Setup Bouncey castle as a Security Provider
		//Create new wallets
		walletA = new Wallet();
		walletB = new Wallet();
		//Test public and private keys
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		//Create a test transaction from WalletA to WalletB
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		//Verify the signature works and verify it from the public key
		System.out.println("Is signature verified");
		System.out.println(transaction.verifySignature());
		
		
		/*
		
		Block genesisBlock = new Block("Hi im the first block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);
		
		Block secondBlock = new Block("Yo im the second block", genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);
		
		Block thirdBlock = new Block("Hey im the third block", secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
	
		//String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		//System.out.println(blockchainJson);
		*/
		/*
		//Blockchain is the array and this is adding a new object to the array using the Block constructor
		//with the parameters data, hash; 0 is used the first time because there is no previous
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to mine block 1... ");
		//mine a block and 'get' index 0 of the array and store is there
		blockchain.get(0).mineBlock(difficulty);
		
		blockchain.add(new Block("Hi im the second block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		blockchain.add(new Block("Hi im the third block", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);	
	
		System.out.println("\nBlockchain is valid : " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nNew block chain : ");
		System.out.println(blockchainJson);
		*/
	}
	public static Boolean isChainValid() {
		Block currentBlock;
		Block previousBlock;
		
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//Loop through blockchain to check hashes
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);
			//compare registered hash and previous hash
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			if (!previousBlock.hash.equals(previousBlock.calculateHash())) {
				System.out.println("Previous Hashes not equal");
				return false;
			}	
		}
		return true;
	}
}
