package datnnhom12api.specifications;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.GraphicsEntity;


public class GraphicsSpecifications extends BaseSpecifications<GraphicsEntity> {
    private static GraphicsSpecifications INSTANCE;

    public static GraphicsSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphicsSpecifications();
        }

        return INSTANCE;
    }

}
