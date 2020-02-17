package by.gsu.epamlab.model.fabrics;

import by.gsu.epamlab.model.bin.Task;
import by.gsu.epamlab.model.comparators.TaskDateComparator;
import by.gsu.epamlab.model.utils.Loggers;
import by.gsu.epamlab.model.utils.TimeUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static by.gsu.epamlab.model.utils.Constants.DELIMITER_TAB;
import static by.gsu.epamlab.model.utils.TimeUtils.*;

public class SectionFactory {
    private static final Logger LOGGER = Loggers.init(SectionFactory.class.getName());
    private static Date today = new Date(new java.util.Date().getTime());

    private enum SectionKind {
        TODAY{
            @Override
            List<Task> sortList(List<Task> list) {
                TaskStatusFabric.TaskStatus status = TaskStatusFabric.TaskStatus.TODO;
                return sortListByParameters(list, status, TimeUtils.datePlusDays(today, -ONE_YEAR_MIL), today);
            }
        },
        TOMORROW{
            @Override
            List<Task> sortList(List<Task> list) {
                Date tomorrow = datePlusDays(today, ONE_DAY_MIL);
                TaskStatusFabric.TaskStatus status = TaskStatusFabric.TaskStatus.TODO;
                return sortListByParameters(list, status, today, datePlusDays(today, TWO_DAY_MIL));
            }
        },
        SOMEDAY{
            @Override
            List<Task> sortList(List<Task> list) {
                Date afterTomorrow = datePlusDays(today, TWO_DAY_MIL);
                Date oneYearLater = datePlusDays(today, ONE_YEAR_MIL);
                TaskStatusFabric.TaskStatus status = TaskStatusFabric.TaskStatus.TODO;
                return sortListByParameters(list, status, afterTomorrow, oneYearLater);
            }
        },
        FIXED{
            @Override
            List<Task> sortList(List<Task> list) {
                Date oneYearBefore = datePlusDays(today, -ONE_YEAR_MIL);
                Date oneYearLater = datePlusDays(today, ONE_YEAR_MIL);
                TaskStatusFabric.TaskStatus status = TaskStatusFabric.TaskStatus.FIXED;
                return sortListByParameters(list, status, oneYearBefore, oneYearLater);
            }
        },
        RECBYN{
            @Override
            List<Task> sortList(List<Task> list) {
                Date oneYearBefore = datePlusDays(today, -ONE_YEAR_MIL);
                Date oneYearLater = datePlusDays(today, ONE_YEAR_MIL);
                TaskStatusFabric.TaskStatus status = TaskStatusFabric.TaskStatus.DELETED;
                return sortListByParameters(list, status, oneYearBefore, oneYearLater);
            }
        };
        abstract List<Task> sortList(List<Task> list);
    }
    public static List<Task> getTasks(String sectionName, List<Task> allTasks){
        SectionKind sectionKind;
        try{
            sectionKind = SectionKind.valueOf(sectionName.toUpperCase());
        }catch (IllegalArgumentException e){
            LOGGER.log( Level.INFO, allTasks + DELIMITER_TAB + sectionName);
            sectionKind = SectionKind.TODAY;
        }
        return sectionKind.sortList(allTasks);
    }
    private static List<Task> sortListByParameters(List<Task> list, TaskStatusFabric.TaskStatus taskStatus, Date dateFrom, Date dateTo){
        List<Task> tasks = new ArrayList<>();
        list.sort(new TaskDateComparator());
        for(Task task:list){
            if(task.getDate().compareTo(dateFrom)>=0 && task.getDate().compareTo(dateTo)<=0){
                if(task.getStatus().getId() == taskStatus.getId()){
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}
