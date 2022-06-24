package com.gamerduck.commons.general;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.plugin.Plugin;

public class Files {
	public static void updatePropertyFiles(Plugin plugin, String fileName) throws IOException {
		File tempFile = new File(plugin.getDataFolder(), fileName);
		File tempCopy = new File(plugin.getDataFolder(), "temp.properties");
		List<String> tempList = com.google.common.io.Files.readLines(getFileFromResourceAsStream(plugin, fileName, tempCopy), StandardCharsets.UTF_8);
		HashMap<String, String> masterlist = new HashMap<String, String>();
		tempList.stream().forEachOrdered(s -> {masterlist.put(s.split("=")[0], s);});
		tempCopy.delete();

		HashMap<String, String> clientlist = new HashMap<String, String>();
		tempList = com.google.common.io.Files.readLines(tempFile, StandardCharsets.UTF_8);
		tempList.stream().forEachOrdered(s -> clientlist.put(s.split("=")[0], s));

		List<String> missingKeys = masterlist.keySet().stream().filter(st -> !clientlist.containsKey(st)).collect(Collectors.toList());
		missingKeys.stream().forEachOrdered(st -> clientlist.put(st, masterlist.get(st)));

		List<String> excessKeys = clientlist.keySet().stream().filter(st -> !masterlist.containsKey(st)).collect(Collectors.toList());
		excessKeys.stream().forEachOrdered(st -> clientlist.remove(st));

		tempFile.delete();
		tempFile.createNewFile();
		FileWriter fw = new FileWriter(tempFile);
        BufferedWriter out = new BufferedWriter(fw);
        clientlist.keySet().stream().forEachOrdered(t -> {
			try {out.write(clientlist.get(t)); out.newLine();}
			catch (IOException e) {e.printStackTrace();}
		});
        out.flush();
        out.close();
	}

    private static File getFileFromResourceAsStream(Plugin plugin, String fileName, File file) throws IOException {

        // The class loader that loaded the class
        ClassLoader classLoader = plugin.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        try (OutputStream output = new FileOutputStream(file, false)) {
            inputStream.transferTo(output);
        }
        return file;
    }
}
