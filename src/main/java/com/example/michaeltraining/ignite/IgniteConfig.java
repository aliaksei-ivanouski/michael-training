package com.example.michaeltraining.ignite;

import com.example.michaeltraining.user.User;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.PartitionLossPolicy;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.SqlConfiguration;
import org.apache.ignite.spi.discovery.zk.ZookeeperDiscoverySpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class IgniteConfig {

    @Bean
    public ZookeeperDiscoverySpi zkDiscoverySpi() {
        // Setting up an IP Finder to ensure the client can locate the servers.
        ZookeeperDiscoverySpi zkDiscoverySpi = new ZookeeperDiscoverySpi();

        zkDiscoverySpi.setZkConnectionString("localhost:2181,localhost:2182");
        zkDiscoverySpi.setSessionTimeout(30_000);

        zkDiscoverySpi.setZkRootPath("/ignite");
        zkDiscoverySpi.setJoinTimeout(10_000);

        return zkDiscoverySpi;
    }

    @Bean
    public IgniteConfiguration igniteConfiguration(
            ZookeeperDiscoverySpi zkDiscoverySpi, SqlConfiguration sqlConfiguration,
            CacheConfiguration<UUID, User> userCacheConfiguration) {
        IgniteConfiguration cfg = new IgniteConfiguration();
        //Override default discovery SPI.
        cfg.setDiscoverySpi(zkDiscoverySpi);
        cfg.setSqlConfiguration(sqlConfiguration);
        cfg.setIgniteInstanceName("MY_INSTANCE");

        cfg.setCacheConfiguration(userCacheConfiguration);

        return cfg;
    }

    @Bean
    public SqlConfiguration sqlConfiguration() {
        SqlConfiguration cfg = new SqlConfiguration();
        cfg.setSqlSchemas("PUBLIC");

        return cfg;
    }

    @Bean
    public CacheConfiguration<UUID, User> userConfiguration() {
        CacheConfiguration<UUID, User> userCacheCfg = new CacheConfiguration<>("USERS");
        userCacheCfg.setBackups(1);
        userCacheCfg.setCacheMode(CacheMode.PARTITIONED);
        userCacheCfg.setPartitionLossPolicy(PartitionLossPolicy.READ_ONLY_SAFE);
        return userCacheCfg;
    }
}
