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
	//private static final long serialVersionUID = 1L;
	private PresidentDAO presidentDAO; 

	public void init() throws ServletException{
		presidentDAO = new PresidentFileDAO(getServletContext());
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("presidents", presidentDAO.getFullList());
		request.getRequestDispatcher("/presidentsdata.jsp").forward(request, response);; //jsp
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		
	}

}
