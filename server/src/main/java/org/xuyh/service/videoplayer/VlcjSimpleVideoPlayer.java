package org.xuyh.service.videoplayer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayer;

public class VlcjSimpleVideoPlayer {

	static {
	}

	public static void actionPlay(String[] videoPaths, String iconImagePath) {

		new NativeDiscovery().discover();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VlcjSimpleVideoPlayer(videoPaths, iconImagePath);
			}

		});
	}

	String[] videoSources;
	int currentVideoIndex = 0;
	boolean fullScreen = true;

	JFrame frame;
	EmbeddedMediaPlayerComponent mediaPlayerComponent;

	public VlcjSimpleVideoPlayer(String[] videoSources, String iconImagePath) {

		super();

		this.videoSources = videoSources;

		frame = new JFrame("Media Player");
		frame.setIconImage(createIconImage(iconImagePath));
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {

			private static final long serialVersionUID = -6952503983616201376L;

			@Override
			public void videoOutput(MediaPlayer mediaPlayer, int newCount) {
				VlcjSimpleVideoPlayer.this.adjustWindowSize();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					VlcjSimpleVideoPlayer.this.backwardSkip();
					break;
				case KeyEvent.VK_UP:
					VlcjSimpleVideoPlayer.this.increaseVolumn();
					break;
				case KeyEvent.VK_RIGHT:
					VlcjSimpleVideoPlayer.this.forwardSkip();
					break;
				case KeyEvent.VK_DOWN:
					VlcjSimpleVideoPlayer.this.decreaseVolumn();
					break;
				case KeyEvent.VK_PAGE_UP:
					VlcjSimpleVideoPlayer.this.playPreviews();
					break;
				case KeyEvent.VK_PAGE_DOWN:
					VlcjSimpleVideoPlayer.this.playNext();
					break;
				case KeyEvent.VK_SPACE:
					VlcjSimpleVideoPlayer.this.reversePause();
					break;
				case KeyEvent.VK_ENTER:
					VlcjSimpleVideoPlayer.this.reverseFullScreen();
					break;
				case KeyEvent.VK_ESCAPE:
					VlcjSimpleVideoPlayer.this.shutdown();
					break;
				default:
					break;
				}
			}

		};

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setContentPane(mediaPlayerComponent);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				VlcjSimpleVideoPlayer.this.shutdown();
			}
		});

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);

		mediaPlayerComponent.getVideoSurface().requestFocusInWindow();

		playCurrent();
	}

	private Image createIconImage(String iconUrl) {
		if (null == iconUrl) {
			return null;
		}
		return frame.getToolkit().getImage(iconUrl);
	}

	public void adjustWindowSize() {
		if (fullScreen) {
			displayAsFullScreen();
		} else {
			displayAsBestWindowSize();
		}
	}

	public void reverseFullScreen() {
		fullScreen = !fullScreen;
		adjustWindowSize();
	}

	private void displayAsBestWindowSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension mediaSize = mediaPlayerComponent.getMediaPlayer().getVideoDimension();
		if (null == mediaSize) {
			mediaSize = new Dimension(screenSize.width / 2, screenSize.height / 2);
		}
		int mw = screenSize.width;
		int mh = screenSize.height;
		mw = (int) Math.min(mw, ((long) mediaSize.width) * mh / mediaSize.height);
		mh = (int) Math.min(mh, ((long) mediaSize.height) * mw / mediaSize.width);
		int x = (Math.max(0, mw - mediaSize.width) - mw + screenSize.width) / 2;
		int y = (Math.max(0, mh - mediaSize.height) - mh + screenSize.height) / 2;
		int w = screenSize.width - 2 * x;
		int h = screenSize.height - 2 * y;
		frame.setLocation(x, y);
		frame.setSize(w, h);
	}

	private void displayAsFullScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(0, 0);
		frame.setSize(screenSize.width, screenSize.height);
	}

	public void reversePause() {
		mediaPlayerComponent.getMediaPlayer().setPause(mediaPlayerComponent.getMediaPlayer().isPlaying());
	}

	public void playNext() {
		currentVideoIndex++;
		if (currentVideoIndex >= videoSources.length) {
			currentVideoIndex = 0;
		}
		playCurrent();
	}

	public void playPreviews() {
		currentVideoIndex--;
		if (currentVideoIndex < 0) {
			currentVideoIndex = Math.max(0, videoSources.length - 1);
		}
		playCurrent();
	}

	public void playCurrent() {
		if (currentVideoIndex < 0 || currentVideoIndex >= videoSources.length) {
			return;
		}
		mediaPlayerComponent.getMediaPlayer().playMedia(videoSources[currentVideoIndex]);
		mediaPlayerComponent.getMediaPlayer().setRepeat(true);
		mediaPlayerComponent.getMediaPlayer().start();
		frame.setTitle(mediaPlayerComponent.getMediaPlayer().getMediaMeta().getTitle());
	}

	public void increaseVolumn() {
		int volumn = mediaPlayerComponent.getMediaPlayer().getVolume();
		volumn += 10;
		if (volumn > 100) {
			volumn = 100;
		}
		mediaPlayerComponent.getMediaPlayer().setVolume(volumn);
	}

	public void decreaseVolumn() {
		int volumn = mediaPlayerComponent.getMediaPlayer().getVolume();
		volumn -= 10;
		if (volumn < 0) {
			volumn = 0;
		}
		mediaPlayerComponent.getMediaPlayer().setVolume(volumn);
	}

	public void forwardSkip() {
		long length = mediaPlayerComponent.getMediaPlayer().getLength();
		if (length <= 0L) {
			return;
		}
		float position = mediaPlayerComponent.getMediaPlayer().getPosition();
		float increase = 5000.0f / length;
		position += increase;
		if (position >= 1.0f) {
			position = 1.0f;
		}
		mediaPlayerComponent.getMediaPlayer().setPosition(position);
	}

	public void backwardSkip() {
		long length = mediaPlayerComponent.getMediaPlayer().getLength();
		if (length <= 0L) {
			return;
		}
		float position = mediaPlayerComponent.getMediaPlayer().getPosition();
		float increase = 5000.0f / length;
		position -= increase;
		if (position < 0.0f) {
			position = 0.0f;
		}
		mediaPlayerComponent.getMediaPlayer().setPosition(position);
	}

	public void shutdown() {
		try {
			mediaPlayerComponent.getMediaPlayer().stop();
			mediaPlayerComponent.release();
			frame.dispose();
		} finally {
			System.exit(0);
		}
	}

}
