package org.doit.ik.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeDao dao;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	//삭제
	@GetMapping(value = "noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq) throws ClassNotFoundException, SQLException {
		
	int deletecnt=this.dao.delete(seq);

	if (deletecnt == 1) {
		return "redirect:notice.htm";
	}else {
		return "redirect:noticeDetail.htm?seq=" + seq + "&error";
	}
		
	}
	
	//수정
	@PostMapping(value = "noticeEdit.htm")
	public String noticeEditProc(NoticeVO notice) throws ClassNotFoundException, SQLException  {
		
		int updatecnt = this.dao.update(notice);
		if (updatecnt == 1) {
			return "redirect:noticeDetail.htm?seq=" + notice.getSeq();
		}else {
			return "redirect:notice.htm";
		}
	}
	
	// 글 수정   ?seq=~
	@GetMapping("/noticeEdit.htm")
	public String noticeEdit(@RequestParam("seq") String seq, Model model) throws ClassNotFoundException, SQLException {
		
		NoticeVO notice =  this.dao.getNotice(seq);
		model.addAttribute("notice", notice);
		return "noticeEdit.jsp";
	}
	
	// 글 작성
	@PostMapping(value = "noticeReg.htm")
	public String noticeReg(NoticeVO notice) throws ClassNotFoundException, SQLException {
	
		// 작성자 X > 로그인해야만 글 작성 가능
		notice.setWriter("msms");
		
		int insertcnt = this.dao.insert(notice);
		if (insertcnt ==1) {
			// response.sendRedirect()  == redirect:
			return "redirect:notice.htm";
		}else {
			return "noticeReg.jsp?error";
		}
	}
	
	@GetMapping(value = "noticeReg.htm")
	public String noticeReg() throws ClassNotFoundException, SQLException {
		
		return "noticeReg.jsp";
	}
	
	//상세보기
	@GetMapping(value = "noticeDetail.htm")
	public String noticeDetail(@RequestParam("seq") String seq, Model model) throws ClassNotFoundException, SQLException {
		
		NoticeVO notice=this.dao.getNotice(seq);
		model.addAttribute("notice",notice);
		
		return "noticeDetail.jsp";
	}

	@GetMapping(value = "notice.htm")
	public String notice(@RequestParam(value = "page", defaultValue = "1") int page
			, @RequestParam(value = "field", defaultValue = "title") String field
			, @RequestParam(value = "query", defaultValue = "") String query
			, Model model) throws ClassNotFoundException, SQLException {
		
		List<NoticeVO> list = this.dao.getNotices(page, field, query);


		model.addAttribute("list",list);
		return "notice.jsp";
	}
	/*
	@GetMapping(value = "notice.htm")
	public ModelAndView notice(Locale locale, Model model, HttpServletRequest request) throws ClassNotFoundException, SQLException {

		ModelAndView mav = new ModelAndView();

		// ?page=~&field=~&query=~
		String ppage = request.getParameter("page");
		String pfield = request.getParameter("field");
		String pquery = request.getParameter("query");

		int page = 1;
		String field = "title";
		String query = "";

		if( ppage != null && !ppage.equals("")) {page = Integer.parseInt(ppage);}
		if( pfield != null && !pfield.equals("")) {field = pfield;}
		if( pquery != null && !pquery.equals("")) {query = pquery;}

		List<NoticeVO> list = this.dao.getNotices(page, field, query);


		mav.addObject("list",list);
		mav.addObject("message","Hello");
		mav.setViewName("notice.jsp");

		return mav;

	}
	*/
}
