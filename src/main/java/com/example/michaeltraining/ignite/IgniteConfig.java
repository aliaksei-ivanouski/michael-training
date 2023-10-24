package com.example.michaeltraining.ignite;

import org.apache.ignite.configuration.DeploymentMode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.SqlConfiguration;
import org.apache.ignite.spi.discovery.zk.ZookeeperDiscoverySpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {

    private static final String MY_INSTANCE = "MY_INSTANCE";

    @Bean
    public ZookeeperDiscoverySpi zkDiscoverySpi() {
        // Setting up an IP Finder to ensure the client can locate the servers.
        ZookeeperDiscoverySpi zkDiscoverySpi = new ZookeeperDiscoverySpi();

        zkDiscoverySpi.setZkConnectionString("localhost:2181,localhost:2182");
        zkDiscoverySpi.setSessionTimeout(30_000);

        zkDiscoverySpi.setZkRootPath("/" + MY_INSTANCE);
        zkDiscoverySpi.setJoinTimeout(10_000);

        return zkDiscoverySpi;
    }

    @Bean
    public IgniteConfiguration igniteConfiguration(ZookeeperDiscoverySpi zkDiscoverySpi, SqlConfiguration sqlConfiguration) {
        IgniteConfiguration cfg = new IgniteConfiguration();
        //Override default discovery SPI.
        cfg.setDiscoverySpi(zkDiscoverySpi);
        cfg.setSqlConfiguration(sqlConfiguration);
        cfg.setIgniteInstanceName(MY_INSTANCE);
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setDeploymentMode(DeploymentMode.SHARED);

        return cfg;
    }

    @Bean
    public SqlConfiguration sqlConfiguration() {
        SqlConfiguration cfg = new SqlConfiguration();
        cfg.setSqlSchemas("PUBLIC");

        return cfg;
    }
}
