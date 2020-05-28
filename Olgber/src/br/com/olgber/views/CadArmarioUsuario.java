package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Limpar;
import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioBean;
import br.com.olgber.bean.CadArmarioFuncBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.awt.event.MouseAdapter;

public class CadArmarioUsuario extends JFrame {

	private JPanel frmcadarmfunc;
	private JTable tabarmario;
	private JTextField txtnome;
	private JTextField txtnumarmario;
	private JTextField txttipo;
	private JTextField txtdisponivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadArmarioUsuario frame = new CadArmarioUsuario();
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
	 */
	public CadArmarioUsuario() throws ParseException {
		setResizable(false);
		setTitle("Cadastro de Arm\u00E1rio para Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 859, 658);
		frmcadarmfunc = new JPanel();
		frmcadarmfunc.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmcadarmfunc);
		frmcadarmfunc.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 65, 808, 294);
		frmcadarmfunc.add(scrollPane);

		txttipo = new JTextField();
		txttipo.setHorizontalAlignment(SwingConstants.CENTER);
		txttipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txttipo.setColumns(10);
		txttipo.setBounds(634, 419, 179, 35);
		frmcadarmfunc.add(txttipo);

		txtdisponivel = new JTextField();
		txtdisponivel.setHorizontalAlignment(SwingConstants.CENTER);
		txtdisponivel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdisponivel.setColumns(10);
		txtdisponivel.setBounds(757, 368, 72, 35);
		frmcadarmfunc.add(txtdisponivel);

