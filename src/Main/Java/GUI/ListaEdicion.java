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
import javax.swing.JPanel;

public class ListaEdicion extends JFrame{
	
    String tipoEditor = "";
    JFrame frame;
    DefaultListModel<String> mdl_edicion;
    JList<String> listaEdicion;
    private static Listeners listener;
    private JLabel etiqueta;
    private JScrollPane scrollPane;
    private JPanel p_botones;
    JButton bt_anadir;
    JButton bt_delete;

	public ListaEdicion(String tipoEditor) {
        this.tipoEditor = tipoEditor;
        listener = MainGUI.listener;
        frame = new JFrame();
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ListaEdicion.class.getResource("/Main/../Resources/Images/aficiones.png")));
		frame.setTitle("Editor de " + tipoEditor);
        frame.setBounds(150, 150, 450, 300);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.addWindowListener(listener);
		
        etiqueta = new JLabel(tipoEditor);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
		etiqueta.setHorizontalTextPosition(SwingConstants.CENTER);
		etiqueta.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(etiqueta, BorderLayout.NORTH);

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
		frame.getContentPane().add(scrollPane);
		
		p_botones = new JPanel();
		frame.getContentPane().add(p_botones, BorderLayout.SOUTH);
		
		bt_delete = new JButton("Eliminar");
		bt_delete.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bt_delete.addActionListener(listener);
		p_botones.add(bt_delete);
		
		bt_anadir = new JButton("Añadir");
		bt_anadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
        bt_anadir.addActionListener(listener);
		p_botones.add(bt_anadir);
	}
    public void actualizarLista(){
        actualizarModelo();
        listaEdicion.setModel(mdl_edicion);
    }
    private void actualizarModelo() {
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
