package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJson {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		byte[] mapData = Files.readAllBytes(Paths.get("test.json"));
		List<Map<String, String>> map = mapper.readValue(mapData, new TypeReference<List<Map<String, String>>>() {
		});
		int i = 0;
		while (i < map.size()) {
			System.out.println(map.get(i).get("price"));
			i++;
		}
	}
}
