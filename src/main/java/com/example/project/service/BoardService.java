package com.example.project.service;

import com.example.project.common.PagingConst;
import com.example.project.dto.BoardDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TrafficRepository trafficRepository;

    //이현
    public void save(BoardDTO boardDTO, Long memberId) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(memberId);
        BoardEntity boardEntity = new BoardEntity();
        if (boardDTO.getBoardType().equals("신호")) {
            Optional<TrafficEntity> trafficEntity = trafficRepository.findById(boardDTO.getTrafficId());
            boardEntity = BoardEntity.toBoardTrafficSaveEntity(boardDTO, memberEntity.get(), trafficEntity.get());
        } else {
            boardEntity = BoardEntity.toBoardSaveEntity(boardDTO, memberEntity.get());
        }
        boardRepository.save(boardEntity);
    }

    // 이현
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        int i = 0;
        for (BoardEntity boardEntity : boardEntityList){
            if (boardEntity.getBoardType().equals("신호")){
                boardDTOList.add(BoardDTO.toTrafficBoardDTO(boardEntity));
                boardDTOList.get(i).setMemberNickname(boardEntity.getMemberEntity().getMemberNickname());
            }else{
                boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
                boardDTOList.get(i).setMemberNickname(boardEntity.getMemberEntity().getMemberNickname());
            }
            i++;
        }
        return boardDTOList;
    }

    public Page<BoardDTO> free(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findByBoardType("자유",PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        // Page<BoardEntity> => Page<BoardDTO>
        // board : BoardEntity 객체
        // new BoardDTO() 생성자
        Page<BoardDTO> boardList = boardEntities.map(
                board -> new BoardDTO(board.getBoardId(),
                        board.getMemberEntity().getMemberId(),
                        null,
                        board.getBoardType(),
                        board.getBoardTypeLocation1(),
                        board.getBoardTypeLocation2(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardCreatedTime(),
                        board.getBoardUpdateTime(),
                        board.getBoardLike(),
                        board.getBoardDislike(),
                        board.isManagerCheck(),
                        board.getMemberEntity().getMemberNickname()
                ));
        return boardList;
    }

    public Page<BoardDTO> traffic(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findByBoardType("신호",PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        // Page<BoardEntity> => Page<BoardDTO>
        // board : BoardEntity 객체
        // new BoardDTO() 생성자
        Page<BoardDTO> boardList = boardEntities.map(
                board -> new BoardDTO(board.getBoardId(),
                        board.getMemberEntity().getMemberId(),
                        null,
                        board.getBoardType(),
                        board.getBoardTypeLocation1(),
                        board.getBoardTypeLocation2(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardCreatedTime(),
                        board.getBoardUpdateTime(),
                        board.getBoardLike(),
                        board.getBoardDislike(),
                        board.isManagerCheck(),
                        board.getMemberEntity().getMemberNickname()
                ));
        return boardList;
    }

    public Page<BoardDTO> admin(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findByBoardType("공지",PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        // Page<BoardEntity> => Page<BoardDTO>
        // board : BoardEntity 객체
        // new BoardDTO() 생성자
        Page<BoardDTO> boardList = boardEntities.map(
                board -> new BoardDTO(board.getBoardId(),
                        board.getMemberEntity().getMemberId(),
                        null,
                        board.getBoardType(),
                        board.getBoardTypeLocation1(),
                        board.getBoardTypeLocation2(),
                        board.getBoardTitle(),
                        board.getBoardContents(),
                        board.getBoardCreatedTime(),
                        board.getBoardUpdateTime(),
                        board.getBoardLike(),
                        board.getBoardDislike(),
                        board.isManagerCheck(),
                        board.getMemberEntity().getMemberNickname()
                ));
        return boardList;
    }
}
