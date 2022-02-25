package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.blockchain.Block;

@RestController
public class Controller {
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	@GetMapping(path = "/")
	public List<Block> printBlocks() {
		// Adding the data to the ArrayList
				
				if(blockchain.size() <= 0) {
					blockchain.add(new Block(
							"Block 1", "0"));
				}
				else {
					blockchain.add(new Block(
							"Block "+blockchain.size(),
							 blockchain
							.get(blockchain.size() - 1)
							.hash));	
				}
				
				return blockchain;
	}
	@GetMapping(path = "/validate")
	public String validation() {
		
		return isChainValid()+"";
	}
	
	public static  Boolean isChainValid()
	{
		Block currentBlock;
		Block previousBlock;

		// Iterating through
		// all the blocks
		for (int i = 1;
			i < blockchain.size();
			i++) {

			// Storing the current block
			// and the previous block
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i - 1);

			// Checking if the current hash
			// is equal to the
			// calculated hash or not
			if (!currentBlock.hash
					.equals(
						currentBlock
							.calculateHash())) {
				System.out.println(
					"Hashes are not equal");
				return false;
			}

			// Checking of the previous hash
			// is equal to the calculated
			// previous hash or not
			if (!previousBlock
					.hash
					.equals(
						currentBlock
							.previousHash)) {
				System.out.println(
					"Previous Hashes are not equal");
				return false;
			}
		}

		// If all the hashes are equal
		// to the calculated hashes,
		// then the blockchain is valid
		return true;
	}

}
