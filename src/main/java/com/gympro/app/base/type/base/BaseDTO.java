package com.gympro.app.base.type.base;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseDTO {
    private Long id;
    private LocalDateTime requestDateTime;

}
