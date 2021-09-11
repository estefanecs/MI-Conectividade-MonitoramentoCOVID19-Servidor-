/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.ControladorPaciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author casa
 */
public class Servidor implements Runnable {
    private ServerSocket servidor;
    private ControladorPaciente controlador;
    private ObjectOutputStream escritor;
    private ObjectInputStream leitor;
     private Thread t;
    private static Servidor instancia;
    
    
     public static synchronized Servidor getInstancia() throws IOException{
        if (instancia == null){
            instancia = new Servidor();
        }
        return instancia;
    }

    public Servidor() throws IOException {
        this.servidor = new ServerSocket(5023);
        this.controlador = ControladorPaciente.getInstancia();
        t = new Thread(this);
        t.start();
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    public ControladorPaciente getControlador() {
        return controlador;
    }

    public void setControlador(ControladorPaciente controlador) {
        this.controlador = controlador;
    }

    public ObjectOutputStream getEscritor() {
        return escritor;
    }

    public void setEscritor(ObjectOutputStream escritor) {
        this.escritor = escritor;
    }

    public ObjectInputStream getLeitor() {
        return leitor;
    }

    public void setLeitor(ObjectInputStream leitor) {
        this.leitor = leitor;
    }
     
    public void cadastrar() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
         String dados = "nula";
        //Cria um objeto json
        if(!controlador.getListaCadastrar().isEmpty()){
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "POST/cadastrarPaciente");
            //dado.put("Dado", "Ana:34474774");
            dado.put("Dado", controlador.getListaCadastrar().getFirst()+"\n");
            dados = dado.toString ();
            controlador.getListaCadastrar().removeFirst();
            System.out.println("Lista de cadastro is empty? "+controlador.getListaCadastrar().isEmpty());
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        escritor.close();
        System.out.println("S- envio de dados concluida");
    }
       
    public void remover() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if de remover");
        //Cria um objeto json
        String dados = "nula";
        if(!controlador.getListaRemover().isEmpty()){
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "DELETE/removerPaciente");
            //dado.put("Dado", "Ana:34474774");
            dado.put("Dado", controlador.getListaRemover().getFirst());
            dados = dado.toString();
            System.out.println("dado da resposta "+dados);
            //Remove da lista o dado que foi enviado para remocao
            controlador.getListaRemover().removeFirst();
            System.out.println("Lista de remover is empty? "+controlador.getListaRemover().isEmpty());
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        escritor.close();
        System.out.println("S- enviado dado");
    }
        
    public void atualizar() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
        //Cria um objeto json
        String dados = "nula";
        if(!controlador.getAtualizacoes().isEmpty()){
            System.out.println("S- controlador n vazio");
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "PUT/atualizarSinais");
            dado.put("Dado", controlador.getAtualizacoes().getFirst());
            dados = dado.toString();
            System.out.println("dado da resposta "+dados);
            //Remove da lista o dado que foi enviado
            controlador.getAtualizacoes().removeFirst();
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        escritor.close();
        System.out.println("S- enviado dado");
    }
        
    //public static void main(String[] args) {
@Override
    public void run() {
       try {
           while(true){
                //Servidor_1 servidor = new Servidor(5023);
                System.out.println("\n****************servidor criado***************************");
                //conecta com cliente
                Socket cliente= servidor.accept();
                System.out.println("S- ouvindo "+servidor.getLocalPort()+ " Cliente: "+cliente.getInetAddress() );
                //cria o leitor e escritor para o envio e recebimento de dados
                escritor= new ObjectOutputStream((cliente.getOutputStream()));
                leitor= new ObjectInputStream((cliente.getInputStream())); 
                        
                //Ler a requisicao enviada pelo cliente
                String requisicao = leitor.readUTF();
                System.out.println("S- recebido do cliente "+ requisicao);
                
                 //separa a requisicao para analisar o método e a ação a ser realizada
                String[] dados= requisicao.split("/");
                System.out.println("vetor 1 " +dados[0] +" vetor 2 "+dados[1]);
                if(dados[0].equals("GET")){
                    System.out.println("s- entrei no get");
                    
                    if(dados[1].equals("cadastrarPaciente")){
                       cadastrar();
                    }
                     else if(dados[1].equals("removerPaciente")){
                        remover();
                    }
                     else if(dados[1].equals("atualizarSinais")){
                        atualizar();
                    }
                }
                else if(dados[0].equals("POST")){
                    
                }
                escritor.close();
                leitor.close();
           }
    }
    catch(Exception e) {
       System.out.println("Erro no servidor: " + e.getMessage());
    }

  }
}
