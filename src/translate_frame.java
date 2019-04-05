import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

import org.json.JSONException;
import org.json.JSONObject;

public class translate_frame extends JFrame implements ActionListener {

	
		//color
		Color white = new Color(255,255,255);
		Color light_black = new Color(61,61,61);
		Color black = new Color(0,0,0);
		Color green = new Color(108,254,0);
		Color blue = new Color(0,150,255);
		
		
		Color background_color = white;
		Color foreground_color = black;
		
	
		//move frame
		int pX,pY;
		
		
		//json
		JsonReader jr = new JsonReader();
		JSONObject json = new JSONObject();
	
		
		//textarea and scrollpane
		JTextArea original_lang = new JTextArea();
		JTextArea translated_lang = new JTextArea();
		
		JScrollPane sp1 = new JScrollPane(original_lang);
		JScrollPane sp2 = new JScrollPane(translated_lang);
		
		
		//buttons
		JButton minimize =  new JButton(new ImageIcon(getClass().getResource(("minimize.png"))));
		JButton translate =  new JButton("translate" , new ImageIcon(getClass().getResource(("Google-Translate-icon.png"))));
		JButton exit =  new JButton(new ImageIcon(getClass().getResource(("delete-icon.png"))));
		JButton info =  new JButton(new ImageIcon(getClass().getResource(("Button-Info-icon.png"))));
		JButton swap =  new JButton(new ImageIcon(getClass().getResource(("MetroUI-Apps-Live-Sync-icon.png"))));
		JButton change_theme =  new JButton(new ImageIcon(getClass().getResource(("Theme-icon.png"))));
		JButton clear =  new JButton("clear" , new ImageIcon(getClass().getResource(("Actions-edit-clear-locationbar-rtl-icon.png"))));
		
		
		//comboboxes
		String []languages = new String[] {"en english","tr turkish"};
				
		JComboBox <String> first_lang = new JComboBox<String> (languages);
		JComboBox <String> second_lang = new JComboBox<String> (languages);
		
		
		
		
		
		
		
		
		
		
		
		
	
		translate_frame(){
			
			create_frame();
			
			buttons();
			scrollpane();
			comboboxes();
		
			
			move_frame();
		}
	
	
		
		
		
		
		
	
		void create_frame(){
			
			this.setLayout(null);
			this.getContentPane().setBackground(background_color);
			//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("42697-fire-icon.png"))));
			
			setSize(545,270);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			setUndecorated(true);
			setOpacity(0.7f);
			setAlwaysOnTop(true);
			setLocationRelativeTo(null);
			setVisible(true);
			
		}
		
		
		
	
	
