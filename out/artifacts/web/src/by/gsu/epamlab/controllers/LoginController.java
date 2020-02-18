package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.db.ConnectorDB;
import by.gsu.epamlab.model.exceptions.*;
import by.gsu.epamlab.model.fabrics.UserDaoFabric;
import by.gsu.epamlab.model.interfaces.UserDAO;
import by.gsu.epamlab.model.utils.Loggers;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.*;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebServlet(
        urlPatterns = {URL_LOGIN},
        initParams = {
                @WebInitParam(name = PROPERTIES_NAME, value = PROPERTIES_FILE)
        },
        loadOnStartup = 1
)
public class LoginController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(LoginController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
        try{
            UserDAO userDao = UserDaoFabric.getDaoFromFabric(properties);
            String username = req.getParameter(PAR_USERNAME_L);
            String password = req.getParameter(PAR_PASSWORD_L);
            Optional<User> optionalUser = Optional.ofNullable(
                    userDao.getUser(username, password));
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                HttpSession session = req.getSession();
                session.setAttribute(PAR_USER, user);
                LOGGER.log( Level.INFO, MSG_LOGIN + user.getName());
                resp.sendRedirect(req.getContextPath() + URL_MAIN + JSP_LIST_TYPE + TYPE_TODAY);
                return;
            }
            String messageToLog = ERR_USER_NOT_REGISTERED + username;
            req.setAttribute(PAR_ERROR, messageToLog);
            LOGGER.log( Level.WARNING, messageToLog);
            req.getRequestDispatcher(JSP_INDEX_LOGIN).forward(req, resp);
        }catch (DaoException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERR_SERVER);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        String propertiesName = config.getInitParameter(PROPERTIES_NAME);
        InputStream input = config.getServletContext().getResourceAsStream(URL_RES + propertiesName);
        Properties properties = new Properties();
        try {
            properties.load(input);
            input.close();
            ConnectorDB.setProperties(properties);
            config.getServletContext().setAttribute(PROPERTIES_NAME, properties);
        } catch (IOException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERR_INIT_PROPERTIES);
        }
    }
}
