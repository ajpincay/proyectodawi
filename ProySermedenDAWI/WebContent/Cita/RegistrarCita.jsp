<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registro de Nueva Cita</title>
<sj:head jqueryui="true" compressed="false"/>
</head>
<body>

<h4 align="center">Nueva Cita</h4>

<form id="formNuevaCita" action="RegistrarNuevaCitaAction">
	<table align="center">
	<tr>
			 <td>
			 DNI de Paciente: 
			 </td>
			 <td>
			 	<s:textfield name="dnibuscado" required="true"></s:textfield>
			  </td>	
		</tr>
		
		<tr>
			<td colspan="2">
				<jsp:include page="/Cita/Turnos-result.jsp"></jsp:include>
			</td>
		</tr>
		<fieldset>
		<tr>
			<td>
				Elija Medico Tratante:
			</td>
			<td>
				<div>
					<s:url id="opcionesUrl" action="listarMedicosAction"/>
					<sj:select	
						href="%{opcionesUrl}"
						id="medicoCita"
						name="medicoCita"
						onChangeTopics="reloadFechas"
						list="listamedicos"
						listKey="idPersona"
						listValue="idPersona"
						headerKey="-1"
						headerValue="--Seleccione un M�dico Tratante--"/>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				Elija Fecha para Cita:
			</td>
			<td>
			    <div>
					<sj:select 
						href="%{opcionesUrl}"
						id="fechaCita"
						formIds="formNuevaCita"
						reloadTopics="reloadFechas"
						onChangeTopics="reloadHoras"
						name="fechaCita"
						list="listafechasxmedico"
						listKey="idPersona"
						listValue="hora"
						headerKey="-1"
						headerValue="- Seleccione una fechadisponible -"/>
				
				</div>
			</td>
		</tr>
		<tr>
			<td>
				Elija Hora:
			</td>
			<td>
				   <div>
					<sj:select 
						href="%{opcionesUrl}"
						id="fechaCita"
						formIds="formNuevaCita"
						reloadTopics="reloadHoras"
						name="fechaCita"
						list="listahorasxfechasxmedico"
						headerKey="-1"
						headerValue="- Seleccione una hora -"/>
				
				</div>
			</td>
		</tr>
		</fieldset>
		<tr>
			<td colspan="2"><sj:submit value="Registrar"></sj:submit> </td>
		</tr>
	</table>
</form>

</body>
</html> 
