package com.netcracker.wind.entities;

import com.netcracker.wind.dao.factory.AbstractFactoryDAO;
import com.netcracker.wind.dao.factory.FactoryCreator;
import com.netcracker.wind.dao.implementations.helper.AbstractOracleDAO;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Anatolii
 */
public class Role implements Serializable {

    public enum Roles {

        Administrator, CustomerUser, CustomerSupportEngineer, ProvisioningEngineer, InstallationEngineer
    }

    private static final long serialVersionUID = 4491197292843435012L;

    private final AbstractFactoryDAO factoryDAO
            = FactoryCreator.getInstance().getFactory();

    public static final int CU_GROUP_ID = 5;
    public static final int PE_GROUP_ID = 2;
    public static final int IE_GROUP_ID = 3;
    public static final int CSE_GROUP_ID = 4;
    public static final int ADM_GROUP_ID = 1;

    private Integer id;
    private String name;
    private transient List<Task> tasksList;
    private transient List<User> usersList;

    public Role() {
    }

    public Role(Integer id) {
        this.id = id;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasksList() {
        if (tasksList == null) {
            tasksList = factoryDAO.createTaskDAO().findByGroup(id);
        }
        return tasksList;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public List<User> getUsersList() {
        if (usersList == null) {
            usersList = factoryDAO.createUserDAO().findByRole(id,
                    AbstractOracleDAO.DEFAULT_PAGE_NUMBER,
                    AbstractOracleDAO.ALL_RECORDS);
        }
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(id);
        builder.append(name);
        builder.append(tasksList);
        builder.append(usersList);
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Role)) {
            return false;
        }
        Role rhs = (Role) object;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(id, rhs.getId());
        builder.append(name, rhs.getName());
        builder.append(tasksList, rhs.getTasksList());
        builder.append(usersList, rhs.getUsersList());
        return builder.isEquals();
    }

    @Override
    public String toString() {
        return "com.netcracker.wind.entities.Roles[ id=" + id + " ]";
    }

}
