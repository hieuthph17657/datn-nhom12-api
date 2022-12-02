package datnnhom12api.service;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ColorEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ColorService {
    Page<ColorEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> orders);
}
