/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizar;

import dao.productoDao;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.producto;

/**
 *
 * @author LN710Q
 */
public class Vista extends JFrame{
    
    public int ANCHO =900,anchoTF=120,anchoB=100;
    public int ALTO=500,altoTF=30,altoB=30;
    public JTextField nombre,codigo,precio,cantidad, tipo;
    public JLabel nombrel,codigol,preciol,cantidadl, disponibilidadl, tipol;
    ButtonGroup disponibilidad=new ButtonGroup();
    public JComboBox TIPO;
    
    public JTable resultados;
    
    public JButton BUSCAR, ELIMINAR, INSERTAR, ACTUALIZAR, LIMPIAR;
    public JRadioButton No;
    public JRadioButton Si;
    
    public JPanel tabla;
    
    DefaultTableModel tm;
    
    public Vista(){
        super("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container =getContentPane();
        container.add(nombre);
        container.add(codigo);
        container.add(precio);
        container.add(cantidad);
        container.add(BUSCAR);
        container.add(INSERTAR);
        container.add(ELIMINAR);
        container.add(ACTUALIZAR);
        container.add(LIMPIAR);
        container.add(TIPO);
        container.add(Si);
        container.add(No);
        container.add(nombrel);
        container.add(codigol);
        container.add(preciol);
        container.add(cantidadl);
        container.add(disponibilidadl);
        container.add(tabla);
        //setPreferredSize(new Dimension(ANCHO,ALTO));
        setSize(600,600);
        eventos();
    }

    private void agregarLabels() {
        nombrel=new JLabel("Nombre: ");
        nombrel.setBounds(10,40,anchoTF,altoTF);
        
        codigol=new JLabel("Codigo: ");
        codigol.setBounds(10,80,anchoTF,altoTF);
        
        preciol=new JLabel("Precio: ");
        preciol.setBounds(10,120,anchoTF,altoTF);
        
        cantidadl=new JLabel("Cantidad: ");
        cantidadl.setBounds(10,160,anchoTF,altoTF);
        
        disponibilidadl=new JLabel("Disponibilidad: ");
        disponibilidadl.setBounds(10,200,anchoTF,altoTF);
        
        tipol=new JLabel("Tipo: ");
        tipol.setBounds(40,280,anchoTF,altoTF);
    }

    private void formulario() {
        nombre=new JTextField();
        nombre.setBounds(new Rectangle(100,40,200,altoTF));
        
        codigo=new JTextField();
        codigo.setBounds(new Rectangle(100,80,anchoTF,altoTF));
        
        precio=new JTextField();
        precio.setBounds(new Rectangle(100,120,anchoTF,altoTF));
        
        cantidad=new JTextField();
        cantidad.setBounds(new Rectangle(100,160,anchoTF,altoTF));

        
        tipo=new JTextField();
        tipo.setBounds(new Rectangle(200,140,anchoTF,altoTF));
        
        TIPO=new JComboBox();
        
        Si=new JRadioButton("si", true);
        Si.setBounds(100, 200, 40, 30);
        No=new JRadioButton("no");
        No.setBounds(150, 200, 40, 30);
        
        BUSCAR=new JButton("Buscar");
        BUSCAR.setBounds(new Rectangle(190,40,anchoB,altoB));
        
        INSERTAR=new JButton("Insertar");
        INSERTAR.setBounds(new Rectangle(190,200,anchoB,altoB));
        
        ELIMINAR=new JButton("Eliminar");
        ELIMINAR.setBounds(new Rectangle(250,200,anchoB,altoB));
        
        ACTUALIZAR=new JButton("Actualizar");
        ACTUALIZAR.setBounds(new Rectangle(300,200,anchoB,altoB));
        
        LIMPIAR=new JButton("Limpiar");
        LIMPIAR.setBounds(new Rectangle(380,200,anchoB,altoB));
        resultados=new JTable();
        tabla=new JPanel();
        
        disponibilidad=new ButtonGroup();
        disponibilidad.add(Si);
        disponibilidad.add(No);
        
        resultados=new JTable();
        tabla.setBounds(10,250,500,200);
        tabla.add(new JScrollPane(resultados));
    }

    private void llenarTabla() {
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Precio");
        tm.addColumn("Cantidad");
        tm.addColumn("Disponibilidad");
        tm.addColumn("Tipo");
        
        productoDao fd =new productoDao();
        ArrayList<producto> filtros= fd.readAll();
        
        for(producto fi: filtros){
            tm.addRow(new Object[]{fi.getCodigo(), fi.getNombre(), fi.getCodigo(), fi.getPrecio(),fi.getCantidad(),fi.getTipo()});
        
    }
        resultados.setModel(tm);
    }
    
    private void eventos() {
        INSERTAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd =new productoDao();
                producto f=new producto(nombre.getText(), Integer.parseInt(codigo.getText()), TIPO.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()),true);
                if(No.isSelected()){
                    f.setDisponibilidad(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Producto registrado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el producto.");
                }
            }
        });
        ACTUALIZAR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd= new productoDao();
                producto f=new producto(nombre.getText(), Integer.parseInt(codigo.getText()), TIPO.getSelectedItem().toString(), Integer.parseInt(cantidad.getText()),true);
                if(No.isSelected()){
                    f.setDisponibilidad(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Producto modificado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el producto.");
                }
            }
            
        });
        ELIMINAR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd= new productoDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null, "Producto eliminado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el producto.");
                }
            }
        });
        BUSCAR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd= new productoDao();
                producto f=fd.read(codigo.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "El producto buscado no se ha encontrado");
                }
                else{
                    nombre.setText(f.getNombre());
                    codigo.setText((f.getCodigo()));
                    tipo.setText(f.getTipo());
                    if(f.getDisponibilidad()){
                        Si.setSelected(true);
                    }
                    else{
                        No.setSelected(true);
                    }
                }
            }
            
        });
        LIMPIAR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); 
    }
    public void limpiarCampos(){
        nombre.setText("");
        codigo.setText("");
        tipo.setText("");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Vista().setVisible(true);
            }
        });
    }
}
