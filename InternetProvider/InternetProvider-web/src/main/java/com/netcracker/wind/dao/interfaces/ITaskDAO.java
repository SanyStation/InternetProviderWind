package com.netcracker.wind.dao.interfaces;

import com.netcracker.wind.entities.Task;
import java.util.List;

/**
 *
 * @author Oksana
 */
public interface ITaskDAO {

    public void add(Task task);

    public void delete(int id);

    public Task findByID(int id);

    public List<Task> findByGroup(int idGroup);

    public List<Task> findByGroup(int idGroup, int pageNumber, int pageSize);

    public List<Task> findByPerformer(int idPerformer);

    public List<Task> findByPerformer(int idPerformer, int from, int number);

    public List<Task> findByPerformerStatus(int idPerformer, String status);

    public List<Task> findByPerformerStatus(int idPerformer, String status,
            int from, int number);

    public List<Task> findByTypeAndStatus(String type, String... status);

    public List<Task> findByServiceOrder(int serviceOrderId);

    public List<Task> findByUser(int userId);

    public void update(Task task);

    public List<Task> findAll();

    public Task occupyTaskByID(int taskId, int userId);
    
    public int getRows();

}
