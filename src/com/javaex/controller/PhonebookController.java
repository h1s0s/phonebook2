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
		} else if ("write".equals(act)){
			System.out.println("action=write");
			
			//파라미터 3개 꺼내온다
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			//vo로 만든다
			PersonVo personVo = new PersonVo(name, hp, company);
			System.out.println(personVo);
			
			//dao 메모리에 올린다
			PhoneDao phoneDao = new PhoneDao();
			
			//dao.insert();
			phoneDao.personInsert(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			//=>리다이렉트는 리스폰의 메소드를 사용, 파일경로가 아닌 주소값을 넣어줌.
			
		} else if ("updateForm".equals(act)) {
			System.out.println("action=updateForm");
			
			//파라미터 꺼내온다
			int personId = Integer.parseInt(request.getParameter("personid"));
			
			//dao 메모리에 올린다
			PhoneDao phoneDao = new PhoneDao();
			
			//getPerson
			PersonVo personVo = phoneDao.getPerson(personId);
			
			//포워드
			request.setAttribute("personVo", personVo);
			System.out.println(personVo);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");//옮겨갈 경로
			rd.forward(request, response);//이 2개를 넘긴다
			
		} else if ("update".equals(act)) {
			System.out.println("action=update");
			
			//파라미터 3개 꺼내온다
			int personId = Integer.parseInt(request.getParameter("personid"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//vo로 만든다
			PersonVo personVo = new PersonVo(personId, name, hp, company);
			System.out.println(personVo);
			
			//dao 메모리에 올린다
			PhoneDao phoneDao = new PhoneDao();
			
			//dao.insert();
			phoneDao.personUpdate(personVo);
			
			//리다이렉트
			response.sendRedirect("/phonebook2/pbc?action=list");
			//=>리다이렉트는 리스폰의 메소드를 사용, 파일경로가 아닌 주소값을 넣어줌.
		} else if ("deleteForm".equals(act)) {
			System.out.println("action=deleteForm");
		} else if ("delete".equals(act)) {
			System.out.println("action=delete");
		} else {
			System.out.println("파라미터 값 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}