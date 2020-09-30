package com.tuwindi.erp.erpservice.utils;

import com.tuwindi.erp.erpservice.entities.Budget;
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
public class BudgetSaveEntity {
    private Budget budget;
    private List<SectionBody> sections = new ArrayList<>();
}
