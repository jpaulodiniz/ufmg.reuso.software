
package br.ufmg.reuso.negocio.aspectos;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.*;

import br.ufmg.reuso.negocio.dado.Dado;

public aspect SonsAspect {
	
pointcut jogouDado() : call (* Dado.sortearValor() );
	
	before() : jogouDado() {
		try {
	        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./Sons/efeito_dado.wav"));
	        AudioFormat format = inputStream.getFormat();
	        DataLine.Info info = new DataLine.Info(Clip.class, format);
	        Clip clip = (Clip)AudioSystem.getLine(info);
	        clip.open(inputStream);
	        clip.start(); 
	      } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	}
}
