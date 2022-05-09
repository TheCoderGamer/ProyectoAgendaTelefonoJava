package Main.Java.GUI;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import Main.Java.BBDD.DataManager;

public class PertenenciaDialog {
    
    JFrame frame;
    JList<CheckListItem> list;
    CheckListItem[] lista;
    Listeners listener = MainGUI.listener;
    String tipo; // Aficiones, Correos, Telefonos
       
    public PertenenciaDialog(int IDcontacto, String tipo) {
        this.tipo = tipo;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(200, 200, 450, 340);
        frame.setTitle("Pertenencia de " + tipo);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ListaEdicion.class.getResource("/Main/../Resources/Images/aficiones.png")));
        
        crearLista(IDcontacto, tipo);
        
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList<CheckListItem> list = (JList<CheckListItem>) event.getSource();
                int index = list.locationToIndex(event.getPoint());// Get index of item
                // clicked
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected()); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        });
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
        frame.addWindowListener(listener);
    }
    
    private void crearLista(int IDcontacto, String tipo){
        Object[] listaContacto;
        Object[] listaTodo;

        switch (tipo){
            case "Aficiones":
                listaContacto = DataManager.getAficionesContacto(IDcontacto).toArray();
                listaTodo = DataManager.getAficiones().toArray();
                break;
            case "Correos":
                listaContacto = DataManager.getCorreosContacto(IDcontacto).toArray();
                listaTodo = DataManager.getCorreos().toArray();
                break;
            case "Telefonos":
                listaContacto = DataManager.getTelefonosContacto(IDcontacto).toArray();
                listaTodo = DataManager.getTelefonos().toArray();
                break;
            default:
                return;
        }
        
        
        
        List <CheckListItem> listaTemp = new ArrayList<CheckListItem>();
        

       for(int i = 0; i < listaTodo.length; i++){
           boolean esta = false;
           for(int j = 0; j < listaContacto.length; j++){
               if(listaTodo[i].equals(listaContacto[j])){
                   esta = true;
                   break;
               }
           }
           listaTemp.add(new CheckListItem(listaTodo[i].toString(), esta));
        }
        lista  = new CheckListItem[listaTemp.size()];
        listaTemp.toArray(lista);
        list = new JList<CheckListItem>(lista);    
    }
}

class CheckListItem {
    
    private String label;
    private boolean isSelected = false;
    
    public CheckListItem(String label, boolean isSelected) {
        this.label = label;
        this.isSelected = isSelected;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    @Override
    public String toString() {
        return label;
    }
}

class CheckListRenderer extends JCheckBox implements ListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean hasFocus) {
        setEnabled(list.isEnabled());
        setSelected(((CheckListItem) value).isSelected());
        setFont(list.getFont());
        setBackground(list.getBackground());
        setForeground(list.getForeground());
        setText(value.toString());
        return this;
    }
}