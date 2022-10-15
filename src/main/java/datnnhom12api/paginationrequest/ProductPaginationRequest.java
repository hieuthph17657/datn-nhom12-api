package datnnhom12api.paginationrequest;

import datnnhom12api.dto.core.Filter;
import datnnhom12api.dto.core.PaginationRequest;
import datnnhom12api.dto.core.QueryOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPaginationRequest extends PaginationRequest {
    private String searchProductName;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchProductName != null && !searchProductName.isEmpty()) {
            list.add(new Filter("name", QueryOperator.LIKE, searchProductName, null));
        }
        return list;
    }
}
