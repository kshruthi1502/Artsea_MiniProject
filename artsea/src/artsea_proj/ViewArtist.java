package artsea_proj;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class ViewArtist {

	JFrame frame;
	JButton back;
	JLabel id;
	JLabel tid;
	JTextArea Display;
	ViewArtist(JFrame frame,String galleryid)
	{
		this.frame=frame;
		frame.setTitle("View Artist");
		Connection con;
		Statement s;
		ResultSet rs;
		 String columnames[] = {"GId","AId","Aname","Aphone","Aaddress"};
		 JTable t= new JTable();
		 Display= new JTextArea();
	        Display.setBounds(100, 280, 360, 120);
	        Display.setEditable(false);
	        Display.setBackground(Color.WHITE);
		try {
    		 con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query= "select g.g_id,a.a_id,a_name,a_phone,a_address from gallery g,have h,artist a where g.g_id="+galleryid+" and g.g_id=h.gid and h.aid=a.a_id ";
			rs = s.executeQuery(query);
				
				String[][] array = new String[15][5];
				int i=0;
				DefaultTableModel dtm = new DefaultTableModel(0, 0);
				dtm.setColumnIdentifiers(columnames);
				t.setModel(dtm);
				String a,b,c,d,e;
				int flag=0;
				while(rs.next())
				{
				 a=Integer.toString(rs.getInt(1));
				 b=Integer.toString(rs.getInt(2));
				 c=rs.getString(3);
				 d=rs.getString(4);
				 e=rs.getString(5);
				array[i][0]=a;
				array[i][1]=b;
				array[i][2]=c;
				array[i][3]=d;
				array[i][4]=e;
				dtm.addRow(new Object[] {a, b, c, d, e});
				i++;
				flag=1;
				}
				frame.add(Display);
				if(flag==0)
				{
				Display.setText("No artist in specified galleryid go back to add one!");
			
				}
		 s.close();
		 con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		t.setBounds(100, 70, 250, 100);
		t.getColumnModel().getColumn(0).setPreferredWidth(50);
		t.getColumnModel().getColumn(1).setPreferredWidth(50);
		t.getColumnModel().getColumn(2).setPreferredWidth(180);
		t.getColumnModel().getColumn(3).setPreferredWidth(160);
		t.getColumnModel().getColumn(4).setPreferredWidth(200);
		t.setFont(new Font("", Font.PLAIN, 15));
		JScrollPane sp = new JScrollPane(t);
		sp.setBounds(15, 70, 450, 150);
		frame.add(sp);
		 back=new JButton("GoBack");
			back.setBounds(500,500,95,50);  
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					frame.repaint();
					new Artist(frame,galleryid);
				}
			});
			frame.add(back);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			frame.getContentPane().setBackground(new Color(125, 193, 232));
	}
	
}
