package datnnhom12api.service.impl;

import datnnhom12api.core.Filter;
import datnnhom12api.entity.CardEntity;
import datnnhom12api.entity.GraphicsEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.specifications.CardSpecifications;
import datnnhom12api.repository.GraphicsRepository;
import datnnhom12api.request.GraphicsRequest;
import datnnhom12api.service.GraphicsService;
import datnnhom12api.specifications.GraphicsSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GraphicsServiceImpl implements GraphicsService {
    @Autowired
    GraphicsRepository graphicsRepository;
    @Override
    public GraphicsEntity create(GraphicsRequest request) throws CustomException {
        GraphicsEntity entity = new GraphicsEntity();
        entity.setData(request);
        this.graphicsRepository.save(entity);
        return entity;
    }

    @Override
    public Page<GraphicsEntity> paginate(int page, int limit, List<Filter> whereParams, Map<String, String> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<GraphicsEntity> specifications = GraphicsSpecifications.getInstance().getQueryResult(whereParams);

        return graphicsRepository.findAll(specifications, pageable);
    }

    @Override
    public GraphicsEntity update(Long id, GraphicsRequest post) throws CustomException {
        Optional<GraphicsEntity> graEntityOptional = graphicsRepository.findById(id);
        if (id <= 0) {
            throw new CustomException(403, "id người dùng phải lớn hơn 0");
        }
        if (graEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy id người dùng muốn sửa");
        }
        GraphicsEntity graphicsEntity = graEntityOptional.get();
        graphicsEntity.setData(post);
        graphicsEntity = graphicsRepository.save(graphicsEntity);
        return graphicsEntity;
    }

    @Override
    public GraphicsEntity delete(Long id) throws CustomException {
        Optional<GraphicsEntity> graphicsEntityOptional = graphicsRepository.findById(id);
        if (graphicsEntityOptional.isEmpty()) {
            throw new CustomException(403, "không tìm thấy đối tượng");
        }
        GraphicsEntity cardEntity = graphicsRepository.getById(id);
        graphicsRepository.delete(cardEntity);
        return cardEntity;
    }
}
