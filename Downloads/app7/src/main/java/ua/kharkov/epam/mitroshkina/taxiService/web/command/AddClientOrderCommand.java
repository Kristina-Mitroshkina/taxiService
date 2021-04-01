package ua.kharkov.epam.mitroshkina.taxiService.web.command;

import org.apache.log4j.Logger;
import ua.kharkov.epam.mitroshkina.taxiService.Path;
import ua.kharkov.epam.mitroshkina.taxiService.db.ClientOrderDao;
import ua.kharkov.epam.mitroshkina.taxiService.db.bean.ConfirmedOrderBean;
import ua.kharkov.epam.mitroshkina.taxiService.db.entity.ClientOrder;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddClientOrderCommand extends Command {
    private static final long serialVersionUID = 5205744529362706039L;

    private static final Logger log = Logger.getLogger(AddClientOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, SQLException {

        log.debug("Command starts");

        ClientOrder order = new ClientOrder();
        boolean addClientOrder = false;

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        if (user_id != 0) {
            order.setUser_id(user_id);
            addClientOrder = true;
        }
        int departure_address = Integer.parseInt(request.getParameter("departure_address"));
        if (departure_address != 0) {
            order.setDeparture_address(departure_address);
            addClientOrder = true;
        }
        int destination_address = Integer.parseInt(request.getParameter("destination_address"));
        if (destination_address != 0) {
            order.setDestination_address(destination_address);
            addClientOrder = true;
        }
        int passengers_number = Integer.parseInt(request.getParameter("passengers_number"));
        if (passengers_number != 0) {
            order.setPassengers_number(passengers_number);
            addClientOrder = true;
        }
        int car_category = Integer.parseInt(request.getParameter("car_category"));
        if (car_category != 0) {
            order.setCar_category(car_category);
            addClientOrder = true;
        }

        int clientOrder_id = 0;
        int car_id = 0;
        int confirmedOrder_id = 0;

        if (addClientOrder == true) {
            clientOrder_id = new ClientOrderDao().addNewClientOrder(new ClientOrder(order.getUser_id(), order.getDeparture_address(),
                    order.getDestination_address(), order.getPassengers_number(), order.getCar_category()));
            car_id = new ClientOrderDao().findSuitableCar(clientOrder_id);
        }
        if (car_id == 0) {
            car_id = new ClientOrderDao().findSuitableCarAnotherCategory(clientOrder_id);
            if (car_id == 0) {
                return Path.PAGE__NO_SUITABLE_CAR;
            }
        }
        confirmedOrder_id = new ClientOrderDao().addNewConfirmedOrder(clientOrder_id, car_id);
        ConfirmedOrderBean confirmedOrderBean = new ClientOrderDao().showConfirmedOrderBean(confirmedOrder_id);

        log.trace("Found in DB: confirmedOrderBean --> " + confirmedOrderBean);

        // put confirmed order beans list to request
        request.setAttribute("confirmedOrderBean", confirmedOrderBean);
        log.trace("Set the request attribute: confirmedOrderBean --> " + confirmedOrderBean);


        log.debug("Command finished");
        return Path.PAGE__CONFIRMED_ORDER;

    }

}
