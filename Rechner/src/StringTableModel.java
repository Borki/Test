import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class StringTableModel extends DefaultTableModel {
	private Vector<String> m_aStringLst = new Vector<>();
	
	public void addEingabe(String pcEingabe)
	{
		m_aStringLst.add(pcEingabe);
		fireTableRowsInserted(m_aStringLst.size()-1, m_aStringLst.size()-1);
	}
	
	public void add(String text) {
		m_aStringLst.add(text);
	}
	
	public int getRowCount()
	{
		if(m_aStringLst==null)
		return 0;
		return m_aStringLst.size();
	}
	
	public String getColumnName(int column) {
		return "Eingabe";
	}
	
	public int getColumnCount()
	{
		return 1;
	}
	
	public void clear() {
		m_aStringLst.clear();
	}
	
	public Object getValueAt(int pnRow, int pnColumn)
	{
		return m_aStringLst.elementAt(pnRow);
	}
	
	public boolean isCellEditable(int row, int col){
        return false;
    }
}
		
	