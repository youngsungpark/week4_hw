package com.sparta.boardfinal.controller;


import com.sparta.boardfinal.domain.Blog;
import com.sparta.boardfinal.dto.BlogRequestDto;
import com.sparta.boardfinal.security.UserDetailsImpl;
import com.sparta.boardfinal.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto, @AuthentificationPrincipal UserDetailsImpl userDetails) {
        requestDto.setName(userDetails.getUsername());
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/blogs")
    public List<Blog> getBlog() {
        return blogService.getBlog();
    }

    @GetMapping("/blogs/detail")
    public ModelAndView getOneBlogAndComments(@RequestParam Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.getOneBlogAndComments(id, userDetails);
    }

    @DeleteMapping("/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }
}
