package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.entity.InformationEntity;

public class DiscountSpecifications extends BaseSpecifications<DiscountEntity> {
    private static DiscountSpecifications INSTANCE;

    public static DiscountSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountSpecifications();
        }

        return INSTANCE;
    }
}
