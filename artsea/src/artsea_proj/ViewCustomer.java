package artsea_proj;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public class ViewCustomer {

	JFrame frame;
	JButton back;
	JLabel id;
	JLabel tid;
	JTextArea Display;
	ViewCustomer(JFrame frame)
	{
		this.frame=frame;
		frame.setTitle("View Customer");
		Connection con;
		Statement s;
		ResultSet rs;
		 String columnames[] = {"CId","Cname","Cphone","Caddress"};
		 JTable t= new JTable();
		 Display= new JTextArea();
	        Display.setBounds(100, 280, 360, 120);
	        Display.setEditable(false);
	        Display.setBackground(Color.WHITE);
		try {
    		 con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query= " select c_id,c_name,c_phone,c_address from customer ";
			rs = s.executeQuery(query);
				
				String[][] array = new String[15][4];
				int i=0;
				DefaultTableModel dtm = new DefaultTableModel(0, 0);
				dtm.setColumnIdentifiers(columnames);
				t.setModel(dtm);
				String a,b,c,d;
				while(rs.next())
				{
				 a=Integer.toString(rs.getInt(1));
				 b=rs.getString(2);
				 c=rs.getString(3);
				 d=rs.getString(4);
				
				array[i][0]=a;
				array[i][1]=b;
				array[i][2]=c;
				array[i][3]=d;
				//array[i][4]=e;
				dtm.addRow(new Object[] {a, b, c, d});
				i++;
				
				}
			//	frame.add(Display);
				
		 s.close();
		 con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		t.setBounds(100, 70, 250, 100);
		t.getColumnModel().getColumn(0).setPreferredWidth(50);
		t.getColumnModel().getColumn(1).setPreferredWidth(80);
		t.getColumnModel().getColumn(2).setPreferredWidth(180);
		t.getColumnModel().getColumn(3).setPreferredWidth(250);
	
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
					new Customer(frame);
				}
			});
			frame.add(back);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			frame.getContentPane().setBackground(new Color(125, 193, 232));
	}
	
}
