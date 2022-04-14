package net.developia.boot_article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import net.developia.boot_article.dao.ArticleDAO;
import net.developia.boot_article.dto.ArticleDTO;

@Log4j2
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDAO articleDAO;
	
	//@Value("${pageSize}")
	private int pageSize = 10;
	
	@Override
	public void insertArticle(ArticleDTO articleDTO) throws Exception {
		articleDAO.insertArticle(articleDTO);
	}
	
	@Override
	public long getArticleCount() throws Exception {
		return articleDAO.getArticleCount();
	} 
	
	@Override
	public List<ArticleDTO> getArticleListPage(long pg) throws Exception {
		long startNum = (pg - 1) * pageSize + 1;
		long endNum   = pg * pageSize;
	
		return articleDAO.getArticleListPage(startNum, endNum);
	}
	
	/*
	@Override
	public List<ArticleDTO> getArticleList() throws Exception {
		return articleDAO.getArticleList();
	}
	
	@Override
	public ArticleDTO getDetail(long no) throws Exception {
		if (no == -1) {
			throw new RuntimeException("잘못된 접근입니다.");
		}
		articleDAO.updateReadcount(no);
		ArticleDTO articleDTO = articleDAO.getDetail(no);
		if (articleDTO == null) {
			throw new RuntimeException(no + "번 글이 존재하지 않습니다.");
		}
		return articleDTO;
	}
	
	@Override
	public void deleteArticle(ArticleDTO articleDTO) throws Exception {
		if(articleDAO.deleteArticle(articleDTO) == 0) {
			throw new RuntimeException(
				"해당하는 게시물이 없거나 비밀번호가 틀립니다.");
		}
	}
	
	@Override
	public void updateArticle(ArticleDTO articleDTO) throws Exception {
		if(articleDAO.updateArticle(articleDTO) == 0) {
			throw new RuntimeException(
				"해당하는 게시물이 없거나 비밀번호가 틀립니다.");
		}
	}

	
	
	*/
}

