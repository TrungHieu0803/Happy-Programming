package com.example.happyprogramming.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TotalRequestMonthly {
    Month month;
    int numberOfRequest;
}