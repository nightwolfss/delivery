$(document).ready(function(){
	$("#valor").mask("999.999.990,00", {reverse:true});
	
	$("#titulo").css({
		"font-size" : "35px"
	});
	
	
	$("#acao").change(function(){
		let idpedido = $("#idpedido").text();
		let acao = $("#acao").val();
		let cor = 'black';
		$.ajax({
			type : 'POST',
			
			data : {id : idpedido, status : acao},
			
			url : 'Controller?cmd=alterarStatusPedido',
			
			success : function(resultado){
				console.log(acao);
				if(acao == 'Cancelado'){
					cor = "red";
				}
				if(acao == 'Enviado'){
					cor = "green";
				}
				if(acao == 'Preparando'){
					cor = "blue";
				}
				$("#acao").closest("tr").fadeOut();
				$("#acao").closest("tr").css({"color":cor});
				$("#acao").closest("tr").fadeIn();
			}
		});
	});
	
});


if ($("#tabela2").width() > $("#prato").width()) {
	$("#prato").width($("#tabela2").width());
	$("#barratopo").width($(window).width());
}


setInterval(function() {
	hora()
}, 1000);

function hora() {
	var tempo = new Date();
	var ano = tempo.getFullYear();
	var mes = tempo.getMonth();
	var dia = tempo.getDate();

	var hora = tempo.getHours();
	var minuto = tempo.getMinutes();
	var segundo = tempo.getSeconds();

	if (mes.toString().length < 2) {
		mes = "0".concat(mes);
	}

	if (dia.toString().length < 2) {
		dia = "0".concat(dia);
	}

	if (hora.toString().length < 2) {
		hora = "0".concat(hora);
	}

	if (minuto.toString().length < 2) {
		minuto = "0".concat(minuto);
	}

	if (segundo.toString().length < 2) {
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

	$("#relogio").html(
			diasemana + ", " + dia + "/" + mes + "/" + ano + " - " + hora + ":"
					+ minuto + ":" + segundo);

}

function carrinho() {
	var a = document.querySelectorAll("#tabela1 tr");
	var aux = "";
	for (count = 0; count < a.length - 1; count++) {
		if (pedidos.itens[count].checked) {
			aux = aux.concat(pedidos.itens[count].value + " - ");
		}
	}
	$("#prato").val(aux);
	$("#quentinha").css("display", "");
}
