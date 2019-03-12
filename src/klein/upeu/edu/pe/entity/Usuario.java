package klein.upeu.edu.pe.entity;

public class Usuario {
	private String nomuser;
	private String clave;
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nomuser, String clave) {
		super();
		this.nomuser = nomuser;
		this.clave = clave;
	}
	public String getNomuser() {
		return nomuser;
	}
	public void setNomuser(String nomuser) {
		this.nomuser = nomuser;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
}
