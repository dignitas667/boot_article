package net.developia.boot_article.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component
public class LogAdvice {
	@AfterThrowing(
		pointcut = "execution(* net.developia.boot_article.service.ArticleService*.*(..))", 
		throwing = "exception")
	public void logException(Exception exception) throws Exception {
		log.info("  ❤❤❤ logAdvice.logException 수행중... ❤❤❤  ");
		log.info(exception.toString());
		throw exception;
	}
}