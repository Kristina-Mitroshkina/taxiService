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

public class AddClientCommand extends Command {
    private static final long serialVersionUID = -3693185256824323484L;

    private static final Logger log = Logger.getLogger(AddClientCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, SQLException {

        log.debug("Command starts");

        User client = new User();
        boolean addClient = false;

        String name = request.getParameter("name");
        if (name != null && !name.isEmpty()) {
            client.setName(name);
            addClient = true;
        }
        String surname = request.getParameter("surname");
        if (surname != null && !surname.isEmpty()) {
            client.setSurname(surname);
            addClient = true;
        }
        String email = request.getParameter("email");
        if (email != null && !email.isEmpty()) {
            client.setEmail(email);
            addClient = true;
        }
        String password = request.getParameter("password");
        if (password != null && !password.isEmpty()) {
            client.setPassword(password);
            addClient = true;
        }
        int phone_number = Integer.parseInt(request.getParameter("phone_number"));
        if (phone_number != 0) {
            client.setPhone_number(phone_number);
            addClient = true;
        }

        int role_id = 1;

        if (addClient == true) {
            new UserDao().addNewClient(new User(client.getName(), client.getSurname(),
                    client.getEmail(), client.getPassword(), client.getPhone_number(), role_id));
        }

        log.debug("Command finished");
        return Path.PAGE__LOGIN;
    }
}
