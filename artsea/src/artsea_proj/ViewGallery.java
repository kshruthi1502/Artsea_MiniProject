package artsea_proj;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
public class ViewGallery {
	JFrame frame;
	JButton back;
	
	ViewGallery(JFrame frame)
	{
		this.frame=frame;
		frame.setTitle("View Gallery");
		Connection con;
		Statement s;
		ResultSet rs;
		try {
    		 con=ConnectionManager.getConnection();
			s = con.createStatement();
			rs = s.executeQuery("select * from gallery");
				
				String[][] array = new String[15][3];
				int i=0;
				int j=0;
				while(rs.next())
				{
				String a=Integer.toString(rs.getInt(1));
				String b=rs.getString(2);
				String c=rs.getString(3);
				array[i][0]=a;
				array[i][1]=b;
				array[i][2]=c;
				i++;
				}
				int len=i;
				String[][] finalarray=new String[len][3];
				for(i=0;i<len;i++)
				{
					for(j=0;j<3;j++)
					{
						finalarray[i][j]=array[i][j];
					}
				}
				JTable t;
				 String columnames[] = {"GId","Gname","Location"};
				  t = new JTable(finalarray,columnames);
			 
				  t.setBounds(30,40,200,300);          
				  JScrollPane sp=new JScrollPane(t);    
				  frame.add(sp);  
				   sp.setBounds(15, 70, 450, 150);
		
		 s.close();
		 con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		back=new JButton("GoBack");
		back.setBounds(500,500,95,50);  
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Gallery(frame);
			}
		});
		frame.add(back);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.setLayout(null);
		frame.setSize(700,700);

		 back.setBackground(Color.white);
		frame.getContentPane().setBackground(new Color(125, 193, 232));
		frame.setVisible(true);
	}
}