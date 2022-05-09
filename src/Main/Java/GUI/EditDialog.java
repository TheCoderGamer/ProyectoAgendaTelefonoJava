package Main.Java.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import Main.Java.BBDD.DataManager;

import java.awt.Font;

public class EditDialog extends JFrame{
	
	private String tipoEditor = "";
	private JFrame frame = new JFrame();
	private JButton bt_aceptar;
	private JButton bt_eliminar;
	private String oldData;
	private JTextField dt_edicion;
	private JPanel botones;
	
	public EditDialog(String oldData, String tipoEditor) {
		this.tipoEditor = tipoEditor;
		this.oldData = oldData;
		frame.setTitle("Editar " + tipoEditor);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(EditDialog.class.getResource("/Main/../Resources/Images/aficiones.png")));
		frame.setBounds(200, 200, 450, 120);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				close(dt_edicion.getText());
		    }
		});

		botones = new JPanel();
		frame.add(botones, BorderLayout.SOUTH);

		dt_edicion = new JTextField();
		dt_edicion.setText(oldData);
		dt_edicion.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dt_edicion.setColumns(10);
		frame.add(dt_edicion, BorderLayout.CENTER);
		
		bt_aceptar = new JButton("Aceptar");
		bt_aceptar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_aceptar.setBackground(Color.WHITE);
		bt_aceptar.setForeground(Color.BLACK);
		bt_aceptar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				close(dt_edicion.getText());
			} 
		});
		botones.add(bt_aceptar);
		
		bt_eliminar = new JButton("Eliminar");
		bt_eliminar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_eliminar.setBackground(Color.WHITE);
		bt_eliminar.setForeground(Color.BLACK);
		bt_eliminar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				delete(dt_edicion.getText());
			} 
		});
		botones.add(bt_eliminar);

		

	}
	private void close(String newData) {
		switch(tipoEditor) {
			case "Aficiones":
				DataManager.editAficion(oldData, newData);
				break;
			case "Correos":
				DataManager.editCorreo(oldData, newData);
				break;
			case "Telefonos":
				DataManager.editTelefono(oldData, newData);
				break;
			default:
				break;
		}		
		Listeners.listaEdicion.actualizarLista();	
		frame.removeAll();	
		frame.dispose();
	}

	private void delete(String aficion){
		switch(tipoEditor) {
			case "Aficiones":
				DataManager.deleteAficion(oldData);
				break;
			case "Correos":
				DataManager.deleteCorreo(oldData);
				break;
			case "Telefonos":
				DataManager.deleteTelefono(oldData);
				break;
			default:
				break;
		}
		Listeners.listaEdicion.actualizarLista();	
		frame.removeAll();	
		frame.dispose();
	}
}
