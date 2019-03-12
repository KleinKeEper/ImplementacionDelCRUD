package klein.upeu.pe.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import klein.upeu.edu.pe.dao.UsuarioDao;
import klein.upeu.edu.pe.entity.Usuario;

public class UsuarioDaoImp implements UsuarioDao {
	public UsuarioDaoImp() {
		lista = new ArrayList<>();
		lista.add(new Usuario("klein", "123"));
	}

	private List<Usuario> lista; 
	@Override
	public void create(Usuario u) {
		// TODO Auto-generated method stub
		lista.add(u);
		
	}

	@Override
	public void update(int index, Usuario u) {
		// TODO Auto-generated method stub
		lista.set(index, u);
	}

	@Override
	public void delate(int index) {
		// TODO Auto-generated method stub
		lista.remove(index);
	}
	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		return lista;
	}
	@Override
	public Usuario read(int index) {
		// TODO Auto-generated method stub
		Usuario x = new Usuario();
		for (int i = 0; i < lista.size(); i++) {
			if (i==index) {
				x = lista.get(index);
			}
		}
		return x;
	}

	@Override
	public int buscar(String nomuser) {
		// TODO Auto-generated method stub
		int x = -1;
		for (int i = 0; i < lista.size(); i++) {
			if (nomuser.equalsIgnoreCase(lista.get(i).getNomuser())) {
				x=i;
				break;
			}
		}
		return x;
	}
	@Override
	public int validar(Usuario u) {
		// TODO Auto-generated method stub
		int x=0;
		for (int i = 0; i <lista.size() ; i++) {
			if (lista.get(i).getNomuser().equalsIgnoreCase(u.getNomuser()) && lista.get(i).getClave().equals(u.getClave())) {
				x = 1;
			}
		}
		return x;
	}

}
