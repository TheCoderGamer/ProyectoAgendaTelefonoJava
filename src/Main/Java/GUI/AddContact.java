package Main.Java.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import Main.Java.BBDD.DataManager;

public class AddContact extends JFrame {
	boolean editar = false;
	private Listeners listener = MainGUI.listener;
	JFrame frame;
	JTextField dt_nombre;
	JTextField dt_apellidos;
	JTextField dt_direccion;
	JTextField dt_notas;
	JTextField dt_fechanac;
	JTextField dt_genero;
	JLabel dl_nombre;
	JLabel dl_apellidos;
	JLabel dl_direccion;
	JLabel dl_notas;
	JLabel dl_fechanac;
	JLabel dl_genero;
	JLabel dl_correos;
	DefaultListModel<String> mdl_correos;
	DefaultListModel<String> mdl_telefonos;
	DefaultListModel<String> mdl_aficiones;
	JList<String> listaCorreos;
	JList<String> listaTelefonos;
	JList<String> listaAficiones;
	JScrollPane scrollCorreos;
	JScrollPane scrollTelefonos;
	JScrollPane scrollAficiones;
	JButton bt_edit_correos;
	JButton bt_edit_telefonos;
	JButton bt_edit_aficiones;
	JButton bt_guardar;
	private ButtonGroup buttonGroup;
	JRadioButton rd_persona;
	JRadioButton rd_empresa;
	JRadioButton rd_mascota;
	private JPanel p_correos;
	
	public AddContact() {
		initialize();
		editar = false;
	}
	public AddContact(int IDcontacto) {
		editar = true;
		initialize();
		AddData(IDcontacto);
	}
	
	private void AddData(int IDcontacto) {
		dt_nombre.setText(DataManager.getNombreContacto(IDcontacto));
		dt_apellidos.setText(DataManager.getApellidosContacto(IDcontacto));
		dt_direccion.setText(DataManager.getDireccionContacto(IDcontacto));
		dt_notas.setText(DataManager.getNotasContacto(IDcontacto));
		dt_fechanac.setText(DataManager.getFechaNacimientoContacto(IDcontacto));
		dt_genero.setText(DataManager.getGeneroContacto(IDcontacto));
		mdl_aficiones = DataManager.getAficionesContacto(IDcontacto);
		mdl_telefonos = DataManager.getTelefonosContacto(IDcontacto);
		mdl_correos = DataManager.getCorreosContacto(IDcontacto);

		listaAficiones.setModel(mdl_aficiones);
		listaTelefonos.setModel(mdl_telefonos);
		listaCorreos.setModel(mdl_correos);

		frame.repaint();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 150, 604, 594);
		frame.setTitle("Añadir contacto");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(new ImageIcon(getClass().getResource("/Main/../Resources/Images/app.png")).getImage());
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTipo = new JPanel();
		panel.add(panelTipo, BorderLayout.NORTH);
		panelTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label2 = new JLabel("Tipo de Contacto:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelTipo.add(label2);
		
		
		rd_persona = new JRadioButton("Persona");
		rd_persona.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rd_persona.setSelected(true);
		rd_persona.addActionListener(listener);
		panelTipo.add(rd_persona);
		
		rd_empresa = new JRadioButton("Empresa");
		rd_empresa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rd_empresa.addActionListener(listener);
		panelTipo.add(rd_empresa);
		
		rd_mascota = new JRadioButton("Mascota");
		rd_mascota.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rd_mascota.addActionListener(listener);
		panelTipo.add(rd_mascota);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rd_persona);
		buttonGroup.add(rd_empresa);
		buttonGroup.add(rd_mascota);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel.add(panelDatos, BorderLayout.CENTER);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 0, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		dl_nombre = new JLabel("NOMBRE:");
		dl_nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_nombre = new GridBagConstraints();
		gbc_dl_nombre.insets = new Insets(0, 0, 5, 5);
		gbc_dl_nombre.anchor = GridBagConstraints.EAST;
		gbc_dl_nombre.gridx = 0;
		gbc_dl_nombre.gridy = 0;
		panelDatos.add(dl_nombre, gbc_dl_nombre);
		
		dt_nombre = new JTextField();
		GridBagConstraints gbc_dt_nombre = new GridBagConstraints();
		gbc_dt_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_dt_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_nombre.gridx = 1;
		gbc_dt_nombre.gridy = 0;
		panelDatos.add(dt_nombre, gbc_dt_nombre);
		dt_nombre.setColumns(10);
		
		dl_apellidos = new JLabel("APELLIDOS:");
		dl_apellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_apellidos = new GridBagConstraints();
		gbc_dl_apellidos.anchor = GridBagConstraints.EAST;
		gbc_dl_apellidos.insets = new Insets(0, 0, 5, 5);
		gbc_dl_apellidos.gridx = 0;
		gbc_dl_apellidos.gridy = 1;
		panelDatos.add(dl_apellidos, gbc_dl_apellidos);
		
