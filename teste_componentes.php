<?php
/**
 * @package SYS
 */
$program->title = 'KMA';
//$program->requirelogin = false;
/**
 * Classe km_dbform
 */
require_once KM::getDirRun() . '/lib/dbform.php';

/**
 * classe rpt_pdf
 */
require_once KM::getDirRun() . '/lib/rptpdf.php';

class SYS_teste_componentes extends km_dbform {

	public function Initialization() {
		skBeginForm('');
		
		$query = db_query::Instanciar();
		
		echo KM_extensoValor('1.258,85') . '<br>';
		echo KM_extensoValor($query->phptype_to_db('numeric', '200,85')) . '<br>';
		echo KM_extensoValor($query->phptype_to_db('numeric', '111554058.85')) . '<br>';
		echo KM_formatNunber($query->phptype_to_db('numeric', '111.554.058,85')) . '<br>';
		echo KM_extensoValor('111554058.85') . '<br>';
		
		echo 'disponivel.txt: "' . file_get_contents(KM::getDirApp() . '/disponivel.txt') . '"<br>';
		$aDisponibilidade = json_decode(file_get_contents(KM::getDirApp() . '/disponivel.txt'), true);
		print_rpre($aDisponibilidade);
		echo '$aDisponibilidade[DISPONIVEL]: ' . $aDisponibilidade['DISPONIVEL'] . '<br>';
		
		echo '1477593202000: ' . datetostr('1477593202000') . '<br>';
		echo '(62) 9969-2995: ' . KM_formatTelefone('(62) 9969-2995') . '<br>';
		echo '(64)92452-101: ' . KM_formatTelefone('(64)92452-101') . '<br>';
		echo '6293958788: ' . KM_formatTelefone('6293958788') . '<br>';
		echo '93958788: ' . KM_formatTelefone('93958788') . '<br>';
		echo '993958788: ' . KM_formatTelefone('993958788') . '<br>';
		echo '(62): ' . KM_formatTelefone('(62)') . '<br>';
		echo '62: ' . KM_formatTelefone('62') . '<br>';
		
		skButton('Inserir Apartamentos de Sindicos e Conselhos', 'action_Inserir_apartamento_sindico_conselho');
		skButton('Alterar ID Contrato para Número de Contrato nas NFEs', 'action_Alterar_Numero_Contrato_Nfe');
		skButton('Atualizar Lista de Telefone Objetiva', 'action_Atualizar_Lista_Telefone_Objetiva');
		skButton('Atualizar NFE Corpo', 'action_Atualizar_sys_nfe');
		skButton('Atualizar NFE Corpo Reverso', 'action_Atualizar_sys_nfe_reverso');
		skButton('Enviar SMS Curso Estética CRF-GO', 'action_Enviar_Sms_Curso_Estetica');
		skButton('Enviar SMS Curso Radiofarmacia CRF-GO', 'action_Enviar_Sms_Curso_Radiofarmacia');
		skButton('Webservice Retorno', 'action_Webservice_Retorno');
		skButton('Webservice Remessa de Títulos Formato Netsuprema', 'action_Webservice_Remesssa_Titulo_Formato_Netsuprema');
		skButton('Webservice Remessa de Títulos', 'action_Webservice_Remesssa_Titulo');
		skEndForm();
	}
	
	public function action_Enviar_Sms_Curso_Estetica() {
		$sArqFonte = KM::getDirApp() . '/CRFGO/sms_curso_estetica.csv';
		$sMensagem = 'CRF-GO INFORMA MUNDAÇA LOCAL  PALESTRA "ATUACAO DO FARM. NA ESTETICA" PUC – AUDITORIO 1, AREA 1, ESQ DA 5ª AV. C/ R 236, BL G, ST. UNIVERSITARIO.';
		$this->enviarSmsCurso($sArqFonte, $sMensagem);
		$this->Initialization();
	}
	
	public function action_Enviar_Sms_Curso_Radiofarmacia() {
		$sArqFonte = KM::getDirApp() . '/CRFGO/sms_curso_radiofarmacia.csv';
		$sMensagem = 'CRF-GO INFORMA MUNDAÇA LOCAL  PALESTRA "ATUACAO DO FARM. NA RADIOFARMACIA" PUC – AUDITORIO 1, AREA 1, ESQ DA 5ª AV. C/ R 236, BL G, ST. UNIVERSITARIO.';
		$this->enviarSmsCurso($sArqFonte, $sMensagem);
		$this->Initialization();
	}
	
	private function enviarSmsCurso($sArqFonte, $sMensagem) {
		$handle = fopen($sArqFonte, "r");
		if ($handle !== FALSE && $handle !== FALSE) {
			$iContEnvio = 0;
			while (($data = fgetcsv($handle, null, ";")) !== FALSE) {
				$sNumeroTelefone = trim($data[2]);
				$sNumeroTelefone = KM_formatTelefone($sNumeroTelefone);
				$iLenTel = strlen($sNumeroTelefone);
				if ($iLenTel == 13 || $iLenTel == 14) {
					if ($iLenTel == 13) {
						$sNumeroTelefone = substr($sNumeroTelefone, 0, 4) . '9' . substr($sNumeroTelefone, 4);
					}
					SYS::EnviarSms(KM_tratarTelefone($sNumeroTelefone), $sMensagem);
					$iContEnvio++;
					echo $iContEnvio . ': ' . KM_tratarTelefone($sNumeroTelefone) . '<br>';
				}
			}
			skMsgInfo('Foram agendados ' . KM_formatNunber($iContEnvio) . ' SMS para o Curso escolhido.');
		} else {
			skMsgWarning('Não foi possível abrir o arquivo que contem os telefones a serem enviados os sms!');
		}
	}
	
	public function action_Atualizar_sys_nfe_reverso() {
		$query = db_query::Instanciar();
		$query2 = db_query::Instanciar();
		
		$query->open('select IDNFE from SYS_NFE where ENVIADO=false');
		$i = 0;
		while ($query->fetch()) {
			$query2->openTable('SYS', 'NFE');
			$query2->findKey($query->IDNFE);
// 			ECHO $query2->NFE_CORPO . '<br><br>';
// 			echo 'Pos: ' . strpos($query2->NFE_CORPO, 'IdRps=') . '<br>';
// 			echo 'Substituir: "' . substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'IdRps='), 10) . '"<br>';
// 			echo 'Pos: ' . strpos($query2->NFE_CORPO, 'NumeroRps=') . '<br>';
// 			echo 'Substituir: "' . substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'NumeroRps='), 14) . '"<br><br><br>';
			
			$query2->NFE_CORPO = str_replace('[{[NUMERO_NFE]}]', $query->IDNFE, $query2->NFE_CORPO);
// 			ECHO $query2->NFE_CORPO . '<br><br>';
// 			die();
			$query2->save();
			$i++;
		}
		skMsgNotice('Foram atualizados ' . KM_formatNunber($i, 0) . ' registros!');
		$this->Initialization();
	}
	
