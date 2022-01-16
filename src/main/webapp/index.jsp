<%-- 
    Document   : index.jsp
    Created on : 8 janv. 2022, 20:38:02
    Author     : Acer
--%>
<%
    if(session.getAttribute("admin")==null){
        response.sendRedirect("./pages/sign-in.jsp");
    }else{
        response.sendRedirect("./pages/home.jsp");
    }
%>
