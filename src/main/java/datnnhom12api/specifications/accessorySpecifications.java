package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.AccessoryEntity;

public class accessorySpecifications extends BaseSpecifications<AccessoryEntity> {
    private static accessorySpecifications INSTANCE;

    public static accessorySpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new accessorySpecifications();
        }
        return INSTANCE;
    }
}
