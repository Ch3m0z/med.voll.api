package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.AgendarConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{
    @Override
    public void validar(AgendarConsultaDTO datos) {
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());

        var antesDeApertura = datos.fecha().getHour() < 7;

        var despuesDeCierre = datos.fecha().getHour() > 19;

        if (domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException("El horario de funcionamiento de funcionamiento de la clinica es de lunes a sábado, de 07:00 a 19:00 horas");
        }
    }
}
