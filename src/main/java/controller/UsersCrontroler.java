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
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;

@WebServlet(urlPatterns = {"/users", "/users/save", "/users/update", "users/delete"})
public class UsersCrontroler extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//TODO: Log: Apagar no final
		String action = req.getRequestURI();
		System.out.println("URL: x" + action);
		
		switch(action) {
		case "/facebook/users":{
			loadUsers(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("users.jsp"); //carrega na index
			rd.forward(req, resp);
			break;
			
		}
		
		case "/facebook/users/save":{
			saveUser(req);
			
			resp.sendRedirect("/facebook/users");
			break;
		}
		case "/facebook/users/update":{
			loadUser(req);
			
			RequestDispatcher rd = req.getRequestDispatcher("/users_form.jsp");
			rd.forward(req, resp);
			break;
		}
			
		case "/facebook/users/delete":{
			deleteUser(req);
			resp.sendRedirect("/facebook/users");
			break;
			
		}
		default:
			String message = "URL não suportada: " + action +".";
			throw new IllegalArgumentException(message);
		}
	}
	
	private void loadUser(HttpServletRequest req) {
		String userIdStr = req.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);
		
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		User userToBeUpdated = null;
		try {
			userToBeUpdated = dao.findById(userId);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("user", userToBeUpdated);
		
	}
	
	private void deleteUser(HttpServletRequest req) {
		String userIdStr = req.getParameter("userId");
		int userId = Integer.parseInt(userIdStr);
		
		User userToBeDeleted = new User(userId);
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			dao.delete(userToBeDeleted);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveUser(HttpServletRequest req) {
		String userIdStr = req.getParameter("user_id");
		User user = null;
		boolean isUpdate = false;
		if ((userIdStr == null ) || userIdStr.isBlank()) {
			user = new User();
		}
		else { 
			user = new User(Integer.parseInt(userIdStr));
			isUpdate= true;
		}
		
		
		//Recuperar os dados da requisição
		String userName = req.getParameter("user_name");
		String userGender = req.getParameter("user_gender");
		String userEmail = req.getParameter("user_email");
		
		//criar objeto do tipo usuario		
		user.setEmail(userEmail);
		user.setName(userName);
		user.setGender(userGender);;
		
		//criar um dao do usuario
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		try {
			if(isUpdate)
				dao.update(user);
			else 
				dao.save(user);
			
		}catch(ModelException e){
			e.printStackTrace();
		}
	}
	private void loadUsers(HttpServletRequest req) {
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		List<model.User> users = List.of();
		
		try {
			users = dao.listAll();
		}catch(ModelException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("users_list", users); 
		
	}
	
}