		dt_apellidos = new JTextField();
		GridBagConstraints gbc_dt_apellidos = new GridBagConstraints();
		gbc_dt_apellidos.insets = new Insets(0, 0, 5, 0);
		gbc_dt_apellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_apellidos.gridx = 1;
		gbc_dt_apellidos.gridy = 1;
		panelDatos.add(dt_apellidos, gbc_dt_apellidos);
		dt_apellidos.setColumns(10);
		
		dl_direccion = new JLabel("DIRECCION:");
		dl_direccion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_direccion = new GridBagConstraints();
		gbc_dl_direccion.anchor = GridBagConstraints.EAST;
		gbc_dl_direccion.insets = new Insets(0, 0, 5, 5);
		gbc_dl_direccion.gridx = 0;
		gbc_dl_direccion.gridy = 2;
		panelDatos.add(dl_direccion, gbc_dl_direccion);
		
		dt_direccion = new JTextField();
		GridBagConstraints gbc_dt_direccion = new GridBagConstraints();
		gbc_dt_direccion.insets = new Insets(0, 0, 5, 0);
		gbc_dt_direccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_direccion.gridx = 1;
		gbc_dt_direccion.gridy = 2;
		panelDatos.add(dt_direccion, gbc_dt_direccion);
		dt_direccion.setColumns(10);
		
		dl_notas = new JLabel("NOTAS:");
		dl_notas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_notas = new GridBagConstraints();
		gbc_dl_notas.anchor = GridBagConstraints.EAST;
		gbc_dl_notas.insets = new Insets(0, 0, 5, 5);
		gbc_dl_notas.gridx = 0;
		gbc_dl_notas.gridy = 3;
		panelDatos.add(dl_notas, gbc_dl_notas);
		
		dt_notas = new JTextField();
		GridBagConstraints gbc_dt_notas = new GridBagConstraints();
		gbc_dt_notas.insets = new Insets(0, 0, 5, 0);
		gbc_dt_notas.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_notas.gridx = 1;
		gbc_dt_notas.gridy = 3;
		panelDatos.add(dt_notas, gbc_dt_notas);
		dt_notas.setColumns(10);
		
		dl_fechanac = new JLabel("FECHA NACIMIENTO:");
		dl_fechanac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_fechanac = new GridBagConstraints();
		gbc_dl_fechanac.anchor = GridBagConstraints.EAST;
		gbc_dl_fechanac.insets = new Insets(0, 0, 5, 5);
		gbc_dl_fechanac.gridx = 0;
		gbc_dl_fechanac.gridy = 4;
		panelDatos.add(dl_fechanac, gbc_dl_fechanac);
		
		dt_fechanac = new JTextField();
		GridBagConstraints gbc_dt_fechanac = new GridBagConstraints();
		gbc_dt_fechanac.insets = new Insets(0, 0, 5, 0);
		gbc_dt_fechanac.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_fechanac.gridx = 1;
		gbc_dt_fechanac.gridy = 4;
		panelDatos.add(dt_fechanac, gbc_dt_fechanac);
		dt_fechanac.setColumns(10);
		
		dl_genero = new JLabel("GENERO:");
		dl_genero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_dl_genero = new GridBagConstraints();
		gbc_dl_genero.anchor = GridBagConstraints.EAST;
		gbc_dl_genero.insets = new Insets(0, 0, 5, 5);
		gbc_dl_genero.gridx = 0;
		gbc_dl_genero.gridy = 5;
		panelDatos.add(dl_genero, gbc_dl_genero);
		
		dt_genero = new JTextField();
		GridBagConstraints gbc_dt_genero = new GridBagConstraints();
		gbc_dt_genero.insets = new Insets(0, 0, 5, 0);
		gbc_dt_genero.fill = GridBagConstraints.HORIZONTAL;
		gbc_dt_genero.gridx = 1;
		gbc_dt_genero.gridy = 5;
		panelDatos.add(dt_genero, gbc_dt_genero);
		dt_genero.setColumns(10);
		
		p_correos = new JPanel();
		GridBagConstraints gbc_p_correos = new GridBagConstraints();
		gbc_p_correos.insets = new Insets(0, 0, 5, 0);
		gbc_p_correos.gridwidth = 2;
		gbc_p_correos.fill = GridBagConstraints.BOTH;
		gbc_p_correos.gridx = 0;
		gbc_p_correos.gridy = 6;
		panelDatos.add(p_correos, gbc_p_correos);
		p_correos.setLayout(new BorderLayout(0, 0));
		
		dl_correos = new JLabel("CORREOS");
		dl_correos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dl_correos.setHorizontalAlignment(SwingConstants.CENTER);
		p_correos.add(dl_correos, BorderLayout.CENTER);
		
