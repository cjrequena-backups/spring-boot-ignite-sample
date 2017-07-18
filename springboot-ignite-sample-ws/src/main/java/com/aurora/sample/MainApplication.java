package com.aurora.sample;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

	@IgniteInstanceResource
	transient Ignite ignite;

	@Autowired
	IgniteConfiguration igniteConfiguration;

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

	/**
	 *
	 * @return
	 */
	@Bean
	public CommandLineRunner runIgnite() {
		return new CommandLineRunner() {


			public void run(String... args) throws Exception {

				Ignite ignite = Ignition.getOrStart(igniteConfiguration);
				// Put values in cache.
				IgniteCache<Integer, String> cache = ignite.getOrCreateCache("test-cache");
				cache.put(1, "Hello");
				cache.put(2, "World!");
				// Get values from cache
				// Broadcast 'Hello World' on all the nodes in the cluster.
				ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));

			}
		};
	}

}
