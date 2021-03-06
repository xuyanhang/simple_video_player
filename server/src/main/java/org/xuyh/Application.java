package org.xuyh;


/**
 * The starter for the project.
 * 
 * @author XuYanhang
 *
 */
// Sprint Boot Auto Configuration
@org.springframework.boot.SpringBootConfiguration
@org.springframework.boot.autoconfigure.EnableAutoConfiguration
@org.springframework.context.annotation.ComponentScan(basePackages = "org.xuyh")
public class Application {

	/**
	 * The entrance for the project.
	 */
	public static void main(String[] args) {
		CustomSpringApplication app = new CustomSpringApplication(Application.class);
		app.run(args);
	}

}
