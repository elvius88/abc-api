package py.com.roshka.abcconsulta.api.services;

import py.com.roshka.abcconsulta.api.domains.ConsultaAbcResponse;

import java.util.List;

public interface IConsultaAbcService {
    List<ConsultaAbcResponse> findAllFiltered(String filter, boolean format);
}
