<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="apple-touch-icon" sizes="76x76" href="images/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="images/favicon.png">

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<title>Liste des backup BDD</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css" />
<link href="css/coming-sssoon.css" rel="stylesheet" />

<!--     Fonts     -->
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Grand+Hotel'
	rel='stylesheet' type='text/css'>
</head>
<%@ page
	import="main.java.io.github.dramanebamba.pole_info.model.Backup,java.util.List"%>
<%
	String connected = (String) session.getAttribute("connected");
	String pseudo = (String) session.getAttribute("login");

	@SuppressWarnings("unchecked")
	List<Backup> listBackup = (List<Backup>) session.getAttribute("listBackup");
	if (connected == "true" && pseudo.equals("admin")) {
%>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="./accueil">Pole Info</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="./accueil">Menu</a></li>
			<li><a href="./GetManageStudent">&Eacute;tudiant</a></li>
			<li><a href="./GetManageTeacher">Enseignant</a></li>
			<li class="active"><a href="./GetManageDataBase">Base de donn&eacute;es</a></li>
			<li><a href="./GetManageJSON">Exports JSON</a></li>
			<li><a href="./GetManageMasterContenu">Masters &amp;
					Contenus</a></li>
			<li><a href="./GetManageAffectation">Affectations</a></li>
			<li><a href="./GetManageCours">Cours</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<!-- <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li> -->
			<li><a href="./GetDeconnexion"> <span
					class="glyphicon glyphicon-log-out"></span> Se d&eacute;connecter
			</a></li>
		</ul>
	</div>
	</nav>

	<div class="container" style="padding-top:70px;">
		<h1 class="page-header">Liste des sauvegardes de la base de donn�es</h1>
		<div class="row">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Date de la sauvegarde</th>
						<th>&Eacute;tiquette</th>
						<th>Supprimer</th>
						<th>Restaurer</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Backup b : listBackup) {
					%>
					<tr>
						<td><%=b.getIdBackup()%></td>
						<td><%=b.getDate()%></td>
						<td><a href="./<%=b.getNom()%>.sql" download><%=b.getLabel()%></a></td>
						<td class="test-align"><a
							href="GetManageBackupServlet?operation=remove&id=<%=b.getIdBackup()%>">
								<span class="glyphicon glyphicon-trash"></span>
						</a></td>
						<td class="text-align"><a
							href="GetManageBackupServlet?operation=restore&id=<%=b.getIdBackup()%>">
								<span class="glyphicon glyphicon-upload"></span>
						</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<br />
		<!-- <div class="row">
			<a class="btn btn-danger" href="./accueil">Annuler</a>
		</div> -->
	</div>
	<%
		} else {
	%>
	NOTHING TO SHOW
	<%
		}
	%>
</body>
</html>