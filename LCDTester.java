import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LCDTester {

    static final String CADENA_FINAL = "Finalizar";
    
    public static void main(String[] args) {

        // Establece los segmentos de cada numero
        List<String> listaComando = new ArrayList<>();
        String comando;
        int espacioDig;
        
        try {

            try (Scanner lector = new Scanner(System.in)) {
                
                System.out.print("Espacio Entre Lineas de Digitos Deseado (0 a 5): ");
                comando = lector.next();

                // Valida si es un numero
                if (ImpresorLCD.isNumeric(comando)) 
                {
                    // es numero valido y asignado al espacioDig
                    espacioDig = Integer.parseInt(comando);
                    
                    // se valida que el espaciado este entre 0 y 5
                    if(espacioDig <0 || espacioDig >5)
                    {
                        throw new IllegalArgumentException("El espacio entre "
                                + "digitos debe ser entre 0 y 5");
                    }
                    
                } 
                else // no es numero valido
                {
                    throw new IllegalArgumentException("Cadena " + comando
                            + " no es un numero entero");
                }
                
                do
                {

                    if(listaComando.isEmpty()) {
                        System.out.print("Entrada (tamaño 1-10, digito para imprimir): ");
                    }else{
                        System.out.print("Agregar tamaño y digito, u Finalizar: ");
                    }

                    comando = lector.next();

                    if(!comando.equalsIgnoreCase(CADENA_FINAL))
                    {
                        listaComando.add(comando);
                    }
                }while (!comando.equalsIgnoreCase(CADENA_FINAL));
            }

            ImpresorLCD impresorLCD = new ImpresorLCD();

            Iterator<String> iterator = listaComando.iterator();
            while (iterator.hasNext()) 
            {
                try 
                {
                    impresorLCD.procesar(iterator.next(), espacioDig);
                } catch (Exception ex) 
                {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
        } catch (Exception ex) 
        {
            System.out.println("Error: "+ex.getMessage());
        }

    }

}
