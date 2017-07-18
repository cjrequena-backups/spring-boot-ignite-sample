package com.aurora.sample.cache.configuration;

import lombok.Data;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.spring.SpringCacheManager;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 *
 * @author cjrequena
 * @version 1.0
 * @see
 * @since JDK1.8
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignite.configuration")
@Validated
public class IgniteCacheConfiguration {

    /** The configurationProperties. */
//    @Valid
//    private List<IgniteConfigurationProperties> configurationProperties = new ArrayList<>();

    /**
     * @return
     */
    @Bean(name = "igniteCacheManager")
    public CacheManager igniteCacheManager() {
        SpringCacheManager cacheManager = new SpringCacheManager();
        cacheManager.setConfiguration(igniteConfiguration());
        //cacheManager.setConfigurationPath("ignite/example-hello-client.xml");
        return cacheManager;
    }

    /**
     * @return
     */
    @Bean(name = "igniteConfiguration")
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setGridName("testGrid");
        igniteConfiguration.setClientMode(true);
        igniteConfiguration.setPeerClassLoadingEnabled(true);

        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        //ipFinder.setAddresses(Collections.singletonList("192.168.82.100:47500..47509"));
        tcpDiscoverySpi.setIpFinder(ipFinder);
        //tcpDiscoverySpi.setLocalAddress("localhost");
        igniteConfiguration.setDiscoverySpi(tcpDiscoverySpi);

        // TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();
        // communicationSpi.setLocalAddress("localhost");
        // igniteConfiguration.setCommunicationSpi(communicationSpi);

        igniteConfiguration.setCacheConfiguration(cacheConfiguration());

        return igniteConfiguration;

    }

    /**
     * @return
     */
    @Bean(name = "cacheConfiguration")
    public CacheConfiguration[] cacheConfiguration() {
        CacheConfiguration[] cacheConfigurations = new CacheConfiguration[10];
        for (int i = 0; i < 10; i++) {
            CacheConfiguration cacheConfiguration = new CacheConfiguration();
            cacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
            cacheConfiguration.setCacheMode(CacheMode.REPLICATED);
            cacheConfiguration.setName("testCache"+i);

            //cacheConfiguration.setWriteSynchronizationMode(CacheWriteSynchronizationMode.FULL_SYNC);
            cacheConfiguration.setWriteThrough(false);
            cacheConfiguration.setReadThrough(false);
            cacheConfiguration.setWriteBehindEnabled(false);

            cacheConfiguration.setBackups(1);
            cacheConfiguration.setStatisticsEnabled(true);
            cacheConfigurations[i]=cacheConfiguration;
        }
        return cacheConfigurations;
    }

    /**
     *
     */
    @Data
    public static class IgniteConfigurationProperties {

        /**  */
        private String gridName;

        /**  */
        private String configurationPath;

        /**  */
        private String localAddress;

        /**  */
        private String ipDiscoveryRange;

        /**  */
        private Boolean clientMode;

        /**  */
        private Boolean peerClassLoadingEnabled;

        /**  */
        private Boolean useSameServerNames;

    }


}
