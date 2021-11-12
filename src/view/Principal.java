package view;

import java.io.IOException;
import java.util.Scanner;

import controller.SteamController;

public class Principal {
	public static void main(String[] args) {
		SteamController ctrl = new SteamController();
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.println("OPÇÔES \n\r"
								+ "1 - Buscar\n\r"
								+ "2 - Filtrar\n\r"
								+ "");
			int opcao = in.nextInt();
			int ano,mes;
			String dir,arq;
			switch (opcao) {
			case 1:
				System.out.println("Digite um ano");
				ano = in.nextInt();
				System.out.println("Digite um mês (1-12)");
				mes = in.nextInt();
				System.out.println("Digite a média de jogadores ativos");
				int media = in.nextInt();
				try {
					ctrl.buscar(ano, mes, media);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Digite um ano");
				ano = in.nextInt();
				System.out.println("Digite um mês (1-12)");
				mes = in.nextInt();
				System.out.println("Digite um nome de diretório");
				dir = in.next();
				System.out.println("Digite um nome de arquivo");
				arq = in.next();
				try {
					ctrl.filtrar(ano, mes, dir,arq);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
		
	}
}
