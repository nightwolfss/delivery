<%@page import="java.util.List"%>
<%@page import="org.apache.catalina.connector.OutputBuffer"%>
<%@page import="entity.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String uri = request.getRequestURI();
String pageName = uri.substring(uri.lastIndexOf("/")+1);

String user = (String) session.getAttribute("usuario");
Integer iduser = (Integer) session.getAttribute("idusuario");
if(user == null){
	request.getRequestDispatcher("login.jsp").forward(request, response);
}
%>
<jsp:useBean id="dao" class="entity.Cliente"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clientes</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" href="icons/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="js/script.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body style="background-color: #F5F5F5">
<div id="barratopo" style="background-color: #FFB500; border-radius: 2px;">
<div class="container">
<span id="titulo">Cadastrar Clientes</span>
</div>

</div>
<hr>

<div class="container" style="background-color: white; border-radius: 2px;">
<span id="relogio">-</span>
</div>

<hr>
<div class="container">
<a href="index.jsp" class="btn btn-sm btn-warning">Início</a> |  
<a href="prato.jsp" class="btn btn-sm btn-warning">Itens</a> |
<a href="pedido.jsp" class="btn btn-sm btn-warning">Pedido</a> | 
<a href="<%=pageName%>" class="btn btn-sm btn-primary">Atualizar</a>
</div>

    <span id="msg" class="container">${msg}</span>
<span id="tempo"></span>
<div class="container" id="tabcliente">
	<form action="Controller?cmd=gravarcliente" method="post" style="background-color: white; padding: 10px; border-radius: 5px;">
	
		<div class="form-group">
		    <label>Nome</label>
		  	<input type="text" name="nome" class="form-control" aria-label="Nome" aria-describedby="basic-addon1">
		</div>
		
		<div class="form-group">
		    <label>Endereço</label>
			<input type="text" name="endereco" class="form-control" aria-label="Endereco" aria-describedby="basic-addon1">
		</div>
		
		<div class="form-group">
		    <label>Telefone</label>
		  	<input type="text" name="telefone" class="form-control" aria-label="Telefone" aria-describedby="basic-addon1">
		</div>
		
		<div class="form-group">
		    <label>Observações</label>
		  	<textarea name="obs" class="form-control" aria-label="With textarea"></textarea>
		  	<input type="hidden" name="idDono" value="<%=iduser%>">
		</div>
		
		<input type="submit" value="Gravar Cliente">
	</form>
</div>
<br><br>


<table class="table table-sm table-striped container" id="tabela1" style="background-color: white; border-radius: 5px;">
<thead class="thead-dark"><tr><th>Nome</th><th>Telefone</th><th>Endereço</th><th>Observações</th><th>Deletar</th><th>Editar</th></tr></thead>
<tbody>
<%
try{
	
	List<Cliente> lista = dao.getListaPorLogin(iduser);
	for(Cliente c : lista){ 
	%>
		<tr><input type="hidden" id="idescrita" value="<%=c.getId()%>">
			<td><span class="spanleitura"><%=c.getNome()%></span><input type="hidden" value="<%=c.getNome()%>" id="nomeescrita"> </td>
			<td><span class="spanleitura"><%=c.getTelefoneCliente()%></span><input type="hidden" value="<%=c.getTelefoneCliente()%>" id="telefoneescrita"> </td>
			<td><span class="spanleitura"><%=c.getEnderecoCliente()%></span><input type="hidden" value="<%=c.getEnderecoCliente()%>" id="enderecoescrita"> </td>
			<td><span class="spanleitura"><%=c.getObservacao()%></span><input type="hidden" value="<%=c.getObservacao()%>" id="obsescrita"> </td>
			<td><a href="Controller?cmd=deletarcliente&id=<%=c.getId()%>">
				<span style="color: red;"><i class="fas fa-user-times"></i></span></a></td>
			<td>
			
			<button type="button" id="btnatualizar" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop">
			  Alterar
			</button>
			
			</td>
		</tr>
	<%
	}
	
}catch(Exception e){
	
}
%>
</tbody>
</table>
<form action="Controller?cmd=alterarCliente" method="post">
<div class="modal fade" id="staticBackdrop" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Atualizar Cliente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
    <label>Nome</label>
    <input type="hidden" name="idcliente" id="idcliente" class="form-control" aria-label="Id" aria-describedby="basic-addon1">
  	<input type="text" name="nomecliente" id="nomecliente" class="form-control" aria-label="Nome" aria-describedby="basic-addon1">
</div>

<div class="form-group">
    <label>Endereço</label>
	<input type="text" name="enderecocliente" id="enderecocliente" class="form-control" aria-label="Endereco" aria-describedby="basic-addon1">
</div>

<div class="form-group">
    <label>Telefone</label>
  	<input type="text" name="telefonecliente" id="telefonecliente" class="form-control" aria-label="Telefone" aria-describedby="basic-addon1">
</div>

<div class="form-group">
    <label>Observações</label>
  	<textarea name="obscliente" id="obscliente" class="form-control" aria-label="With textarea"></textarea>
</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="Salvar">
      </div>
    </div>
  </div>
</div>

</form>

</body>
</html>