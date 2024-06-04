package Formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Clases.Cliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formulario1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JLabel lblNewLabel;
	protected JTextField txtNombre;
	protected JLabel lblNewLabel_1;
	protected JTextField txtNumero;
	protected JButton btnAgregar;
	protected JButton btnBorrar;
	protected JLabel lblNewLabel_2;
	protected JTextField txtDni;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected static ArrayList<Cliente> listaClientes= new ArrayList<Cliente>();
	protected JButton btnModificar;
	protected JButton btnListar;
	protected JButton btnSalir;
	protected JButton btnGuardar;
	protected JButton btnLeer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formulario1 frame = new Formulario1();
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
	public Formulario1() {
		setTitle("Prueba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 389);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.lblNewLabel = new JLabel("Nombre:");
		this.lblNewLabel.setBounds(20, 33, 69, 13);
		this.contentPane.add(this.lblNewLabel);
		
		this.txtNombre = new JTextField();
		this.txtNombre.setBounds(114, 30, 96, 19);
		this.contentPane.add(this.txtNombre);
		this.txtNombre.setColumns(10);
		
		this.lblNewLabel_1 = new JLabel("Numero:");
		this.lblNewLabel_1.setBounds(20, 76, 69, 13);
		this.contentPane.add(this.lblNewLabel_1);
		
		this.txtNumero = new JTextField();
		this.txtNumero.setBounds(114, 73, 96, 19);
		this.contentPane.add(this.txtNumero);
		this.txtNumero.setColumns(10);
		
		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String nombre= txtNombre.getText();
					long dni= Long.parseLong(txtDni.getText());
					int numero= Integer.parseInt(txtNumero.getText());
					Cliente nuevoCliente= new Cliente(numero, nombre, dni);
				    listaClientes.add(nuevoCliente);
					FMenu.setClientes.add(nuevoCliente);
					FMenu.mapClientes.put(nuevoCliente.getNro(), nuevoCliente);
					//cargarTabla(listaClientes);
					//cargarTabla(FMenu.setClientes);
					cargarTabla(FMenu.mapClientes);
				}
				catch(NumberFormatException ee) {
					JOptionPane.showMessageDialog(rootPane, "Error en el numero ingresado");
				}
				
				txtNombre.setText("");
				txtDni.setText("");
				txtNumero.setText("");
			}
		});
		this.btnAgregar.setBounds(298, 25, 85, 21);
		this.contentPane.add(this.btnAgregar);
		
		this.btnBorrar = new JButton("Borrar");
		this.btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila= table.getSelectedRow();
				if(fila != -1) {
					String numeroFila= table.getValueAt(fila, 1).toString();
					try {
						int numero= Integer.parseInt(numeroFila);
						for(Iterator iterator= FMenu.setClientes.iterator();iterator.hasNext();) {
							Cliente aEliminar= (Cliente)iterator.next();
							if(aEliminar.getNro()==numero) {
								iterator.remove();
								JOptionPane.showMessageDialog(rootPane, "Eliminado correctamente");
								cargarTabla(FMenu.setClientes);
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
				//cargarTabla(listaClientes);
				
			}
		});
		this.btnBorrar.setBounds(298, 50, 85, 21);
		this.contentPane.add(this.btnBorrar);
		
		this.lblNewLabel_2 = new JLabel("DNI:");
		this.lblNewLabel_2.setBounds(44, 112, 45, 13);
		this.contentPane.add(this.lblNewLabel_2);
		
		this.txtDni = new JTextField();
		this.txtDni.setBounds(114, 109, 96, 19);
		this.contentPane.add(this.txtDni);
		this.txtDni.setColumns(10);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 248, 416, 94);
		this.contentPane.add(this.scrollPane);
		
		this.table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila= table.getSelectedRow();
				if(fila != -1) {
					txtNombre.setText(table.getValueAt(fila, 0).toString());
					txtNumero.setText(table.getValueAt(fila, 1).toString());
					txtDni.setText(table.getValueAt(fila, 2).toString());
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ninguna fila");
				}
			}
		});
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Nombre", "Numero", "DNI"
			}
		));
		this.scrollPane.setViewportView(this.table);
		
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int numFila= table.getSelectedRow();
				if(numFila != -1) {
					try {
						String nombre= txtNombre.getText();
						long dni= Long.parseLong(txtDni.getText());
						
						String nFila= table.getValueAt(numFila, 1).toString();
						int numero= Integer.parseInt(nFila);
						for(Iterator iterator=FMenu.setClientes.iterator();iterator.hasNext();) {
							Cliente aModificar= (Cliente)iterator.next();
							if(aModificar.getNro()==numero) {
								aModificar.setNombre(nombre);
								aModificar.setDni(dni);
							}
						}
						cargarTabla(FMenu.setClientes);
					}catch(NumberFormatException ee) {
						JOptionPane.showMessageDialog(null, "Numero mal escrito");
					}
					catch(Exception ex) {
						JOptionPane.showInternalMessageDialog(null, "Error desconocido");
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				}
			}
		});
		this.btnModificar.setBounds(298, 76, 85, 21);
		this.contentPane.add(this.btnModificar);
		
		this.btnListar = new JButton("Listar");
		this.btnListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//cargarTabla(listaClientes);
				cargarTabla(FMenu.setClientes);
			}
		});
		this.btnListar.setBounds(298, 104, 85, 21);
		this.contentPane.add(this.btnListar);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		this.btnSalir.setBounds(298, 217, 85, 21);
		this.contentPane.add(this.btnSalir);
		
		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FileOutputStream archivo= new FileOutputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\kakaroto.txt");
					ObjectOutputStream archEscribir= new ObjectOutputStream(archivo);
					for(Iterator it=listaClientes.iterator();it.hasNext();) {
						Cliente cliente= (Cliente)it.next();
						archEscribir.writeObject(cliente);
					}
					archEscribir.close();
					archivo.close();
				}
				catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Error al intentar escribir el archivo");
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(null, "Error desconocido");
				}
			}
		});
		this.btnGuardar.setBounds(298, 135, 85, 21);
		this.contentPane.add(this.btnGuardar);
		
		this.btnLeer = new JButton("Leer");
		this.btnLeer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FileInputStream archivoLeer= new FileInputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\kakaroto.txt");
					ObjectInputStream leerArchivo= new ObjectInputStream(archivoLeer);
					Cliente c= (Cliente)leerArchivo.readObject();
					listaClientes.add(c);
					
					leerArchivo.close();
					archivoLeer.close();

				}
				catch(IOException excepA) {
					JOptionPane.showMessageDialog(null, "Error al intentar abrir el archivo");
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(rootPane, "Error desconocido");
				}
			}
		});
		this.btnLeer.setBounds(298, 171, 85, 21);
		this.contentPane.add(this.btnLeer);
	}
	private void cargarTabla(Set<Cliente> setClientes) {
		Object[][] aux= (Object[][]) new Object[setClientes.size()][3];
		int renglon=0;
		for(Iterator<Cliente> it= setClientes.iterator();it.hasNext();) {
			Cliente miCliente= it.next();
			aux[renglon][0]= miCliente.getNombre();
			aux[renglon][1]= miCliente.getNro();
			aux[renglon][2]= miCliente.getDni();
			renglon++;
			}
		table.setModel(new DefaultTableModel(
				aux,
				new String[] {
					"nombre","numero","dni"
				}));
		
  }
	
	private void cargarTabla(ArrayList<Cliente> listaClientes) {
		Object[][] aux= (Object[][]) new Object[listaClientes.size()][3];
		int renglon=0;
		for(Iterator<Cliente> it= listaClientes.iterator();it.hasNext();) {
			Cliente miCliente= it.next();
			aux[renglon][0]= miCliente.getNombre();
			aux[renglon][1]= miCliente.getNro();
			aux[renglon][2]= miCliente.getDni();
			renglon++;
			}
		table.setModel(new DefaultTableModel(
				aux,
				new String[] {
					"nombre","numero","dni"
				}));
		
  }
	
	private void cargarTabla(Map<Integer, Cliente>mapClientes) {
		Object[][] aux= (Object[][])new Object[mapClientes.size()][3];
		int renglon=0;
		for(Iterator it=mapClientes.entrySet().iterator();it.hasNext();) {
			Cliente otroCliente= (Cliente)((Entry)it.next()).getValue();
			Iterator<Entry<Integer, Cliente>>iterator= mapClientes.entrySet().iterator();
			while(iterator.hasNext()) {
				Entry<Integer, Cliente> nuevoCliente= iterator.next();
				aux[renglon][0]=otroCliente.getNombre();
				aux[renglon][1]=otroCliente.getNro();
				aux[renglon][2]=otroCliente.getDni();
				renglon++;
			}
			table.setModel(new DefaultTableModel(aux,
					new String[] {
				"nombre","numero","dni"
			}));
			
		}
	}
	
	private static void graboConArray(ArrayList<Cliente>listaClientes) {
		try {
			FileOutputStream fos= new FileOutputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo2.txt");
			ObjectOutputStream oss1= new ObjectOutputStream(fos);
			
			//cerramos en orden invertido
			oss1.close();
			fos.close();
			JOptionPane.showMessageDialog(null, "Grabando con array");
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al intentar grabar");
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(null, exc.getMessage());
		}
	}
	
	private static void graboConSet(Set<Cliente>setClientes) {
		try {
			FileOutputStream fos2= new FileOutputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo3.txt");
			ObjectOutputStream oos2= new ObjectOutputStream(fos2);
			
			//cerramos en orden contrario
			oos2.close();
			fos2.close();
			
			JOptionPane.showMessageDialog(null, "Grabando con set");
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al intentar grabar");
		}
		catch(Exception ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage());
		}
	}
	
	private static void graboConMap(Map<Integer, Cliente>mapCliente) {
		try {
			FileOutputStream fos3= new FileOutputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo4.txt");
			ObjectOutputStream oos3= new ObjectOutputStream(fos3);
			
			//cerramos en orden inverso
			
			oos3.close();
			fos3.close();
			
			JOptionPane.showMessageDialog(null, "Grabando con map");
		}catch(IOException ee) {
			JOptionPane.showMessageDialog(null, "Error al intentar grabar");
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(null, exc.getMessage());
		}
	}
	
	private static void leoConArray(ArrayList<Cliente>listaClientes) {
		boolean salgo=false;
		try {
			FileInputStream fis= new FileInputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo.txt");
			ObjectInputStream ois= new ObjectInputStream(fis);
			listaClientes.clear();
			try {
				var arr= (Object[])ois.readObject();
				for(Object obj:arr) {
					Cliente nuevoC= (Cliente)obj;
					listaClientes.add(nuevoC);
				}
			}catch(Exception ee) {
				JOptionPane.showMessageDialog(null, ee.getMessage());
			}
			ois.close();
			fis.close();
		}
		catch(IOException ee) {
			JOptionPane.showMessageDialog(null, "Error en el archivo");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			salgo=true;
		}
	}
	
	private static void leoConSet(Set<Cliente>setClientes) {
		boolean salgo=false;
		try {
			FileInputStream fis2= new FileInputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo2.txt");
			ObjectInputStream ois2= new ObjectInputStream(fis2);
			setClientes.clear();
			try {
				var arr2= (Object[])ois2.readObject();
				for(Object obj2:arr2) {
					Cliente otroCliente= (Cliente)obj2;
					setClientes.add(otroCliente);
				}
			}
			catch(Exception ee) {
				
			}
			ois2.close();
			fis2.close();
		}
		catch(IOException exc) {
			JOptionPane.showMessageDialog(null, "Error en el archivo");
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			salgo=true;
		}
	}
	
	private static void leoConMap(Map<Integer, Cliente>mapClientes) {
		boolean salgo= false;
		try {
			FileInputStream fis3= new FileInputStream("C:\\Users\\julim\\eclipse-workspace\\Jtable_lab3\\archivo3.txt");
			ObjectInputStream ois3= new ObjectInputStream(fis3);
			mapClientes.clear();
			try {
				var arr3= (Object[])ois3.readObject();
				for(Object obj3:arr3) {
					Cliente otroCliente= (Cliente)obj3;
					mapClientes.put(otroCliente.getNro(), otroCliente);
				}
			}catch(Exception ee) {
				
			}
			ois3.close();
			fis3.close();
		}
		catch(IOException ex) {
			JOptionPane.showMessageDialog(null, "Error en el archivo");
		}
		catch(Exception exc) {
			JOptionPane.showMessageDialog(null, exc.getMessage());
			salgo=true;
		}
	}
	
}
