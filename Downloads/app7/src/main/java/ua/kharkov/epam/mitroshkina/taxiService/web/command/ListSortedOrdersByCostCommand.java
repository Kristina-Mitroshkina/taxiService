package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import org.apache.log4j.Logger;
import ua.kharkov.epam.mitroshkina.taxiService.Path;
import ua.kharkov.epam.mitroshkina.taxiService.db.bean.ConfirmedOrderBean;
import ua.kharkov.epam.mitroshkina.taxiService.db.ClientOrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListSortedOrdersByCostCommand extends Command{
    private static final long serialVersionUID = 6112180983295217919L;

    private static final Logger log = Logger.getLogger(ListSortedOrdersByCostCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, SQLException {
        log.debug("Commands starts");

        List<ConfirmedOrderBean> confirmedOrderBeanList = new ClientOrderDao().sortConfirmedOrderBeansByCost();
        log.trace("Found in DB: confirmedOrderBeanList --> " + confirmedOrderBeanList);

        // put confirmed order beans list to request
        request.setAttribute("confirmedOrderBeanList", confirmedOrderBeanList);
        log.trace("Set the request attribute: confirmedOrderBeanList --> " + confirmedOrderBeanList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_ALL_ORDERS;
    }
}
