package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.member.entity.Member;
import com.ll.jap.domain.member.service.MemberService;
import com.ll.jap.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    void T1() {
        Member user1 = memberService.findByUsername("user1").get();

        postService.write(user1,"글1", "내용1");
        postService.write(user1,"글2", "내용2");
    }

    @Test
    @DisplayName("findAll")
    void T2() {
        List<Post> findAll = postService.findAll();
        assertEquals(3, findAll.size());

        Post post = findAll.get(0);
        assertEquals("title1", post.getTitle());
    }

    @Test
    @DisplayName("findById")
    void T3() {
        Optional<Post> opPost = postService.findById(2);

        if (opPost.isPresent()) {
            Post post1 = opPost.get();
            assertEquals("title2", post1.getTitle());
        }

    }

    @Test
    @DisplayName("findByTitle")
    void T4() {
        // Optional 사용불가: 옵셔널은 1개의 값만 사용하기 때문
        Post post = postService.findByTitle("title2").get(0);
        assertEquals(2, post.getId());
    }

    @Test
    @DisplayName("findByTitleAndContent")
    void T5() {
        Post post = postService.findByTitleAndContent("title1", "content1").get(0);
        assertEquals(1, post.getId());
    }

    @Test
    @DisplayName("findByTitleLike")
    void T6() {
        List<Post> posts = postService.findByTitleLike("%2");
        Post post = posts.get(0);
        // 여기서 get은 posts리모콘에서 받아온 값의 index의 첫번째라는 의미
        // 즉, 어차피 findByTitleLike에서 id값은 2인 0번 index의 값을 post에 주입
        assertEquals("title2", post.getTitle());
        // 결국, post에 주입된 0번째 값은 title2 이기 때문에, 정답이 됨.
    }

    @Test
    @DisplayName("findByTitleLikeOrderByIdDesc")
    void T7() {
        List<Post> posts = postService.findByTitleLikeOrderByIdDesc("title%");
        assertEquals(3, posts.size());
        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
    }

    @Test
    @DisplayName("findByIdDesc")
    void T8() {
        List<Post> posts = postService.findByOrderByIdDesc();
        assertEquals(3, posts.size());
        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
    }

    @Test
    @DisplayName("findTop2ByTitleLikeOrderByIdDesc")
        // SELECT * FROM post WHERE title LIKE 'title%' ORDER BY id DESC LIMIT 2;
    void t9() {
        List<Post> posts = postService.findTop2ByTitleLikeOrderByIdDesc("title%");
        assertEquals(2, posts.size()); // LIMIT가 된것을 확인
        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
    }

    @Test
    @DisplayName("findTop2ByOrderByIdDesc")
        // SELECT * FROM post WHERE ORDER BY id DESC LIMIT 2;
    void t10() {
        List<Post> posts = postService.findTop2ByOrderByIdDesc();
        assertEquals(2, posts.size());
        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
    }

    @Test
    @DisplayName("findAll(Pageable pageable)")
        // SELECT * FROM post WHERE ORDER BY id DESC LIMIT 2;
    void t11() {
        int itemsPerPage = 2; // 한 페이지에 보여줄 아이템 수
        int pageNumber = 2; // 현재 페이지 == 2
        pageNumber--; // 1을 빼는 이유는 jpa는 페이지 번호를 0부터 시작하기 때문
        Pageable pageable =
                PageRequest.of(
                        pageNumber,
                        itemsPerPage,
                        Sort.by(Sort.Direction.DESC, "id"));

        Page<Post> postPage = postService.findAll(pageable);
        List<Post> posts = postPage.getContent();
        assertEquals(1, posts.size()); // 글이 총 3개이고, 현재 페이지는 2이므로 1개만 보여야 함
        Post post = posts.get(0);
        assertEquals(1, post.getId());
        assertEquals("title1", post.getTitle());
        assertEquals(3, postPage.getTotalElements()); // 전체 글 수
        assertEquals(2, postPage.getTotalPages()); // 전체 페이지 수
        assertEquals(1, postPage.getNumberOfElements()); // 현재 페이지에 노출된 글 수
        assertEquals(pageNumber, postPage.getNumber()); // 현재 페이지 번호
    }

    @Test
    @DisplayName("findByTitleLike(Pageable pageable)")
        // SELECT * FROM post WHERE title LIKE 'title%' ORDER BY id DESC LIMIT 2, 2;
    void t12() {
        int itemsPerPage = 2;
        int pageNumber = 2;
        pageNumber--;

        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> postPage = postService.findByTitleLike("title%", pageable);
        List<Post> posts = postPage.getContent();
        assertEquals(1, posts.size()); // 확인하는 코드
        Post post = posts.get(0); // 올바르게 가져왔는지에 대한 테스트1
        assertEquals(1, post.getId());
        // 내림차순 정렬하고, 2페이지에 있기 때문에 Id가 1임
        assertEquals("title1", post.getTitle());
        assertEquals(3, postPage.getTotalElements()); // 목록수
        assertEquals(2, postPage.getTotalPages()); // 총 페이지 수
        assertEquals(1, postPage.getNumberOfElements()); // 페이지에 있는 게시글 수
        assertEquals(pageNumber, postPage.getNumber()); // 페이지 번호
    }

    @Test
    @DisplayName("findByTitleLike(Pageable pageable)")
        // SELECT * FROM post WHERE title LIKE 'title%' ORDER BY id DESC LIMIT 0, 10;
    void t13() {
        int itemsPerPage = 10;
        int pageNumber = 1;
        pageNumber--;
        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage, Sort.by(Sort.Direction.DESC, "id"));
        Page<Post> postPage = postService.findByTitleLike("title%", pageable);
        List<Post> posts = postPage.getContent();
        assertEquals(3, posts.size());
        Post post = posts.get(0);
        assertEquals(3, post.getId());
        assertEquals("title3", post.getTitle());
        assertEquals(3, postPage.getTotalElements());
        assertEquals(1, postPage.getTotalPages());
        assertEquals(3, postPage.getNumberOfElements());
        assertEquals(pageNumber, postPage.getNumber());
    }

    @Test
    @DisplayName("findByAuthorNickname")
    void t14() {
        List<Post> posts = postService.findByAuthorNickname("유저1");
        assertEquals(2, posts.size());
    }
}
