package com.aurora.sample.cache.configuration;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Component
public class CacheManagerCheck implements CommandLineRunner {

	private final CacheManager cacheManager;

	@Autowired
	public CacheManagerCheck(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void run(String... strings) throws Exception {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (Iterator<String> iterator = cacheNames.iterator(); iterator.hasNext();) {
			String cacheName = iterator.next();
			String cacheManagerName = cacheManager.getCache(cacheName).getNativeCache().getClass().getName();
			log.info("Cache {} Using cache manager: {}", cacheName, cacheManagerName);
		}
	}

}
