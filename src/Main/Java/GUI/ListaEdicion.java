package Main.Java.GUI;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

import Main.Java.BBDD.DataManager;

public class ListaEdicion extends JFrame{
	
    static String tipoEditor = "";
    JFrame frame;
    static DefaultListModel<String> mdl_edicion;
    static JList<String> listaEdicion;
    static JButton bt_anadir;
    private static Listeners listener;
    private static JLabel etiqueta;
    private static JScrollPane scrollPane;

	public ListaEdicion(String tipoEditor) {
        ListaEdicion.tipoEditor = tipoEditor;
        listener = MainGUI.listener;
        frame = new JFrame();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ListaEdicion.class.getResource("/Main/Resources/Images/aficiones.png")));
		frame.setTitle("Editor de " + tipoEditor);
        frame.setBounds(150, 150, 450, 300);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.addWindowListener(listener);
		
        etiqueta = new JLabel(tipoEditor);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setHorizontalTextPosition(SwingConstants.CENTER);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.add(etiqueta, BorderLayout.NORTH);

        // Boton añadir
        bt_anadir = new JButton("Añadir");
        bt_anadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bt_anadir.setBackground(Color.WHITE);
        bt_anadir.setForeground(Color.BLACK);
        bt_anadir.addActionListener(listener);
        frame.add(bt_anadir, BorderLayout.SOUTH);

        // Lista de aficiones
        actualizarModelo();
        listaEdicion = new JList<String>(mdl_edicion);
        listaEdicion.setModel(mdl_edicion);
		listaEdicion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listaEdicion.setSelectionForeground(new Color(0, 0, 0));
		listaEdicion.setSelectionBackground(new Color(255, 0, 255));
		listaEdicion.setBackground(new Color(240, 240, 240));
		scrollPane = new JScrollPane(listaEdicion);
        listaEdicion.addMouseListener(listener);
		frame.add(scrollPane);
	}
    public void actualizarLista(){
        actualizarModelo();
        listaEdicion.setModel(mdl_edicion);
    }
    public void actualizarModelo() {
        switch(tipoEditor) {
            case "Aficiones":
                mdl_edicion = DataManager.getAficiones();
                break;
            case "Correos":
                mdl_edicion = DataManager.getCorreos();
                break;
            case "Telefonos":
                mdl_edicion = DataManager.getTelefonos();
                break;
            default:
                mdl_edicion = new DefaultListModel<String>();
                break;
        }
    }
    
}
