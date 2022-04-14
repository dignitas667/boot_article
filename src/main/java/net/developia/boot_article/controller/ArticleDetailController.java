package net.developia.boot_article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;

@Log4j2
@Controller
@RequestMapping("article/{pg}/{no}")
public class ArticleDetailController {
	
	@Autowired
	private ArticleService articleService;

	@GetMapping(value = "/")
	public String detail(
			@PathVariable long pg,
			@PathVariable long no,
			@RequestParam long vn, // virtual number
			Model model) {
		
		try {
			ArticleDTO articleDTO = articleService.getDetail(no);
			model.addAttribute("articleDTO", articleDTO);
			model.addAttribute("vn", vn);
			return "article/detail";
		} catch(RuntimeException e) { 
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "../");
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", "상세보기 에러");
			model.addAttribute("url", "../");
			return "result";
		}
	}
	
	@GetMapping("delete")
	public String delete(@PathVariable long no, Model model) {
		return "article/delete";
	}
	
	@PostMapping("delete")
	public ModelAndView delete(@ModelAttribute ArticleDTO articleDTO) {
		log.info(articleDTO.toString());
		ModelAndView mav = new ModelAndView("result");
		try {
			articleService.deleteArticle(articleDTO);
			mav.addObject("msg", articleDTO.getNo() + "번 게시물이 삭제되었습니다.");
			mav.addObject("url", "../../1/");
		} catch (Exception e) {
			mav.addObject("msg", e.getMessage());
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	
	@GetMapping("update")
	public String update(long vn, @PathVariable long no, Model model) {
		try {
			ArticleDTO articleDTO = articleService.getDetail(no);
			model.addAttribute("articleDTO", articleDTO);
			model.addAttribute("vn", vn);
			return "article/update";
		} catch (Exception e) {
			model.addAttribute("msg", "해당하는 게시물이 없거나 시스템 에러입니다.");
			model.addAttribute("url", "../../1/");
			return "result";
		}
	}
	
	@PostMapping("update")
	public String updateArticle(
		@ModelAttribute ArticleDTO articleDTO,
		long vn,	
		Model model) {
		
		log.info(articleDTO.toString());
		try {
			articleService.updateArticle(articleDTO);
			model.addAttribute("msg", articleDTO.getNo() + "번 게시물이 수정되었습니다.");
			model.addAttribute("url", "./?vn=" + vn);
			return "result";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}