package com.example.project.service;

import com.example.project.common.PagingConst;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.LikeCheckDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.LikeCheckEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.TrafficEntity;
import com.example.project.repository.BoardRepository;
import com.example.project.repository.LikeCheckRepository;
import com.example.project.repository.MemberRepository;
import com.example.project.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TrafficRepository trafficRepository;
    private final LikeCheckRepository likeCheckRepository;

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
                        board.getTrafficEntity().getTrafficId(),
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

    public Page<BoardDTO> findAllList(Pageable pageable) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
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

    public Page<BoardDTO> location(Pageable pageable, Long id, String local1, String local2) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = null;
        if (id == 1){
            boardEntities = boardRepository.findByBoardTypeLocation1AndBoardTypeLocation2(local1,local2,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        }else if (id==2){
            boardEntities = boardRepository.findByBoardTypeContainingAndBoardTypeLocation1ContainingAndBoardTypeLocation2Containing("자유",local1,local2,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        }else if (id==3){
            boardEntities = boardRepository.findByBoardTypeContainingAndBoardTypeLocation1ContainingAndBoardTypeLocation2Containing("신호",local1,local2,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
        }
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

    public Page<BoardDTO> search(Pageable pageable, String search) {
        int page = pageable.getPageNumber(); // 요청 페이지값 가져옴.
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1; // 삼항연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findByBoardTitleContaining(search,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "boardId")));
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

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> findById = boardRepository.findById(id);
        BoardEntity boardEntity = findById.get();
        BoardDTO boardDTO = new BoardDTO();
        if (boardEntity.getBoardType().equals("신호")){
            boardDTO = BoardDTO.toTrafficBoardDTO(boardEntity);
        }else {
            boardDTO = BoardDTO.toBoardDTO(boardEntity);
        }

        boardDTO.setMemberNickname(boardEntity.getMemberEntity().getMemberNickname());
        return boardDTO;
    }

    @Transactional
    public void likeCheck(Long like, Long boardId , Long loginId) {
        Optional<BoardEntity> byId = boardRepository.findById(boardId);
        LikeCheckEntity likeCheckEntity = likeCheckRepository.findByBoardEntity(byId.get());
        Optional<MemberEntity> memberId = memberRepository.findById(loginId);
        // 첫 등록
        if (likeCheckEntity == null){
            if (like == 1){ //좋아요
                LikeCheckEntity likeCheckEntity1 = LikeCheckEntity.toLikeCheckSaveEntity(true, memberId.get() , byId.get());
                likeCheckRepository.save(likeCheckEntity1);
                boardRepository.like(boardId);
            }else{ // 싫어요
                likeCheckRepository.save(LikeCheckEntity.toLikeCheckSaveEntity(false,memberRepository.findById(loginId).get(),byId.get()));
                boardRepository.dislike(boardId);
            }

        }else if(likeCheckEntity.isLikeCheck()){ // 좋아요 누른상태
            if (like == 1){ //좋아요
                likeCheckRepository.deleteById(likeCheckEntity.getLikeCheckId());
            }else{ // 싫어요
                likeCheckEntity.setLikeCheck(false);
                likeCheckRepository.save(likeCheckEntity);
                boardRepository.dislike(boardId);
            }
            boardRepository.UnLike(boardId);
        }else { // 싫어요 누른상태
            if (like == 1){ //좋아요
                likeCheckEntity.setLikeCheck(true);
                likeCheckRepository.save(likeCheckEntity);
                boardRepository.like(boardId);
            }else{ // 싫어요
                likeCheckRepository.deleteById(likeCheckEntity.getLikeCheckId());
            }
            boardRepository.UnDislike(boardId);
        }

    }

    public void update(BoardDTO boardDTO) {
        MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId()).get();
        System.out.println(memberEntity.getMemberId());
        System.out.println(boardDTO);
        if (boardDTO.getBoardType().equals("신호")){
            TrafficEntity trafficEntity = trafficRepository.findById(boardDTO.getTrafficId()).get();
            boardRepository.save(BoardEntity.toBoardTrafficUpdateEntity(boardDTO,memberEntity,trafficEntity));
        }else{
            boardRepository.save(BoardEntity.toBoardUpdateEntity(boardDTO,memberEntity));
        }

    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDTO> hots() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<BoardEntity> allByBoardCreatedTimeBetween = boardRepository.findAllByBoardCreatedTimeBetweenOrderByBoardLikeDesc(startDatetime,endDatetime);
        List<BoardDTO> boardDTOS = new ArrayList<>();
        int i = 0;
        for(BoardEntity boardEntity : allByBoardCreatedTimeBetween){
            if (boardEntity.getBoardType().equals("신호")){
                boardDTOS.add(BoardDTO.toTrafficBoardDTO(boardEntity));
                boardDTOS.get(i).setMemberNickname(boardEntity.getMemberEntity().getMemberNickname());
            }else{
                boardDTOS.add(BoardDTO.toBoardDTO(boardEntity));
                boardDTOS.get(i).setMemberNickname(boardEntity.getMemberEntity().getMemberNickname());
            }
            i++;
        }
        return boardDTOS;
    }
}
