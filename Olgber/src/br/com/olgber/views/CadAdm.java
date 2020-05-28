package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.AdmBean;
import br.com.olgber.bean.CadAdmBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadAdm extends JFrame {

	private JPanel frmCadAdm;
	private JTextField txtnome;
	private JTextField txtcargo;
	private JTable tabAdm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadAdm frame = new CadAdm();
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
	public CadAdm() {
		setTitle("Cadastro de Funcion\u00E1rios Administrativos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 833, 658);
		frmCadAdm = new JPanel();
		frmCadAdm.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmCadAdm);
		frmCadAdm.setLayout(null);

		JLabel lblNome = new JLabel(" Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNome.setBounds(52, 68, 78, 35);
		frmCadAdm.add(lblNome);

		CRUD sql = new CRUD();
	
		
		txtnome = new JTextField();
		txtnome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtnome.setColumns(10);
		txtnome.setBounds(128, 68, 587, 35);
		frmCadAdm.add(txtnome);

		txtcargo = new JTextField();
		txtcargo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtcargo.setColumns(10);
		txtcargo.setBounds(128, 137, 587, 35);
		frmCadAdm.add(txtcargo);

	

		JLabel lblCargo = new JLabel("Fun\u00E7\u00E3o:");
		lblCargo.setForeground(Color.WHITE);
		lblCargo.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblCargo.setBounds(39, 137, 91, 35);
		frmCadAdm.add(lblCargo);

		JButton btcadastrar = new JButton("");
		btcadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CadAdmBean bean = new CadAdmBean();

				bean.setNome(txtnome.getText());
				bean.setCargo(txtcargo.getText());
				try {
					sql.SalvarFuncAdm(bean);
					JOptionPane.showMessageDialog(null, "Cadastro Efetuado com Sucesso");
					txtnome.setText("");
					txtcargo.setText("");
					txtnome.grabFocus();

					DefaultTableModel modelo = new DefaultTableModel();

					tabAdm.setModel(modelo);

					modelo.addColumn("ID");
					modelo.addColumn("Nome");
					modelo.addColumn("Cargo");

					tabAdm.getColumnModel().getColumn(0).setPreferredWidth(10);
					tabAdm.getColumnModel().getColumn(1).setPreferredWidth(100);
					tabAdm.getColumnModel().getColumn(2).setPreferredWidth(120);

					tabAdm.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabAdm
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (CadAdmBean list : sql.BuscaFuncAdm()) {
							modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getCargo() });
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e2);
				}
			}
		});
		btcadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btcadastrar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btcadastrar.setRolloverIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaocadgrande.png")));
			}
		});
		btcadastrar.setIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaocadastrar.png")));
		btcadastrar.setFocusable(false);
		btcadastrar.setContentAreaFilled(false);
		btcadastrar.setBorderPainted(false);
		btcadastrar.setBounds(321, 185, 155, 52);
		frmCadAdm.add(btcadastrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 251, 771, 194);
		frmCadAdm.add(scrollPane);

		tabAdm = new JTable();
		tabAdm.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabAdm);
		
		DefaultTableModel modelo = new DefaultTableModel();

		tabAdm.setModel(modelo);

		modelo.addColumn("ID");
		modelo.addColumn("Nome");
		modelo.addColumn("Cargo");

		tabAdm.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabAdm.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabAdm.getColumnModel().getColumn(2).setPreferredWidth(120);

		tabAdm.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabAdm.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (CadAdmBean list : sql.BuscaFuncAdm()) {
				modelo.addRow(new Object[] { list.getId(), list.getNome(), list.getCargo() });
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}

		JButton btexcluir = new JButton("");
		btexcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				id = Integer.parseInt(tabAdm.getModel().getValueAt(tabAdm.getSelectedRow(), 0).toString());

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Cadastro?",
							"Atenção", JOptionPane.YES_OPTION);
					
					if (confirmar == JOptionPane.YES_OPTION) {
						
						sql.ExcluirFunAdm(id);												
						
						JOptionPane.showMessageDialog(null, "Cadastro Excluido com Sucesso");
						((DefaultTableModel) tabAdm.getModel()).removeRow(tabAdm.getSelectedRow());  
		                tabAdm.repaint();  
		                tabAdm.revalidate(); 
						
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btexcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btexcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btexcluir.setRolloverIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btexcluir.setIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaoexcluir.png")));
		btexcluir.setFocusable(false);
		btexcluir.setContentAreaFilled(false);
		btexcluir.setBorderPainted(false);
		btexcluir.setBounds(88, 476, 155, 52);
		frmCadAdm.add(btexcluir);

		JButton btsair = new JButton("");
		btsair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btsair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btsair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btsair.setRolloverIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btsair.setIcon(new ImageIcon(CadAdm.class.getResource("/imagens/botaosairmenor.png")));
		btsair.setFocusable(false);
		btsair.setContentAreaFilled(false);
		btsair.setBorderPainted(false);
		btsair.setBounds(609, 476, 155, 52);
		frmCadAdm.add(btsair);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				CadAdm.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, 0, 840, 640);
		frmCadAdm.add(label);
		setLocationRelativeTo(null);
	}
}
