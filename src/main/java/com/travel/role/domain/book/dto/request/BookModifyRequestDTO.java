package com.travel.role.domain.book.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.travel.role.domain.accounting.entity.PaymentMethod;
import com.travel.role.global.exception.dto.ExceptionMessage;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookModifyRequestDTO {

	@NotNull(message = ExceptionMessage.BOOK_INFO_ID_VALUE_NOT_EMPTY)
	private Long bookInfoId;

	@NotNull(message = ExceptionMessage.ACCOUNTING_INFO_ID_VALUE_NOT_EMPTY)
	private Long accountingInfoId;

	@NotNull(message = ExceptionMessage.INVALID_PAYMENT_METHOD)
	private PaymentMethod paymentMethod;

	@Min(value = 0, message = ExceptionMessage.EXPENSE_MUST_GREATER_THAN_OR_EQUAL_TO_ZERO)
	private Integer price;

	private String bookEtc;
}
