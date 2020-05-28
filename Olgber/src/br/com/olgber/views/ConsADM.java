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
import br.com.olgber.bean.AdmBean;
import br.com.olgber.bean.CadAtrasoSaidaBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ConsADM extends JFrame {

	private JPanel contentPane;
	private JTextField txtnome;
	private JTable tabAdm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsADM frame = new ConsADM();
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
	public ConsADM() throws ParseException {
		setResizable(false);
		setTitle("Consulta Administrativo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Mascara masc = new Mascara();
		
		JLabel label_1 = new JLabel("Busca Pelo Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(105, 13, 185, 35);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 167, 734, 363);
		contentPane.add(scrollPane);
		
		tabAdm = new JTable();
		tabAdm.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabAdm);
		
		txtnome = new JTextField();
		txtnome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();
				String nome;
				nome = txtnome.getText();
				
				tabAdm.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Data");
				modelo.addColumn("Entrada");
				modelo.addColumn("Saída");
				modelo.addColumn("Id");
				

				tabAdm.getColumnModel().getColumn(0).setPreferredWidth(120);
				tabAdm.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(2).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(3).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(4).setPreferredWidth(10);
				

				tabAdm.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabAdm
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (AdmBean list : sql.BuscaNomeAdm(nome)) {
						modelo.addRow(new Object[] { list.getNome(), list.getData(), list.getEntrada(), list.getSaida(),list.getId()
								 });
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setColumns(10);
		txtnome.setBounds(65, 61, 259, 35);
		contentPane.add(txtnome);
		
		JLabel label_2 = new JLabel("Busca Pela Data:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(524, 13, 185, 35);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("De:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(410, 61, 45, 35);
		contentPane.add(label_3);
		
		JFormattedTextField txtdatainicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatainicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatainicio.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatainicio.setBounds(455, 61, 134, 35);
		contentPane.add(txtdatainicio);
		
		JLabel label_4 = new JLabel("At\u00E9:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(601, 61, 45, 35);
		contentPane.add(label_4);
		
		JFormattedTextField txtdatafinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtdatafinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtdatafinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtdatafinal.setBounds(654, 61, 134, 35);
		contentPane.add(txtdatafinal);
		
		JButton btbuscar = new JButton("");
		btbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD sql = new CRUD();

				DefaultTableModel modelo = new DefaultTableModel();

				String datainicio, datafinal;

				datainicio = masc.convertDate(txtdatainicio.getText());
				datafinal = masc.convertDate(txtdatafinal.getText());

				tabAdm.setModel(modelo);

				modelo.addColumn("Nome");
				modelo.addColumn("Data");
				modelo.addColumn("Entrada");
				modelo.addColumn("Saída");
				modelo.addColumn("Id");
				

				tabAdm.getColumnModel().getColumn(0).setPreferredWidth(120);
				tabAdm.getColumnModel().getColumn(1).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(2).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(3).setPreferredWidth(50);
				tabAdm.getColumnModel().getColumn(4).setPreferredWidth(10);
				

				tabAdm.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabAdm
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (AdmBean list : sql.BuscaDataAdm(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getNome(), list.getData(), list.getEntrada(), list.getSaida(),list.getId()
								 });
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
				btbuscar.setRolloverIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaobuscarbig.png")));
			}
		});
		btbuscar.setIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaobuscar.png")));
		btbuscar.setFocusable(false);
		btbuscar.setContentAreaFilled(false);
		btbuscar.setBorderPainted(false);
		btbuscar.setBounds(530, 109, 164, 54);
		contentPane.add(btbuscar);
		
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
				btsair.setRolloverIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(551, 527, 149, 71);
		contentPane.add(btsair);
		
		JButton btexcluir = new JButton("");
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = 0;

				num = Integer.parseInt(String
						.valueOf(tabAdm.getModel().getValueAt(tabAdm.getSelectedRow(), 4).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir o Usuário deste Armário",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirAdm(num);
						JOptionPane.showMessageDialog(null,"Cadastro Excluído com Sucesso");
						DefaultTableModel modelo = new DefaultTableModel();

						((DefaultTableModel) tabAdm.getModel()).removeRow(tabAdm.getSelectedRow());  
		                tabAdm.repaint();  
		                tabAdm.revalidate();
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			}
		});
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(ConsADM.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(176, 527, 149, 71);
		contentPane.add(btexcluir);
		
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConsADM.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 866, 647);
		contentPane.add(label);
		setLocationRelativeTo(null);
	}
}
