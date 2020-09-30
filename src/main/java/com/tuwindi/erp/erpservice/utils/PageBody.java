package com.tuwindi.erp.erpservice.utils;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageBody {
    private Long budgetId;
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortdirection = Sort.Direction.ASC;
    private String sortBy = "id";
}