		tabarmario = new JTable();
		tabarmario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				txtnumarmario.setText(
						String.valueOf(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 0).toString()));
				txttipo.setText(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 2).toString());
			}
		});
		tabarmario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabarmario);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNome.setBounds(46, 416, 507, 35);
		frmcadarmfunc.add(lblNome);

		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setBounds(118, 419, 435, 35);
		frmcadarmfunc.add(txtnome);
		txtnome.setColumns(10);

		JLabel lblNDoArmrio = new JLabel("N\u00BA do Arm\u00E1rio:");
		lblNDoArmrio.setForeground(Color.WHITE);
		lblNDoArmrio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNDoArmrio.setBounds(46, 483, 158, 35);
		frmcadarmfunc.add(lblNDoArmrio);

		txtnumarmario = new JTextField();
		txtnumarmario.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnumarmario.setBounds(199, 483, 82, 35);
		frmcadarmfunc.add(txtnumarmario);
		txtnumarmario.setColumns(10);

		JLabel lblDataDoCadastro = new JLabel("Data do Cadastro:");
		lblDataDoCadastro.setForeground(Color.WHITE);
		lblDataDoCadastro.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDataDoCadastro.setBounds(355, 483, 192, 35);
		frmcadarmfunc.add(lblDataDoCadastro);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setText("   ");
		txtdata.setBounds(559, 486, 140, 35);
		frmcadarmfunc.add(txtdata);

		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		JButton btfeminino = new JButton("");
		btfeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();
				txtdisponivel.setText("0");
				DefaultTableModel modelo = new DefaultTableModel();

				tabarmario.setModel(modelo);

				modelo.addColumn("Nº Armário");
				modelo.addColumn("Status");
				modelo.addColumn("Tipo");
				modelo.addColumn("Chave Reserva");
				modelo.addColumn("Situação");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("ID");

				tabarmario.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabarmario.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
				tabarmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioBean list : sql.Buscadisponivel("Disponivel", "Feminino")) {
						int somaLinhas = tabarmario.getModel().getRowCount() + 1;
						txtdisponivel.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getNarmario(), list.getStatus(), list.getChavereserva(),
								list.getTipo(), list.getSituacao(), list.getData(), list.getId() });
					}
				} catch (Exception e) {
					System.out.println(e);

				}
			}
		});
		btfeminino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btfeminino.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btfeminino.setRolloverIcon(
						new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaofemininobig.png")));
			}
		});
		btfeminino.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaofeminino.png")));
		btfeminino.setFocusable(false);
		btfeminino.setContentAreaFilled(false);
		btfeminino.setBorderPainted(false);
		btfeminino.setBounds(208, 0, 140, 58);
		frmcadarmfunc.add(btfeminino);

		JButton btmasculino = new JButton("");
		btmasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();
				txtdisponivel.setText("0");
				DefaultTableModel modelo = new DefaultTableModel();

				tabarmario.setModel(modelo);

				modelo.addColumn("Nº Armário");
				modelo.addColumn("Status");
				modelo.addColumn("Tipo");
				modelo.addColumn("Chave Reserva");
				modelo.addColumn("Situação");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("ID");

				tabarmario.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabarmario.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
				tabarmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioBean list : sql.Buscadisponivel("Disponivel", "Masculino")) {
						int somaLinhas = tabarmario.getModel().getRowCount() + 1;
						txtdisponivel.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getNarmario(), list.getStatus(), list.getChavereserva(),
								list.getTipo(), list.getSituacao(), list.getData(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e);

				}
			}
		});
		btmasculino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btmasculino.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btmasculino.setRolloverIcon(
						new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaomasculinobig.png")));
			}
		});
		btmasculino.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaomasculino.png")));
		btmasculino.setFocusable(false);
		btmasculino.setContentAreaFilled(false);
		btmasculino.setBorderPainted(false);
		btmasculino.setBounds(342, 0, 140, 58);
		frmcadarmfunc.add(btmasculino);

		JButton bttempfeminino = new JButton("");
		bttempfeminino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				txtdisponivel.setText("0");
				tabarmario.setModel(modelo);

				modelo.addColumn("Nº Armário");
				modelo.addColumn("Status");
				modelo.addColumn("Tipo");
				modelo.addColumn("Chave Reserva");
				modelo.addColumn("Situação");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("ID");

				tabarmario.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabarmario.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
				tabarmario.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
				tabarmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				
				try {
					for (CadArmarioBean list : sql.Buscadisponivel("Disponivel", "Temp.Feminino")) {
						int somaLinhas = tabarmario.getModel().getRowCount() + 1;						
						txtdisponivel.setText(somaLinhas + "");						
						modelo.addRow(new Object[] { list.getNarmario(), list.getStatus(), list.getChavereserva(),
								list.getTipo(), list.getSituacao(), list.getData(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e);

				}
			}
		});
		bttempfeminino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttempfeminino.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				bttempfeminino.setRolloverIcon(
						new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaotempfemininobig.png")));
			}
		});
		bttempfeminino.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaotempfeminino.png")));
		bttempfeminino.setFocusable(false);
		bttempfeminino.setContentAreaFilled(false);
		bttempfeminino.setBorderPainted(false);
		bttempfeminino.setBounds(475, 0, 140, 58);
		frmcadarmfunc.add(bttempfeminino);

		JButton bttempmasculino = new JButton("");
		bttempmasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();
				txtdisponivel.setText("0");
				DefaultTableModel modelo = new DefaultTableModel();

				tabarmario.setModel(modelo);

				modelo.addColumn("Nº Armário");
				modelo.addColumn("Status");
				modelo.addColumn("Tipo");
				modelo.addColumn("Chave Reserva");
				modelo.addColumn("Situação");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("ID");

				tabarmario.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabarmario.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
				tabarmario.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
				tabarmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioBean list : sql.Buscadisponivel("Disponivel", "Temp.Masculino")) {
						int somaLinhas = tabarmario.getModel().getRowCount() + 1;						
						txtdisponivel.setText(somaLinhas + "");
						modelo.addRow(new Object[] { list.getNarmario(), list.getStatus(), list.getChavereserva(),
								list.getTipo(), list.getSituacao(), list.getData(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e);

				}

				

			}
		});
		bttempmasculino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bttempmasculino.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				bttempmasculino.setRolloverIcon(
						new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaotempmasculinobig.png")));
			}
		});
		bttempmasculino.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaotempmasculino.png")));
		bttempmasculino.setFocusable(false);
		bttempmasculino.setContentAreaFilled(false);
		bttempmasculino.setBorderPainted(false);
		bttempmasculino.setBounds(609, 0, 140, 58);
		frmcadarmfunc.add(bttempmasculino);

		JLabel lblArmrios = new JLabel("Arm\u00E1rios");
		lblArmrios.setForeground(Color.WHITE);
		lblArmrios.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblArmrios.setBounds(43, 1, 93, 26);
		frmcadarmfunc.add(lblArmrios);

		JLabel lblDisponveisPara = new JLabel("Dispon\u00EDveis para:");
		lblDisponveisPara.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDisponveisPara.setForeground(Color.WHITE);
		lblDisponveisPara.setBounds(12, 21, 169, 31);
		frmcadarmfunc.add(lblDisponveisPara);

		JLabel lblCadastrarArmrioPara = new JLabel("Cadastrar Arm\u00E1rio para os Funcion\u00E1rios");
		lblCadastrarArmrioPara.setForeground(Color.WHITE);
		lblCadastrarArmrioPara.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblCadastrarArmrioPara.setBounds(36, 368, 409, 35);
		frmcadarmfunc.add(lblCadastrarArmrioPara);

		JButton btcadastrar = new JButton("");
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar.setRolloverIcon(
						new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				String status;
				status = txttipo.getText();
				if (txtnome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha o campo nome corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String data;
					data = masc.convertDate(txtdata.getText());
					CadArmarioFuncBean bean = new CadArmarioFuncBean();

					bean.setNarmario(Integer.parseInt(txtnumarmario.getText()));
					bean.setNome(txtnome.getText());
					bean.setData(data);
					bean.setTipo(txttipo.getText());

					Date dia = null;
					String dataTexto = new String(txtdata.getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						format.setLenient(false);
						dia = format.parse(dataTexto);
						try {
							sql.SalvaArmFuncionario(bean);
							CadArmarioBean bean2 = new CadArmarioBean();
							bean2.setId(Integer.parseInt(String.valueOf(
									tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 6).toString())));
							bean2.setStatus("Ocupado");
							sql.AtualizaFunciArmarioCad(bean2);
							JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
							txtnome.setText("");
							txtnumarmario.setText("");
							txttipo.setText("");
							txtnome.grabFocus();
							DefaultTableModel modelo = new DefaultTableModel();

							tabarmario.setModel(modelo);

							modelo.addColumn("Nº Armário");
							modelo.addColumn("Status");
							modelo.addColumn("Tipo");
							modelo.addColumn("Chave Reserva");
							modelo.addColumn("Situação");
							modelo.addColumn("Data do Cadastro");
							modelo.addColumn("ID");

							tabarmario.getColumnModel().getColumn(0).setPreferredWidth(40);
							tabarmario.getColumnModel().getColumn(1).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
							tabarmario.getColumnModel().getColumn(3).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
							tabarmario.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								for (CadArmarioBean list : sql.Buscadisponivel("Disponivel", status)) {
									int somaLinhas = tabarmario.getModel().getRowCount() + 1;
									txtdisponivel.setText(Integer.toString(somaLinhas));
									modelo.addRow(
											new Object[] { list.getNarmario(), list.getStatus(), list.getChavereserva(),
													list.getTipo(), list.getSituacao(), list.getData(), list.getId() });
								}
							} catch (Exception e1) {
								System.out.println(e);

							}

						} catch (Exception e2) {
							System.out.println(e2);
						}
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente!", "AVISO",
								JOptionPane.WARNING_MESSAGE);
						txtdata.setText("");
						txtdata.grabFocus();
					}

				}
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(90, 531, 140, 58);
		frmcadarmfunc.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(CadArmarioUsuario.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(636, 531, 140, 58);
		frmcadarmfunc.add(btsair);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setForeground(Color.WHITE);
		lblTipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTipo.setBounds(575, 419, 61, 35);
		frmcadarmfunc.add(lblTipo);

		JLabel lblArmriosDisponveis = new JLabel("Arm\u00E1rios Dispon\u00EDveis:");
		lblArmriosDisponveis.setForeground(Color.WHITE);
		lblArmriosDisponveis.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblArmriosDisponveis.setBounds(541, 368, 218, 35);
		frmcadarmfunc.add(lblArmriosDisponveis);

		JLabel wallpaper = new JLabel("");
		wallpaper.setIcon(new ImageIcon(
				CadArmarioUsuario.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		wallpaper.setBounds(-12, 0, 942, 635);
		frmcadarmfunc.add(wallpaper);
	}
}
