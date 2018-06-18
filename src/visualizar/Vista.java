/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizar;

import dao.productoDao;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.producto;

/**
 *
 * @author LN710Q
 */
public class Vista extends JPanel{
    
    public int ANCHO =900,anchoTF=120,anchoB=100;
    public int ALTO=500,altoTF=30,altoB=30;
    public JTextField nombre,codigo,precio,cantidad, tipo;
    public JLabel nombrel,codigol,preciol,cantidadl, disponibilidadl, tipol;
    public JComboBox TIPO;
    
    public JButton BUSCAR, ELIMINAR, INSERTAR, ACTUALIZAR, LIMPIAR;
    public JRadioButton No;
    public JRadioButton Si;
    
    
    DefaultTableModel tm;
    
    public Vista(){
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
        //BOTONES
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
        
        TIPO=new JComboBox();
        TIPO.setBounds(new Rectangle(410,200, anchoTF, altoTF));
        
        Si=new JRadioButton("si", true);
        Si.setBounds(100, 200, 40, 30);
        No=new JRadioButton("no");
        No.setBounds(150, 200, 40, 30);
        
        nombre.setEditable(true);
        codigo.setEditable(true);
        precio.setEditable(true);
        cantidad.setEditable(true);
        tipo.setEditable(true);
        
        //labels text
        nombrel=new JLabel("Nombre: ");
        nombrel.setBounds(new Rectangle(10,40,anchoTF,altoTF));
        
        codigol=new JLabel("Codigo: ");
        codigol.setBounds(new Rectangle(10,80,anchoTF,altoTF));
        
        preciol=new JLabel("Precio: ");
        preciol.setBounds(new Rectangle(10,120,anchoTF,altoTF));
        
        cantidadl=new JLabel("Cantidad: ");
        cantidadl.setBounds(new Rectangle(10,160,anchoTF,altoTF));
        
        disponibilidadl=new JLabel("Disponibilidad: ");
        disponibilidadl.setBounds(new Rectangle(10,200,anchoTF,altoTF));
        
        tipol=new JLabel("Tipo: ");
        tipol.setBounds(new Rectangle(40,280,anchoTF,altoTF));
        
        ACTUALIZAR.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                productoDao fd= new productoDao();
                producto f=new producto(codigo.getText(), marca.getSelectedItem().toString(), Integer.parseInt(stock.getText()),true);
                if(No.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro.");
                }
            }
            
        });
        
        
        
        add(nombre);
        add(codigo);
        add(precio);
        add(cantidad);
        add(BUSCAR);
        add(INSERTAR);
        add(ELIMINAR);
        add(ACTUALIZAR);
        add(LIMPIAR);
        add(TIPO);
        add(Si);
        add(No);
        
        add(nombrel);
        add(codigol);
        add(preciol);
        add(cantidadl);
        add(disponibilidadl);
        
        setLayout(null);
        setPreferredSize(new Dimension(ANCHO,ALTO));
    }
    
    
}
