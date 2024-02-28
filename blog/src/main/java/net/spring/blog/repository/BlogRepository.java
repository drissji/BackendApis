package net.spring.blog.repository;

import net.spring.blog.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog,Long> {
}
