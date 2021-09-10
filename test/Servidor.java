/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author casa
 */
public class Servidor implements Runnable {
    private ServerSocket servidor;
    private Thread t;
    private ControladorPaciente controlador;
    private BufferedReader leitor;
    private BufferedWriter escritor;

    public Servidor() throws IOException {
        this.servidor = new ServerSocket(5023);
        System.out.println("servidor criado");
        t = new Thread(this);
        t.start();
    }
    
    
    @Override
    public void run() {
        try {
            while(true){
                Socket cliente= servidor.accept();
                System.out.println("S- ouvindo "+servidor.getLocalPort()+ " Cliente: "+cliente.getInetAddress() );
                escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
                leitor = new BufferedReader (new InputStreamReader(cliente.getInputStream()));
                String requisicao = leitor.readLine();
                System.out.println("S- recebido do cliente "+ requisicao);
                /*String[] dados= requisicao.split("/");
                controlador = ControladorPaciente.getInstancia();
                if(dados[0].equals("GET")){
                    if(dados[1].equals("cadastrarPaciente")){
                        String resposta = "POST/cadastrarPaciente/"+ controlador.getListaCadastrar().getFirst();
                        System.out.println("dado da resposta "+resposta);
                        escritor.write(resposta);
                        escritor.flush();
                        System.out.println("S- enviado dado");
                        controlador.getListaCadastrar().removeFirst();
                    }
                }
                else if (dados[0].equals("POST")){
                }*/
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
