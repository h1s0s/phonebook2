package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("PhonebookController");//작동확인용
		
		String act = request.getParameter("action");//파라미터에서 액션을 뽑아냄
		
		if("list".equals(act)) {
			System.out.println("action=list");
			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> personList = phoneDao.getPersonList();
			
			//System.out.println(personList);
			//html과 list를 섞어서 표현해야한다.
			//servlet으로는 표현이 복잡함. 그래서 jsp를 이용할거임
			
			request.setAttribute("pList", personList);//이름(키값), 넣을것
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");//옮겨갈 경로
			rd.forward(request, response);//이 2개를 넘긴다
		} else if ("writeForm".equals(act)) {
			System.out.println("action=writeForm");
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");//옮겨갈 경로
			rd.forward(request, response);//이 2개를 넘긴다
		} else if("insert".equals(act)) {
			System.out.println("미구현");
		} else {
			System.out.println("파라미터 값 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}