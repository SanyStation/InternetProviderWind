/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.wind.annotations;

import com.netcracker.wind.entities.Role;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Anatolii
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RolesAllowed {

    public Role.Roles[] roles();
//    public int[] role() default {Role.ADM_GROUP_ID, Role.CSE_GROUP_ID,
//        Role.CU_GROUP_ID, Role.IE_GROUP_ID, Role.PE_GROUP_ID};
}
