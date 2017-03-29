package br.com.netsuprema.testes.parametrosservico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.netsuprema.dominio.cedente.Cedente;
import br.com.netsuprema.dominio.cedente.Conta;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;
import br.com.netsuprema.repository.RemessaRepository;
import br.com.netsuprema.service.FileService;
import br.com.netsuprema.service.RemessasDeTitulosService;
import br.com.netsuprema.service.cedente.CedenteService;

public class TesteCedente {
	
	@Before
	public void setDown(){
		limparBase();
	}
	
	@After
	public void setUp(){
		limparBase();
	}
	
	@Test
	public void testeInserirCedente(){
		try {
			Cedente cedente = criarCedente();
			CedenteService service = new CedenteService();
			service.criarCedente(cedente);
			Assert.assertEquals(true, !(service.listarPorCodigo(cedente.getCodigo()) == null));
		} catch (Exception e) {
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testeDiretoriosForamCriados(){
		try {
			Cedente cedente = criarCedente();
			CedenteService service = new CedenteService();
			service.criarCedente(cedente);
			
			Assert.assertEquals(true, new FileService().diretorioExists(cedente.getDiretorioPadrao()));
		} catch (Exception e) {
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testeRemoverCedenteComRefenciaParaConta(){
		try {
			Cedente cedente = criarCedente();
			CedenteService service = new CedenteService();
			service.criarCedente(cedente);
			
			service.excluirCedentePorCodigo(cedente.getCodigo());
			Assert.assertEquals(true, (service.listarPorCodigo(cedente.getCodigo()) == null));
		} catch (Exception e) {
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	public void testeRemoverCedenteComDadosRemessa(){
		try {
			Cedente cedente = criarCedente();
			CedenteService service = new CedenteService();
			service.criarCedente(cedente);
			
			FileService fileService = new FileService();
			
			cedente.getContas().stream().forEach(x->{
				try {
					fileService.criarArquivo(x.getDiretorio()+"/"+x.getNumeroConta()+".txt", String.valueOf(x.getNumeroConta()));
				} catch (IOException e) {
					Assert.assertEquals(true, false);
				}
			});
			
			new RemessasDeTitulosService().processar();
			service.excluirCedentePorCodigo(cedente.getCodigo());
			Assert.assertEquals(true, (service.listarPorCodigo(cedente.getCodigo()) == null));
		} catch (Exception e) {
			Assert.assertEquals(true, false);
		}
	}
	
	private void limparBase() {
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		new RemessaRepository(session).limparTabela();
		new CedenteRepository(session).limparTabela();
		transaction.commit();
	}

	private Cedente criarCedente() {
		
		Cedente cedente = new Cedente();
		cedente.setCodigo(123456);
		cedente.setContas(criarContas());
		cedente.setDigitoVerificador(1);
		cedente.setNome("Pablo Henrique Reis Carvalho");
		
		return cedente;
	}
	
	private List<Conta> criarContas(){
		List<Conta> contas = new ArrayList<Conta>();
		contas.add(criarConta(111111));
		contas.add(criarConta(111112));
		return contas;
	}

	private Conta criarConta(int numeroConta) {
		Conta conta = new Conta();
		conta.setDigitoVerificador(1);
		conta.setNumeroConta(numeroConta);
		return conta;
	}
}
