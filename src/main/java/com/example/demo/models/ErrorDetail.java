package com.example.demo.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
	@Builder.Default
	private Date timestamp = new Date();
	private String message;
	private String origin;
	private List<Map<String, String>> details;
}