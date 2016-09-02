/*
Simplex computes evenly spaced points on the simplex
Copyright (C) 2015  Gianfranco Giulioni

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
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.filechooser.FileSystemView;
import java.util.Properties;
import java.util.Calendar;

public class GuiBuilder{
    public static int screenLeftUpX,screenLeftUpY,screenWidth,screenHeight;


    public void buildGui(){

	System.out.println("Simplex: Copyright (C) 2011 Gianfranco Giulioni.");
	System.out.println("This program comes with ABSOLUTELY NO WARRANTY.");
	System.out.println("This is free software, and you are welcome to redistribute it");
	System.out.println("under certain conditions, see http://www.gnu.org/licenses/");
       Rectangle bounds =  GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
       screenLeftUpX=bounds.x;
       screenLeftUpY=bounds.y;
       screenWidth=bounds.width;
       screenHeight=bounds.height;


       MainWindow mainWin=new MainWindow("Simplex",((GraphicsEnvironment.getLocalGraphicsEnvironment()).getDefaultScreenDevice()).getDefaultConfiguration());
       mainWin.setLocation((bounds.width-220)/2,(bounds.height-80)/2);
       mainWin.setSize(200,200);
       mainWin.setLayout(new GridLayout(6,1));
       mainWin.buildMenus();
       mainWin.buildButtons();
//       mainWin.setIconImage((new ImageIcon(""+System.getProperties().getProperty("java.class.path")+"clock.png")).getImage());
       mainWin.setVisible(true);
       mainWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
