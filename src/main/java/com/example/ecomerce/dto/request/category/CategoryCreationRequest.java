package com.example.ecomerce.dto.request.category;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreationRequest {
    private String categoryName;
}
