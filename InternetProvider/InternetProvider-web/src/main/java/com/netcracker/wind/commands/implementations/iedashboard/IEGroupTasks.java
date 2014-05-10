/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.netcracker.wind.commands.implementations.iedashboard;

import com.netcracker.wind.annotations.RolesAllowed;
import com.netcracker.wind.commands.implementations.dashboards.GetGroupTasks;
import com.netcracker.wind.entities.Role;

/**
 *
 * @author Anatolii
 */
@RolesAllowed(roles = Role.Roles.InstallationEngineer)
public class IEGroupTasks extends GetGroupTasks{

    public IEGroupTasks(int groupId, String pageForReturn) {
        super(groupId, pageForReturn);
    }
    
}
