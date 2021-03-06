package com.example.memeplanet.service;

import com.example.memeplanet.model.Comment;
import com.example.memeplanet.model.User;
import com.example.memeplanet.repositoty.CommentRepository;
import com.example.memeplanet.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment create(Comment comment, String  user) {
        comment.setUser(user);
        commentRepository.save(comment);
        return comment;
    }

    public List<Comment> listComments() {
        return commentRepository.findAll();
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
