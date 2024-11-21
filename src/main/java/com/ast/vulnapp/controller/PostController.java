package com.ast.vulnapp.controller;

import com.ast.vulnapp.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class PostController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/posts")
    String posts(@RequestParam("postId") String id, Model model) {
        String query = "SELECT text FROM posts WHERE post_id = '" + id + "'";
        try (Connection connection = this.dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            Collection<String> posts = toPosts(() -> statement.executeQuery(query));
            model.addAttribute("posts", posts);
            model.addAttribute("postId", id);
            return "posts";
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Invalid post id", ex);
        }
    }

    @PostMapping("/post")
    String post(@RequestParam("postId") Long id, @RequestParam("text") String text) {
        addPost(new Post(id, text));
        return "redirect:/posts?postId=" + id;
    }

    @GetMapping("/post/{id}")
    @ResponseBody String post(@PathVariable("id") Long id) {
        String query = "SELECT text FROM posts WHERE id = '" + id + "'";
        try (Connection connection = this.dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            Collection<String> posts = toPosts(() -> statement.executeQuery(query));
            if (posts.isEmpty()) {
                return null;
            }
            return HtmlUtils.htmlEscape(posts.iterator().next());
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Invalid post id", ex);
        }
    }

    private boolean addPost(Post post) {
        String query = "INSERT INTO posts (post_id, text) VALUES (?, ?)";
        try (Connection connection = this.dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, post.getPostId());
            statement.setString(2, post.getText());
            return statement.execute();
        } catch (SQLException ex) {
            throw new IllegalArgumentException("Bad request", ex);
        }
    }

    private Collection<String> toPosts(ResultsSupplier supplier) throws SQLException {
        Collection<String> posts = new ArrayList<>();
        try (ResultSet resultSet = supplier.get()) {
            while (resultSet.next()) {
                String text = resultSet.getString("text");
                posts.add(text);
            }
        }
        return posts;
    }

    private interface ResultsSupplier {
        ResultSet get() throws SQLException;
    }
}
