<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" href="icons/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
  body {
        margin: 0px;
    }
    
    .container {
        width: 100vw;
        height: 50vh;
        
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center
    }
    #centro {
        width: 300px;
        height: 300px;
        
    }

</style>

</head>
<body class="container">
<div class="tudo">
<div class="imagem">
<div id="centro">
<h1>Login</h1>
<form action="Controller?cmd=logar" method="post" class="form-group">
<table class="table table-borderless">
	<tr><td>Nome</td><td><input type="text" name="nome" class="form-control"></td></tr>
	<tr><td>Senha</td><td><input type="password" name="senha" class="form-control"></td></tr>
	<tr><td colspan="2" align="right"><input type="submit" value="login" class="btn btn-info"></td></tr>
</table>
</form>

<p>

<button id="btncriar">Novo Usuário</button>

<div id="criarlogin" style="display: none;">
<table>
	<tr><td>Nome</td><td><input type="text" name="criarnome" id="criarnome"></td></tr>
	<tr><td>Senha</td><td><input type="password" name="criarsenha" id="criarsenha"></td></tr>
	<tr><td colspan="2" align="right"><input type="button" value="criar" id="criar"></td></tr>
</table>

<div id="msg"></div>

</div>
</div>
</div>
</div>
<script>
$("#btncriar").click(function(){
	$("#criarlogin").css("display","none");
	$("#criarlogin").fadeIn(500);
});

$("#criar").click(function(){
	var nome = $("#criarnome").val();
	var senha = $("#criarsenha").val();
	var admin = 'n';
	
	$.ajax({
		type: 'POST',
		data: {	nome: nome,
				senha: senha,
				admin: admin },
		url: 'Controller?cmd=criarlogin',
		success: function(resultado){
			
			$("#msg").html(resultado);
			$("#msg").fadeIn();
			$("#msg").fadeOut();
			$("#msg").fadeIn();
			$("#msg").fadeOut();
			$("#msg").fadeIn();
		},
		error: function(resultado){
			$("#msg").html(resultado).css("font-color", "red");
			$("#msg").fadeIn();
			$("#msg").fadeOut();
			$("#msg").fadeIn();
			$("#msg").fadeOut();
			$("#msg").fadeIn();
		}
	});
});

$("#criarnome").keyup(function(){
	var nome = $("#criarnome").val();
	
	$.ajax({
		type : 'POST',
		data : {nome : nome},
		url : 'Controller?cmd=consultarloginNome',
		success : function(resultado){
			$("#msg").html(resultado);
		}
	});
});
</script>

</body>
</html>