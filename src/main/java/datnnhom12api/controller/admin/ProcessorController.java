package datnnhom12api.controller.admin;

import datnnhom12api.entity.CartEntity;
import datnnhom12api.entity.ProcessorEntity;
import datnnhom12api.exceptions.CustomException;
import datnnhom12api.exceptions.CustomValidationException;
import datnnhom12api.mapper.CartMapper;
import datnnhom12api.mapper.ProcessorMapper;
import datnnhom12api.paginationrequest.CartPaginationRequest;
import datnnhom12api.request.CartRequest;
import datnnhom12api.request.ProcessorRequest;
import datnnhom12api.response.CartResponse;
import datnnhom12api.response.ProcessorResponse;
import datnnhom12api.service.CartService;
import datnnhom12api.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    @GetMapping
    public ProcessorResponse index(@Valid CartPaginationRequest request, BindingResult bindingResult)
            throws CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<ProcessorEntity> page = processorService.paginate(request.getPage(), request.getLimit(), request.getFilters(),
                request.getOrders());
        return new ProcessorResponse(ProcessorMapper.toPageDTO(page));
    }

    @PostMapping()
    public ProcessorResponse create(@Valid @RequestBody ProcessorRequest post, BindingResult bindingResult)
            throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProcessorEntity postEntity = processorService.create(post);
        return new ProcessorResponse(ProcessorMapper.getInstance().toDTO(postEntity));
    }
    @PutMapping("/{id}")
    public ProcessorResponse edit(@PathVariable("id") Long id, @Valid @RequestBody ProcessorRequest post, BindingResult bindingResult)
            throws CustomValidationException, CustomException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProcessorEntity postEntity = processorService.update(id, post);
        return new ProcessorResponse(ProcessorMapper.getInstance().toDTO(postEntity));
    }

    @DeleteMapping("/{id}")
    public ProcessorResponse delete(@PathVariable("id") Long id) throws CustomException {
        processorService.delete(id);
        return new ProcessorResponse();
    }

}
