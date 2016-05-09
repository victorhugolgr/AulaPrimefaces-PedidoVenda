package br.com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.algaworks.pedidovenda.model.Grupo;
import br.com.algaworks.pedidovenda.repository.GrupoRepository;
import br.com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	private GrupoRepository grupoRepository;

	public GrupoConverter() {
		grupoRepository = CDIServiceLocator.getBean(GrupoRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet, String value) {
		Grupo retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = grupoRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Grupo grupo = ((Grupo) value);
			if (grupo.getId() != null) {
				return grupo.getId().toString();
			}
		}

		return "";
	}

}