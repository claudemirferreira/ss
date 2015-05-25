package br.com.ss.alevino.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import br.com.ss.alevino.util.ReflectionsUtil;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -1094782059469169706L;

	@Transient
	private Long id = null;

	public AbstractEntity() {
	}

	@PostLoad
	void loadId() {
		try {
			Field field = ReflectionsUtil.findAnnotatedFields(this.getClass(),
					Id.class)[0];
			field.setAccessible(true);
			id = new Long(field.get(this).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isPersistent() {
		return getId() != null;
	}

	/** HashCode. */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id));
		return result;
	}

	/** Equals. */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		final AbstractEntity other = (AbstractEntity) obj;

		if ((this.id == null) || (other.id == null)) {
			return false;
		}

		if (this.id.intValue() != other.id.intValue()) {
			return false;
		}

		return true;
	}

	public Long getId() {
		return id;
	}

}