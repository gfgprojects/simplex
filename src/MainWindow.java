/*
Simplex computes evenly spaced points on the simplex
Copyright (C) 2015 Gianfranco Giulioni

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.ParagraphView;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.math.BigDecimal;
import java.io.PrintWriter;
import java.io.IOException;
public class MainWindow extends JFrame{

    public static int dimension=3;
    public static String passo="0.1";
    public static String fileName="simplex.csv";

    public static PrintWriter out = null;

    public JLabel stato=null;
    JFrame aboutFrame,errorFrame;
    //    public static JFrame preferencesFrame;
    GraphicsConfiguration myGc=null;
    public  MainWindow(String title){
	super(title);
    }

    public  MainWindow(String title,GraphicsConfiguration gc){
	super(title,gc);
	myGc=gc;
    }

    public void buildMenus(){

		JLabel aboutLabel1 = new JLabel("Simplex ",SwingConstants.CENTER);
		JLabel aboutLabel2 = new JLabel("released under the GPL licence",SwingConstants.CENTER);
		JLabel aboutLabel3 = new JLabel("http://www.gnu.org/licenses/",SwingConstants.CENTER);
		JLabel aboutLabel4 = new JLabel(" ",SwingConstants.CENTER);
		JLabel aboutLabel5 = new JLabel("send bugs to",SwingConstants.CENTER);
		JLabel aboutLabel6 = new JLabel("g.giulioni@unich.it",SwingConstants.CENTER);


		aboutFrame=new JFrame("About Simplex");
		aboutFrame.setLayout(new GridLayout(6,1));
		aboutFrame.add(aboutLabel1);
		aboutFrame.add(aboutLabel2);
		aboutFrame.add(aboutLabel3);
		aboutFrame.add(aboutLabel4);
		aboutFrame.add(aboutLabel5);
		aboutFrame.add(aboutLabel6);
		aboutFrame.setLocation((GuiBuilder.screenWidth-600)/2,(GuiBuilder.screenHeight-280)/2);
		aboutFrame.setSize(400,200);

		JLabel errorLabel1 = new JLabel("1/difference must be an integer number",SwingConstants.CENTER);

		errorFrame=new JFrame("Simplex error");
		errorFrame.setLayout(new GridLayout(1,1));
		errorFrame.add(errorLabel1);
		errorFrame.setLocation((GuiBuilder.screenWidth-600)/2,(GuiBuilder.screenHeight-280)/2);
		errorFrame.setSize(400,200);


		/*
		preferencesFrame=new JFrame(" Simplex preferences");
	        JComponent newContentPane = new PreferencesWindowPanel();
		newContentPane.setOpaque(true);
		preferencesFrame.setContentPane(newContentPane);
		newContentPane.repaint();
		preferencesFrame.setLocation((GuiBuilder.screenWidth/2)-450,(GuiBuilder.screenHeight/2)-300);

       		preferencesFrame.setSize(450,475);
		*/



		 JMenuBar allmenus = new JMenuBar();
		 JMenu viewMenu = new JMenu("View");
		 /*
	 JMenuItem optionsMenuConfigure = new JMenuItem("preferences");
	 optionsMenuConfigure.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 //					 System.out.println("Configurare");  // code to execute when button is pressed
					 GuiBuilder.startWin.setVisible(false);
					 GuiBuilder.rectanglesWin.setVisible(false);
					 preferencesFrame.setVisible(true);
				     }
				 }
				 );
		 */
	 JMenuItem optionsMenuInfo = new JMenuItem("about");
	 optionsMenuInfo.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 //					 System.out.println("Info");  // code to execute when button is pressed
					 aboutFrame.setVisible(true);
				     }
				 }
				 );

	 //	 viewMenu.add(optionsMenuConfigure);
	 viewMenu.add(optionsMenuInfo);



		 JMenu actionsMenu = new JMenu("Actions");
	 JMenuItem actionsMenuStart = new JMenuItem("Make");
	 actionsMenuStart.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 //					 System.out.println("Configurare");  // code to execute when button is pressed
					 makeSimplex();
				     }
				 }
				 );
	 JMenuItem actionsMenuStop = new JMenuItem("Quit");
	 actionsMenuStop.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					quitSimplex(); //					 System.out.println("Info");  // code to execute when button is pressed
					 
				     }
				 }
				 );
	 /*
	 JMenuItem actionsMenuReset = new JMenuItem("Reset");
	 actionsMenuReset.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					GuiBuilder.timer.stop();
    if(GuiBuilder.orientation.equals("vertical") && GuiBuilder.direction.equals("forward")){
    GuiBuilder.actualHeight = 0;
    GuiBuilder.myPanel.setProgressVertical(GuiBuilder.actualHeight);
    GuiBuilder.startWin.setVisible(true);
    }
    if(GuiBuilder.orientation.equals("horizontal") && GuiBuilder.direction.equals("forward")){
	GuiBuilder.actualWidth=0;
    GuiBuilder.myPanel.setProgressHorizontal(GuiBuilder.actualWidth);
    GuiBuilder.startWin.setVisible(true);
    }

    if(GuiBuilder.orientation.equals("vertical") && GuiBuilder.direction.equals("backward")){
    GuiBuilder.actualHeight = GuiBuilder.rwHeight;
    GuiBuilder.myPanel.setProgressVertical(GuiBuilder.actualHeight);
    GuiBuilder.startWin.setVisible(true);
    }
    if(GuiBuilder.orientation.equals("horizontal") && GuiBuilder.direction.equals("backward")){
	GuiBuilder.actualWidth=GuiBuilder.rwWidth;
    GuiBuilder.myPanel.setProgressHorizontal(GuiBuilder.actualWidth);
    GuiBuilder.startWin.setVisible(true);
    }

				     }
				 }
				 );
	 */
	 actionsMenu.add(actionsMenuStart);
	 actionsMenu.add(actionsMenuStop);
	 //	 actionsMenu.add(actionsMenuReset);


	 allmenus.add(actionsMenu);
	 allmenus.add(viewMenu);
	 setJMenuBar(allmenus);
    }
    public void buildButtons(){

    JPanel lengthPanel=new JPanel(new GridLayout(1,2));
    //    lengthPanel.setPreferredSize(new Dimension(416,60));
    JTextField length = new JTextField("");
    length.addCaretListener(
				 new CaretListener() {
				     public void caretUpdate(CaretEvent e) {
					 if(((JTextField)e.getSource()).getText().equals("")){
					 dimension=3;

					 }
					 else{ 
					 dimension=Integer.parseInt(((JTextField)e.getSource()).getText());
					 }
				     }
				 }
				 );
    lengthPanel.add(new JLabel("  dimension"));
    lengthPanel.add(length);


    JPanel passoPanel=new JPanel(new GridLayout(1,2));
    JTextField passo = new JTextField("");
    passo.addCaretListener(
				 new CaretListener() {
				     public void caretUpdate(CaretEvent e) {
					 if(((JTextField)e.getSource()).getText().equals("")){
					 MainWindow.passo="0.1";

					 }
					 else{ 
					 MainWindow.passo=(((JTextField)e.getSource()).getText()).toString();
					 }
				     }
				 }
				 );
    passoPanel.add(new JLabel("  difference"));
    passoPanel.add(passo);

    JPanel fileNamePanel=new JPanel(new GridLayout(1,2));
    JTextField fileName = new JTextField(MainWindow.fileName);
    fileName.addCaretListener(
				 new CaretListener() {
				     public void caretUpdate(CaretEvent e) {
					 if(((JTextField)e.getSource()).getText().equals("")){
					     //					 MainWindow.passo="0.1";

					 }
					 else{ 
					 MainWindow.fileName=(((JTextField)e.getSource()).getText()).toString();
					 }
				     }
				 }
				 );
    fileNamePanel.add(new JLabel("  file"));
    fileNamePanel.add(fileName);



	 JButton quitButton = new JButton("Quit");
	 quitButton.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 quitSimplex();
				     }
				 }
				 );

	 JButton makeButton = new JButton("Make the Simplex");
	 makeButton.addActionListener(
				 new ActionListener() {
				     public void actionPerformed(ActionEvent e) {
					 makeSimplex();
				     }
				 }
				 );

	 stato=new JLabel("  state: ready");

	 add(lengthPanel);
	 add(passoPanel);
	 add(fileNamePanel);
	 add(makeButton);
	 add(quitButton);
	 add(stato);
    }
    /*
    public static void hidePreferencesWindow(){
	preferencesFrame.setVisible(false);
    }
    */
    public void makeSimplex(){
	BigDecimal numer =BigDecimal.ONE;
	BigDecimal denominat =new BigDecimal(passo);
	double rem =(numer.remainder(denominat)).doubleValue();
	if(rem==0){
		stato.setText("  state: working");
		update(getGraphics());
		System.out.println("Simplex: writing the simplex to file "+fileName);
		try{
		    out = new PrintWriter(fileName);
		}
		catch (IOException e){
		    e.printStackTrace();
		}

		BigDecimal vettore[] = new BigDecimal[dimension];
		for(int i=0;i<vettore.length;i++){
		    vettore[i]=new BigDecimal("0.0");
		}
		RDim dimension=new RDim(0);
		dimension.iterPrint(vettore);
		out.close();
		System.out.println("Simplex: file written");
		stato.setText("  state: ready");
		}
	else{
		errorFrame.setVisible(true);
	}

    }

    public void quitSimplex(){
	System.out.println("Simplex: bye");
	System.exit(0);
    }
}
