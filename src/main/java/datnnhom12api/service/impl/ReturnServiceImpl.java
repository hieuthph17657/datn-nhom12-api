package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.ProductEntity;
import datnnhom12api.entity.ReturnEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.ReturnRepository;
import datnnhom12api.request.ReturnRequest;
import datnnhom12api.service.ReturnService;
import datnnhom12api.specifications.CartSpecifications;
import datnnhom12api.specifications.ReturnSpecifications;
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

@Service("returnService")
@Transactional(rollbackFor = Throwable.class)
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnRepository returnRepository;

    @Override
    public ReturnEntity insert(ReturnRequest post) throws CustomException {
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setData(post);
        this.returnRepository.save(returnEntity);
        return returnEntity;
    }

    @Override
    public ReturnEntity update(Long id, ReturnRequest post) throws CustomException {
        Optional<ReturnEntity> returnEntityOptional = this.returnRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "Mã phải lớn hơn 0");
        }
        if (returnEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy mã muốn sửa");
        }
        ReturnEntity returnEntity = returnEntityOptional.get();
        System.out.println(returnEntity.getId() +", " + returnEntity.getReason());
        returnEntity.setData(post);
        ReturnEntity returnEn =this.returnRepository.save(returnEntity);
        System.out.println(returnEn.getId() +", " + returnEn.getReason());
        return returnEn;
    }

    @Override
    public Page<ReturnEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ReturnEntity> specifications = ReturnSpecifications.getInstance().getQueryResult(filters);
        return returnRepository.findAll(specifications, pageable);
    }

    @Override
    public void delete(Long id) {

    }
}
