package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Cliente;
import Clases.Proveedor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Set;

public class Formulario2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel lblNewLabel;
	protected JLabel lblNewLabel_1;
	protected JLabel lblNewLabel_2;
	protected JLabel lblNewLabel_3;
	protected JTextField txtNombreP;
	protected JTextField txtNumeroP;
	protected JTextField txtEmail;
	protected JTextField txtCelular;
	protected JButton btnAgregar;
	protected JButton btnBorrar;
	protected JButton btnModificar;
	protected JButton btnSalir;
	protected JButton btnListar;
	protected JScrollPane scrollPane;
	protected JTable tableProveedores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario2 frame = new Formulario2();
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
	public Formulario2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.lblNewLabel = new JLabel("Nombre:");
		this.lblNewLabel.setBounds(10, 21, 45, 13);
		this.contentPane.add(this.lblNewLabel);
		
		this.lblNewLabel_1 = new JLabel("Numero:");
		this.lblNewLabel_1.setBounds(10, 58, 45, 13);
		this.contentPane.add(this.lblNewLabel_1);
		
		this.lblNewLabel_2 = new JLabel("Email:");
		this.lblNewLabel_2.setBounds(10, 97, 45, 13);
		this.contentPane.add(this.lblNewLabel_2);
		
		this.lblNewLabel_3 = new JLabel("Celular:");
		this.lblNewLabel_3.setBounds(10, 141, 45, 13);
		this.contentPane.add(this.lblNewLabel_3);
		
		this.txtNombreP = new JTextField();
		this.txtNombreP.setBounds(79, 18, 96, 19);
		this.contentPane.add(this.txtNombreP);
		this.txtNombreP.setColumns(10);
		
		this.txtNumeroP = new JTextField();
		this.txtNumeroP.setBounds(79, 55, 96, 19);
		this.contentPane.add(this.txtNumeroP);
		this.txtNumeroP.setColumns(10);
		
		this.txtEmail = new JTextField();
		this.txtEmail.setBounds(79, 94, 96, 19);
		this.contentPane.add(this.txtEmail);
		this.txtEmail.setColumns(10);
		
		this.txtCelular = new JTextField();
		this.txtCelular.setBounds(79, 138, 96, 19);
		this.contentPane.add(this.txtCelular);
		this.txtCelular.setColumns(10);
		
		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String nombreP= txtNombreP.getText();
					int numero= Integer.parseInt(txtNumeroP.getText());
					String email= txtEmail.getText();
					long celular= Long.parseLong(txtCelular.getText());
					if(!nombreP.isEmpty() && !email.isEmpty() && numero>0 && celular>0) {
					
						Proveedor nuevoProveedor= new Proveedor(nombreP, numero, email, celular);
						FMenu.setProveedores.add(nuevoProveedor);
						cargarTabla2(FMenu.setProveedores);
					}else {
						JOptionPane.showMessageDialog(rootPane, "No se ha podido agregar el proveedor");
					}
				}
				catch(NumberFormatException ee) {
					JOptionPane.showMessageDialog(rootPane, "Error en el numero ingresado");
				}
				catch(Exception eee) {
					JOptionPane.showMessageDialog(rootPane, "Error desconocido");
				}
				
				txtNombreP.setText("");
				txtNumeroP.setText("");
				txtEmail.setText("");
				txtCelular.setText("");
			}
		});
		this.btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnAgregar.setBounds(0, 232, 85, 21);
		this.contentPane.add(this.btnAgregar);
		
		this.btnBorrar = new JButton("Borrar");
		this.btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila= tableProveedores.getSelectedRow();
				if(fila != -1) {
					String numeroFila= tableProveedores.getValueAt(fila, 1).toString();
					try {
						int numero= Integer.parseInt(numeroFila);
						for(Iterator iterator= FMenu.setProveedores.iterator();iterator.hasNext();) {
							Proveedor aEliminar= (Proveedor)iterator.next();
							if(aEliminar.getNro()==numero) {
								iterator.remove();
								JOptionPane.showMessageDialog(rootPane, "Eliminado correctamente");
							}
						}
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(rootPane, "Error desconocido");
					}
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ninguna fila");
				}
			}
		});
		this.btnBorrar.setBounds(79, 232, 85, 21);
		this.contentPane.add(this.btnBorrar);
		
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int numFila= tableProveedores.getSelectedRow();
				if(numFila != -1) {
					try {
						String nombre= txtNombreP.getText();
						long celular= Long.parseLong(txtCelular.getText());
						
						String nFila= tableProveedores.getValueAt(numFila, 1).toString();
						int numero= Integer.parseInt(nFila);
						for(Iterator iterator=FMenu.setProveedores.iterator();iterator.hasNext();) {
							Proveedor aModificar= (Proveedor)iterator.next();
							if(aModificar.getNro()==numero) {
								aModificar.setNombre(nombre);
								aModificar.setCelular(celular);
							}
						}
						cargarTabla2(FMenu.setProveedores);
					}catch(NumberFormatException ee) {
						JOptionPane.showMessageDialog(rootPane, "Numero mal escrito");
					}
					catch(Exception ex) {
						JOptionPane.showInternalMessageDialog(rootPane, "Error desconocido");
					}
				}else {
					JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ninguna fila");
				}
			}
		});
		this.btnModificar.setBounds(161, 232, 85, 21);
		this.contentPane.add(this.btnModificar);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		this.btnSalir.setBounds(341, 232, 85, 21);
		this.contentPane.add(this.btnSalir);
		
		this.btnListar = new JButton("Listar");
		this.btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarTabla2(FMenu.setProveedores);
			}
		});
		this.btnListar.setBounds(246, 232, 85, 21);
		this.contentPane.add(this.btnListar);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(192, 21, 254, 133);
		this.contentPane.add(this.scrollPane);
		
		this.tableProveedores = new JTable();
		this.tableProveedores.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Nombre", "Numero", "Email", "Celular"
			}
		));
		this.scrollPane.setViewportView(this.tableProveedores);
	}
private void cargarTabla2(Set<Proveedor> setProveedores) {
	Object[][] aux= (Object[][]) new Object[setProveedores.size()][4];
	int renglon=0;
	for(Iterator<Proveedor> it= setProveedores.iterator();it.hasNext();) {
		Proveedor miProveedor= it.next();
		aux[renglon][0]= miProveedor.getNombre();
		aux[renglon][1]= miProveedor.getNro();
		aux[renglon][2]= miProveedor.getEmail();
		aux[renglon][3]= miProveedor.getCelular();
		
		renglon++;
		}
	tableProveedores.setModel(new DefaultTableModel(
			aux,
			new String[] {
				"nombre","numero","email","celular"
			}));
	
}
}
