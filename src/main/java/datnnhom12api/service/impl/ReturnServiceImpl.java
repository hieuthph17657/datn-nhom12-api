package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.ReturnDetailDTO;
import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ReturnDetailEntity;
import datnnhom12api.entity.ReturnEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.OrderDetailRepository;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.repository.ReturnDetailRepository;
import datnnhom12api.repository.ReturnRepository;
import datnnhom12api.request.ReturnDetailRequest;
import datnnhom12api.request.ReturnRequest;
import datnnhom12api.service.ReturnService;
import datnnhom12api.specifications.ReturnSpecifications;
import datnnhom12api.utils.support.OrderDetailStatus;
import datnnhom12api.utils.support.ReturnDetailStatus;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ReturnDetailRepository returnDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ReturnEntity insert(ReturnRequest request) throws CustomException {
        ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setData(request);
        this.returnRepository.save(returnEntity);
        List<ReturnDetailRequest> list = request.getReturnDetailEntities();
        Long id = returnEntity.getId();
        list.forEach(item -> {
            ReturnDetailEntity returnDetailEntity = new ReturnDetailEntity();
            returnDetailEntity.setReturn(this.returnRepository.getById(id));
            returnDetailEntity.setQuantity(item.getQuantity());
            returnDetailEntity.setOrderDetail(this.orderDetailRepository.getById(item.getOrderDetailId()));
            returnDetailEntity.setProductId(this.productRepository.getById(item.getProductId()));
            returnDetailEntity.setStatus(ReturnDetailStatus.YEU_CAU);
            this.returnDetailRepository.save(returnDetailEntity);
        });
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
        returnEntity.setData(post);
        ReturnEntity returnEn = this.returnRepository.save(returnEntity);
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

    @Override
    public List<ReturnDetailEntity> findById(Long id) {
        System.out.println("id return: " + id);
//        List<ReturnDetailEntity> list =
        return this.returnDetailRepository.findReturnByIds(id);
    }

    @Override
    public UpdateReturnDetailDTO updateByReturnDetail(Long id, ReturnDetailRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        ReturnDetailEntity returnDetailEntity = this.returnDetailRepository.getById(id);
        returnDetailEntity.setStatus(request.getStatus());
        this.returnDetailRepository.save(returnDetailEntity);
        UpdateReturnDetailDTO returnDetailDTO = modelMapper.map(returnDetailEntity,UpdateReturnDetailDTO.class);
        return returnDetailDTO;
    }
}