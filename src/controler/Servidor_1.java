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
public class Servidor_1 {
    private ServerSocket servidor;
    private ControladorPaciente controlador;
    private ObjectOutputStream escritor;
    private ObjectInputStream leitor;

    public Servidor_1(int porta) throws IOException {
        this.servidor = new ServerSocket(porta);
        System.out.println("servidor criado");
        this.controlador = ControladorPaciente.getInstancia();
        
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
       //Cria um objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: metodos e dado
        dado.put("Metodo", "POST/cadastrarPaciente");
        dado.put("Dado", "Ana:34474774");
        //dado.put("Dado", controlador.getListaCadastrar().getFirst()+"\n");
        
        //envia os dados para o cliente
        String dados = dado.toString ();
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        System.out.println("S- envio de dados concluida");
        controlador.getListaCadastrar().removeFirst();
        System.out.println("Lista de cadastro is empty? "+controlador.getListaCadastrar().isEmpty());
    }
       
        public void remover() throws IOException, JSONException{
        System.out.println("s- entrei no segundo if");
        //Cria um objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: metodos e dado
        dado.put("Metodo", "DELETE/removerPaciente");
        dado.put("Dado", controlador.getListaRemover().getFirst());
        String resposta = dado.toString ();
        System.out.println("dado da resposta "+resposta);
        //envia os dados para o cliente

        escritor.writeUTF(resposta);
        escritor.flush();
        escritor.close();
        System.out.println("S- enviado dado");
        //Remove da lista o dado que foi enviado para remocao
        controlador.getListaRemover().removeFirst();
        System.out.println("Lista de remover is empty? "+controlador.getListaRemover().isEmpty());
    }
        
    public static void main(String[] args) {

       try {
                Servidor_1 servidor = new Servidor_1(5023);
                System.out.println("servidor criado");
                //conecta com cliente
                Socket cliente= servidor.servidor.accept();
                System.out.println("S- ouvindo "+servidor.servidor.getLocalPort()+ " Cliente: "+cliente.getInetAddress() );
                //cria o leitor e escritor para o envio e recebimento de dados
                servidor.escritor= new ObjectOutputStream((cliente.getOutputStream()));
                servidor.leitor= new ObjectInputStream((cliente.getInputStream())); 
                        
                //Ler a requisicao enviada pelo cliente
                String requisicao = servidor.leitor.readUTF();
                System.out.println("S- recebido do cliente "+ requisicao);
                
                 //separa a requisicao para analisar o método e a ação a ser realizada
                String[] dados= requisicao.split("/");
                System.out.println("vetor 1 " +dados[0] +" vetor 2 "+dados[1]);
                if(dados[0].equals("GET")){
                    System.out.println("s- entrei no get");
                    
                    if(dados[1].equals("cadastrarPaciente")){
                       servidor.cadastrar();
                    }
                     else if(dados[1].equals("removerPaciente")){
                        servidor.remover();
                    }
                     else if(dados[1].equals("GET/atualizarSinais")){
                        
                    }
                }
                else if(dados[0].equals("POST")){
                    
                }
                servidor.escritor.close();
                servidor.leitor.close();
                /*escritor.writeUTF("Hello World");
                 System.out.println("S- Enviei para o cliente ");
                escritor.flush();*/  
    }
    catch(Exception e) {
       System.out.println("Erro: " + e.getMessage());
    }

  }
}
