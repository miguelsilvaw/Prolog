/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.jpl7.Query;

/**
 *
 * @author migue
 */
public class LigacaoProlog {
    private String output = "interface.pl";
    private String prolog = "";
    private ArrayList<String> dados;
    private String linha;
    private String line;
    private String preco1;
    private String musica1;
    private String estacao1;
    private String regiao1;
    private String campismo1;
    private String ambiente1;
    private String diversao1;
   
    
    public LigacaoProlog(String ficheiroProlog){
        dados = new ArrayList<>();
        prolog = "consult('" + ficheiroProlog + "')";
        Query prologQuery = new Query(prolog);
        if (prologQuery.hasSolution()) {
             } 
        prologQuery.close();
    }
    
    public DefaultTableModel getResultado(String preco, String musica, String estacao, String regiao, String campismo, String ambiente, String diversao) throws IOException{
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Pais");
        modelo.addColumn("Cidade");
        modelo.addColumn("Musica");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço Geral");
        modelo.addColumn("Preço Diário");
        modelo.addColumn("Estação");
        modelo.addColumn("Duração");
        modelo.addColumn("Campismo");
        modelo.addColumn("Preço Médio");
        modelo.addColumn("Ambiente");
        modelo.addColumn("Região");
        modelo.addColumn("Diversão");

        
        BufferedReader reader = new BufferedReader(new FileReader(output));
        line = reader.readLine();
        
         while (line != null) {
            if (line.equals(":- dynamic(fact/1), [forward,proof,basedados1].")) {
                line = reader.readLine();
                
         } else {
                String replace = line.replace("if ", "");
                String[] nm = replace.split(" and ");
                this.preco1 = nm[0];
               
                this.musica1 = nm[1];
             
                this.estacao1 = nm[2];
              
                this.regiao1= nm[3];
              
                this.campismo1 = nm[4];
              
                this.ambiente1 = nm[5];
               
                this.diversao1 = nm[6].substring(0, 9);
               
                String[] testarBD = nm[6].split("then ");
                
                linha = testarBD[1].substring(1, testarBD[1].length() - 2);
               
             if(preco.equals(preco1) && musica.equals(musica1) &&  estacao.equals(estacao1) && regiao.equals(regiao1) && campismo.equals(campismo1) && ambiente.equals(ambiente1) && diversao.equals(diversao1)) {
               dados = getFestival(linha);
                
               modelo.addRow(new Object[]{linha, dados.get(0), dados.get(1), dados.get(2), dados.get(3), dados.get(4), dados.get(5), dados.get(6), dados.get(7), dados.get(8), dados.get(9), dados.get(10), dados.get(11), dados.get(12)});
               return modelo;
               
                } else {
                     line = reader.readLine();
                 }
   }
            
         }
           reader.close();
           return modelo;
    }


    public ArrayList<String> getFestival(String linha) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("basedados1.pl"));
        line = reader.readLine();
      
        while (line != null) {
            String[] bd =line.split(",");
            String basedados = bd[0].substring(7,bd[0].length()-1);
               if (basedados.equals(linha)) {
                String[] qw = line.split(",");
                String pais = qw[1].substring(2, qw[1].length() - 1);
                
                String cidade= qw[2].substring(1, qw[2].length() - 1);
               
                String musica = qw[3].substring(1, qw[3].length() - 1);
               
                String nome  = qw[4].substring(1, qw[4].length() - 1);
                
                String precoGeral = qw[5].substring(1, qw[5].length() - 1);
                
                String precoDiario = qw[6].substring(1, qw[6].length() - 1);
                
                String estacao = qw[7].substring(1, qw[7].length() - 1);
               
                String duracao = qw[8].substring(1, qw[8].length() - 1);
               
                String campismo = qw[9].substring(1, qw[9].length() - 1);
               
                String precoMedio = qw[10].substring(1, qw[10].length() - 1);
               
                String ambiente = qw[11].substring(1, qw[11].length() - 1);
              
                String regiao = qw[12].substring(1, qw[12].length() - 1);
               
                String diversao = qw[13].substring(1, qw[13].length() - 4);
               
                dados.add(pais);
                dados.add(cidade);
                dados.add(musica);
                dados.add(nome);
                dados.add(precoGeral);
                dados.add(precoDiario);
                dados.add(estacao);
                dados.add(duracao);
                dados.add(campismo);
                dados.add(precoMedio);
                dados.add(ambiente);
                dados.add(regiao);
                dados.add(diversao);

                return dados;
            } else {
                line = reader.readLine();
            }
    }
        
            return null;
    
    }
    
}