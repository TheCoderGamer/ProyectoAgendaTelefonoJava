package Main.Java.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Main.Java.BBDD.DataManager;

class Listeners implements ActionListener, WindowListener, MouseListener {
    static ListaEdicion listaEdicion;
    static PertenenciaDialog pertenenciaDialog;

    @Override
    public void actionPerformed(ActionEvent e) {
      Object fuente = e.getSource();
      
      // Botones
      if (fuente == MainGUI.mb1_bbdd) {
      }
      else if (fuente == MainGUI.mb2_aficiones){
        listaEdicion = new ListaEdicion("Aficiones");
      }
      else if (fuente == MainGUI.mb2_correos){
        listaEdicion = new ListaEdicion("Correos");
      }

      else if(fuente == ListaEdicion.bt_anadir){
        new AddDialog(ListaEdicion.tipoEditor);
      }
      
      else if (fuente == MainGUI.bt_edit_aficiones){
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Aficiones");
      }
      else if (fuente == MainGUI.bt_edit_correos){
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Correos");
      }
      else if (fuente == MainGUI.bt_edit_telefonos){
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Telefonos");
      }
  
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
      Object fuente = windowEvent.getSource();
      
      // Ventana principal
      if (fuente == MainGUI.frame) {
        if (JOptionPane.showConfirmDialog((JFrame) windowEvent.getSource(),
            "Esta seguro que desea cerrar el programa?", "Salir", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
      }
      else if (listaEdicion != null && fuente == listaEdicion.frame) {
        MainGUI.actualizarDetallesContacto();
      }
      else if (fuente == pertenenciaDialog.frame){
       
        for (int i = 0; i < pertenenciaDialog.lista.length; i++) {
          Boolean esta = pertenenciaDialog.lista[i].isSelected();

          //TODO: Tiene que comprobar si esta tickeado y si lo esta comprobar si esta en la tabla
          //      contactosaficiones.. y no lo esta añadirlo. Y si no esta tickeado hacer lo mismo pero quitandolo


        }
        

        MainGUI.actualizarDetallesContacto();
        pertenenciaDialog.frame.dispose();
      }
    }
    
    @Override
    public void mouseClicked(final java.awt.event.MouseEvent evt) {
      Object fuente = evt.getSource();
      
      // Editar Aficion
      if (fuente == ListaEdicion.listaEdicion){
        if (evt.getClickCount() == 2) {
          new EditDialog(ListaEdicion.listaEdicion.getSelectedValue(), ListaEdicion.tipoEditor);
        }
      }
      
      else if (fuente == MainGUI.listaContactos){
        MainGUI.actualizarDetallesContacto();
      }
    }

    @Override
    public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }


    @Override
    public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }


    @Override
    public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }


    @Override
    public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }


    @Override
    public void windowActivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }


    @Override
    public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
    }
  }