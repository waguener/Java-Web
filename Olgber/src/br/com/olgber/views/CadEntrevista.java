package br.com.olgber.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import br.com.controle.util.Mascara;
import br.com.olgber.CRUD.CRUD;
import br.com.olgber.bean.ChavesBean;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.JTable;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class CadEntrevista extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTable tabEntrevista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadEntrevista frame = new CadEntrevista();
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
	public CadEntrevista() {
		setResizable(false);
		setTitle("Cadastro de Entrevistas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 974, 658);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		
		CRUD sql = new CRUD();
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(164, 43, 71, 35);
		contentPane.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtNome.setColumns(10);
		txtNome.setBounds(270, 46, 472, 35);
		contentPane.add(txtNome);
		
		JLabel label_2 = new JLabel("RG:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(164, 102, 50, 35);
		contentPane.add(label_2);
		
		JFormattedTextField txtRg = new JFormattedTextField();
		txtRg.setText("");
		txtRg.setHorizontalAlignment(SwingConstants.LEFT);
		txtRg.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtRg.setBounds(270, 102, 246, 35);
		contentPane.add(txtRg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 231, 944, 291);
		contentPane.add(scrollPane);
		Integer teste = 0;
		tabEntrevista = new JTable();
		
		
		tabEntrevista.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabEntrevista);
		String hoje;
		hoje = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
		DefaultTableModel modelo = new DefaultTableModel();

		tabEntrevista.setModel(modelo);

		modelo.addColumn("Nome");
		modelo.addColumn("RG");
		modelo.addColumn("Data");
		modelo.addColumn("Hora Entrada");
		modelo.addColumn("Hora Saída");
		modelo.addColumn("Id");

		tabEntrevista.getColumnModel().getColumn(0).setPreferredWidth(280);
		tabEntrevista.getColumnModel().getColumn(1).setPreferredWidth(60);
		tabEntrevista.getColumnModel().getColumn(2).setPreferredWidth(30);
		tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(10);
		tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(10);
		tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
		

		tabEntrevista.setRowHeight(30);

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
				.getDefaultRenderer(JLabel.class);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			for (br.com.olgber.bean.CadEntrevista list : sql.BuscaDataEntrevista(hoje)) {
				modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
						list.getEntrada(), list.getSaida(), list.getId()});
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		
		JButton btCadEntrada = new JButton("");
		btCadEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if (txtNome.getText().equals("") || txtRg.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					String data,entrada,saida;
					data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
					entrada = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
					saida = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));
					
					br.com.olgber.bean.CadEntrevista bean = new br.com.olgber.bean.CadEntrevista();
					
					bean.setNome(txtNome.getText());
					bean.setRg(txtRg.getText());
					bean.setData(data);
					bean.setEntrada(entrada);
					bean.setSaida(null);
					
					try {
						sql.SalvaEntrevista(bean);
						JOptionPane.showMessageDialog(null, "Entrevistado Cadastrado com Sucesso");
						txtNome.setText("");
						txtRg.setText("");
						txtNome.grabFocus();
						
						DefaultTableModel modelo = new DefaultTableModel();
						
						tabEntrevista.setModel(modelo);

						modelo.addColumn("Nome");
						modelo.addColumn("RG");
						modelo.addColumn("Data");
						modelo.addColumn("Hora Entrada");
						modelo.addColumn("Hora Saída");
						modelo.addColumn("Id");

						tabEntrevista.getColumnModel().getColumn(0).setPreferredWidth(280);
						tabEntrevista.getColumnModel().getColumn(1).setPreferredWidth(60);
						tabEntrevista.getColumnModel().getColumn(2).setPreferredWidth(30);
						tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(10);
						tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(10);
						tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
						

						tabEntrevista.setRowHeight(30);

						DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
								.getDefaultRenderer(JLabel.class);
						renderer.setHorizontalAlignment(SwingConstants.CENTER);
						try {
							for (br.com.olgber.bean.CadEntrevista list : sql.BuscaDataEntrevista(hoje)) {
								modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
										list.getEntrada(), list.getSaida(), list.getId()});
							}
						} catch (Exception e1) {
							System.out.println(e1);
						}
						
					} catch (ClassNotFoundException | SQLException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		});
		btCadEntrada.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btCadEntrada.setRolloverIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/btcadentradabig.png")));
			}
		});
		btCadEntrada.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btCadEntrada.setIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/btcadentrada.png")));
		btCadEntrada.setFocusable(false);
		btCadEntrada.setContentAreaFilled(false);
		btCadEntrada.setBorderPainted(false);
		btCadEntrada.setBounds(329, 153, 148, 65);
		contentPane.add(btCadEntrada);
		
		
		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer num = 0;
				num = Integer.parseInt(String
						.valueOf(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 5).toString()));

				CRUD sql = new CRUD();

				try {
					int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta Entrevista?",
							"Atenção", JOptionPane.YES_OPTION);
					if (confirmar == JOptionPane.YES_OPTION) {
						sql.ExcluirEntrevista(num);
						JOptionPane.showMessageDialog(null,"Entrevista Excluída com Sucesso");
						((DefaultTableModel) tabEntrevista.getModel()).removeRow(tabEntrevista.getSelectedRow());  
		                tabEntrevista.repaint();  
		                tabEntrevista.revalidate();
					
				} 
				}catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btExcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btExcluir.setRolloverIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btExcluir.setIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/botaoexcluir.png")));
		btExcluir.setFocusable(false);
		btExcluir.setContentAreaFilled(false);
		btExcluir.setBorderPainted(false);
		btExcluir.setBounds(414, 565, 148, 45);
		contentPane.add(btExcluir);
		
		JButton btSair = new JButton("");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btSair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btSair.setRolloverIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSair.setIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/botaosairmenor.png")));
		btSair.setFocusable(false);
		btSair.setContentAreaFilled(false);
		btSair.setBorderPainted(false);
		btSair.setBounds(645, 565, 154, 45);
		contentPane.add(btSair);
		
		JButton btCadSaida = new JButton("");
		btCadSaida.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btCadSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				br.com.olgber.bean.CadEntrevista bean = new br.com.olgber.bean.CadEntrevista();
				String saida = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));	
							
				bean.setNome(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 0).toString());
				bean.setRg(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 1).toString());
				bean.setData(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 2).toString());
				bean.setEntrada(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 3).toString());
				bean.setSaida(saida);
				bean.setId(Integer.parseInt(String.valueOf(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 5).toString())));
				
				try {
					sql.AtualizaEntrevista(bean);
					JOptionPane.showMessageDialog(null,"Saída Cadastrada com Sucesso");
					
					DefaultTableModel modelo = new DefaultTableModel();
					
					tabEntrevista.setModel(modelo);

					modelo.addColumn("Nome");
					modelo.addColumn("RG");
					modelo.addColumn("Data");
					modelo.addColumn("Hora Entrada");
					modelo.addColumn("Hora Saída");
					modelo.addColumn("Id");

					tabEntrevista.getColumnModel().getColumn(0).setPreferredWidth(280);
					tabEntrevista.getColumnModel().getColumn(1).setPreferredWidth(60);
					tabEntrevista.getColumnModel().getColumn(2).setPreferredWidth(30);
					tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(10);
					tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(10);
					tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
					

					tabEntrevista.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (br.com.olgber.bean.CadEntrevista list : sql.BuscaDataEntrevista(hoje)) {
							modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
									list.getEntrada(), list.getSaida(), list.getId()});
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			
		});
		btCadSaida.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btCadSaida.setRolloverIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/btcadsaidabig.png")));
			}
		});
		btCadSaida.setIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/btcadsaida.png")));
		btCadSaida.setFocusable(false);
		btCadSaida.setContentAreaFilled(false);
		btCadSaida.setBorderPainted(false);
		btCadSaida.setBounds(164, 565, 148, 45);
		contentPane.add(btCadSaida);
		
		JLabel label = new JLabel("5");
		label.setIcon(new ImageIcon(CadEntrevista.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(-23, 0, 991, 623);
		contentPane.add(label);
	}
}
