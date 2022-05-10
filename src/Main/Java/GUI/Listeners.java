package Main.Java.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Main.Java.BBDD.DataManager;
import Main.Java.Tools.Logger;

class Listeners implements ActionListener, WindowListener, MouseListener {
    static ListaEdicion listaEdicion;
    static PertenenciaDialog pertenenciaDialog;
    private AddContact addContact;

    // Botones
    @Override
    public void actionPerformed(ActionEvent e) {
      Object fuente = e.getSource();
      
      if (fuente == MainGUI.mb1_bbdd) {
      }
      else if (fuente == MainGUI.mb2_aficiones){
        listaEdicion = new ListaEdicion("Aficiones");
      }
      else if (fuente == MainGUI.mb2_correos){
        listaEdicion = new ListaEdicion("Correos");
      }
      else if (fuente == MainGUI.mb2_telefonos){
        listaEdicion = new ListaEdicion("Telefonos");
      }

      else if(fuente == listaEdicion.bt_anadir){
        new AddDialog(listaEdicion.tipoEditor);
      }
      else if (fuente == listaEdicion.bt_delete){
        if (listaEdicion.listaEdicion.getSelectedIndex() != -1) {
          switch (listaEdicion.tipoEditor) {
            case "Aficiones":
              DataManager.deleteAficion(listaEdicion.listaEdicion.getSelectedValue());
              break;
            case "Correos":
              DataManager.deleteCorreo(listaEdicion.listaEdicion.getSelectedValue());
              break;
            case "Telefonos":
              DataManager.deleteTelefono(listaEdicion.listaEdicion.getSelectedValue());
              break;
          }
          listaEdicion.actualizarLista();
        }
      }
      
      else if (fuente == MainGUI.bt_edit_aficiones){
        if (MainGUI.IDcontacto == null) { return; }
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Aficiones");
      }
      else if (fuente == MainGUI.bt_edit_correos){
        if (MainGUI.IDcontacto == null) { return; }
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Correos");
      }
      else if (fuente == MainGUI.bt_edit_telefonos){
        if (MainGUI.IDcontacto == null) { return; }
        pertenenciaDialog = new PertenenciaDialog(MainGUI.IDcontacto, "Telefonos");
      }
      else if (fuente == MainGUI.bt_edit){
        if (MainGUI.IDcontacto == null) { return; }
        addContact = new AddContact(MainGUI.IDcontacto);
      }
      else if (fuente == MainGUI.bt_delete){
        if (MainGUI.IDcontacto == null) { return; }
        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que quiere eliminar este contacto?", "Eliminar contacto", JOptionPane.YES_NO_OPTION);
        int index = MainGUI.listaContactos.getSelectedIndex();
        if (opcion == JOptionPane.YES_OPTION) {
          DataManager.deleteContact(MainGUI.IDcontacto);
          MainGUI.actualizarListaContactos();
          MainGUI.listaContactos.setSelectedIndex(index-1);
          MainGUI.actualizarDetallesContacto();
        }
      }
      else if (fuente == MainGUI.bt_anadir){
        addContact = new AddContact();
      }
      else if (fuente == addContact.bt_edit_aficiones){
        pertenenciaDialog = new PertenenciaDialog("Aficiones", addContact.mdl_aficiones);
      }
      else if (fuente == addContact.bt_edit_correos){
        pertenenciaDialog = new PertenenciaDialog("Correos", addContact.mdl_correos);
      }
      else if (fuente == addContact.bt_edit_telefonos){
        pertenenciaDialog = new PertenenciaDialog("Telefonos", addContact.mdl_telefonos);
      }
      else if (fuente == addContact.bt_guardar){
        if (addContact.dt_nombre.getText().isEmpty()) { return; }
        int index = MainGUI.listaContactos.getSelectedIndex();

        if (!addContact.editar) {
          DataManager.addContact(
            addContact.dt_nombre.getText(), 
            addContact.dt_apellidos.getText(), 
            addContact.dt_direccion.getText(), 
            addContact.dt_fechanac.getText(), 
            addContact.dt_notas.getText(), 
            addContact.dt_genero.getText(),
            addContact.mdl_aficiones,
            addContact.mdl_correos,
            addContact.mdl_telefonos
            );
          }
          else {
            DataManager.editContact(
              MainGUI.IDcontacto,
              addContact.dt_nombre.getText(), 
              addContact.dt_apellidos.getText(), 
              addContact.dt_direccion.getText(), 
              addContact.dt_fechanac.getText(), 
              addContact.dt_notas.getText(), 
              addContact.dt_genero.getText(),
              addContact.mdl_aficiones,
              addContact.mdl_correos,
              addContact.mdl_telefonos
            );
          }
          MainGUI.actualizarListaContactos();
          MainGUI.listaContactos.setSelectedIndex(index);
          MainGUI.actualizarDetallesContacto();
          addContact.frame.dispose();
      }
      else if (fuente == addContact.rd_persona){
        addContact.dt_nombre.setEnabled(true);
        addContact.dl_nombre.setEnabled(true);
        addContact.dt_apellidos.setEnabled(true);
        addContact.dl_apellidos.setEnabled(true);
        addContact.dt_fechanac.setEnabled(true);
        addContact.dl_fechanac.setEnabled(true);
        addContact.dt_genero.setEnabled(true);
        addContact.dl_genero.setEnabled(true);
        addContact.dt_direccion.setEnabled(true);
        addContact.dl_direccion.setEnabled(true);
        addContact.dt_notas.setEnabled(true);
        addContact.dl_notas.setEnabled(true);
        addContact.scrollAficiones.setEnabled(true);
        addContact.listaAficiones.setEnabled(true);
        addContact.bt_edit_aficiones.setEnabled(true);
        addContact.scrollCorreos.setEnabled(true);
        addContact.listaCorreos.setEnabled(true);
        addContact.bt_edit_correos.setEnabled(true);
        addContact.scrollTelefonos.setEnabled(true);
        addContact.listaTelefonos.setEnabled(true);
        addContact.bt_edit_telefonos.setEnabled(true);
        addContact.frame.repaint();
      }
      else if (fuente == addContact.rd_empresa){
        addContact.dt_nombre.setEnabled(true);
        addContact.dl_nombre.setEnabled(true);
        addContact.dt_apellidos.setEnabled(false);
        addContact.dl_apellidos.setEnabled(false);
        addContact.dt_apellidos.setText("");
        addContact.dt_fechanac.setEnabled(false);
        addContact.dl_fechanac.setEnabled(false);
        addContact.dt_fechanac.setText("");
        addContact.dt_genero.setEnabled(false);
        addContact.dl_genero.setEnabled(false);
        addContact.dt_genero.setText("");
        addContact.dt_direccion.setEnabled(true);
        addContact.dl_direccion.setEnabled(true); 
        addContact.dt_notas.setEnabled(true);
        addContact.dl_notas.setEnabled(true);
        addContact.scrollAficiones.setEnabled(false);
        addContact.listaAficiones.setEnabled(false);
        addContact.bt_edit_aficiones.setEnabled(false);
        addContact.scrollCorreos.setEnabled(true);
        addContact.listaCorreos.setEnabled(true);
        addContact.bt_edit_correos.setEnabled(true);
        addContact.scrollTelefonos.setEnabled(true);
        addContact.listaTelefonos.setEnabled(true);
        addContact.bt_edit_telefonos.setEnabled(true);
        addContact.mdl_aficiones.clear();
        addContact.frame.repaint();
      }
      else if (fuente == addContact.rd_mascota){
        addContact.dt_nombre.setEnabled(true);
        addContact.dl_nombre.setEnabled(true);
        addContact.dt_apellidos.setEnabled(false);
        addContact.dl_apellidos.setEnabled(false);
        addContact.dt_apellidos.setText("");
        addContact.dt_fechanac.setEnabled(true);
        addContact.dl_fechanac.setEnabled(true);
        addContact.dt_genero.setEnabled(false);
        addContact.dl_genero.setEnabled(false);
        addContact.dt_genero.setText("");
        addContact.dt_direccion.setEnabled(true);
        addContact.dl_direccion.setEnabled(true);
        addContact.dt_notas.setEnabled(true);
        addContact.dl_notas.setEnabled(true);
        addContact.scrollAficiones.setEnabled(false);
        addContact.listaAficiones.setEnabled(false);
        addContact.bt_edit_aficiones.setEnabled(false);
        addContact.scrollCorreos.setEnabled(false);
        addContact.listaCorreos.setEnabled(false);
        addContact.bt_edit_correos.setEnabled(false);
        addContact.scrollTelefonos.setEnabled(false);
        addContact.listaTelefonos.setEnabled(false);
        addContact.bt_edit_telefonos.setEnabled(false);
        addContact.mdl_aficiones.clear();
        addContact.mdl_correos.clear();
        addContact.mdl_telefonos.clear();
        addContact.frame.repaint();
      }
  
    }

