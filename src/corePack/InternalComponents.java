package corePack;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.util.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;

import javafx.scene.layout.Border;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import com.sun.glass.events.KeyEvent;

public class InternalComponents extends JComponent implements FocusListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;
	JPanel mainPanel,subpanel;
	JMenuBar mBar;
	JMenu editMenu;
	JMenuItem miUndo, miRedo, popupMenu, bold, under, italics;
	JTextArea textArea;
	HashMap<String, Integer> hm;
	TreeMap<String, Integer> tm;
	StringBuilder builder2;
	ArrayList<String> al;
	JPopupMenu popup;
	int ctrl = 17;
	int k;
	int space = 32;
	boolean ctrlPressed = false;
	boolean spacePressed = false;
	
	Integer key = 1;
	int top =0;
	int code,flag=1,temp=0;
	char[] data22;
	ArrayList<Character> data = new ArrayList<Character>();
	ArrayList<Character> data2 = new ArrayList<Character>();
	public InternalComponents(JFrame fr) {
		
		//al = new ArrayList<String>();
		
		hm = new HashMap<String, Integer>();
		tm = new TreeMap<String, Integer>(new valueComparator(hm));
		al = new ArrayList<String>();
		popup = new JPopupMenu();
		textArea = new JTextArea();
		bold = new JMenuItem("BOLD");
		italics = new JMenuItem("italics");
		under = new JMenuItem("underline");
		bold.setFont(new Font("Tahoma", Font.PLAIN, 19));
		italics.setFont(new Font("Tahoma", Font.PLAIN, 19));
		under.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		scrollPane = new JScrollPane();
		mainPanel = new JPanel(new BorderLayout(0, 0));
		mBar = new JMenuBar();
		editMenu = new JMenu("Edit");
		editMenu.setFont(new Font("Tahoma", Font.PLAIN, 19));
		mainPanel.add(textArea, BorderLayout.CENTER);
			
		miUndo = new JMenuItem("Undo", KeyEvent.VK_Z);
		miUndo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		miUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
				
		miRedo = new JMenuItem("Redo", KeyEvent.VK_Y);
		miRedo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		miRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 29));
		textArea.setEditable(true);
		
		editMenu.add(miUndo);
		editMenu.add(miRedo);
		editMenu.add(bold);
		editMenu.add(italics);
		editMenu.add(under);
		mBar.add(editMenu);
		mainPanel.add(mBar, BorderLayout.NORTH);	
		scrollPane.setViewportView(mainPanel);
		
		fr.setContentPane(scrollPane);
		textArea.addFocusListener(this);
		
		
	
		

				
		textArea.addKeyListener(new KeyListener() {
			
			String s = "", suggest = "";
			@Override
			public void keyTyped(java.awt.event.KeyEvent keyEvnt) {
				// TODO Auto-generated method stub
				code = (int)keyEvnt.getKeyChar();
				if(code!=8 && code!=26 && code!=25){
					char dat = keyEvnt.getKeyChar();
					data.add(dat);
					data2.add(dat);
					flag = 1;
					//System.out.println("c audi "+temp);
					
				}
				
			try{
					if((int)keyEvnt.getKeyChar() == 8){
						if(flag==1){
							temp = data2.size();
							k = temp;
							data22 = new char[temp];
						//	System.out.println("c audi "+temp);
							builder2 = new StringBuilder();
							for(Character value : data2)
								builder2.append(value);
								//System.out.println(builder.toString());
							
						//	System.out.println(k);
							String s = builder2.toString();
							data22 = s.toCharArray();
							flag = 0;
						}	
						
						
						
						
					
						data22[k-1] = '$';
						//System.out.println(data22.toString());
						k--;
					}
					
				}
			
				catch(Exception e){
					e.printStackTrace();
				System.out.println("u arse! "+temp);
				}
				
				
			}
			
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(ctrl == arg0.getKeyCode()){
					ctrlPressed = false;
				}
				if(space == arg0.getKeyCode()){
					spacePressed = false;
				}
			}
			
			@Override
			public void keyPressed(java.awt.event.KeyEvent keyPress) {
				// TODO Auto-generated method stub
				//System.out.println(keyPress);
				int count =0;
				popup.removeAll();
								
				if(ctrl == keyPress.getKeyCode()){
					ctrlPressed = true;
				}
				if(space == keyPress.getKeyCode()){
					spacePressed = true;
				}
				
				if(ctrlPressed == true && spacePressed == true){
					
					hm.remove(suggest);
					tm.putAll(hm);
					generateSelection(suggest, textArea.getCaretPosition());
					//System.out.println(textArea.getCursor());
					
					//popup.setVisible(true);
					suggest = "";
					
					System.out.println(tm);
					tm.clear();
					
				}
								
				else if(java.awt.event.KeyEvent.getKeyText(keyPress.getKeyCode()).length() == 1){
					//System.out.println(keyPress.getKeyChar());
					s += keyPress.getKeyChar();
					suggest += keyPress.getKeyChar();
					
				}
				else{
					
					if(keyPress.getKeyCode() == 8){
						
						if(!(suggest.length() == 0)){
						count++;
						suggest = suggest.substring(0, suggest.length()-count);
						System.out.println(suggest);
						}
					}
					
					if(!(keyPress.isControlDown() || keyPress.getKeyCode() == 8)){
						if(s.length() != 0){
							
							if(!hm.containsKey(s)){
								hm.put(s, key);
								
							}
							
							else{
								hm.replace(s, (hm.get(s)+1));
								
							}
							
						}
						
						
						s = "";
						suggest = "";
					}
					//System.out.println(tm);
				}
			}
		});
	miUndo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StringBuilder builder = new StringBuilder();
				for(Character value : data){
					
					builder.append(value);
				}
				String s = builder.toString();
				textArea.setText(s);
			
				performUndoTask();
				
			}
		});
	bold.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
	
		
		
		}
	});
		
		miRedo.addActionListener(new ActionListener() {
			
		
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try{
				StringBuilder builder = new StringBuilder();
				for(Character value : data22){
					if(value != '$')
					builder.append(value);
				}
				String s = builder.toString();
				textArea.setText(s);
				performRedoTask();
			}
				catch(Exception e){
					
				}
			}
			
			
		});
		
		
	}
	
	protected void generateSelection(String substr, int i){
		//System.out.println(substr);
		int len = substr.length();
		Set<String> set = tm.keySet();
		for(String str : set){
			
			if(str.length() >= len){
				if(str.substring(0, len).equalsIgnoreCase(substr)){
					//System.out.println(str);
					
					al.add(str);
				}
			}
			
		}
		generatePopup(substr, i);
		al.removeAll(al);
	}
	
	private void generatePopup(String str, int i) {
		// TODO Auto-generated method stub
		//System.out.println(al);
		int len = str.length();
		if(al.size() == 1){
			
			//System.out.println(i);
			//System.out.println(i+len);
			textArea.replaceRange(al.get(0), i-len, i);
			hm.replace(al.get(0), (hm.get(al.get(0))+1));
			ctrlPressed = false;
			spacePressed = false;
		}
		else{
			
			ActionListener menuAction = new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//System.out.println(e.getActionCommand());
					textArea.replaceRange(e.getActionCommand(), i-len, i);
					hm.replace(e.getActionCommand(), (hm.get(e.getActionCommand())+1));
					ctrlPressed = false;
					spacePressed = false;
					
					popup.removeAll();
				}
			};
			
			
			//mainPanel.repaint();
			
			for(String s : al){
				popup.add(popupMenu = new JMenuItem(s));
				popupMenu.setActionCommand(s);
				popupMenu.addActionListener(menuAction);
			}
			Point pt = textArea.getCaret().getMagicCaretPosition();
			popup.show(textArea, pt.x, pt.y);
			textArea.repaint();
			ctrlPressed = false;
			spacePressed = false;
						
			
		}
	}
	

	protected void performRedoTask() {
		// TODO Auto-generated method stub
		
	}

	protected void performUndoTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
