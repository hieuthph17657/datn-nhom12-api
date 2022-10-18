package datnnhom12api.controller.admin;

import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CategoryMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.request.CategoryRequest;
import datnnhom12api.response.CategoryResponse;
import datnnhom12api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService cateService;

    @GetMapping
    public CategoryResponse index(@Valid CategoryPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<CategoryEntity> page = cateService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new CategoryResponse(CategoryMapper.toPageDTO(page));
    }

    @PostMapping()
    public CategoryResponse create(@Valid @RequestBody CategoryRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CategoryEntity postEntity = cateService.save(post);
        return new CategoryResponse(CategoryMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/{id}")
    public CategoryResponse edit(@PathVariable("id") Long id, @Valid @RequestBody CategoryRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CategoryEntity postEntity = cateService.edit(id, post);
        return new CategoryResponse(CategoryMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable("id") Long id) throws CustomException {
        cateService.delete(id);
        return new CategoryResponse();
    }
}
