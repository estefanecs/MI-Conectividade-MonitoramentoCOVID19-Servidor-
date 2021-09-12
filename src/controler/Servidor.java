/**
 * Componente Curricular: Módulo Integrado de Concorrência e Conectividade
 * Autor: Estéfane Carmo de Souza
 * Data: 13/09/2021
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor,
 * tais como provindos de livros e apostilas, e páginas ou documentos
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
 */
package controler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;


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
        String dados = "nula";
        //Se houver pacientes na lista a serem cadastrados 
        if(!controlador.getListaCadastrar().isEmpty()){
            //Cria um objeto json
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "POST/cadastrarPaciente");
            dado.put("Dado", controlador.getListaCadastrar().getFirst()+"\n");
            //transforma o json para string
            dados = dado.toString ();
            //remove o paciente da lista, que será enviado
            controlador.getListaCadastrar().removeFirst();
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        //fecha o buffer de escrita
        escritor.close();
        System.out.println("S- envio de dados concluida");
    }
       
    public void remover() throws IOException, JSONException{
        String dados = "nula";
        //Se houver pacientes na lista a serem removidos
        if(!controlador.getListaRemover().isEmpty()){
            //Cria um objeto json
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "DELETE/removerPaciente");
            dado.put("Dado", controlador.getListaRemover().getFirst());
            dados = dado.toString();//transforma o json para string
            //Remove da lista o dado que foi enviado para remoção
            controlador.getListaRemover().removeFirst();
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        //fecha o buffer de escrita
        escritor.close();
        System.out.println("S- enviado dado");
    }
        
    public void atualizar() throws IOException, JSONException{
        String dados = "nula";
        //Se houver atualizacoes de dados na lista
        if(!controlador.getAtualizacoes().isEmpty()){
            //Cria um objeto json
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "PUT/atualizarSinais");
            dado.put("Dado", controlador.getAtualizacoes().getFirst());
            dados = dado.toString();//transforma o json para string
            //Remove da lista o dado que foi enviado
            controlador.getAtualizacoes().removeFirst();
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        //fecha o buffer de escrita
        escritor.close();
        System.out.println("S- enviado dado");
    }
    
    public void notificar(String dados) throws JSONException{
        //Separa a string recebida
        String[] informacoes= dados.split(":");
        //cria o objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: Paciente e mensagem
        dado.put("Paciente",informacoes[0]);
        dado.put("Mensagem",informacoes[1]);
        //adiciona o objeto json na lista do controlador de mensagens recebidas
        controlador.getMensagens().add(dado);
    }
        
@Override
    public void run() {
       try {
           while(true){
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
                //Se a requisição recebida for GET
                if(dados[0].equals("GET")){         
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
                //Se a requisicao recebida for GET
                else if(dados[0].equals("POST")){
                    if(dados[1].equals("notificarPaciente")){
                        notificar(dados[2]);
                    }
                }
                //Fecha o buffer de escrita e de leitura
                escritor.close();
                leitor.close();
           }
    }
    catch(Exception e) {
       System.out.println("Erro no servidor: " + e.getMessage());
    }

  }
}
