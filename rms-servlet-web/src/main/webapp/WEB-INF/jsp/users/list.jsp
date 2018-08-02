<%@ page language="java" pageEncoding="UTF-8" session="false"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>RMS</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <link type="stylesheet" href="http://localhost:8081/rms-servlet-web/css/styles.css?v=1.0"/>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script>
      $(function(){
            $(".delete").click(function(){
                var r = confirm("Are you sure want to delete this data?");
                if(r){
                    window.location.href = "del?use_id="+$(this).attr("data-id");
                }
                return false;
            });
      })
  </script>

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100 box-center">
    	<main class="mdl-layout__content" style="position:relative;margin:auto;max-width:800px;min-width:400px;">
            <div style="padding:5px 10px;cursor:pointer;text-align:center;margin-top:100px;">
                ${theMsg}
            </div>
            <div style="padding:5px 10px;cursor:pointer;text-align:right;margin-top:100px;">
                <a href="form">+ New Users</a>
            </div>
    		<table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
              <thead>
                <tr>
                  <th class="mdl-data-table__cell--non-numeric">User Name</th>
                  <th>Password</th>
                  <th style="text-align:center;">Actions</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items = "${users}" var="user">
                  <tr>
                    <td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.userName}"/></td>
                    <td><c:out value = "${user.password}"/></td>
                    <td>
                        <span style="margin-right:20px;"><a href="form?use_id=${user.id}" class="edit">Edit</a></span>
                        <span style="margin-left:20px;"><a href="#" class="delete" data-id="${user.id}">Delete</a></span></td>
                  </tr>
              </c:forEach>
              </tbody>
            </table>
    	</main>
    </div>
  <script src="js/scripts.js"></script>
</body>
</html>
