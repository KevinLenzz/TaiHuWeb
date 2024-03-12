package com.dvweb4th.springprj.dao;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneReview{
    private LocalDateTime time;
    private String name;
    private String content;
    private Double rate;
    private Integer foodnum;
}
