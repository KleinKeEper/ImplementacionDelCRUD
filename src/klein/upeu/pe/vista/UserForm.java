package klein.upeu.pe.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import klein.upeu.edu.pe.dao.UsuarioDao;
import klein.upeu.edu.pe.entity.Usuario;
import klein.upeu.pe.model.UsuarioDaoImp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtClave;
	//private JTable tdatos;
	private UsuarioDao dao = new UsuarioDaoImp();
	private DefaultTableModel model;
	private int fila = -1;
	private JTable tdatos;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForm frame = new UserForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 408, 143);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombres :");
		lblNombre.setBounds(30, 25, 77, 16);
		panel.add(lblNombre);
		
		JLabel lblClave = new JLabel("Clave :");
		lblClave.setBounds(30, 58, 56, 16);
		panel.add(lblClave);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtClave.requestFocus();
				}
				
			}
		});
		txtUser.setBounds(119, 22, 116, 22);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setBounds(119, 58, 116, 22);
		panel.add(txtClave);
		txtClave.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtUser.getText();
				String clave = txtClave.getText();
				
				if (user.equals("")|| clave.equals("")) {
					JOptionPane.showMessageDialog(null, "Ingrese datos....");
				} else {
				dao.create(new Usuario(user,clave));
				borrar();
				limpiar();
				listar();
				}
			}
		});
		btnCreate.setBounds(266, 16, 97, 25);
		panel.add(btnCreate);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(fila>=0){
					int resp=JOptionPane.showConfirmDialog(null,"Modificar el registro?");
				      if (JOptionPane.OK_OPTION == resp){
				    	  if (txtUser.getText().equals("") || txtClave.getText().equals("")) {
				    		  JOptionPane.showMessageDialog(null, "Ingrese datos....");
						}else {
				    	  dao.update(fila, new Usuario(txtUser.getText(), txtClave.getText()));
						  borrar();
						  limpiar();
						  listar();
						  JOptionPane.showMessageDialog(null,"Registro Modificado Corrrectamente...!");
						}
				       }
				      else{
				           System.out.println("No selecciona una opción afirmativa");
				       }
					
				}else{
					JOptionPane.showMessageDialog(null,"Seleccionar Fila");
				}
			}
		});
		btnNewButton.setBounds(266, 57, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtUser.getText().equals("")) {
					if(dao.buscar(txtUser.getText())>=0) {
						JOptionPane.showMessageDialog(null,"existe");
						tdatos.changeSelection(dao.buscar(txtUser.getText()), 1, false, false);
					}else {
						JOptionPane.showMessageDialog(null,"no existe");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Ingresar nombre de Usuario");
				}
				
			}
		});
		btnSearch.setBounds(61, 94, 97, 25);
		panel.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fila>=0){
					int resp=JOptionPane.showConfirmDialog(null,"Eliminar el registro?");
				      if (JOptionPane.OK_OPTION == resp){
				    	  dao.delate(fila);
						  borrar();
						  limpiar();
						  listar();
						  JOptionPane.showMessageDialog(null,"Registro Eliminado Corrrectamente...!");
				       }
				      else{
				           System.out.println("No selecciona una opción afirmativa");
				       }
					
				}else{
					JOptionPane.showMessageDialog(null,"Seleccionar Fila");
				}
				
			}
		});
		btnDelete.setBounds(211, 94, 97, 25);
		panel.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 182, 376, 134);
		contentPane.add(scrollPane);
		
		tdatos = new JTable();
		
		
		tdatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fila = tdatos.getSelectedRow();
				txtUser.setText(tdatos.getValueAt(fila, 1).toString());
				txtClave.setText(tdatos.getValueAt(fila, 2).toString());
			}
		});
		scrollPane.setColumnHeaderView(tdatos);
		tdatos.setModel(new DefaultTableModel(
				new Object [][] {
				},
				new String [] {
						"N\u00B0", "Usuario", "Password"
				}
				));
		tdatos.getColumnModel().getColumn(0).setPreferredWidth(65);
		tdatos.getColumnModel().getColumn(0).setMinWidth(30);
		tdatos.getColumnModel().getColumn(1).setMinWidth(150);
		tdatos.getColumnModel().getColumn(2).setPreferredWidth(15);
		tdatos.getColumnModel().getColumn(2).setMinWidth(150);
		scrollPane.setViewportView(tdatos);	
		listar();
	}
	
	public void listar() {
		List<Usuario> list = new ArrayList<Usuario>();
		model = (DefaultTableModel) tdatos.getModel();
		list = dao.readAll();
		Object [] datos = new Object [3];
		for (int i = 0; i < list.size(); i++) {
			datos[0]= i+1;
			datos[1]= list.get(i).getNomuser();
			datos[2]= list.get(i).getClave();
			model.addRow(datos);
		}
		tdatos.setModel(model);
	}
	public void limpiar() {
		for (int i = 0; i < tdatos.getRowCount(); i++) {
			model.removeRow(i);
			i-=1;
		}
		
	}
	public void borrar() {
		txtUser.setText(null);
		txtClave.setText(null);
		txtUser.requestFocus();
	}
	
	
	

}
