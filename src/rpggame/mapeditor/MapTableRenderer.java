/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.mapeditor;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import rpggame.models.Dot;

/**
 *
 * @author papageno
 */
public class MapTableRenderer extends DefaultTableCellRenderer{
    public static Dot[][] map;
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
{ 
    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    c.setBackground(map[row][column].getColour());

    return c; 
} 
    
}
