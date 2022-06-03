package mx.consultoria.juridica.especializada.repository;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.NonContextualLobCreator;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import mx.consultoria.juridica.especializada.dto.HistoricoDocumentosDTO;
import mx.consultoria.juridica.especializada.entities.HistoricoCasos;
import mx.consultoria.juridica.especializada.entities.UploadFile;


@Repository
@Transactional(propagation = Propagation.REQUIRED) //-> la anotación @Transactional se usa para decirle a Spring que inserte automáticamente el código de administración de transacciones en el código de bytes.
public class RepositorioImplHistoricoDocumentos implements RepositorioDaoHistoricoDocumentos{
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	 @Autowired
	 private SessionFactory sessionFactory; // Este es un objeto de hibernate para poder hacer el insertar en DB

	 public RepositorioImplHistoricoDocumentos() {         
	    }
	 
	 public RepositorioImplHistoricoDocumentos(SessionFactory sessionFactory) { // este metodo recibe el objeto de esta clase para poder hacer uso de la sessionFactory		 
	        this.sessionFactory = sessionFactory;
	    }
	 
	 
	
	@Autowired
	private DataSource dataSource;  // ESTE TIENE LA CONEXIÓN A BASE DE DATOS
	private JdbcTemplate jdbcTemplate=new JdbcTemplate();
	
	
	
	
//	ESTE METODO ES COMO LO MANEJAMOS CREANDO APARTE UNA CLASE -> Mapper ES EL MISMO DE LA LINEA #74
//	@Override
//	public List<HistoricoDocumentosDTO> listHistoricoDeCasosAntiguos() {
//		jdbcTemplate.setDataSource(dataSource);
//		return jdbcTemplate.query("SELECT * FROM HISTORICO_CASOS", new RamoMapper<HistoricoDocumentosDTO>());
//	}
	
	
//	ESTE METODO ES EL MISMO PERO SIN HACER APARTE EN OTRA CLASE EL Mapper SI NO AUI MISMO
	
 	@SuppressWarnings("unchecked") // Esta anotacion es para -> @Suprimir advertencias (lineas amarillas - PUEDEN COMENTAR ESTA LINEA PARA QUE VEAN A QUE ME REFIERO)
	@Override
    public List<HistoricoDocumentosDTO> listHistoricoDeCasosAntiguos() {
		jdbcTemplate.setDataSource(dataSource);
		   StringBuilder sb = new StringBuilder("SELECT * FROM HISTORICO_CASOS");

		   return (List<HistoricoDocumentosDTO>) jdbcTemplate.query(sb.toString(), new Object[] {}, new RowMapper() {
		         @Override
		         public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		        	 HistoricoDocumentosDTO datos = new HistoricoDocumentosDTO();
				   
		           datos.setIdHistoricoCasos(rs.getInt("ID_HISTORICO_CASOS"));
				   datos.setNombreDocumento(rs.getString("NOMBRE_ARCHIVO"));
				   datos.setFechaRegistro(rs.getString("FECHA_REGISTRO"));

				   return datos;
			   }
		   });
	   }
	

	
	
//	ESTE METODO EL INSERTO A LA DB SE HACE POR MEDIO DE HIBERNATE
	@Override
	public int insertarDocumentosCaso(HistoricoCasos historicoCasos) {
		
		sessionFactory.getCurrentSession().save(historicoCasos);
		return 1; // Para rapido returnamos un 1 eso kiere decir que si llegamos hasta esta clase es por que ya esta todo bien la informecion
//		que se esta recolectando desde la vista entonces solo insertamos y le decimos con el # -> 1 que se inserto de forma correcta.
	}


/*
 * 1.- Spring MVC = La arquitectura (estructura de como esta hecho el sistema)
 * 2.- Consultas : donde van las consultas (en el repositorio) {
 *     * JDBCTEMPLATE (select * from......)
 *     * Hibernate (read(), create o save(), update(), delete(), saveOrUpdate)
 *     * JPA (read(), finById(id), findAll())
 * } 
 *  HIBERNATE Y JPA siempre te obliga a trabajar con entities
 */ 
	
	
	
