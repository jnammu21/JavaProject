package com.Decentralized.action;

import java.math.BigInteger;
import java.util.Random;

public class RandomDemo {

	public static void main(String[] args) {

		int isignature;

		String test = "zxwyrkmv2vlf1g7g";

		Random random = new Random();
		isignature = (int) (Math.random() * 100000 + 5123400000L);
		int randage = random.nextInt(99);

		System.out.println("" + randage);

		RandomDemo rd = new RandomDemo();
		String t = rd.nextSessionId();
        System.out.println(t);

	}

	public String nextSessionId() {
		Random random = new Random();
		return new BigInteger(130, random).toString(32);

	}

}
