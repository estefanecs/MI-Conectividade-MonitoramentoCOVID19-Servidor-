/**
 * Componente Curricular: Módulo Integrado de Concorrência e Conectividade
 * Autor: Estéfane Carmo de Souza
 * Data: /09/2021
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

import java.util.LinkedList;
import org.json.JSONObject;

public class ControladorPaciente {
    
    private static ControladorPaciente instancia;
    private LinkedList<String> listaCadastrar;
    private LinkedList<String> listaRemover;
    private LinkedList<JSONObject> atualizacoes;
     

    public ControladorPaciente() {
       listaCadastrar = new LinkedList();
       listaRemover = new LinkedList();
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

    public LinkedList<JSONObject> getAtualizacoes() {
        return atualizacoes;
    }

    public void setAtualizacoes(LinkedList<JSONObject> atualizacoes) {
        this.atualizacoes = atualizacoes;
    }

    
    
}
