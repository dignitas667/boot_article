package net.developia.boot_article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import net.developia.boot_article.dto.ArticleDTO;

@Mapper
public interface ArticleDAO {
	void insertArticle(ArticleDTO articleDTO) throws Exception;
	List<ArticleDTO> getArticleList() throws Exception;

	ArticleDTO getDetail(long no) throws Exception;

	void updateReadcount(long no) throws Exception;

	int deleteArticle(ArticleDTO articleDTO) throws Exception;

	int updateArticle(ArticleDTO articleDTO) throws Exception;

	List<ArticleDTO> getArticleListPage(@Param("startNum") long startNum, @Param("endNum") long endNum);

	long getArticleCount() throws Exception;

}
