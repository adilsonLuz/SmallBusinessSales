/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.Resources;

import br.com.projeto.model.ItemVenda;
import br.com.projeto.view.FrmProdutos;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.projeto.view.FrmClientes;
import br.com.projeto.view.FrmDetalheVenda;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

/**
 * Classe criada como ajudante de metodos repetitivos *
 * @author Adilson Luz
 * @since Classe Criada em 03/07/2021, 23:03:25
 */
public class Helpers {

    //Cria novo objeto para telas
    FrmProdutos t = new FrmProdutos();
    FrmClientes c = new FrmClientes();
    FrmDetalheVenda it = new FrmDetalheVenda();
    

    private String ean;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    //Método para limpar tela
    public void limpaTela(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
            if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(-1);
            }
            if (component instanceof JDateChooser) {
                ((JDateChooser) component).setCalendar(null);
            }
        }
    }//fim do metodo

    //Metodo que faz conversão de data e hora para o padrão brasileiro
    public String dataHoraBr() {
        String date;
        // data/hora atual 
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = formatterHora.format(agora);

        date = data + " - " + hora;

        return date;
    }//fim do metodo

    //Metodo que faz conversão de data e hora para o padrão americano
    public String dataHoraEUA() {
        String date;
        // data/hora atual
        LocalDateTime agora = LocalDateTime.now();

        // formatar a data
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String data = formatterData.format(agora);

        // formatar a hora
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = formatterHora.format(agora);

        date = data + " - " + hora;

        return date;
    }//fim do metodo

    //Metodo que gera um código de barras valido
    public String gerarCodBar(String codBarras) {

        int[] numeros = codBarras.chars().map(Character::getNumericValue).toArray();
        int somaPares = numeros[1] * 3 + numeros[3] * 3 + numeros[5] * 3 + numeros[7] * 3 + numeros[9] * 3 + numeros[11] * 3;
        int somaImpares = numeros[0] + numeros[2] + numeros[4] + numeros[6] + numeros[8] + numeros[10];
        int resultado = somaImpares + somaPares;
        int digitoVerificador = 10 - resultado % 10;

        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }
        ean = codBarras + Integer.toString(digitoVerificador);
        System.out.println(ean);

        return ean;
    }//fim do metodo
        
    //Metodo que organiza impressao do cupom
    private void cupom(ArrayList<ItemVenda> lista, ItemVenda obj) {
        String printCP = "";

        for (int i = 0; i < lista.size(); i++) {
            printCP += i+1 +""
                    +  lista.get(i).getProduto() +"    "
                    +  lista.get(i).getQtd() +"    "
                    +  lista.get(i).getSubtotal() + "\n\r";
            
                    this.imprimirCupom("Small Business Sales"
                    + "Av. Tenente Marques 5136\n\r"
                    + " Fazendinha Stna Parnaíba\n\r"
                    + "CNPJ 00.000.000/0001-00\n\r"
                    + "Tel: (11) 2424-2757\n\r"
                    + dataHoraBr() + "\n\r"
                    + "----------------------------------\n\r"
                    + "        CUPOM NAO FISCAL          \n\r"
                    + "----------------------------------\n\r"
                    + "#    DESCRICAO      QT    SUBTOTAL\n\r"
                    + printCP + "\n\r"
                    + "----------------------------------\n\r"
                    + "VALOR                     " + it.txtTotalVenda.getText() + "\n\r"
                    + "DESCONTO                  " + it.txtObs.getText() + "\n\r"
                    + "TOTAL                     " + it.txtTotalVenda.getText() + "\n\r"
                    + "\n\r"
                    + "     OBRIGADO, VOLTE SEMPRE!!!    \n\r"
                    + "\n\r\n\r\f"                    
            );
                    
        }
    }//fim da classe
    
    //Metodo que imprime um cupom não fiscal
    public void imprimirCupom(String cupom){
        try {
            //preparando documento para imprimir
                InputStream print = new ByteArrayInputStream(cupom.getBytes());
                DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                SimpleDoc docText = new SimpleDoc(print, docFlavor, null);
                PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
                //buscando impressora
                PrintRequestAttributeSet printerAtributes = new HashPrintRequestAttributeSet();
                printerAtributes.add(new JobName("Impressao", null));
                //configurando pagina para impressao
                printerAtributes.add(OrientationRequested.PORTRAIT);
                printerAtributes.add(MediaSizeName.ISO_A4);
                //pegando a impressao formatada
                DocPrintJob printJob = impressora.createPrintJob();                
                
            try {
                //imprimindo
                printJob.print(docText, (PrintRequestAttributeSet) printerAtributes);
                
            } catch (Exception e) {
                //retorna messagem de erro de impressao
                JOptionPane.showMessageDialog(null, "Erro na impressão!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            
            print.close();
            
        } catch (Exception e) {
            
        }
    }//fim do metodo
    
}//fim da classe
