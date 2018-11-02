package br.ufmg.reuso.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A caixa de diálogo intermediária que define a configuração do jogo ou i
 * ício do jogo com configuração padrão.
 * 
 * String Retorno = createAndShowGetModo().getReturn() ;
 * 
 * Ou instanciando a classe ScreenModo como uma janela de diálogo.
 * 
 */
public class ScreenStart extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = "ReusoSimulES";
	private static final int WINDOW_WIDTH = 500;
	private static final int SPACING_UNIT = 5;
	private static final Dimension BUTTON_DIMENSION = new Dimension(120, 30);
	private static final Option defaultOption = Option.START;

	private enum Option {
		START("Iniciar"), CONFIG("Configurações"), PROJECT("Criar Projeto"), MANUAL("Manual");

		public String label;

		private Option(String label) {
			this.label = label;
		}

		public int getKey() {
			switch (this.name()) {
			case "START":
				return KeyEvent.VK_S;
			case "CONFIG":
				return KeyEvent.VK_C;
			case "PROJECT":
				return KeyEvent.VK_P;
			case "MANUAL":
				return KeyEvent.VK_M;
			default:
				return 0;
			}
		}
	}

	private String stringReturn;

	// =====================================================================================//
	/**
	 * Construtor da janela de seleção início ou configuração
	 */
	public ScreenStart(ScreenTabuleiro tabuleiro) {
		super(tabuleiro);
		this.renderWindow();
	}

	private static ArrayList<Option> getMenuOptions() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(Option.START);
		options.add(Option.CONFIG);
		options.add(Option.PROJECT);
		// #ifdef StartMenuManual
		options.add(Option.MANUAL);
		// #endif
		return options;
	}

	private void renderWindow() {
		JButton defaultButton;
		JPanel panel = new JPanel();
		this.setLayout(new BorderLayout());
		panel.setLayout(null);
		Dimension panelDimension = new Dimension(WINDOW_WIDTH,
				(SPACING_UNIT * 2 + BUTTON_DIMENSION.height) * (getMenuOptions().size() + 1));
		panel.setPreferredSize(panelDimension);

		addLabel(panel);
		defaultButton = addButtons(panel);
		add(panel);
		getRootPane().setDefaultButton(defaultButton);
		setResizable(false);
	}

	private void addLabel(JPanel panel) {
		JLabel label = new JLabel(WINDOW_TITLE, SwingConstants.CENTER);
		int posX = (panel.getPreferredSize().width / 2) - (BUTTON_DIMENSION.width/2);
		int posY = SPACING_UNIT;
		label.setPreferredSize(BUTTON_DIMENSION);
		label.setBounds(posX, posY, BUTTON_DIMENSION.width, BUTTON_DIMENSION.height);
		panel.add(label);
	}

	private JButton addButtons(JPanel panel) {
		JButton defaultButton = null;
		ArrayList<Option> options = getMenuOptions();
		int posX = (panel.getPreferredSize().width / 2) - (BUTTON_DIMENSION.width/2);
		for(int i = 0; i < options.size(); i++) {
			Option option = options.get(i);
			JButton button = new JButton(option.label);
			int posY = (i+1) * (2 * SPACING_UNIT + BUTTON_DIMENSION.height);
			button.setMnemonic(option.getKey());
			button.setActionCommand(option.name());
			button.setPreferredSize(BUTTON_DIMENSION);
			button.setBounds(posX, posY, BUTTON_DIMENSION.width, BUTTON_DIMENSION.height);
			button.addActionListener(this);
			panel.add(button);
			if (option == defaultOption) defaultButton = button;
		}
		return defaultButton;
	}

	// =====================================================================================//
	/** Controlador de eventos dos botões. */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == Option.CONFIG.name()) {
			stringReturn = e.getActionCommand();
			ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
			ScreenModo.createAndShowGetModo(tabuleiro).getModeGame();

		} else if (e.getActionCommand() == Option.START.name()) {
			ScreenStart.this.dispose();
			stringReturn = e.getActionCommand();

		} else {
			ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
			ScreenCreateProject.createAndShowGetCProject(tabuleiro);
			ScreenStart.this.dispose();
			stringReturn = e.getActionCommand();

		}
	}

	// =====================================================================================//
	/**
	 * Retorna o modo do jogo selecionado na tela
	 */
	public String getReturn() {
		return stringReturn;
	}

	// =====================================================================================//
	/**
	 * Retorna um ImageIcon ou null se o caminho for inválido.
	 * 
	 * @param path - String com o caminho da imagem
	 * @return - ImageIcon para ser inserido em um JLabel
	 */

	protected static ImageIcon createImageIcon(String path) {
		File fl = new File(path);
		if (fl.isFile()) {
			return new ImageIcon(path);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Cria a tela e a faz visível. Por segurança é o método chamado pela Thread
	 * para construção da GUI
	 * 
	 * @param tabuleiro - tabuleiro atual
	 * @return - tela com as opções.
	 */

	public static ScreenStart createAndShowGetModo(ScreenTabuleiro tabuleiro) {

		// Cria e configura a tela.
		ScreenStart scr = new ScreenStart(tabuleiro);
		scr.rootPane.setOpaque(true);
		scr.pack();
		scr.setModal(true);
		scr.setLocationRelativeTo(null);
		scr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		scr.addWindowListener(ScreenInteraction.getScreenInteraction().windowsExitGame());
		scr.setVisible(true);
		return scr;
	}

	public static void main(String[] args) {

		final ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println(createAndShowGetModo(tabuleiro).getReturn());
			}
		});
	}

}
