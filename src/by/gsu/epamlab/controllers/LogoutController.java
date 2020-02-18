package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.utils.Loggers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.*;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebServlet(URL_LOGOUT)
public class LogoutController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(LogoutController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute(PAR_USER);
        session.invalidate();
        LOGGER.log( Level.INFO, MSG_LOGOUT + user.getName());
        resp.sendRedirect(URL_PROJECT_WEB + JSP_INDEX);
    }
}