	public function action_Atualizar_sys_nfe() {
		$query = db_query::Instanciar();
		$query2 = db_query::Instanciar();
		
		$query->open('select IDNFE from SYS_NFE where ENVIADO=false');
		$i = 0;
		while ($query->fetch()) {
			$query2->openTable('SYS', 'NFE');
			$query2->findKey($query->IDNFE);
// 			ECHO $query2->NFE_CORPO . '<br><br>';
// 			echo 'Pos: ' . strpos($query2->NFE_CORPO, 'IdRps=') . '<br>';
// 			echo 'Substituir: "' . substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'IdRps='), 10) . '"<br>';
// 			echo 'Pos: ' . strpos($query2->NFE_CORPO, 'NumeroRps=') . '<br>';
// 			echo 'Substituir: "' . substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'NumeroRps='), 14) . '"<br><br><br>';
			
			$query2->NFE_CORPO = str_replace(substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'IdRps='), 10), 'IdRps=[{[NUMERO_NFE]}]', $query2->NFE_CORPO);
			$query2->NFE_CORPO = str_replace(substr($query2->NFE_CORPO, strpos($query2->NFE_CORPO, 'NumeroRps='), 14), 'NumeroRps=[{[NUMERO_NFE]}]', $query2->NFE_CORPO);
// 			ECHO $query2->NFE_CORPO . '<br><br>';
// 			die();
			$query2->save();
			$i++;
		}
		skMsgNotice('Foram atualizados ' . KM_formatNunber($i, 0) . ' registros!');
		$this->Initialization();
	}
	
	public function action_Alterar_Numero_Contrato_Nfe() {
		$query = db_query::Instanciar();
		$query2 = db_query::Instanciar();
		$query3 = db_query::Instanciar();
		
		$query->open('select IDCONTRATO, NUMERO_CONTRATO from ALU_CONTRATO');
		$iNumRegistroAtualizado = 0;
		while ($query->fetch()) {
			
			$sSearch = '. Contrato ' . $query->IDCONTRATO . ' ';
			$sReplace = '. Num Contrato ' . $query->NUMERO_CONTRATO . ' ';
			$query2->open('select idnfe from sys_nfe where nfe_corpo like '. db_escape_string('%' . $sSearch . '%'));
			while ($query2->fetch()) {
				$query3->openTable('SYS', 'NFE');
				$query3->findKey($query2->IDNFE);
				$query3->NFE_CORPO = str_replace($sSearch, $sReplace, $query3->NFE_CORPO);
				$query3->save();
				$iNumRegistroAtualizado++;
			}
		}
		skMsgNotice('Foram atualizados ' . KM_formatNunber($iNumRegistroAtualizado, 0) . ' registro de NFE!');
	}
	
	public function action_Inserir_apartamento_sindico_conselho() {
		$query = db_query::Instanciar();
		$query2 = db_query::Instanciar();
		
		$query2->open('select 
	ac.IDATENDIMENTO_CONDOMINIO,
	ar.IDATENDIMENTO_REPARTICAO
from 
	CRM_ATENDIMENTO_CONDOMINIO ac
	left join crm_atendimento_reparticao ar on ac.IDATENDIMENTO_CONDOMINIO=ar.IDATENDIMENTO_CONDOMINIO
order by
	ac.IDATENDIMENTO_CONDOMINIO,
	ar.IDATENDIMENTO_REPARTICAO');
		$iIdAtendimentoCondominioAnt = null;
		while ($query2->fetch()) {
			if ($iIdAtendimentoCondominioAnt != $query2->IDATENDIMENTO_CONDOMINIO) {
				/**
				 * INCLUINDO
				 * 000- Síndico
				 */
				$query->openTable('SYS', 'PESSOA');
				$query->IDOPERADOR_CADASTRO = SYS_idOperador();
				$query->DHCADASTRO = time();
				$query->NMPESSOA      = '000- Síndico';
				$query->NOME_FANTASIA = '000- Síndico';
				$query->TIPO_PESSOA   = 'F';
				$query->APARTAMENTO   = true;
				$query->IDOPERADOR_ALTERACAO = SYS_idOperador();
				$query->DHALTERACAO = time();
				$query->save(true);
				$iIdpessoa = $query->IDPESSOA;
				
				$query->openTable('CRM', 'ATENDIMENTO_APARTAMENTO');
				$query->IDATENDIMENTO_APARTAMENTO = $iIdpessoa;
				$query->IDATENDIMENTO_CONDOMINIO  = $query2->IDATENDIMENTO_CONDOMINIO;
				$query->IDATENDIMENTO_REPARTICAO  = $query2->IDATENDIMENTO_REPARTICAO;
				$query->save();
				
				/**
				 * INCLUINDO
				 * 000- Conselho
				 */
				$query->openTable('SYS', 'PESSOA');
				$query->IDOPERADOR_CADASTRO = SYS_idOperador();
				$query->DHCADASTRO = time();
				$query->NMPESSOA      = '000- Conselho';
				$query->NOME_FANTASIA = '000- Conselho';
				$query->TIPO_PESSOA   = 'F';
				$query->APARTAMENTO   = true;
				$query->IDOPERADOR_ALTERACAO = SYS_idOperador();
				$query->DHALTERACAO = time();
				$query->save(true);
				$iIdpessoa = $query->IDPESSOA;
				
				$query->openTable('CRM', 'ATENDIMENTO_APARTAMENTO');
				$query->IDATENDIMENTO_APARTAMENTO = $iIdpessoa;
				$query->IDATENDIMENTO_CONDOMINIO  = $query2->IDATENDIMENTO_CONDOMINIO;
				$query->IDATENDIMENTO_REPARTICAO  = $query2->IDATENDIMENTO_REPARTICAO;
				$query->save();
				
				$iIdAtendimentoCondominioAnt = $query2->IDATENDIMENTO_CONDOMINIO;
			}
		}
		
		$this->Initialization();
	}
		
	public function action_Atualizar_Lista_Telefone_Objetiva() {
		require_once KM::getDirRun() . '/pkg.PRS/rotina.php';
		$classPrsRotina = new PRS_rotina();
		$classPrsRotina->action_Atualizar_Lista_Telefone_Objetiva();
	}
	
	public function action_Atualizar_Lista_Telefone_Objetiva1() {
		$query = db_query::Instanciar();
		
		/**
		 ***************************************************************************************************************
		 * CONECTAR NO BD via shell
		 * # mysql -h localhost -u netsuprema -p
		 * Enter password:
		 * 
		 * ## Escolhendo qual será o banco de dados;
		 * mysql> use asterisk;
		 * Database changed
		 * mysql>
		 *************************************************************************************************************** 
		 */
		
$url = '192.168.7.5';
$porta = '3306';
$senha = 'supremanet@';
$usuario = 'netsuprema';
$banco = 'asterisk';

$conn = mysql_connect($url, $usuario, $senha);
if (!$conn) {
	die('Erro ao tentar conectar');
}
mysql_select_db($banco) or die('Erro ao se conectar');

		$sql = 'select 
	pt.TELEFONE,
	c.SENHA_CONSULTORIA,
	c.SENHA_SITE,
	c.SITUACAO
from 
	prs_contrato c
	join sys_pessoa_telefone pt on c.IDCLIENTE=pt.IDPESSOA
where
	situacao<>2';
		$query->open($sql);
// 		$sql_delete = 'delete from crm_telefone;';
// 		mysql_query($sql_delete, $conn);
		$i = 0;
		if ($query->fetch()) {
			$i++;
			$sql_insert = 'insert into crm_telefone (telefone, codigo_acesso, status) values ("' . preg_replace("/[^0-9]/", "", $query->TELEFONE) . '", ' . ($query->SENHA_CONSULTORIA ? '"' . $query->SENHA_CONSULTORIA  . '"' : 'null') . ', "' . (($query->SITUACAO == 1 || $query->SITUACAO == 3) ? 1 : 2) . '")';
			while ($query->fetch()) {
				$sql_insert .= ', ("' . preg_replace("/[^0-9]/", "", $query->TELEFONE) . '", ' . ($query->SENHA_CONSULTORIA ? '"' . $query->SENHA_CONSULTORIA  . '"' : 'null') . ', "' . (($query->SITUACAO == 1 || $query->SITUACAO == 3) ? 1 : 2) . '")';
				$i++;
			}
			$sql_insert .= ';';
			print '$sql_insert<br>' . $sql_insert . '<br><br><br><br>';
// 			die();
			mysql_query($sql_insert, $conn);
		}

// inserindo
// $sql_insert = 'insert into crm_telefone (telefone, codigo_acesso, status) values ("' . rand(10000000, 99999999) . '", "5", "1")';
// mysql_query($sql_insert, $conn);

// Buscando
$sql = 'select * from crm_telefone';
$result = mysql_query($sql);
while ($record = mysql_fetch_assoc($result)){
	print_r($record);
}
mysql_close($conn);

		skMsgNotice('Foram atualizados/incluídos ' . KM_formatNunber($i) . ' registros de telefones.');
	}
	
	
	public function Initialization2() {
		
		
		echo 'Cel: ' . KM_tratarTelefone('(16)9810-45670') . '<br>';
		
		echo 'data: ' . datetimetostr(113000001999) . 'br';
		
		skBeginForm('');
		
		$dtAtual = time();
		$dtSoma = KM_dataSomaDiaUtil($dtAtual, 5);
		echo '$dtAtual: ' . datetostr($dtAtual) . '<br>';
		echo '$dtSoma: ' . datetostr($dtSoma) . '<br>';
		
		skButton('Teste Referer', 'action_Teste');
		skButton('Teste2', 'action_Teste2');
		skEndForm();
	}
	
	public function action_Teste2() {
		print_rpre($_SERVER);
	}
	
	public function action_Teste() {
		skBeginForm('');
		print_rpre($_SERVER);
		skEndForm();
	}
	public function teste() {
		
//		print_rpre($_SERVER);
	
//		skWindowOpen($url);
		
//		require_once KM::getDirRun() . '/pkg.ALU/lib/AluDashboard4.php';
//		$oDash = new AluDashboard4();
//		print_rpre($oDash->carregar());
//		die();
		
//		PAY::WebserviceBrasPagRequisicao(342);
//		exit;
		
		skBeginForm('');
		//		$url = 'http://sig3.netsuprema.com.br/credisaude/index.php?KMP=COL&KMF=titulo_uniodonto&SACADO=RAFAEL%20BOCA%20ABERTA&CPF_CNPJ=01234567890&EXTRATO=VALOR%20INCRICAO%20PARA%20BOCA&VALOR=150&VENCIMENTO=06/02/2012';
		//		$url = 'http://sig.netsuprema.com.br/credisaude/index2.php?KMP=COL&KMF=TITULO_UNIODONTO&doctype=nomenu&SACADO=&CPF_CNPJ=TESTE&EXTRATO=Valor%20da%20Inscri%E7%E3o&VALOR=1&VENCIMENTO=06/02/2012';
		//		$p = array('SACADO'=>'RAFAEL BOCA ABERTA', 'CPF_CNPJ'=>'01234567890', 'EXTRATO'=>'VALOR INCRICAO PARA BOCA','VALOR'=>150,'VENCIMENTO'=>'06/02/2012');
		//		$url = sk_montaLinkGet('Initialization', $p, 'COL', 'TITULO_UNIODONTO');
		//		$js = skWindowOpen($url);
		//		skButtonInput('Chamar Geração', '', $js);
		//		skButton('Atualizar Cobrança do Empreendimento em uma nova Tabela', 'action_Atualizar');
		//		skButton('Visualizar 2º via de boletos', 'action_Visualizar_Boletos');
				skButton('Webservice Retorno', 'action_Webservice_Retorno');
//				skButton('Webservice Retorno', 'action_Testar_Opener');
				skButton('Webservice Remessa de Títulos Formato Netsuprema', 'action_Webservice_Remesssa_Titulo_Formato_Netsuprema');
				skButton('Envio de email de Teste', 'action_Enviar_Email_Teste');
				skButton('Verificar Replicação', 'action_Verificar_Replicacao');
				skButton('Direito cliente GOPAG', 'action_Direito_Cliente_Gopag');
				skEndForm();
	}
	
	public function action_Direito_Cliente_Gopag() {
		require_once KM::getDirRun() . '/pkg.PAY/lib/api.php';
		
		API::validarOperador(597);
		$this->Initialization();
	}
	
	public function action_Verificar_Replicacao() {
		global $km_db;
		$km_db = new db_connection('192.168.7.220', 'SIG_COL_secovicred', KM::DbUser, KM::DbPassword);
		$query = db_query::Instanciar();
		$query->open('select DHINCLUSAO from col_titulo order by idtitulo desc limit 1');
		$query->fetch();
		skTextLabel('Último título registrado no servidor de replicação', datetimetostr($query->DHINCLUSAO));
		
		$km_db = new db_connection('192.168.7.220', 'SIG_COL_secovicred', KM::DbUser, KM::DbPassword);
		$query = db_query::Instanciar();
		$query->open('select DHINCLUSAO from col_titulo order by idtitulo desc limit 1');
		$query->fetch();
		skTextLabel('Último título registrado no servidor de replicação', datetimetostr($query->DHINCLUSAO));
	}
	
	public function action_Enviar_Email_Teste() {
		require_once 'Zend/Mail.php';
		require_once 'Zend/Mail/Transport/Smtp.php';
		

		$sEmail = 'geovany@netsuprema.com.br';
		$sUsername = 'GeovanyNetsuprema';
		/* Setando os endereços de From & Reply-To */
		Zend_Mail::setDefaultFrom($sEmail, $sEmail);
		Zend_Mail::setDefaultReplyTo($sEmail, $sEmail);
		
		/* Criando configuração de login*/
		$config = array('auth' => 'login', 'username' => $sEmail, 'password' => '1a2b3c', 'port' => 587);
		$oTransport = new Zend_Mail_Transport_Smtp('mail.netsuprema.com.br', $config);
		
		/* Criando configuração de login*/
		$sBody = 'Teste';
		$aReplace = array('[NOME_ASSOCIADO]', '[DATA_ENVIO]', '[DTVENCIMENTO]');
		$sBody = str_replace($aReplace, $aValues, $sBody);
		$aEmails = array('osias@netsuprema.com.br', 'osias26@hotmail.com', 'geovanybastos@gmail.com');
		/*preparando e enviando o email*/
		$sAssunto = 'Teste';
		$mail = new Zend_Mail();
		$mail->addTo($aEmails);
		$mail->setSubject($sAssunto);
		$mail->setBodyHtml($sBody);
		if ($mail->send($oTransport)) {
			echo 'Email enviado';
		} else {
			echo 'Não enviado';
		}	
	}
	
	public function display_Data_Testar() {
		skBeginForm('');
		$aFeriado = KM_dataGetFeriados();
		$aFeriado[] = array(null => '01/02/2013');
		$aFeriado[] = array('01/07/2013' => null);
		print_rpre($aFeriado);
		$DtAtual = datetostr();
		echo '$DtAtual: '.$DtAtual.'<br>';
		skEditDate('Data Teste', 'DTTESTE', '', true, $aFeriado);
		skButton('Testar Data', 'action_Data_Testar');
		
		skEndForm();
	}
	
	public function action_Webservice_Remesssa_Titulo() {
		$query = db_query::Instanciar();
		//		$p = array(
		//				'KMF' => 'webservice',
		//				'KMP' => 'SYS',
		//				'SERVICO' => 'SYS::RetornoWebserviceCNAB400',
		//				'doctype' => 'json',
		//				'OPERADOR' => SYS::WebserviceConsultaFinanceiraOperador,
		//				'NIVEL_CONSULTA' => $iNivelConsulta,
		//				'CPF_CNPJ' => $sCpfCnpj);
		//
		//		require_once KM::getDirRun() . '/pkg.SYS/webservice.php';
		//		$aRetorno = SYS_webservice::chamadaRemota(static::WebserviceServidorPadraoConsultaFinanceira . http_build_query($p));
		//		print_rpre($aRetorno);
		//		die();
	
		/**
		 *************************************************************************************************************
		 * INÍCIO
		 *************************************************************************************************************
		 */
		require_once KM::getDirRun() . '../libs/nusoap/lib/nusoap.php';
// 		$url = 'http://192.168.7.205/geovany/aplication/webservice_remessa_titulo.php?wsdl';
					$url = 'http://sig5.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
// 					$url = 'https://sig.netsuprema.com.br/col_cobrancateste/webservice_remessa_titulo.php?wsdl';
		// 			$url = 'https://sig.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
		//			$url = 'http://remessa.secovicred.netsuprema.com.br/';
		//			$url = 'http://remessa.secovicred.netsuprema.com.br/?wsdl';
		//			$url = 'https://sig.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
		$client = new nusoap_client($url, 'wsdl');
			
		$err = $client->getError();
		if ($err) {
			//$aRetorno['error'] = true;
			//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
			echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
		}
		$debug = KM::getDebug();
			
// 		$Autenticacao = array(
// 								'USUARIO' => 'CRECI_5_HOMOLOGACAO',
// 								'SENHA_MD5' => '00470d2168526576c72bcf8b60c11297');
// 		$Cedente = array(
// 								'CODIGO_CEDENTE' => '11436',
// 								'CODIGO_CEDENTE_DV' => '7',
// 								'CONTA_CORRENTE' => '1744',
// 								'CONTA_CORRENTE_DV' => '2');

// 		$Autenticacao = array(
// 								'USUARIO' => 'CRECI_5_HOMOLOGACAO',
// 								'SENHA_MD5' => '00470d2168526576c72bcf8b60c11297');
// 		$Cedente = array(
// 				'CODIGO_CEDENTE' => '3994',
// 				'CODIGO_CEDENTE_DV' => '4',
// 				'CONTA_CORRENTE' => '895',
// 				'CONTA_CORRENTE_DV' => '8');
		
		$Autenticacao = array(
								'USUARIO' => 'BELOMONTE_WEBSERVICE',
								'SENHA_MD5' => '28a4fb4c09237e95f95312a0409f499e');
		$Cedente = array(
				'CODIGO_CEDENTE' => '18975',
				'CODIGO_CEDENTE_DV' => '8',
				'CONTA_CORRENTE' => '2715',
				'CONTA_CORRENTE_DV' => '4');
		
// 		$Autenticacao = array(
// 				'USUARIO' => 'CASA DE PANO',
// 				'SENHA_MD5' => '74d88cfe6a273b85fde235f426f29845');
// 		$Cedente = array(
// 				'CODIGO_CEDENTE' => '8958',
// 				'CODIGO_CEDENTE_DV' => '3',
// 				'CONTA_CORRENTE' => '3072',
// 				'CONTA_CORRENTE_DV' => '4');
			
		$Dados = array('EMAIL_NOTIFICACAO' => 'debora.costa@uau.com.br', 'FORMATO' => '4', 'NOME_ARQUIVO' => 'cobrem756_3333_2715-4_090117_011.txt', 'REMESSA' => 
'01REMESSA01COBRANÇA       33332000189758      BELO MONTE EMPREENDIMENTOS IMO756BANCOOBCED     0901170003201                                                                                                                                                                                                                                                                                               000001
1021561118500014833332000027154000000                         0000003102370100       0000000000000000     01010000046088160117000000004307075633332010090117070700994802000010000000000000000000900000000000000000000000000100039774783204JONES PACHECO PINTO                     RUA DRAGAO DO MAR, 2925 - PROX. AO GUPREMEM         68372566ALTAMIRA       PANão Protestar.                          00 000002
9                                                                                                                                                                                                                                                                                                                                                                                                         000003
');
			
			
// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '1', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => 
// '40010
// 30012015000000030000000723810000064729               ADRIANDO MAYKEL BONFIM        100081948492172RUA DIAMANTINA QD-37 LT-21              GOIANIA                               GOJD. ANA LUCIA                   74315140000000000CONTRATO:19    PARCELA        53 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00064729                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233765
// 20012015000000030000000723820000091813               ALMIR ORLANDO                 100023287110144RUA ACARAJE Q.26 L.1                    GOIANIA                               GOJD. PLANALTO                    74333080000000000CONTRATO:10    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00091813                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233766
// 20012015000000030000000723830000066080               EDNA MISSENO PIRES            100088832651149RUA 228 QD-35 LT-25                     GOIANIA                               GOLESTE UNIVERSITARIO             74610140000000000CONTRATO:12    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00066080                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233767
// 20012015000000030000000723840000114119               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:14    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00114119                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233768
// 20012015000000030000000723850000121343               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:15    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00121343                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233769
// 02012015000000030000000723860000081594               JOAO LUIS MARQUES NETO        100022760881172RUA 22 QD.46 LT.07                      GOIANIRA                              GOPQ. DAS CAMELIAS                75370000000000000CONTRATO:18    PARCELA        14 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081594                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233770
// 11012015000000030000000723870000090305               MARIA DIVINA CANDIDA          100000322959870RUA 6 QD.04 LT.11                       BRAZABANTES                           GOSETOR ANANIAS                   75440000000000000CONTRATO:17    PARCELA        16 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00090305                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233771
// 10012015000000030000000723880000081615               REGIS CARDOSO TEIXEIRA        100053101553172RUA MONTE ALEGRE QD.D LT.02             GOIANIRA                              GOST OESTE                        75370000000000000CONTRATO:16    PARCELA        59 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081615                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233772
// 09012015000000030000000723890000080345               RODRIGO DE SOUZA REZENDE      100080603696104R.DAS TULIPAS QD.06, LT07               GOIANIRA                              GOREC.DAS AGUAS                   75370000000000000CONTRATO:6     PARCELA        55 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00080345                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233773
// 25012015000000030000000723900000084359               SEBASTIAO DONATO CIRILO       100042679923120RUA GETULIO VARGAS N 12 QD. K LT. 12A   INHUMAS                               GOSANTA MARIA                     75400000000000000CONTRATO:13    PARCELA        45 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00084359                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233774
// Apos vencimento MULTA 2 por cento  e MORA DIARIA 0,033                Nao receber apos 14 dias vencimento.Apos 14 dias pagar                na administradora acrescido honorario advocaticio.                                                                                                                                                                S
// ');
			
			
// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '2', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => '40010
// 30012015000000030000000723810000064729               ADRIANDO MAYKEL BONFIM        100081948492172RUA DIAMANTINA QD-37 LT-21              GOIANIA                               GOJD. ANA LUCIA                   74315140000000000CONTRATO:19    PARCELA        53 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00064729                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233765
// 20012015000000030000000723820000091813               ALMIR ORLANDO                 100023287110144RUA ACARAJE Q.26 L.1                    GOIANIA                               GOJD. PLANALTO                    74333080000000000CONTRATO:10    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00091813                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233766
// 20012015000000030000000723830000066080               EDNA MISSENO PIRES            100088832651149RUA 228 QD-35 LT-25                     GOIANIA                               GOLESTE UNIVERSITARIO             74610140000000000CONTRATO:12    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00066080                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233767
// 20012015000000030000000723840000114119               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:14    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00114119                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233768
// 20012015000000030000000723850000121343               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:15    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00121343                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233769
// 02012015000000030000000723860000081594               JOAO LUIS MARQUES NETO        100022760881172RUA 22 QD.46 LT.07                      GOIANIRA                              GOPQ. DAS CAMELIAS                75370000000000000CONTRATO:18    PARCELA        14 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081594                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233770
// 11012015000000030000000723870000090305               MARIA DIVINA CANDIDA          100000322959870RUA 6 QD.04 LT.11                       BRAZABANTES                           GOSETOR ANANIAS                   75440000000000000CONTRATO:17    PARCELA        16 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00090305                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233771
// 10012015000000030000000723880000081615               REGIS CARDOSO TEIXEIRA        100053101553172RUA MONTE ALEGRE QD.D LT.02             GOIANIRA                              GOST OESTE                        75370000000000000CONTRATO:16    PARCELA        59 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081615                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233772
// 09012015000000030000000723890000080345               RODRIGO DE SOUZA REZENDE      100080603696104R.DAS TULIPAS QD.06, LT07               GOIANIRA                              GOREC.DAS AGUAS                   75370000000000000CONTRATO:6     PARCELA        55 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00080345                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233773
// 25012015000000030000000723900000084359               SEBASTIAO DONATO CIRILO       100042679923120RUA GETULIO VARGAS N 12 QD. K LT. 12A   INHUMAS                               GOSANTA MARIA                     75400000000000000CONTRATO:13    PARCELA        45 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00084359                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233774
// Apos vencimento MULTA 2 por cento  e MORA DIARIA 0,033                Nao receber apos 14 dias vencimento.Apos 14 dias pagar                na administradora acrescido honorario advocaticio.                                                                                                                                                                S
// ');
		
// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '4', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => 
// '01REMESSA01COBRANÇA       33006000088943      V.I.A T3XTIL FRANCISCO JAMACY 756BANCOOBCED     1410160001029                                                                                                                                                                                                                                                                                               000001
// 1020960187900010033006000030430000000                         0000051104050100       0000000000000000    201011967/1    171116000000012396275633006010131016000009000002000020000000000000000000900000000000000000000000000211305730000117SUPERMERCADO CERRADO LTDA               AV.RIO VERDE 7908 QD78,LT AREA       JD PRESIDENTE  74353520GOIANIA        GO                                        00 000002
// 1020960187900010033006000030430000000                         0000051104200100       0000000000000000    20101871/1     111116000000004232675633006010141016000009000002000020000000000000000000900000000000000000000000000223329788000180GOMES COM. DE GÊNEROS ALIM. EIRELI ME   R:61,S/N,QDB12,LT13                  JD GOIAS       74810280GOIANIA        GO                                        00 000003
// 1020960187900010033006000030430000000                         0000051104370100       0000000000000000    201011968/1    111116000000009451975633006010141016000009000002000020000000000000000000900000000000000000000000000211769891000161EMPORIO PRATIKO COMERCIO DE ALIMENTOS LTRUA CORONEL COSME 151                ST LESTE VILA N74635030GOIANIA        GO                                        00 000004
// 1020960187900010033006000030430000000                         0000051104440200       0000000000000000    201011968/2    281116000000009451975633006010141016000009000002000020000000000000000000900000000000000000000000000211769891000161EMPORIO PRATIKO COMERCIO DE ALIMENTOS LTRUA CORONEL COSME 151                ST LESTE VILA N74635030GOIANIA        GO                                        00 000005
// 1020960187900010033006000030430000000                         0000051104120100       0000000000000000    201011969/1    231116000000008567075633006010141016000009000002000020000000000000000000900000000000000000000000000207099190000195PRATIKO COMÉRCIO DE  ALIMENTOS LTDA     SEXTA AVENIDA, 41 QD 90              ST LESTE UNIVER74603304GOIANIA        GO                                        00 000006
// 1020960187900010033006000030430000000                         0000051104510100       0000000000000000    201011970/1    031116000000014425675633006010141016000009000002000020000000000000000000900000000000000000000000000203007673000161COMERCIAL DE ALIMENTOS MONTEMAR LTDA    AV.CONTORNO 325                      CENTRO         74055140GOIANIA        GO                                        00 000007
// 1020960187900010033006000030430000000                         0000051104690100       0000000000000000    201011972/1    181116000000007346575633006010141016000009000002000020000000000000000000900000000000000000000000000206745741000188MAIS COMERCIO DE ALIMENTOS LTDA         R.CONQUISTA 213 QD115                ST LESTE UNIVER74615270GOIANIA        GO                                        00 000008
// 1020960187900010033006000030430000000                         0000051104760200       0000000000000000    201011972/2    281116000000007346575633006010141016000009000002000020000000000000000000900000000000000000000000000206745741000188MAIS COMERCIO DE ALIMENTOS LTDA         R.CONQUISTA 213 QD115                ST LESTE UNIVER74615270GOIANIA        GO                                        00 000009
// 1020960187900010033006000030430000000                         0000051104830100       0000000000000000    201011974/1    281116000000018209675633006010141016000009000002000020000000000000000000900000000000000000000000000273456287000132LEVE ALIMENTOS LTDA                     AV.ANHANGUERA 3261                   ST LESTE UNIVER74610001GOIANIA        GO                                        00 000010
// 1020960187900010033006000030430000000                         0000051104900100       0000000000000000    201011975/1    031116000000012370075633006010141016000009000002000020000000000000000000900000000000000000000000000202967327000162COMERCIAL DE ALIMENTOS ITATICO LTDA     R. 26C 2526                          ST GARAVELO    74932120AP DE GOIANIA  GO                                        00 000011
// 1020960187900010033006000030430000000                         0000051105090100       0000000000000000    201011976/1    031116000000020773075633006010141016000009000002000020000000000000000000900000000000000000000000000224447350000169CENTRO OESTE COMERCIAL DE ALIM.EIRELI   R;DO COMÉRCIO,QD02,LT01/23,S/N       ST CENTRO OESTE74550060GOIANIA        GO                                        00 000012
// 9                                                                                                                                                                                                                                                                                                                                                                                                         000013
// ');
		
		
// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '4', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => 
// '01REMESSA01COBRANÇA       33006000088943      V.I.A T3XTIL FRANCISCO JAMACY 756BANCOOBCED     1410160001029                                                                                                                                                                                                                                                                                               000001
// 1020960187900010033006000030430000000                         0000051104050100       0000000000000000    201061967/1    171116000000012396275633006010131016000009000002000020000000000000000000900000000000000000000000000211305730000117SUPERMERCADO CERRADO LTDA               AV.RIO VERDE 7908 QD78,LT AREA       JD PRESIDENTE  74353520GOIANIA        GO                                        00 000002
// 1020960187900010033006000030430000000                         0000051104200100       0000000000000000    20106871/1     111116000000004232675633006010141016000009000002000020000000000000000000900000000000000000000000000223329788000180GOMES COM. DE GÊNEROS ALIM. EIRELI ME   R:61,S/N,QDB12,LT13                  JD GOIAS       74810280GOIANIA        GO                                        00 000003
// 1020960187900010033006000030430000000                         0000051104370100       0000000000000000    201061968/1    111116000000009451975633006010141016000009000002000020000000000000000000900000000000000000000000000211769891000161EMPORIO PRATIKO COMERCIO DE ALIMENTOS LTRUA CORONEL COSME 151                ST LESTE VILA N74635030GOIANIA        GO                                        00 000004
// 1020960187900010033006000030430000000                         0000051104440200       0000000000000000    201061968/2    281116000000009451975633006010141016000009000002000020000000000000000000900000000000000000000000000211769891000161EMPORIO PRATIKO COMERCIO DE ALIMENTOS LTRUA CORONEL COSME 151                ST LESTE VILA N74635030GOIANIA        GO                                        00 000005
// 1020960187900010033006000030430000000                         0000051104120100       0000000000000000    201061969/1    231116000000008567075633006010141016000009000002000020000000000000000000900000000000000000000000000207099190000195PRATIKO COMÉRCIO DE  ALIMENTOS LTDA     SEXTA AVENIDA, 41 QD 90              ST LESTE UNIVER74603304GOIANIA        GO                                        00 000006
// 1020960187900010033006000030430000000                         0000051104510100       0000000000000000    201061970/1    031116000000014425675633006010141016000009000002000020000000000000000000900000000000000000000000000203007673000161COMERCIAL DE ALIMENTOS MONTEMAR LTDA    AV.CONTORNO 325                      CENTRO         74055140GOIANIA        GO                                        00 000007
// 1020960187900010033006000030430000000                         0000051104690100       0000000000000000    201061972/1    181116000000007346575633006010141016000009000002000020000000000000000000900000000000000000000000000206745741000188MAIS COMERCIO DE ALIMENTOS LTDA         R.CONQUISTA 213 QD115                ST LESTE UNIVER74615270GOIANIA        GO                                        00 000008
// 1020960187900010033006000030430000000                         0000051104760200       0000000000000000    201061972/2    281116000000007346575633006010141016000009000002000020000000000000000000900000000000000000000000000206745741000188MAIS COMERCIO DE ALIMENTOS LTDA         R.CONQUISTA 213 QD115                ST LESTE UNIVER74615270GOIANIA        GO                                        00 000009
// 1020960187900010033006000030430000000                         0000051104830100       0000000000000000    201061974/1    281116000000018209675633006010141016000009000002000020000000000000000000900000000000000000000000000273456287000132LEVE ALIMENTOS LTDA                     AV.ANHANGUERA 3261                   ST LESTE UNIVER74610001GOIANIA        GO                                        00 000010
// 1020960187900010033006000030430000000                         0000051104900100       0000000000000000    201061975/1    031116000000012370075633006010141016000009000002000020000000000000000000900000000000000000000000000202967327000162COMERCIAL DE ALIMENTOS ITATICO LTDA     R. 26C 2526                          ST GARAVELO    74932120AP DE GOIANIA  GO                                        00 000011
// 1020960187900010033006000030430000000                         0000051105090100       0000000000000000    201061976/1    031116000000020773075633006010141016000009000002000020000000000000000000900000000000000000000000000224447350000169CENTRO OESTE COMERCIAL DE ALIM.EIRELI   R;DO COMÉRCIO,QD02,LT01/23,S/N       ST CENTRO OESTE74550060GOIANIA        GO                                        00 000012
// 9                                                                                                                                                                                                                                                                                                                                                                                                         000013
// ');
		
// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '5', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => 
// '75600000         210454586000118                    0330060000000026212 TEKNOCOPY COPIADORAS E SISTEMASICOOB                                  11209201608211400000108100000                                                                     
// 75600011R01  040 2010454586000118                    0330060000000026212 TEKNOCOPY COPIADORAS E SISTEMA                                                                                000000451209201600000000                                 
// 7560001300013P 010330060000000026212 000002294501014     10 226758A          1910201600000000002550000000 02N08092016207102016000000000000200000000000000000000000000000000000000000000000000000000                         1000   090000000000 
// 7560001300014Q 012010836451000117MIRLENA MARIA VAZ - ME                  AV. MANOEL MONTEIRO, 390 QD. 15 LT. 21  JARDIM SALVADOR75380000TRINDADE       GO0                                                       000                            
// 7560001300015R 01000000000000000000000000100000000000000000000000207102016000000000000200                                                                                                              0000000000000000 000000000000  0         
// 7560001300016S 013PARA ATUALIZAR ESSE BOLETO ACESSE: www.s  PROTESTAR NO 05. DIA UTIL APOS VENCIDOCobrar juros de R$ 0,07 por dia de atrasCobrar Multa de R$ 5,10 após o venciment                                                              
// 75699999         000001000003000000                                                                                                                                                                                                             
// ');

// 		$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'FORMATO' => '3', 'NOME_ARQUIVO' => 'testeRemAtualizacaoNet.REM', 'REMESSA' => 
// '00003072400008958317102016
// 00050508010620102016000000000000000000000000000000000000000
// 00050508010620102012000000000000000000000000000000000000000
// 00050508010620102016000000000000000000000000000000000000000
// 00050508010620102015000000000000000000000000000000000000000
// 00050108010620102016000000000000000000000000000000000000000
// 00050502010620102016000000000000000000000000000000000000000
// 00050508010620102014000000000000000000000000000000000000000
// 00050505010620102016000000000000000000000000000000000000000');
		
		// 			$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'NOME_ARQUIVO' => 'testeWeb00005.REM', 'REMESSA' => '');
			
		$send_array = array('Autenticacao' => $Autenticacao, 'Cedente' => $Cedente, 'Dados' => $Dados);
			
		try {
			echo __LINE__ . '<br>';
			$result = $client->call('REMESSA_TITULO', array("Servico" => $send_array), '', '', false, true);
			print_rpre($client);
			echo __LINE__ . '<br><br>';
			echo $client->request;
			echo __LINE__ . '<br><br>';
		} catch (Exception $e) {
			skMsgWarning($e->getMessage());
		}
			
		//			print_rpre($result);
			
		print_rpre($result);
		echo __LINE__ . '<br>';
		if ($client->fault) {
			//$aRetorno['error'] = true;
			//$aRetorno['errorDetail']['message'] = print_rpre($result, true);
			skMsgError('<h2>Fault</h2>' . print_rpre($result, true));
		} else {
			$err = $client->getError();
			if ($err) {
				//$aRetorno['error'] = true;
				//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
				skMsgError('<h2>Error</h2><pre>' . $err . '</pre>');
			} else {
				if (!$result['SITUACAO'] == 'OK') {
					throw new Exception('Não foi possivel enviar a remessa de títulos, favor entrar em contato com suporte.');
				}
					
				if ($debug) {
					echo '<h2>Result</h2>';
					print_rpre($result);
				}
			}
		}
		if ($debug) {
			echo '<h2>Request</h2><pre>' . htmlspecialchars($client->request, ENT_QUOTES) . '</pre>';
			echo '<h2>Response</h2><pre>' . htmlspecialchars($client->response, ENT_QUOTES) . '</pre>';
			echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->debug_str, ENT_QUOTES) . '</pre>';
		}
		/**
		 *************************************************************************************************************
		 * FIM
		 *************************************************************************************************************
		 */
	}
	
	public function action_Webservice_Remesssa_Titulo_Formato_Netsuprema() {
		$query = db_query::Instanciar();
//		$p = array(
//				'KMF' => 'webservice', 
//				'KMP' => 'SYS', 
//				'SERVICO' => 'SYS::RetornoWebserviceCNAB400', 
//				'doctype' => 'json', 
//				'OPERADOR' => SYS::WebserviceConsultaFinanceiraOperador, 
//				'NIVEL_CONSULTA' => $iNivelConsulta, 
//				'CPF_CNPJ' => $sCpfCnpj);
//		
//		require_once KM::getDirRun() . '/pkg.SYS/webservice.php';
//		$aRetorno = SYS_webservice::chamadaRemota(static::WebserviceServidorPadraoConsultaFinanceira . http_build_query($p));
//		print_rpre($aRetorno);
//		die();
		
		/**
			 *************************************************************************************************************
			 * INÍCIO
			 *************************************************************************************************************
			 */
			require_once KM::getDirRun() . '../libs/nusoap/lib/nusoap.php';
// 			$url = 'http://192.168.7.205/geovany/aplication/webservice_remessa_titulo.php?wsdl';
//			$url = 'http://sig5.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
			$url = 'https://sig.netsuprema.com.br/col_cobrancateste/webservice_remessa_titulo.php?wsdl';
// 			$url = 'https://sig.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
//			$url = 'http://remessa.secovicred.netsuprema.com.br/';
//			$url = 'http://remessa.secovicred.netsuprema.com.br/?wsdl';
//			$url = 'https://sig.netsuprema.com.br/secovicred/webservice_remessa_titulo.php?wsdl';
			$client = new nusoap_client($url, 'wsdl');
			
			$err = $client->getError();
			if ($err) {
				//$aRetorno['error'] = true;
				//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
				echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			}
			$debug = KM::getDebug();
			
// 			$Autenticacao = array(
// 									'USUARIO' => 'CASA DE PANO', 
// 									'SENHA_MD5' => '74d88cfe6a273b85fde235f426f29845');
// 			$Cedente = array(
// 									'CODIGO_CEDENTE' => '8958', 
// 									'CODIGO_CEDENTE_DV' => '3', 
// 									'CONTA_CORRENTE' => '3072', 
// 									'CONTA_CORRENTE_DV' => '4');
			
			$Cedente = array(
									'CODIGO_CEDENTE' => '11436', 
									'CODIGO_CEDENTE_DV' => '7', 
									'CONTA_CORRENTE' => '1744', 
									'CONTA_CORRENTE_DV' => '2');
			$Autenticacao = array(
									'USUARIO' => 'CRECI_5_HOMOLOGACAO', 
									'SENHA_MD5' => '00470d2168526576c72bcf8b60c11297');
			
			$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'NOME_ARQUIVO' => 'testeWeb00004.REM', 'REMESSA' => '40010
30012015000000030000000723810000064729               ADRIANDO MAYKEL BONFIM        100081948492172RUA DIAMANTINA QD-37 LT-21              GOIANIA                               GOJD. ANA LUCIA                   74315140000000000CONTRATO:19    PARCELA        53 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00064729                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233765                                                                                                                                                                                         
20012015000000030000000723820000091813               ALMIR ORLANDO                 100023287110144RUA ACARAJE Q.26 L.1                    GOIANIA                               GOJD. PLANALTO                    74333080000000000CONTRATO:10    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00091813                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233766                                                                                                                                                                                         
20012015000000030000000723830000066080               EDNA MISSENO PIRES            100088832651149RUA 228 QD-35 LT-25                     GOIANIA                               GOLESTE UNIVERSITARIO             74610140000000000CONTRATO:12    PARCELA        54 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00066080                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233767                                                                                                                                                                                         
20012015000000030000000723840000114119               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:14    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00114119                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233768                                                                                                                                                                                         
20012015000000030000000723850000121343               GIULIANO GUIDI GOBBI          100085395510982RUA 4 Q. 5 LT. 14                       INHUMAS                               GOST SOL NASCENTE                 75400000000000000CONTRATO:15    PARCELA        44 .60                                  MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00121343                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233769                                                                                                                                                                                         
02012015000000030000000723860000081594               JOAO LUIS MARQUES NETO        100022760881172RUA 22 QD.46 LT.07                      GOIANIRA                              GOPQ. DAS CAMELIAS                75370000000000000CONTRATO:18    PARCELA        14 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081594                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233770                                                                                                                                                                                         
11012015000000030000000723870000090305               MARIA DIVINA CANDIDA          100000322959870RUA 6 QD.04 LT.11                       BRAZABANTES                           GOSETOR ANANIAS                   75440000000000000CONTRATO:17    PARCELA        16 .150                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00090305                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233771                                                                                                                                                                                         
10012015000000030000000723880000081615               REGIS CARDOSO TEIXEIRA        100053101553172RUA MONTE ALEGRE QD.D LT.02             GOIANIRA                              GOST OESTE                        75370000000000000CONTRATO:16    PARCELA        59 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00081615                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233772                                                                                                                                                                                         
09012015000000030000000723890000080345               RODRIGO DE SOUZA REZENDE      100080603696104R.DAS TULIPAS QD.06, LT07               GOIANIRA                              GOREC.DAS AGUAS                   75370000000000000CONTRATO:6     PARCELA        55 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00080345                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233773                                                                                                                                                                                         
25012015000000030000000723900000084359               SEBASTIAO DONATO CIRILO       100042679923120RUA GETULIO VARGAS N 12 QD. K LT. 12A   INHUMAS                               GOSANTA MARIA                     75400000000000000CONTRATO:13    PARCELA        45 .120                                 MULTA 2% E MORA DIARIA 0,033%.NAO RECEBER APOS 30 DIAS DO VENCTO.PAGAR NA ADMINISTRADORA.                                                                                                                                                                                                                                                           VALOR TOTAL DO DOCUMENTO                          00084359                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000                                                  00000000000000000233774                                                                                                                                                                                         
Apos vencimento MULTA 2 por cento  e MORA DIARIA 0,033                Nao receber apos 14 dias vencimento.Apos 14 dias pagar                na administradora acrescido honorario advocaticio.                                                                                                                                                                S                                                                                                                                                                                                       
');
			
// 			$Dados = array('EMAIL_NOTIFICACAO' => 'geovany@netsuprema.com.br', 'NOME_ARQUIVO' => 'testeWeb00005.REM', 'REMESSA' => '');
			
			$send_array = array('Autenticacao' => $Autenticacao, 'Cedente' => $Cedente, 'Dados' => $Dados);
			
			try {
				echo __LINE__ . '<br>';
				$result = $client->call('REMESSA_TITULO_FORMATO_NETSUPREMA', array("Servico" => $send_array), '', '', false, true);
				echo __LINE__ . '<br>';
			} catch (Exception $e) {
				skMsgWarning($e->getMessage());
			}
			
//			print_rpre($result);
			
			print_rpre($result);
			echo __LINE__ . '<br>';
			if ($client->fault) {
				//$aRetorno['error'] = true;
				//$aRetorno['errorDetail']['message'] = print_rpre($result, true);
				skMsgError('<h2>Fault</h2>' . print_rpre($result, true));
			} else {
				$err = $client->getError();
				if ($err) {
					//$aRetorno['error'] = true;
					//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
					skMsgError('<h2>Error</h2><pre>' . $err . '</pre>');
				} else {
					if (!$result['SITUACAO'] == 'OK') {
						throw new Exception('Não foi possivel enviar a remessa de títulos, favor entrar em contato com suporte.');
					}
					
					if ($debug) {
						echo '<h2>Result</h2>';
						print_rpre($result);
					}
				}
			}
			if ($debug) {
				echo '<h2>Request</h2><pre>' . htmlspecialchars($client->request, ENT_QUOTES) . '</pre>';
				echo '<h2>Response</h2><pre>' . htmlspecialchars($client->response, ENT_QUOTES) . '</pre>';
				echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->debug_str, ENT_QUOTES) . '</pre>';
			}
			/**
			 *************************************************************************************************************
			 * FIM
			 *************************************************************************************************************
			 */
	}
	
	public function action_Webservice_Retorno() {
		$query = db_query::Instanciar();
//		$p = array(
//				'KMF' => 'webservice', 
//				'KMP' => 'SYS', 
//				'SERVICO' => 'SYS::RetornoWebserviceCNAB400', 
//				'doctype' => 'json', 
//				'OPERADOR' => SYS::WebserviceConsultaFinanceiraOperador, 
//				'NIVEL_CONSULTA' => $iNivelConsulta, 
//				'CPF_CNPJ' => $sCpfCnpj);
//		
//		require_once KM::getDirRun() . '/pkg.SYS/webservice.php';
//		$aRetorno = SYS_webservice::chamadaRemota(static::WebserviceServidorPadraoConsultaFinanceira . http_build_query($p));
//		print_rpre($aRetorno);
//		die();
		
		/**
			 *************************************************************************************************************
			 * INÍCIO
			 *************************************************************************************************************
			 */
			require_once KM::getDirRun() . '../libs/nusoap/lib/nusoap.php';
//			$url = 'http://192.168.7.205/geovany/aplication/webservice_retorno.php?wsdl';
// 			$url = 'https://sig.netsuprema.com.br/secovicred/webservice_retorno.php?wsdl';
			$url = 'https://sig.netsuprema.com.br/credisaude/webservice_retorno.php?wsdl';
			$client = new nusoap_client($url, 'wsdl');
			
			$err = $client->getError();
			if ($err) {
				//$aRetorno['error'] = true;
				//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
				echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
			}
			$debug = KM::getDebug();
			
// 			$Autenticacao = array(
// 									'USUARIO' => 'df3999dedbe46d795e40dcf60741bc0c', 
// //									'USUARIO' => '20b6eab871e09b42aa5322c5ef9c8273', 
// 									'CODIGO_CEDENTE' => '371', 
// 									'CONTA_CORRENTE' => '207', 
// 									'DTRETORNO' => '05/06/2015');
			$Autenticacao = array(
									'USUARIO' => '889bd9e3d65a2d477b58304cb18a5cb9', 
									'CODIGO_CEDENTE' => '2517', 
									'CONTA_CORRENTE' => '11340', 
									'DTRETORNO' => '12/01/2017');
			
			$send_array = array('Autenticacao' => $Autenticacao);
			
			try {
				$result = $client->call('RETORNO_CNAB400_01', array("Servico" => $send_array), '', '', false, true);
			} catch (Exception $e) {
				skMsgWarning($e->getMessage());
			}
			
			print_rpre($result);
			
			if ($client->fault) {
				//$aRetorno['error'] = true;
				//$aRetorno['errorDetail']['message'] = print_rpre($result, true);
				skMsgError('<h2>Fault</h2>' . print_rpre($result, true));
			} else {
				$err = $client->getError();
				if ($err) {
					//$aRetorno['error'] = true;
					//$aRetorno['errorDetail']['message'] = print_rpre($err, true);
					skMsgError('<h2>Error</h2><pre>' . $err . '</pre>');
				} else {
					if (!$result['SITUACAO'] == 'OK') {
						throw new Exception('Não foi possivel efetuar a consulta do retorno, favor entrar em contato com suporte.');
					}
					
					if ($debug) {
						echo '<h2>Result</h2>';
						print_rpre($result);
					}
				}
			}
			if ($debug) {
				echo '<h2>Request</h2><pre>' . htmlspecialchars($client->request, ENT_QUOTES) . '</pre>';
				echo '<h2>Response</h2><pre>' . htmlspecialchars($client->response, ENT_QUOTES) . '</pre>';
				echo '<h2>Debug</h2><pre>' . htmlspecialchars($client->debug_str, ENT_QUOTES) . '</pre>';
			}
			/**
			 *************************************************************************************************************
			 * FIM
			 *************************************************************************************************************
			 */
	}
	
	public function action_Visualizar_Boletos() {
		$js = skWindowOpen('http://192.168.7.205/geovany/aplication/index.php?KMP=COL&KMF=reemissao&KMA=action_Imprimir_Cedente_Nosso_numero&CODIGO_CEDENTE=130&NOSSO_NUMERO=1,12,14,171,1,2,3,4,5,588,899,5444&doctype=nomenu');
		sk_onLoad($js);
		$this->Initialization();
	}

	function action_Atualizar() {
		$query = db_query::Instanciar();
		$query2 = db_query::Instanciar();
		$totalAtualizado = 0;
		
		$sql = 'select * from imob_empreendimento where not iptu is null or not celg is null or not saneago is null order by idempreendimento';
		$query->open($sql);
		while ($query->fetch()) {
			
			if ($query->IPTU) {
				$aIptu = explode("\n", $query->IPTU);
				foreach ($aIptu as $iptu) {
					$query2->openTable('IMOB', 'EMPREENDIMENTO_COBRANCA');
					$query2->IDEMPREENDIMENTO = $query->IDEMPREENDIMENTO;
					$query2->TIPO = IMOB::EmpreendimentoCobrancaTipoIptu;
					$query2->CODIGO_COBRANCA = $iptu;
					$query2->OBSERVACAO = $query->OBSERVACAO_COBRANCA;
					$query2->save(true);
				}
			}
			
			if ($query->SANEAGO) {
				$aAgua = explode("\n", $query->SANEAGO);
				foreach ($aAgua as $agua) {
					$query2->openTable('IMOB', 'EMPREENDIMENTO_COBRANCA');
					$query2->IDEMPREENDIMENTO = $query->IDEMPREENDIMENTO;
					$query2->TIPO = IMOB::EmpreendimentoCobrancaTipoAgua;
					$query2->CODIGO_COBRANCA = $agua;
					$query2->OBSERVACAO = $query->OBSERVACAO_COBRANCA;
					$query2->save(true);
				}
			}
			
			if ($query->CELG) {
				$aEnergia = explode("\n", $query->CELG);
				foreach ($aEnergia as $energia) {
					$query2->openTable('IMOB', 'EMPREENDIMENTO_COBRANCA');
					$query2->IDEMPREENDIMENTO = $query->IDEMPREENDIMENTO;
					$query2->TIPO = IMOB::EmpreendimentoCobrancaTipoEnergia;
					$query2->CODIGO_COBRANCA = $energia;
					$query2->OBSERVACAO = $query->OBSERVACAO_COBRANCA;
					$query2->save(true);
				}
			}
			$totalAtualizado++;
		}
		
		skMsgNotice('Foram atualizados ' . $totalAtualizado . ' registros de imóveis para tabela de cobrança (IPTU, AGUA, ENERGIA)');
	}
}
?>