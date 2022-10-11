package datnnhom12api.specifications.products;

import datnnhom12api.core.BaseSpecifications;
import datnnhom12api.entity.products.SpeicificationEntity;

public class DesignSpeicification extends BaseSpecifications<SpeicificationEntity> {
    private static  DesignSpeicification INSTANCE;

    public static  DesignSpeicification getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DesignSpeicification();
        }
        return  INSTANCE;
    }

}
