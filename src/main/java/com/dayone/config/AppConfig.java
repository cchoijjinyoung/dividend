package com.dayone.config;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    /**
     * Service내에서 싱글톤을 유지해야하고,
     * 코드의 일관성을 유지하기 위해 Trie를 빈으로 관리.
     */
    @Bean
    public Trie<String, String> trie() {
        return new PatriciaTrie<>();
    }
}
