package by.gsu.epamlab.controllers.actions;

import by.gsu.epamlab.model.bin.Task;
import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.fabrics.ActionOnTaskFactory;
import by.gsu.epamlab.model.fabrics.TaskDaoFabric;
import by.gsu.epamlab.model.interfaces.TaskDAO;
import by.gsu.epamlab.model.utils.Loggers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.*;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebServlet({URL_ACTION_DONE, URL_ACTION_DELETE})
public class ActionsController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(ActionsController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
            TaskDAO taskDao = TaskDaoFabric.getDaoFromFabric(properties);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(PAR_USER);
            List<Task> tasks = taskDao.getTasks(user);
            int[] checkedTasks = Arrays.stream(req.getParameterValues(PAR_IDS))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            List<Task> tasksToChange = ActionOnTaskFactory.getTasksFromListByIds(checkedTasks, tasks);
            session.setAttribute(PAR_TASKS_TO_CHANGE, tasksToChange);
            String actionName = req.getParameter(PAR_PRESSED).toUpperCase();
            ActionOnTaskFactory.doActionWithTasks(actionName, tasksToChange, TaskDaoFabric.getDaoFromFabric(properties));
            LOGGER.log( Level.INFO, actionName + DELIMITER_TAB + tasksToChange.toString());
            resp.sendRedirect(req.getContextPath() + URL_MAIN + JSP_LIST_TYPE + TYPE_TODAY);
        }catch (DaoException | Exception e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERROR_SERVER);
        }
    }
}
