package com.travel.role.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponseDTO {
	private Long userId;
	private String name;
	private String email;
	private String profile;
}
