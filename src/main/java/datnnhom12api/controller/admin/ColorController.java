package datnnhom12api.controller.admin;


import datnnhom12api.entity.CategoryEntity;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CategoryMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.response.CategoryResponse;
import datnnhom12api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ColorController {
    @Autowired
    CategoryService cateService;

    @GetMapping("/auth/colors")
    public CategoryResponse index(@Valid CategoryPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<CategoryEntity> page = cateService.paginate(request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        return new CategoryResponse(CategoryMapper.toPageDTO(page));
    }
}
