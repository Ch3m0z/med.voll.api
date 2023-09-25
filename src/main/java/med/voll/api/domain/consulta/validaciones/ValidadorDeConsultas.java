package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.AgendarConsultaDTO;

public interface ValidadorDeConsultas {
    void validar(AgendarConsultaDTO datos);

}
