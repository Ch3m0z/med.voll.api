package med.voll.api.domain.consulta;

import jakarta.transaction.Transactional;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class AgendarConsultaService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public void agendar(AgendarConsultaDTO datos) {
        if (pacienteRepository.findById(datos.idPaciente()).isPresent())
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado");

        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico()))
            throw new ValidacionDeIntegridad("Este id para el medico no fue encontrado");


        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var medico = seleccionarMedico(datos);

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(AgendarConsultaDTO datos) {
        if (datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }

        if (datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("Debe seleccionar la especialidad del medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
