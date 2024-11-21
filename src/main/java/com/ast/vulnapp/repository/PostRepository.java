package com.ast.vulnapp.repository;

import com.ast.vulnapp.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