//	-------------------- Obtener fecha y hora actual -----------------------ESTA CONSULTA LA PUENDE EJECUTAR *PARA QUE PRUEBEN QUE LES REGRESA LA CONSULTA*
//	ESTA CAMPO EN LA BASE DE DATOS ESTA COMO -> FECHA_REGISTRO - TIMESTAMP(6), hacemos esta consulta para que nos extraiga la fecha de forma mas rapida
	   @Transactional(propagation = Propagation.REQUIRED)
		public Date obtenerMomentoActual() {
			final Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery("select sysdate param from dual");
			query.addScalar("param", TimestampType.INSTANCE);
			Date ahora = (Date) query.uniqueResult();
			return ahora;
		}
	   
	   
//		-------------------- Obtener/ consultar la secuencia de la tabla  ----------------------- ESTA CONSULTA LA PUENDE EJECUTAR *CON SU SECUENCIA PARA QUE PRUEBEN QUE LES REGRESA LA CONSULTA*
	   
// COMO EL METODO DE INSERTAR LO ESTAMOS HACIENDO CON HIBERNATE YA NO NOS SIRVE EL TRIGGER YA NO SE PUEDE TOMAR DE FORMA AUTOMATICA CUANDO SE INTENTA INSERTAR EN LA BD
// CON HIBERNATE ESTAMOS OBLIGADOS A MANDAR TODOS LOS PARAMETROS DE LA BASE DE DATOS, EN NUESTRA TABLA DE -> HISTORICO_CASOS TENEMOS 5 CAMPOS Y *TENEMOS* QUE MANDAR LOS 5 DATOS
// ONCLUYENDO EL ID_DE_LA_TABLA X ESO ES QUE HACEMOS ESTA CONSULTA DONDE EXTRAEMOS LA SECUENCIA PARA MANDAR EL ID DE LA TABLA.
	   
	   public int obtenValorSecuenciaTableHistorico() {
			String sqlSequence = "SELECT SEQ_HISTORICO_CASOS.NEXTVAL AS SECUENCIAUSER FROM DUAL";
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query = session.createSQLQuery(sqlSequence);
			List result = query.list();
			return ((BigDecimal) result.get(0)).intValue();
		}
	   
	   
	   	  

//EN ESTE METIDO CONSULTAMOS EL NOMBRE Y EL DOCUMENTO, IGUAL HACEMOS EL Mapper AKI MISMO SEGUIDO PARA NO HACER OTRA CLASE APARTE DEL PURO Mapper

	@Override
	public HistoricoCasos obtenerDocumentoPorIdTablaHistoricoEntity(HistoricoDocumentosDTO historicoDocumentosDTO) {

		Object[] params = new Object[1];
		params[0] = historicoDocumentosDTO.getIdHistoricoCasos();

		final StringBuilder query = new StringBuilder(" SELECT NOMBRE_ARCHIVO, DOCUMENTO ")
				.append(" from HISTORICO_CASOS  ").append(" WHERE ID_HISTORICO_CASOS = ? ");

		return this.jdbcTemplate.queryForObject(query.toString(), new HistoricoRowMapper(), params);
	}

	class HistoricoRowMapper implements RowMapper<HistoricoCasos> {

		public HistoricoCasos mapRow(ResultSet rs, int rowNum) throws SQLException {
			HistoricoCasos his = new HistoricoCasos();
			his.setNombreArchivo(rs.getString("NOMBRE_ARCHIVO"));
			his.setDocumentoBy(rs.getBytes("DOCUMENTO"));

			return his;
		}
	}

	   
	   


	@Override
	public int eliminarInformacion(HistoricoDocumentosDTO identificador) {
		jdbcTemplate.setDataSource(dataSource);		         
		Object [] idHistorico = {identificador.getIdHistoricoCasos()};      
		int[] types = {Types.INTEGER}; 						 
		return jdbcTemplate.update("delete from HISTORICO_CASOS where ID_HISTORICO_CASOS = ?",idHistorico,types); // Query
	}




}
