package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Task;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ITaskDAO extends IRowsCounter {

    public void add(Task task);

    public void delete(int id);

    public Task findById(int id);

    public List<Task> findByGroup(int groupId);

    public List<Task> findByGroup(int groupId, int pageNumber, int pageSize);
    public List<Task> findByGroupStatus(int groupId,String status, int pageNumber, int pageSize);
    public List<Task> findByPerformer(int idPerformer, int pageNumber,
            int pageSize);

    public List<Task> findByPerformerStatus(int idPerformer, String status,
            int pageNumber, int pageSize);

    public List<Task> findByTypeAndStatus(int pageNumber, int pageSize, 
            Task.Type type, Task.Status... status);

    public List<Task> findByServiceOrder(int serviceOrderId, int pageNumber, 
            int pageSize);

    public List<Task> findByUser(int userId, int pageNumber, int pageSize);

    public void update(Task task);

    public List<Task> findAll(int pageNumber, int pageSize);

    public Task occupyTaskByID(int taskId, int userId);

}
