package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{
    @Override
    public void validar(AgendarConsultaDTO datos){
        var ahora = LocalDateTime.now();

        var horaDeConsulta = datos.fecha();

        var diferenciaDe30Minutos = Duration.between(ahora, horaDeConsulta).toMinutes()<30;
        if (diferenciaDe30Minutos){
            throw new ValidationException("La consulta no puede ser agendada con menos de 30 minutos de anticipacion");
        }
    }
}
