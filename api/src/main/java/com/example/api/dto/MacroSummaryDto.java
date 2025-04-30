package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MacroSummaryDto {
    private double totalCalories;
    private double totalProteins;
    private double totalFibers;
    private double totalCarbohydrates;
}
