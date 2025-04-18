package com.travel.role.domain.comment.service;

import static com.travel.role.global.exception.common.ResourceOperationAccessDeniedException.*;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.role.domain.comment.dto.request.CommentReqDTO;
import com.travel.role.domain.comment.dto.response.PageResDTO;
import com.travel.role.domain.comment.dto.response.CommentResDTO;
import com.travel.role.domain.comment.entity.Comment;
import com.travel.role.domain.comment.repository.CommentRepository;
import com.travel.role.domain.room.entity.Room;
import com.travel.role.domain.room.service.RoomParticipantReadService;
import com.travel.role.domain.room.service.RoomReadService;
import com.travel.role.domain.user.entity.User;
import com.travel.role.domain.user.service.UserReadService;
import com.travel.role.global.exception.common.ResourceOperationAccessDeniedException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	private final CommentReadService commentReadService;
	private final UserReadService userReadService;
	private final RoomReadService roomReadService;
	private final CommentRepository commentRepository;
	private final RoomParticipantReadService roomParticipantReadService;

	public void createComment(String email, Long roomId, Long parentId, CommentReqDTO reqDTO) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		roomParticipantReadService.checkParticipant(loginUser, room);

		Comment newComment = createComment(loginUser, room, parentId, reqDTO.getContent());

		commentRepository.save(newComment);
	}

	@Transactional(readOnly = true)
	public PageResDTO<CommentResDTO> getCommentsInRoom(String email, Long roomId, Pageable pageable) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		roomParticipantReadService.checkParticipant(loginUser, room);

		Page<CommentResDTO> page = commentReadService.getCommentsByRoomId(roomId, pageable);

		return PageResDTO.from(page);
	}

	public void modifyComment(String email, Long roomId, Long commentId, CommentReqDTO reqDTO) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		Comment comment = commentReadService.findCommentByIdOrElseThrow(commentId);

		roomParticipantReadService.checkParticipant(loginUser, room);
		checkAuthorizationForComment(comment, loginUser.getId(), Operation.MODIFY);

		comment.update(reqDTO.getContent());
		commentRepository.save(comment);
	}

	public void deleteComment(String email, Long roomId, Long commentId) {

		User loginUser = userReadService.findUserByEmailOrElseThrow(email);
		Room room = roomReadService.findRoomByIdOrElseThrow(roomId);
		Comment comment = commentReadService.findCommentByIdOrElseThrow(commentId);

		roomParticipantReadService.checkParticipant(loginUser, room);
		checkAuthorizationForComment(comment, loginUser.getId(), Operation.DELETE);

		commentRepository.dynamicDeleteById(commentId);
	}

	public void deleteCommentsByRoomIdAndUserId(Long roomId, Long userId) {

		commentRepository.dynamicDeleteByUserIdAndRoomId(roomId, userId);
	}

	public void deleteAllByRoomId(Long roomId){

		commentRepository.deleteAllByRoomId(roomId);
	}

	private Comment createComment(User fromUser, Room room, Long parentId, String content) {

		Comment newComment;

		if (parentId == null) {
			newComment = Comment.ofParent(fromUser, room, content);
			commentRepository.save(newComment);
			newComment.setGroupId(newComment.getId());
		} else {
			Comment parentComment = commentReadService
				.findCommentWithFromUserByIdOrElseThrow(parentId);

			newComment =
				Comment.ofChild(fromUser, parentComment.getFromUser(), room, parentComment.getGroupId(), content);
		}

		return newComment;
	}

	private void checkAuthorizationForComment(Comment comment, Long userId, Operation operation) {

		if (!Objects.equals(comment.getFromUser().getId(), userId)) {
			throw new ResourceOperationAccessDeniedException(Resource.COMMENT, operation);
		}
	}
}
