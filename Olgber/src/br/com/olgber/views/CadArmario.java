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
import br.com.olgber.bean.CadUsuarioBean;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

public class CadArmario extends JFrame {

	private JPanel frmcadarmario;
	private JTextField txtnarmario;
	private JTable tabarmario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadArmario frame = new CadArmario();
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
	public CadArmario() throws ParseException, ClassNotFoundException, SQLException {
		setResizable(false);
		setTitle("Cadastro de Arm\u00E1rios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmcadarmario = new JPanel();
		frmcadarmario.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmcadarmario);
		frmcadarmario.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(668, 531, 150, 59);
		frmcadarmario.add(btsair);

		JComboBox combositiacao = new JComboBox();
		combositiacao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combositiacao.setModel(new DefaultComboBoxModel(new String[] { "Ok", "Quebrado" }));
		combositiacao.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combositiacao.setBounds(266, 484, 133, 35);
		frmcadarmario.add(combositiacao);

		JLabel label_1 = new JLabel("Situa\u00E7\u00E3o:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(424, 484, 95, 35);
		frmcadarmario.add(label_1);

		JLabel label_2 = new JLabel("Chave Reserva:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(106, 413, 160, 35);
		frmcadarmario.add(label_2);

		JComboBox combochavereserva = new JComboBox();
		combochavereserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combochavereserva.setModel(new DefaultComboBoxModel(new String[] { "Sim", "N\u00E3o" }));
		combochavereserva.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combochavereserva.setBounds(266, 413, 83, 35);
		frmcadarmario.add(combochavereserva);

		txtnarmario = new JTextField();
		txtnarmario.setEditable(false);
		txtnarmario.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnarmario.setColumns(10);
		txtnarmario.setBounds(645, 351, 83, 35);
		frmcadarmario.add(txtnarmario);

		JLabel label_3 = new JLabel("N\u00BA do Arm\u00E1rio:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(489, 351, 148, 35);
		frmcadarmario.add(label_3);

		JLabel label_5 = new JLabel("Tipo:");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_5.setBounds(131, 351, 59, 35);
		frmcadarmario.add(label_5);

		JComboBox combostatus = new JComboBox();
		combostatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combostatus.setModel(new DefaultComboBoxModel(new String[] { "Disponivel", "Ocupado" }));
		combostatus.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combostatus.setBounds(525, 484, 203, 35);
		frmcadarmario.add(combostatus);

		JComboBox combotipo = new JComboBox();
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.setModel(
				new DefaultComboBoxModel(new String[] { "Feminino", "Masculino", "Temp.Feminino", "Temp.Masculino" }));
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combotipo.setBounds(196, 351, 203, 35);
		frmcadarmario.add(combotipo);

		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(460, 413, 59, 35);
		frmcadarmario.add(lblData);

		JFormattedTextField txtdata = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdata.setHorizontalAlignment(SwingConstants.CENTER);
		txtdata.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdata.setBounds(525, 413, 203, 35);
		frmcadarmario.add(txtdata);
		
		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		txtdata.setText(hoje);

		JButton btcadastrar = new JButton("");
		btcadastrar.setEnabled(false);
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();
				boolean numarmario = false;
				if (txtnarmario.getText().equals("") || txtdata.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null,
							"Os campos precisam ser preenchidos para concluir o cadastro");
					limpar.Limpar(frmcadarmario);
					txtnarmario.grabFocus();
				} else {
				
						String data;
						String status = "Disponivel";
						data = masc.convertDate(txtdata.getText());
						CadArmarioBean bean = new CadArmarioBean();

						bean.setNarmario(Integer.parseInt(txtnarmario.getText()));
						bean.setStatus(status);
						bean.setChavereserva(combochavereserva.getSelectedItem().toString());
						bean.setTipo(combotipo.getSelectedItem().toString());
						bean.setData(data);
						bean.setSituacao(combositiacao.getSelectedItem().toString());
						
						Date dia = null;
			            String dataTexto = new String(txtdata.getText());
			            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			            try {
			                    format.setLenient(false);
			                    dia = format.parse(dataTexto);
						try {
							sql.SalvaArmario(bean);
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
							
							txtnarmario.grabFocus();

							DefaultTableModel modelo = new DefaultTableModel();
							tabarmario.setModel(modelo);

							modelo.addColumn("Nº Armário");
							modelo.addColumn("Status");
							modelo.addColumn("Tipo");
							modelo.addColumn("Chave Reserva");
							modelo.addColumn("Situação");
							modelo.addColumn("Data do Cadastro");
							modelo.addColumn("ID");

							tabarmario.getColumnModel().getColumn(0).setPreferredWidth(30);
							tabarmario.getColumnModel().getColumn(1).setPreferredWidth(70);
							tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
							tabarmario.getColumnModel().getColumn(3).setPreferredWidth(60);
							tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
							tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
							tabarmario.setRowHeight(30);

							DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
									.getDefaultRenderer(JLabel.class);
							renderer.setHorizontalAlignment(SwingConstants.CENTER);
							try {
								for (CadArmarioBean list : sql.BuscaArm()) {
									modelo.addRow(new Object[] { list.getNarmario(), list.getSituacao(),
											list.getChavereserva(), list.getTipo(), list.getStatus(), list.getData(),list.getId() });
								}
							} catch (Exception e) {
								System.out.println(e);
							}
						} catch (Exception e) {
							System.out.println(e);
						}
			            } catch (ParseException e) {
				     		JOptionPane.showMessageDialog(null,
				                        "Data inválida. Tente novamente!",
				                        "AVISO",
				                        JOptionPane.WARNING_MESSAGE);
				     		txtdata.setText("");
				     		txtdata.grabFocus();
				            }
					}
				
			}
		});
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(149, 531, 141, 59);
		frmcadarmario.add(btcadastrar);

