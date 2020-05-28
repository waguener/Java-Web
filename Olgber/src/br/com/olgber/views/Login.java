package br.com.olgber.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.LoginBean;


import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frame;
	private JTextField txtnome;
	private JPasswordField txtsenha;

	/**
	 * Sistema para Recepção OLGBER
	 * criado por Wagner Elias
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		frame.setBounds(100, 100, 691, 513);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Usu\u00E1rio:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNome.setBounds(151, 222, 90, 33);
		frame.getContentPane().add(lblNome);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblSenha.setBounds(163, 287, 78, 35);
		frame.getContentPane().add(lblSenha);

		txtsenha = new JPasswordField();
		txtsenha.addKeyListener(new KeyAdapter() {
			@Override
			
			/**Este evento é usado para entrar no sistema com 
			 * o botão ENTER.
			 */
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					CRUD sql = new CRUD();
					LoginBean bean = new LoginBean();

					/**
					 * Aqui temos o if do usuario e senha administrador.
					 * 
					 */
					if ((txtnome.getText().equals("admin")) && (txtsenha.getText().toString().equals("olgber1"))) {

						try {
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
							Date hora = Calendar.getInstance().getTime(); 
							String horaFormatada = sdf.format(hora);
							
							java.util.Date d = new Date();						
							String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);						
							
							bean.setNome(txtnome.getText());
							bean.setData(data);
							bean.setHora(horaFormatada);

							/**
							 * Neste try ele salva a entrada no banco de dados. 
							 */
							try {
								sql.Login(bean);
							} catch (ClassNotFoundException | SQLException | ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							/**
							 * Aqui ele abre a tela principal e 
							 * no final fecha a tela de login.
							 */
							Principal main;
							try {
								main = new Principal();
								main.setVisible(true);
							} catch (ClassNotFoundException | SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							frame.dispose();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						/**
						 * Caso não seja um usuario admin ele caira neste else
						 * e fara uma busca no banco de dados de usuario.
						 */
					} else {

						boolean testeLogico = false;
						try {
							testeLogico = sql.BuscaUsuario(txtnome.getText(), txtsenha.getText().toString());
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
							System.out.println(e1);
						}
						/**
						 * Neste if se o usuario digitado existir ele salva sua entrada 
						 * no banco de dados
						 */
						if (testeLogico == true) {

							try {
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
								Date hora = Calendar.getInstance().getTime(); 
								String horaFormatada = sdf.format(hora);
								
								java.util.Date d = new Date();						
								String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
								
								bean.setNome(txtnome.getText());
								bean.setData(data);
								bean.setHora(horaFormatada);
								try {
									sql.Login(bean);
								} catch (ClassNotFoundException | SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Principal main;
								try {
									main = new Principal();
									main.setVisible(true);
									frame.dispose();
								} catch (ClassNotFoundException | SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							/**
							 * Caso oque for digitado pelo usuario não ser igual ao usuario admin ou a algum
							 * usuario cadastrado ele dara este aviso.
							 */
						} else {
							JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
						}
					}
				}
			}
		});
		txtsenha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtsenha.setBounds(253, 287, 215, 35);
		frame.getContentPane().add(txtsenha);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setBounds(253, 220, 216, 35);
		frame.getContentPane().add(txtnome);
		txtnome.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagens/logo.png")));
		lblNewLabel.setBounds(259, 13, 169, 144);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblSejaBemVindo = new JLabel("Seja Bem Vindo ao Sistema!");
		lblSejaBemVindo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblSejaBemVindo.setForeground(Color.WHITE);
		lblSejaBemVindo.setBounds(204, 158, 285, 35);
		frame.getContentPane().add(lblSejaBemVindo);

		JButton btentrar = new JButton("");
		btentrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			/**
			 
			 */
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();
				LoginBean bean = new LoginBean();

				if ((txtnome.getText().equals("admin")) && (txtsenha.getText().toString().equals("olgber1"))) {

					try {
						bean.setNome(txtnome.getText());

						try {
							sql.Login(bean);
						} catch (ClassNotFoundException | SQLException | ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						Principal main;
						try {
							main = new Principal();
							main.setVisible(true);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						frame.dispose();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {

					boolean testeLogico = false;
					try {
						testeLogico = sql.BuscaUsuario(txtnome.getText(), txtsenha.getText().toString());
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
						System.out.println("erro 1 " + e1);

					}
					if (testeLogico == true) {

						try {
							bean.setNome(txtnome.getText());
							
							try {
								sql.Login(bean);
								
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								System.out.println("erro 2 " + e1);
							}
							Principal main;
							try {
								main = new Principal();
								main.setVisible(true);
								frame.dispose();
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								System.out.println("Erro 3 " + e1);
							}

						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.println("Erro 4" + e1);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
					}

				}
			}
		});
		btentrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btentrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			/**
			 * Evento para movimento do botao ao passar o mouse sobre ele.
			 */
			public void mouseMoved(MouseEvent e) {
				btentrar.setRolloverIcon(new ImageIcon(Login.class.getResource("/imagens/botaosombra.png")));
			}
		});
		btentrar.setIcon(new ImageIcon(Login.class.getResource("/imagens/botao.png")));
		btentrar.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		btentrar.setFocusable(false);
		btentrar.setContentAreaFilled(false);
		btentrar.setBorderPainted(false);
		btentrar.setBounds(204, 354, 147, 47);
		frame.getContentPane().add(btentrar);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				this.fechar();
			}

			/**
			 * Botao sair pergunta ao usuario se quer realmente sair.
			 */
			private void fechar() {
				if (javax.swing.JOptionPane.showConfirmDialog(null, "Deseja Sair?", "ATENÇÂO ",
						javax.swing.JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			/**
			 * Evento para movimento do botao ao passar o mouse sobre ele.
			 */
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(Login.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setIcon(new ImageIcon(Login.class.getResource("/imagens/botaosair.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		btsair.setBounds(353, 354, 147, 47);
		frame.getContentPane().add(btsair);

		JLabel lblteste = new JLabel(",");
		lblteste.setBounds(545, 421, 112, 33);
		frame.getContentPane().add(lblteste);
		
		JLabel lblVerso = new JLabel("Vers\u00E3o 2018");
		lblVerso.setForeground(Color.WHITE);
		lblVerso.setBounds(596, 449, 77, 16);
		frame.getContentPane().add(lblVerso);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
		Login.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(-138, -19, 853, 516);
		frame.getContentPane().add(label);
	}
}