		bt_edit_correos = new JButton("Editar");
		bt_edit_correos.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_correos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_edit_correos.addActionListener(listener);
		p_correos.add(bt_edit_correos, BorderLayout.EAST);
		
		
		mdl_correos = new DefaultListModel<String>();
		
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
		gbc_scrollCorreos.fill = GridBagConstraints.BOTH;
		gbc_scrollCorreos.gridx = 0;
		gbc_scrollCorreos.gridy = 7;
		panelDatos.add(scrollCorreos, gbc_scrollCorreos);
		listaCorreos.setVisibleRowCount(3);
		
		mdl_telefonos = new DefaultListModel<String>();
				
		JPanel p_telefonos = new JPanel();
		GridBagConstraints gbc_p_telefonos = new GridBagConstraints();
		gbc_p_telefonos.insets = new Insets(0, 0, 5, 0);
		gbc_p_telefonos.gridwidth = 2;
		gbc_p_telefonos.fill = GridBagConstraints.BOTH;
		gbc_p_telefonos.gridx = 0;
		gbc_p_telefonos.gridy = 8;
		panelDatos.add(p_telefonos, gbc_p_telefonos);
		p_telefonos.setLayout(new BorderLayout(0, 0));
		
		JLabel dl_telefonos = new JLabel("TELEFONOS");
		dl_telefonos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dl_telefonos.setHorizontalAlignment(SwingConstants.CENTER);
		p_telefonos.add(dl_telefonos, BorderLayout.CENTER);
		
		bt_edit_telefonos = new JButton("Editar");
		bt_edit_telefonos.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_telefonos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_edit_telefonos.addActionListener(listener);
		p_telefonos.add(bt_edit_telefonos, BorderLayout.EAST);
		
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
		gbc_scrollTelefonos.fill = GridBagConstraints.BOTH;
		gbc_scrollTelefonos.gridx = 0;
		gbc_scrollTelefonos.gridy = 9;
		panelDatos.add(scrollTelefonos, gbc_scrollTelefonos);
		
		mdl_aficiones = new DefaultListModel<String>();
		
		JPanel p_aficiones = new JPanel();
		GridBagConstraints gbc_p_aficiones = new GridBagConstraints();
		gbc_p_aficiones.insets = new Insets(0, 0, 5, 0);
		gbc_p_aficiones.gridwidth = 2;
		gbc_p_aficiones.fill = GridBagConstraints.BOTH;
		gbc_p_aficiones.gridx = 0;
		gbc_p_aficiones.gridy = 10;
		panelDatos.add(p_aficiones, gbc_p_aficiones);
		p_aficiones.setLayout(new BorderLayout(0, 0));
		
		JLabel dl_aficiones = new JLabel("AFICIONES");
		dl_aficiones.setHorizontalAlignment(SwingConstants.CENTER);
		dl_aficiones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p_aficiones.add(dl_aficiones, BorderLayout.CENTER);
		
		bt_edit_aficiones = new JButton("Editar");
		bt_edit_aficiones.setMargin(new Insets(0, 0, 0, 0));
		bt_edit_aficiones.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_edit_aficiones.addActionListener(listener);
		p_aficiones.add(bt_edit_aficiones, BorderLayout.EAST);

		listaAficiones = new JList<String>(mdl_aficiones);
		listaAficiones.setModel(mdl_aficiones);
		listaAficiones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaAficiones.setSelectionForeground(new Color(0, 0, 0));
		listaAficiones.setSelectionBackground(new Color(255, 0, 255));
		listaAficiones.setBackground(new Color(240, 240, 240));
		scrollAficiones = new JScrollPane(listaAficiones);
		GridBagConstraints gbc_scrollAficiones = new GridBagConstraints();
		gbc_scrollAficiones.gridwidth = 2;
		gbc_scrollAficiones.insets = new Insets(0, 0, 0, 5);
		gbc_scrollAficiones.fill = GridBagConstraints.BOTH;
		gbc_scrollAficiones.gridx = 0;
		gbc_scrollAficiones.gridy = 11;
		panelDatos.add(scrollAficiones, gbc_scrollAficiones);
		
		JPanel panel_superior = new JPanel();
		frame.getContentPane().add(panel_superior, BorderLayout.NORTH);
		panel_superior.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Nuevo Contacto");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_superior.add(label, BorderLayout.CENTER);
		
		bt_guardar = new JButton("GUARDAR");
		panel_superior.add(bt_guardar, BorderLayout.EAST);
		bt_guardar.addActionListener(listener);
		bt_guardar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}

	public void actualizarListas(){
		// Correos
		listaCorreos.setModel(mdl_correos);
		// Telefonos
		listaTelefonos.setModel(mdl_telefonos);
		// Aficiones
		listaAficiones.setModel(mdl_aficiones);
		frame.revalidate();
		frame.repaint();
	}

}
