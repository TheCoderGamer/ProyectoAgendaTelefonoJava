package Main.Java.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import Main.Java.BBDD.DataManager;

import java.awt.Font;

public class AddDialog extends JFrame{
	
	private String tipoEditor = "";
	private JFrame frame = new JFrame();
	private JButton bt_aceptar;
	private JTextField dt_adicionNueva;
	private JLabel etiqueta1;
	
	public AddDialog(String tipoEditor) {
		this.tipoEditor = tipoEditor;
		frame.setTitle("Añadir " + tipoEditor);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditDialog.class.getResource("/Main/Resources/Images/aficiones.png")));
		frame.setBounds(200, 200, 450, 140);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	close(dt_adicionNueva.getText());
		    }
		});

		dt_adicionNueva = new JTextField();
		dt_adicionNueva.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dt_adicionNueva.setText("");
		dt_adicionNueva.setColumns(10);
		frame.add(dt_adicionNueva, BorderLayout.CENTER);

		etiqueta1 = new JLabel(tipoEditor + " a añadir:");
		etiqueta1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		frame.add(etiqueta1, BorderLayout.NORTH);

		bt_aceptar = new JButton("Aceptar");
		bt_aceptar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_aceptar.setBackground(Color.WHITE);
		bt_aceptar.setForeground(Color.BLACK);
		bt_aceptar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				close(dt_adicionNueva.getText());
			} 
		});
		frame.add(bt_aceptar, BorderLayout.SOUTH);

	}    
	private void close(String newData) {
		if (newData.trim().equals("")) {frame.removeAll(); frame.dispose(); return;}

		switch(tipoEditor) {
			case "Aficiones":
				DataManager.insertAficion(newData);
				break;
			case "Correos":
				DataManager.insertCorreo(newData);
				break;
			default :
				break;
		}
		Listeners.listaEdicion.actualizarLista();		
		frame.removeAll();
		frame.dispose();
	}
}
