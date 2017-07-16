package com.aurora.sample;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
@SpringBootApplication
public class MainApplication  {

	private static Class<MainApplication> mainApplicationClass = MainApplication.class;

	/**
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			SpringApplication springApplication = new SpringApplication(mainApplicationClass);
			springApplication.setBannerMode(Banner.Mode.OFF);
			springApplication.run(args);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}
