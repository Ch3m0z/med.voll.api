package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.AgendarConsultaDTO;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository medicoRepository;
    @Override
    public void validar(AgendarConsultaDTO datos){
        if (datos.idMedico()==null){
            TODO: return ;
        }

        var medicoActivo=medicoRepository.findActivoById(datos.idMedico());

        if (!medicoActivo){
            throw new ValidationException("El medico seleccionado no puede agendar una cita debido a que se encuentra inactivo");
        }
    }
}
