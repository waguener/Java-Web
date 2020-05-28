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
import br.com.olgber.bean.AdmBean;
import br.com.olgber.bean.CadChaveBean;
import br.com.olgber.bean.CadVisitanteBean;
import br.com.olgber.bean.ChavesBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Chaves extends JFrame {

	private JPanel frmConChaves;
	private JTextField txtfuncionario;
	private JTable tabchaves;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chaves frame = new Chaves();
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
	public Chaves() throws ParseException {
		setResizable(false);
		setTitle("Controle das Chaves da Empresa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 658);
		frmConChaves = new JPanel();
		frmConChaves.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmConChaves);
		frmConChaves.setLayout(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio:");
		lblFuncionrio.setForeground(Color.WHITE);
		lblFuncionrio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblFuncionrio.setBounds(49, 25, 136, 35);
		frmConChaves.add(lblFuncionrio);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(631, 25, 134, 35);
		frmConChaves.add(txtdata);

		String hoje, data;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 216, 779, 231);
		frmConChaves.add(scrollPane);

		tabchaves = new JTable();
		tabchaves.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabchaves);

		txtfuncionario = new JTextField();
		txtfuncionario.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtfuncionario.setColumns(10);
		txtfuncionario.setBounds(181, 25, 377, 35);
		frmConChaves.add(txtfuncionario);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(570, 25, 67, 35);
		frmConChaves.add(lblData);

		JComboBox combochave = new JComboBox();
		combochave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combochave.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combochave.setBounds(459, 82, 347, 35);
		frmConChaves.add(combochave);

		CRUD sql = new CRUD();

		try {
			for (CadChaveBean list : sql.BuscaChave()) {
				combochave.addItem(list.getNome());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}

		JLabel lblRetirada = new JLabel("Retirada:");
		lblRetirada.setForeground(Color.WHITE);
		lblRetirada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblRetirada.setBounds(49, 82, 111, 35);
		frmConChaves.add(lblRetirada);

		JFormattedTextField txthoraretirada = new JFormattedTextField(new MaskFormatter("##:##"));
		txthoraretirada.setHorizontalAlignment(SwingConstants.CENTER);
		txthoraretirada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthoraretirada.setBounds(147, 82, 112, 35);
		frmConChaves.add(txthoraretirada);

		JLabel lblEntrega = new JLabel("Entrega:");
		lblEntrega.setForeground(Color.WHITE);
		lblEntrega.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblEntrega.setBounds(49, 467, 86, 35);
		frmConChaves.add(lblEntrega);

		JFormattedTextField txthoraentrega = new JFormattedTextField(new MaskFormatter("##:##"));
		txthoraentrega.setHorizontalAlignment(SwingConstants.CENTER);
		txthoraentrega.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthoraentrega.setBounds(147, 467, 112, 35);
		frmConChaves.add(txthoraentrega);

		JLabel lblTipoDaChave = new JLabel("Tipo da Chave:");
		lblTipoDaChave.setForeground(Color.WHITE);
		lblTipoDaChave.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTipoDaChave.setBounds(301, 82, 164, 35);
		frmConChaves.add(lblTipoDaChave);

		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setForeground(Color.WHITE);
		lblObservao.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblObservao.setBounds(49, 143, 164, 35);
		frmConChaves.add(lblObservao);

		JTextArea txtobservacao = new JTextArea();
		txtobservacao.setText("OK");
		txtobservacao.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtobservacao.setBounds(181, 145, 303, 30);
		frmConChaves.add(txtobservacao);
		
		data = masc.convertDate(txtdata.getText());
		DefaultTableModel modelo = new DefaultTableModel();

		tabchaves.setModel(modelo);

		modelo.addColumn("Funcionário");
		modelo.addColumn("Data");
		modelo.addColumn("Hora Retirada");
		modelo.addColumn("Hora Entrega");
		modelo.addColumn("Tipo");
		modelo.addColumn("Observação");
		modelo.addColumn("Id");

		tabchaves.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabchaves.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabchaves.getColumnModel().getColumn(2).setPreferredWidth(40);
		tabchaves.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabchaves.getColumnModel().getColumn(4).setPreferredWidth(80);
		tabchaves.getColumnModel().getColumn(5).setPreferredWidth(20);
		tabchaves.getColumnModel().getColumn(6).setPreferredWidth(10);

		tabchaves.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabchaves
				.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (ChavesBean list : sql.BuscaDataChave(data)) {
				modelo.addRow(new Object[] { list.getFuncionario(), list.getData(),
						list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),
						list.getId() });
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtfuncionario.getText().equals("") || txtdata.getText().equals("")
						|| txthoraretirada.getText().equals("") || txtobservacao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {

					CRUD sql = new CRUD();
					String data;
					data = masc.convertDate(txtdata.getText());

					ChavesBean bean = new ChavesBean();

					bean.setFuncionario(txtfuncionario.getText());
					bean.setData(data);
					bean.setEntrega(txthoraentrega.getText());
					
					bean.setRetirada(txthoraretirada.getText());
					bean.setTipo(combochave.getSelectedItem().toString());
					bean.setObservacao(txtobservacao.getText());
					Date dia = null;
					String dataTexto = new String(txtdata.getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

					try {
						format.setLenient(false);
						dia = format.parse(dataTexto);
						try {
							sql.SalvarChaves(bean);
							JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
							txtfuncionario.setText("");
							txthoraretirada.setText("");
							
							txtobservacao.setText("");
							txtfuncionario.grabFocus();

							
							DefaultTableModel modelo = new DefaultTableModel();

							tabchaves.setModel(modelo);

							modelo.addColumn("Funcionário");
							modelo.addColumn("Data");
							modelo.addColumn("Hora Retirada");
							modelo.addColumn("Hora Entrega");
							modelo.addColumn("Tipo");
							modelo.addColumn("Observação");
							modelo.addColumn("Id");

							tabchaves.getColumnModel().getColumn(0).setPreferredWidth(50);
							tabchaves.getColumnModel().getColumn(1).setPreferredWidth(40);
							tabchaves.getColumnModel().getColumn(2).setPreferredWidth(40);
							tabchaves.getColumnModel().getColumn(3).setPreferredWidth(40);
							tabchaves.getColumnModel().getColumn(4).setPreferredWidth(80);
							tabchaves.getColumnModel().getColumn(5).setPreferredWidth(20);
							tabchaves.getColumnModel().getColumn(6).setPreferredWidth(10);

							tabchaves.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabchaves
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								for (ChavesBean list : sql.BuscaDataChave(data)) {
									modelo.addRow(new Object[] { list.getFuncionario(), list.getData(),
											list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),
											list.getId() });
								}
							} catch (Exception e1) {
								System.out.println(e1);
							}
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
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(Chaves.class.getResource("/imagens/btretiradabig.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(Chaves.class.getResource("/imagens/btretirada.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(523, 130, 135, 59);
		frmConChaves.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(Chaves.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(Chaves.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(555, 527, 135, 59);
		frmConChaves.add(btsair);

		JButton btcadchave = new JButton("");
		btcadchave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadChave tela;
				try {
					tela = new CadChave();
					tela.setVisible(true);
					dispose();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btcadchave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadchave.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadchave.setRolloverIcon(new ImageIcon(Chaves.class.getResource("/imagens/cadchavebig.png")));
			}
		});
		btcadchave.setIcon(new ImageIcon(Chaves.class.getResource("/imagens/cadchave.png")));
		btcadchave.setFocusable(false);
		btcadchave.setContentAreaFilled(false);
		btcadchave.setBorderPainted(false);
		btcadchave.setBounds(357, 527, 135, 59);
		frmConChaves.add(btcadchave);

		JButton btcadastroentrega = new JButton("");
		btcadastroentrega.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();
				
				ChavesBean bean = new ChavesBean();
				
				
				bean.setFuncionario(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 0).toString());
				bean.setData(data);
				bean.setRetirada(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 2).toString());
				bean.setEntrega(txthoraentrega.getText());
				bean.setTipo(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 4).toString());
				bean.setObservacao(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 5).toString());
				bean.setId( Integer.parseInt(String.valueOf(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 6).toString())));
				
				try {
					sql.AtualizaChave(bean);
					JOptionPane.showMessageDialog(null,"Cadastro de Entrega Concluída com Sucesso");
					txthoraentrega.setText("");
					DefaultTableModel modelo = new DefaultTableModel();

					tabchaves.setModel(modelo);

					modelo.addColumn("Funcionário");
					modelo.addColumn("Data");
					modelo.addColumn("Hora Retirada");
					modelo.addColumn("Hora Entrega");
					modelo.addColumn("Tipo");
					modelo.addColumn("Observação");
					modelo.addColumn("Id");

					tabchaves.getColumnModel().getColumn(0).setPreferredWidth(50);
					tabchaves.getColumnModel().getColumn(1).setPreferredWidth(40);
					tabchaves.getColumnModel().getColumn(2).setPreferredWidth(40);
					tabchaves.getColumnModel().getColumn(3).setPreferredWidth(40);
					tabchaves.getColumnModel().getColumn(4).setPreferredWidth(80);
					tabchaves.getColumnModel().getColumn(5).setPreferredWidth(20);
					tabchaves.getColumnModel().getColumn(6).setPreferredWidth(10);

					tabchaves.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabchaves
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (ChavesBean list : sql.BuscaDataChave(data)) {
							modelo.addRow(new Object[] { list.getFuncionario(), list.getData(),
									list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),
									list.getId() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btcadastroentrega.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastroentrega.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastroentrega
						.setRolloverIcon(new ImageIcon(Chaves.class.getResource("/imagens/btentregabig.png")));
			}
		});
		btcadastroentrega.setIcon(new ImageIcon(Chaves.class.getResource("/imagens/btentrega.png")));
		btcadastroentrega.setFocusable(false);
		btcadastroentrega.setContentAreaFilled(false);
		btcadastroentrega.setBorderPainted(false);
		btcadastroentrega.setBounds(143, 527, 135, 59);
		frmConChaves.add(btcadastroentrega);
		
		JButton btbuscar = new JButton("");
		btbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btbuscar
				.setRolloverIcon(new ImageIcon(Chaves.class.getResource("/imagens/botaopesquisarbig.png")));
			}
		});
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					for (ChavesBean list : sql.BuscarTodas()) {
						modelo.addRow(new Object[] { list.getFuncionario(), list.getData(),
								list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),
								list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btbuscar.setIcon(new ImageIcon(Chaves.class.getResource("/imagens/botaopesquisar.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.setBounds(658, 130, 135, 59);
		frmConChaves.add(btbuscar);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						Chaves.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(0, 0, 850, 621);
				frmConChaves.add(label);
		setLocationRelativeTo(null);

	}
}
