package br.com.olgber.views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.VisitasCadastradasBean;

public class ConsVisitantes extends JFrame {

	private JPanel contentPane;
	private JTable tabvisitas;
	private JTextField txtnome;
	private JTextField txtempresa;
	private JButton btsair;
	private JTextField txttotalvisitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsVisitantes frame = new ConsVisitantes();
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
	public ConsVisitantes() throws ParseException {
		setResizable(false);
		setTitle("Visitantes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 974, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 188, 944, 347);
		contentPane.add(scrollPane);

		tabvisitas = new JTable();
		tabvisitas.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabvisitas);

		txttotalvisitas = new JTextField();
		txttotalvisitas.setHorizontalAlignment(SwingConstants.CENTER);
		txttotalvisitas.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txttotalvisitas.setColumns(10);
		txttotalvisitas.setBounds(508, 140, 104, 35);
		contentPane.add(txttotalvisitas);
		
		JLabel label_1 = new JLabel("Busca Pelo Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(97, 13, 185, 35);
		contentPane.add(label_1);

		txtnome = new JTextField();
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();
				
				
				
				tabvisitas.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Nome");
				modelo.addColumn("RG");
				modelo.addColumn("Empresa");
				modelo.addColumn("Data");
				modelo.addColumn("Data da Visita");
				modelo.addColumn("Hora da Entrada");
				modelo.addColumn("Hora da Saída");
				modelo.addColumn("Motivo");

				tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(8).setPreferredWidth(80);

				tabvisitas.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (VisitasCadastradasBean list : sql.BuscaNomeVisita(nome)) {
						int somaLinhas = tabvisitas.getModel().getRowCount()+1;   
						txttotalvisitas.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
								list.getDatavisita(), list.getHoraentrada(), list.getHorasaida(), list.getMotivo() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setColumns(10);
		txtnome.setBounds(59, 53, 259, 35);
		contentPane.add(txtnome);

		JLabel label_2 = new JLabel("De:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(525, 53, 45, 35);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("Busca Pela Data:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(635, 13, 185, 35);
		contentPane.add(label_3);

		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setBounds(570, 53, 134, 35);
		contentPane.add(txtdatainicio);

		JLabel label_4 = new JLabel("At\u00E9:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(716, 53, 45, 35);
		contentPane.add(label_4);

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(769, 53, 134, 35);
		contentPane.add(txtdatafinal);

		JButton btpesquisar = new JButton("");
		btpesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btpesquisar.setFocusable(false);
		btpesquisar.setContentAreaFilled(false);
		btpesquisar.setBorderPainted(false);
		btpesquisar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btpesquisar.setRolloverIcon(
						new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaopesquisarbig.png")));
			}
		});
		btpesquisar.setIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaopesquisar.png")));
		btpesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabvisitas.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Nome");
				modelo.addColumn("RG");
				modelo.addColumn("Empresa");
				modelo.addColumn("Data");
				modelo.addColumn("Data da Visita");
				modelo.addColumn("Hora da Entrada");
				modelo.addColumn("Hora da Saída");
				modelo.addColumn("Motivo");

				tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(8).setPreferredWidth(80);

				tabvisitas.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (VisitasCadastradasBean list : sql.BuscaVisitasTotal()) {
						int somaLinhas = tabvisitas.getModel().getRowCount()+1;   
						txttotalvisitas.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
								list.getDatavisita(), list.getHoraentrada(), list.getHorasaida(), list.getMotivo() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btpesquisar.setBounds(60, 538, 176, 72);
		contentPane.add(btpesquisar);

		btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setBounds(686, 548, 149, 51);
		contentPane.add(btsair);

		JButton btbuscar = new JButton("");
		btbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicio.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tabvisitas.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Nome");
				modelo.addColumn("RG");
				modelo.addColumn("Empresa");
				modelo.addColumn("Data");
				modelo.addColumn("Data da Visita");
				modelo.addColumn("Hora da Entrada");
				modelo.addColumn("Hora da Saída");
				modelo.addColumn("Motivo");

				tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(8).setPreferredWidth(80);

				tabvisitas.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (VisitasCadastradasBean list : sql.BuscaDataVisitas(datainicio, datafinal)) {
						int somaLinhas = tabvisitas.getModel().getRowCount()+1;   
						txttotalvisitas.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
								list.getDatavisita(), list.getHoraentrada(), list.getHorasaida(), list.getMotivo() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btbuscar.setRolloverIcon(
						new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscar.setIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaobuscar.png")));
		btbuscar.setBounds(668, 109, 155, 52);
		contentPane.add(btbuscar);

		JLabel lblPelaEmpresa = new JLabel("Pela Empresa:");
		lblPelaEmpresa.setForeground(Color.WHITE);
		lblPelaEmpresa.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblPelaEmpresa.setBounds(108, 101, 149, 35);
		contentPane.add(lblPelaEmpresa);

		txtempresa = new JTextField();
		txtempresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String empresa;
				empresa = txtempresa.getText();

				tabvisitas.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Nome");
				modelo.addColumn("RG");
				modelo.addColumn("Empresa");
				modelo.addColumn("Data");
				modelo.addColumn("Data da Visita");
				modelo.addColumn("Hora da Entrada");
				modelo.addColumn("Hora da Saída");
				modelo.addColumn("Motivo");

				tabvisitas.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvisitas.getColumnModel().getColumn(1).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(3).setPreferredWidth(80);
				tabvisitas.getColumnModel().getColumn(4).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabvisitas.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabvisitas.getColumnModel().getColumn(8).setPreferredWidth(80);

				tabvisitas.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvisitas
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (VisitasCadastradasBean list : sql.BuscaNomeEmpresa(empresa)) {
						int somaLinhas = tabvisitas.getModel().getRowCount()+1;   
						txttotalvisitas.setText(Integer.toString(somaLinhas));
						modelo.addRow(new Object[] { list.getId(),list.getNome(), list.getRg(), list.getEmpresa(), list.getData(),
								list.getDatavisita(), list.getHoraentrada(), list.getHorasaida(), list.getMotivo() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		txtempresa.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtempresa.setColumns(10);
		txtempresa.setBounds(59, 140, 259, 35);
		contentPane.add(txtempresa);

		JLabel lblTotalDeVisitas = new JLabel("Total de Visitas:");
		lblTotalDeVisitas.setForeground(Color.WHITE);
		lblTotalDeVisitas.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblTotalDeVisitas.setBounds(342, 140, 170, 35);
		contentPane.add(lblTotalDeVisitas);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabvisitas.getModel().getValueAt(tabvisitas.getSelectedRow(), 0).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Cadastro?",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirVisitas(num);
						JOptionPane.showMessageDialog(null,"Cadastro Excluído com Sucesso");
						((DefaultTableModel) tabvisitas.getModel()).removeRow(tabvisitas.getSelectedRow());  
		                tabvisitas.repaint();  
		                tabvisitas.revalidate();
					
				} 
				}catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(361, 538, 176, 72);
		contentPane.add(btexcluir);
		
				
		
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						ConsVisitantes.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(0, 0, 968, 641);
				contentPane.add(label);
	}
}
