package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.AccessoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.accessoryRepository;
import datnnhom12api.request.accessoryRequest;
import datnnhom12api.service.AccessoryService;
import datnnhom12api.specifications.accessorySpecifications;
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

@Service("accessoryService")
@Transactional(rollbackFor = Throwable.class)
public class accessoryServiceImpl implements AccessoryService {

    @Autowired
    accessoryRepository accessoryRepo;

    @Override
    public AccessoryEntity create(accessoryRequest accessoryRequest) throws CustomException {
        AccessoryEntity accessoryEntity = new AccessoryEntity();
        accessoryEntity.setData(accessoryRequest);
        accessoryEntity = accessoryRepo.save(accessoryEntity);
        return accessoryEntity;
    }

    @Override
    public Page<AccessoryEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<AccessoryEntity> specifications = accessorySpecifications.getInstance().getQueryResult(filters);
        return accessoryRepo.findAll(specifications, pageable);
    }

    @Override
    public List<AccessoryEntity> findAll() {
        return accessoryRepo.findAll();
    }
}
