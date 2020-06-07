$(document).ready(function(){
	
	var ano = new Date().getFullYear();
	var options='';
	for(i = ano; i <= ano + 3 ; i++){
	    options+='<option value="'+i+'">'+i+'</option>';
	}
	$("#anolembrete").append(options);
	
	
	
	$("#titulo").css({"font-size":"35px"});
	
	$("#msg").fadeOut(500);
	$("#msg").fadeIn(500);
	$("#msg").fadeOut(500);
	$("#msg").fadeIn(500);
	
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
		
		$(".titulolembrete").fadeOut(500);
		$(".titulolembrete").fadeIn(500);
	}
	
	
	
	});
	