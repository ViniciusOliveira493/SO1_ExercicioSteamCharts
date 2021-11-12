package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class SteamController {
	private final String MESES[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};  
	
	public void buscar(int ano,int mes, int media) throws IOException{
		File arquivo = new File("SteamCharts.csv");
		if(arquivo.exists() && arquivo.isFile()) {
			BufferedReader buffer = criarBuffer(arquivo);
			String linha = buffer.readLine();
			while (linha != null) {
				if(linha.contains(MESES[mes-1]) && linha.contains(ano+"")) {
					String jogo[] = linha.split(",");
					if(jogo[2].equals(MESES[mes-1]) && jogo[1].equals(ano+"")) {
						double mediaPlayers = Double.parseDouble(jogo[3]);
						if(mediaPlayers >= media) {
							System.out.println(jogo[0]+" | "+jogo[3]);
						}
					}
				}
				linha = buffer.readLine();
			}
			
		}else {
			throw new IOException("O Arquivo não existe");
		}
	}
	
	public void filtrar(int ano,int mes, String diretorio, String nomeArquivo) throws IOException {
		File dir = new File(diretorio);		
		if(dir.exists() && dir.isDirectory()) {
			File arquivo = new File(diretorio,nomeArquivo);
			if(arquivo.exists() && arquivo.isFile()) {
				BufferedReader br = criarBuffer(arquivo);
				String linha = br.readLine();
	
				String nomeDoArquivo = ano+"_"+mes+".csv";
				File arq = new File(diretorio,nomeDoArquivo);
				FileWriter writer = new FileWriter(arq,false);
				PrintWriter print = new PrintWriter(writer);
				while(linha != null) {
					if(linha.contains(MESES[mes-1]) && linha.contains(ano+"")) {
						String jogo[] = linha.split(",");
						if(jogo[2].equals(MESES[mes-1]) && jogo[1].equals(ano+"")) {
							print.write(jogo[0]+";"+jogo[3]+"\r\n");
							print.flush();
						}
					}
					linha = br.readLine();
				}
				print.close();
				writer.close();
				System.out.println("\n\rO Arquivo "+nomeDoArquivo+" foi criado em "+diretorio+"\n\r");
			}else {
				throw new IOException("O arquivo não existe");
			}
		}else {
			throw new IOException("O diretório não existe");
		}
		
	}
	
	private BufferedReader criarBuffer(File arquivo) throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream(arquivo);
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader buffer = new BufferedReader(reader);
		
		return buffer;
	}
}
