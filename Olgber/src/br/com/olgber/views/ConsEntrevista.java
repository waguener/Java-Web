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
import br.com.olgber.bean.CadEntrevista;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class ConsEntrevista extends JFrame {

	private JPanel frmConsEntrevista;
	private JTextField txtNome;
	private JTable tabEntrevista;
	private JTextField txtNomeEdit;
	private JTextField txtRgEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsEntrevista frame = new ConsEntrevista();
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
	public ConsEntrevista() throws ParseException {
		setTitle("Consulta Entrevista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 673);
		frmConsEntrevista = new JPanel();
		frmConsEntrevista.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frmConsEntrevista);
		frmConsEntrevista.setLayout(null);
		setLocationRelativeTo(null);
		Mascara masc = new Mascara();
		CRUD sql = new CRUD();
		Limpar limpar = new Limpar();
		
		JLabel lblNomeEntrevistado = new JLabel("Nome Entrevistado:");
		lblNomeEntrevistado.setForeground(Color.WHITE);
		lblNomeEntrevistado.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNomeEntrevistado.setBounds(36, 2, 230, 35);
		frmConsEntrevista.add(lblNomeEntrevistado);
		
		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				
				
				DefaultTableModel modelo = new DefaultTableModel();
				String nome = txtNome.getText();
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
				tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(20);
				tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(20);
				tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
				

				tabEntrevista.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadEntrevista list : sql.BuscaEntrevistaNome(nome)) {
						modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
								list.getEntrada(), list.getSaida(), list.getId()});
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		txtNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtNome.setColumns(10);
		txtNome.setBounds(36, 33, 315, 35);
		frmConsEntrevista.add(txtNome);
		
		JLabel label_2 = new JLabel("Busca Pela Data:");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_2.setBounds(538, 2, 185, 35);
		frmConsEntrevista.add(label_2);
		
		JLabel label_3 = new JLabel("De:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_3.setBounds(432, 33, 45, 35);
		frmConsEntrevista.add(label_3);
		
		JFormattedTextField txtDataInicial = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataInicial.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataInicial.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtDataInicial.setBounds(477, 33, 134, 35);
		frmConsEntrevista.add(txtDataInicial);
		
		JLabel label_4 = new JLabel("At\u00E9:");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_4.setBounds(623, 33, 45, 35);
		frmConsEntrevista.add(label_4);
		
		JFormattedTextField txtDataFinal = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataFinal.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtDataFinal.setBounds(676, 33, 134, 35);
		frmConsEntrevista.add(txtDataFinal);
		
		JButton btBuscarData = new JButton("");
		btBuscarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String datainicio,datafinal;
				
				DefaultTableModel modelo = new DefaultTableModel();
				datainicio = txtDataInicial.getText();
				datafinal = txtDataFinal.getText();
				
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
				tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(20);
				tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(20);
				tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
				

				tabEntrevista.setRowHeight(30);

				DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
						.getDefaultRenderer(JLabel.class);
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				try {
					for (CadEntrevista list : sql.BuscaDataEntrevista2(datainicio, datafinal)) {
						modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
								list.getEntrada(), list.getSaida(), list.getId()});
						
					}
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btBuscarData.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btBuscarData.setIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/lupa.png")));
		btBuscarData.setFocusable(false);
		btBuscarData.setContentAreaFilled(false);
		btBuscarData.setBorderPainted(false);
		btBuscarData.setBounds(813, 25, 68, 54);
		frmConsEntrevista.add(btBuscarData);
		
		JButton btSair = new JButton("");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btSair.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btSair.setRolloverIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaosairbig.png")));
			}
		});
		btSair.setIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaosair.png")));
		btSair.setFocusable(false);
		btSair.setContentAreaFilled(false);
		btSair.setBorderPainted(false);
		btSair.setBounds(654, 539, 149, 54);
		frmConsEntrevista.add(btSair);
		
		JFormattedTextField txtEntradaEdit = new JFormattedTextField(new MaskFormatter("##:##"));
		txtEntradaEdit.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntradaEdit.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtEntradaEdit.setBounds(432, 471, 141, 35);
		frmConsEntrevista.add(txtEntradaEdit);
		
		JFormattedTextField txtSaidaEdit = new JFormattedTextField(new MaskFormatter("##:##"));
		txtSaidaEdit.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaidaEdit.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtSaidaEdit.setBounds(642, 471, 134, 35);
		frmConsEntrevista.add(txtSaidaEdit);
		
		JFormattedTextField txtDataEdit = new JFormattedTextField(new MaskFormatter("##/##/####"));
		txtDataEdit.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataEdit.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtDataEdit.setBounds(654, 392, 134, 35);
		frmConsEntrevista.add(txtDataEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 92, 958, 258);
		frmConsEntrevista.add(scrollPane);
		
		tabEntrevista = new JTable();
		tabEntrevista.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String nome,rg,data,entrada,saida;
				int id;
				nome = (tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 0).toString());
				rg = (tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 1).toString());
				data = (tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 2).toString());
				entrada = (tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 3).toString());
				saida = (tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 4).toString());
				id = (Integer.parseInt(String.valueOf(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 5).toString())));
				
				txtNomeEdit.setText(nome);
				txtRgEdit.setText(rg);
				txtDataEdit.setText(data);
				txtEntradaEdit.setText(entrada);
				txtSaidaEdit.setText(saida);
				
				
			}
		});
		tabEntrevista.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		scrollPane.setViewportView(tabEntrevista);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblNome.setBounds(147, 363, 93, 35);
		frmConsEntrevista.add(lblNome);
		
		txtNomeEdit = new JTextField();
		txtNomeEdit.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtNomeEdit.setColumns(10);
		txtNomeEdit.setBounds(147, 392, 447, 35);
		frmConsEntrevista.add(txtNomeEdit);
		
		JLabel lblRg = new JLabel("RG:");
		lblRg.setForeground(Color.WHITE);
		lblRg.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblRg.setBounds(207, 440, 54, 35);
		frmConsEntrevista.add(lblRg);
		
	
		
		txtRgEdit = new JTextField();
		txtRgEdit.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		txtRgEdit.setColumns(10);
		txtRgEdit.setBounds(207, 471, 168, 35);
		frmConsEntrevista.add(txtRgEdit);
		
		JButton btAlterar = new JButton("");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CadEntrevista bean = new CadEntrevista();
				
				String data,entrada,saida;
				data = masc.convertDate(txtDataEdit.getText());
				entrada = (txtEntradaEdit.getText());
				saida = (txtSaidaEdit.getText());
				System.out.println("teste "+entrada);
				
				bean.setNome(txtNomeEdit.getText());
				bean.setRg(txtRgEdit.getText());
				bean.setData (data);
				bean.setEntrada (entrada);
				bean.setSaida(saida);
				bean.setId((Integer.parseInt(String.valueOf(tabEntrevista.getModel().getValueAt(tabEntrevista.getSelectedRow(), 5).toString()))));
				
				try {
					sql.AtualizaEntrevista(bean);
					JOptionPane.showMessageDialog(null,"Entrevista Editado com Sucesso");
					String nome = txtNomeEdit.getText();
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
					tabEntrevista.getColumnModel().getColumn(3).setPreferredWidth(20);
					tabEntrevista.getColumnModel().getColumn(4).setPreferredWidth(20);
					tabEntrevista.getColumnModel().getColumn(5).setPreferredWidth(5);
					

					tabEntrevista.setRowHeight(30);

					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tabEntrevista
							.getDefaultRenderer(JLabel.class);
					renderer.setHorizontalAlignment(SwingConstants.CENTER);
					try {
						for (br.com.olgber.bean.CadEntrevista list : sql.BuscaEntrevistaNome(nome)) {
							modelo.addRow(new Object[] { list.getNome(),list.getRg(), list.getData(),
									list.getEntrada(), list.getSaida(), list.getId()});
						}
					} catch (Exception e1) {
						System.out.println(e1);
					}
				} catch (ClassNotFoundException | SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
				}
				}
			
			
		});
		btAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btAlterar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btAlterar.setRolloverIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaoaltgrande.png")));
			}
		});
		btAlterar.setIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaoalterar.png")));
		btAlterar.setFocusable(false);
		btAlterar.setContentAreaFilled(false);
		btAlterar.setBorderPainted(false);
		btAlterar.setBounds(194, 536, 141, 57);
		frmConsEntrevista.add(btAlterar);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setForeground(Color.WHITE);
		lblData.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblData.setBounds(654, 361, 75, 35);
		frmConsEntrevista.add(lblData);
		
		
		
		JLabel lblHoraEntrada = new JLabel("Hora Entrada:");
		lblHoraEntrada.setForeground(Color.WHITE);
		lblHoraEntrada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraEntrada.setBounds(432, 440, 149, 35);
		frmConsEntrevista.add(lblHoraEntrada);
		
		JLabel lblHoraSada = new JLabel("Hora Sa\u00EDda:");
		lblHoraSada.setForeground(Color.WHITE);
		lblHoraSada.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		lblHoraSada.setBounds(642, 440, 123, 35);
		frmConsEntrevista.add(lblHoraSada);
		
		JButton btExcluir = new JButton("");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		                limpar.Limpar(frmConsEntrevista);
				} 
				}catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btExcluir.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btExcluir.setRolloverIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaoexcluirgrande.png")));
			}
		});
		btExcluir.setIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/botaoexcluir.png")));
		btExcluir.setFocusable(false);
		btExcluir.setContentAreaFilled(false);
		btExcluir.setBorderPainted(false);
		btExcluir.setBounds(425, 539, 148, 54);
		frmConsEntrevista.add(btExcluir);
		
			
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ConsEntrevista.class.getResource("/imagens/119685_Papel-de-Parede-Apple-Azul-Claro_1920x1200.jpg")));
		label.setBounds(0, -14, 996, 642);
		frmConsEntrevista.add(label);
		}
		

	
}
