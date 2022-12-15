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
public class CardPaginationrequest extends PaginationRequest {
    private String trandemark;
    private String memory;

    public List<Filter> getFilters() {
        List<Filter> list = new ArrayList<>();
        if (trandemark != null && !trandemark.isEmpty()) {
            list.add(new Filter("trandemark", QueryOperator.LIKE, trandemark, null));
        }if (memory != null && !memory.isEmpty()) {
            list.add(new Filter("memory", QueryOperator.LIKE, memory, null));
        }
        return list;
    }
}
