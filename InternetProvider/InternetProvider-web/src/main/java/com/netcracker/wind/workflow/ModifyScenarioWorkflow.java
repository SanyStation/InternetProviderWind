<<<<<<< HEAD
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
public class ModifyScenarioWorkflow {

    public static Task createTaskForModifyScenario(ServiceOrder order) {
        Task task = null;
        task = new Task();
        task.setRole(new Role(Role.CSE_GROUP_ID));
        task.setServiceOrder(order);
        task.setStatus(Task.TaskStatus.NEW.toString());
        task.setType(Task.TaskType.CIRCUIT_MANAGMENT.toString());
        return task;
    }
}
=======
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
public class ModifyScenarioWorkflow {

    public static Task createTaskForModifyScenario(ServiceOrder order) {
        Task task = null;
        task = new Task();
        task.setRole(new Role(Role.CSE_GROUP_ID));
        task.setServiceOrder(order);
        task.setStatus(Task.TaskStatus.NEW.toString());
        task.setType(Task.TaskType.CIRCUIT_MANAGMENT.toString());
        return task;
    }
}
>>>>>>> 8bafff6a8c4cfa3616a56bb7a2738f811bb4ca64
