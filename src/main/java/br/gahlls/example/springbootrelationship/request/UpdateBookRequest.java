package br.gahlls.example.springbootrelationship.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookRequest {

	private String title;
	private Double price;
	
	@JsonProperty("id-category")
	private int idBookCategory;
}
