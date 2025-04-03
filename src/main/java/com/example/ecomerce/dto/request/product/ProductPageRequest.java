package com.example.ecomerce.dto.request.product;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
public class ProductPageRequest {

    private int page = 0;
    private int size = 10;
    private String sort = "id,asc";

    public ProductPageRequest() {
    }

    public ProductPageRequest(int page, int size, String sort) {
       this.page = page;
       this.size = size;
       this.sort = sort;
    }

    public Pageable toPageable() {
        String[] sortParams = sort.split(",");
        Sort.Direction direction = Sort.Direction.fromString(sortParams[1]);
        Sort sortBy = Sort.by(direction, sortParams[0]);
        return PageRequest.of(page, size, sortBy);
    }

}
