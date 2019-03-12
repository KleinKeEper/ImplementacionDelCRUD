package klein.upeu.pe.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import klein.upeu.edu.pe.dao.UsuarioDao;
import klein.upeu.edu.pe.entity.Usuario;
import klein.upeu.pe.model.UsuarioDaoImp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtpassword;
	private UsuarioDao dao = new UsuarioDaoImp();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 378, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		
		JLabel lblUser = new JLabel("User: ");
		lblUser.setBounds(83, 70, 56, 16);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(83, 118, 71, 16);
		contentPane.add(lblPassword);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					txtpassword.requestFocus();
				}
			}
		});
		txtUser.setBounds(167, 67, 116, 22);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnSingIn = new JButton("Sing in");
		btnSingIn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ingresar();
			}
		});
		btnSingIn.setBounds(127, 160, 97, 25);
		contentPane.add(btnSingIn);
		
		txtpassword = new JPasswordField();
		txtpassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnSingIn.requestFocus();
				}
			}
		});
		txtpassword.setBounds(166, 115, 116, 22);
		contentPane.add(txtpassword);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, Color.LIGHT_GRAY, null));
		panel.setBounds(12, 13, 336, 227);
		contentPane.add(panel);
		
	}
	
	public void ingresar() {
		try {
			
			String user = txtUser.getText();
			String pass = new String(txtpassword.getPassword());
			if (txtUser.getText().equals("") || txtpassword.getPassword().equals("") ) {
				JOptionPane.showMessageDialog(null, "Ingresar datos...");
			}else {
				if (dao.validar(new Usuario(user, pass))==1) {
					PrincipalForm pf = new PrincipalForm();
					pf.setVisible(true);
					setVisible(false);
					System.out.println();
				} else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos...");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Datos incorrectos...");
		}	
	}
	
	
	}

