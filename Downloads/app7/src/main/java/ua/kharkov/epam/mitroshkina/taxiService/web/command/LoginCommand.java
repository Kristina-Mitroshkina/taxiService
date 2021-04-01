package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import org.apache.log4j.Logger;
import ua.kharkov.epam.mitroshkina.taxiService.Path;
import ua.kharkov.epam.mitroshkina.taxiService.db.Role;
import ua.kharkov.epam.mitroshkina.taxiService.db.UserDao;
import ua.kharkov.epam.mitroshkina.taxiService.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Login command.
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger log = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, SQLException {

		log.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from the request
		String email = request.getParameter("email");
		log.trace("Request parameter: email --> " + email);

		String password = request.getParameter("password");

		// error handler
		String errorMessage = null;
		//String forward = Path.PAGE__NEW_CLIENT;
		String forward = Path.PAGE__ERROR_PAGE;

		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}

		User user = new UserDao().findUserByEmail(email);
		log.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		/*if (user == null || !password.equals(user.getPassword())) {
			return forward;*/
		} else {
			Role userRole = Role.getRole(user);
			log.trace("userRole --> " + userRole);

			if (userRole == Role.ADMIN)
				forward = Path.COMMAND__LIST_CLIENTS;

			if (userRole == Role.CLIENT)
				forward = Path.COMMAND__NEW_CLIENT_ORDER;

			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);

			session.setAttribute("userRole", userRole);
			log.trace("Set the session attribute: userRole --> " + userRole);

			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
		}

		log.debug("Command finished");
		return forward;
	}
}
