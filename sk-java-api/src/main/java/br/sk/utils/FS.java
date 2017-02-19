package br.sk.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Optional<Stream<Path>> listDirectories(String path) {
		try {
			return Optional.of(Files.list(Paths.get(path)).filter(Files::isDirectory));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public Optional<Stream<Path>> listDirectoriesRecursively(String path) {
		try {
			return Optional.of(Files.walk(Paths.get(path)).filter(Files::isDirectory));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public Optional<Stream<Path>> listFiles(String path) {
		try {
			return Optional.of(Files.list(Paths.get(path)).filter(Files::isRegularFile));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public Optional<Stream<Path>> listFilesRecursively(String path) {
		try {
			return Optional.of(Files.walk(Paths.get(path)).filter(Files::isRegularFile));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public Optional<Stream<Path>> listFilesRecursively(Path path) {
		try {
			return Optional.of(Files.walk(path).filter(Files::isRegularFile));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return Optional.empty();
	}

	public boolean hasFile(String path, String fileName) {
		try {
			return Files.list(Paths.get(path)).filter(Files::isRegularFile).anyMatch(path1 -> path1.toFile().getName().equals(fileName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean hasFile(Path path, String fileName) {
		try {
			return Files.list(path).filter(Files::isRegularFile).anyMatch(path1 -> path1.toFile().getName().equals(fileName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean hasDirectory(Path path, String dirName) {
		try {
			return Files.list(path).filter(Files::isDirectory).anyMatch(path1 -> path1.toFile().getName().equals(dirName));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void mkdir(String... paths) {
		String finalPath = normalize(paths);
		if (Files.notExists(Paths.get(normalize(paths)))) {
			try {
				Files.createDirectories(Paths.get(finalPath));
				System.out.println("Diretório criado com sucesso: {}" + finalPath);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		System.out.println("Diretório já existe: {}" + finalPath);
	}

	public void createFile(String path, String content) {
		this.createParentDirsIfNotExists(path);
		if (Files.notExists(Paths.get(path))) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
				writer.write(content);
				writer.flush();
				writer.close();
				System.out.println("Arquivo criando com sucesso: {}" + path);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		System.out.println("Arquivo já existe: {}" + path);
	}

	public void appendFile(String path, String content) {
		this.createParentDirsIfNotExists(path);
		if (Files.exists(Paths.get(path))) {
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
				writer.write(content);
				writer.flush();
				writer.close();
				System.out.println("Arquivo atualizado com sucesso: {}" + path);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return;
		}
		System.out.println("Arquivo não existe: {}" + path);
	}

	public String normalize(String... paths) {
		return FilenameUtils.normalize(StringUtils.join(paths, "/"));
	}

	private void createParentDirsIfNotExists(String path) {
		if (StringUtils.isNotBlank(FilenameUtils.getExtension(path))) {
			String finalPath = FilenameUtils.getFullPath(path);
			this.mkdir(finalPath);
		}
	}
}
