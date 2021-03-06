package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class Artist_gid {

	JFrame frame;
	JLabel id;
	JTextField tid;
	JButton viewopt,back;
	JTextArea Display;
	JLabel welcome;
	Artist_gid(JFrame frame)
	{
		this.frame=frame;
		frame.setTitle("Artist");
		welcome=new JLabel("Enter gallery id to view options");
		welcome.setBounds(200,30, 200,80);
		frame.add(welcome);
		id = new JLabel("Id");
        id.setFont(new Font("Arial", Font.PLAIN, 15));
        id.setSize(100, 20);
        id.setLocation(100, 100);
        tid = new JTextField();
        tid.setFont(new Font("Arial", Font.PLAIN, 15));
        tid.setSize(190, 20);
        tid.setLocation(200, 100);
        frame.add(tid);
        frame.add(id);
        Display= new JTextArea();
        Display.setBounds(100, 280, 360, 120);
        Display.setEditable(false);
        Display.setBackground(Color.WHITE);
        frame.add(Display);
        viewopt=new JButton("VIEW OPTIONS");
        viewopt.setBounds(230,210,100,50);
        frame.add(viewopt);
        viewopt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        viewopt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int flag=0;
				 Connection con;
					Statement s;
					ResultSet rs;
					try {
			   		 con=ConnectionManager.getConnection();
						s = con.createStatement();
						rs = s.executeQuery("select * from gallery");
					
			
						while(rs.next())
						{
						String a=Integer.toString(rs.getInt(1));
						String b=rs.getString(2);
						String c=rs.getString(3);
						if(a.equals(tid.getText()))
						{
							flag=1;								
					
						break;
						}
						
						}
						s.close();
						con.close();
						if(flag==0)
						{
							Display.setText("Entered g_id doesn't exist try Again !");	
						}
						else
						{
							frame.getContentPane().removeAll();
							frame.repaint();
							new Artist(frame,tid.getText());
						}
						s.close();
						 con.close();
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
			}
		});
        back=new JButton("GoBack");
        back.setBackground(Color.white);
        viewopt.setBackground(Color.white);
		back.setBounds(500,500,95,50);  
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Homepage(frame);
			}
		});
		frame.add(back);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.setTitle("Artist");
		frame.setLayout(null);
		frame.setSize(700,700);
		frame.getContentPane().setBackground(new Color(125, 193, 232));
		frame.getContentPane().setBackground(new Color(161, 222, 111));
		
		frame.setVisible(true);
	}
}