    // ---------- Ventanas cerrando ----------
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
      Object fuente = windowEvent.getSource();
      
      // Ventana principal
      if (fuente == MainGUI.frame) {
        if (JOptionPane.showConfirmDialog((JFrame) windowEvent.getSource(),
            "Esta seguro que desea cerrar el programa?", "Salir", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
              Logger.log("Programa cerrado");
              System.exit(0);
        }
      }
      // Lista edicion
      else if (listaEdicion != null && fuente == listaEdicion.frame) {
        MainGUI.actualizarDetallesContacto();
      }
      // Editando datos de la seccion de MainGUI
      else if (pertenenciaDialog != null && fuente == pertenenciaDialog.frame && pertenenciaDialog.editar){
        for (int i = 0; i < pertenenciaDialog.lista.length; i++) {
          Boolean esta = pertenenciaDialog.lista[i].isSelected();
          if (esta) {
            switch (pertenenciaDialog.tipo) {
              case "Aficiones":
                DataManager.insertarContactoAficion(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
              case "Correos":
                DataManager.insertarContactoCorreo(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
              case "Telefonos":
                DataManager.insertarContactoTelefono(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
            }
          }
          else {
            switch (pertenenciaDialog.tipo) {
              case "Aficiones":
                DataManager.borrarContactoAficion(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
              case "Correos":
                DataManager.borrarContactoCorreo(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
              case "Telefonos":
                DataManager.borrarContactoTelefono(MainGUI.IDcontacto, pertenenciaDialog.lista[i].toString());
                break;
            }
          }
        }
        MainGUI.actualizarDetallesContacto();
        pertenenciaDialog.frame.dispose();
      }
      else if (pertenenciaDialog != null && fuente == pertenenciaDialog.frame && !pertenenciaDialog.editar){
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (int i = 0; i < pertenenciaDialog.lista.length; i++) {
          if (pertenenciaDialog.lista[i].isSelected()) {
            model.addElement(pertenenciaDialog.lista[i].toString());
          }
        }
        switch (pertenenciaDialog.tipo) {
          case "Aficiones":
            addContact.mdl_aficiones = model;
            break;
          case "Correos":
            addContact.mdl_correos = model;
            break;
          case "Telefonos":
            addContact.mdl_telefonos = model;
            break;
        }
        addContact.actualizarListas();
        pertenenciaDialog.frame.dispose();
      }
    }
    
    @Override
    public void mouseClicked(final java.awt.event.MouseEvent evt) {
      Object fuente = evt.getSource();
      
      // Editar Aficion
      if (fuente == listaEdicion.listaEdicion){
        if (evt.getClickCount() == 2) {
          new EditDialog(listaEdicion.listaEdicion.getSelectedValue(), listaEdicion.tipoEditor);
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