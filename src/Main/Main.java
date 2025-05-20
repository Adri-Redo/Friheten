package Main;

import javax.swing.SwingUtilities;

import Presentacion.GUIFriheten.GUIFriheten;


public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUIFriheten();
			}

		});
	}

}
