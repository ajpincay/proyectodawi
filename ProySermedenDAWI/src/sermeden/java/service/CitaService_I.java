package sermeden.java.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sermeden.java.bean.CitaDTO;
import sermeden.java.bean.UsuarioDTO;


public interface CitaService_I {

	ArrayList<HashMap<String, Object>> listadoTurnos();

	List<UsuarioDTO> cargarMedicos();

	List<HashMap<String, Object>> cargarHorasDispXFechaXMedico(String medicoCita,
			String fechaCita);

	List<HashMap<String, Object>> cargarHorariosCitasHechas(String medicoCita, String fechaCita);

	List<HashMap<String, Object>> cargarSemanaCita(CitaDTO cita);

	int registrarNuevaCita(CitaDTO cita) throws Exception;

}
