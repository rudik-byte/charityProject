package com.takebook.demo.dto;

import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

    @Null(groups = OnCreateValidationGroup.class)
    @NotNull(groups = OnUpdateValidationGroup.class)
    private Long id;

    @NotNull(groups = OnUpdateValidationGroup.class)
    private Long book_id;

    @NotNull(groups = OnUpdateValidationGroup.class)
    private Long user_id;

    private Date dateRented;

    private Date dateReturned;
}
