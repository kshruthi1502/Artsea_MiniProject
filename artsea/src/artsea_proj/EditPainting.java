package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class EditPainting {

	JFrame frame;
	JLabel id,title,pyear,ptype;
	JTextField Ttitle,tid,tpyear,tptype;
	JButton back,edit;
	JTextArea Display;
	EditPainting(JFrame frame,String artistid)
	{
		this.frame=frame;
		
		frame.setTitle("Edit Painting");
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
			edit=new JButton("UPDATE");
			 edit.setBounds(230,230,100,50);
			 frame.add(edit);
			 edit.setBackground(Color.white);
			 back.setBackground(Color.white);
			 
				
		edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					String warning="";
					 String idstr="ID	:"+tid.getText();
					 String titlestr="Title	:"+Ttitle.getText();
					 String yearstr="Pyear	:"+tpyear.getText();
					 String typestr="Ptype	:"+tptype.getText();
				
					warning+=validateaid();

					if("".equals(validateaid()) )
					{
						 Connection con;
							Statement s;
						
							try {
							 con=ConnectionManager.getConnection();
								s = con.createStatement();
								String query1="UPDATE painting SET title='"+Ttitle.getText()+"' WHERE p_id ="+tid.getText()+"";
								String query2="UPDATE painting SET p_year="+tpyear.getText()+" WHERE p_id ="+tid.getText()+"";
								String query3="UPDATE painting SET p_type='"+tptype.getText()+"' WHERE p_id ="+tid.getText()+"";
								s.executeQuery(query1);
								s.executeQuery(query2);
								s.executeQuery(query3);
								s.executeQuery("commit");
								
			
								Display.setText(idstr+"\n"+titlestr+"\n"+yearstr+"\n"+typestr+"\n"+"Row Edited Successfully");
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
			edit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	String validateaid()
	{
		Connection con;
		Statement s;
		ResultSet rs;
		int flag=0;
		try {
			 con=ConnectionManager.getConnection();
				s = con.createStatement();
				rs = s.executeQuery("select * from painting");
		while(rs.next())
		{
		String a=Integer.toString(rs.getInt(1));
		if(a.equals(tid.getText()))
		{
			flag=1;	
				break;
		}
		
		}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		if(flag==0)
		{
				return "Entered p_id doesn't exist try Again !";
		}
		return "";
	}	
	String validateaidingid(String idval,String galleryid)
	{
		Connection con;
		Statement s;
		ResultSet rs;
		String artistidingid ="";
		try
		{
			con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query= "select a.a_id  from gallery g,have h,artist a where g.g_id="+galleryid+" and g.g_id=h.gid and h.aid=a.a_id ";
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
				artistidingid +="artist id already exists in specified gallery  ";
			}
			s.close();
			con.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}	
		return artistidingid;	
	}
}