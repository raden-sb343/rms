<%@ page language="java" pageEncoding="UTF-8" session="false"%>

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
            $(".save-btn").click(function(){
                $("#saveForm").submit();
            });
      })
  </script>

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100">
    	<main class="mdl-layout__content">
    		<div class="mdl-card mdl-shadow--6dp">
    			<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
    				<h2 class="mdl-card__title-text">Acme Co.</h2>
    			</div>
    	  	<div class="mdl-card__supporting-text">
                    <div>${theMsg}</div>
    				<form action="form" id="saveForm" method="post">
    					<div class="mdl-textfield mdl-js-textfield">
                                                <input name="username" class="mdl-textfield__input" type="text" id="username" value="${users.getUserName()}"/>
    						<label class="mdl-textfield__label" for="username">Username</label>
    					</div>
    					<div class="mdl-textfield mdl-js-textfield">
    						<input name="userpass" class="mdl-textfield__input" type="text" id="userpass" value="${users.getPassword()}" />
    						<label class="mdl-textfield__label" for="userpass">Password</label>
    					</div>
                                        <input name="use_id" class="mdl-textfield__input" type="text" id="userpass" value="${users.getId()}" style="display:none" />
                                        <input type="submit" value="Save" style="display:none"/>
    				</form>
    			</div>
    			<div class="mdl-card__actions mdl-card--border">
    				<button class="save-btn mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
    			</div>
    		</div>
    	</main>
    </div>
  <script src="js/scripts.js"></script>
</body>
</html>
