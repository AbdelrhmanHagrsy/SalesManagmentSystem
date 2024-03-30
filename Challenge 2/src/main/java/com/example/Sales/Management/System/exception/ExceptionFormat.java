package com.example.Sales.Management.System.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExceptionFormat {
    public boolean status;
    public LocalTime time;
    public String message;
    public List<String> details;
}
