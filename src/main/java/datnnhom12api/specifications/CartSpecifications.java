package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.CategoryEntity;

public class CartSpecifications extends BaseSpecifications<CategoryEntity> {

    private static CartSpecifications INSTANCE;

    public static CartSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CartSpecifications();
        }

        return INSTANCE;
    }
}
