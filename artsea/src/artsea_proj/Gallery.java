package artsea_proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Gallery
{
	public JButton viewg,addg,editg,delg,back;
	Label ll;
	JFrame frame;
	Gallery(JFrame frame)
	{
		this.frame=frame;
		ll = new Label();
		ll.setAlignment(Label.CENTER);
		ll.setBounds(110,250,500,200);
		ll.setFont(new Font("Platino",Font.ITALIC,40));
		ll.setText("GALLERY");
		Panel p=new Panel(new GridLayout(4,1));
		addg = new JButton("Add Gallery");
		editg=new JButton("Edit Gallery");
		delg=new JButton("Delete Gallery");
		viewg=new JButton("View Gallery");
		back=new JButton("GoBack");
		back.setBounds(500,150,95,50);  
		p.add(viewg);
		p.add(addg);
		p.add(editg);
		p.add(delg);
		frame.add(back);
		
		p.setBounds(5,30,200,100);
		frame.add(ll);
		frame.add(p);
		frame.setTitle("Gallery");
		frame.setLayout(null);
		frame.setSize(700,700);
		
	frame.getContentPane().setBackground(new Color(161, 222, 111));
		
		frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Homepage(frame);
			}
		});
		
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		editg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewg.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new ViewGallery(frame);
			}
		});
		addg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new AddGallery(frame);
			}
		});
		editg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new EditGallery(frame);
			}
		});
		delg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new DeleteGallery(frame);
			}
		});
		
		
	}
}
