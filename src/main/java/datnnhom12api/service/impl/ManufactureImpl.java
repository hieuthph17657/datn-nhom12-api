package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.ManufactureEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.ManufactureRepository;
import datnnhom12api.request.ManufactureRequest;
import datnnhom12api.service.ManufactureService;
import datnnhom12api.specifications.ManufactureSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("manufactureService")
@Transactional(rollbackFor = Throwable.class)
public class ManufactureImpl implements ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Override
    public ManufactureEntity insert(ManufactureRequest request) throws CustomException {
        return null;
    }

    @Override
    public Page<ManufactureEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ManufactureEntity> specifications = ManufactureSpecifications.getInstance().getQueryResult(filters);
        return manufactureRepository.findAll(specifications, pageable);
    }
}
