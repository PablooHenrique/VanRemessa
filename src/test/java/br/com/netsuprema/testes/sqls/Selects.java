package br.com.netsuprema.testes.sqls;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.netsuprema.dominio.Cedente;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.CedenteRepository;

public class Selects {
	
	private SessionFactory factory = Application.getInstance().getSessionFactory();
	
	public void listarDiretorios(){
		
		
	}
	
	public void listarCedentes(){
		Session session = factory.openSession();
		List<Cedente> cedentes = new CedenteRepository(session).listar();
		
		if (cedentes.isEmpty()) {
			System.out.println("Cedentes Vazio");
		}else{
			cedentes.stream().forEach(x-> System.out.println("ID : " + x.getId() + " Nome : "+x.getNome() +" Dir: "+ x.getDiretorioPadrao()));
		}
		
		session.close();
	}
	
	public void listarContas(){
		Session session = factory.openSession();
		List<Cedente> cedentes = new CedenteRepository(session).listar();
		
		if (cedentes.isEmpty()) {
			System.out.println("Contas Vazio");
		}else{
			for (Cedente cedente : cedentes) {
				cedente.getContas().stream().forEach(x -> System.out.println("Cedente: " +cedente.getNome()+ " Conta: " + x.getNumeroConta() +" dir: "+x.getDiretorio()));
			}
		}
		
		session.close();
	}
	
	public static void main(String[] args) {
		new Selects().listarCedentes();
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
}
