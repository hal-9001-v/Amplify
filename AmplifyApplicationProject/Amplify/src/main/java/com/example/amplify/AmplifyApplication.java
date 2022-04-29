package com.example.amplify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableHazelcastHttpSession
public class AmplifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmplifyApplication.class, args);
    }

    // Configuración de caché para la base de datos con Hazelcast
    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }
    @Bean
    public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

    // Configuración de Hazelcast para el descubrimiento de nodos (Sesión distribuida)
    @Bean
    public Config config() {
        Config config = new Config();

        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
        joinConfig.getMulticastConfig().setEnabled(true);

        MapConfig usersMapConfig = new MapConfig().setName("amplify");
        config.addMapConfig(usersMapConfig);

        return config;
    }
}
