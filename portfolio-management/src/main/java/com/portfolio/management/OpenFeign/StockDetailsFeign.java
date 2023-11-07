package com.portfolio.management.OpenFeign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface StockDetailsFeign {
    ResponseEntity<Long> getavailablefunds(@PathVariable String uId);
}
