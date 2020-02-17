package by.gsu.epamlab.model.utils;

import static by.gsu.epamlab.model.utils.ConstantsJSP.*;

public class ConstantsDB {
    //database
    public static final String DB_NAME = "web.";
    public static final String DB_PROPERTIES = URL_RES + PROPERTIES_FILE;

    //error
    public static final String ERR_DAO = "Data Access Object Error: ";
    public static final String ERR_DAO_DB = ERR_DAO + "Cannot insert/get DAO. ";

    //tables and fields
    public static final String TAB_USERS = "users";
    public static final String TAB_USER_ID = "user_id";
    public static final String TAB_USER_NAME = "user_name";
    public static final String TAB_USER_PASS = "user_password";
    public static final String TAB_USER_MAIL = "user_email";

    public static final String TAB_TASKS = "tasks";
    public static final String TAB_TASKS_ID = "tasks_id";
    public static final String TAB_TASKS_NAME = "tasks_name";
    public static final String TAB_TASKS_DATE = "tasks_date";
    public static final String TAB_TASKS_STATUS = "status_id";
    public static final String TAB_TASKS_USER = "user_id";

    public static final String TAB_STATUS = "task_status";
    public static final String TAB_STATUS_ID = "status_id";
    public static final String TAB_STATUS_NAME = "status_name";

    //queries
    public static final String SQL_INSERT_USER =
            "INSERT INTO "+ TAB_USERS +
            " (" + TAB_USER_NAME + ", " + TAB_USER_PASS + ", " + TAB_USER_MAIL + ") " +
            "VALUES (?, ?, ?)";
    public static final String SQL_SELECT_USER =
            "SELECT * FROM " + TAB_USERS +
            " WHERE " + TAB_USER_NAME + " = ? AND " + TAB_USER_PASS + " = ?";

    public static final String SQL_INSERT_TASK =
            "INSERT INTO "+ TAB_TASKS +
                    " (" + TAB_TASKS_NAME + ", " + TAB_TASKS_DATE + ", " + TAB_TASKS_STATUS + ", " + TAB_TASKS_USER + ", " + "filename" + ") " +
                    "VALUES (?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_TASKS =
            "SELECT * FROM " + TAB_TASKS + ", " + TAB_STATUS +
                " WHERE " + TAB_TASKS_USER + " = ? " +
                    " AND " + TAB_TASKS + "." + TAB_TASKS_STATUS + " = " + TAB_STATUS + "." + TAB_STATUS_ID;
    public static final String SQL_UPDATE_TASK =
            "UPDATE " + TAB_TASKS +
            " SET " + TAB_TASKS_NAME   + " = ?, " +
                      TAB_TASKS_DATE   + " = ?, " +
                      TAB_TASKS_STATUS + " = ?, " +
                    " filename " + " = ? " +
            " WHERE (" + TAB_TASKS_ID + " = ?)";
    public static final String SQL_SELECT_STATUS =
            "SELECT * FROM " + TAB_STATUS +
                    " WHERE " + TAB_STATUS_NAME + " = ? ";
}
