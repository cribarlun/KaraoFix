/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.karaofix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author japar
 */

public class Fixer {

	public static void copiarArchivos(String origen) {

		Path pathOrigen = Paths.get(origen);
		Charset charset = Charset.forName("ISO-8859-15");
		List<Path> carpetasOrigen = new ArrayList<>();
		List<Path> archivosOrigen = new ArrayList<>();
		BufferedReader bufferedReader;
		BufferedWriter bufferedWriter;
		String line = "";
		Path pathDestino;

		try {
			carpetasOrigen = Files.list(pathOrigen).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Path p : carpetasOrigen) {
			try {
				archivosOrigen.addAll(Files.walk(p).collect(Collectors.toList()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Path p : archivosOrigen) {

			pathDestino = Paths.get(p.toString().replace("temazos", "grandesexistos"));
			try {
				Files.copy(p, pathDestino);
				if (p.toString().endsWith(".txt")) {
					bufferedReader = Files.newBufferedReader(p, charset);
					bufferedWriter = Files.newBufferedWriter(pathDestino, charset);
					
					while ((line = bufferedReader.readLine()) != null) {
						if (line.contains("#GAP") || line.contains("#BPM")) {
							String[] parts = line.split(":");
							Double decimal = Double.valueOf(parts[1].replace(",", ".")).doubleValue();
							Integer entero = (int) Math.round(decimal);
							line = parts[0] + ":" + String.valueOf(entero);
						}
						bufferedWriter.write(line + "\r\n");
					}
					bufferedWriter.close();
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) throws IOException {

		copiarArchivos("C:\\Users\\Cristina\\Downloads\\KaraoFix-master\\karaofix\\src\\main\\resources\\temazos");
	}
}