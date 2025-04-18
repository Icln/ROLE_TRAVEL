package com.travel.role.global.exception;

import java.time.LocalDateTime;

import javax.mail.SendFailedException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazonaws.SdkClientException;
import com.travel.role.global.exception.accounting.AccountingInfoCannotBeDeletedException;
import com.travel.role.global.exception.accounting.AccountingInfoCannotBeModifiedException;
import com.travel.role.global.exception.accounting.AccountingInfoNotFoundException;
import com.travel.role.global.exception.auth.InvalidTokenException;
import com.travel.role.global.exception.auth.NotExistTokenException;
import com.travel.role.global.exception.board.BoardNotFoundException;
import com.travel.role.global.exception.board.BookInfoNotFoundException;
import com.travel.role.global.exception.comment.CommentInfoNotFoundException;
import com.travel.role.global.exception.common.ResourceOperationAccessDeniedException;
import com.travel.role.global.exception.common.S3ImageNotFoundException;
import com.travel.role.global.exception.dto.ExceptionResponse;
import com.travel.role.global.exception.room.AlreadyExistInRoomException;
import com.travel.role.global.exception.room.InvalidInviteCode;
import com.travel.role.global.exception.room.InvalidLocalDateException;
import com.travel.role.global.exception.room.RoomNotUpdateAdminException;
import com.travel.role.global.exception.room.UserHaveNotPrivilegeException;
import com.travel.role.global.exception.user.AlreadyExistUserException;
import com.travel.role.global.exception.user.InputValueNotMatchException;
import com.travel.role.global.exception.user.PlaceInfoNotFoundException;
import com.travel.role.global.exception.user.RoomInfoNotFoundException;
import com.travel.role.global.exception.user.UserInfoNotFoundException;
import com.travel.role.global.exception.user.UserNotParticipateRoomException;
import com.travel.role.global.exception.wantPlace.WantPlaceNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotExistTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse notExistTokenHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse usernameNotFoundHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
	}

	@ExceptionHandler(InvalidTokenException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse invalidTokenHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse badCredentialUserHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
	}

	@ExceptionHandler(AlreadyExistUserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse alreadyExistUserHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(SendFailedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse sendMailFailedHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
	}

	@ExceptionHandler(UserInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse userInfoNotFoundHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
		return new ExceptionResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
			, HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(InvalidLocalDateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse invalidLocalDateException(InvalidLocalDateException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(UserNotParticipateRoomException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionResponse userNotParticipateRoomHandler(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
	}

	@ExceptionHandler(InputValueNotMatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse inputValueNotMatchException(InputValueNotMatchException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(SdkClientException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse sdkClientException(SdkClientException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
	}

	@ExceptionHandler(S3ImageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse s3ImageNotFoundException(S3ImageNotFoundException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
	}

	@ExceptionHandler(UserHaveNotPrivilegeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse userHaveNotPrivilegeException(UserHaveNotPrivilegeException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(RoomInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse roomInfoNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
	}

	@ExceptionHandler(PlaceInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse placeInfoNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(CommentInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse commentInfoNotFoundException(CommentInfoNotFoundException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
	}

	@ExceptionHandler(ResourceOperationAccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ExceptionResponse resourceOperationAccessDeniedException(ResourceOperationAccessDeniedException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.FORBIDDEN, LocalDateTime.now());
	}

	@ExceptionHandler(InvalidInviteCode.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse invalidInviteCodeException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AlreadyExistInRoomException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse alreadyExistInRoomException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AccountingInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse accountingInfoNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(BookInfoNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse bookInfoNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(BoardNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse boardNotFoundException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AccountingInfoCannotBeDeletedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse accountingInfoCannotBeDeletedException(AccountingInfoCannotBeDeletedException e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(AccountingInfoCannotBeModifiedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse accountingInfoCannotBeModifiedException(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(WantPlaceNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse wantPlaceNotFound(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}

	@ExceptionHandler(RoomNotUpdateAdminException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse roomNotUpdateAdmin(Exception e) {
		return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
	}
}
