package by.gsu.epamlab.model.utils;

public class ConstantsJSP {
    //logs
    public final static String LOG_FILE = "WEB_PROJECT_LOGS.log";
    public final static String LOG_PATH = "logs/";
    //properties
    public final static String PROPERTIES_FILE = "web.properties";
    public final static String PROPERTIES_NAME = "properties";
    public final static String PROPERTIES_RES_USERS = "resource.with.users";
    public final static String PROPERTIES_RES_TASKS = "resource.with.tasks";
    public final static String PROPERTIES_USERS_DATA = "users.data.path";
    //path
    public final static String URL_PROJECT_WEB = "/web/";
    public final static String URL_VIEW = "/WEB-INF/view/";
    public final static String URL_RES = "/WEB-INF/res/";
    //urlPatterns
    public final static String URL_ACTIONS = "/actions/*";
    public final static String URL_ACTION_ADD = "/actionAdd";
    public final static String URL_ACTION_CHANGE = "/actionChange";
    public final static String URL_ACTION_CHANGE0 = "/actionChange0";
    public final static String URL_ACTION_DONE = "/actionDone";
    public final static String URL_ACTION_DELETE = "/actionDelete";
    public final static String URL_LOGIN = "/login";
    public final static String URL_MAIN = "/main";
    public final static String URL_LOGOUT = "/logout";
    public final static String URL_REGISTRATION = "/registration";
    public final static String URL_DOWNLOAD = "/download";
    //types
    public final static String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
    public final static String CONTENT_DISPOSITION = "Content-Disposition";
    public final static String CONTENT_ATTACHMENT = "attachment; filename=\"";
    public final static String CONTENT_ATTACHMENT_END = "\"";
    //jsp
    public final static String JSP_INDEX = "index.jsp";
    public final static String JSP_MAIN = "main.jsp";
    public final static String JSP_INDEX_LOGIN = "/index.jsp?login=yes";
    public final static String JSP_INDEX_REGIN = "/index.jsp?register=yes";
    public final static String JSP_LIST_TYPE = "?list_type=";
    public final static String JSP_ACTION = "/action.jsp";
    public final static String JSP_ACTION_CHANGE = "?change=yes";
    //name of parameters/attributes on jsp page
    public final static String PAR_LIST_TYPE = "list_type";
    public final static String TYPE_TODAY = "today";
    public final static String REFERER_NAME = "referer";
    public final static String PAR_ERROR = "error";
    public final static String PAR_USERNAME_L = "usernameLogin";
    public final static String PAR_PASSWORD_L = "passwordLogin";
    public final static String PAR_USERNAME_R = "usernameRegistration";
    public final static String PAR_PASSWORD_R = "passwordRegistration";
    public final static String PAR_EMAIL_R = "email";
    public final static String PAR_USER = "user";
    public final static String PAR_TASKS_ALL = "alltasks";
    public final static String PAR_TASKS = "tasks";
    public final static String PAR_TODAY = TYPE_TODAY;
    public final static String PAR_TOMORROW = "tomorrow";
    public final static String PAR_PRESSED = "pressed_button";
    public final static String PAR_CHANGED = "CHANGE0";
    public final static String PAR_TASKS_TO_CHANGE = "taskstochange";
    public final static String PAR_IDS = "idTask";
    public final static String PAR_FILE = "file";
    public final static String PAR_TASK_NAME = "taskName";
    public final static String PAR_TASK_DATE = "taskDate";
    public final static String PAR_TASK_STATUS = "taskStatus";
}
