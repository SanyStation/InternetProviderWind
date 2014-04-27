/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public List<Task> findByGroup(int idGroup, int from, int number);

    public List<Task> findByPerformer(int idPerformer);

    public List<Task> findByPerformerStatus(int idPerformer, String status);

    public void update(Task task);

    public List<Task> findAll();

    public Task occupyTaskByID(int taskId, int userId);

}
