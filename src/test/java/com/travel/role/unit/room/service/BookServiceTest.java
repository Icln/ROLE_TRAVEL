package com.travel.role.unit.room.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.travel.role.domain.accounting.entity.AccountingInfo;
import com.travel.role.domain.accounting.entity.Category;
import com.travel.role.domain.book.dto.response.BookInfoResponseDTO;
import com.travel.role.domain.book.entity.BookInfo;
import com.travel.role.domain.book.service.BookReadService;
import com.travel.role.domain.book.service.BookService;
import com.travel.role.domain.room.entity.Room;
import com.travel.role.domain.room.service.RoomParticipantReadService;
import com.travel.role.domain.room.service.RoomReadService;
import com.travel.role.domain.schedule.dto.request.ScheduleRequestDTO;
import com.travel.role.domain.schedule.entity.Board;
import com.travel.role.domain.schedule.entity.ScheduleInfo;
import com.travel.role.domain.schedule.service.ScheduleService;
import com.travel.role.domain.user.entity.User;
import com.travel.role.domain.user.service.UserReadService;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	@Mock
	private UserReadService userReadService;

	@Mock
	private RoomReadService roomReadService;

	@Mock
	private RoomParticipantReadService roomParticipantReadService;

	@Mock
	private BookReadService bookReadService;

	@Mock
	private ScheduleService scheduleService;

	@InjectMocks
	private BookService bookService;

	@Test
	void 예약페이지_조회_성공() {
		//given
		User user = makeUser(1L);
		Room room = makeRoom(1L);
		LocalDate date = LocalDate.now();
		given(userReadService.findUserByEmailOrElseThrow(anyString()))
			.willReturn(user);
		given(roomReadService.findRoomByIdOrElseThrow(anyLong()))
			.willReturn(room);
		given(bookReadService.findBookInfoForDate(anyLong(), any(LocalDate.class)))
			.willReturn(findBoardList());
		doNothing()
			.when(roomParticipantReadService).checkParticipant(any(User.class), any(Room.class));
		doNothing()
			.when(scheduleService).validateDate(any(LocalDate.class), any(LocalDate.class), any(LocalDate.class));

		//when
		List<BookInfoResponseDTO> result = bookService.getBookInfo("asd@naver.com", 1L, LocalDate.now());

		//then
		assertThat(result.get(0).getPlaceName()).isEqualTo("우도");
		assertThat(result.get(0).getCategory()).isEqualTo(Category.ETC);
		assertThat(result.get(0).getPrice()).isEqualTo(0);
	}

	private User makeUser(Long id) {
		return User.builder()
			.id(id)
			.email("asd@naver.com")
			.name("asd")
			.build();
	}

	private Room makeRoom(Long id) {
		return Room.builder()
			.id(id)
			.location("korea")
			.roomName("asd")
			.travelStartDate(LocalDate.now())
			.travelEndDate(LocalDate.now())
			.build();
	}

	private ScheduleRequestDTO createBoardRequestDTO() {
		return new ScheduleRequestDTO("우도", "제주도", LocalDate.now().atTime(LocalTime.now()), null, true, Category.ETC,
			123.0, 456.0, null, 12345L);
	}

	private List<Board> findBoardList() {
		List<Board> result = new ArrayList<>();
		Board temp = Board.of(makeRoom(1L), createBoardRequestDTO());
		ScheduleInfo scheduleInfo = ScheduleInfo.of(temp, createBoardRequestDTO());
		BookInfo bookInfo = BookInfo.builder()
			.isBooked(false)
			.bookEtc(null)
			.build();
		AccountingInfo accountingInfo = AccountingInfo.builder()
			.accountingEtc(null)
			.board(temp)
			.bookInfo(bookInfo)
			.category(temp.getCategory())
			.paymentMethod(null)
			.paymentName(scheduleInfo.getPlaceName())
			.paymentTime(null)
			.price(0)
			.build();

		Board board = Board.builder()
			.id(1L)
			.scheduleDate(LocalDateTime.now())
			.category(Category.ETC)
			.scheduleInfo(scheduleInfo)
			.accountingInfo(accountingInfo)
			.build();

		result.add(board);

		return result;
	}
}
