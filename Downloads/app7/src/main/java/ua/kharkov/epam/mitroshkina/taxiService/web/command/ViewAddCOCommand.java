package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.epam.mitroshkina.taxiService.Path;

public class ViewAddCOCommand extends Command{

    private static final long serialVersionUID = -269666745329784430L;

    private static final Logger log = Logger.getLogger(ViewAddCOCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");

        log.debug("Command finished");
        return Path.PAGE__NEW_CLIENT_ORDER;
    }
}
