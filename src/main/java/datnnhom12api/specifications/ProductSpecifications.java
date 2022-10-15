package datnnhom12api.specifications;

import datnnhom12api.dto.core.BaseSpecifications;
import datnnhom12api.entity.products.ProductEntity;

public class ProductSpecifications extends BaseSpecifications<ProductEntity> {
    private static ProductSpecifications INSTANCE;

    public static ProductSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductSpecifications();
        }
        return INSTANCE;
    }
}
