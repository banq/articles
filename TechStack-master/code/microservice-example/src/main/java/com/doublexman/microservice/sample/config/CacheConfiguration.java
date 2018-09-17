package com.doublexman.microservice.sample.config;

import com.doublexman.microservice.sample.domain.User;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CacheConfiguration {


    /**
     * cache configuration example
     * @return
     */
    @Bean
    CacheManager cacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        cacheManager.createCache("user-cache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, User.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(20, EntryUnit.ENTRIES)
                                .offheap(50, MemoryUnit.MB)).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(30))));
        return cacheManager;
    }

}
