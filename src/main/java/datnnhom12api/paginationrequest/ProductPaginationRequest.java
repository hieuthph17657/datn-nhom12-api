package datnnhom12api.paginationrequest;

import datnnhom12api.core.Filter;
import datnnhom12api.core.PaginationRequest;
import datnnhom12api.core.QueryOperator;
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

    private String searchStatus;

    private String searchPrice;

    private String searchDiscount;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchProductName != null && !searchProductName.isEmpty()) {
            list.add(new Filter("name", QueryOperator.LIKE, searchProductName, null));
        }
        if (searchStatus != null && !searchStatus.isEmpty()) {
            list.add(new Filter("status", QueryOperator.EQUALS, searchStatus, null));
        }
        if (searchPrice != null && !searchPrice.isEmpty()) {
            list.add(new Filter("price", QueryOperator.GREATER_THAN, searchPrice, null));
        }
        return list;
    }
}
