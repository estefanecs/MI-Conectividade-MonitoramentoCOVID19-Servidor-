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
package view;

import controler.ControladorPaciente;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class DispositivosPaciente extends javax.swing.JFrame implements Runnable {
    
    private String Paciente; //Nome do paciente
    private ControladorPaciente controlador; //Controlador da interface
    private Semaphore semaforo; //Semaforo
    private boolean statusAtualizacao=true; //Status que indica se a atualização foi salva
    /**
     * Creates new form DispositivosPaciente
     */
    public DispositivosPaciente(String nome, Semaphore semaforo) {
        initComponents();
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        Paciente = nome; //Salva o nome do paciente
        nomePaciente.setText("Paciente: "+nome); //Exibe o nome do paciente selecionado
        controlador= ControladorPaciente.getInstancia(); //obtém a instancia do controlador
        this.semaforo= semaforo; //salva o semaforo recebido
        //cria e inicializa a Thread
        Thread t =new Thread(this);
        t.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        temperatura = new javax.swing.JLabel();
        freqCardiaca = new javax.swing.JLabel();
        respiracao = new javax.swing.JLabel();
        pressao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagem = new javax.swing.JTextArea();
        saturacao = new javax.swing.JLabel();
        sinalTemp = new javax.swing.JSpinner();
        sinalfcardiaca = new javax.swing.JSpinner();
        sinalResp = new javax.swing.JSpinner();
        sinalPressao = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        sinalSaturacao = new javax.swing.JSpinner();
        nomePaciente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        temperatura.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        temperatura.setText("TEMPERATURA:");

        freqCardiaca.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        freqCardiaca.setText("FREQUÊNCIA CARDÍACA:");

        respiracao.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        respiracao.setText("FREQUÊNCIA RESPIRATÓRIA:");

        pressao.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        pressao.setText("PRESSÃO ARTERIAL SISTÓLICA:");

        mensagem.setEditable(false);
        mensagem.setColumns(20);
        mensagem.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mensagem.setForeground(new java.awt.Color(255, 102, 0));
        mensagem.setRows(5);
        mensagem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 153, 51), new java.awt.Color(255, 153, 51)));
        mensagem.setCaretColor(new java.awt.Color(255, 153, 51));
        jScrollPane2.setViewportView(mensagem);

        saturacao.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        saturacao.setText("SATURAÇÃO DE OXIGÊNIO:");

        sinalTemp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sinalTemp.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, null, 1.0d));
        sinalTemp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sinalTempStateChanged(evt);
            }
        });

        sinalfcardiaca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sinalfcardiaca.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        sinalfcardiaca.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sinalfcardiacaStateChanged(evt);
            }
        });

        sinalResp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sinalResp.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        sinalResp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sinalRespStateChanged(evt);
            }
        });

        sinalPressao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sinalPressao.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        sinalPressao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sinalPressaoStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DISPOSITIVOS DE MONITORAMENTO");

        sinalSaturacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sinalSaturacao.setModel(new javax.swing.SpinnerNumberModel(0.0f, 0.0f, null, 1.0f));
        sinalSaturacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sinalSaturacaoStateChanged(evt);
            }
        });

        nomePaciente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nomePaciente.setText("Paciente:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(saturacao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sinalSaturacao, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pressao)
                                .addComponent(respiracao)
                                .addComponent(freqCardiaca)
                                .addComponent(temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(sinalTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sinalfcardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sinalResp, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sinalPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(temperatura)
                            .addComponent(sinalTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(freqCardiaca)
                            .addComponent(sinalfcardiaca, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(respiracao))
                    .addComponent(sinalResp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pressao)
                    .addComponent(sinalPressao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saturacao)
                    .addComponent(sinalSaturacao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    

    private void sinalTempStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sinalTempStateChanged

    }//GEN-LAST:event_sinalTempStateChanged

    private void sinalfcardiacaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sinalfcardiacaStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sinalfcardiacaStateChanged

    private void sinalRespStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sinalRespStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sinalRespStateChanged

    private void sinalPressaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sinalPressaoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sinalPressaoStateChanged

    private void sinalSaturacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sinalSaturacaoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_sinalSaturacaoStateChanged

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel freqCardiaca;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagem;
    private javax.swing.JLabel nomePaciente;
    private javax.swing.JLabel pressao;
    private javax.swing.JLabel respiracao;
    private javax.swing.JLabel saturacao;
    private javax.swing.JSpinner sinalPressao;
    private javax.swing.JSpinner sinalResp;
    private javax.swing.JSpinner sinalSaturacao;
    private javax.swing.JSpinner sinalTemp;
    private javax.swing.JSpinner sinalfcardiaca;
    private javax.swing.JLabel temperatura;
    // End of variables declaration//GEN-END:variables

    /**
     * Método run da Thread. A cada intervalo de tempo, obtem os dados no campos
     * de cada sinal vital salva os dados na lista atualizar do controlador e procura
     * a existência de uma mensagem de alerta para o paciente, se existe, exibe na tela
     */
    @Override
    public void run() {
        while (statusAtualizacao) {
            boolean ocupado = controlador.isThreadAtualizando(); //Verifica se há thread adicionando atualizacoes
            if (!ocupado) { //Se não houver thread adicionando atualizacoes
                try {
                    semaforo.acquire(); //Entra na região critica e garante exclusividade
                } catch (InterruptedException ex) {
                    Logger.getLogger(DispositivosPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
                controlador.setThreadAtualizando(true);//Indica que há thread adicionando atualizações
                //Cria a string dados com os campos de cada sinal vital
                String dado = Paciente + ":" + sinalTemp.getValue() + ":" + sinalfcardiaca.getValue() + ":"
                        + sinalResp.getValue() + ":" + sinalPressao.getValue() + ":" + sinalSaturacao.getValue();

                try {
                    //salva os dados de cada sinal vital, em uma lista do controlador
                    controlador.atualizar(dado);
                    //exibe na tela mensagem de alerta, caso exista
                    mensagem.setText(controlador.atualizarMensagem(Paciente));

                } catch (IOException ex) {
                    Logger.getLogger(DispositivosPaciente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(DispositivosPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
                semaforo.release(); //Sai da região critica
                controlador.setThreadAtualizando(false); //Libera o recurso
                try {
                    //Dorme por 4 segundos
                    Thread.sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DispositivosPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {//Se houver thread enviando dados, aguarda por 7 segundos
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DispositivosPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
