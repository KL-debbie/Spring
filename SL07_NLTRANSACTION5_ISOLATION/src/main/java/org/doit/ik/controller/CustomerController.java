package org.doit.ik.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.doit.ik.domain.NoticeVO;
import org.doit.ik.persistence.NoticeDao;
import org.doit.ik.service.MemberShipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/customer/*")
public class CustomerController {

	@Autowired
	private NoticeDao dao;
	
	@Autowired
	private MemberShipService service;

	//다운로드
	// ?dir=customer/upload&file=${ notice.filesrc  }
	@RequestMapping( "download.htm")
	public void download(
			@RequestParam("dir")   String d
			, @RequestParam("file") String fname
			, HttpServletResponse response
			, HttpServletRequest request
			) throws Exception{ 

		response.setHeader("Content-Disposition","attachment;filename="+ new String(fname.getBytes(), "ISO8859_1"));      

		String fullPath = request.getServletContext().getRealPath(   d + "/" + fname);

		FileInputStream fin = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream();  
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size); 
		}
		fin.close();
		sout.close();

	}

	//삭제
	@GetMapping(value = "noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq
			, @RequestParam("filesrc") String delFilesrc
			, HttpServletRequest request
			) throws ClassNotFoundException, SQLException {

		// 1 
		String upPath = request.getServletContext().getRealPath("/customer/upload");
		File delFile = new File(upPath,delFilesrc);
		if (delFile.exists()) delFile.delete();

		// 2
		int deletecnt=this.dao.delete(seq);

		if (deletecnt == 1) {
			return "redirect:notice.htm";
		}else {
			return "redirect:noticeDetail.htm?seq=" + seq + "&error";
		}

	}

	//수정
	@PostMapping(value = "noticeEdit.htm")
	public String noticeEditProc(NoticeVO notice  //새로 첨부된 파일이 있다면 커맨드 객체 저장
			, @RequestParam("o_filesrc") String oFilesrc
			, HttpServletRequest request
			) throws ClassNotFoundException, SQLException, IllegalStateException, IOException  {

		// 1. 첨부된 파일 유무 확인 후 서버에 파일 저장
		CommonsMultipartFile multipartFile=notice.getFile();

		// 서버 배포했을 경우 실제 저장 경로
		String upPath = null;
		if (!multipartFile.isEmpty() ) {//새 첨부파일 존재
			upPath = request.getServletContext().getRealPath("/customer/upload");

			System.out.println("> realPath : " + upPath);
			
			//원래 첨부파일 삭제
			File delFile = new File(upPath, oFilesrc);
			if (delFile.exists()) delFile.delete();

			String originFileName = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(upPath, originFileName);
			
			File dest = new File(upPath, filesystemName);
			multipartFile.transferTo(dest); // 실제 파일 저장

			notice.setFilesrc(filesystemName);
		}else {
			notice.setFilesrc(oFilesrc);
		}

		// 2
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

	private String getFileNameCheck(String uploadRealPath, String originalFilename) {
		int index = 1;      
		while( true ) {         
			File f = new File(uploadRealPath, originalFilename);         
			if( !f.exists() ) return originalFilename;         
			// upload 폴더에 originalFilename 파일이 존재한다는 의미         a.txt (4자리)
			String fileName = originalFilename.substring(0, originalFilename.length() - 4 );  //   a
			String ext =  originalFilename.substring(originalFilename.length() - 4 );  // .txt
			// asdfasf-3.txt
			originalFilename = fileName+"-"+(index)+ext;

			index++;
		} // while 
	}

	// 글 작성
	@PostMapping(value = "noticeReg.htm")
	public String noticeReg(NoticeVO notice, HttpServletRequest request) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {

		// 1. 첨부된 파일 유무 확인 후 서버에 파일 저장
		CommonsMultipartFile multipartFile=notice.getFile();

		// 서버 배포했을 경우 실제 저장 경로
		String upPath = null;
		if (!multipartFile.isEmpty() ) {//첨부파일 존재
			upPath = request.getServletContext().getRealPath("/customer/upload");

			//			File saveDir = new File(upPath);
			//			if (saveDir.exists()) saveDir.mkdirs();

			System.out.println("> realPath : " + upPath);

			String originFileName = multipartFile.getOriginalFilename();
			String filesystemName = getFileNameCheck(upPath, originFileName);

			File dest = new File(upPath, filesystemName);
			multipartFile.transferTo(dest); // 실제 파일 저장

			notice.setFilesrc(filesystemName);
		}

		// 작성자 X > 로그인해야만 글 작성 가능 세션사용
		notice.setWriter("msms");

		// int insertcnt = this.dao.insert(notice);
//		this.dao.insertAndPointUpOfMember(notice, "msms");
		this.service.insertAndPointUpOfMember(notice, "msms");
		
		int insertcnt = 1;
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
		
		this.dao.hitup(seq); //조회수 1 증가
		NoticeVO notice=this.dao.getNotice(seq);
		model.addAttribute("notice",notice);

		return "noticeDetail.jsp";
	}

	// 목록보기
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
