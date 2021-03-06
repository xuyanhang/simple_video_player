package org.xuyh.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Tool to action operations on IO Stream and files.
 * 
 * @author XuYanhang
 *
 */
public class FileUtil {

	/**
	 * Read a file as bytes.
	 */
	public static byte[] read(String filePath) {

		if (null == filePath) {
			return null;
		}

		return read(new File(filePath));
	}

	/**
	 * Read a file as bytes.
	 */
	public static byte[] read(File file) {

		if (null == file || !file.exists() || !file.isFile()) {
			return null;
		}

		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (Exception e) {
			return null;
		}

		return read(inputStream);
	}

	/**
	 * Cast an input stream as bytes.
	 */
	public static byte[] read(InputStream inputStream) {

		if (null == inputStream) {
			return null;
		}

		try {
			int initSize = 1024;
			try {
				initSize = Math.max(inputStream.available(), initSize);
			} catch (Exception e) {
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(initSize);
			byte[] buffer = new byte[1024];
			int len = -1;
			while (-1 != (len = inputStream.read(buffer))) {
				outputStream.write(buffer, 0, len);
			}
			return outputStream.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * Read the text on file.
	 */
	public static String readText(String filePath, String charset) {
		if (null == filePath) {
			return null;
		}
		return readText(new File(filePath), charset);
	}

	/**
	 * Read the text on file.
	 */
	public static String readText(File file, String charset) {

		if (null == file || !file.exists() || !file.isFile()) {
			return null;
		}

		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (Exception e) {
			return null;
		}

		return readText(inputStream, charset);
	}

	/**
	 * Read the text.
	 */
	public static String readText(InputStream inputStream, String charset) {
		if (null == inputStream) {
			return null;
		}
		BufferedReader reader = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(1024);
			if (null != charset) {
				try {
					reader = new BufferedReader(new InputStreamReader(inputStream, charset));
				} catch (UnsupportedEncodingException e) {
				}
			}
			if (null == reader) {
				reader = new BufferedReader(new InputStreamReader(inputStream));
			}
			char[] buffer = new char[1024];
			for (int len = -1; (len = reader.read(buffer)) != -1;) {
				stringBuilder.append(buffer, 0, len);
			}
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		} finally {
			try {
				reader.close();
			} catch (Exception e) {
			}
			try {
				inputStream.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Create an input stream on local input stream.
	 */
	public static InputStream createLocalStream(String srcLocation) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (null == classLoader) {
			classLoader = FileUtil.class.getClassLoader();
			if (null == classLoader) {
				classLoader = ClassLoader.getSystemClassLoader();
			}
		}
		return classLoader.getResourceAsStream(srcLocation);
	}

	/**
	 * Read the local resource data.
	 */
	public static byte[] readLocal(String srcLocation) {
		InputStream inputStream = createLocalStream(srcLocation);
		if (null == inputStream) {
			return null;
		}
		return read(inputStream);
	}

	/**
	 * Read the local text.
	 */
	public static String readLocalText(String srcLocation, String charset) {
		InputStream inputStream = createLocalStream(srcLocation);
		if (null == inputStream) {
			return null;
		}
		return readText(inputStream, charset);
	}

	/**
	 * Write a data to file.
	 */
	public static boolean write(byte[] data, String filePath) {
		if (null == filePath) {
			return false;
		}
		return write(data, new File(filePath));
	}

	/**
	 * Write a data to file.
	 */
	public static boolean write(byte[] data, File file) {
		if (null == file || file.isDirectory()) {
			return false;
		}
		try (OutputStream out = new FileOutputStream(file)) {
			return write(data, new FileOutputStream(file));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Write a data to output stream.
	 */
	public static boolean write(byte[] data, OutputStream out) {

		if (null == data) {
			return false;
		}

		try {
			out = new BufferedOutputStream(out);
			out.write(data);
			out.flush();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Write a text to file.
	 */
	public static boolean writeText(String text, String filePath, String charset) {
		if (null == filePath) {
			return false;
		}
		return writeText(text, new File(filePath), charset);
	}

	/**
	 * Write a text to file.
	 */
	public static boolean writeText(String text, File file, String charset) {
		if (null == file || file.isDirectory()) {
			return false;
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try (OutputStream out = new FileOutputStream(file)) {
			return writeText(text, out, charset);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Write a text to output stream.
	 */
	public static boolean writeText(String text, OutputStream out, String charset) {

		if (null == text) {
			return false;
		}

		BufferedWriter writer = null;
		if (null != charset) {
			try {
				writer = new BufferedWriter(new OutputStreamWriter(out, charset));
			} catch (UnsupportedEncodingException e1) {
			}
		}
		if (null == writer) {
			writer = new BufferedWriter(new OutputStreamWriter(out));
		}
		try {
			writer.write(text);
			writer.flush();
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Copy a file to another File.
	 * 
	 * @throws Exception
	 */
	public static void copyValidFile(File fromFile, File toFile) throws Exception {
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fromFile));
			out = new BufferedOutputStream(new FileOutputStream(toFile));
			byte[] buffer = new byte[1024];
			for (int len = -1; (len = in.read(buffer)) != -1;) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * Copy a director to another director.
	 * 
	 * @throws Exception
	 */
	public static void copyDir(File fromDir, File toDir) throws Exception {
		if (!toDir.exists()) {
			toDir.mkdirs();
		}
		File[] fromFiles = fromDir.listFiles();
		for (File fromFile : fromFiles) {
			if (!fromFile.canRead()) {
				continue;
			}
			File toFile = new File(toDir, fromFile.getName());
			if (fromFile.isDirectory()) {
				copyDir(fromFile, toFile);
			} else if (fromFile.isFile()) {
				copyValidFile(fromFile, toFile);
			}
		}
	}

	/**
	 * Copy file-file file-dir dir-dir.
	 * 
	 * @throws Exception
	 */
	public static void copyFile(File fromFile, File toFile) throws Exception {
		if (fromFile.isDirectory()) {
			copyDir(fromFile, toFile);
		} else if (fromFile.isFile()) {
			File targetFile = toFile.isDirectory() ? new File(toFile, fromFile.getName()) : toFile;
			copyValidFile(fromFile, targetFile);
		}
	}

	/**
	 * Copy a file or director into another file or director.
	 * 
	 * @throws Exception
	 */
	public static void copyFileInto(File fromFile, File toDir) throws Exception {
		if (!toDir.exists()) {
			toDir.mkdirs();
		}
		File toFile = new File(toDir, fromFile.getName());
		if (fromFile.isDirectory()) {
			copyDir(fromFile, toFile);
		} else {
			copyValidFile(fromFile, toFile);
		}
	}

	/**
	 * Delete a file or director.
	 * 
	 * @param file
	 */
	public static boolean deleteFile(File file) {
		if (null == file || !file.exists()) {
			return false;
		}
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if (null != subFiles) {
				for (File subFile : subFiles) {
					deleteFile(subFile);
				}
			}
		}
		return file.delete();
	}

	/**
	 * Move a file as another file.
	 */
	public static boolean moveFile(File fromFile, File toFile) {
		if (!fromFile.exists()) {
			return false;
		}

		deleteFile(toFile);

		if (!fromFile.renameTo(toFile)) {
			// The rename method may be fail then use the copy method.
			try {
				copyFile(fromFile, toFile);
				deleteFile(fromFile);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
