package datnnhom12api.controller.admin;


import datnnhom12api.entity.GraphicsEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.GraphicsMapper;
import datnnhom12api.paginationrequest.GraphicsPaginationrequest;
import datnnhom12api.repository.GraphicsRepository;
import datnnhom12api.request.GraphicsRequest;
import datnnhom12api.response.GraphicResponse;
import datnnhom12api.service.GraphicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/graphics")
public class GraphicsController {
    @Autowired
    private GraphicsService graphicsService;

    @GetMapping
    public GraphicResponse index(@Valid GraphicsPaginationrequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<GraphicsEntity> page = this.graphicsService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new GraphicResponse(GraphicsMapper.toPageDTO(page));
    }


    @PostMapping()
    public GraphicResponse create(@Valid @RequestBody GraphicsRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        GraphicsEntity postEntity = this.graphicsService.create(post);
        return new GraphicResponse(GraphicsMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/{id}")
    public GraphicResponse edit(@PathVariable("id") Long id, @Valid @RequestBody GraphicsRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        GraphicsEntity postEntity = this.graphicsService.update(id, post);
        return new GraphicResponse(GraphicsMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public GraphicResponse delete(@PathVariable("id") Long id) throws CustomException {
        this.graphicsService.delete(id);
        return new GraphicResponse();
    }
}
