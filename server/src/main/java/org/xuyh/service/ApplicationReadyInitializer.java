package org.xuyh.service;

import java.util.EventListener;

/**
 * After the application context is ready then the {@link #initialize()} will be
 * apply. The apply order is from the {@link #getPriority()} value. The larger
 * value will be apply earlier. <br />
 * We know that the application started in a process. In some scene we need
 * initialize some service by {@link javax.annotation.PostConstruct}. But
 * between the process the application may have not be ready then some injected
 * value maybe <code>null</code> so that some initialize method should be
 * executed failed with exception of {@link NullPointerException}. If a better
 * way is needed then we can control the execute order in this way.<br />
 * 
 * @see ApplicationReadyService
 * @author XuYanhang
 *
 */
public interface ApplicationReadyInitializer extends EventListener {

	/**
	 * Initialize method. After the application is ready, the function actions
	 * once. <br />
	 */
	public void initialize();

	/**
	 * Get the priority. The initializer will a larger priority will be executed
	 * before the one will a smaller one. The initializers will same priority
	 * will be executed one by one in uncontrollable order. The default value is
	 * <code>5</code>. For some initializers the execute order is needed then
	 * rewrite this function. <br />
	 * 
	 * [USER RULES] <br />
	 * 1. The value expected to be in 0~10. <br />
	 * 2. The value before dot defines the public order while the value after
	 * dot defines the private order. <br />
	 * 3. The default priority value is <code>5.0</code>. <br />
	 * 4. For some initializers, new resources should be created then the
	 * priority expected to be larger. <br />
	 */
	default public float getPriority() {
		return 5.0f;
	}

}
