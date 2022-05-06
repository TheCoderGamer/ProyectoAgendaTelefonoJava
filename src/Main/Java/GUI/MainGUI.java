package Main.Java.GUI;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Main.Java.BBDD.DataManager;
import javax.swing.JButton;


public class MainGUI extends JFrame{

	public static Listeners listener;

	static JFrame frame;
	static JTextField dt_nombre;
	static JTextField dt_apellidos;
	static JLabel etiqueta2;
	static JMenuItem mb2_aficiones;
	static JMenuItem mb2_correos;
	static JMenuItem mb1_bbdd;
	static DefaultListModel<String> mdl_contactos;
	static DefaultListModel<String> mdl_correos;
	static DefaultListModel<String> mdl_telefonos;
	static JList<String> listaContactos;
	static JList<String> listaCorreos;
	static JList<String> listaTelefonos;
	private static JScrollPane scrollContactos;
	private static JScrollPane scrollCorreos;
	private static JMenuBar menuBar;
	private static JMenu mb_archivo;
	private static JMenu mb_editar;
	private static JPanel zonaUtil;
	private static JPanel zonaContactos;
	private static JPanel zonaDetalles;
	private static JPanel detalles;
	private static JLabel dl_nombre;
	private static JLabel dl_apellidos;
	private static JLabel dl_direccion;
	private static JTextField dt_direccion;
	private static JLabel dl_fechaNac;
	private static JTextField dt_fechaNac;
	private static JLabel dl_genero;
	private static JTextField dt_genero;
	private static JLabel dt_correos;
	private static JLabel dt_Telefonos;
	private static JLabel dt_aficiones;
	private static JScrollPane scrollAficiones;
	private static JLabel dl_notas;
	private static JTextField dt_notas;
	private static JButton bt_anadir;

	private JScrollPane scrollTelefonos;

	private static DefaultListModel<String> mdl_aficiones;

	private static JList<String> listaAficiones;
	private JPanel p_correo;
	static JButton bt_edit_correos;
	private JPanel panel;
	private JPanel panel_1;
	static JButton bt_edit_telefonos;
	static JButton bt_edit_aficiones;

	static Integer IDcontacto;
	
	
	public MainGUI() {
		initialize();
	}
	
