package br.com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.algaworks.pedidovenda.model.Categoria;
import br.com.algaworks.pedidovenda.repository.CategoriaRepository;
import br.com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

	private CategoriaRepository categoriaRepository;

	public CategoriaConverter() {
		categoriaRepository = CDIServiceLocator.getBean(CategoriaRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		Categoria retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = categoriaRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Categoria categoria = ((Categoria) value);
			if (categoria.getId() != null) {
				return categoria.getId().toString();
			}
		}

		return "";
	}

}