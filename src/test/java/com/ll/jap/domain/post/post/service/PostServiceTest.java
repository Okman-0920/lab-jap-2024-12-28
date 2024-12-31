package com.ll.jap.domain.post.post.service;

import com.ll.jap.domain.post.post.entity.Post;
import com.ll.jap.domain.post.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private PostRepository postRepository;

    @Test
    @DisplayName("글 2개 생성")
    @Transactional
    void T1() {
        postService.write("글1", "내용1");
        postService.write("글2", "내용2");
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
}
