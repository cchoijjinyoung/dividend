package com.dayone;

import com.dayone.model.Company;
import com.dayone.scraper.YahooFinanceScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);

        YahooFinanceScraper scraper = new YahooFinanceScraper();
        var result = scraper.scrap(Company.builder().ticker("O").build());
        System.out.println(result);
    }
}
