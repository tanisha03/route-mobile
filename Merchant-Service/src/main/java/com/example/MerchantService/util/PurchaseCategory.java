package com.example.MerchantService.util;

import java.util.HashMap;
import java.util.Map;

public enum PurchaseCategory {
    GROCERY(1),HOMEUSE(2),UTILITY(3),FOOD(4);

    private int value;
    private static Map map = new HashMap<>();

    private PurchaseCategory(int value) {
        this.value = value;
    }

    static {
        for (PurchaseCategory pageType :PurchaseCategory.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static PurchaseCategory valueOf(int pageType) {
        return (PurchaseCategory) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
