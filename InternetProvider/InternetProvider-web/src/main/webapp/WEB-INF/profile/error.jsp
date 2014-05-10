<%-- 
    Document   : error
    Created on : 01.04.2014, 16:33:20
    Author     : oneplayer
--%>
<jsp:include page="login.jsp">
    <jsp:param name="error" value="true"/>
    <jsp:param name="message" value="Wrong email/password combination!"/>
    
    <jsp:param name="message2" value="Maybe CapsLock is ON."/>
</jsp:include>
