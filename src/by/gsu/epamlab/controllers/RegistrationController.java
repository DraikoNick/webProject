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
import javax.servlet.http.HttpSession;
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
        try{
            String username = req.getParameter(PAR_USERNAME_R);
            String password = req.getParameter(PAR_PASSWORD_R);
            String email = req.getParameter(PAR_EMAIL_R);
            Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
            UserDAO userDao = UserDaoFabric.getDaoFromFabric(properties);
            Optional<User> optionalUser = Optional.ofNullable(userDao.getUser(username, password));
            synchronized (this){
                if(optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    String messageToLog = ERROR_USER + user.getName() + ERROR_REGISTERED;
                    LOGGER.log(Level.WARNING, messageToLog);
                    req.setAttribute(PAR_ERROR, ERROR_USER + user.getName() + ERROR_REGISTERED);
                    req.getRequestDispatcher(JSP_INDEX_REGIN).forward(req, resp);
                }else{
                    User user = new User(0, username, password, email);
                    userDao.insertUser(user);
                    LOGGER.log( Level.INFO, MSG_USER_REGISTER_SUCCESS + user);
                    HttpSession session = req.getSession();
                    session.setAttribute(PAR_USER, user);
                    resp.sendRedirect(req.getContextPath() + JSP_INDEX_LOGIN);
                }
            }
        }catch (DaoException e){
            LOGGER.log( Level.SEVERE, e.toString(), e);
            throw new ServletException(ERROR_SERVER);
        }
    }
}
