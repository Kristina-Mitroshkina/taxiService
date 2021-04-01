package ua.kharkov.epam.mitroshkina.taxiService.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.epam.mitroshkina.taxiService.web.command.Command;
import ua.kharkov.epam.mitroshkina.taxiService.web.command.CommandContainer;

/**
 * Main servlet controller.
 */
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger log = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	/**
	 * Main method of this controller.
	 */
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, SQLException {
		
		log.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		log.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		log.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = command.execute(request, response);
		log.trace("Forward address --> " + forward);

		log.debug("Controller finished, now go to forward address --> " + forward);

		// if the forward address is not null go to the address
		if (forward != null) {
			RequestDispatcher disp = request.getRequestDispatcher(forward);
			disp.forward(request, response);
		}
	}

}