	// Inicializar la ventana
	private void initialize() {
		listener = new Listeners();
		// Ventana principal
		frame = new JFrame();
		frame.setBounds(100, 100, 909, 681);
		frame.setTitle("Agenda de Contactos");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(new ImageIcon(getClass().getResource("/Main/Resources/Images/app.png")).getImage());
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(listener);
		frame.setVisible(true);

		// Panel superior
		menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		// Menu Archivo
		mb_archivo = new JMenu("Archivo");
		menuBar.add(mb_archivo);
		// Menu Archivo -> BBDD
		mb1_bbdd = new JMenuItem("Configurar Base de Datos");
		mb1_bbdd.setIcon(new ImageIcon(MainGUI.class.getResource("/Main/Resources/Images/bbdd.png")));
		mb1_bbdd.addActionListener(listener);
		mb_archivo.add(mb1_bbdd);
		// Menu Editar
		mb_editar = new JMenu("Editar");
		menuBar.add(mb_editar);
		// Menu Editar -> Aficiones
		mb2_aficiones = new JMenuItem("Aficiones");
		mb2_aficiones.setIcon(new ImageIcon(MainGUI.class.getResource("/Main/Resources/Images/aficiones.png")));
		mb2_aficiones.addActionListener(listener);
		mb_editar.add(mb2_aficiones);	
		// Menu Editar -> Correos
		mb2_correos = new JMenuItem("Correos");
		mb2_correos.setIcon(new ImageIcon(MainGUI.class.getResource("/Main/Resources/Images/correos.png")));
		mb2_correos.addActionListener(listener);
		mb_editar.add(mb2_correos);

		// Zona util
		zonaUtil = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		zonaUtil.setLayout(gridBagLayout);
		frame.getContentPane().add(zonaUtil);
		
		// Panel lista contactos
		zonaContactos = new JPanel();
		zonaContactos.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_zonaContactos = new GridBagConstraints();
		gbc_zonaContactos.insets = new Insets(0, 0, 5, 5);
		gbc_zonaContactos.fill = GridBagConstraints.BOTH;
		gbc_zonaContactos.weightx = 1.0;
		gbc_zonaContactos.weighty = 1.0;
		gbc_zonaContactos.gridx = 0;
		gbc_zonaContactos.gridy = 0;
		zonaUtil.add(zonaContactos, gbc_zonaContactos);
		
		// Panel detalles contactos
		zonaDetalles = new JPanel();
		zonaDetalles.setBackground(Color.WHITE);
		GridBagConstraints gbc_zonaDetalles = new GridBagConstraints();
		gbc_zonaDetalles.insets = new Insets(0, 0, 5, 0);
		gbc_zonaDetalles.fill = GridBagConstraints.BOTH;
		gbc_zonaDetalles.weightx = 3.0;
		gbc_zonaDetalles.weighty = 1.0;
		gbc_zonaDetalles.gridx = 1;
		gbc_zonaDetalles.gridy = 0;
		zonaContactos.setLayout(new BorderLayout(0, 0));
		zonaUtil.add(zonaDetalles, gbc_zonaDetalles);
		zonaDetalles.setLayout(new BorderLayout(0, 0));
		
		// Etiqueta nombre contacto en Titulo
		etiqueta2 = new JLabel("Contacto1");
		etiqueta2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		etiqueta2.setIcon(new ImageIcon(MainGUI.class.getResource("/Main/Resources/Images/app.png")));
		zonaDetalles.add(etiqueta2, BorderLayout.NORTH);
		
		// Panel detalles
		detalles = new JPanel();
		detalles.setBackground(Color.WHITE);
		GridBagLayout gbl_detalles = new GridBagLayout();
		gbl_detalles.columnWidths = new int[]{0, 0, 0};
		gbl_detalles.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_detalles.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_detalles.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		detalles.setLayout(gbl_detalles);
		zonaDetalles.add(detalles, BorderLayout.CENTER);
		
		// Etiqueta nombre contacto
		dl_nombre = new JLabel("Nombre:");
		dl_nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_nombre = new GridBagConstraints();
		gbc_dl_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_dl_nombre.anchor = GridBagConstraints.EAST;
		gbc_dl_nombre.gridx = 0;
		gbc_dl_nombre.gridy = 0;
		detalles.add(dl_nombre, gbc_dl_nombre);
		
		// Texto nombre contacto
		dt_nombre = new JTextField();
		dt_nombre.setEditable(false);
		dt_nombre.setText("...");
		dt_nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dt_nombre = new GridBagConstraints();
		gbc_dt_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_dt_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_nombre.gridx = 1;
		gbc_dt_nombre.gridy = 0;
		detalles.add(dt_nombre, gbc_dt_nombre);
		dt_nombre.setColumns(10);
		
		// Etiqueta apellidos contacto
		dl_apellidos = new JLabel("Apellidos:");
		dl_apellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_apellidos = new GridBagConstraints();
		gbc_dl_apellidos.anchor = GridBagConstraints.EAST;
		gbc_dl_apellidos.insets = new Insets(0, 0, 5, 5);
		gbc_dl_apellidos.gridx = 0;
		gbc_dl_apellidos.gridy = 1;
		detalles.add(dl_apellidos, gbc_dl_apellidos);
		
		// Texto apellidos contacto
		dt_apellidos = new JTextField();
		dt_apellidos.setEditable(false);
		dt_apellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dt_apellidos.setText("...");
		GridBagConstraints gbc_dt_apellidos = new GridBagConstraints();
		gbc_dt_apellidos.insets = new Insets(0, 0, 5, 0);
		gbc_dt_apellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_apellidos.gridx = 1;
		gbc_dt_apellidos.gridy = 1;
		detalles.add(dt_apellidos, gbc_dt_apellidos);
		dt_apellidos.setColumns(10);
		
		dl_direccion = new JLabel("Direccion:");
		dl_direccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_direccion = new GridBagConstraints();
		gbc_dl_direccion.anchor = GridBagConstraints.EAST;
		gbc_dl_direccion.insets = new Insets(0, 0, 5, 5);
		gbc_dl_direccion.gridx = 0;
		gbc_dl_direccion.gridy = 2;
		detalles.add(dl_direccion, gbc_dl_direccion);
		
		dt_direccion = new JTextField();
		dt_direccion.setEditable(false);
		dt_direccion.setText("...");
		dt_direccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dt_direccion = new GridBagConstraints();
		gbc_dt_direccion.insets = new Insets(0, 0, 5, 0);
		gbc_dt_direccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_direccion.gridx = 1;
		gbc_dt_direccion.gridy = 2;
		detalles.add(dt_direccion, gbc_dt_direccion);
		dt_direccion.setColumns(10);
		
		dl_fechaNac = new JLabel("Fecha Nacimiento:");
		dl_fechaNac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_fechaNac = new GridBagConstraints();
		gbc_dl_fechaNac.anchor = GridBagConstraints.EAST;
		gbc_dl_fechaNac.insets = new Insets(0, 0, 5, 5);
		gbc_dl_fechaNac.gridx = 0;
		gbc_dl_fechaNac.gridy = 3;
		detalles.add(dl_fechaNac, gbc_dl_fechaNac);
		
		dt_fechaNac = new JTextField();
		dt_fechaNac.setEditable(false);
		dt_fechaNac.setText("...");
		dt_fechaNac.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dt_fechaNac = new GridBagConstraints();
		gbc_dt_fechaNac.insets = new Insets(0, 0, 5, 0);
		gbc_dt_fechaNac.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_fechaNac.gridx = 1;
		gbc_dt_fechaNac.gridy = 3;
		detalles.add(dt_fechaNac, gbc_dt_fechaNac);
		dt_fechaNac.setColumns(10);
		
		dl_genero = new JLabel("Genero:");
		dl_genero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_genero = new GridBagConstraints();
		gbc_dl_genero.anchor = GridBagConstraints.EAST;
		gbc_dl_genero.insets = new Insets(0, 0, 5, 5);
		gbc_dl_genero.gridx = 0;
		gbc_dl_genero.gridy = 4;
		detalles.add(dl_genero, gbc_dl_genero);
		
		dt_genero = new JTextField();
		dt_genero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dt_genero.setEditable(false);
		dt_genero.setText("...");
		GridBagConstraints gbc_dt_genero = new GridBagConstraints();
		gbc_dt_genero.insets = new Insets(0, 0, 5, 0);
		gbc_dt_genero.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_genero.gridx = 1;
		gbc_dt_genero.gridy = 4;
		detalles.add(dt_genero, gbc_dt_genero);
		dt_genero.setColumns(10);
		
		dl_notas = new JLabel("Notas:");
		dl_notas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dl_notas = new GridBagConstraints();
		gbc_dl_notas.anchor = GridBagConstraints.EAST;
		gbc_dl_notas.insets = new Insets(0, 0, 5, 5);
		gbc_dl_notas.gridx = 0;
		gbc_dl_notas.gridy = 5;
		detalles.add(dl_notas, gbc_dl_notas);
		
		dt_notas = new JTextField();
		dt_notas.setEditable(false);
		dt_notas.setText("...");
		dt_notas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_dt_notas = new GridBagConstraints();
		gbc_dt_notas.insets = new Insets(0, 0, 5, 0);
		gbc_dt_notas.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_notas.gridx = 1;
		gbc_dt_notas.gridy = 5;
		detalles.add(dt_notas, gbc_dt_notas);
		dt_notas.setColumns(10);
		
		
		mdl_correos = new DefaultListModel<String>();
		mdl_correos.addElement("                                                            ");
		mdl_correos.addElement("Test2");
		
		p_correo = new JPanel();
		GridBagConstraints gbc_p_correo = new GridBagConstraints();
		gbc_p_correo.anchor = GridBagConstraints.EAST;
		gbc_p_correo.gridwidth = 2;
		gbc_p_correo.insets = new Insets(0, 0, 5, 0);
		gbc_p_correo.fill = GridBagConstraints.BOTH;
		gbc_p_correo.gridx = 0;
		gbc_p_correo.gridy = 6;
		detalles.add(p_correo, gbc_p_correo);
		p_correo.setLayout(new BorderLayout(0, 0));
		
		dt_correos = new JLabel("Correos");
		dt_correos.setHorizontalAlignment(SwingConstants.CENTER);
		p_correo.add(dt_correos, BorderLayout.CENTER);
		dt_correos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bt_edit_correos = new JButton("Editar");
		bt_edit_correos.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_correos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_edit_correos.addActionListener(listener);
		p_correo.add(bt_edit_correos, BorderLayout.EAST);

		listaCorreos = new JList<String>(mdl_correos);
		listaCorreos.setModel(mdl_correos);
		listaCorreos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaCorreos.setSelectionForeground(new Color(0, 0, 0));
		listaCorreos.setSelectionBackground(new Color(255, 0, 255));
		listaCorreos.setBackground(new Color(240, 240, 240));
		scrollCorreos = new JScrollPane(listaCorreos);
		GridBagConstraints gbc_scrollCorreos = new GridBagConstraints();
		gbc_scrollCorreos.insets = new Insets(0, 0, 5, 0);
		gbc_scrollCorreos.gridwidth = 2;
		gbc_scrollCorreos.gridx = 0;
		gbc_scrollCorreos.gridy = 7;
		detalles.add(scrollCorreos, gbc_scrollCorreos);
		// listaCorreos.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listaCorreos.setVisibleRowCount(3);
		
		mdl_telefonos = new DefaultListModel<String>();
		mdl_telefonos.addElement("                                                            ");
		mdl_telefonos.addElement("Test2");
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		detalles.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));
		// Dimension d = listaCorreos.getPreferredSize();
		// d.width = 500;
		// scrollCorreos.setPreferredSize(d);

		// Telefonos
		dt_Telefonos = new JLabel("Telefonos");
		dt_Telefonos.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(dt_Telefonos);
		dt_Telefonos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bt_edit_telefonos = new JButton("Editar");
		bt_edit_telefonos.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_telefonos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bt_edit_telefonos.addActionListener(listener);
		panel.add(bt_edit_telefonos, BorderLayout.EAST);

		listaTelefonos = new JList<String>(mdl_telefonos);
		listaTelefonos.setModel(mdl_telefonos);
		listaTelefonos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaTelefonos.setSelectionForeground(new Color(0, 0, 0));
		listaTelefonos.setSelectionBackground(new Color(255, 0, 255));
		listaTelefonos.setBackground(new Color(240, 240, 240));
		scrollTelefonos = new JScrollPane(listaTelefonos);
		GridBagConstraints gbc_scrollTelefonos = new GridBagConstraints();
		gbc_scrollTelefonos.insets = new Insets(0, 0, 5, 0);
		gbc_scrollTelefonos.gridwidth = 2;
		gbc_scrollTelefonos.gridx = 0;
		gbc_scrollTelefonos.gridy = 9;
		detalles.add(scrollTelefonos, gbc_scrollTelefonos);
		listaTelefonos.setVisibleRowCount(3);
		
		mdl_aficiones = new DefaultListModel<String>();
		mdl_aficiones.addElement("                                                            ");
		mdl_aficiones.addElement("Test2");
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 10;
		detalles.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		

		dt_aficiones = new JLabel("Aficiones");
		dt_aficiones.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(dt_aficiones);
		dt_aficiones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bt_edit_aficiones = new JButton("Editar");
		bt_edit_aficiones.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_aficiones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_edit_aficiones.addActionListener(listener);
		panel_1.add(bt_edit_aficiones, BorderLayout.EAST);

		listaAficiones = new JList<String>(mdl_aficiones);
		listaAficiones.setModel(mdl_aficiones);
		listaAficiones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaAficiones.setSelectionForeground(new Color(0, 0, 0));
		listaAficiones.setSelectionBackground(new Color(255, 0, 255));
		listaAficiones.setBackground(new Color(240, 240, 240));
		scrollAficiones = new JScrollPane(listaAficiones);
		GridBagConstraints gbc_scrollAficiones = new GridBagConstraints();
		gbc_scrollAficiones.insets = new Insets(0, 0, 5, 0);
		gbc_scrollAficiones.gridwidth = 2;
		gbc_scrollAficiones.gridx = 0;
		gbc_scrollAficiones.gridy = 11;
		detalles.add(scrollAficiones, gbc_scrollAficiones);
		listaAficiones.setVisibleRowCount(3);
		
		// -- Zona con lista de contactos --
		JLabel etiqueta1 = new JLabel("CONTACTOS");
		etiqueta1.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta1.setFont(new Font("Tahoma", Font.BOLD, 20));
		zonaContactos.add(etiqueta1, BorderLayout.NORTH);
		
		JPanel zonaListaContactos = new JPanel();
		zonaContactos.add(zonaListaContactos, BorderLayout.CENTER);
		zonaListaContactos.setLayout(new BorderLayout(0, 0));	
		
		// Lista con todos los contactos
        mdl_contactos = DataManager.getContactos();
        listaContactos = new JList<String>(mdl_contactos);
        listaContactos.setModel(mdl_contactos);
		listaContactos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaContactos.setSelectionForeground(new Color(0, 0, 0));
		listaContactos.setSelectionBackground(new Color(255, 0, 255));
		listaContactos.setBackground(new Color(240, 240, 240));
		listaContactos.addMouseListener(listener);
		scrollContactos = new JScrollPane(listaContactos);
		zonaListaContactos.add(scrollContactos);
		
		bt_anadir = new JButton("Nuevo Contacto");
		bt_anadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		zonaContactos.add(bt_anadir, BorderLayout.SOUTH);
	}

	public void actualizarListaContactos() {
        mdl_contactos = DataManager.getContactos();
        listaContactos.setModel(mdl_contactos);
    }
	public static void actualizarDetallesContacto(){
		IDcontacto = Integer.parseInt(listaContactos.getSelectedValue().split("\\|")[0]);
		if(listaContactos.getSelectedValue() == null){ return; }
		// Nombre
		dt_nombre.setText(DataManager.getNombreContacto(IDcontacto));
		// Apellidos
		dt_apellidos.setText(DataManager.getApellidosContacto(IDcontacto));
		// Direccion
		dt_direccion.setText(DataManager.getDireccionContacto(IDcontacto));
		// FechaNac
		dt_fechaNac.setText(DataManager.getFechaNacimientoContacto(IDcontacto));
		// Genero
		dt_genero.setText(DataManager.getGeneroContacto(IDcontacto));
		// Notas
		dt_notas.setText(DataManager.getNotasContacto(IDcontacto));
		// Correos
		mdl_correos.clear();
		mdl_correos = DataManager.getCorreosContacto(IDcontacto);
		listaCorreos.setModel(mdl_correos);
		// Telefonos
		mdl_telefonos.clear();
		mdl_telefonos = DataManager.getTelefonosContacto(IDcontacto);
		listaTelefonos.setModel(mdl_telefonos);
		// Aficiones
		mdl_aficiones.clear();
		mdl_aficiones = DataManager.getAficionesContacto(IDcontacto);
		listaAficiones.setModel(mdl_aficiones);
		frame.revalidate();
	}
}