package com.travel.role.domain.accounting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.role.domain.accounting.dto.response.ExpenseDetailResDTO;
import com.travel.role.domain.accounting.dto.response.ExpenseDetailsResDTO;
import com.travel.role.domain.accounting.dto.response.TotalExpenseDetailResDTO;
import com.travel.role.domain.accounting.entity.AccountingInfo;
import com.travel.role.domain.accounting.entity.PaymentMethod;
import com.travel.role.domain.accounting.repository.AccountingInfoRepository;
import com.travel.role.domain.room.entity.Room;
import com.travel.role.domain.room.service.RoomParticipantReadService;
import com.travel.role.domain.room.service.RoomReadService;
import com.travel.role.domain.user.entity.User;
import com.travel.role.domain.user.service.UserReadService;
import com.travel.role.global.exception.accounting.AccountingInfoNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountingInfoReadService {

	private final UserReadService userReadService;
	private final RoomReadService roomReadService;
	private final RoomParticipantReadService roomParticipantReadService;
	private final AccountingInfoRepository accountingInfoRepository;

	public AccountingInfo findAccountingInfoByIdOrElseThrow(Long accountingInfoId) {

		return accountingInfoRepository.findById(accountingInfoId)
			.orElseThrow(AccountingInfoNotFoundException::new);
	}

	public ExpenseDetailsResDTO getExpenseDetails(String email, Long roomId, LocalDate searchDate,
		PaymentMethod paymentMethod) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		roomParticipantReadService.checkParticipant(loginUser, room);

		List<ExpenseDetailResDTO> expenseDetailResDTOS = accountingInfoRepository.findAllByRoomIdAndDateAndPaymentMethod(
				roomId, searchDate, paymentMethod).stream()
			.map(ExpenseDetailResDTO::from)
			.collect(Collectors.toList());

		return ExpenseDetailsResDTO.from(expenseDetailResDTOS);
	}

	public TotalExpenseDetailResDTO getTotalExpenseDetails(String email, Long roomId) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		roomParticipantReadService.checkParticipant(loginUser, room);

		int totalExpense = accountingInfoRepository.findTotalExpenseByRoomId(roomId);

		return new TotalExpenseDetailResDTO(totalExpense);
	}

	public List<AccountingInfo> findAccountingInfoByRoomIdOrBoardIds(Long roomId, List<Long> boardIds) {
		return accountingInfoRepository.findAccountingInfoByRoomIdOrBoardIds(roomId, boardIds);
	}

	public List<AccountingInfo> findAccountingInfoByRoomIdAndBoardIds(Long roomId, List<Long> boardIds) {
		return accountingInfoRepository.findAccountingInfoByRoomIdAndBoardIds(roomId, boardIds);
	}

}
