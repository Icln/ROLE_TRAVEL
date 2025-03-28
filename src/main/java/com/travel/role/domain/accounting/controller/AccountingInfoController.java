package com.travel.role.domain.accounting.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.role.domain.accounting.dto.request.ExpenseDetailCreateReqDTO;
import com.travel.role.domain.accounting.dto.request.ExpenseDetailModifyReqDTO;
import com.travel.role.domain.accounting.dto.response.ExpenseDetailCreateResDTO;
import com.travel.role.domain.accounting.dto.response.ExpenseDetailModifyResDTO;
import com.travel.role.domain.accounting.dto.response.ExpenseDetailsResDTO;
import com.travel.role.domain.accounting.dto.response.TotalExpenseDetailResDTO;
import com.travel.role.domain.accounting.entity.PaymentMethod;
import com.travel.role.domain.accounting.service.AccountingInfoReadService;
import com.travel.role.domain.accounting.service.AccountingInfoService;
import com.travel.role.global.auth.token.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room/{roomId}/accounting")
public class AccountingInfoController {

	private final AccountingInfoService accountingInfoService;
	private final AccountingInfoReadService accountingInfoReadService;

	@GetMapping
	public ResponseEntity<ExpenseDetailsResDTO> getExpenseDetails(@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable("roomId") Long roomId,
		@RequestParam(name = "date") LocalDate searchDate,
		@RequestParam(name = "paymentMethod", required = false) PaymentMethod paymentMethod) {

		ExpenseDetailsResDTO resDTO = accountingInfoReadService.getExpenseDetails(
			userPrincipal.getEmail(), roomId, searchDate, paymentMethod);

		return ResponseEntity.ok(resDTO);
	}

	@GetMapping("/total")
	public ResponseEntity<TotalExpenseDetailResDTO> getTotalExpenseDetails(@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable("roomId") Long roomId){

		TotalExpenseDetailResDTO resDTO = accountingInfoReadService.getTotalExpenseDetails(userPrincipal.getEmail(), roomId);

		return ResponseEntity.ok(resDTO);
	}

	@PostMapping
	public ResponseEntity<ExpenseDetailCreateResDTO> createExpenseDetail(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable("roomId") Long roomId,
		@RequestBody @Valid ExpenseDetailCreateReqDTO requestDTO) {

		ExpenseDetailCreateResDTO resDTO = accountingInfoService.createExpenseDetail(
			roomId, userPrincipal.getEmail(), requestDTO);

		return new ResponseEntity<>(resDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{accounting_id}")
	public ResponseEntity<ExpenseDetailModifyResDTO> modifyExpenseDetail(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable("roomId") Long roomId,
		@PathVariable("accounting_id") Long accountingId,
		@RequestBody @Valid ExpenseDetailModifyReqDTO requestDTO) {

		ExpenseDetailModifyResDTO resDTO = accountingInfoService.modifyExpenseDetail(
			userPrincipal.getEmail(), roomId, accountingId, requestDTO);

		return ResponseEntity.ok(resDTO);
	}

	@DeleteMapping("/{accounting_id}")
	public void deleteExpenseDetail(
		@AuthenticationPrincipal UserPrincipal userPrincipal,
		@PathVariable("roomId") Long roomId,
		@PathVariable("accounting_id") Long accountingId){

		accountingInfoService.deleteExpenseDetail(userPrincipal.getEmail(), roomId, accountingId);
	}
}
