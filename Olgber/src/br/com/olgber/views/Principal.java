package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.Cursor;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.JobAttributes;

import javax.swing.JDesktopPane;

import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.MaskFormatter;



import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.OcorrenciasBean;

import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.JTextField;

public class Principal extends JFrame {

	private JPanel frmPrincipal;
	private JTextField txtresponsavel;
	private JLabel lblUsuario;
	private JMenuItem mntmMovimentaoDeVeculos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Principal() throws ParseException, ClassNotFoundException, SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 673);
		frmPrincipal = new JPanel();
		frmPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(frmPrincipal);
		frmPrincipal.setLayout(null);
		Mascara masc = new Mascara();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 247, 675, 227);
		frmPrincipal.add(scrollPane);

		JTextArea txttexto = new JTextArea();
		scrollPane.setViewportView(txttexto);
		txttexto.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txttexto.setLineWrap(true);
		txttexto.setWrapStyleWord(true);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(SystemColor.controlDkShadow);
		menuBar.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.setBounds(0, 0, 1203, 44);
		frmPrincipal.add(menuBar);

		JMenu mnNewMenu = new JMenu("Cadastros");
		mnNewMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnNewMenu);

		/**
		 * Chama a tela de cadastrar usuario.
		 */
		JMenuItem btusuario = new JMenuItem("Usu\u00E1rios");
		btusuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadUsuario tela;
				tela = new CadUsuario();

				tela.setVisible(true);
			}
		});

		btusuario.setIcon(new ImageIcon(Principal.class.getResource("/imagens/users.png")));
		btusuario.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		mnNewMenu.add(btusuario);

		/**
		 * Chama a tela de cadastro de Armários
		 */
		JMenuItem btarmario = new JMenuItem("Arm\u00E1rios");
		btarmario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				CadArmario tela;
				try {
					tela = new CadArmario();
					tela.setVisible(true);
				} catch (ClassNotFoundException | ParseException | SQLException e) {
					e.printStackTrace();
				}

			}
		});

		/**
		 * Chama a tela de Cadastro de Funcionarios Administrativos.
		 */
		JMenuItem btfuncadm = new JMenuItem("Funcion\u00E1rios Administrativos");
		btfuncadm.setIcon(new ImageIcon(Principal.class.getResource("/imagens/CadAdm.png")));
		btfuncadm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadAdm tela;
				try {
					tela = new CadAdm();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btfuncadm.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btfuncadm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNewMenu.add(btfuncadm);
		btarmario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btarmario.setIcon(new ImageIcon(Principal.class.getResource("/imagens/armario.png")));
		btarmario.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		mnNewMenu.add(btarmario);

		/**
		 * Chama a tela de Cadastro de Armários para Funcionários.
		 */
		JMenuItem mntmArmriosParaFuncionrios = new JMenuItem("Arm\u00E1rios para Funcion\u00E1rios");
		mntmArmriosParaFuncionrios.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CadArmarioUsuario tela;
				try {
					tela = new CadArmarioUsuario();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmArmriosParaFuncionrios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmArmriosParaFuncionrios.setIcon(new ImageIcon(Principal.class.getResource("/imagens/armfunc.png")));
		mntmArmriosParaFuncionrios.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		mnNewMenu.add(mntmArmriosParaFuncionrios);

		/**
		 * Chama a tela de Cadastro de Veículos.
		 */
		JMenuItem btveiculo = new JMenuItem("Ve\u00EDculos");
		btveiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadVeiculo tela;
				try {
					tela = new CadVeiculo();
					tela.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btveiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btveiculo.setIcon(new ImageIcon(Principal.class.getResource("/imagens/carrocadastro.png")));
		btveiculo.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		mnNewMenu.add(btveiculo);

		/**
		 * Chama a tela de Cadastro de Documentos do Veículo.
		 */
		JMenuItem btdocumento = new JMenuItem("Documentos");
		btdocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadDocumentos tela;
				try {
					tela = new CadDocumentos();
					tela.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btdocumento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btdocumento.setIcon(new ImageIcon(Principal.class.getResource("/imagens/documentos.png")));
		btdocumento.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		mnNewMenu.add(btdocumento);

		JMenu mnMovimentao = new JMenu("Movimenta\u00E7\u00E3o");
		mnMovimentao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnMovimentao.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnMovimentao);

		/**
		 * Chama a tela de Movimentação dos Veículos.
		 */
		JMenuItem mntmMovimentaesDoVeculo = new JMenuItem("Movimenta\u00E7\u00F5es do Ve\u00EDculo");
		mntmMovimentaesDoVeculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				MovVeiculo tela;
				try {
					tela = new MovVeiculo();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		mntmMovimentaesDoVeculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMovimentaesDoVeculo.setIcon(new ImageIcon(Principal.class.getResource("/imagens/carro movimento.png")));
		mntmMovimentaesDoVeculo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnMovimentao.add(mntmMovimentaesDoVeculo);

		/**
		 * Chama a tela de Controle de Vans
		 */
		JMenuItem mntmControleDeVans = new JMenuItem("Controle de Vans");
		mntmControleDeVans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControleVans tela;
				try {
					tela = new ControleVans();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		mntmControleDeVans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmControleDeVans.setIcon(new ImageIcon(Principal.class.getResource("/imagens/van.png")));
		mntmControleDeVans.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnMovimentao.add(mntmControleDeVans);

		JMenu mnVisitantes = new JMenu("Visitantes");
		mnVisitantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnVisitantes.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnVisitantes);

		/**
		 * Chama a tela de Cadastro de Visitantes.
		 */
		JMenuItem mntmCadastroDeVisitantes = new JMenuItem("Cadastro de Visitantes");
		mntmCadastroDeVisitantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				CadVisitantes tela;
				try {
					tela = new CadVisitantes();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});
		mntmCadastroDeVisitantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmCadastroDeVisitantes.setIcon(new ImageIcon(Principal.class.getResource("/imagens/visitante.png")));
		mntmCadastroDeVisitantes.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnVisitantes.add(mntmCadastroDeVisitantes);

		/**
		 * Chama a tela de Cadastro de Visitas.
		 */
		JMenuItem mntmVisitantesCadstrados = new JMenuItem("Visitantes Cadastrados");
		mntmVisitantesCadstrados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmVisitantesCadstrados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				VisitasCadastradas tela;
				try {
					tela = new VisitasCadastradas();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmVisitantesCadstrados.setIcon(new ImageIcon(Principal.class.getResource("/imagens/visitascadastradas.png")));
		mntmVisitantesCadstrados.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnVisitantes.add(mntmVisitantesCadstrados);
		
		JMenuItem mntmEntrevistas = new JMenuItem("Entrevistas");
		mntmEntrevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadEntrevista tela;
				try {
					tela = new CadEntrevista();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		mntmEntrevistas.setIcon(new ImageIcon("\\\\oel\\PUBLICO\\OLGBER\\imagens\\entrevista.png"));
		mntmEntrevistas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEntrevistas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnVisitantes.add(mntmEntrevistas);

		JMenu mnControle = new JMenu("Controle");
		mnControle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnControle.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnControle);

		/**
		 * Chama a tela de Controle de Chaves.
		 */
		JMenuItem mntmChaves = new JMenuItem("Chaves ");
		mntmChaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chaves tela;
				try {
					tela = new Chaves();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		mntmChaves.setIcon(new ImageIcon(Principal.class.getResource("/imagens/chaves.png")));
		mntmChaves.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mntmChaves.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnControle.add(mntmChaves);

		/**
		 * Chama a tela de Controle do Administrativo.
		 */
		JMenuItem mntmAdministrativo = new JMenuItem("Administrativo");
		mntmAdministrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControleADM tela;
				try {
					tela = new ControleADM();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmAdministrativo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmAdministrativo.setIcon(new ImageIcon(Principal.class.getResource("/imagens/adm.png")));
		mntmAdministrativo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnControle.add(mntmAdministrativo);

		/**
		 * Chama a tela de Controle de Descartáveis.
		 */
		JMenuItem mntmDescartveis = new JMenuItem("Descart\u00E1veis");
		mntmDescartveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Descartaveis tela;
				try {
					tela = new Descartaveis();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
				}

			}
		});
		mntmDescartveis.setIcon(new ImageIcon(Principal.class.getResource("/imagens/Descartaveis.png")));
		mntmDescartveis.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mntmDescartveis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnControle.add(mntmDescartveis);

		JMenu mnConsultas = new JMenu("Consultas");
		mnConsultas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnConsultas.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnConsultas);

		/**
		 * Chama a tela de Consulta de Armários.
		 */
		JMenuItem mntmArmrios_1 = new JMenuItem("Arm\u00E1rios");
		mntmArmrios_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ConsArmario tela;
				try {
					tela = new ConsArmario();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmArmrios_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmArmrios_1.setIcon(new ImageIcon(Principal.class.getResource("/imagens/armario.png")));
		mntmArmrios_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmArmrios_1);

		/**
		 * Chama a tela de Consulta de Atrasos e Saidas Antecipadas.
		 */
		JMenuItem mntmAtrasosESadas = new JMenuItem("Atrasos e Sa\u00EDdas");
		mntmAtrasosESadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsAtraso tela;
				try {
					tela = new ConsAtraso();
					tela.setVisible(true);
				} catch (Exception e2) {

				}
			}
		});

		/**
		 * Chama a tela de Consulta do Controle de Chaves
		 */
		JMenuItem mntmChavesEntregadas = new JMenuItem("Hist\u00F3rico das Chaves");
		mntmChavesEntregadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsChaves tela;
				try {
					tela = new ConsChaves();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		mntmChavesEntregadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmChavesEntregadas.setIcon(new ImageIcon(Principal.class.getResource("/imagens/entregachave.png")));
		mntmChavesEntregadas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmChavesEntregadas);
		mntmAtrasosESadas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmAtrasosESadas.setIcon(new ImageIcon(Principal.class.getResource("/imagens/atraso.png")));
		mntmAtrasosESadas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmAtrasosESadas);

		/**
		 * Chama a tela de Consulta de Visitas.
		 */
		JMenuItem mntmVisitantes = new JMenuItem("Visitas");
		mntmVisitantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ConsVisitantes tela;
				try {
					tela = new ConsVisitantes();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmVisitantes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmVisitantes.setIcon(new ImageIcon(Principal.class.getResource("/imagens/visitante.png")));
		mntmVisitantes.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmVisitantes);

		/**
		 * Chama a tela de Consulta de Movimentação dos Veículos.
		 */
		mntmMovimentaoDeVeculos = new JMenuItem("Movimenta\u00E7\u00E3o de Ve\u00EDculo");
		mntmMovimentaoDeVeculos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmMovimentaoDeVeculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ConsMovVeiculo tela;
				try {
					tela = new ConsMovVeiculo();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		JMenuItem mntmEntrevistas_1 = new JMenuItem("Entrevistas");
		mntmEntrevistas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsEntrevista tela;
				try {
					tela = new ConsEntrevista();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmEntrevistas_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmEntrevistas_1.setIcon(new ImageIcon("\\\\oel\\PUBLICO\\OLGBER\\imagens\\botaoEntrevista.png"));
		mntmEntrevistas_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmEntrevistas_1);
		mntmMovimentaoDeVeculos.setIcon(new ImageIcon(Principal.class.getResource("/imagens/movveiculo.png")));
		mntmMovimentaoDeVeculos.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmMovimentaoDeVeculos);

		/**
		 * Chama a tela de Consulta de Vans.
		 */
		JMenuItem mntmVansEPassageiros = new JMenuItem("Vans e Passageiros");
		mntmVansEPassageiros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmVansEPassageiros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsVans tela;
				try {
					tela = new ConsVans();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmVansEPassageiros.setIcon(new ImageIcon(Principal.class.getResource("/imagens/van.png")));
		mntmVansEPassageiros.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmVansEPassageiros);

		/**
		 * Chama a tela de Consulta de Administrativo.
		 */
		JMenuItem mntmAdministrativo_1 = new JMenuItem("Administrativo");
		mntmAdministrativo_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmAdministrativo_1.setIcon(new ImageIcon(Principal.class.getResource("/imagens/buscaadmin.png")));
		mntmAdministrativo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsADM tela;
				try {
					tela = new ConsADM();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmAdministrativo_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmAdministrativo_1);

		/**
		 * Chama a tela de Consulta de Descartáveis.
		 */
		JMenuItem mntmUsoDeDescartveis = new JMenuItem("Uso de Descart\u00E1veis");
		mntmUsoDeDescartveis.setIcon(new ImageIcon(Principal.class.getResource("/imagens/descartavel.png")));
		mntmUsoDeDescartveis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsDescartavel tela;
				try {
					tela = new ConsDescartavel();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		mntmUsoDeDescartveis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmUsoDeDescartveis.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnConsultas.add(mntmUsoDeDescartveis);

		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		mnOpes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		menuBar.add(mnOpes);

		/**
		 * Sai do Sistema
		 */
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSair.setIcon(new ImageIcon(Principal.class.getResource("/imagens/sair.png")));
		mntmSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sobre tela;
				try {
					tela = new Sobre();
					tela.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmSobre.setIcon(new ImageIcon(Principal.class.getResource("/imagens/sobre.png")));
		mntmSobre.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mntmSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOpes.add(mntmSobre);
		mntmSair.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		mnOpes.add(mntmSair);

		JLabel lblSejaBemVindo = new JLabel("Seja Bem Vindo(a):");
		lblSejaBemVindo.setForeground(Color.WHITE);
		lblSejaBemVindo.setBackground(Color.WHITE);
		lblSejaBemVindo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblSejaBemVindo.setBounds(801, 57, 169, 21);
		frmPrincipal.add(lblSejaBemVindo);

		lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblUsuario.setBounds(823, 80, 161, 35);
		frmPrincipal.add(lblUsuario);

		/**
		 * Este try Faz uma Busca no Banco Pelo ultimo usuario logado e se for
		 * do tipo admin ele tem acesso a todos os cadastros se for usuario os
		 * botões cadastro de usuario,cadastro de veiculo, cadastro de
		 * documentos e cadastro de funcionarios administrativos serão
		 * desabilitados.
		 */
		CRUD sql = new CRUD();
		String tipo;
		String login;
		try {
			login = sql.BuscaLogin();
			lblUsuario.setText(login);
			tipo = sql.BuscaUsuarioTipo(lblUsuario.getText().toString());

			if (tipo.equals("Usuário")) {
				btusuario.setEnabled(false);
				
				
				btfuncadm.setEnabled(false);
				btusuario.setToolTipText("Somente o Administrador");
				
				btfuncadm.setToolTipText("Somente o Administrador");
			}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		panel.setBounds(755, 199, 225, 323);
		frmPrincipal.add(panel);
		panel.setLayout(null);

		/**
		 * Movimento do botao ao passar o mouse sobre ele.
		 */
		JButton btatraso = new JButton("");
		btatraso.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btatraso.setRolloverIcon(new ImageIcon(Principal.class.getResource("/imagens/botaoatrasobig.png")));
			}
		});
		btatraso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btatraso.setIcon(new ImageIcon(Principal.class.getResource("/imagens/botaoatraso.png")));
		btatraso.setFocusable(false);
		btatraso.setContentAreaFilled(false);
		btatraso.setBorderPainted(false);
		btatraso.setForeground(Color.BLACK);
		btatraso.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btatraso.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btatraso.addActionListener(new ActionListener() {
			/**
			 * Chama a tela de Cadastro de Atraso e Saidas antecipadas.
			 */
			public void actionPerformed(ActionEvent e) {
				CadAtrasoSaida tela;
				try {
					tela = new CadAtrasoSaida();
					tela.setVisible(true);

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btatraso.setBounds(12, 40, 200, 83);
		panel.add(btatraso);

		/**
		 * Movimento do botao ao passar o mouse sobre ele.
		 */
		JButton btcalc = new JButton("");
		btcalc.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcalc.setRolloverIcon(new ImageIcon(Principal.class.getResource("/imagens/botaocalcbig.png")));
			}
		});
		btcalc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcalc.setIcon(new ImageIcon(Principal.class.getResource("/imagens/botaocalc.png")));
		
		btcalc.addActionListener(new ActionListener() {
			/**
			 * Chama a Calculadora.
			 */
			public void actionPerformed(ActionEvent arg0) {
				try {
					Runtime.getRuntime().exec("calc");
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			}
		});
		btcalc.setBounds(34, 177, 153, 122);
		panel.add(btcalc);

		JLabel lblOcorrncias = new JLabel("Ocorr\u00EAncias:");
		lblOcorrncias.setForeground(Color.WHITE);
		lblOcorrncias.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblOcorrncias.setBounds(35, 199, 137, 35);
		frmPrincipal.add(lblOcorrncias);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblData.setBounds(35, 487, 67, 35);
		frmPrincipal.add(lblData);

		txtresponsavel = new JTextField();
		txtresponsavel.setHorizontalAlignment(SwingConstants.CENTER);
		txtresponsavel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtresponsavel.setBounds(465, 200, 245, 35);
		frmPrincipal.add(txtresponsavel);
		txtresponsavel.setColumns(10);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(114, 487, 169, 35);
		frmPrincipal.add(txtdata);

		String hoje, data;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		/**
		 * Cadastra as ocorrencias.
		 */
		JButton btcadastrar = new JButton("");
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcalc.setFocusable(false);
		btcalc.setContentAreaFilled(false);
		btcalc.setBorderPainted(false);
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtresponsavel.getText().equals("") || txttexto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {

					CRUD sql = new CRUD();
					OcorrenciasBean bean = new OcorrenciasBean();
					String data;
					data = masc.convertDate(txtdata.getText());
					bean.setNome(txtresponsavel.getText());
					bean.setTexto(txttexto.getText());
					bean.setData(data);

					Date dia = null;
					String dataTexto = new String(txtdata.getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						format.setLenient(false);
						dia = format.parse(dataTexto);
						try {
							sql.Ocorrencias(bean);
							JOptionPane.showMessageDialog(null, "Ocorrência Salva com Sucesso");
							txttexto.setText("");
							txtdata.setText("");
							txtresponsavel.setText("");
							txttexto.grabFocus();
						} catch (Exception e) {
							System.out.println(e);
						}
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente!", "AVISO",
								JOptionPane.WARNING_MESSAGE);
						txtdata.setText("");
						txtdata.grabFocus();
					}

				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar.setRolloverIcon(new ImageIcon(Principal.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(316, 476, 140, 57);
		frmPrincipal.add(btcadastrar);

		/**
		 * Chama a tela de Checar Ocorrencias.
		 */
		JButton btchecar = new JButton("");
		btchecar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btchecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChecarOcorrencias tela;
				try {
					tela = new ChecarOcorrencias();
					tela.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btchecar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btchecar.setRolloverIcon(new ImageIcon(Principal.class.getResource("/imagens/botaochecarbig.png")));
			}
		});
		btchecar.setIcon(new ImageIcon(Principal.class.getResource("/imagens/botaochecar.png")));
		btchecar.setFocusable(false);
		btchecar.setContentAreaFilled(false);
		btchecar.setBorderPainted(false);
		btchecar.setBounds(468, 476, 140, 57);
		frmPrincipal.add(btchecar);

		JLabel lblResponsvel = new JLabel("Respons\u00E1vel:");
		lblResponsvel.setForeground(Color.WHITE);
		lblResponsvel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblResponsvel.setBounds(325, 199, 137, 35);
		frmPrincipal.add(lblResponsvel);

		JLabel lblHojeDia = new JLabel("Hoje \u00E9 Dia:");
		lblHojeDia.setForeground(Color.WHITE);
		lblHojeDia.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblHojeDia.setBounds(788, 523, 126, 35);
		frmPrincipal.add(lblHojeDia);

		JLabel lblhoje = new JLabel("Hoje \u00E9 Dia:");
		lblhoje.setForeground(Color.WHITE);
		lblhoje.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblhoje.setBounds(692, 549, 292, 35);
		frmPrincipal.add(lblhoje);

		/**
		 * Seta o dia atual na lblhoje.
		 */
		Date data3 = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		lblhoje.setText(formato.format(data3));
		
		JLabel label_1 = new JLabel("Vers\u00E3o 2018");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(907, 609, 77, 16);
		frmPrincipal.add(label_1);
		
				JLabel label = new JLabel("");
				label.setDisabledIcon(null);
				label.setOpaque(true);
				label.setForeground(new Color(0, 0, 0));
				label.setIcon(new ImageIcon(
						Principal.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(-36, 0, 1239, 745);
				frmPrincipal.add(label);
	}
}
