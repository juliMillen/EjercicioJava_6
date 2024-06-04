package Formularios;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Clases.Cliente;
import Clases.Proveedor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JButton btnProveedores;
	protected JButton btnSalir;
	protected JButton btnClientes;
	protected JScrollPane scrollPane;
	protected JTable table;
	protected static Set<Cliente> setClientes= new HashSet<Cliente>();
	protected static Set<Proveedor> setProveedores= new HashSet<Proveedor>();
	protected static Map<Integer,Cliente>mapClientes= new HashMap<Integer, Cliente>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FMenu frame = new FMenu();
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
	public FMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 311);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.btnProveedores = new JButton("Proveedores");
		this.btnProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Formulario2 frame = new Formulario2();
					frame.setVisible(true);
				} catch (Exception exc) {
					exc.printStackTrace();
				}
				cargarTabla2(setProveedores);
			}
		});
		this.btnProveedores.setBounds(138, 10, 120, 21);
		this.contentPane.add(this.btnProveedores);
		
		this.btnSalir = new JButton("Salir");
		this.btnSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		this.btnSalir.setBounds(268, 10, 85, 21);
		this.contentPane.add(this.btnSalir);
		
		this.btnClientes = new JButton("Clientes");
		this.btnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Formulario1 frame = new Formulario1();
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
				cargarTabla(setClientes);
			}
		});
		this.btnClientes.setBounds(23, 10, 105, 21);
		this.contentPane.add(this.btnClientes);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(23, 94, 377, 159);
		this.contentPane.add(this.scrollPane);
		
		this.table = new JTable();
		this.table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Nombre", "Numero", "DNI", "Email", "Celular"
			}
		));
		this.scrollPane.setViewportView(this.table);
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
		table.setModel(new DefaultTableModel(
				aux,
				new String[] {
					"nombre","numero","email","celular"
				}));
		
  }
  }
