package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.bin.Task;
import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.fabrics.SectionFactory;
import by.gsu.epamlab.model.fabrics.TaskDaoFabric;
import by.gsu.epamlab.model.interfaces.TaskDAO;
import by.gsu.epamlab.model.utils.Loggers;
import by.gsu.epamlab.model.utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(PAR_USER);
            Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
            TaskDAO taskDao = TaskDaoFabric.getDaoFromFabric(properties);
            SectionFactory.SectionKind section = SectionFactory
                    .SectionKind.valueOf(req.getParameter(PAR_LIST_TYPE).toUpperCase());

            req.setAttribute(PAR_SECTIONS_MAP, Utils.getSectionsMap());
            req.setAttribute(PAR_BUTTONS_TASK, SectionFactory.getButtonsForEachTask(section));
            req.setAttribute(PAR_BUTTONS_SECTION, SectionFactory.getButtonsForSection(section));
            List<Task> sortedList = SectionFactory.getTasks(section, user, taskDao);
            session.setAttribute(PAR_TASKS, sortedList);
            LOGGER.log( Level.INFO, MSG_SECTION + section  + DELIMITER_LINE
                                        + MSG_TASKS + sortedList);
            req.getRequestDispatcher(URL_VIEW + JSP_MAIN).forward(req, resp);
        }catch (DaoException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERR_SERVER);
        }
    }
}
