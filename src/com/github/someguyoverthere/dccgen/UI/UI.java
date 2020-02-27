/*
 * 
 */
package com.github.someguyoverthere.dccgen.UI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.github.someguyoverthere.dccgen.Roller;
import com.github.someguyoverthere.dccgen.character.ListLoader;
import com.github.someguyoverthere.dccgen.character.PCharacter;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Spinner;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;

public class UI {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text occupationPath;
	private Text tradeGoodPath;
	private Text trainedWeaponPath;
	private Text equipmentPath;
	private Text birthSignPath;
	private Text outputPath;

	JFileChooser chooser = new JFileChooser();// setup for file loading
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");

	File occupation;
	File tradeGood;
	File trainedWeapon;
	File equipment;
	File birthSign;
	File output;
	File configFile;

	Boolean occupationLoaded = false;
	Boolean tradeGoodLoaded = false;
	Boolean trainedWeaponLoaded = false;
	Boolean equipmentLoaded = false;
	Boolean birthSignLoaded = false;
	Boolean outputPathSelected = false;

	String outputDir;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UI window = new UI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		configFile = new File("DCCGEN.properties");
		Properties props = new Properties();
		props.setProperty("ass", "wjhy");
		try {
			props.store(new FileOutputStream(configFile), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(500, 330));
		shell.setSize(536, 494);
		shell.setText("Dungeon Crawl Classics Character Generator");// title bar

		Label lblHowWouldYou = new Label(shell, SWT.NONE);
		lblHowWouldYou.setBounds(10, 18, 247, 15);
		lblHowWouldYou.setText("How would you like to generate Ability Scores?");

		CCombo abilityScoreSpinner = new CCombo(shell, SWT.BORDER);// holds the ability score generation options
		abilityScoreSpinner.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		abilityScoreSpinner.setForeground(SWTResourceManager.getColor(0, 0, 0));
		abilityScoreSpinner.setItems(new String[] { "3d6 (As Crom intended)", "4d6 (Power Characters)",
				"2d10 (Jack's Deformed/Standard Mobs)", "Tetterdamalion's Heros (3d7, 7=6)",
				"Tatterdamalion's Gongfarmers (3d7, 7=1)", "4d7 (Supers: Sezrekan Method)", "1d16+2 (Mad O'Murrish)",
				"6d6/2 (Average Joe)" });
		abilityScoreSpinner.setBounds(269, 15, 248, 21);// end ability score options
		abilityScoreSpinner.select(0);
		abilityScoreSpinner.setEditable(false);

		Label lblHowWouldYou_1 = new Label(shell, SWT.NONE);
		lblHowWouldYou_1.setBounds(10, 51, 228, 15);
		lblHowWouldYou_1.setText("Select Hit Point generation method.");

		CCombo hitPointSpinner = new CCombo(shell, SWT.BORDER);// contains hit point generation options
		hitPointSpinner.setForeground(SWTResourceManager.getColor(0, 0, 0));
		hitPointSpinner.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		hitPointSpinner.setItems(new String[] {"1d4 (standard)", "1d4 (reroll on 1)", "2d4, keep best", "1d2+2", "1d4+2 (snake)", "Max"});
		hitPointSpinner.setBounds(269, 48, 114, 21);// end HP options
		hitPointSpinner.select(0);
		hitPointSpinner.setEditable(false);

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 78, 263, 15);
		lblNewLabel.setText("How many characters would you like to generate?");

		Spinner numOfCharacters = new Spinner(shell, SWT.BORDER);
		numOfCharacters.setForeground(SWTResourceManager.getColor(0, 0, 0));
		numOfCharacters.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		numOfCharacters.setSelection(4);
		numOfCharacters.setBounds(290, 75, 44, 22);
		formToolkit.adapt(numOfCharacters);
		formToolkit.paintBordersFor(numOfCharacters);

		Label lblOccupationList = new Label(shell, SWT.NONE);
		lblOccupationList.setText("Occupation List:");
		lblOccupationList.setBounds(20, 133, 86, 15);
		formToolkit.adapt(lblOccupationList, true, true);

		Label lblTradeGoodList = new Label(shell, SWT.NONE);
		lblTradeGoodList.setText("Trade Good List:");
		lblTradeGoodList.setBounds(20, 181, 86, 15);
		formToolkit.adapt(lblTradeGoodList, true, true);

		Label lblTrainedWeaponList = new Label(shell, SWT.NONE);
		lblTrainedWeaponList.setText("Trained Weapon List:");
		lblTrainedWeaponList.setBounds(20, 229, 111, 15);
		formToolkit.adapt(lblTrainedWeaponList, true, true);

