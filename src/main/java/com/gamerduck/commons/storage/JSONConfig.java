package com.gamerduck.commons.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;

public class JSONConfig {
	@Getter private File file;
	private JSONObject json;
	private JSONParser parser = new JSONParser();
	private HashMap<String, Object> defaults = new HashMap<String, Object>();


	public JSONConfig(File file) {
		this.file = file;
		reload();
	}

	public void add(String path, Object obj) {
		defaults.put(path, obj);
	}

	public String getRawData(String key) {
		return json.containsKey(key) ? json.get(key).toString()
				: (defaults.containsKey(key) ? defaults.get(key).toString() : key);
	}

	public String getString(String key) {return getRawData(key);}
	public boolean getBoolean(String key) {return Boolean.valueOf(getRawData(key));}
	public double getDouble(String key) {
		try {return Double.parseDouble(getRawData(key));}
		catch (Exception ex) {return -1;}
	}
	public double getInteger(String key) {
		try {return Integer.parseInt(getRawData(key));}
		catch (Exception ex) {return -1;}
	}

	public JSONObject getObject(String key) {
		return json.containsKey(key) ? (JSONObject) json.get(key)
				: (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
	}

	public JSONArray getArray(String key) {
		return json.containsKey(key) ? (JSONArray) json.get(key)
				: (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
	}
	public void reload() {
		try {	
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
				writeDefaultFile();
			}
			json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		} catch (Exception ex) {ex.printStackTrace();}
	}

	public void clear() {
		try {	
			if (file.exists()) {
				file.delete();
				file.createNewFile();
				writeDefaultFile();
			}
			json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		} catch (Exception ex) {ex.printStackTrace();}
	}

	private void writeDefaultFile() {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file, "UTF-8");
			pw.print("{");
			pw.print("}");
			pw.flush();
			pw.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {e.printStackTrace();} 
	}

	@SuppressWarnings("unchecked")
	public void save() {
		try {
			JSONObject toSave = new JSONObject();
			for (String s : defaults.keySet()) {
				Object o = defaults.get(s);
				if (o instanceof String) toSave.put(s, getString(s));
				else if (o instanceof Double) toSave.put(s, getDouble(s));
				else if (o instanceof Integer) toSave.put(s, getInteger(s));
				else if (o instanceof JSONObject) toSave.put(s, getObject(s));
				else if (o instanceof JSONArray) toSave.put(s, getArray(s));
			}
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
			treeMap.putAll(toSave);
			Gson g = new GsonBuilder().setPrettyPrinting().create();
			String prettyJsonString = g.toJson(treeMap);
			PrintWriter fw = new PrintWriter(file, "UTF-8");
			fw.write(prettyJsonString);
			fw.flush();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
