package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.CadArmarioFuncBean;
import br.com.olgber.bean.CadChaveBean;
import br.com.olgber.bean.ChavesBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsChaves extends JFrame {

	private JPanel frmConsChaves;
	private JTextField txtnome;
	private JTable tabchaves;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsChaves frame = new ConsChaves();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsChaves() {
		setTitle("Consulta de Chaves ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		frmConsChaves = new JPanel();
		frmConsChaves.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmConsChaves);
		frmConsChaves.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 143, 818, 376);
		frmConsChaves.add(scrollPane);
		
		tabchaves = new JTable();
		tabchaves.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		scrollPane.setViewportView(tabchaves);
		
		JComboBox combotipo = new JComboBox();
		combotipo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		combotipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = combotipo.getSelectedItem().toString();

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
				tabchaves.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabchaves.getColumnModel().getColumn(6).setPreferredWidth(10);

				tabchaves.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabchaves
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (ChavesBean list : sql.BuscaTipochave(nome)) {
						modelo.addRow(new Object[] { list.getFuncionario(), list.getData(), list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		combotipo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		combotipo.setBounds(96, 70, 347, 35);
		frmConsChaves.add(combotipo);
		
		CRUD sql = new CRUD();

		try {
			for (CadChaveBean list : sql.BuscaChave()) {
				combotipo.addItem(list.getNome());
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		
		JLabel label_1 = new JLabel("Tipo:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(40, 70, 61, 35);
		frmConsChaves.add(label_1);
		
		JLabel label_2 = new JLabel("Funcion\u00E1rio:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(40, 13, 135, 35);
		frmConsChaves.add(label_2);
		
		txtnome = new JTextField();
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();

				tabchaves.setModel(modelo);

				modelo.addColumn("Funcionário");
				modelo.addColumn("Data");
				modelo.addColumn("Hora Retirada");
				modelo.addColumn("Hora Entrega");
				modelo.addColumn("Tipo");
				modelo.addColumn("Observação");
				modelo.addColumn("ID");
				

				tabchaves.getColumnModel().getColumn(0).setPreferredWidth(50);
				tabchaves.getColumnModel().getColumn(1).setPreferredWidth(40);
				tabchaves.getColumnModel().getColumn(2).setPreferredWidth(40);
				tabchaves.getColumnModel().getColumn(3).setPreferredWidth(40);
				tabchaves.getColumnModel().getColumn(4).setPreferredWidth(80);
				tabchaves.getColumnModel().getColumn(5).setPreferredWidth(80);
				tabchaves.getColumnModel().getColumn(6).setPreferredWidth(10);

				tabchaves.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabchaves
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (ChavesBean list : sql.BuscaNomeChave(nome)) {
						modelo.addRow(new Object[] { list.getFuncionario(), list.getData(), list.getRetirada(), list.getEntrega(), list.getTipo(), list.getObservacao(),list.getId() });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setColumns(10);
		txtnome.setBounds(170, 13, 186, 35);
		frmConsChaves.add(txtnome);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		comboBox_1.setBounds(96, 70, 347, 35);
		frmConsChaves.add(comboBox_1);
		
		JLabel label_3 = new JLabel("Tipo:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(40, 70, 61, 35);
		frmConsChaves.add(label_3);
		
		JLabel label_4 = new JLabel("Funcion\u00E1rio:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(40, 13, 135, 35);
		frmConsChaves.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		textField_1.setColumns(10);
		textField_1.setBounds(170, 13, 186, 35);
		frmConsChaves.add(textField_1);
		
		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btsair.setRolloverIcon(new ImageIcon(ConsChaves.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(ConsChaves.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(644, 532, 149, 63);
		frmConsChaves.add(btsair);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabchaves.getModel().getValueAt(tabchaves.getSelectedRow(), 6).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirChaves(num);
						JOptionPane.showMessageDialog(null, "Usuário Excluido com Sucesso");
						((DefaultTableModel) tabchaves.getModel()).removeRow(tabchaves.getSelectedRow());  
		                tabchaves.repaint();  
		                tabchaves.revalidate();
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsChaves.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsChaves.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(168, 532, 149, 63);
		frmConsChaves.add(btexcluir);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConsChaves.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 853, 640);
		frmConsChaves.add(label);
		setLocationRelativeTo(null);
	}
}
