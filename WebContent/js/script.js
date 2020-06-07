$(document).ready(function(){
	
	if($("#tabela1").width() > $("#tabcliente").width()){
		$("#tabcliente").width($("#tabela1").width());
		$("#barratopo").width($("#tabela1").width() + 25);
	}
	
	$("button").click(function(){
		$("#idcliente").val($(this).closest("tr").find("#idescrita").val());
		$("#nomecliente").val($(this).closest("tr").find("#nomeescrita").val());
		$("#telefonecliente").val($(this).closest("tr").find("#telefoneescrita").val());
		$("#enderecocliente").val($(this).closest("tr").find("#enderecoescrita").val());
		$("#obscliente").val($(this).closest("tr").find("#obsescrita").val());
	});
	
$("#titulo").css({"font-size":"35px"});
	
	setInterval(function(){hora()}, 1000);
	setInterval(function(){startCountdown()}, 1000);
	
	
	function hora(){
		var tempo = new Date();
		var ano = tempo.getFullYear();
		var mes = tempo.getMonth();
		var dia = tempo.getDate();
		
		var hora = tempo.getHours();
		var minuto = tempo.getMinutes();
		var segundo = tempo.getSeconds();
		
		if(mes.toString().length < 2){
			mes = "0".concat(mes);
		}
		
		if(dia.toString().length < 2){
			dia = "0".concat(dia);
		}
		
		if(hora.toString().length < 2){
			hora = "0".concat(hora);
		}
		
		if(minuto.toString().length < 2){
			minuto = "0".concat(minuto);
		}
		
		if(segundo.toString().length < 2){
			segundo = "0".concat(segundo);
		}
		
		var semana = new Array(7);
		semana[0] = "Domingo";
		semana[1] = "Segunda";
		semana[2] = "Terça";
		semana[3] = "Quarta";
		semana[4] = "Quinta";
		semana[5] = "Sexta";
		semana[6] = "Sábado";

		  var diasemana = semana[tempo.getDay()];
		
		$("#relogio").html(diasemana +", "+dia+"/"+mes+"/"+ano+" - "+hora+":"+minuto+":"+segundo);
		
	}

});
