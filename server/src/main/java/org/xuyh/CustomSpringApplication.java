package org.xuyh;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.xuyh.util.FileUtil;

/**
 * Custom application so that we can do something
 * 
 * @author XuYanhang
 *
 */
public class CustomSpringApplication extends SpringApplication {

	/**
	 * @param sources
	 */
	public CustomSpringApplication(Object... sources) {
		super(sources);
	}

	/**
	 * @param resourceLoader
	 * @param sources
	 */
	public CustomSpringApplication(ResourceLoader resourceLoader, Object... sources) {
		super(resourceLoader, sources);
	}

	/**
	 * Action run method
	 */
	public ConfigurableApplicationContext run(String... args) {

		super.setBannerMode(Banner.Mode.LOG);
		this.configureHeadlessProperty();
		try {
			ConfigurableApplicationContext context = super.run(args);
			getApplicationLog()
					.info("Start Success on " + System.getProperty("os.name") + "\n" + startSuccessTailMessage());
			return context;
		} catch (Throwable t) {
			getApplicationLog()
					.error("Start Failure on " + System.getProperty("os.name") + "\n" + startFailureTailMessage());
			throw t;
		}
	}

	/**
	 * The information on start application success
	 */
	private String startSuccessTailMessage() {
		String text = FileUtil.readLocalText("startsuccess.txt", "utf-8");
		return (null == text || text.isEmpty()) ? "START SUCCESS" : text;
	}

	/**
	 * The information on start application failure
	 */
	private String startFailureTailMessage() {
		String text = FileUtil.readLocalText("startfailure.txt", "utf-8");
		return (null == text || text.isEmpty()) ? "START FAILURE" : text;
	}

	/**
	 * Configure set the headless property value
	 */
	private void configureHeadlessProperty() {
		setHeadless(Boolean.getBoolean(System.getProperty("java.awt.headless", Boolean.FALSE.toString())));
	}
}
