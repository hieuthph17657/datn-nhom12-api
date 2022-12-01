package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.accessoryEntity;

public class accessorySpecifications extends BaseSpecifications<accessoryEntity> {
    private static accessorySpecifications INSTANCE;

    public static accessorySpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new accessorySpecifications();
        }
        return INSTANCE;
    }
}
