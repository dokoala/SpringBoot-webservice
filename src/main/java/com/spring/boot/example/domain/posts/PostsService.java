package com.spring.boot.example.domain.posts;

import com.spring.boot.example.web.dto.PostsResponseDto;
import com.spring.boot.example.web.dto.PostsSaveRequestDto;

import com.spring.boot.example.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
        //더티체킹 :  트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
        //Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = "+ id));
        return new PostsResponseDto(entity);
    }
}
