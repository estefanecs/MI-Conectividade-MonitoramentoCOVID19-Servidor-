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

public class ControladorPaciente {
    
    private static ControladorPaciente instancia;
    private LinkedList<String> listaCadastrar;
    private LinkedList<String> listaRemover;
    private LinkedList<String> atualizacoes;
    private LinkedList<JSONObject> mensagens;
     

    public ControladorPaciente() {
       listaCadastrar = new LinkedList();
       listaRemover = new LinkedList();
       atualizacoes =new LinkedList();
       mensagens = new LinkedList();
    }
    
    public static synchronized ControladorPaciente getInstancia(){
        if (instancia == null){
            instancia = new ControladorPaciente();
        }
        return instancia;
    }

    public LinkedList<String> getListaCadastrar() {
        return listaCadastrar;
    }

    public void setListaCadastrar(LinkedList<String> listaCadastrar) {
        this.listaCadastrar = listaCadastrar;
    }

    public LinkedList<String> getListaRemover() {
        return listaRemover;
    }

    public void setListaRemover(LinkedList<String> listaRemover) {
        this.listaRemover = listaRemover;
    }
    
    public void removerPaciente(String nome){
        System.out.println("remover: "+nome);
        listaRemover.add(nome);
        System.out.println("remover isEmpty?: "+listaRemover.isEmpty()+" Size: "+listaRemover.size());
    }
    
    public void cadastrarPaciente(String nome, String cpf){
        String cadastro = nome + ":"+cpf;
        listaCadastrar.add(cadastro);
        System.out.println("cadastrar isEmpty?: "+listaCadastrar.isEmpty()+" Size: "+listaCadastrar.size());
        
    }

    public LinkedList<String> getAtualizacoes() {
        return atualizacoes;
    }

    public void setAtualizacoes(LinkedList<String> atualizacoes) {
        this.atualizacoes = atualizacoes;
    }

    public LinkedList<JSONObject> getMensagens() {
        return mensagens;
    }

    public void setMensagens(LinkedList<JSONObject> mensagens) {
        this.mensagens = mensagens;
    } 
    
    public void atualizar(String dado) throws IOException, JSONException{
       this.atualizacoes.add(dado);
       System.out.println("atualizei dados: "+dado+" Size dados "+atualizacoes.size());    
    }
    
    public String atualizarMensagem(String nomePaciente) throws JSONException{
        int count=0;
        while (count < this.mensagens.size()) {
            if (this.mensagens.get(count).getString("Paciente").equals(nomePaciente)){
                String notificacao =this.mensagens.get(count).getString("Mensagem");
                this.mensagens.remove(count);
                return notificacao;
            }
            count++;
        }
        return null;
    }
    
}
