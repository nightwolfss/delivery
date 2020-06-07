$(document).ready(function(){
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
});