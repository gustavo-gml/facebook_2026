package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;


@WebServlet(urlPatterns = {"/posts"})
public class PostController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//TODO: Log: Apagar no final
		String action = req.getRequestURI();
		System.out.println("URL: y" + action);
		
		switch(action) {
		case "/facebook/posts":{
			loadPosts(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("posts.jsp");
			rd.forward(req, resp);
			break;
		}
		default:
			String message = "URL não suportada: " + action +".";
			throw new IllegalArgumentException(message);
		}
	}
	
	private void loadPosts(HttpServletRequest req) {
		PostDAO dao = DAOFactory.createDAO(PostDAO.class);
		
		List<model.Post> posts = List.of();
		
		try {
			posts = dao.listAll();
		}catch(ModelException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("posts_list", posts); //carrega na index
		
	}
	

}