package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.bin.Task;
import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.fabrics.SectionFactory;
import by.gsu.epamlab.model.fabrics.TaskDaoFabric;
import by.gsu.epamlab.model.interfaces.TaskDAO;
import by.gsu.epamlab.model.utils.Loggers;
import by.gsu.epamlab.model.utils.TimeUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.*;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebServlet(URL_MAIN)
public class MainController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(MainController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            java.sql.Date today = new java.sql.Date(new Date().getTime());
            java.sql.Date tomorrow = TimeUtils.datePlusDays(today, TimeUtils.ONE_DAY_MIL);
            Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
            HttpSession session = req.getSession();
            String listType = req.getParameter(PAR_LIST_TYPE);
            User user = (User) session.getAttribute(PAR_USER);
            TaskDAO taskDao = TaskDaoFabric.getDaoFromFabric(properties);
            List<Task> tasks = taskDao.getTasks(user);
            List<Task> sortedList = SectionFactory.getTasks(listType, tasks);
            req.setAttribute(PAR_TODAY, TimeUtils.formatDate(today));
            req.setAttribute(PAR_TOMORROW, TimeUtils.formatDate(tomorrow));
            session.setAttribute(PAR_TASKS, sortedList);
            LOGGER.log( Level.INFO, MSG_USER_TASKS_LOADED + today + tasks + listType + sortedList);
            req.getRequestDispatcher(URL_VIEW + JSP_MAIN).forward(req, resp);
        }catch (DaoException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERROR_SERVER);
        }
    }
}
