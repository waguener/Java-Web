package br.com.olgber.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Limpar;
import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadVeiculoBean;
import br.com.olgber.bean.MovVeiculoBean;

public class MovVeiculo extends JFrame {

	private JPanel frmmovveiculo;
	private JTextField txtkminicio;
	private JTextField txtkmfinal;
	private JTable tabmovveiculo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovVeiculo frame = new MovVeiculo();
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
	public MovVeiculo() throws ParseException {
		setResizable(false);
		setTitle("Movimenta\u00E7\u00F5es dos Ve\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1002, 673);
		frmmovveiculo = new JPanel();
		frmmovveiculo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmmovveiculo);
		frmmovveiculo.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel lblVeculo = new JLabel("Ve\u00EDculo:");
		lblVeculo.setForeground(Color.WHITE);
		lblVeculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblVeculo.setBounds(34, 13, 95, 35);
		frmmovveiculo.add(lblVeculo);

		CRUD sql = new CRUD();

		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio:");
		lblFuncionrio.setForeground(Color.WHITE);
		lblFuncionrio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblFuncionrio.setBounds(34, 61, 124, 35);
		frmmovveiculo.add(lblFuncionrio);

		JComboBox combofuncionario = new JComboBox();
		combofuncionario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		combofuncionario.setEditable(true);
		combofuncionario.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combofuncionario.setBounds(170, 61, 273, 35);
		frmmovveiculo.add(combofuncionario);

		try {
			for (MovVeiculoBean list : sql.BuscaMovFuncionario()) {
				combofuncionario.addItem(list.getFuncionario());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}

		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setForeground(Color.WHITE);
		lblDestino.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDestino.setBounds(455, 61, 95, 35);
		frmmovveiculo.add(lblDestino);

		JComboBox combodestino = new JComboBox();
		combodestino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combodestino.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combodestino.setEditable(true);
		combodestino.setBounds(543, 61, 441, 35);
		frmmovveiculo.add(combodestino);

		try {
			for (MovVeiculoBean list : sql.BuscaMovDestino()) {
				combodestino.addItem(list.getDestino());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}

		JLabel lblHoraDaSada = new JLabel("Hora da Sa\u00EDda:");
		lblHoraDaSada.setForeground(Color.WHITE);
		lblHoraDaSada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraDaSada.setBounds(368, 124, 157, 35);
		frmmovveiculo.add(lblHoraDaSada);

		JLabel lblKminicial = new JLabel("KM Inicial:");
		lblKminicial.setForeground(Color.WHITE);
		lblKminicial.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblKminicial.setBounds(38, 124, 112, 35);
		frmmovveiculo.add(lblKminicial);

		JLabel lblDataSada = new JLabel("Data Sa\u00EDda:");
		lblDataSada.setForeground(Color.WHITE);
		lblDataSada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDataSada.setBounds(554, 13, 120, 35);
		frmmovveiculo.add(lblDataSada);

		JLabel lblDataRetorno = new JLabel("Data Retorno:");
		lblDataRetorno.setForeground(Color.WHITE);
		lblDataRetorno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblDataRetorno.setBounds(330, 378, 153, 35);
		frmmovveiculo.add(lblDataRetorno);

		JLabel lblCombustvel = new JLabel("Combust\u00EDvel:");
		lblCombustvel.setForeground(Color.WHITE);
		lblCombustvel.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblCombustvel.setBounds(77, 456, 135, 35);
		frmmovveiculo.add(lblCombustvel);

		JLabel lblGasolinaal = new JLabel("Gasolina\\\u00C1lcool");
		lblGasolinaal.setForeground(Color.WHITE);
		lblGasolinaal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblGasolinaal.setBounds(240, 603, 157, 35);
		frmmovveiculo.add(lblGasolinaal);

		JLabel lblHoraDoRetorno = new JLabel("Hora do Retorno:");
		lblHoraDoRetorno.setForeground(Color.WHITE);
		lblHoraDoRetorno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraDoRetorno.setBounds(651, 378, 172, 35);
		frmmovveiculo.add(lblHoraDoRetorno);

		JLabel lblKmFinal = new JLabel("KM Final:");
		lblKmFinal.setForeground(Color.WHITE);
		lblKmFinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblKmFinal.setBounds(34, 378, 95, 35);
		frmmovveiculo.add(lblKmFinal);

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(213, 439, 208, 162);
		frmmovveiculo.add(panel);
		panel.setLayout(null);

		JSlider slidergasolina = new JSlider();
		slidergasolina.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slidergasolina.setBounds(3, 0, 205, 55);
		panel.add(slidergasolina);
		slidergasolina.setValue(5);
		slidergasolina.setSnapToTicks(true);
		slidergasolina.setPaintTicks(true);
		slidergasolina.setPaintLabels(true);
		slidergasolina.setMinorTickSpacing(1);
		slidergasolina.setMaximum(4);
		slidergasolina.setMajorTickSpacing(1);
		slidergasolina.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/mostrador5.png")));
		label_1.setBounds(9, 53, 196, 103);
		panel.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(466, 439, 208, 162);
		frmmovveiculo.add(panel_1);

		JSlider slidergas = new JSlider();
		slidergas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slidergas.setValue(5);
		slidergas.setSnapToTicks(true);
		slidergas.setPaintTicks(true);
		slidergas.setPaintLabels(true);
		slidergas.setMinorTickSpacing(1);
		slidergas.setMaximum(4);
		slidergas.setMajorTickSpacing(1);
		slidergas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		slidergas.setBounds(3, 3, 205, 55);
		panel_1.add(slidergas);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/mostrador5.png")));
		label_2.setBounds(9, 53, 196, 103);
		panel_1.add(label_2);

		JComboBox comboveiculo = new JComboBox();
		comboveiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboveiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();
				String veiculo, gasolina, gas;

				try {
					boolean modelo;
					modelo = sql.Veiculo(comboveiculo.getSelectedItem().toString());

					if (modelo = true) {

						veiculo = sql.BuscaKM(comboveiculo.getSelectedItem().toString());
						gasolina = sql.BuscaGasolina(comboveiculo.getSelectedItem().toString());
						gas = sql.BuscaGas(comboveiculo.getSelectedItem().toString());
						txtkminicio.setText(veiculo);
						slidergasolina.setValue(Integer.parseInt(gasolina));
						slidergas.setValue(Integer.parseInt(gas));
						if (slidergasolina.getValue() <= 1) {
							JOptionPane.showMessageDialog(null, "Pouco combustível, Avisar para abastecer", "Atenção",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (Exception e2) {

				} finally {

				}

			}
		});
		comboveiculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		comboveiculo.setBounds(128, 13, 172, 35);
		frmmovveiculo.add(comboveiculo);

		try {
			for (CadVeiculoBean list : sql.BuscaVeiculo()) {
				comboveiculo.addItem(list.getModelo());
			}
		} catch (Exception e2) {
			System.out.println("Erro2_buscar" + e2);
		}

		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setBounds(686, 13, 131, 35);
		frmmovveiculo.add(txtdatainicio);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 172, 972, 193);
		frmmovveiculo.add(scrollPane);

		tabmovveiculo = new JTable();
		tabmovveiculo.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabmovveiculo);

		String hoje, data;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdatainicio.setText(hoje);

		data = masc.convertDate(txtdatainicio.getText());

		DefaultTableModel modelo = new DefaultTableModel();

		tabmovveiculo.setModel(modelo);

		modelo.addColumn("Veículo");
		modelo.addColumn("Funcionário");
		modelo.addColumn("Destino");
		modelo.addColumn("Hora da Saída");
		modelo.addColumn("Hora do Retorno");
		modelo.addColumn("Data de Saída");
		modelo.addColumn("Data do Retorno");
		modelo.addColumn("KM Inicial");
		modelo.addColumn("KM Final");
		modelo.addColumn("ID");

		tabmovveiculo.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabmovveiculo.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabmovveiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabmovveiculo.getColumnModel().getColumn(3).setPreferredWidth(30);
		tabmovveiculo.getColumnModel().getColumn(4).setPreferredWidth(40);
		tabmovveiculo.getColumnModel().getColumn(5).setPreferredWidth(60);
		tabmovveiculo.getColumnModel().getColumn(6).setPreferredWidth(60);
		tabmovveiculo.getColumnModel().getColumn(7).setPreferredWidth(40);
		tabmovveiculo.getColumnModel().getColumn(8).setPreferredWidth(40);
		tabmovveiculo.getColumnModel().getColumn(9).setPreferredWidth(5);

		tabmovveiculo.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovveiculo.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		try {
			System.out.println(data);
			for (MovVeiculoBean list : sql.BuscaMovHoje(data)) {
				modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(), list.getDestino(),
						list.getHorasaida(), list.getHoraretorno(), list.getDatasaida(), list.getDataretorno(),
						list.getKminicio(), list.getKmfinal(), list.getId() });
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(477, 378, 131, 35);
		frmmovveiculo.add(txtdatafinal);

		JFormattedTextField txthorasaida = new JFormattedTextField(new MaskFormatter("##:##"));
		txthorasaida.setHorizontalAlignment(SwingConstants.CENTER);
		txthorasaida.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthorasaida.setBounds(527, 124, 131, 35);
		frmmovveiculo.add(txthorasaida);

		JFormattedTextField txthoraretorno = new JFormattedTextField(new MaskFormatter("##:##"));
		txthoraretorno.setHorizontalAlignment(SwingConstants.CENTER);
		txthoraretorno.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txthoraretorno.setBounds(835, 378, 131, 35);
		frmmovveiculo.add(txthoraretorno);

		txtkminicio = new JTextField();
		txtkminicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtkminicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtkminicio.setColumns(10);
		txtkminicio.setBounds(163, 124, 164, 35);
		frmmovveiculo.add(txtkminicio);

		txtkmfinal = new JTextField();
		txtkmfinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtkmfinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtkmfinal.setColumns(10);
		txtkmfinal.setBounds(131, 378, 164, 35);
		frmmovveiculo.add(txtkmfinal);

		JLabel lblGs = new JLabel("G\u00E1s");
		lblGs.setForeground(Color.WHITE);
		lblGs.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblGs.setBounds(546, 603, 51, 35);
		frmmovveiculo.add(lblGs);

		JButton btcadastrar = new JButton("");
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CRUD sql = new CRUD();

				if (combofuncionario.getSelectedItem().toString().equals("") || txthorasaida.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Os campos precisam ser preenchidos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String datasaida;
					datasaida = masc.convertDate(txtdatainicio.getText());

					MovVeiculoBean bean = new MovVeiculoBean();

					bean.setVeiculo(comboveiculo.getSelectedItem().toString());
					bean.setFuncionario(combofuncionario.getSelectedItem().toString());
					bean.setDestino(combodestino.getSelectedItem().toString());
					bean.setHorasaida(txthorasaida.getText());
					bean.setDatasaida(datasaida);
					bean.setKminicio(txtkminicio.getText());

					Date diasaida = null;

					String dataTexto = new String(txtdatainicio.getText());
					String datatexto = new String(txtdatafinal.getText());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						format.setLenient(false);
						diasaida = format.parse(dataTexto);

						try {

							sql.SalvaMovVeiculo(bean);
							System.out.println("Veiculo salvo");

							JOptionPane.showMessageDialog(null, "Cadastrado a Saída do Veículo com Sucesso!");
							combofuncionario.setSelectedItem("");
							txthorasaida.setText("");
							combodestino.setSelectedItem("");
							txtkminicio.setText("");
							comboveiculo.grabFocus();

							DefaultTableModel modelo = new DefaultTableModel();

							tabmovveiculo.setModel(modelo);

							modelo.addColumn("Veículo");
							modelo.addColumn("Funcionário");
							modelo.addColumn("Destino");
							modelo.addColumn("Hora da Saída");
							modelo.addColumn("Hora do Retorno");
							modelo.addColumn("Data de Saída");
							modelo.addColumn("Data do Retorno");
							modelo.addColumn("KM Inicial");
							modelo.addColumn("KM Final");
							modelo.addColumn("ID");

							tabmovveiculo.getColumnModel().getColumn(0).setPreferredWidth(30);
							tabmovveiculo.getColumnModel().getColumn(1).setPreferredWidth(50);
							tabmovveiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
							tabmovveiculo.getColumnModel().getColumn(3).setPreferredWidth(30);
							tabmovveiculo.getColumnModel().getColumn(4).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(5).setPreferredWidth(60);
							tabmovveiculo.getColumnModel().getColumn(6).setPreferredWidth(60);
							tabmovveiculo.getColumnModel().getColumn(7).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(8).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(9).setPreferredWidth(5);

							tabmovveiculo.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovveiculo
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);

							try {
								for (MovVeiculoBean list : sql.BuscaMovHoje(data)) {
									modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(),
											list.getDestino(), list.getHorasaida(), list.getHoraretorno(),
											list.getDatasaida(), list.getDataretorno(), list.getKminicio(),
											list.getKmfinal(), list.getId() });
								}
							} catch (Exception e1) {
								System.out.println(e1);
							}
						} catch (Exception e) {
							System.out.println("Erro1" + e);
						}
					} catch (ParseException e) {
						System.out.println("Erro2" + e);
						JOptionPane.showMessageDialog(null, "Data inválida. Tente novamente!", "AVISO",
								JOptionPane.WARNING_MESSAGE);
						txtdatainicio.setText("");
						txtdatafinal.setText("");
						txtdatainicio.grabFocus();
					}
				}
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar
						.setRolloverIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/btcadsaidabig.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/btcadsaida.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(687, 114, 135, 59);
		frmmovveiculo.add(btcadastrar);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadDocumentos.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(748, 542, 157, 59);
		frmmovveiculo.add(btsair);

		JButton btretorno = new JButton("");
		btretorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String model;
				int id, kminicio, kmfinal;
				id = Integer.parseInt(String
						.valueOf(tabmovveiculo.getModel().getValueAt(tabmovveiculo.getSelectedRow(), 9).toString()));
				kminicio = Integer.parseInt(String
						.valueOf(tabmovveiculo.getModel().getValueAt(tabmovveiculo.getSelectedRow(), 7).toString()));
				kmfinal = Integer.parseInt(txtkmfinal.getText());
				model = tabmovveiculo.getModel().getValueAt(tabmovveiculo.getSelectedRow(), 0).toString();
				

				if (txtkmfinal.getText().equals("") || txtdatafinal.getText().equals("")
						|| txthoraretorno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Os campos precisam ser preenchidos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (kmfinal >= kminicio) {

						String datafinal;
						datafinal = masc.convertDate(txtdatafinal.getText());

						CadVeiculoBean atualiza = new CadVeiculoBean();

						atualiza.setGasolina(String.valueOf(slidergasolina.getValue()));
						atualiza.setGas(String.valueOf(slidergasolina.getValue()));
						atualiza.setModelo(model);

						MovVeiculoBean movatualiza = new MovVeiculoBean();

						movatualiza.setHoraretorno(txthoraretorno.getText());
						movatualiza.setDataretorno(datafinal);
						movatualiza.setKmfinal(txtkmfinal.getText());
						movatualiza.setGasolina(String.valueOf(slidergasolina.getValue()));
						movatualiza.setGas(String.valueOf(slidergasolina.getValue()));

						movatualiza.setId(id);

						try {
							sql.AtualizaCombustivel(atualiza);
							sql.AtualizaMovVeiculo(movatualiza);
							JOptionPane.showMessageDialog(null, "Retorno Cadastrado com Sucesso!");

							DefaultTableModel modelo = new DefaultTableModel();

							tabmovveiculo.setModel(modelo);

							modelo.addColumn("Veículo");
							modelo.addColumn("Funcionário");
							modelo.addColumn("Destino");
							modelo.addColumn("Hora da Saída");
							modelo.addColumn("Hora do Retorno");
							modelo.addColumn("Data de Saída");
							modelo.addColumn("Data do Retorno");
							modelo.addColumn("KM Inicial");
							modelo.addColumn("KM Final");
							modelo.addColumn("ID");

							tabmovveiculo.getColumnModel().getColumn(0).setPreferredWidth(30);
							tabmovveiculo.getColumnModel().getColumn(1).setPreferredWidth(50);
							tabmovveiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
							tabmovveiculo.getColumnModel().getColumn(3).setPreferredWidth(30);
							tabmovveiculo.getColumnModel().getColumn(4).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(5).setPreferredWidth(60);
							tabmovveiculo.getColumnModel().getColumn(6).setPreferredWidth(60);
							tabmovveiculo.getColumnModel().getColumn(7).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(8).setPreferredWidth(40);
							tabmovveiculo.getColumnModel().getColumn(9).setPreferredWidth(5);

							tabmovveiculo.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovveiculo
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);

							try {
								for (MovVeiculoBean list : sql.BuscaMovHoje(data)) {
									modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(),
											list.getDestino(), list.getHorasaida(), list.getHoraretorno(),
											list.getDatasaida(), list.getDataretorno(), list.getKminicio(),
											list.getKmfinal(), list.getId() });
								}
							} catch (Exception e1) {
								System.out.println(e1);
							}

						} catch (Exception e) {
							System.out.println(e);
						}

					} else {
						JOptionPane.showMessageDialog(null, " Atenção o KM Final não Pode ser Menor que o KM Inicial");
					}
				}
			}
		});
		btretorno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btretorno.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btretorno.setRolloverIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/btcadretornobig.png")));
			}
		});
		btretorno.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/btcadretorno.png")));
		btretorno.setFocusable(false);
		btretorno.setContentAreaFilled(false);
		btretorno.setBorderPainted(false);
		btretorno.setBounds(748, 459, 157, 59);
		frmmovveiculo.add(btretorno);

		JButton bttodos = new JButton("");
		bttodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel modelo = new DefaultTableModel();

				tabmovveiculo.setModel(modelo);

				modelo.addColumn("Veículo");
				modelo.addColumn("Funcionário");
				modelo.addColumn("Destino");
				modelo.addColumn("Hora da Saída");
				modelo.addColumn("Hora do Retorno");
				modelo.addColumn("Data de Saída");
				modelo.addColumn("Data do Retorno");
				modelo.addColumn("KM Inicial");
				modelo.addColumn("KM Final");
				modelo.addColumn("ID");

				tabmovveiculo.getColumnModel().getColumn(0).setPreferredWidth(30);
				tabmovveiculo.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabmovveiculo.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabmovveiculo.getColumnModel().getColumn(3).setPreferredWidth(30);
				tabmovveiculo.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabmovveiculo.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabmovveiculo.getColumnModel().getColumn(6).setPreferredWidth(60);
				tabmovveiculo.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabmovveiculo.getColumnModel().getColumn(8).setPreferredWidth(40);
				tabmovveiculo.getColumnModel().getColumn(9).setPreferredWidth(5);

				tabmovveiculo.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovveiculo
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);

				try {
					for (MovVeiculoBean list : sql.BuscaMovTotal()) {
						modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(), list.getDestino(),
								list.getHorasaida(), list.getHoraretorno(), list.getDatasaida(), list.getDataretorno(),
								list.getKminicio(), list.getKmfinal(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		bttodos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				bttodos.setRolloverIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/botaopesquisarbig.png")));
			}
		});
		bttodos.setIcon(new ImageIcon(MovVeiculo.class.getResource("/imagens/botaopesquisar.png")));
		bttodos.setFocusable(false);
		bttodos.setContentAreaFilled(false);
		bttodos.setBorderPainted(false);
		bttodos.setBounds(849, 114, 135, 59);
		frmmovveiculo.add(bttodos);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				MovVeiculo.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 996, 664);
		frmmovveiculo.add(label);
	}
}
