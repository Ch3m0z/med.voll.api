package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.AgendarConsultaDTO;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository pacienteRepository;
    @Override
    public void validar(AgendarConsultaDTO datos){
        if (datos.idPaciente()==null){
            TODO: return;
        }

        var pacienteActivo=pacienteRepository.findActivoById(datos.idPaciente());

        if (!pacienteActivo){
            throw new ValidationException("El paciente seleccionado no puede agendar una cita debido a que se encuentra inactivo");
        }
    }
}
