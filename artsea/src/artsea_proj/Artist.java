package artsea_proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Artist {

	JButton viewa,adda,edita,dela,back;
	Label ll;
	String id;
	JFrame frame;
	Artist(JFrame frame,String id)
	{
		this.frame=frame;
		frame.setTitle("Artist");
		ll = new Label();
		ll.setAlignment(Label.CENTER);
		ll.setBounds(110,250,500,200);
		ll.setFont(new Font("Platino",Font.ITALIC,40));
		ll.setText("ARTIST");
		Panel p=new Panel(new GridLayout(4,1));
		adda= new JButton("Add Artist");
		edita=new JButton("Edit Artist");
		dela=new JButton("Delete Artist");
		viewa=new JButton("View Artist");
		back=new JButton("Goback");
		 adda.setBackground(Color.white);
		 edita.setBackground(Color.white);
		 dela.setBackground(Color.white);
		 viewa.setBackground(Color.white);
		 back.setBackground(Color.white);
		back.setBounds(500,150,95,50);  
		p.add(viewa);
		p.add(adda);
		p.add(edita);
		p.add(dela);
		p.setBounds(5,30,200,100);
		frame.add(back);
		frame.add(ll);
		frame.add(p);
		frame.setTitle("Artist");
		frame.setLayout(null);
		frame.setSize(700,700);
		frame.getContentPane().setBackground(new Color(255, 234, 156));
		frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
			frame.repaint();
				new Artist_gid(frame);
			}
		});
		viewa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new ViewArtist(frame,id);
			}
		});
		adda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new AddArtist(frame,id);
			}
		});
		edita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new EditArtist(frame,id);
			}
		});
		dela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new DeleteArtist(frame,id);
			}
		});
		
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		edita.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		dela.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		adda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}
