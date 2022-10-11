package datnnhom12api.service.products;

import datnnhom12api.entity.products.DesignEntity;
import datnnhom12api.exceptions.CustomException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DesignService {
    DesignEntity insert() throws CustomException;
    DesignEntity update() throws CustomException;
    DesignEntity delete() throws CustomException;
    DesignEntity getOne() throws CustomException;
    Page<DesignEntity> paginate() throws CustomException;
}
