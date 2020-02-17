package by.gsu.epamlab.model.fabrics;

public class TaskStatusFabric {

    public enum TaskStatus {
        TODO(1),            //1
        FIXED(2),          //2
        DELETED(3);      //3

        private int id;
        TaskStatus(int id){
            this.id = id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
    }

    public static TaskStatus getTaskStatus(String statusName){
        return TaskStatus.valueOf(statusName);
    }

    public static TaskStatus getTaskStatus(int statusId){
        TaskStatus taskStatus = TaskStatus.TODO;
        taskStatus.setId(statusId);
        return taskStatus;
    }
}
