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

    private ServerSocket servidor; //Socket
    private ControladorPaciente controlador; //Controlador da interface
    private ObjectOutputStream escritor; //Buffer de escrita
    private ObjectInputStream leitor; //Buffer de saída
    private Thread t; //Thread
    private static Servidor instancia; //Instância da classe

    /**
     * Método que retorna a única instancia do Servior. Caso não exista, cria a
     * mesma.
     *
     * @return Servidor- a instância do servidor
     */
    public static synchronized Servidor getInstancia(String numeroPorta) throws IOException {
        if (instancia == null) {
            int porta = Integer.parseInt(numeroPorta);
            instancia = new Servidor(porta);
        }
        return instancia;
    }

    /**
     * Método construtor da classe. Instancia o serverSocket, obtém a instância
     * do controlador, cria e inicia a thread
     *
     * @param porta - numero da porta do servidr
     * @throws IOException
     */
    private Servidor(int porta) throws IOException {
        this.servidor = new ServerSocket(porta);
        this.controlador = ControladorPaciente.getInstancia();
        System.out.println("\n**********servidor criado**************");
        t = new Thread(this);
        t.start();
    }

    /**
     * Método que retona a instancia do socket servidor
     *
     * @return servidor
     */
    public ServerSocket getServidor() {
        return servidor;
    }

    /**
     * Método que altera a instancia do socket servidor
     *
     * @param servidor - novo socket
     */
    public void setServidor(ServerSocket servidor) {
        this.servidor = servidor;
    }

    /**
     * Método que retona o controlador de paciente
     *
     * @return controlador
     */
    public ControladorPaciente getControlador() {
        return controlador;
    }

    /**
     * Método que altera o controlador de paciente
     *
     * @param controlador - novo controlador
     */
    public void setControlador(ControladorPaciente controlador) {
        this.controlador = controlador;
    }

    /**
     * Método que retorna o buffer de escrita
     *
     * @return escritor
     */
    public ObjectOutputStream getEscritor() {
        return escritor;
    }

    /**
     * Método que altera o buffer de escrita
     *
     * @param escritor - novo buffer
     */
    public void setEscritor(ObjectOutputStream escritor) {
        this.escritor = escritor;
    }

    /**
     * Método qe retorna o buffer de leitura
     *
     * @return leitor
     */
    public ObjectInputStream getLeitor() {
        return leitor;
    }

    /**
     * Método que altera o buffer de leitura
     *
     * @param leitor - o novo buffer
     */
    public void setLeitor(ObjectInputStream leitor) {
        this.leitor = leitor;
    }

    /**
     * Método para o envio da requisição de cadastramento de pacientes. Envia
     * para a aplicação do médico o nome e cpf do paciente. É enviado 1 paciente
     * por vez.
     *
     * @throws IOException
     * @throws JSONException
     */
    public void cadastrar() throws IOException, JSONException {
        String dados = "nula";
        //Se houver pacientes na lista a serem cadastrados 
        if (!controlador.getListaCadastrar().isEmpty()) {
            //Cria um objeto json
            JSONObject dado = new JSONObject();
            //preenche o objeto com os campos: metodos e dado
            dado.put("Metodo", "POST/cadastrarPaciente");
            dado.put("Dado", controlador.getListaCadastrar().getFirst());
            //transforma o json para string
            dados = dado.toString();
            //remove o paciente da lista, que será enviado
            controlador.getListaCadastrar().removeFirst();
        }
        //envia os dados para o cliente
        escritor.writeUTF(dados);
        escritor.flush();
        //fecha o buffer de escrita
        escritor.close();
        System.out.println("Envio de dados concluida");
    }

    /**
     * Método para o envio da requisição de remoção de pacientes. Envia para a
     * aplicação do médico o nome do paciente. Envia 1 nome por vez.
     *
     * @throws IOException
     * @throws JSONException
     */
    public void remover() throws IOException, JSONException {
        String dados = "nula";
        //Se houver pacientes na lista a serem removidos
        if (!controlador.getListaRemover().isEmpty()) {
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
        System.out.println("Dados enviados");
    }

    /**
     * Método para o envio da requisição atualização dos sinais vitais de um
     * paciente. Envia para a aplicação do médico.
     *
     * @throws IOException
     * @throws JSONException
     */
    public void atualizar() throws IOException, JSONException {
        String dados = "nula";
        //Se houver atualizacoes de dados na lista
        if (!controlador.getAtualizacoes().isEmpty()) {
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
        System.out.println("Dados enviados");
    }

    /**
     * Método que trata o dado recebido da aplicação do médico, de uma
     * requisição de notificação.
     *
     * @param dados
     * @throws JSONException
     */
    public void notificar(String dados) throws JSONException {
        //Separa a string recebida
        String[] informacoes = dados.split(":");
        //cria o objeto json
        JSONObject dado = new JSONObject();
        //preenche o objeto com os campos: Paciente e mensagem
        dado.put("Paciente", informacoes[0]);
        dado.put("Mensagem", informacoes[1]);
        //adiciona o objeto json na lista de mensagens recebidas, do controlador
        controlador.getMensagens().add(dado);
    }

    /**
     * Método run do servidor. Fica sempre aguardando uma conexão com um
     * cliente. Após a conexão, aguarda uma requisição do cliente. Se a
     * requisição for do tipo GET, busca o dado pedido pelo cliente e envia para
     * o cliente. Se a requisição for POST, apenas repassa para o controlador ou
     * interface o que foi recebido.
     */
    @Override
    public void run() {
        try {
            while (true) {
                //conecta com cliente
                Socket cliente = servidor.accept();
                System.out.println("S- ouvindo " + servidor.getLocalPort() + " Cliente: " + cliente.getInetAddress());
                //cria o leitor e escritor para o envio e recebimento de dados
                escritor = new ObjectOutputStream((cliente.getOutputStream()));
                leitor = new ObjectInputStream((cliente.getInputStream()));

                //Ler a requisicao enviada pelo cliente
                String requisicao = leitor.readUTF();
                System.out.println("S- recebido do cliente " + requisicao);

                //separa a requisicao para analisar o método e a ação a ser realizada
                String[] dados = requisicao.split("/");

                //Se a requisição recebida for GET
                if (dados[0].equals("GET")) {
                    if (dados[1].equals("cadastrarPaciente")) {
                        cadastrar();
                    } else if (dados[1].equals("removerPaciente")) {
                        remover();
                    } else if (dados[1].equals("atualizarSinais")) {
                        atualizar();
                    }
                } //Se a requisicao recebida for GET
                else if (dados[0].equals("POST")) {
                    if (dados[1].equals("notificarPaciente")) {
                        notificar(dados[2]);
                    }
                }
                //Fecha o buffer de escrita e de leitura
                escritor.close();
                leitor.close();
            }
        } catch (Exception e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }

    }
}
