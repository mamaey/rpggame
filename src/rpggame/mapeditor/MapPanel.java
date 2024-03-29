/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import rpggame.models.Dot;
import rpggame.models.DotType;
import rpggame.models.Map;
import rpggame.net.client.Client;

/**
 *
 * @author papageno
 */
public class MapPanel extends javax.swing.JPanel {

    /**
     * Creates new form MapPanel2
     */
    private Map map;
    private Dot dot;
    private double zoom=1;
    private static Client c;
    
    public MapPanel(Client c) {
        if(c==null)
            this.c = c;
        initComponents();
        jTable1.setVisible(false);
        jLabel1.setVisible(true);
        dot = new Dot(0, 0, Color.white, DotType.dtWalkOn);
    }
    public void setDot(Color color,DotType type){
        if(map!=null)
            this.dot = new Dot(map.getDotLength(),map.getDotHeight(),color,type);
    }
    public void setMap(Map map){
        this.map = map;
        if(map==null){
            jTable1.setVisible(false);
            jLabel1.setVisible(true);
        }else{
            jTable1.setVisible(true);
            jLabel1.setVisible(false);
            MapTableRenderer.map = map.getMap();
            dot = new Dot(map.getDotLength(), map.getDotHeight(), dot.getColour(), dot.getDotType());
            loadMap();
        }
    }
    public Map getMap(){
        return map;
    }
    private void setSize(){
        Dimension d = new Dimension((int)(zoom*map.getMapLength()*map.getDotLength()), (int)(zoom*map.getMapHeight()*map.getDotHeight()));
        setSize(d);
        jTable1.setSize(d);
        jTable1.setRowHeight((int)(zoom*map.getDotHeight()));
        TableColumn column;
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            column = jTable1.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)(zoom*map.getDotLength()));
        }
    }
    public void setZoom(double zoom){
        this.zoom=zoom;
        if(map!=null){
            setSize();
            jTable1.repaint();
        }
   }
    private void loadMap(){
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public int getRowCount() { return map.getMapHeight(); }
            @Override
            public int getColumnCount() { return map.getMapLength(); }
            @Override
            public Object getValueAt(int row, int col) {return "";}
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTable1.setModel(tableModel);
        setSize();
        jTable1.repaint();
    }
    private void eventDot(){
        int[] x = jTable1.getSelectedColumns();
        int[] y = jTable1.getSelectedRows();
        for(int xs:x)
            for(int ys:y)
                map.getMap()[xs][ys]=dot;
        jTable1.repaint();
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        MapTableRenderer myRenderer = new MapTableRenderer();
        jTable1.setDefaultRenderer(Object.class, myRenderer);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                eventDot();
            }
        });
        add(jTable1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Bitte wählen Sie eine Map!");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
