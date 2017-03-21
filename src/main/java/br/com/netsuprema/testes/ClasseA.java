package br.com.netsuprema.testes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.com.netsuprema.dominio.Cedente;
import br.com.netsuprema.dominio.remessa.LogEnvioRemessa;
import br.com.netsuprema.dominio.remessa.Remessa;
import br.com.netsuprema.repository.Application;
import br.com.netsuprema.repository.RemessaRepository;

@Entity(name="classea")
public class ClasseA {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = false)
    @JoinColumn(name="classB_id", nullable=false)
	private ClasseB classB;
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public ClasseB getClassB() {
		return classB;
	}


	public void setClassB(ClasseB classB) {
		this.classB = classB;
	}


	public static void main(String[] args) {
		Remessa remessa = new Remessa();
		remessa.setLog(new LogEnvioRemessa());
		remessa.setCedente(new Cedente());
		
		SessionFactory factory = Application.getInstance().getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(remessa);
		transaction.commit();
		
		List<Remessa> listar = new RemessaRepository(session).listar();
		System.out.println("--");
		
	}
}
