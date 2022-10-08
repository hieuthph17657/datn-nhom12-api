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
public class OrderPaginationRequest extends PaginationRequest {
    private String searchAddress;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (searchAddress != null && !searchAddress.isEmpty()) {
            list.add(new Filter("address", QueryOperator.LIKE, searchAddress, null));
        }
        return list;
    }
}
