package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.dto.InventoryDTO;
import datnnhom12api.dto.ExchangeDetailDTO;
import datnnhom12api.dto.UpdateReturnDetailDTO;
import datnnhom12api.entity.ExchangeDetailEntity;
import datnnhom12api.entity.ExchangeEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.repository.OrderDetailRepository;
import datnnhom12api.repository.ProductRepository;
import datnnhom12api.repository.ExchangeDetailRepository;
import datnnhom12api.repository.ExchangeRepository;
import datnnhom12api.request.ExchangeDetailRequest;
import datnnhom12api.request.ExchangeRequest2;
import datnnhom12api.service.ReturnService;
import datnnhom12api.specifications.ReturnSpecifications;
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
import java.util.stream.Collectors;

@Service("returnService")
@Transactional(rollbackFor = Throwable.class)
public class ExchangeServiceImpl implements ReturnService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeDetailRepository exchangeDetailRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ExchangeEntity insert(ExchangeRequest2 request) throws CustomException {
        ExchangeEntity exchangeEntity = new ExchangeEntity();
        exchangeEntity.setData(request);
        this.exchangeRepository.save(exchangeEntity);
        List<ExchangeDetailRequest> list = request.getReturnDetailEntities();
        Long id = exchangeEntity.getId();
        list.forEach(item -> {
            ExchangeDetailEntity exchangeDetailEntity = new ExchangeDetailEntity();
            exchangeDetailEntity.setExchange(this.exchangeRepository.getById(id));
            exchangeDetailEntity.setQuantity(item.getQuantity());
            exchangeDetailEntity.setOrderDetail(this.orderDetailRepository.getById(item.getOrderDetailId()));
            exchangeDetailEntity.setProductId(this.productRepository.getById(item.getProductId()));
            exchangeDetailEntity.setStatus(ReturnDetailStatus.YEU_CAU);
            exchangeDetailEntity.setOrderChange(item.getOrderChange());
            this.exchangeDetailRepository.save(exchangeDetailEntity);
        });
        return exchangeEntity;
    }

    @Override
    public ExchangeEntity update(Long id, ExchangeRequest2 post) throws CustomException {
        Optional<ExchangeEntity> returnEntityOptional = this.exchangeRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "Mã phải lớn hơn 0");
        }
        if (returnEntityOptional.isEmpty()) {
            throw new CustomException(403, "Không tìm thấy mã muốn sửa");
        }
        ExchangeEntity exchangeEntity = returnEntityOptional.get();
        exchangeEntity.setStatus(post.getStatus());
//        exchangeEntity.setIsCheck(post.getIsCheck());
       List<ExchangeDetailEntity> returnDetailEntities = this.exchangeDetailRepository.findReturnByIds(exchangeEntity.getId());
       post.getReturnDetailEntities().forEach(request -> {
           ExchangeDetailEntity exchangeDetailEntity = this.exchangeDetailRepository.getById(request.getId());
           exchangeDetailEntity.setStatus(request.getStatus());
           this.exchangeDetailRepository.saveAll(returnDetailEntities);
       });


//       returnDetailEntities.forEach(returnDetailEntity -> {
//           returnDetailEntity.setStatus(ReturnDetailStatus.DA_XAC_NHAN);
//       });
//       this.returnDetailRepository.saveAll(returnDetailEntities);
        ExchangeEntity returnEn = this.exchangeRepository.save(exchangeEntity);
        return returnEn;
    }

    @Override
    public Page<ExchangeEntity> paginate(int page, int limit, List<Filter> filters, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        Specification<ExchangeEntity> specifications = ReturnSpecifications.getInstance().getQueryResult(filters);
        return exchangeRepository.findAll(specifications, pageable);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<ExchangeDetailDTO> findById(Long id) {
        List<ExchangeDetailEntity> exchangeDetailEntity = this.exchangeDetailRepository.findReturnByIds(id);
        ModelMapper modelMapper = new ModelMapper();
        List<ExchangeDetailDTO> dtos = exchangeDetailEntity
                .stream()
                .map(user -> modelMapper.map(user, ExchangeDetailDTO.class))
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UpdateReturnDetailDTO updateByReturnDetail(Long id, ExchangeDetailRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        ExchangeDetailEntity exchangeDetailEntity = this.exchangeDetailRepository.getById(id);
        exchangeDetailEntity.setStatus(request.getStatus());
        this.exchangeDetailRepository.save(exchangeDetailEntity);
        UpdateReturnDetailDTO returnDetailDTO = modelMapper.map(exchangeDetailEntity,UpdateReturnDetailDTO.class);
        return returnDetailDTO;
    }

    @Override
    public ExchangeEntity getById(Long id) {
        return this.exchangeRepository.getById(id);
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        List<InventoryDTO> list = this.exchangeDetailRepository.getAllInventoryDTO();
        return list;
    }
}