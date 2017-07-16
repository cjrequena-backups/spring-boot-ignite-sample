package com.aurora.sample.cache.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
@Data
@Configuration
@ConfigurationProperties(prefix = "cache.caffeine")
@Validated
public class CaffeineCacheConfiguration {

    /** The configurations. */
    @Valid
    private List<CaffeineConfiguration> configurations = new ArrayList<>();

    /**
     * Instantiates a new Caffeine configuration.
     */
    @Data
    public static class CaffeineConfiguration {

        /** The cache name. */
        private String cacheName;

        /** The initial capacity. */
        private Integer initialCapacity;

        /** The maximum size. */
        private Integer maximumSize;

        /** The maximum weight. */
        private Integer maximumWeight;

        /** The expire after access. */
        private Integer expireAfterAccess;

        /** The expire after write. */
        private Integer expireAfterWrite;

        /** The refresh after write. */
        private Integer refreshAfterWrite;
    }

    /**
     * Caffeine cache manager.
     *
     * @return the cache manager
     */
    @Bean(name = "caffeineCacheManager")
    public CacheManager CaffeineCacheManager() {
        List<CaffeineCache> cacheList = new ArrayList<>();
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCacheConfiguration.CaffeineConfiguration> configurations = getConfigurations();
        for (CaffeineCacheConfiguration.CaffeineConfiguration configuration : configurations) {
            Caffeine<Object, Object> chacheBuilder = Caffeine.newBuilder();
            if (configuration.getInitialCapacity() != null) {
                chacheBuilder.initialCapacity(configuration.getInitialCapacity());
            }
            if (configuration.getMaximumSize() != null) {
                chacheBuilder.maximumSize(configuration.getMaximumSize());
            }
            if (configuration.getMaximumWeight() != null) {
                chacheBuilder.maximumWeight(configuration.getMaximumWeight());
            }
            if (configuration.getExpireAfterAccess() != null) {
                chacheBuilder.expireAfterAccess(configuration.getExpireAfterAccess(), TimeUnit.SECONDS);
            }
            if (configuration.getExpireAfterWrite() != null) {
                chacheBuilder.expireAfterWrite(configuration.getExpireAfterWrite(), TimeUnit.SECONDS);
            }
            if (configuration.getRefreshAfterWrite() != null) {
                chacheBuilder.refreshAfterWrite(configuration.getRefreshAfterWrite(), TimeUnit.SECONDS);
            }
            CaffeineCache CaffeineCache = new CaffeineCache(configuration.getCacheName(), chacheBuilder.build());
            cacheList.add(CaffeineCache);
        }
        cacheManager.setCaches(cacheList);
        return cacheManager;
    }
}
