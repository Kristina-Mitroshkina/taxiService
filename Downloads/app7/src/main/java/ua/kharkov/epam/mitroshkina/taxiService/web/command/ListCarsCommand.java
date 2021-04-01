package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.epam.mitroshkina.taxiService.Path;
import ua.kharkov.epam.mitroshkina.taxiService.db.CarDao;
import ua.kharkov.epam.mitroshkina.taxiService.db.bean.CarBean;

public class ListCarsCommand extends Command {
    private static final long serialVersionUID = -4603393497643095981L;

    private static final Logger log = Logger.getLogger(ListCarsCommand.class);


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.debug("Commands starts");

        List<CarBean> carBeanList = new CarDao().getCarBeans();
        log.trace("Found in DB: carBeanList --> " + carBeanList);

        // put car beans list to request
        request.setAttribute("carBeanList", carBeanList);
        log.trace("Set the request attribute: carBeanList --> " + carBeanList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_CARS;
    }

}
