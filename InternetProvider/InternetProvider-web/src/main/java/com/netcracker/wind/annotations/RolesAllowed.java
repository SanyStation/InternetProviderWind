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
 * This annotation allow to check access rules.
 * Annotation can be used for marker commands.
 * All roles that will declare in input parameters can
 * invoke command in main controller class.
 *
 * @author Anatolii
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RolesAllowed {

    public Role.Roles[] roles();
}
