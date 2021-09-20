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
import java.util.LinkedList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Esta classe é para objetos do tipo ControladorPaciente, contendo seus
 * atributos como lista de cadastrar, lista de remover, lista de atualizacoes,
 * lista de mensagens.
 *
 * Exemplo de uso:
 *
 * ControladorPaciente controlado= ControladorPaciente.getInstancia();
 */
public class ControladorPaciente {

    private static ControladorPaciente instancia; //Instancia da classe
    private LinkedList<String> listaCadastrar; //Lista de pacientes a serem cadastrados
    private LinkedList<String> listaRemover; //Lista com o nome do paciente a ser removido
    private LinkedList<String> atualizacoes; //Lista com as atualizações dos sinais vitais
    private LinkedList<JSONObject> mensagens; //Lista com as mensagens recebidas
    private boolean threadAtualizando; //Variavel para indicar se há thread na lista de atualizações

    /**
     * Método construtor da classe. Instancia as lista de pacientes para
     * cadastrar, pacientes para remover, de atualizacoes e de mensagens.
     */
    private ControladorPaciente() {
        listaCadastrar = new LinkedList();
        listaRemover = new LinkedList();
        atualizacoes = new LinkedList();
        mensagens = new LinkedList();
        threadAtualizando = false;
    }

    /**
     * Método que retorna a única instancia do Controlador. Caso não exista,
     * cria a mesma.
     *
     * @return ControladorPaciente- a instância do controlador
     */
    public static synchronized ControladorPaciente getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPaciente();
        }
        return instancia;
    }

    /**
     * Método que obtém a lista de pacientes para cadastrar
     *
     * @return listaCadastrar
     */
    public LinkedList<String> getListaCadastrar() {
        return listaCadastrar;
    }

    /**
     * Método que altera a lista de pacientes para cadastrar
     *
     * @param listaCadastrar - a nova lista
     */
    public void setListaCadastrar(LinkedList<String> listaCadastrar) {
        this.listaCadastrar = listaCadastrar;
    }

    /**
     * Método que retorna a lista de pacientes para remover
     *
     * @return listaRemover
     */
    public LinkedList<String> getListaRemover() {
        return listaRemover;
    }

    /**
     * Método que altera a lista de pacientes para remover
     *
     * @param listaRemover - a nova lista
     */
    public void setListaRemover(LinkedList<String> listaRemover) {
        this.listaRemover = listaRemover;
    }

    /**
     * Método que retona a lista de atualizações a serem feitas
     *
     * @return atualizacoes
     */
    public LinkedList<String> getAtualizacoes() {
        return atualizacoes;
    }

    /**
     * Método que altera a lista de atualizaçõess a serem feiras
     *
     * @param atualizacoes - nova lista
     */
    public void setAtualizacoes(LinkedList<String> atualizacoes) {
        this.atualizacoes = atualizacoes;
    }

    /**
     * Método que retorna a lista de mensagens recebidas
     *
     * @return mensagens
     */
    public LinkedList<JSONObject> getMensagens() {
        return mensagens;
    }

    /**
     * Método que altera a lista de mensagens
     *
     * @param mensagens - nova lista
     */
    public void setMensagens(LinkedList<JSONObject> mensagens) {
        this.mensagens = mensagens;
    }

    /**
     * Método que retorna se há thread adicionando informações na lista de
     * atualizações
     *
     * @return true - se tiver thread
     */
    public boolean isThreadAtualizando() {
        return threadAtualizando;
    }

    /**
     * Método que altera a informação de a thread está manipulando a lista de
     * atualizações
     *
     * @param threadAtualizando - a nova informação
     */
    public void setThreadAtualizando(boolean threadAtualizando) {
        this.threadAtualizando = threadAtualizando;
    }

    /**
     * Método adiciona na lista de pacientes a serem removidos, o nome do
     * paciente
     *
     * @param nome - nome do paciente a ser adicionado na lista
     */
    public void removerPaciente(String nome) {
        listaRemover.add(nome);
    }

    /**
     * Método que adiciona na lista de pacientes a serem cadastrados, as
     * informações de um paciente.
     *
     * @param nome - nome do paciente
     * @param cpf - cpf do pacientes
     */
    public void cadastrarPaciente(String nome, String cpf) {
        String cadastro = nome + ":" + cpf; //concatena o nome e o cpf do paciente
        listaCadastrar.add(cadastro); //adiciona na lista  
    }

    /**
     * Método que adiciona na lista de atualizações, os dados recebidos da
     * interface. O dado recebido é composto pelo nome, temperatura, frequência
     * cardíaca, frequência respiratória, pressão e saturação do oxigênio
     *
     * @param dado - dado a ser adicionado na lista
     * @throws IOException
     * @throws JSONException
     */
    public void atualizar(String dado) throws IOException, JSONException {
        this.atualizacoes.add(dado);

        /*APAGAR DEPOIS SÓ PARA CONTROLE */
        System.out.println("atualizei dados: " + dado + " Size dados " + atualizacoes.size());
    }

    /**
     * Método que atualiza na interface do paciente, a mensagem que o médico
     * enviou
     *
     * @param nomePaciente - nome do paciente a ser procurado
     * @return String - a mensagem recebida
     * @throws JSONException
     */
    public String atualizarMensagem(String nomePaciente) throws JSONException {
        int count = 0;
        while (count < this.mensagens.size()) {//Até que a variavel seja menor que o tamanho da lista
            //Se o nó da lista de mensagem tiver o nome do paciente
            if (this.mensagens.get(count).getString("Paciente").equals(nomePaciente)) {
                //Salva a notificacao enviada
                String notificacao = this.mensagens.get(count).getString("Mensagem");
                this.mensagens.remove(count);//remove
                return notificacao;// retorna a mensagem para a interface
            }
            count++;
        }
        return null;
    }

}
