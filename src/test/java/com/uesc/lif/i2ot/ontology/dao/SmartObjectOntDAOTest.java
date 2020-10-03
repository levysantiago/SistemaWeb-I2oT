package com.uesc.lif.i2ot.ontology.dao;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.junit.Ignore;
import org.junit.Test;

import com.uesc.lif.i2ot.util.OntologyManager;

public class SmartObjectOntDAOTest {
	@Test
	@Ignore
	public void allowed() {
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		
		smartObjectOntDAO.beginTransaction();
		
		boolean resp = smartObjectOntDAO.allowedPlace(11L);
		boolean resp2 = smartObjectOntDAO.allowedPerson(11L);
		boolean resp3 = smartObjectOntDAO.allowedMaterial(11L);
		
		smartObjectOntDAO.endTransaction();
		
		System.out.println("Resposta: "+resp);
		System.out.println("Resposta: "+resp2);
		System.out.println("Resposta: "+resp3);
	}
	
	@Test
	@Ignore
	public void getTag() {
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		
		smartObjectOntDAO.beginTransaction();
		String tag = smartObjectOntDAO.getDeviceCode(11l);
		smartObjectOntDAO.endTransaction();
		System.out.println(tag);
	}
	
	@Test
	@Ignore
	public void writing() {
		SmartObjectOntDAO smartObjectOntDAO = new SmartObjectOntDAO();
		smartObjectOntDAO.beginTransaction();
		if(!smartObjectOntDAO.setDeviceCode(11l, "2f2f2f5f")) {
			System.out.println("Objeto não encontrado.");
		}
		System.out.println(smartObjectOntDAO.commitChanges());
	}
	
	@Test
	@Ignore
	public void getIndividualFather() {
		SmartObjectOntDAO dao = new SmartObjectOntDAO();
		dao.beginTransaction();
		String father = dao.getIndividualFather("stud1");
		System.out.println(father);
		dao.endTransaction();
	}
	
	@Test
	@Ignore
	public void movedBySome() {
		SmartObjectOntDAO dao = new SmartObjectOntDAO();
		dao.beginTransaction();
		String mv = dao.movedBySome(1l);
		System.out.println(mv);
		dao.endTransaction();
	}
	
	@Test
	public void individualFather() {
		SmartObjectOntDAO dao = new SmartObjectOntDAO();
		
		dao.beginTransaction();
		
		/*Individual i = dao.getIndividual("func1");
		ExtendedIterator<OntClass> l = i.listOntClasses(true);
		while(l.hasNext()) {
			String name = l.next().getLocalName();
			if(!name.equals("NamedIndividual")) {
				System.out.println(name);
				break;
			}
			
		}*/
		dao.endTransaction();
	}
	
	/*PRÓXIMOS PASSOS:
	 * Fazer o método DAO para pegar o status do material *
	 * Fazer o método Service que retorna o status do objeto inteligente
	 * Testar o método Service
	 * 
	 * Organizar/Criar/Atualizar métodos para criar um objeto inteligente na ontologia.
	 * Para isso, você terá que fazer com que na hora de salvar o objeto, ele retorne o mesmo objeto com o id que foi registrado.
	 * Depois de recebido, o controle vai chamar a ontologia para que o objeto seja cadastrado lá também.
	 * O cadastro na ontologia não precisa ter todos os atributos do objeto. Veja aí quais são os mais importantes para a ontologia.
	 * */
}
