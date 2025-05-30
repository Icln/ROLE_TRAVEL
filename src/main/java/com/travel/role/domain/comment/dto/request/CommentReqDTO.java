package com.travel.role.domain.comment.dto.request;

import javax.validation.constraints.NotBlank;

import com.travel.role.global.exception.dto.ExceptionMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentReqDTO {

	@NotBlank(message = ExceptionMessage.COMMENT_NOT_EMPTY)
	private String content;
}
