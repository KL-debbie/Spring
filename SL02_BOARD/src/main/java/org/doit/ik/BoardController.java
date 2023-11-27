package org.doit.ik;

import org.doit.ik.domain.BoardVO;
import org.doit.ik.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private BoardService boardService;

	// http://localhost/board/list 게시글 목록 보기
	@GetMapping("/list")
	//	@RequestMapping(value = "/list")
	public void list(Model model ) {
		log.info("> /board/list get ");
		model.addAttribute( "list", this.boardService.getList() );
	}

	// http://localhost/board/register 게시글 + GET
	@GetMapping("/register")
	public void register(Model model ) {
		log.info("> /board/register get ");

	}

	// http://localhost/board/register 게시글 + GET
	@PostMapping("/register")
	public String register(BoardVO board , RedirectAttributes rttr) {
		log.info("> /board/register post ");
		this.boardService.register(board);
		// 스프링 리다이렉트 코딩방법
		rttr.addFlashAttribute("result", board.getBno()); // 1회성
		return "redirect:/board/list";
	}

	// 글 보기 / 수정
	// http://localhost/board/get?bno= + GET
	// http://localhost/board/modify?bno= + GET
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bon, Model model ) {
		log.info("> /board/read get or modify ");
		model.addAttribute("board", this.boardService.get(bon));
	}
	
	// http://localhost/board/modify?bno= + POST
	@PostMapping( "/modify")
	public String modify(BoardVO board, RedirectAttributes rttr ) {
		log.info("> /board/modify post");
		if ( this.boardService.modify(board) ) {
		rttr.addFlashAttribute("result", "success");
			
		}
		return "redirect:/board/list";
	}

	
	@GetMapping( "/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr ) {
		log.info("> /board/modify post");
		if ( this.boardService.remove(bno) ) {
		rttr.addFlashAttribute("result", "success");
			
		}
		return "redirect:/board/list";
	}
}