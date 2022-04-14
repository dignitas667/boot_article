package net.developia.boot_article.dao;

import org.apache.ibatis.annotations.Mapper;

import net.developia.boot_article.dto.ArticleDTO;

@Mapper
public interface ArticleDAO {

	void insertArticle(ArticleDTO articleDTO) throws Exception;

}
