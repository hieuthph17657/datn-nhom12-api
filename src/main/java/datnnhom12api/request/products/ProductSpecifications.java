package datnnhom12api.request.products;

import datnnhom12api.core.BaseSpecifications;
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
