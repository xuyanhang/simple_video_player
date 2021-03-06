package org.xuyh.service.videoplayer;

import java.util.LinkedList;

import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.xuyh.service.ApplicationReadyInitializer;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

@org.springframework.stereotype.Service
public class VlcjSimpleVideoPlayerService {

	@org.springframework.stereotype.Component
	static public class VlcjSimpleVideoPlayerServiceReadyInitializer implements ApplicationReadyInitializer {

		@Autowired
		VlcjSimpleVideoPlayerService vlcjSimpleVideoPlayerService;

		/**
		 * 
		 */
		public VlcjSimpleVideoPlayerServiceReadyInitializer() {
			super();
		}

		@Override
		public void initialize() {
			vlcjSimpleVideoPlayerService.actionPlay();
		}
		
	}

	@Value("${icon.image.filepath}")
	String iconImageFilePath;

	@Value("${video.filepath.filelistpath}")
	String videoFilesListFilePath;

	/**
	 * 
	 */
	public VlcjSimpleVideoPlayerService() {
		super();

		new NativeDiscovery().discover();
	}

	public void actionPlay() {
		LinkedList<String> videoPaths = new VideoFilesPathLoader().loadVideoPaths(videoFilesListFilePath);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VlcjSimpleVideoPlayer(videoPaths.toArray(new String[videoPaths.size()]), iconImageFilePath);
			}
		});
	}

}
