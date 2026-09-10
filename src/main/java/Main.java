import model.dao.UserDAO;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.MySQLUserDAO;

public class Main {
	public static void main(String[] args) throws ModelException {
		//Usando Dao
		//Instanciando o DAO
		UserDAO userDAO = DAOFactory.createDAO(UserDAO.class);
		
		
		
		
		
		//Insert
		User bill = new User();
		bill.setName("Bill Hates");
		bill.setGender("M");
		bill.setName("bill@microsoft.com");
		bill.setPassword("1234");
		//userDAO.save(bill);
		
		//Alteração
		bill.setID(6);
		bill.setName("Bill Gates");
		
		//userDAO.update(bill);
		
		//Exclusão
		userDAO.delete(bill);
		//Consulta
		for(User user : userDAO.listAll()) {
			System.out.printf("Nome %s, Sexo %s, Email %s \n" 
					,user.getName()
					,user.getGender()
					,user.getEmail()
			);
		}
		
		
	}
}