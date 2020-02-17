package by.gsu.epamlab.controllers.actions;

import by.gsu.epamlab.model.utils.FileUpDownLoadUtils;
import by.gsu.epamlab.model.utils.Loggers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static by.gsu.epamlab.model.utils.Constants.DELIMITER_TAB;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;
import static java.lang.System.out;

@WebServlet(URL_DOWNLOAD)
public class DownloadController extends HttpServlet {
    private static final Logger LOGGER = Loggers.init(DownloadController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = (Properties) req.getServletContext().getAttribute(PROPERTIES_NAME);
        String path = properties.getProperty(PROPERTIES_USERS_DATA);
        String filename = req.getParameter(PAR_FILE);
        resp.setContentType(CONTENT_TYPE);
        resp.setHeader(CONTENT_DISPOSITION,CONTENT_ATTACHMENT + filename + CONTENT_ATTACHMENT_END);
        FileUpDownLoadUtils.doDownLoad(path + filename);
        LOGGER.log( Level.INFO, URL_DOWNLOAD + DELIMITER_TAB + filename);
        out.close();
    }
}
