package com.hotelAlura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hotelAlura.controller.HuespedesController;
import com.hotelAlura.controller.ReservasController;
import com.hotelAlura.modelo.Huespedes;
import com.hotelAlura.modelo.Reservas;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/com/hotelAlura/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		
		tbReservas = new JTable(new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0 && column != 5;
		    }
		});
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("ID Huesped");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/com/hotelAlura/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		cargarTablaReservasCompleta(modelo);
		
		tbHuespedes = new JTable(new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column != 0;
		    }
		});
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/com/hotelAlura/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		cargarTablaHuespedesCompleta(modeloHuesped);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/com/hotelAlura/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcion = JOptionPane.showConfirmDialog(
			            null,
			            "¿Desea cerrar el programa?",
			            "Confirmar Cierre",
			            JOptionPane.YES_NO_OPTION
			        );

			    if (opcion == JOptionPane.YES_OPTION) {System.exit(0);}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				HuespedesController huespedesController = new HuespedesController();
				List<Huespedes> listaHuespedes = huespedesController.busqueda(txtBuscar.getText());
				
				if(!listaHuespedes.isEmpty()) {
					
					modelo.setRowCount(0);
					modeloHuesped.setRowCount(0);
					
					for (int i = 0; i < listaHuespedes.size(); i++) {
						Object[] nuevaFila = {
								listaHuespedes.get(i).getId(),
								listaHuespedes.get(i).getNombre(),
								listaHuespedes.get(i).getApellido(),
								listaHuespedes.get(i).getFecha_de_nacimientoSQL(),
								listaHuespedes.get(i).getNacionalidad(),
								listaHuespedes.get(i).getTelefono(),
								listaHuespedes.get(i).getReservacion_actual()};
						
						modeloHuesped.addRow(nuevaFila);
					}
					
					ReservasController reservasController = new ReservasController();
					List<Reservas> allReservas = reservasController.busquedaPorIdHuesped(listaHuespedes);
					
					for (int i = 0; i < allReservas.size(); i++) {
						Object[] nuevaFila = {
								allReservas.get(i).getId(),
								allReservas.get(i).getFecha_entradaSQL(),
								allReservas.get(i).getFecha_salidaSQL(),
								("$ " + new DecimalFormat("#,###.##").format(allReservas.get(i).getValor())),
								allReservas.get(i).getFormato_de_pago(),
								allReservas.get(i).getId_huesped()
						};
						
						modelo.addRow(nuevaFila);
					}
				}else {
					JOptionPane.showMessageDialog(
		            		null,
		            		"Ninguna persona con ese nombre o apellido se ha registrado",
		            		"Advertencia",
		            		JOptionPane.WARNING_MESSAGE);	
				}
				
				
				

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
				int selectedRow;
				int selectedIndex = panel.getSelectedIndex();

				switch (selectedIndex) {
				
					case 0:
												
						selectedRow = tbReservas.getSelectedRow();
												
						if (selectedRow != -1) {
							
							NumberFormat formatoDinero = NumberFormat.getCurrencyInstance();

					        String numeroFormateado = new String ((String)tbReservas.
					        		getValueAt(selectedRow,3)).replaceAll("\\s", "");
					        
					        Number numeroDesformateado = 0;
   
							try {
								numeroDesformateado = formatoDinero.parse(numeroFormateado);
							} catch (ParseException e1) {}
														
							String obtenerFecha = "" + tbReservas.getValueAt(selectedRow, 1);
							java.sql.Date fechaIngreso = java.sql.Date.valueOf(obtenerFecha);
					        
							obtenerFecha = "" + tbReservas.getValueAt(selectedRow, 2);
							java.sql.Date fechaSalida = java.sql.Date.valueOf(obtenerFecha);
					        
					        Integer idUsuario = Integer.parseInt("" + tbReservas.getValueAt(selectedRow,5));
					        
							Reservas reserva = new Reservas(
								(Integer)tbReservas.getValueAt(selectedRow,0),
								fechaIngreso,
								fechaSalida,
								numeroDesformateado.doubleValue(),
								(String)tbReservas.getValueAt(selectedRow,4),
								idUsuario
									);
							
							ReservasController reservasController = new ReservasController();
							
							reservasController.editar(reserva);
							
							cargarTablaReservasCompleta(modelo);
							
				            JOptionPane.showMessageDialog(null,"Reservacion editada con exito");

							
						} else {
							JOptionPane.showMessageDialog(
				            		null,
				            		"Seleccione una casilla para editar",
				            		"Advertencia",
				            		JOptionPane.WARNING_MESSAGE);	
						}
						
						break;
	
					case 1:
						
						selectedRow = tbHuespedes.getSelectedRow();
						
						if (selectedRow != -1) {
							
							String obtenerFecha = "" + tbHuespedes.getValueAt(selectedRow, 3);
							java.sql.Date fechaNacimiento = java.sql.Date.valueOf(obtenerFecha);
							
							Integer idReserva = Integer.parseInt("" + tbHuespedes.getValueAt(selectedRow,6));
							
							Huespedes huesped = new Huespedes(
								(int)tbHuespedes.getValueAt(selectedRow,0),
								(String) tbHuespedes.getValueAt(selectedRow,1),
								(String) tbHuespedes.getValueAt(selectedRow,2),
								fechaNacimiento,
								(String)tbHuespedes.getValueAt(selectedRow,4),
								(String)tbHuespedes.getValueAt(selectedRow,5),
								idReserva
									);
							
							HuespedesController huespedesController = new HuespedesController();
							
							huespedesController.editar(huesped);
							
							cargarTablaReservasCompleta(modelo);	
							
				            JOptionPane.showMessageDialog(null,"Informacion del huesped editada con exito");

						}
						
						break;	
					default:
						JOptionPane.showMessageDialog(
			            		null,
			            		"Seleccione una casilla para editar",
			            		"Advertencia",
			            		JOptionPane.WARNING_MESSAGE);	
						break;
				}

			}
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
				int selectedRow;
				int selectedIndex = panel.getSelectedIndex();
				List<Integer> idReservas = new ArrayList<>();

				switch (selectedIndex) {
				
					case 0:
												
						selectedRow = tbReservas.getSelectedRow();
												
						if (selectedRow != -1) {
							
							idReservas.add(Integer.parseInt("" + tbReservas.getValueAt(selectedRow,0)));
							
							ReservasController reservasController = new ReservasController();
							
							reservasController.eliminar(idReservas);
							
							cargarTablaReservasCompleta(modelo);
							
				            JOptionPane.showMessageDialog(null,"Reservacion eliminada con exito");

							
						} else {
							JOptionPane.showMessageDialog(
				            		null,
				            		"Seleccione una casilla para eliminar",
				            		"Advertencia",
				            		JOptionPane.WARNING_MESSAGE);	
						}
						
						break;
	
					case 1:
						
						selectedRow = tbHuespedes.getSelectedRow();
						
						if (selectedRow != -1) {
							
							Integer idHuesped = Integer.parseInt("" + tbHuespedes.getValueAt(selectedRow,0));
							
							for (int fila = 0; fila < modelo.getRowCount(); fila++) {
								
								Integer currentHuespedId = Integer.parseInt("" + 
										modelo.getValueAt(fila,5));
								
								if (currentHuespedId == idHuesped) {
									Integer id = Integer.parseInt("" + modelo.getValueAt(
							    		fila, 0));
									idReservas.add(id);
								}						
								
							}
							
							if (!idReservas.isEmpty()) {
								ReservasController reservasController = new ReservasController();
								
								reservasController.eliminar(idReservas);
							}
														
							HuespedesController huespedesController = new HuespedesController();
							
							huespedesController.eliminar(idHuesped);
							
				            JOptionPane.showMessageDialog(null,"Huesped y reservaciones eliminadas con exito");
														
						} else {
							JOptionPane.showMessageDialog(
				            		null,
				            		"Seleccione una casilla para eliminar",
				            		"Advertencia",
				            		JOptionPane.WARNING_MESSAGE);	
						}
						
						break;	
					default:
						JOptionPane.showMessageDialog(
			            		null,
			            		"Seleccione una ventana y un dato para editar",
			            		"Advertencia",
			            		JOptionPane.WARNING_MESSAGE);	
						break;
				}
				
				cargarTablaHuespedesCompleta(modeloHuesped);
				cargarTablaReservasCompleta(modelo);

			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		JPanel btnRecargar = new JPanel();
		btnRecargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cargarTablaReservasCompleta(modelo);
				cargarTablaHuespedesCompleta(modeloHuesped);
			}
		});
		btnRecargar.setLayout(null);
		btnRecargar.setBackground(new Color(12, 138, 199));
		btnRecargar.setBounds(100, 508, 122, 35);
		btnRecargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnRecargar);
		
		JLabel lblRecargar = new JLabel("RECARGAR");
		lblRecargar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecargar.setForeground(Color.WHITE);
		lblRecargar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblRecargar.setBounds(0, 0, 122, 35);
		btnRecargar.add(lblRecargar);
		setResizable(false);
	}

	private void cargarTablaHuespedesCompleta(DefaultTableModel modelo) {
		modeloHuesped.setRowCount(0);
		
		HuespedesController huespedesController = new HuespedesController();
		List<Huespedes> allHuespedes = huespedesController.listarTodo();
		
		for (int i = 0; i < allHuespedes.size(); i++) {
			Object[] nuevaFila = {
					allHuespedes.get(i).getId(),
					allHuespedes.get(i).getNombre(),
					allHuespedes.get(i).getApellido(),
					allHuespedes.get(i).getFecha_de_nacimientoSQL(),
					allHuespedes.get(i).getNacionalidad(),
					allHuespedes.get(i).getTelefono(),
					allHuespedes.get(i).getReservacion_actual()};
			
			modeloHuesped.addRow(nuevaFila);
		}
	}

	private void cargarTablaReservasCompleta(DefaultTableModel modelo) {
		modelo.setRowCount(0);
		
		ReservasController reservasController = new ReservasController();
		List<Reservas> allReservas = reservasController.listarTodo();
		
		for (int i = 0; i < allReservas.size(); i++) {
			Object[] nuevaFila = {
					allReservas.get(i).getId(),
					allReservas.get(i).getFecha_entradaSQL(),
					allReservas.get(i).getFecha_salidaSQL(),
					("$ " + new DecimalFormat("#,###.##").format(allReservas.get(i).getValor())),
					allReservas.get(i).getFormato_de_pago(),
					allReservas.get(i).getId_huesped()
			};
			
			modelo.addRow(nuevaFila);
		}
	}

	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	 }

	 private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	 }
}
