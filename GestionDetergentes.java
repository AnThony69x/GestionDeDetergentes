import javax.swing.JOptionPane;
import java.io.IOException;

public class GestionDetergentes {
    public static final int MAX_LENGTH = 20; // Tamaño máximo de las pilas
    public static String Pila1[] = new String[MAX_LENGTH]; // Pila 1
    public static String Pila2[] = new String[MAX_LENGTH]; // Pila 2
    public static String Pila3[] = new String[MAX_LENGTH]; // Pila 3
    public static int cimaPila1 = -1; // Índice de la cima de la pila 1
    public static int cimaPila2 = -1; // Índice de la cima de la pila 2
    public static int cimaPila3 = -1; // Índice de la cima de la pila 3
    public static String Pilaaux[] = new String[MAX_LENGTH]; // Pila auxiliar
    public static int cimaaux = -1; // Índice de la cima de la pila auxiliar
    public static int totalDetergentes = 0; // Contador total de detergentes

    public static void main(String[] args) throws IOException {
        Menu();
    }

    public static void Menu() throws IOException {
        String salida = "====Menú Manejo Pila====\n" 
                + "1- Insertar elemento\n" 
                + "2- Eliminar elemento\n"
                + "3- Buscar elemento\n" 
                + "4- Imprimir pila\n" 
                + "5- Contar repetición\n" 
                + "6- Salir\n";
        String entra = JOptionPane.showInputDialog(null, salida);
        int op = Integer.parseInt(entra);
        Opciones(op);
    }

    public static void Opciones(int op) throws IOException {
        switch (op) {
            case 1:
                Insertar();
                Menu();
                break;
            case 2:
                Eliminar();
                Menu();
                break;
            case 3:
                Buscar();
                Menu();
                break;
            case 4:
                Imprimir();
                Menu();
                break;
            case 5:
                Contar();
                Menu();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                Menu();
                break;
        }
    }

