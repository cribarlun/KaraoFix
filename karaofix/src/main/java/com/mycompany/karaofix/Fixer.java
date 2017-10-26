/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.karaofix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
	public static void main(String[] args) throws IOException {

		Path path = Paths.get("C:\\Users\\TIC\\workspace Cristina\\KaraoFix\\karaofix\\src\\main\\resources\\temazos");
		Path copia = Paths
				.get("C:\\Users\\TIC\\workspace Cristina\\KaraoFix\\karaofix\\src\\main\\resources\\grandesexistos");

		List<Path> lista1 = new ArrayList<>();

		try {
			lista1 = Files.list(path).collect(Collectors.toList());
		} catch (IOException x) {
			System.out.println("Error al leer");
			x.printStackTrace();
		}
		
		
		List<Path> lista2= new ArrayList<>();
		for(Path p:lista1) {
			lista2.addAll(Files.walk(p).collect(Collectors.toList()));
		}
		
		//-*-------
		

		Charset charset = Charset.forName("UTF-8");
		String line = null;
		try (BufferedReader reader = Files.newBufferedReader(path);
				PrintWriter writer = new PrintWriter(Files.newBufferedWriter(copia))) {

			while (line != null) {
				System.out.println(line);
				writer.println(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error al leer");
			e.printStackTrace();
		}

	}
}
// files tiene un metodo que te dice los archivos que tiene dentro
// list<Path>
// walk hace un listado profundo
// Files.copy(p1,p2)
// Files.list(file).collect(Collectors.tolist())

//Files.createDirectories(path)// si la ruta no existe, los crea todos
//Files.delete(path)