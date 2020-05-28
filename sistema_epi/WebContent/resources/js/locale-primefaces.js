PrimeFaces.locales['pt'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho',
			'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta',
			'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 0,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Todo o Dia'
};

//Mascara PIS
function mascara(o, f) {
	v_obj = o
	v_fun = f
	setTimeout("execmascara()", 1)
};

function execmascara() {
	v_obj.value = v_fun(v_obj.value)
};

function pispasep(v) {
	v = v.replace(/\D/g, "") //Remove tudo o que não é dígito
	v = v.replace(/^(\d{3})(\d)/, "$1.$2") //Coloca ponto entre o terceiro e o quarto dígitos
	v = v.replace(/^(\d{3})\.(\d{5})(\d)/, "$1.$2.$3") //Coloca ponto entre o quinto e o sexto dígitos
	v = v.replace(/(\d{3})\.(\d{5})\.(\d{2})(\d)/, "$1.$2.$3.$4") //Coloca ponto entre o décimo e o décimo primeiro dígitos
	return v
};

function validarPIS(pis) {

	var multiplicadorBase = "3298765432";
	var total = 0;
	var resto = 0;
	var multiplicando = 0;
	var multiplicador = 0;
	var digito = 99;

	// Retira a mascara
	var numeroPIS = pis.replace(/[^\d]+/g, '');
	if (numeroPIS.length !== 11 || numeroPIS === "00000000000"
			|| numeroPIS === "11111111111"
			|| numeroPIS === "22222222222"
			|| numeroPIS === "33333333333"
			|| numeroPIS === "44444444444"
			|| numeroPIS === "55555555555"
			|| numeroPIS === "66666666666"
			|| numeroPIS === "77777777777"
			|| numeroPIS === "88888888888"
			|| numeroPIS === "99999999999") {
		return false;
	} else {
		for (var i = 0; i < 10; i++) {
			multiplicando = parseInt(numeroPIS.substring(i, i + 1));
			multiplicador = parseInt(multiplicadorBase.substring(i,
					i + 1));
			total += multiplicando * multiplicador;
		}
		resto = 11 - total % 11;
		resto = resto === 10 || resto === 11 ? 0 : resto;
		digito = parseInt("" + numeroPIS.charAt(10));
		return resto === digito;
	}
};

