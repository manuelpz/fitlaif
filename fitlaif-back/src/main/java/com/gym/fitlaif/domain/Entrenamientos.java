package com.gym.fitlaif.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Getter
@Setter
@NoArgsConstructor
public class Entrenamientos {

	@Id
	private String entrenamientoId;
	
	@NotBlank
	private String musculo;
	
	private String img;
	
	private List<String> hashtag;
}