		JButton btatualizar = new JButton("");
		btatualizar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btatualizar
						.setRolloverIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoatualizar2.png")));
			}
		});
		btatualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btatualizar.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoatualizar.png")));
		btatualizar.setFocusable(false);
		btatualizar.setContentAreaFilled(false);
		btatualizar.setBorderPainted(false);
		btatualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabarmario.setModel(modelo);

				modelo.addColumn("Nº Armário");
				modelo.addColumn("Status");
				modelo.addColumn("Tipo");
				modelo.addColumn("Chave Reserva");
				modelo.addColumn("Situação");
				modelo.addColumn("Data do Cadastro");
				modelo.addColumn("ID");

				tabarmario.getColumnModel().getColumn(0).setPreferredWidth(30);
				tabarmario.getColumnModel().getColumn(1).setPreferredWidth(70);
				tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
				tabarmario.getColumnModel().getColumn(3).setPreferredWidth(60);
				tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
				tabarmario.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadArmarioBean list : sql.BuscaArm()) {
						modelo.addRow(new Object[] { list.getNarmario(), list.getSituacao(), list.getChavereserva(),
								list.getTipo(), list.getStatus(), list.getData(),list.getId() });
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btatualizar.setBounds(25, 316, 66, 59);
		frmcadarmario.add(btatualizar);

		JButton btalterar = new JButton("");
		btalterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (txtnarmario.getText().equals("") || txtdata.getText().equals("  /  /    ")) {
					JOptionPane.showMessageDialog(null,
							"Os campos precisam ser preenchidos para concluir o cadastro");
					
				}else{
				
				CRUD sql = new CRUD();

				String data;
				
				data = masc.convertDate(txtdata.getText());
				CadArmarioBean bean = new CadArmarioBean();

				
				bean.setStatus(combostatus.getSelectedItem().toString());
				bean.setChavereserva(combochavereserva.getSelectedItem().toString());
				bean.setTipo(combotipo.getSelectedItem().toString());
				bean.setData(data);
				bean.setSituacao(combositiacao.getSelectedItem().toString());
				bean.setNarmario(Integer.parseInt(txtnarmario.getText()));
				bean.setId( Integer.parseInt(String.valueOf(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 6).toString())));
				Date dia = null;
	            String dataTexto = new String(txtdata.getText());
	            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	            try {
	                    format.setLenient(false);
	                    dia = format.parse(dataTexto);
				try {
					sql.AtualizaArmario(bean);
					
					JOptionPane.showMessageDialog(null, "Armário Alterado com Sucesso");
					

					DefaultTableModel modelo = new DefaultTableModel();

					tabarmario.setModel(modelo);

					modelo.addColumn("Nº Armário");
					modelo.addColumn("Status");
					modelo.addColumn("Tipo");
					modelo.addColumn("Chave Reserva");
					modelo.addColumn("Situação");
					modelo.addColumn("Data do Cadastro");
					modelo.addColumn("ID");

					tabarmario.getColumnModel().getColumn(0).setPreferredWidth(30);
					tabarmario.getColumnModel().getColumn(1).setPreferredWidth(70);
					tabarmario.getColumnModel().getColumn(2).setPreferredWidth(100);
					tabarmario.getColumnModel().getColumn(3).setPreferredWidth(60);
					tabarmario.getColumnModel().getColumn(4).setPreferredWidth(80);
					tabarmario.getColumnModel().getColumn(5).setPreferredWidth(80);
					tabarmario.getColumnModel().getColumn(6).setPreferredWidth(10);
					tabarmario.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabarmario
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (CadArmarioBean list : sql.BuscaArm()) {
							modelo.addRow(new Object[] { list.getNarmario(), list.getSituacao(), list.getChavereserva(),
									list.getTipo(), list.getStatus(), list.getData(),list.getId() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} catch (Exception e2) {
					System.out.println(e2);
					
				}
	            } catch (ParseException e) {
		     		JOptionPane.showMessageDialog(null,
		                        "Data inválida. Tente novamente!",
		                        "AVISO",
		                        JOptionPane.WARNING_MESSAGE);
		     		txtdata.setText("");
		     		txtdata.grabFocus();
		            }
			}}
		});
		btalterar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btalterar.setRolloverIcon(
						new ImageIcon(CadArmario.class.getResource("/imagens/botaoaltgrande.png")));
			}
		});
		btalterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btalterar.setEnabled(false);
		btalterar.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoalterar.png")));
		btalterar.setFocusable(false);
		btalterar.setContentAreaFilled(false);
		btalterar.setBorderPainted(false);
		btalterar.setBounds(415, 531, 152, 59);
		frmcadarmario.add(btalterar);

		JButton bteditar = new JButton("");
		bteditar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				bteditar.setRolloverIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoeditargrande.png")));
				
			}
		});
		bteditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtnarmario.setEditable(true);
				txtdata.setEditable(true);
				txtdata.grabFocus();
				txtdata.setEnabled(true);
				btalterar.setEnabled(true);
				btcadastrar.setEnabled(false);

			}
		});
		bteditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bteditar.setEnabled(false);
		bteditar.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoeditar.png")));
		bteditar.setFocusable(false);
		bteditar.setContentAreaFilled(false);
		bteditar.setBorderPainted(false);
		bteditar.setBounds(545, 531, 150, 59);
		frmcadarmario.add(bteditar);

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				int id;
				id =  Integer.parseInt(String.valueOf(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 6).toString()));
				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Armário?", "Atenção",
							JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirArmario(id);
						JOptionPane.showMessageDialog(null, "Armário Excluido com Sucesso");
						

						((DefaultTableModel) tabarmario.getModel()).removeRow(tabarmario.getSelectedRow());  
		                tabarmario.repaint();  
		                tabarmario.revalidate();
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
				
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(
						new ImageIcon(CadArmario.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setEnabled(false);
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(281, 531, 150, 59);
		frmcadarmario.add(btexcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 12, 793, 292);
		frmcadarmario.add(scrollPane);

		tabarmario = new JTable();
		tabarmario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				bteditar.setEnabled(true);
				btexcluir.setEnabled(true);
				btcadastrar.setEnabled(false);
				txtnarmario.setEditable(false);
				
				combochavereserva.setEditable(false);
				combositiacao.setEditable(false);
				combostatus.setEditable(false);
				combotipo.setEditable(false);
				txtnarmario.setText(
						String.valueOf(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 0).toString()));
				combotipo.setSelectedItem(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 2).toString());
				combochavereserva
						.setSelectedItem(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 3).toString());
				combostatus
						.setSelectedItem(tabarmario.getModel().getValueAt(tabarmario.getSelectedRow(), 4).toString());
				
				
			}
		});
		tabarmario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabarmario);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblStatus.setBounds(183, 484, 77, 35);
		frmcadarmario.add(lblStatus);

		JButton btinserir = new JButton("");
		btinserir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btinserir.setRolloverIcon(
						new ImageIcon(CadArmario.class.getResource("/imagens/botaoinserirgrande.png")));
			}
		});
		btinserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				btcadastrar.setEnabled(true);
				btexcluir.setEnabled(false);
				btalterar.setEnabled(false);
				bteditar.setEnabled(false);
				txtnarmario.setEditable(true);
				txtnarmario.grabFocus();
				txtdata.setEditable(true);
				combotipo.setEnabled(true);
				combochavereserva.setEnabled(true);
				combostatus.setEnabled(true);
				combositiacao.setEnabled(true);
				
				
			
			}
		});
		btinserir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btinserir.setIcon(new ImageIcon(CadArmario.class.getResource("/imagens/botaoinserir.png")));
		btinserir.setFocusable(false);
		btinserir.setContentAreaFilled(false);
		btinserir.setBorderPainted(false);
		btinserir.setBounds(12, 531, 141, 59);
		frmcadarmario.add(btinserir);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadArmario.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 859, 646);
		frmcadarmario.add(label);
	}
}
