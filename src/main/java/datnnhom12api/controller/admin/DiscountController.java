package datnnhom12api.controller.admin;

import datnnhom12api.entity.DiscountEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.DiscountMapper;
import datnnhom12api.paginationrequest.CategoryPaginationRequest;
import datnnhom12api.paginationrequest.DiscountPaginationRequest;
import datnnhom12api.request.DiscountRequest;
import datnnhom12api.response.DiscountResponse;
import datnnhom12api.service.DiscountService;
import datnnhom12api.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @GetMapping
        public DiscountResponse index(@Valid DiscountPaginationRequest request, BindingResult bindingResult) throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<DiscountEntity> page = discountService.paginate(request.getSearchStartDate(),request.getSearchEndDate(), request.getPage(), request.getLimit(), request.getFilters(), request.getOrders());
        System.out.println(page);
        return new DiscountResponse(DiscountMapper.toPageDTO(page));
    }

    @GetMapping("/{id}")
    public DiscountResponse getId(@PathVariable("id") Long id) throws CustomException{
        DiscountEntity entity =discountService.getByIdDiscount(id);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(entity));
    }

    @PostMapping()
    public DiscountResponse create(@Valid @RequestBody DiscountRequest post, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        DiscountEntity postEntity = discountService.save(post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }

    @PutMapping("/{id}")
    public DiscountResponse edit(@PathVariable("id") Long id, @Valid @RequestBody DiscountRequest post, BindingResult bindingResult) throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        DiscountEntity postEntity = discountService.edit(id, post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }
    @PutMapping("/active/{id}")
    public DiscountResponse active(@PathVariable("id") Long id) throws CustomValidationException, CustomException {
        DiscountEntity postEntity = discountService.active(id);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }
    @PostMapping("/draft")
    public DiscountResponse draft(@RequestBody DiscountRequest post) throws CustomValidationException, CustomException {
        post.setActive(2);
        DiscountEntity postEntity = discountService.save(post);
        return new DiscountResponse(DiscountMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public DiscountResponse delete(@PathVariable("id") Long id) throws CustomException {
        DiscountEntity entity =discountService.getByIdDiscount(id);
        if (entity.getActive()==2) {
            discountService.delete(id);
            return new DiscountResponse();
        }else{
            throw new CustomException();
        }
    }
}
