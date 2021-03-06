package org.xuyh.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Start boot actions after application all injection ready. <br />
 * 
 * @author XuYanhang
 *
 */
@org.springframework.stereotype.Component
public class ApplicationReadyInitializeConfig implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		event.getApplicationContext() // Get_the_context_for_beans
				.getBeansOfType(ApplicationReadyInitializer.class) // Get_target_beans
				.values() // Get_all_beans_values
				.stream() // Get_the_stream
				.sorted((v1, v2) -> Float.compare(v2.getPriority(), v1.getPriority())) // Sort_the_priority
				.forEach(v -> v.initialize()); // Action_the_initialize
	}

}
