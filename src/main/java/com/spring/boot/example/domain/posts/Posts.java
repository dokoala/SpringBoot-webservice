package com.spring.boot.example.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter//클래스 내 모든 필드의 Getter  메소드를 자동 생성
@NoArgsConstructor//기본 생성자 자동 추가
@Entity// 테이블과 링크될 클래스.
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
// ex) SalesManager.java -> sales_mangertable
public class Posts {
    @Id // 해당 테이블의 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pk의 생성규칙
    // spring boot 2.0에서는 GenerationType.IDENTITY옵션을 추가해야
    // auto_increment가 된다.
    private Long id;

    @Column(length = 500, nullable = false)
    //테이블의 칼럼. 선언 하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨.
    //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용.
    //사이즈를 늘린다거나 타입을 바꾼다거나..
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public  Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