		void buttons() {
			add(translate);
			translate.setBounds(405,230,130,30);
			translate.addActionListener(this);		
			translate.setFocusable(false);
			translate.setBackground(background_color);
			translate.setForeground(foreground_color);
			
			add(clear);
			clear.setBounds(160,230,100,30);
			clear.addActionListener(this);		
			clear.setFocusable(false);
			clear.setBackground(background_color);
			clear.setForeground(foreground_color);
			
			add(minimize);
			minimize.setBounds(450,10,40,40);
			minimize.addActionListener(this);
			minimize.setFocusable(false);
			minimize.setBackground(background_color);
			
			add(exit);
			exit.setBounds(495,10,40,40);
			exit.addActionListener(this);		
			exit.setFocusable(false);
			exit.setBackground(background_color);
			
			add(change_theme);
			change_theme.setBounds(50,10,40,40);
			change_theme.addActionListener(this);		
			change_theme.setFocusable(false);
			change_theme.setBackground(background_color);
			
			add(swap);
			swap.setBounds(250,10,40,40);
			swap.addActionListener(this);		
			swap.setFocusable(false);
			swap.setBackground(background_color);
			
			add(info);
			info.setBounds(10,10,17,17);
			info.addActionListener(this);		
			info.setFocusable(false);
			info.setContentAreaFilled(false);
			info.setBorderPainted(false);
		
		}
		
		
	
	
		void scrollpane() {
			
			add(sp1);
			sp1.setBounds(10,70,250,150);
			sp1.setBackground(background_color);
			sp1.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 7));
			sp1.getHorizontalScrollBar().setBackground(background_color);
			//sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			
			original_lang.setBackground(background_color);
			original_lang.setForeground(foreground_color);
			original_lang.setFont(new Font("arial",Font.BOLD,20));
			original_lang.setCaretColor(foreground_color);
			
			
			
			
			add(sp2);
			sp2.setBounds(285,70,250,150);
			sp2.setBackground(background_color);
			sp2.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 7));
			sp2.getHorizontalScrollBar().setBackground(background_color);
			//sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			
			translated_lang.setBackground(background_color);
			translated_lang.setForeground(foreground_color);
			translated_lang.setFont(new Font("arial",Font.BOLD,20));
			translated_lang.setEditable(false);
			translated_lang.setFocusable(false);
			
		}
	
	
	
	
		void comboboxes() {
			
			add(first_lang);
			first_lang.setBounds(100,10,140,40);
			first_lang.setFont(new Font("arial",Font.BOLD,15));
			first_lang.setBackground(background_color);
			first_lang.setForeground(foreground_color);
			first_lang.setFocusable(false);
			
			
			
			add(second_lang);
			second_lang.setBounds(300,10,140,40);
			second_lang.setFont(new Font("arial",Font.BOLD,15));
			second_lang.setBackground(background_color);
			second_lang.setForeground(foreground_color);
			second_lang.setFocusable(false);
			second_lang.setSelectedIndex(1);
		
		}
	
	
	

	void call_values() throws Exception {
	
		String new_original_text = original_lang.getText().replaceAll(" ", "%20").replaceAll("\\n|\\r", "%20");
		
		try {
			System.out.println(new_original_text);
			json = jr.readJsonFromUrl("https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + Api_key.api_key +
			"&text=" + new_original_text + "&lang=" + first_lang.getSelectedItem().toString().substring(0,2) + "-" + 
			second_lang.getSelectedItem().toString().substring(0,2));
			
			System.out.println(new_original_text);
			
			String substring1 = json.get("text").toString().substring(2);
			String substring2 = substring1.substring(0,(substring1.length() - 2));
			System.out.println(substring2);
			

		}
		
		catch(IOException e) {
			throw e;
		}
		
		catch(Exception e){
			throw e;
		}
	}
	
	
	
	
	
	
	void Translate() {
		
		if(!original_lang.getText().equals("")) {
		
		translated_lang.setText("loading...");
		translated_lang.setText("");
		
		
		
		try {
			
			call_values();
			
			if(json.get("code").toString().equals("213")) {
				translated_lang.setText("text is too long");
			}
		
			else if(json.get("code").toString().equals("222")) {
				translated_lang.setText("translation failed");
			}
		
			else {
				String substring1 = json.get("text").toString().substring(2);
				String substring2 = substring1.substring(0,(substring1.length() - 2));
				translated_lang.setText(substring2);
			}
	
		} 
		/*
		catch (JSONException e) {
			System.out.println("ERROR");
			translated_lang.setText("ERROR");
			e.printStackTrace();
		}
		*/
		catch(IOException e) {
			System.out.println("no connection");
			translated_lang.setText("no connection");
			e.printStackTrace();
		}
		
		catch(Exception e){
			System.out.println("ERROR");
			translated_lang.setText("ERROR");
			e.printStackTrace();
		}
		
		
	   }
	}
	
	
	
	
	void Change_theme() {

		if(background_color == light_black) {
			 background_color = white;
			 foreground_color = black;
			}
		
		else{
			background_color = light_black;
			foreground_color = white;
			
		}
		
			getContentPane().setBackground(background_color);
		 	
			translate.setBackground(background_color);
		 	translate.setForeground(foreground_color);
			minimize.setBackground(background_color);
			exit.setBackground(background_color);
			change_theme.setBackground(background_color);
			swap.setBackground(background_color);
			clear.setBackground(background_color);
			clear.setForeground(foreground_color);
			
			sp1.getHorizontalScrollBar().setBackground(background_color);
			
			original_lang.setBackground(background_color);
			original_lang.setForeground(foreground_color);
			original_lang.setCaretColor(foreground_color);
			
			sp2.setBackground(background_color);
			
			translated_lang.setBackground(background_color);
			translated_lang.setForeground(foreground_color);
			
			
			first_lang.setBackground(background_color);
			first_lang.setForeground(foreground_color);
			second_lang.setBackground(background_color);
			second_lang.setForeground(foreground_color);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	void move_frame() {
		addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent me){
	          pX=me.getX();
	           pY=me.getY();
	        }
	    });
		
		addMouseMotionListener(new MouseAdapter(){
	         public void mouseDragged(MouseEvent me){
	             setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
	         }
	     });
	}
	
	
	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == exit) {
			System.exit(0);
		}
		
		if(e.getSource() == translate) {
			Translate();
		}
		
		if(e.getSource() == clear) {
			original_lang.setText("");
		}

		if(e.getSource() == change_theme) {
			Change_theme();
		}
		
		if(e.getSource() == swap) {
			int temp = first_lang.getSelectedIndex();
			first_lang.setSelectedIndex(second_lang.getSelectedIndex());
			second_lang.setSelectedIndex(temp);
		}
		
		if(e.getSource() == minimize) {
			setState(ICONIFIED);
		}

		
		if(e.getSource() == info) {
			setAlwaysOnTop(false);
			
			String msg = "<html><ul><li>Source:https://tech.yandex.com/translate/.<br/><br/>"
						+ "<li>Creator: <b>Can Kurt<b></ul></html>";

			 JLabel label = new JLabel(msg);
		     label.setFont(new Font("arial", Font.PLAIN, 15));
			
		    JOptionPane.showMessageDialog(null, label ,"Info", JOptionPane.INFORMATION_MESSAGE);
			
			setAlwaysOnTop(true);
		}
		
		
		
	}

	
	
	
}
