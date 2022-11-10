package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.ReturnEntity;

public class ReturnSpecifications  extends BaseSpecifications<ReturnEntity> {
    private static ReturnSpecifications INSTANCE;

    public static ReturnSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReturnSpecifications();
        }

        return INSTANCE;
    }
}
