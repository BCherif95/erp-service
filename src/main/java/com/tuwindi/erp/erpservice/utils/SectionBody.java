package com.tuwindi.erp.erpservice.utils;

import com.tuwindi.erp.erpservice.entities.BudgetLine;
import com.tuwindi.erp.erpservice.entities.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionBody {
    private String name;
    private List<BudgetLine> lines = new ArrayList<>();
}
