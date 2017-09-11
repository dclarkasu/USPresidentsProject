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

    public void init() throws ServletException { //RUNS AUTOMATICALLY
        presidentDAO = new PresidentFileDAO(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            if ((isNotNullOrEmpty(request.getParameter("selection")))){  //CHECK IF NOT NULL & BUTTON SELECTION
                String radioButton = request.getParameter("search");
                String userInput = request.getParameter("selection");
                
                switch(radioButton) {   //CHECK WHICH BUTTON USER SELECTED
                case "party":   //USER SELECTED PARTY
                    presidentDAO.setCurrent(presidentDAO.getFilterList(userInput));
                    if (presidentDAO.getCurrent().size() == 0) {
                        presidentDAO.setCurrent(presidentDAO.getFullList());
                    }
                    i = 1;
                    break;
                case "term":   //USER SELECTED TERM BUTTON
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
            else if ((isNotNullOrEmpty(request.getParameter("full")))) {  //RESET FULL LIST HERE WHEN USER SELECTS BUTTON
                presidentDAO.setCurrent(presidentDAO.getFullList());
                i = 1;
            }
            else if (isNotNullOrEmpty(request.getParameter("next"))) {  //USER SELECTED NEXT BUTTON
                if (i == presidentDAO.getCurrent().size()) {
                    i = 1;
                } 
                else {
                    ++i;
                }
            } 
            else if (isNotNullOrEmpty(request.getParameter("previous"))) {  //USER SELECTED PREVIOUS BUTTON
                if (i == 1) {
                    i = presidentDAO.getCurrent().size();
                } else {
                    --i;
                }
            }
        }
        catch (Exception e) { //IF USER DID NOT PUT IN AN INTEGER, IT WILL SHOW GEORGE WASHINGTON
            i = 1;
        }

        request.setAttribute("currentPresident", presidentDAO.getCurrent().get(i - 1)); // TELL JSP, THE WORD "CURRENT PRESIDENT, REFERS TO THE SECOND ARG
        request.getRequestDispatcher("/presidentsdata.jsp").forward(request, response); // SENDS INFO TO JSP

    }

    private boolean isNotNullOrEmpty(String op) {  //METHOD THAT CHECKS IF USER INPUT IS NULL
        return op != null && op.length() > 0;
    }
}