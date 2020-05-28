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
import br.com.olgber.bean.CadVeiculoBean;
import br.com.olgber.bean.MovVeiculoBean;
import br.com.olgber.bean.VisitasCadastradasBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ConsMovVeiculo extends JFrame {

	private JPanel contentPane;
	private JTable tabmovimentacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsMovVeiculo frame = new ConsMovVeiculo();
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
	public ConsMovVeiculo() throws ParseException {
		setTitle("Consulta de Movimenta\u00E7\u00E3o dos Ve\u00EDculos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		Limpar limpar = new Limpar();

		JLabel lblVeculo = new JLabel("Ve\u00EDculo:");
		lblVeculo.setForeground(Color.WHITE);
		lblVeculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblVeculo.setBounds(51, 33, 88, 35);
		contentPane.add(lblVeculo);

		JComboBox comboveiculo = new JComboBox();
		comboveiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboveiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String veiculo;
				veiculo = comboveiculo.getSelectedItem().toString();

				tabmovimentacao.setModel(modelo);

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

				tabmovimentacao.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabmovimentacao.getColumnModel().getColumn(2).setPreferredWidth(70);
				tabmovimentacao.getColumnModel().getColumn(3).setPreferredWidth(30);
				tabmovimentacao.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(6).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(8).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(9).setPreferredWidth(5);

				tabmovimentacao.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovimentacao
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);

				try {
					for (MovVeiculoBean list : sql.BuscaMovVeiculo(veiculo)) {
						modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(), list.getDestino(),
								list.getHorasaida(), list.getHoraretorno(), list.getDatasaida(), list.getDataretorno(),
								list.getKminicio(), list.getKmfinal(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		comboveiculo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		comboveiculo.setBounds(43, 81, 185, 35);
		contentPane.add(comboveiculo);

		JLabel label_1 = new JLabel("Busca Pela Data:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(676, 27, 185, 35);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("De:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(561, 75, 45, 35);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("At\u00E9:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(752, 75, 45, 35);
		contentPane.add(label_3);

		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setBounds(605, 78, 128, 35);
		contentPane.add(txtdatainicio);

		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setBounds(804, 78, 128, 35);
		contentPane.add(txtdatafinal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 212, 970, 296);
		contentPane.add(scrollPane);

		tabmovimentacao = new JTable();
		tabmovimentacao.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabmovimentacao);

		JButton btbuscar = new JButton("");
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicio.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tabmovimentacao.setModel(modelo);

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

				tabmovimentacao.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabmovimentacao.getColumnModel().getColumn(2).setPreferredWidth(70);
				tabmovimentacao.getColumnModel().getColumn(3).setPreferredWidth(30);
				tabmovimentacao.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(6).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(8).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(9).setPreferredWidth(5);

				tabmovimentacao.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovimentacao
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);

				try {
					for (MovVeiculoBean list : sql.BuscaDataMov(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getVeiculo(), list.getFuncionario(), list.getDestino(),
								list.getHorasaida(), list.getHoraretorno(), list.getDatasaida(), list.getDataretorno(),
								list.getKminicio(), list.getKmfinal(), list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btbuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btbuscar.setRolloverIcon(
						new ImageIcon(ConsVisitantes.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscar.setIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaobuscar.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.setBounds(702, 137, 138, 51);
		contentPane.add(btbuscar);

		JButton btbuscaveiculo = new JButton("");
		btbuscaveiculo.setIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/lupa.png")));
		btbuscaveiculo.setFocusable(false);
		btbuscaveiculo.setContentAreaFilled(false);
		btbuscaveiculo.setBorderPainted(false);
		btbuscaveiculo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btbuscaveiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CRUD sql = new CRUD();

				try {
					for (CadVeiculoBean list : sql.BuscaVeiculo()) {
						comboveiculo.addItem(list.getModelo());
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btbuscaveiculo.setBounds(135, 23, 68, 57);
		contentPane.add(btbuscaveiculo);

		JButton btsair = new JButton("");
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(702, 535, 149, 63);
		contentPane.add(btsair);

		JButton btpesquisar = new JButton("");
		btpesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btpesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				tabmovimentacao.setModel(modelo);

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

				tabmovimentacao.getColumnModel().getColumn(0).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabmovimentacao.getColumnModel().getColumn(2).setPreferredWidth(70);
				tabmovimentacao.getColumnModel().getColumn(3).setPreferredWidth(30);
				tabmovimentacao.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(5).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(6).setPreferredWidth(60);
				tabmovimentacao.getColumnModel().getColumn(7).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(8).setPreferredWidth(40);
				tabmovimentacao.getColumnModel().getColumn(9).setPreferredWidth(5);

				tabmovimentacao.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabmovimentacao
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
		btpesquisar.setIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaopesquisar.png")));
		btpesquisar.setFocusable(false);
		btpesquisar.setContentAreaFilled(false);
		btpesquisar.setBorderPainted(false);
		btpesquisar.setBounds(79, 131, 149, 57);
		contentPane.add(btpesquisar);
		
		JButton btexcluir = new JButton("");
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num = 0;

			num = Integer.parseInt(String
					.valueOf(tabmovimentacao.getModel().getValueAt(tabmovimentacao.getSelectedRow(), 9).toString()));

			CRUD sql = new CRUD();

			try {
				int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
						"Atenção", JOptionPane.YES_OPTION);
				if (confirmar == JOptionPane.YES_OPTION) {
					sql.ExcluirMovVeiculo(num);
					JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso");
					((DefaultTableModel) tabmovimentacao.getModel()).removeRow(tabmovimentacao.getSelectedRow());  
	                tabmovimentacao.repaint();  
	                tabmovimentacao.revalidate();
				}
			} catch (Exception e1) {
				System.out.println(e1);
			}
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsMovVeiculo.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(79, 535, 149, 63);
		contentPane.add(btexcluir);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(
						ConsMovVeiculo.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
				label.setBounds(-12, -14, 1006, 654);
				contentPane.add(label);
	}
}
