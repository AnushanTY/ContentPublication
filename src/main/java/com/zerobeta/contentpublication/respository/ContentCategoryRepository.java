package com.zerobeta.contentpublication.respository;

import com.zerobeta.contentpublication.entity.ContentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentCategoryRepository extends JpaRepository<ContentCategory, Integer> {
}
