package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadAtrasoSaidaBean;
import br.com.olgber.bean.CadVansBean;
import br.com.olgber.bean.NovaLinhaBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsVans extends JFrame {

	private JPanel ConsVans;
	private JTextField txtmotorista;
	private JTable tabvans;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsVans frame = new ConsVans();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public ConsVans() throws ParseException {
		setResizable(false);
		setTitle("Consultar Vans ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,1000, 658);
		ConsVans = new JPanel();
		ConsVans.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ConsVans);
		ConsVans.setLayout(null);
		Mascara masc = new Mascara();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 958, 275);
		ConsVans.add(scrollPane);
		
		tabvans = new JTable();
		tabvans.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabvans);
		JLabel lblLinha = new JLabel("Linha:");
		lblLinha.setForeground(Color.WHITE);
		lblLinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblLinha.setBounds(67, 73, 70, 35);
		ConsVans.add(lblLinha);
		
		JComboBox combolinha = new JComboBox();
		combolinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String linha;
				linha = combolinha.getSelectedItem().toString();
				
				tabvans.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Motorista");
				modelo.addColumn("Data");
				modelo.addColumn("Linha");
				modelo.addColumn("Tipo");
				modelo.addColumn("Hora");
				modelo.addColumn("Passageiros");
				modelo.addColumn("Responsável");

				tabvans.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvans.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabvans.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvans.getColumnModel().getColumn(3).setPreferredWidth(120);
				tabvans.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(5).setPreferredWidth(30);
				tabvans.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(7).setPreferredWidth(50);

				tabvans.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvans
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadVansBean list : sql.BuscaLinha(linha)) {
						modelo.addRow(new Object[] { list.getId(),list.getMotorista(), list.getData(), list.getLinha(), list.getTipo(),
								list.getHora(), list.getQtdpassageiros(),list.getResponsavel() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		combolinha.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combolinha.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combolinha.setBounds(133, 73, 229, 35);
		ConsVans.add(combolinha);
		
		CRUD sql = new CRUD();
		
		try {
			for (NovaLinhaBean list : sql.BuscaNovaLinha()) {
				combolinha.addItem(list.getLinha());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		JLabel label_1 = new JLabel("Busca Pela Data:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(681, 21, 185, 35);
		ConsVans.add(label_1);
		
		JLabel label_2 = new JLabel("Motorista:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(30, 25, 107, 35);
		ConsVans.add(label_2);
		
		txtmotorista = new JTextField();
		txtmotorista.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtmotorista.getText();
				
				tabvans.setModel(modelo);
				
				modelo.addColumn("ID");
				modelo.addColumn("Motorista");
				modelo.addColumn("Data");
				modelo.addColumn("Linha");
				modelo.addColumn("Tipo");
				modelo.addColumn("Hora");
				modelo.addColumn("Passageiros");
				modelo.addColumn("Responsável");

				tabvans.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvans.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabvans.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvans.getColumnModel().getColumn(3).setPreferredWidth(120);
				tabvans.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(5).setPreferredWidth(30);
				tabvans.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(7).setPreferredWidth(50);

				tabvans.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvans
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadVansBean list : sql.BuscaMotorista(nome)) {
						modelo.addRow(new Object[] { list.getId(),list.getMotorista(), list.getData(), list.getLinha(), list.getTipo(),
								list.getHora(), list.getQtdpassageiros(),list.getResponsavel() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}

			}
		});
		txtmotorista.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtmotorista.setColumns(10);
		txtmotorista.setBounds(133, 22, 315, 35);
		ConsVans.add(txtmotorista);
		
		JLabel label_3 = new JLabel("De:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(561, 69, 45, 35);
		ConsVans.add(label_3);
		
		JFormattedTextField txtdatainicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicial.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicial.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicial.setBounds(606, 69, 134, 35);
		ConsVans.add(txtdatainicial);
		
		JLabel label_4 = new JLabel("At\u00E9:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(752, 69, 45, 35);
		ConsVans.add(label_4);
		
		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(805, 69, 134, 35);
		ConsVans.add(txtdatafinal);
		
		JButton btbuscar = new JButton("");
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicial.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());
				
				tabvans.setModel(modelo);

				modelo.addColumn("ID");
				modelo.addColumn("Motorista");
				modelo.addColumn("Data");
				modelo.addColumn("Linha");
				modelo.addColumn("Tipo");
				modelo.addColumn("Hora");
				modelo.addColumn("Passageiros");
				modelo.addColumn("Responsável");

				tabvans.getColumnModel().getColumn(0).setPreferredWidth(10);
				tabvans.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabvans.getColumnModel().getColumn(2).setPreferredWidth(80);
				tabvans.getColumnModel().getColumn(3).setPreferredWidth(120);
				tabvans.getColumnModel().getColumn(4).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(5).setPreferredWidth(30);
				tabvans.getColumnModel().getColumn(6).setPreferredWidth(40);
				tabvans.getColumnModel().getColumn(7).setPreferredWidth(50);

				tabvans.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabvans
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadVansBean list : sql.BuscaDataVans(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getId(), list.getMotorista(), list.getData(), list.getLinha(), list.getTipo(),
								list.getHora(), list.getQtdpassageiros(),list.getResponsavel() });
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
				btbuscar.setRolloverIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscar.setIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaobuscar.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.setBounds(681, 117, 164, 54);
		ConsVans.add(btbuscar);
		
		
		
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
				btsair.setRolloverIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(726, 493, 149, 71);
		ConsVans.add(btsair);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabvans.getModel().getValueAt(tabvans.getSelectedRow(), 0).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirVan(num);
						JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso");
						((DefaultTableModel) tabvans.getModel()).removeRow(tabvans.getSelectedRow());  
		                tabvans.repaint();  
		                tabvans.revalidate();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsVans.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(191, 494, 141, 57);
		ConsVans.add(btexcluir);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConsVans.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 994, 623);
		ConsVans.add(label);
		setLocationRelativeTo(null);
	}
}
