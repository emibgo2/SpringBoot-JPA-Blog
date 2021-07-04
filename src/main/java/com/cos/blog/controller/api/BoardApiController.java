package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.LikeSaveRequestDto;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.LikeRepository;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{boardId}/like")
    public ResponseDto<Integer> likeUpdate(@PathVariable int boardId, @RequestBody Board board) {
        System.out.println("11111111111111");
        boardService.좋아요히트(boardId, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/like")
    public ResponseDto<Integer> likeListUpdate(@PathVariable int boardId,@RequestBody LikeSaveRequestDto likeSaveRequestDto) {
        boardService.좋아요히트(boardId, likeSaveRequestDto);
        System.out.println("222222222222222222");

        System.out.println(likeSaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터 받을때 컨트롤러에서 DTO를 만들어서 받는게 좋다.
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {

        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
