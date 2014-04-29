/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.workflow;

import com.netcracker.wind.entities.Role;
import com.netcracker.wind.entities.ServiceOrder;
import com.netcracker.wind.entities.Task;

/**
 *
 * @author Anatolii
 */
public class TaskCreator {

    public static Task createTask(int roleId, Task.TaskType taskType,
            Task.TaskStatus taskStatus, ServiceOrder order) {
        Task task = new Task();
        task.setRole(new Role(roleId));
        task.setType(taskType.toString());
        task.setStatus(taskStatus.toString());
        task.setServiceOrder(order);
        return task;
    }
}
