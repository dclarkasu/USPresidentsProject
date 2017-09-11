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
	private PresidentFileDAO presidentDAO;
	private int i = 1;

	public void init() throws ServletException {
		presidentDAO = new PresidentFileDAO(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
            if ((isNotNullOrEmpty(request.getParameter("selection")))){
                String radioButton = request.getParameter("search");
                String userInput = request.getParameter("selection");
                switch(radioButton) {
                case "party":
                    presidentDAO.setCurrent(presidentDAO.getFilterList(userInput));
                    if (presidentDAO.getCurrent().size() == 0) {
                        presidentDAO.setCurrent(presidentDAO.getFullList());
                    }
                    i = 1;
                    break;
                case "term":
                    presidentDAO.setCurrent(presidentDAO.getFullList());
                    i = Integer.parseInt(userInput);
                    if (i <= 0 || i > (presidentDAO.getFullList().size())) {
                        i = 1;
                    }
                    break;
                default:
                    i = 1;
                }
            } 
            else if ((isNotNullOrEmpty(request.getParameter("full")))) {
                presidentDAO.setCurrent(presidentDAO.getFullList());
                i = 1;
            }
            else if (isNotNullOrEmpty(request.getParameter("next"))) {
                if (i == presidentDAO.getCurrent().size()) {
                    i = 1;
                } 
                else {
                    ++i;
                }
            } 
            else if (isNotNullOrEmpty(request.getParameter("previous"))) {
                if (i == 1) {
                    i = presidentDAO.getCurrent().size();
                } else {
                    --i;
                }
            }
        }
		catch (Exception e) {
			i = 1;
		}

		request.setAttribute("currentPresident", presidentDAO.getCurrent().get(i - 1));
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
