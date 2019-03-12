package klein.upeu.edu.pe.dao;

import java.util.List;

import klein.upeu.edu.pe.entity.Usuario;

public interface UsuarioDao {
	public void create(Usuario u);
	public void update (int index, Usuario u );
	public void delate (int index);
	public Usuario read(int index);
	public int buscar(String nomuser);
	public List<Usuario> readAll();
	public int validar(Usuario u);
	
}
