<%-- 
    Document   : blocked
    Created on : May 10, 2014, 8:50:35 AM
    Author     : Anatolii
--%>

<jsp:include page="login.jsp">
    <jsp:param name="error" value="true"/>
    <jsp:param name="message" value="Your account is blocked!"/>
    <jsp:param name="message2" value="<a href=\"logout\">Click to logout.</a>"/>
    
</jsp:include>