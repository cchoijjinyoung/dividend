package com.dayone.scraper;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;

/**
 * Yahoo.finance 말고도 다른 사이트에서 데이터를 스크래핑을 할 수도 있다.
 * 코드의 재사용성과 확장성을 위해 인터페이스를 정의.
 */
public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
