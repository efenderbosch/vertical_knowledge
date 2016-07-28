package net.fender.vk.four;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Four {

	private static final Pattern IPv4 = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

	public static void main(String[] args) throws Exception {
		List<String> lines = Files.readAllLines(Paths.get("src/main/resources/domains.txt"));
		SortedMap<String, Integer> tlds = new TreeMap<>();
		for (String line : lines) {
			if (IPv4.matcher(line).lookingAt()) {
				continue;
			}
			int lastDot = StringUtils.lastIndexOf(line, ".");
			String tld = line.substring(lastDot + 1);
			Integer count = tlds.get(tld);
			if (count == null) {
				count = 0;
			}
			tlds.put(tld, ++count);
		}
		for (Map.Entry<String, Integer> entry : tlds.entrySet()) {
			// TODO file output to tlds.txt
			// System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		tlds.entrySet().stream().sorted((entry1, entry2) -> {
			int compare = entry1.getValue().compareTo(entry2.getValue());
			return compare != 0 ? compare : entry1.getKey().compareTo(entry2.getKey());
		});
		Map<String, Integer> scores = new HashMap<>();
		for (String line : lines) {
			int sum = 0;
			String lower = line.toLowerCase();
			int position = 1;
			for (byte c : lower.getBytes()) {
				if (!Character.isAlphabetic(c)) {
					continue;
				}
				sum = (c - 97) * position;
				position++;
			}
			scores.put(lower, sum);
		}
		tlds.entrySet().stream().sorted((entry1, entry2) -> {
			int compare = entry1.getValue().compareTo(entry2.getValue());
			return compare != 0 ? compare : entry1.getKey().compareTo(entry2.getKey());
		}).forEach(System.out::println);
	}

}