		Button occupationBrowse = new Button(shell, SWT.NONE);// Occupation browser button
		occupationBrowse.setBounds(269, 152, 50, 25);
		formToolkit.adapt(occupationBrowse, true, true);
		occupationBrowse.setText("Browse");
		occupationBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.addChoosableFileFilter(filter);
				chooser.setDialogTitle("Please select the Occupations file.");
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					occupation = chooser.getSelectedFile();
					occupationPath.setText(occupation.getAbsolutePath());
					occupationLoaded = true;
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

				}

			}

		});

		Button tradeGoodBrowse = new Button(shell, SWT.NONE);// Trade Good browser button
		tradeGoodBrowse.setText("Browse");
		tradeGoodBrowse.setBounds(269, 200, 50, 25);
		formToolkit.adapt(tradeGoodBrowse, true, true);
		tradeGoodBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.addChoosableFileFilter(filter);
				chooser.setDialogTitle("Please select the Trade Goods file.");
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					tradeGood = chooser.getSelectedFile();
					tradeGoodPath.setText(tradeGood.getAbsolutePath());
					tradeGoodLoaded = true;
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

				}
			}

		});

		Button trainedWeaponBrowse = new Button(shell, SWT.NONE);// Trained Weapon browser button
		trainedWeaponBrowse.setText("Browse");
		trainedWeaponBrowse.setBounds(269, 248, 50, 25);
		formToolkit.adapt(trainedWeaponBrowse, true, true);
		trainedWeaponBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.addChoosableFileFilter(filter);
				chooser.setDialogTitle("Please select the Trained Weapon file.");
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					trainedWeapon = chooser.getSelectedFile();
					trainedWeaponPath.setText(trainedWeapon.getAbsolutePath());
					trainedWeaponLoaded = true;
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

				}
			}

		});

		occupationPath = new Text(shell, SWT.BORDER);
		occupationPath.setBounds(20, 154, 243, 21);
		formToolkit.adapt(occupationPath, true, true);

		tradeGoodPath = new Text(shell, SWT.BORDER);
		tradeGoodPath.setBounds(20, 202, 243, 21);
		formToolkit.adapt(tradeGoodPath, true, true);

		trainedWeaponPath = new Text(shell, SWT.BORDER);
		trainedWeaponPath.setBounds(20, 250, 243, 21);
		formToolkit.adapt(trainedWeaponPath, true, true);

		Label lblEquipmentList = new Label(shell, SWT.NONE);
		lblEquipmentList.setText("Equipment List:");
		lblEquipmentList.setBounds(20, 277, 82, 15);
		formToolkit.adapt(lblEquipmentList, true, true);

		equipmentPath = new Text(shell, SWT.BORDER);
		equipmentPath.setBounds(20, 298, 243, 21);
		formToolkit.adapt(equipmentPath, true, true);

		Button equipmentBrowse = new Button(shell, SWT.NONE);
		equipmentBrowse.setText("Browse");
		equipmentBrowse.setBounds(269, 296, 50, 25);
		formToolkit.adapt(equipmentBrowse, true, true);
		equipmentBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.addChoosableFileFilter(filter);
				chooser.setDialogTitle("Please select the equipment file.");
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					equipment = chooser.getSelectedFile();
					equipmentPath.setText(equipment.getAbsolutePath());
					equipmentLoaded = true;
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

				}
			}

		});

		Label lblBirthsignList = new Label(shell, SWT.NONE);
		lblBirthsignList.setText("Birthsign List:");
		lblBirthsignList.setBounds(20, 325, 71, 15);
		formToolkit.adapt(lblBirthsignList, true, true);

		birthSignPath = new Text(shell, SWT.BORDER);
		birthSignPath.setBounds(20, 346, 243, 21);
		formToolkit.adapt(birthSignPath, true, true);

		Button birthSignBrowse = new Button(shell, SWT.NONE);
		birthSignBrowse.setText("Browse");
		birthSignBrowse.setBounds(269, 344, 50, 25);
		formToolkit.adapt(birthSignBrowse, true, true);
		birthSignBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.addChoosableFileFilter(filter);
				chooser.setDialogTitle("Please select the birth sign file.");
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					birthSign = chooser.getSelectedFile();
					birthSignPath.setText(birthSign.getAbsolutePath());
					birthSignLoaded = true;
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {

				}
			}

		});

		Label lblOutputDirectory = new Label(shell, SWT.NONE);
		lblOutputDirectory.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblOutputDirectory.setForeground(SWTResourceManager.getColor(0, 0, 0));
		lblOutputDirectory.setText("Output Directory:");
		lblOutputDirectory.setBounds(20, 373, 92, 15);
		formToolkit.adapt(lblOutputDirectory, true, true);

		outputPath = new Text(shell, SWT.BORDER);
		outputPath.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		outputPath.setForeground(SWTResourceManager.getColor(0, 0, 0));
		outputPath.setBounds(20, 394, 243, 21);
		formToolkit.adapt(outputPath, true, true);

		Button outputBrowse = new Button(shell, SWT.NONE);
		outputBrowse.setText("Browse");
		outputBrowse.setBounds(269, 392, 50, 25);
		formToolkit.adapt(outputBrowse, true, true);
		outputBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				chooser.setDialogTitle("Where would you like to save your dead?");

				int userSelection = chooser.showSaveDialog(chooser);
				chooser.addChoosableFileFilter(filter);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					output = chooser.getSelectedFile();
					outputPath.setText(output.getAbsolutePath());
					outputPathSelected = true;

				}

			}

		});

		Button btnBringOutMy = formToolkit.createButton(shell, "BRING OUT MY DEAD", SWT.NONE);
		btnBringOutMy.setForeground(SWTResourceManager.getColor(0, 0, 0));
		btnBringOutMy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {// function that executes character generation

				if (occupationLoaded == true && tradeGoodLoaded == true && trainedWeaponLoaded == true
						&& equipmentLoaded == true && birthSignLoaded == true && outputPathSelected == true) {
					PCharacter[] characters = new PCharacter[numOfCharacters.getSelection()];// creating the array to
																								// hold characters

					for (int i = 0; i < numOfCharacters.getSelection(); i++) {
						characters[i] = new PCharacter(i + 1);// Initializes the character and assigns an ID number

					}
					abilityScoreGeneration(characters, abilityScoreSpinner.getSelectionIndex());
					hitPointGeneration(characters, hitPointSpinner.getSelectionIndex());
					occupationGeneration(characters);
					inventoryGeneration(characters);
					birthSignGeneration(characters);
					try {
						exportCharacters(characters, output,
								abilityScoreSpinner.getItem(abilityScoreSpinner.getSelectionIndex()),
								hitPointSpinner.getItem(abilityScoreSpinner.getSelectionIndex()));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					String error = "";

					if (occupationLoaded != true) {
						error += "Occupation file must be loaded.\n";
					}

					if (tradeGoodLoaded != true) {
						error += "Trade Goods file must be loaded.\n";
					}

					if (trainedWeaponLoaded != true) {
						error += "Trained Weapon file must be loaded.\n";
					}

					if (equipmentLoaded != true) {
						error += "Equipment file must be loaded.\n";
					}

					if (birthSignLoaded != true) {
						error += "Birth Sign file must be loaded.\n";
					}

					if (outputPathSelected != true) {
						error += "Output file has not been selected.\n";
					}

					JOptionPane.showMessageDialog(chooser, "The following errors have occured.\n\n" + error, "Error",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		btnBringOutMy.setBounds(197, 421, 127, 25);

	}

	public void abilityScoreGeneration(PCharacter[] characters, int generationType) {
		int[] ability;// hold reference to ability score array
		for (int i = 0; i < characters.length; i++) {
			switch (generationType) {// decides which ability score generation method to use based on user selection

			case 0:// 3d6 (As Crom intended)

				ability = Roller.rollSet(3, 6);// STR
				characters[i].setSTR(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 6);// AGI
				characters[i].setAGI(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 6);// STM
				characters[i].setSTM(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 6);// INT
				characters[i].setINT(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 6);// PER
				characters[i].setPER(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 6);// LUK
				characters[i].setLUK(ability[0] + ability[1] + ability[2]);

				break;// end 3d6

			case 1:// 4d6 (Power Characters)

				ability = Roller.rollSet(4, 6);// STR
				characters[i].setSTR(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 6);// AGI
				characters[i].setAGI(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 6);// STM
				characters[i].setSTM(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 6);// INT
				characters[i].setINT(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 6);// PER
				characters[i].setPER(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 6);// LUK
				characters[i].setLUK(ability[0] + ability[1] + ability[2] + ability[3]);

				break;// end 4d6

			case 2:// 2d10 (Jack's Deformed/Standard Mobs)

				ability = Roller.rollSet(2, 10);// STR
				characters[i].setSTR(ability[0] + ability[1]);

				ability = Roller.rollSet(2, 10);// AGI
				characters[i].setAGI(ability[0] + ability[1]);

				ability = Roller.rollSet(2, 10);// STM
				characters[i].setSTM(ability[0] + ability[1]);

				ability = Roller.rollSet(2, 10);// INT
				characters[i].setINT(ability[0] + ability[1]);

				ability = Roller.rollSet(2, 10);// PER
				characters[i].setPER(ability[0] + ability[1]);

				ability = Roller.rollSet(2, 10);// LUK
				characters[i].setLUK(ability[0] + ability[1]);

				break;

			case 3:// Tetterdamalion's Heros (3d7, 7=6)

				ability = Roller.rollSet(3, 7);// STR
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setSTR(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// AGI
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setAGI(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// STM
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setSTM(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// INT
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setINT(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// PER
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setPER(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// LUK
				ability = Roller.rollConverter(ability, 7, 6);
				characters[i].setLUK(ability[0] + ability[1] + ability[2]);

				break;

			case 4:// "Tatterdamalion's Gongfarmers (3d7, 7=1)

				ability = Roller.rollSet(3, 7);// STR
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setSTR(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// AGI
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setAGI(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// STM
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setSTM(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// INT
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setINT(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// PER
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setPER(ability[0] + ability[1] + ability[2]);

				ability = Roller.rollSet(3, 7);// LUK
				ability = Roller.rollConverter(ability, 7, 1);
				characters[i].setLUK(ability[0] + ability[1] + ability[2]);

				break;

			case 5:// 4d7 (Supers: Sezrekan Method)

				ability = Roller.rollSet(4, 7);// STR
				characters[i].setSTR(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 7);// AGI
				characters[i].setAGI(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 7);// STM
				characters[i].setSTM(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 7);// INT
				characters[i].setINT(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 7);// PER
				characters[i].setPER(ability[0] + ability[1] + ability[2] + ability[3]);

				ability = Roller.rollSet(4, 7);// LUK
				characters[i].setLUK(ability[0] + ability[1] + ability[2] + ability[3]);

				break;

			case 6:// 1d16+2 (Mad O'Murrish)

				ability = Roller.rollSet(1, 16);// STR
				characters[i].setSTR(ability[0] + 2);

				ability = Roller.rollSet(1, 16);// AGI
				characters[i].setAGI(ability[0] + 2);

				ability = Roller.rollSet(1, 16);// STM
				characters[i].setSTM(ability[0] + 2);

				ability = Roller.rollSet(1, 16);// INT
				characters[i].setINT(ability[0] + 2);

				ability = Roller.rollSet(1, 16);// PER
				characters[i].setPER(ability[0] + 2);

				ability = Roller.rollSet(1, 16);// LUK
				characters[i].setLUK(ability[0] + 2);

				break;

			case 7:// 6d6/2 (Average Joe)

				ability = Roller.rollSet(6, 6);// STR
				characters[i].setSTR(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				ability = Roller.rollSet(6, 6);// AGI
				characters[i].setAGI(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				ability = Roller.rollSet(6, 6);// STM
				characters[i].setSTM(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				ability = Roller.rollSet(6, 6);// INT
				characters[i].setINT(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				ability = Roller.rollSet(6, 6);// PER
				characters[i].setPER(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				ability = Roller.rollSet(6, 6);// LUK
				characters[i].setLUK(
						(int) (ability[0] + ability[1] + ability[2] + ability[3] + ability[4] + ability[5]) / 2);

				break;

			}// end switch
		} // end loop
	}

	public void hitPointGeneration(PCharacter[] characters, int generationType) {
		int[] hpRoll;// hold reference to rolled HP
		for (int i = 0; i < characters.length; i++) {
			switch (generationType) {// decides which Hit Point generation method to use based on user selection

			case 0:// 1d4
				hpRoll = Roller.rollSet(1, 4);
				characters[i].setHP(hpRoll[0] + characters[i].getAbilityMod(characters[i].getSTM()));

				break;

			case 1:// 1d4(reroll on 1)
				hpRoll = Roller.rollSet(1, 4);
				while (hpRoll[0] == 1) {
					hpRoll = Roller.rollSet(1, 4);

				}
				characters[i].setHP(hpRoll[0] + characters[i].getAbilityMod(characters[i].getSTM()));

				break;

			case 2:// 2d4 keep best
				hpRoll = Roller.rollSet(2, 4);
				if (hpRoll[0] < hpRoll[1]) {
					hpRoll[0] = hpRoll[1];
				}
				characters[i].setHP(hpRoll[0] + characters[i].getAbilityMod(characters[i].getSTM()));

				break;

			case 3:// 1d2+2
				hpRoll = Roller.rollSet(1, 2);
				characters[i].setHP(2 + hpRoll[0] + characters[i].getAbilityMod(characters[i].getSTM()));
				break;
				
			case 4:// 1d4+2
				hpRoll = Roller.rollSet(1, 4);
				characters[i].setHP(2 + hpRoll[0] + characters[i].getAbilityMod(characters[i].getSTM()));
				break;
				
			case 5:// Max
				characters[i].setHP(4 + characters[i].getAbilityMod(characters[i].getSTM()));
				break;

			}// end HP switch
			
			if(characters[i].getHP() < 1) {
				characters[i].setHP(1);
			}
			
		} // end loop
	}// end HPGen

	/**
	 * Generates the occupation and related items for the array of characters.
	 *
	 * @param characters the characters
	 */
	public void occupationGeneration(PCharacter[] characters) {
		ListLoader occupat = new ListLoader(occupation);
		ListLoader tGood = new ListLoader(tradeGood);
		ListLoader tWeapon = new ListLoader(trainedWeapon);
		int roll;

		for (int i = 0; i < characters.length; i++) {
			roll = Roller.rollSingle(1, occupat.getListLength()) - 1;
			characters[i].setOccupation(occupat.getItemAtIndex(roll));
			characters[i].addItem(tGood.getItemAtIndex(roll));
			characters[i].addItem(tWeapon.getItemAtIndex(roll));
		}

	}

	/**
	 * Generates an inventory and starting copper for the array of characters.
	 *
	 * @param characters the characters
	 */
	public void inventoryGeneration(PCharacter[] characters) {
		int roll;
		ListLoader equips = new ListLoader(equipment);

		for (int i = 0; i < characters.length; i++) {
			roll = Roller.rollSingle(1, equips.getListLength());
			characters[i].setCopper(Roller.rollSingle(5, 12));
			characters[i].addItem(equips.getItemAtIndex(roll));
		}

	}

	public void setSecondaryStats(PCharacter[] characters) {
		for (int i = 0; i < characters.length; i++) {
			characters[i].calculateAC();
			characters[i].calculateReflex();
			characters[i].calculateFort();
			characters[i].calculateWill();
		}
	}

	/**
	 * Generates a birth sign for the array of characters.
	 *
	 * @param characters The array containing characters.
	 */
	public void birthSignGeneration(PCharacter[] characters) {
		int roll;
		ListLoader bSign = new ListLoader(birthSign);
		for (int i = 0; i < characters.length; i++) {
			roll = Roller.rollSingle(1, bSign.getListLength());
			characters[i].setBirthSign(bSign.getItemAtIndex(roll));

		}

	}

	public void exportCharacters(PCharacter[] characters, File file, String rollMode, String HPMode)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(file);

		for (int i = 0; i < characters.length; i++) {
			ArrayList<String> inventory = characters[i].getInventory();
			
			writer.printf("Generator Settings\n");
			writer.printf("Roll Mode: %s | HP Mode: %s \n\n", rollMode, HPMode);
			writer.printf("Character: %d \n", characters[i].getNum());
			writer.printf("Strength: %d (%d)\n", characters[i].getSTR(),
					characters[i].getAbilityMod(characters[i].getSTR()));
			writer.printf("Agility: %d (%d)\n", characters[i].getAGI(),
					characters[i].getAbilityMod(characters[i].getAGI()));
			writer.printf("Stamina: %d (%d)\n", characters[i].getSTM(),
					characters[i].getAbilityMod(characters[i].getSTM()));
			writer.printf("Personality: %d (%d)\n", characters[i].getPER(),
					characters[i].getAbilityMod(characters[i].getPER()));
			writer.printf("Intellegence: %d (%d)\n", characters[i].getINT(),
					characters[i].getAbilityMod(characters[i].getINT()));
			writer.printf("Luck: %d (%d)\n\n", characters[i].getLUK(),
					characters[i].getAbilityMod(characters[i].getLUK()));
			writer.printf("AC: %d; HP: %d\n", characters[i].getAC(), characters[i].getHP());
			writer.printf("Speed: 30; Init: %d; Ref; %d; Fort: %d; Will: %d\n\n", characters[i].getReflex(),
					characters[i].getReflex(), characters[i].getFort(), characters[i].getWill());
			
			writer.printf("Equipment: ");
			
			for(int j = 0; j < inventory.size(); j++) {
				writer.printf("%s, ", inventory.get(j));
			}
			
			writer.print("\n\n");
			writer.printf("Starting Funds: %d cp\n", characters[i].getCopper());
			writer.printf("Lucky Sign: %s\n\n", characters[i].getBirthsign());
			
			
		}
		writer.close();

	}
}
