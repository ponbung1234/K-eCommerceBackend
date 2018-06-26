package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BrTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("test.json")));
		String returnResult = "";

		while (true) {
			final String line = br.readLine();
			if (line == null)
				break;

			returnResult += line;
		}
		br.close();
		System.out.println(returnResult);

	}
}
