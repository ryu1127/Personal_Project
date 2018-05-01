import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class test {

	static class MyGUI extends JFrame{
		
		String fileName = "test";
		String adr = "C:/Users/Ryu Dongheon/Desktop/"+fileName+".text";
		MyGUI(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Memos");
			
			//TextArea
			JTextArea txt = new JTextArea(10,80);
			this.add(txt);
			this.add(new JScrollPane(txt));			
			
			//Menu
			JMenuBar menuBar = new JMenuBar();
			
			JMenu fileMenu = new JMenu("File");
			JMenu editMenu = new JMenu("Edit");
			
			JMenuItem save = new JMenuItem("Save");
			JMenuItem asSave = new JMenuItem("asSave");
			JMenuItem load = new JMenuItem("Load");
			JMenuItem find = new JMenuItem("Find");
			JMenuItem replace = new JMenuItem("Replace");
			
			setJMenuBar(menuBar);
			
			menuBar.add(fileMenu);
			menuBar.add(editMenu);
			
			fileMenu.add(save);
			fileMenu.add(asSave);
			fileMenu.add(load);
			
			editMenu.add(find);
			editMenu.add(replace);
			
			setSize(1000,1000);
			setVisible(true);
			setLayout(new FlowLayout());
			
			//save
			save.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					try{
					FileWriter fw = new FileWriter(adr);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(txt.getText());
					bw.close();
					fw.close();
					}catch(Exception e){
						System.out.println(e);
						
					}
				}
			});
			
			//asSave
			asSave.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					try{
						/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						setTitle("Renaming");
						
					
						//TextArea
						JTextArea txt = new JTextArea(2,20);
						add(txt);
						add(new JScrollPane(txt));		
						
						
						
						JButton btn = new JButton("저장");
					    btn.setBounds(160,160,20,20);
					    add(btn);
						btn.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0){
								try{
									fileName = txt.getText();
									FileWriter fw = new FileWriter(adr);
									BufferedWriter bw = new BufferedWriter(fw);
									bw.write(txt.getText());
									bw.close();
									fw.close();
									setVisible(false);
									}catch(Exception e){
										System.out.println(e);
									}
							}
						});
						setSize(200,200);
						setVisible(true);*/
						
						String resultStr;
						resultStr = JOptionPane.showInputDialog("Rename : ");
						fileName=resultStr;
						String adrr = "C:/Users/Ryu Dongheon/Desktop/"+fileName+".text";
						adr = new String(adrr);
						FileWriter fw = new FileWriter(adr);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(txt.getText());
						bw.close();
						fw.close();
						txt.setText("");
						
					}catch(Exception e){
						System.out.println(e);
					}
				}
			});
			
			//load
			load.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					try{
						String resultStr = JOptionPane.showInputDialog("fileName : ");
					fileName = new String(resultStr);
					String adrr = "C:/Users/Ryu Dongheon/Desktop/"+fileName+".text";
					adr = new String(adrr);
					FileReader fr = new FileReader(adr);
					BufferedReader br = new BufferedReader(fr);
					
					txt.setText("");
					String line;
					
					while((line = br.readLine()) != null){
						txt.append(line + "\n");
					}
					br.close();
					fr.close();
					}catch(Exception e){
						System.out.println(e);
					}
			}
			});
			//find
			find.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					String word = JOptionPane.showInputDialog("find word : ");
					int index = txt.getText().indexOf(word);
					JOptionPane.showMessageDialog(null, "찾고자 하는 위치의 인덱스 값은 : "+index+"입니다.", "Index", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			//replace
			replace.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					String word = JOptionPane.showInputDialog("find word : ");
					String words = JOptionPane.showInputDialog("replace word : ");
					String after = txt.getText().replaceAll(word,words);
					txt.setText("");
					txt.setText(after);
				}
			});
	}
}
	public static void main(String[] args) {
		new MyGUI();
	}
	
}