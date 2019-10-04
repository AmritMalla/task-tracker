package com.amt.time_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private Long id;

    private String title;

    private String description;

    private LocalDate taskDate;

    private Float taskHour;

    private Float productivity;

    private Long userId;
}
