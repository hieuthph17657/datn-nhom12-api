package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.RamEntity;
import datnnhom12api.entity.ScreenEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.ScreenRepository;
import datnnhom12api.request.ScreenRequest;
import datnnhom12api.service.ScreenService;
import datnnhom12api.specifications.RamSpecifications;
import datnnhom12api.specifications.ScreenScpecifications;
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
import java.util.Optional;


@Service("screenService")
@Transactional(rollbackFor = Throwable.class)
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public Page<ScreenEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ScreenEntity> specifications = ScreenScpecifications.getInstance().getQueryResult(filters);
        return screenRepository.findAll(specifications, pageable);
    }

    @Override
    public ScreenEntity create(ScreenRequest post) {
        ScreenEntity screenEntity = new ScreenEntity();
        screenEntity.setData(post);
        screenEntity = screenRepository.save(screenEntity);
        return screenEntity;
    }

    @Override
    public ScreenEntity update(Long id, ScreenRequest post) throws CustomException{
        Optional<ScreenEntity> processorEntityOptional = screenRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (processorEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        ScreenEntity screenEntity = processorEntityOptional.get();
        screenEntity.setData(post);
        screenEntity = screenRepository.save(screenEntity);
        return screenEntity;
    }

    @Override
    public void delete(Long id) {
        ScreenEntity screenEntity = this.screenRepository.getById(id);
        screenRepository.delete(screenEntity);
    }
}
