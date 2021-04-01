package ua.kharkov.epam.mitroshkina.taxiService;

public final class Path {

	// pages
	public static final String PAGE__LOGIN = "/login.jsp";
	public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE__LIST_CLIENTS = "/WEB-INF/jsp/admin/list_clients.jsp";
	public static final String PAGE__NEW_CLIENT_ORDER = "/WEB-INF/jsp/client/new_clientOrder.jsp";
	public static final String PAGE__CONFIRMED_ORDER = "/WEB-INF/jsp/client/confirmedOrder.jsp";
	public static final String PAGE__LIST_CARS = "/WEB-INF/jsp/admin/list_cars.jsp";
	public static final String PAGE__NEW_CLIENT = "/WEB-INF/jsp/client/createAccount.jsp";
	public static final String PAGE__FINISH_ORDER = "/WEB-INF/jsp/client/finishOrder.jsp";
	public static final String PAGE__LIST_ALL_ORDERS = "/WEB-INF/jsp/admin/list_confirmedOrders.jsp";
	public static final String PAGE__NO_SUITABLE_CAR = "/WEB-INF/jsp/client/noSuitableCars.jsp";

	// commands
	public static final String COMMAND__NEW_CLIENT_ORDER = "/controller?command=viewAddCO";
	public static final String COMMAND__LIST_CLIENTS = "/controller?command=listClients";
}