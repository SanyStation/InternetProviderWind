<%-- 
    Document   : cse-table-users
    Created on : 03.05.2014, 13:54:34
    Author     : oneplayer
--%>
<%@ page import="com.netcracker.wind.paging.IExtendedPaginatedList"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%
    IExtendedPaginatedList expl = (IExtendedPaginatedList) session.getAttribute("tasks");
    expl.setRequest(request);
%>
<h3>Customer users list</h3>
<form role="form">
    <display:table name="sessionScope.tasks" sort="external" 
                           partialList="true" class="simple"
                           pagesize="${sessionScope.tasks.objectsPerPage}" 
                           size="${sessionScope.tasks.fullListSize}">
                <display:column property="type" title="Type" />
                <display:column property="status" title="Status" />
                <display:column property="user.name" title="User name" />
                <display:column property="serviceOrderId" title="Service order id" />
                <display:column property="role.name" title="Role" />
            </display:table>
    <table class="table table-striped table-hover nomargin">
        <tr>
            <td>
                <b>User's id</b>
            </td>
            <td>
                <b>e-mail</b>
            </td>
            <td>
                <b>Firstname</b>
            </td>
            <td>
                <b>Lastname</b>
            </td>
            <td>
                <b>Address</b>
            </td>
        </tr>
        <tr>
            <td>
                312432
            </td>
            <td>
                someemail@gmail.com
            </td>
            <td>
                Andrey
            </td>
            <td>
                Ivanov
            </td>
            <td>
                prosp. Komarova 28
            </td>
        </tr>
        <tr>
            <td>
                312432
            </td>
            <td>
                someemail@gmail.com
            </td>
            <td>
                Andrey
            </td>
            <td>
                Ivanov
            </td>
            <td>
                prosp. Komarova 28
            </td>
        </tr>
        <tr>
            <td>
                312432
            </td>
            <td>
                someemail@gmail.com
            </td>
            <td>
                Andrey
            </td>
            <td>
                Ivanov
            </td>
            <td>
                prosp. Komarova 28
            </td>
        </tr>
        <tr>
            <td>
                312432
            </td>
            <td>
                someemail@gmail.com
            </td>
            <td>
                Andrey
            </td>
            <td>
                Ivanov
            </td>
            <td>
                prosp. Komarova 28
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" class="form-control">
            </td>
            <td>
                <input type="text" class="form-control">
            </td>
            <td>
                <input type="text" class="form-control">
            </td>
            <td>
                <input type="text" class="form-control">
            </td>
            <td>
                <input type="text" class="form-control">
            </td>
        </tr>                
    </table>
    <button type="button" class="btn btn-default margin"><span class="glyphicon glyphicon-remove-circle"></span> Apply filter</button>
    <button type="button" class="btn btn-default margin"><span class="glyphicon glyphicon-remove-circle"></span> Clean filter</button>
</form>
