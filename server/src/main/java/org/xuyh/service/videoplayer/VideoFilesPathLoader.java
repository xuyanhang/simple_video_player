package org.xuyh.service.videoplayer;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.xuyh.util.UrlBuilder;

public class VideoFilesPathLoader {

	String[] allowedSuffix = { "avi", "mp4", "mov", "mkv", "wmv", "m4v", "mpeg", "ogv", "3gp", "flv", "f4v", "swf",
			"mp3", "ac3", "wav", "m4v", "m4a", "wma", "aac", "ape", "swk", "bmp", "png", "jpg", "jpeg", "gif" };

	/**
	 * 
	 */
	public VideoFilesPathLoader() {
		super();
	}

	public LinkedList<String> loadVideoPaths(String listFilePath) {
		LinkedList<String> seatchVideoPaths = new LinkedList<>();
		File file = new File(listFilePath);
		if (file.exists() && file.isFile()) {
			try (Scanner scanner = new Scanner(file)) {
				while (scanner.hasNextLine()) {
					String nextLine = scanner.nextLine().trim();
					if (!nextLine.isEmpty()) {
						seatchVideoPaths.add(nextLine);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		LinkedList<String> videoPaths = new LinkedList<>();
		for (String seatchVideoPath : seatchVideoPaths) {
			File seatchVideoFile = new File(seatchVideoPath);
			if (seatchVideoFile.exists()) {
				listVideoPaths(seatchVideoFile, videoPaths);
			} else if (null != UrlBuilder.from(seatchVideoPath)) {
				if (!videoPaths.contains(seatchVideoPath)) {
					videoPaths.add(seatchVideoPath);
				}
			}
		}

		return videoPaths;
	}

	private void listVideoPaths(File file, List<String> videoPaths) {
		if (file.isDirectory()) {
			File[] subfiles = file.listFiles();
			if (null == subfiles) {
				return;
			}
			for (int i = 0; i < subfiles.length; i++) {
				File subfile = subfiles[i];
				if (null == subfile) {
					continue;
				}
				listVideoPaths(subfile, videoPaths);
			}
		} else if (file.isFile()) {
			if (file.canRead()) {
				String filePath = file.getAbsolutePath();
				if (isAllowVideoFile(filePath)) {
					if (!videoPaths.contains(filePath)) {
						videoPaths.add(filePath);
					}
				}
			}
		}
	}

	private boolean isAllowVideoFile(String filePath) {
		int lastPoint = filePath.lastIndexOf('.');
		if (lastPoint <= 0) {
			return false;
		}
		String suffix = filePath.substring(lastPoint + 1).toLowerCase();
		for (int i = 0; i < allowedSuffix.length; i++) {
			if (suffix.equals(allowedSuffix[i])) {
				return true;
			}
		}
		return false;
	}

}
