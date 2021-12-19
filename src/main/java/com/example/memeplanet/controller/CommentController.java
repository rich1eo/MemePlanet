package com.example.memeplanet.controller;

import com.example.memeplanet.model.Comment;
import com.example.memeplanet.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Comment create(@RequestBody Comment comment, Principal principal) {
        return commentService.create(comment, commentService.getUserByPrincipal(principal));
    }
}
