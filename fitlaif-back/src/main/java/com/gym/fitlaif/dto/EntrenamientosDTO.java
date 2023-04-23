package com.gym.fitlaif.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class EntrenamientosDTO {
	private String entrenamientoId;
	private String musculo;
	private String img;
	private List<String> hashtag;
}
