package servicios;


import dao.DAOFactory;
import dao.SolicitudExoneracionDAO;

public class SolicitudExoneracionDaoService implements SolicitudExoneracionService {
	private DAOFactory fabrica = null;
	private SolicitudExoneracionDAO exoneraciondao;
	

	public SolicitudExoneracionDaoService(int jpa) {
		// TODO Auto-generated constructor stub
		fabrica = DAOFactory.getDAOFactory(jpa);
        this.exoneraciondao = fabrica.getSolicitudExoneracionDAO();
	}

	@Override
	public boolean CumpleCalendarioExoneracion(int codigoCalendario)
			throws Exception {
		// TODO Auto-generated method stub
		return exoneraciondao.CumpleCalendarioExoneracion(codigoCalendario);
	}



}