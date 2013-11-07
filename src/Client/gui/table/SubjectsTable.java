/**
 * 
 */
package gui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import remote.IClient;

/**
 * @author bmael
 *
 */
public class SubjectsTable extends JTable {

	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = 5528007055384561416L;

	public SubjectsTable(IClient user, DefaultTableModel model){
		super(model);
	}

	@Override
	public Class<? extends Object> getColumnClass(int column){
		return getValueAt(0, column).getClass();
	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{
		Component c = super.prepareRenderer(renderer, row, column);

		if (!isRowSelected(row))
			c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);

		return c;
	}

}
