package com.skilldistillery.presidents.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PresidentServlet
 */
@WebServlet("/PresidentServlet")
public class PresidentServlet extends HttpServlet {
	// private static final long serialVersionUID = 1L;
	private PresidentDAO presidentDAO;
	private int i = 1;

	public void init() throws ServletException {
		presidentDAO = new PresidentFileDAO(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		int selection = Integer.parseInt(request.getParameter("selection"));
		
		if ((isNotNullOrEmpty(request.getParameter("selection")))  && (Integer.parseInt(request.getParameter("selection")) >= 0) && (Integer.parseInt(request.getParameter("selection")) <= 45) ) {
			i = Integer.parseInt(request.getParameter("selection"));
		}
		else if (isNotNullOrEmpty(request.getParameter("next"))) {
			if (i == presidentDAO.getFullList().size()) {
				i = 1;
			} else {
				++i;
			}
		} else if (isNotNullOrEmpty(request.getParameter("previous"))) {
			if (i == 1) {
				i = presidentDAO.getFullList().size();
			} else {
				--i;
			}
		}
		
		request.setAttribute("currentPresident", presidentDAO.getFullList().get(i-1));
		request.getRequestDispatcher("/presidentsdata.jsp").forward(request, response); // jsp
		
	}

	private boolean isNotNullOrEmpty(String op) {
		return op != null && op.length() > 0;
	}

	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// doGet(request, response);
	//
	// }

}
