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
import br.com.olgber.bean.CadVisitanteBean;
import br.com.olgber.bean.VisitasCadastradasBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VisitasCadastradas extends JFrame {

	private JPanel frmhistvisitas;
	private JTable tabvisitas;
	private JTable tabvisitasaidas;
	private JTextField txtnome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisitasCadastradas frame = new VisitasCadastradas();
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
	public VisitasCadastradas() throws ParseException {
		setTitle("Visitas Cadastradas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1002, 673);
		frmhistvisitas = new JPanel();
		frmhistvisitas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmhistvisitas);
		frmhistvisitas.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(6, 47, 985, 167);
		frmhistvisitas.add(scrollPane);

		tabvisitas = new JTable();
		tabvisitas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabvisitas);

		CRUD sql = new CRUD();

		DefaultTableModel modelo = new DefaultTableModel();

		tabvisitas.setModel(modelo);

		modelo.addColumn("Nome");
		modelo.addColumn("RG");
		modelo.addColumn("Empresa");
		modelo.addColumn("Data Cadastro");
		modelo.addColumn("Autorização");
		modelo.addColumn("ID");

		tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(140);
		tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
		tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(120);
		tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
		tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(5);

		tabvisitas.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (CadVisitanteBean list : sql.BuscaVisitantesTotal()) {
				modelo.addRow(new Object[] { list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
						list.getMotivo(), list.getId() });

			}

		} catch (Exception e) {
			System.out.println(e);

		}

		JFormattedTextField txtdatavisita = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatavisita.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatavisita.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatavisita.setBounds(190, 227, 135, 35);
		frmhistvisitas.add(txtdatavisita);
		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdatavisita.setText(hoje);

		JLabel lblDataDaVisita = new JLabel("Data da Visita:");
		lblDataDaVisita.setForeground(Color.WHITE);
		lblDataDaVisita.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDataDaVisita.setBounds(34, 227, 154, 35);
		frmhistvisitas.add(lblDataDaVisita);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 377, 985, 185);
		frmhistvisitas.add(scrollPane_1);

		tabvisitasaidas = new JTable();
		tabvisitasaidas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane_1.setViewportView(tabvisitasaidas);

		JLabel lblHoraDaEntrada = new JLabel("Hora da Entrada:");
		lblHoraDaEntrada.setForeground(Color.WHITE);
		lblHoraDaEntrada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraDaEntrada.setBounds(349, 227, 171, 35);
		frmhistvisitas.add(lblHoraDaEntrada);

		JLabel lblHoraDaSada = new JLabel("Hora da Sa\u00EDda:");
		lblHoraDaSada.setForeground(Color.WHITE);
		lblHoraDaSada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraDaSada.setBounds(34, 587, 154, 35);
		frmhistvisitas.add(lblHoraDaSada);

		JTextArea txtmotivo = new JTextArea();
		txtmotivo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtmotivo.setBounds(204, 273, 322, 35);
		frmhistvisitas.add(txtmotivo);

		JLabel lblMotivoDaVisita = new JLabel("Motivo da Visita:");
		lblMotivoDaVisita.setForeground(Color.WHITE);
		lblMotivoDaVisita.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblMotivoDaVisita.setBounds(34, 271, 181, 35);
		frmhistvisitas.add(lblMotivoDaVisita);

		JFormattedTextField txthoraentrada = new JFormattedTextField(new MaskFormatter("##:##"));
		txthoraentrada.setHorizontalAlignment(SwingConstants.CENTER);
		txthoraentrada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthoraentrada.setBounds(534, 227, 116, 35);
		frmhistvisitas.add(txthoraentrada);

		JFormattedTextField txthorasaida = new JFormattedTextField(new MaskFormatter("##:##"));
		txthorasaida.setHorizontalAlignment(SwingConstants.CENTER);
		txthorasaida.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthorasaida.setBounds(197, 587, 116, 35);
		frmhistvisitas.add(txthorasaida);

		JButton btsalvar = new JButton("");
		btsalvar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsalvar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsalvar.setRolloverIcon(
						new ImageIcon(VisitasCadastradas.class.getResource("/imagens/btcadentradabig.png")));
			}
		});
		btsalvar.setIcon(new ImageIcon(VisitasCadastradas.class.getResource("/imagens/btcadentrada.png")));
		btsalvar.setFocusable(false);
		btsalvar.setContentAreaFilled(false);
		btsalvar.setBorderPainted(false);
		btsalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txthoraentrada.getText().equals("") || txthorasaida.getText().equals("")
						|| txtdatavisita.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {

					CRUD sql = new CRUD();

					String datavisita;

					datavisita = masc.convertDate(txtdatavisita.getText());

					VisitasCadastradasBean bean = new VisitasCadastradasBean();

					bean.setNome(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 0).toString());
					bean.setRg(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 1).toString());
					bean.setEmpresa(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 2).toString());
					bean.setData(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 3).toString());
					bean.setDatavisita(datavisita);
					bean.setHoraentrada(txthoraentrada.getText());
					bean.setHorasaida(txthorasaida.getText());
					bean.setMotivo(txtmotivo.getText());

					Date dia = null;
					String dataTexto = new String(txtdatavisita.getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

					try {
						format.setLenient(false);
						dia = format.parse(dataTexto);
						try {
							sql.SalvaHistVisita(bean);
							JOptionPane.showMessageDialog(null, "Salvo com Sucesso");

							txtmotivo.setText("");
							txthoraentrada.setText("");
							DefaultTableModel modelo2 = new DefaultTableModel();
							tabvisitasaidas.setModel(modelo2);

							String data;
							data = masc.convertDate(txtdatavisita.getText());
							modelo2.addColumn("Nome");
							modelo2.addColumn("RG");
							modelo2.addColumn("Empresa");
							modelo2.addColumn("Data da Visita");
							modelo2.addColumn("Hora da Entrada");
							modelo2.addColumn("Hora da Saída");
							modelo2.addColumn("Motivo");
							modelo2.addColumn("ID");

							tabvisitasaidas.getColumnModel().getColumn(0).setPreferredWidth(80);
							tabvisitasaidas.getColumnModel().getColumn(1).setPreferredWidth(80);
							tabvisitasaidas.getColumnModel().getColumn(2).setPreferredWidth(80);
							tabvisitasaidas.getColumnModel().getColumn(3).setPreferredWidth(60);
							tabvisitasaidas.getColumnModel().getColumn(4).setPreferredWidth(40);
							tabvisitasaidas.getColumnModel().getColumn(5).setPreferredWidth(40);
							tabvisitasaidas.getColumnModel().getColumn(6).setPreferredWidth(80);
							tabvisitasaidas.getColumnModel().getColumn(7).setPreferredWidth(10);

							tabvisitasaidas.setRowHeight(30);

							DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tabvisitasaidas
									.getDefaultRenderer(JLabel.class);
							renderer2.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								for (VisitasCadastradasBean lista : sql.BuscaVisitaHoje(data)) {

									modelo2.addRow(new Object[] { lista.getNome(), lista.getRg(), lista.getEmpresa(),
											lista.getDatavisita(), lista.getHoraentrada(), lista.getHorasaida(),
											lista.getMotivo(), lista.getId() });
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
						txtdatavisita.setText("");
						txtdatavisita.grabFocus();
					}
				}
			}
		});
		btsalvar.setBounds(521, 261, 146, 63);
		frmhistvisitas.add(btsalvar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(
						new ImageIcon(VisitasCadastradas.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(VisitasCadastradas.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setBounds(809, 575, 149, 63);
		frmhistvisitas.add(btsair);

		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Cadastro desta Visita?",
							"Atenção", JOptionPane.YES_OPTION);

					if (confirmar == JOptionPane.YES_OPTION) {

						sql.ExcluirVisitas(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 0).toString());

						JOptionPane.showMessageDialog(null, "Visita Excluida com Sucesso");
						((DefaultTableModel) tabvisitas.getModel()).removeRow(tabvisitas.getSelectedRow());
						tabvisitas.repaint();
						tabvisitas.revalidate();

					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(
						new ImageIcon(VisitasCadastradas.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(VisitasCadastradas.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(798, 261, 146, 63);
		frmhistvisitas.add(btexcluir);

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String data;
				data = masc.convertDate(txtdatavisita.getText());
				DefaultTableModel modelo2 = new DefaultTableModel();
				tabvisitasaidas.setModel(modelo2);

				modelo2.addColumn("Nome");
				modelo2.addColumn("RG");
				modelo2.addColumn("Empresa");
				modelo2.addColumn("Data da Visita");
				modelo2.addColumn("Hora da Entrada");
				modelo2.addColumn("Hora da Saída");
				modelo2.addColumn("Motivo");
				modelo2.addColumn("ID");

				tabvisitasaidas.getColumnModel().getColumn(0).setPreferredWidth(80);
				tabvisitasaidas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitasaidas.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvisitasaidas.getColumnModel().getColumn(3).setPreferredWidth(60);
				tabvisitasaidas.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabvisitasaidas.getColumnModel().getColumn(5).setPreferredWidth(40);
				tabvisitasaidas.getColumnModel().getColumn(6).setPreferredWidth(80);
				tabvisitasaidas.getColumnModel().getColumn(7).setPreferredWidth(10);

				tabvisitasaidas.setRowHeight(30);

				DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tabvisitasaidas
						.getDefaultRenderer(JLabel.class);
				renderer2.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (VisitasCadastradasBean lista : sql.BuscaVisitaHoje(data)) {

						modelo2.addRow(new Object[] { lista.getNome(), lista.getRg(), lista.getEmpresa(),
								lista.getDatavisita(), lista.getHoraentrada(), lista.getHorasaida(), lista.getMotivo(),
								lista.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setIcon(new ImageIcon(VisitasCadastradas.class.getResource("/imagens/lupa.png")));
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBounds(19, 312, 56, 63);
		frmhistvisitas.add(button);

		JButton btcadastrarsaida = new JButton("");
		btcadastrarsaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VisitasCadastradasBean bean = new VisitasCadastradasBean();

				bean.setHorasaida(txthorasaida.getText());
				bean.setId(Integer.parseInt(String.valueOf(
						tabvisitasaidas.getModel().getValueAt(tabvisitasaidas.getSelectedRow(), 7).toString())));
				try {
					sql.AtualizaVisitas(bean);
					JOptionPane.showMessageDialog(null, "Saída Cadastrada com Sucesso");
					DefaultTableModel modelo2 = new DefaultTableModel();
					tabvisitasaidas.setModel(modelo2);

					String data;
					data = masc.convertDate(txtdatavisita.getText());
					modelo2.addColumn("Nome");
					modelo2.addColumn("RG");
					modelo2.addColumn("Empresa");
					modelo2.addColumn("Data da Visita");
					modelo2.addColumn("Hora da Entrada");
					modelo2.addColumn("Hora da Saída");
					modelo2.addColumn("Motivo");
					modelo2.addColumn("ID");

					tabvisitasaidas.getColumnModel().getColumn(0).setPreferredWidth(80);
					tabvisitasaidas.getColumnModel().getColumn(1).setPreferredWidth(80);
					tabvisitasaidas.getColumnModel().getColumn(2).setPreferredWidth(80);
					tabvisitasaidas.getColumnModel().getColumn(3).setPreferredWidth(60);
					tabvisitasaidas.getColumnModel().getColumn(4).setPreferredWidth(40);
					tabvisitasaidas.getColumnModel().getColumn(5).setPreferredWidth(40);
					tabvisitasaidas.getColumnModel().getColumn(6).setPreferredWidth(80);
					tabvisitasaidas.getColumnModel().getColumn(7).setPreferredWidth(10);

					tabvisitasaidas.setRowHeight(30);

					DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tabvisitasaidas
							.getDefaultRenderer(JLabel.class);
					renderer2.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (VisitasCadastradasBean lista : sql.BuscaVisitaHoje(data)) {

							modelo2.addRow(new Object[] { lista.getNome(), lista.getRg(), lista.getEmpresa(),
									lista.getDatavisita(), lista.getHoraentrada(), lista.getHorasaida(),
									lista.getMotivo(), lista.getId() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}

				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btcadastrarsaida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrarsaida.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrarsaida.setRolloverIcon(
						new ImageIcon(VisitasCadastradas.class.getResource("/imagens/btcadsaidabig.png")));
			}
		});
		btcadastrarsaida.setIcon(new ImageIcon(VisitasCadastradas.class.getResource("/imagens/btcadsaida.png")));
		btcadastrarsaida.setFocusable(false);
		btcadastrarsaida.setContentAreaFilled(false);
		btcadastrarsaida.setBorderPainted(false);
		btcadastrarsaida.setBounds(325, 575, 146, 63);
		frmhistvisitas.add(btcadastrarsaida);

		JLabel lblExcluirCadastro = new JLabel("Excluir Cadastro:");
		lblExcluirCadastro.setForeground(Color.WHITE);
		lblExcluirCadastro.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblExcluirCadastro.setBounds(785, 227, 171, 35);
		frmhistvisitas.add(lblExcluirCadastro);

		JLabel lblPesquisePeloNome = new JLabel("Pesquise pelo Nome:");
		lblPesquisePeloNome.setForeground(Color.WHITE);
		lblPesquisePeloNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblPesquisePeloNome.setBounds(6, 5, 209, 35);
		frmhistvisitas.add(lblPesquisePeloNome);

		txtnome = new JTextField();
		txtnome.setHorizontalAlignment(SwingConstants.CENTER);
		txtnome.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();

				tabvisitas.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("RG");
				modelo.addColumn("Empresa");
				modelo.addColumn("Data Cadastro");
				modelo.addColumn("Autorização");
				modelo.addColumn("ID");

				tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(140);
				tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(120);
				tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(5);

				tabvisitas.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadVisitanteBean list : sql.BuscaNomeVisitacadastrada(nome)) {
						modelo.addRow(new Object[] { list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
								list.getMotivo(), list.getId() });

					}

				} catch (Exception e1) {
					System.out.println(e1);

				}
			}
		});
		txtnome.setBounds(220, 5, 116, 35);
		frmhistvisitas.add(txtnome);
		txtnome.setColumns(10);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(VisitasCadastradas.class
				.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 996, 638);
		frmhistvisitas.add(label);
	}
}