    public static void Insertar() throws IOException {
        String tipo = JOptionPane.showInputDialog("Ingrese el número del tipo de detergente \n1: Deja\n2: Limón\n3: Torbellino Floral");
        String pilaSeleccionada = JOptionPane.showInputDialog("¿En qué pila quieres poner la caja del detergente? \n1: Pila 1 \n2: Pila 2 \n3: Pila 3");
        int tipoInt;
        int pilaInt;
        try {
            tipoInt = Integer.parseInt(tipo);
            pilaInt = Integer.parseInt(pilaSeleccionada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el tipo de detergente y la pila");
            return;
        }

        String tipoDetergente = obtenerTipoDetergente(tipoInt);
        if (tipoDetergente == null) {
            JOptionPane.showMessageDialog(null, "Número de tipo de detergente no válido");
            return;
        }

        Apilar(tipoDetergente, pilaInt);
        totalDetergentes++;
        JOptionPane.showMessageDialog(null, "Caja de detergente registrada correctamente");
    }

    public static String obtenerTipoDetergente(int tipoInt) {
        switch (tipoInt) {
            case 1:
                return "Deja";
            case 2:
                return "Limón";
            case 3:
                return "Torbellino Floral";
            default:
                return null;
        }
    }

    public static void Apilar(String tipo, int pila) {
        switch (pila) {
            case 1:
                if (cimaPila1 < MAX_LENGTH - 1) {
                    Pila1[++cimaPila1] = tipo;
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de la pila 1 al límite");
                }
                break;
            case 2:
                if (cimaPila2 < MAX_LENGTH - 1) {
                    Pila2[++cimaPila2] = tipo;
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de la pila 2 al límite");
                }
                break;
            case 3:
                if (cimaPila3 < MAX_LENGTH - 1) {
                    Pila3[++cimaPila3] = tipo;
                } else {
                    JOptionPane.showMessageDialog(null, "Capacidad de la pila 3 al límite");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Número de pila no válido");
                break;
        }
    }

    public static void Eliminar() throws IOException {
        String tipo = JOptionPane.showInputDialog("Ingrese el número del tipo de detergente a eliminar \n1: Deja\n2: Limón\n3: Torbellino Floral");
        String pilaSeleccionada = JOptionPane.showInputDialog("¿De qué pila deseas eliminar? \n1: Pila 1 \n2: Pila 2 \n3: Pila 3");
        int tipoInt;
        int pilaInt;
        try {
            tipoInt = Integer.parseInt(tipo);
            pilaInt = Integer.parseInt(pilaSeleccionada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el tipo de detergente y la pila");
            return;
        }

        if (pilaVacia(pilaInt)) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar porque la pila está vacía");
            return;
        }

        String tipoDetergente = obtenerTipoDetergente(tipoInt);
        if (tipoDetergente == null) {
            JOptionPane.showMessageDialog(null, "Número de tipo de detergente no válido");
            return;
        }

        String salio = Desapilar(tipoDetergente, pilaInt);
        if (salio != null) {
            JOptionPane.showMessageDialog(null, "El dato que salió es " + salio);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el detergente en la pila seleccionada");
        }
    }

    public static boolean pilaVacia(int pila) {
        switch (pila) {
            case 1:
                return cimaPila1 == -1;
            case 2:
                return cimaPila2 == -1;
            case 3:
                return cimaPila3 == -1;
            default:
                return true;
        }
    }

    public static String Desapilar(String tipo, int pila) {
        switch (pila) {
            case 1:
                return DesapilarDePila(Pila1, tipo, cimaPila1--);
            case 2:
                return DesapilarDePila(Pila2, tipo, cimaPila2--);
            case 3:
                return DesapilarDePila(Pila3, tipo, cimaPila3--);
            default:
                JOptionPane.showMessageDialog(null, "Número de pila no válido");
                break;
        }
        return null;
    }

    public static String DesapilarDePila(String[] pila, String tipo, int cima) {
        for (int i = cima; i >= 0; i--) {
            if (pila[i].equals(tipo)) {
                String dato = pila[i];
                pila[i] = null;
                return dato;
            }
        }
        return null;
    }

    public static void Buscar() throws IOException {
        if (totalDetergentes == 0) {
            JOptionPane.showMessageDialog(null, "No hay detergentes en la bodega");
            return;
        }

        String tipo = JOptionPane.showInputDialog("Ingrese el número del tipo de detergente a buscar \n1: Deja\n2: Limón\n3: Torbellino Floral");
        String pilaSeleccionada = JOptionPane.showInputDialog("¿En qué pila deseas buscar? \n1: Pila 1 \n2: Pila 2 \n3: Pila 3");
        int tipoInt;
        int pilaInt;
        try {
            tipoInt = Integer.parseInt(tipo);
            pilaInt = Integer.parseInt(pilaSeleccionada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el tipo de detergente y la pila");
            return;
        }

        if (pilaVacia(pilaInt)) {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
            return;
        }

        String tipoDetergente = obtenerTipoDetergente(tipoInt);
        if (tipoDetergente == null) {
            JOptionPane.showMessageDialog(null, "Número de tipo de detergente no válido");
            return;
        }

        boolean encontrado = BuscarEnPila(tipoDetergente, pilaInt);
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Elemento encontrado");
        } else {
            JOptionPane.showMessageDialog(null, "Elemento no encontrado");
        }
    }

    public static boolean BuscarEnPila(String tipo, int pila) {
        switch (pila) {
            case 1:
                return BuscarEnPilaEspecifica(Pila1, tipo);
            case 2:
                return BuscarEnPilaEspecifica(Pila2, tipo);
            case 3:
                return BuscarEnPilaEspecifica(Pila3, tipo);
            default:
                JOptionPane.showMessageDialog(null, "Número de pila no válido");
                break;
        }
        return false;
    }

    public static boolean BuscarEnPilaEspecifica(String[] pila, String tipo) {
        for (String item : pila) {
            if (tipo.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public static void Imprimir() throws IOException {
        String pilaSeleccionada = JOptionPane.showInputDialog("¿Qué pila deseas imprimir? \n1: Pila 1 \n2: Pila 2 \n3: Pila 3");
        int pilaInt;
        try {
            pilaInt = Integer.parseInt(pilaSeleccionada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la pila");
            return;
        }

        if (pilaVacia(pilaInt)) {
            JOptionPane.showMessageDialog(null, "La pila está vacía");
            return;
        }

        switch (pilaInt) {
            case 1:
                ImprimirPila("Pila 1", Pila1);
                break;
            case 2:
                ImprimirPila("Pila 2", Pila2);
                break;
            case 3:
                ImprimirPila("Pila 3", Pila3);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Número de pila no válido");
                break;
        }
    }

    public static void ImprimirPila(String nombre, String[] pila) {
        StringBuilder sb = new StringBuilder(nombre + ":\n");
        for (String item : pila) {
            if (item != null) {
                sb.append(item).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void Contar() throws IOException {
        String tipo = JOptionPane.showInputDialog("Ingrese el número del tipo de detergente a contar \n1: Deja\n2: Limón\n3: Torbellino Floral");
        int tipoInt;
        try {
            tipoInt = Integer.parseInt(tipo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el tipo de detergente");
            return;
        }

        String tipoDetergente = obtenerTipoDetergente(tipoInt);
        if (tipoDetergente == null) {
            JOptionPane.showMessageDialog(null, "Número de tipo de detergente no válido");
            return;
        }

        int total = ContarStockTotal(tipoDetergente);
        JOptionPane.showMessageDialog(null, "Hay " + total + " caja(s) de " + tipoDetergente + " en total");
    }

    public static int ContarStockTotal(String tipo) {
        return ContarStock(Pila1, tipo) + ContarStock(Pila2, tipo) + ContarStock(Pila3, tipo);
    }

    public static int ContarStock(String[] pila, String tipo) {
        int count = 0;
        for (String item : pila) {
            if (tipo.equals(item)) {
                count++;
            }
        }
        return count;
    }

    public static boolean vacia(int cima) {
        return cima == -1;
    }

    public static boolean vaciaaux() {
        return cimaaux == -1;
    }
}
