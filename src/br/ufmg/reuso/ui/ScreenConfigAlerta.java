package br.ufmg.reuso.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import br.ufmg.reuso.negocio.tabuleiro.SetupInteraction;


public class ScreenConfigAlerta extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    static String habilitarString = "Habilitar";
    static String desabilitarString = "Desabilitar";


    private JLabel picture;

    private int intReturn;

    public ScreenConfigAlerta(ScreenTabuleiro tabuleiro) {
        super(tabuleiro);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300,200));

        JPanel panel = new JPanel(new BorderLayout());
        //Cria os botões de rádio.
        JRadioButton habilitarButton = new JRadioButton(habilitarString);
        habilitarButton.setMnemonic(KeyEvent.VK_H);
        habilitarButton.setActionCommand(habilitarString);

        JRadioButton desabilitarButton = new JRadioButton(desabilitarString);
        desabilitarButton.setMnemonic(KeyEvent.VK_M);
        desabilitarButton.setActionCommand(desabilitarString);

        //Cria o grupo de botões de rádio.
        ButtonGroup group = new ButtonGroup();
        group.add(habilitarButton);
        group.add(desabilitarButton);

        //Registra os objetos no controle de eventos.
        habilitarButton.addActionListener(this);
        desabilitarButton.addActionListener(this);

        //Coloca os botões de rádio em uma coluna no painel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(habilitarButton);
        radioPanel.add(desabilitarButton);

        //Button OK
        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(this);
        buttonOk.setPreferredSize(new Dimension(50,30));
        buttonOk.setMaximumSize(new Dimension(50,30));


        panel.add(radioPanel, BorderLayout.LINE_START);

        panel.add(buttonOk, BorderLayout.PAGE_END);

        getRootPane().setDefaultButton(buttonOk);

        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        this.add(panel,BorderLayout.CENTER);

    }
    //=====================================================================================//
    /** Controlador de eventos dos botões. */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand()== "OK" ){
            ScreenConfigAlerta.this.dispose();
        }
        else {

            if ( e.getActionCommand().equalsIgnoreCase(habilitarString)  ){
                intReturn = 1;
            }
            else if(e.getActionCommand().equalsIgnoreCase(desabilitarString)){
                intReturn = 0;
            }
        }
    }

    //=====================================================================================//
    /**
     * Retorna o modo do jogo selecionado na tela
     *
     * 	@return - Inteiro contendo a escolha do jogador.
     *
     * 	intReturn = UserInteraction.EASY
     *	intReturn = UserInteraction.MODERATE;
     *	intReturn = UserInteraction.HARD;
     *
     */
    public static void retornaHabilitarAlerta(int retorno)
    {

    }

    public int getModeGame(){
        return intReturn;
    }

    public static ScreenConfigAlerta createAndShowConfigAlerta(ScreenTabuleiro tabuleiro) {
        //Cria e configura a tela.
        ScreenConfigAlerta scr = new ScreenConfigAlerta(tabuleiro);
        scr.rootPane.setOpaque(true);
        scr.pack();
        scr.setTitle("Alerta de progresso");
        scr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        scr.addWindowListener(ScreenInteraction.getScreenInteraction().windowsExitGame());
        scr.setModal(true);
        scr.setLocationRelativeTo(null);
        scr.setVisible(true);

        return scr;
    }

    public static void main(String[] args) {
        //O uso da Thread com a utilização de invokeLater tem a
        //função da construção total da GUI para somente então
        //apresentá-la na tela.
        final ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println (createAndShowConfigAlerta(tabuleiro).getModeGame());
            }
        });
    }
}