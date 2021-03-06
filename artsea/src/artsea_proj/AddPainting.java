package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class AddPainting {

	JFrame frame;
	JLabel id,title,pyear,ptype;
	JTextField Ttitle,tid,tpyear,tptype;
	JButton back,add;
	JTextArea Display;
	AddPainting(JFrame frame,String artistid)
	{
		this.frame=frame;
		frame.setTitle("Add Painting");
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
	        title = new JLabel("Title");
	        title.setFont(new Font("Arial", Font.PLAIN, 15));
	        title.setSize(100, 20);
	        title.setLocation(100, 130);
	        Ttitle = new JTextField();
	        Ttitle.setFont(new Font("Arial", Font.PLAIN, 15));
	        Ttitle.setSize(190, 20);
	        Ttitle.setLocation(200, 130);
	        frame.add(Ttitle);
	        frame.add(title);
	        pyear=new JLabel("PYear");
	        pyear.setFont(new Font("Arial", Font.PLAIN, 15));
	        pyear.setSize(100, 20);
	        pyear.setLocation(100, 160);
	        tpyear = new JTextField();
	        tpyear.setFont(new Font("Arial", Font.PLAIN, 15));
	        tpyear.setSize(190, 20);
	        tpyear.setLocation(200, 160);
	        frame.add(tpyear);
	        frame.add(pyear);
	        ptype = new JLabel("Ptype");
	        ptype.setFont(new Font("Arial", Font.PLAIN, 15));
	        ptype.setSize(100, 20);
	        ptype.setLocation(100, 190);
	        tptype = new JTextField();
	        tptype.setFont(new Font("Arial", Font.PLAIN, 15));
	        tptype.setSize(190, 20);
	        tptype.setLocation(200, 190);
	        frame.add(ptype);
	        frame.add(tptype);
	        Display= new JTextArea();
	        Display.setBounds(100, 300, 360, 120);
	        Display.setEditable(false);
	        Display.setBackground(Color.WHITE);
	       frame.add(Display);
	        back=new JButton("GoBack");
			back.setBounds(500,500,95,50);  
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					frame.repaint();
					new Painting(frame,artistid);
				}
			});
			frame.add(back);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add=new JButton("INSERT");
			 add.setBounds(230,230,100,50);
			 frame.add(add);
			 add.setBackground(Color.white);
			 back.setBackground(Color.white);
				
		add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 String idstr="ID	:"+tid.getText();
					 String titlestr="Title	:"+Ttitle.getText();
					 String yearstr="Pyear	:"+tpyear.getText();
					 String typestr="Ptype	:"+tptype.getText();
					String warning="";
					warning+=validatepid(tid.getText()); 
					if("".equals(validatepid(tid.getText()))  )
					{
						 Connection con;
							Statement s;
						
							try {
							 con=ConnectionManager.getConnection();
								s = con.createStatement();
						String query1="insert into painting values("+tid.getText()+",'"+Ttitle.getText()+"',"+tpyear.getText()+",'"+tptype.getText()+"')";
							s.executeQuery(query1); 
							String query2="insert into paintby values("+artistid+","+tid.getText()+")";
							s.executeQuery(query2);
							s.executeQuery("commit");
							 s.close();
							 con.close();
							 Display.setText(idstr+"\n"+titlestr+"\n"+yearstr+"\n"+typestr+"\n"+"Row Added Successfully");
							}
							catch(Exception e1)
							{
								e1.printStackTrace();
							}	
					}
				else
					{
					Display.setText(warning);
					
					}
				}
			});
			frame.getContentPane().setBackground(new Color(125, 193, 232));
			add.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	String validatepid(String idval)
	{
		Connection con;
		Statement s;
		ResultSet rs;
		String paintingid ="";
		try
		{
			con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query="select * from painting";
			rs = s.executeQuery(query);
			int array[]=new int[20];
			int i=0,j,flag=0;
			while(rs.next())
			{
				array[i]=rs.getInt(1);
				i++;
			}
			for(j=0;j<array.length;j++)
			{
				if(array[j]==Integer.parseInt(idval))
				{
				
					flag=1;
					break;
				}	      
			}
			if(flag==1)
			{
				paintingid +="painting id already exists";
			}
			s.close();
			con.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}	
		return paintingid;
		
	}
	
}

