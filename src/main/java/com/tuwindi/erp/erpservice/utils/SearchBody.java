package com.tuwindi.erp.erpservice.utils;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SearchBody implements Serializable {
    private Enumeration.TYPE_UNITY typeUnity;
}
