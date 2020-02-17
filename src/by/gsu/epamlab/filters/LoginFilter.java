package by.gsu.epamlab.filters;

import by.gsu.epamlab.model.bin.User;
import by.gsu.epamlab.model.utils.Loggers;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

@WebFilter({URL_ACTIONS, URL_MAIN, URL_DOWNLOAD})
public class LoginFilter implements Filter {
    private static final Logger LOGGER = Loggers.init(TypedUrlFilter.class.getName());

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(PAR_USER);
        LOGGER.log(Level.INFO, PAR_USER);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }
        chain.doFilter(request, response);
        return;
    }
}

