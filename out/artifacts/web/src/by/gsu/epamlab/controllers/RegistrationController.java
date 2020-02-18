package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.fabrics.UserDaoFabric;
import by.gsu.epamlab.model.interfaces.UserDAO;
import by.gsu.epamlab.model.utils.Loggers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.*;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebServlet(URL_REGISTRATION)
public class RegistrationController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(RegistrationController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter(PAR_USERNAME_R);
        String password = req.getParameter(PAR_PASSWORD_R);
        String email = req.getParameter(PAR_EMAIL_R);
        Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
        try{
            UserDAO userDao = UserDaoFabric.getDaoFromFabric(properties);
            Optional<User> optionalUser = Optional.ofNullable(
                    userDao.insertUser(
                            new User(0, username, password, email)));
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                LOGGER.log( Level.INFO, MSG_REGISTER + user);
                req.getSession().setAttribute(PAR_USER, user);
                resp.sendRedirect(req.getContextPath() + URL_LOGOUT);
                return;
            }
            String messageToLog = ERR_USER_EXIST + username;
            LOGGER.log(Level.WARNING, messageToLog);
            req.setAttribute(PAR_ERROR, messageToLog);
            req.getRequestDispatcher(JSP_INDEX_REGIN).forward(req, resp);
        } catch (DaoException e) {
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERR_SERVER);
        }
    }
}
