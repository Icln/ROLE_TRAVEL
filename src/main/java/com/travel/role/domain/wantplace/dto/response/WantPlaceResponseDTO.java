package com.travel.role.domain.wantplace.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WantPlaceResponseDTO {
	private List<WantPlaceDTO> wantPlaces = new ArrayList<>();
	private Boolean isScheduler;

	private WantPlaceResponseDTO(List<WantPlaceDTO> wantPlaces, Boolean isScheduler) {
		this.wantPlaces = wantPlaces;
		this.isScheduler = isScheduler;
	}

	public static WantPlaceResponseDTO of(List<WantPlaceDTO> wantPlaces, Boolean isScheduler) {
		return new WantPlaceResponseDTO(wantPlaces, isScheduler);
	}
}
