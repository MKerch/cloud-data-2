<%-- 
    Document   : account
    Created on : Oct 16, 2018, 7:13:54 PM
    Author     : kerch
--%>
<%@page import="com.cloud2.pojo.FileMetaData"%>
<%@page import="com.cloud2.metadata.MetaDataDAOImpl"%>
<%@page import="com.cloud2.metadata.MetaDateDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style> 
            <%@include file="images/cloudImageStyle.css"%>
            <%@include file="images/table.css"%>
        </style>
    </head>
    <body>
        <%-----------------------------1--------------------------------------%>
    <center>
        <h1>
            Hi, <%=request.getSession().getAttribute("user")%>!          <a href="LoginServlet">Logout</a>
        </h1>
    </center>
    <%-----------------------------1--------------------------------------%>
    <%-----------------------##############-------------------------------%>
    <%-----------------------------2--------------------------------------%>
    <%
        String action = request.getParameter("action");
        if (action != null) {
    %>
    <%="File upload result: " + request.getParameter("action")%>
    <%
        }
    %>        
    <p>Upload file:<p>
    <form method="POST" action="AWSUploadServlet" enctype="multipart/form-data">
        <input type="file" name="data" />
        <input type="submit" value="SAVE/UPDATE"/>            
    </form>
    <br><br>
    <form action="DownloadServlet" method="get">    
        <input type="submit" value="Download latest file" >
    </form>
    <!--<p><a href="DownloadServlet">Download latest file:</a><p>-->
    <%-------------------------------2------------------------------------%>
    <%-----------------------##############-------------------------------%>
    <%-------------------------------3------------------------------------%>
    <% MetaDateDAO dao = new MetaDataDAOImpl();
        String user = request.getSession().getAttribute("user") + "";
        List<FileMetaData> files = dao.getAll(user);
    %>
    <center>
        <table>
            <tr>
                <th><p><b>FileName</b></p></th>
                <th><p><b>FileSize</b></p></th>
                <th><p><b>Type</b></p></th>
                <th><p><b>CreationDate</b></p></th>
            </tr>
            <%for (int i = 0; i < files.size(); i++) {%>
            <tr>
                <td><%=files.get(i).getName()%></td>
                <td><%=files.get(i).getSize()%></td>
                <td><%=files.get(i).getType()%></td>
                <td><%=files.get(i).getCreatedDate()%></td>
                <td>
                    <form action="AWSDeleteServlet" method="post">
                        <input type="hidden" name="id" value="<%=files.get(i).getId()%>"/>
                        <input type="submit" value="DELETE" >
                    </form>
                </td>
                <td>
                    <form action="AWSDownloadServlet" method="POST">
                        <input type="hidden" name="id" value="<%=files.get(i).getId()%>"/>
                        <input type="submit" value="DOWNLOAD" >
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </center>

    <%-------------------------------3------------------------------------%>




</body>
</html>
