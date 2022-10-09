package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.OrderDetailEntity;

public class OrderDetailSpecifications extends BaseSpecifications<OrderDetailEntity> {
    private static OrderDetailSpecifications INSTANCE;
    public static OrderDetailSpecifications getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new OrderDetailSpecifications();
        }
        return INSTANCE;
    }
}
