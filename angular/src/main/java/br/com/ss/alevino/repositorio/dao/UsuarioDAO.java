package br.com.ss.alevino.repositorio.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ss.alevino.model.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario> {
	public UsuarioDAO() {
		super(Usuario.class);
	}

	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	/**
	 * metodo utilizada para validar login e senha do usuario
	 * 
	 * @param login
	 * @param senha
	 * @return usuario autenticado
	 */
	public Usuario autenticar(String login, String senha) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).where(cb.equal(Usuario.get(Usuario_.email),
		// email));
		criteria.select(Usuario).where(cb.equal(Usuario.get("login"), login));
		criteria.select(Usuario).where(cb.equal(Usuario.get("senha"), senha));

		Usuario usuario = new Usuario();

		try {
			return em.createQuery(criteria).getSingleResult();

		} catch (NoResultException ne) {
			System.out.println("Login ou senha invalida");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ocorreu um erro");
		}

		return null;
	}

	public Usuario findByNome(String nome) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).where(cb.equal(Usuario.get(Usuario_.email),
		// email));
		criteria.select(Usuario).where(cb.equal(Usuario.get("nome"), nome));
		return em.createQuery(criteria).getSingleResult();
	}

	public List<Usuario> findAllOrderedByNome() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = cb.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteria.from(Usuario.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(Usuario).orderBy(cb.asc(Usuario.get(Usuario_.nome)));
		criteria.select(Usuario).orderBy(cb.asc(Usuario.get("nome")));
		return em.createQuery(criteria).getResultList();
	}
}