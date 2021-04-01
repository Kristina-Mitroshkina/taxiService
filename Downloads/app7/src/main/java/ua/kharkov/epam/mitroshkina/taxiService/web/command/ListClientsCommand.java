package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import org.apache.log4j.Logger;
import ua.kharkov.epam.mitroshkina.taxiService.Path;
import ua.kharkov.epam.mitroshkina.taxiService.db.UserDao;
import ua.kharkov.epam.mitroshkina.taxiService.db.entity.User;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListClientsCommand extends Command {

    private static final long serialVersionUID = 7425152420595527181L;

    private static final Logger log = Logger.getLogger(ListClientsCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, SQLException {

        log.debug("Command starts");

        List<User> clients = new UserDao().findAllClients();
        log.trace("Found in DB: clientsList --> " + clients);

        // put client list to the request
        request.setAttribute("clients", clients);
        log.trace("Set the request attribute: clients --> " + clients);

        log.debug("Command finished");
        return Path.PAGE__LIST_CLIENTS;
    }
}
