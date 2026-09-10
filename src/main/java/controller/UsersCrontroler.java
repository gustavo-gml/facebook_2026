package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/"})
public class UsersCrontroler extends HttpServlet{
	
	@Override
	protected void doGet() throws ServletException, IoException{
		System.out.print("Rodou");)
	}
	
}